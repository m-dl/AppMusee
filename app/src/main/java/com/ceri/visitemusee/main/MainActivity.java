package com.ceri.visitemusee.main;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.basket.BasketActivity;
import com.ceri.visitemusee.custom.CustomVisitActivity;
import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.entities.musee.Visit;
import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tileview.TileViewTools;
import com.ceri.visitemusee.tool.ScreenParam;
import com.ceri.visitemusee.tool.Tools;
import com.qozix.tileview.TileView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.ceri.visitemusee.tool.Tools.distanceToRange;

/**
 * Created by Maxime
 */
public class MainActivity extends AppCompatActivity implements BeaconConsumer {

	private static final String BEACON_TAG = "BeaconReferenceApp";
	private BackgroundPowerSaver backgroundPowerSaver;
	private double currentBeaconRangeDistance = 1;
	private BeaconManager beaconManager;

	private Resources resources;
	private Context m_Context;
	public static Activity m_Activity;
	private static MainActivity instance;
	private ActionBarDrawerToggle m_DrawerToggle;
	private ScreenParam param;

	@Bind(R.id.drawer_layout)
	DrawerLayout m_DrawerLayout;

	@Bind(R.id.navigationView)
	NavigationView m_NavigationView;

	@Bind(R.id.toolbar)
	Toolbar m_Toolbar;

	private TileView tileView;
	private LinearLayout linearLayout;

	// Constructor
	public MainActivity() {
		instance = this;
	}

	// Getter
	public static Context getContext() {
		if (instance == null) {
			instance = new MainActivity();
		}
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// auto pin the app
		startLockTask();
		// force BTE on
		BluetoothAdapter.getDefaultAdapter().enable();
		initBeacon();
		initObjects();
		selectLanguage();
	}

	// initiate the objects and design
	private void initObjects() {
		setContentView(R.layout.main);
		ButterKnife.bind(this);
		resources = getResources();
		m_Context = getContext();
		m_Activity = MainActivity.this;
		param = new ScreenParam();
		param.paramWindowFullScreen(getWindow());
		param.paramSetSupportActionBar(m_Toolbar, this);
		m_DrawerToggle = new ActionBarDrawerToggle(this, m_DrawerLayout, 0, 0);
		setDrawer();
		initVisit();
	}

	// initiate the map design for the current floor and add the pins
	private void initMap(int f) {
		// change these path to change map plans
		String floorPath = "maps/map_" + f + "/%col%_%row%.jpg";
		String floorPath2 = "maps/map_" + f + "/planmusee.jpg";
		linearLayout = (LinearLayout) findViewById(R.id.map);
		// multiple references
		tileView = new TileView(this);
		LinearLayout.LayoutParams tileViewLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
		linearLayout.addView(tileView, tileViewLayout);
		// size of original image at 100% scale
		tileView.setSize(4000, 2762);
		// detail levels
		tileView.addDetailLevel(1.000f, floorPath, floorPath2);
		// let's use 0-1 positioning...
		tileView.defineRelativeBounds(0, 0, 1, 1);
		// center markers along both axes
		tileView.setMarkerAnchorPoints(-0.5f, -0.5f);
		// add marker event when touched
		tileView.addMarkerEventListener(TileViewTools.markerEventListener);
		// scale it down to manageable size
		tileView.setScale(0.5);
		// center the frame
		TileViewTools.frameTo(tileView, 0.5, 0.5);
		// add pins
		initPins();
	}

	// add pins from IP on the map
	private void initPins() {
		// if a visit is running, list the IP on the map
		if(AppParams.getInstance().getCurrentVisit() != null) {
			ArrayList<InterestPoint> IPArray = AppParams.getInstance().getCurrentVisit().getIP();
			for(InterestPoint IP : IPArray) {
				TileViewTools.addPin(tileView, getContext(), IP);
			}
		}
	}

	// initiate Beacon manager
	private void initBeacon() {
		beaconManager = org.altbeacon.beacon.BeaconManager.getInstanceForApplication(this);
		beaconManager.bind(this);

		// By default the AndroidBeaconLibrary will only find AltBeacons.  If you wish to make it
		// find a different type of beacon, you must specify the byte layout for that beacon's
		// advertisement with a line like below.  The example shows how to find a beacon with the
		// same byte layout as AltBeacon but with a beaconTypeCode of 0xaabb.  To find the proper
		// layout expression for other beacon types, do a web search for "setBeaconLayout"
		// including the quotes.
		//
		beaconManager.getBeaconParsers().clear();
		beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));

		Log.d(BEACON_TAG, "setting up background monitoring for beacons and power saving");
		// wake up the app when a beacon is seen
		//Region region = new Region("backgroundRegion", null, null, null);
		//regionBootstrap = new RegionBootstrap(this, region);

		// simply constructing this class and holding a reference to it in your custom Application
		// class will automatically cause the BeaconLibrary to save battery whenever the application
		// is not visible.  This reduces bluetooth power usage by about 60%
		backgroundPowerSaver = new BackgroundPowerSaver(this);
	}

	// init a visit
	private void initVisit() {
		Visit v = new Visit(getString(R.string.action_section_1), getString(R.string.action_section_en_1));
		AppParams.getInstance().setCurrentVisit(v);
		if(linearLayout != null)
			linearLayout.removeAllViewsInLayout();
		initMap(Tools.MAP_ONE);
		if(AppParams.getInstance().getM_french())
			renameActionBar(getString(R.string.action_section_0));
		else
			renameActionBar(getString(R.string.action_section_en_0));
	}

	// set en or fr language for the app and set some texts
	private void selectLanguage() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(R.string.set_english_app_title);
		alertDialogBuilder
				.setMessage(R.string.set_english_app)
				.setCancelable(false)
				.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						AppParams.getInstance().setM_french(false);
						FileManager.renameMenuItems(m_NavigationView, AppParams.getInstance().getM_french());
						renameActionBar(resources.getString(R.string.app_name_en));
						dialog.cancel();
					}
				})
				.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						AppParams.getInstance().setM_french(true);
						dialog.cancel();
					}
				});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	// set action bar text
	private void renameActionBar(String s) {
		android.support.v7.app.ActionBar actionBar = getSupportActionBar();
		if (actionBar != null)
			actionBar.setTitle(s);
	}

	// initiate the drawer design for the menu
	public void setDrawer() {
		m_DrawerLayout.setDrawerListener(m_DrawerToggle);
		m_NavigationView.getMenu().findItem(R.id.all_visits_item).setChecked(true);
		m_NavigationView.setNavigationItemSelectedListener(
				new NavigationView.OnNavigationItemSelectedListener() {
					MenuItem m_menuItem;

					@Override
					public boolean onNavigationItemSelected(MenuItem menuItem) {
						m_DrawerLayout.closeDrawers();
						m_menuItem = menuItem;
						//TODO: gerer les checkable du menu selon les activities
						if(!menuItem.isChecked()) {
							menuItem.setChecked(true);
							new Handler().postDelayed(new Runnable() {
								@Override
								public void run() {
									navigationDrawerItemSelected(m_menuItem);
								}
							}, 250);
						}
						return false;
					}
				});
	}

	// get item clicked in the menu
	public void navigationDrawerItemSelected(MenuItem menuItem) {
		// full visit item
		if (menuItem.getItemId() == R.id.all_visits_item) {
			initVisit();
		}
		// custom visit item
		else if (menuItem.getItemId() == R.id.custom_visits_item) {
			Intent intent = new Intent(MainActivity.m_Activity, CustomVisitActivity.class);
			ActivityCompat.startActivity(MainActivity.m_Activity, intent, null);
		}
		// basket item
		else if (menuItem.getItemId() == R.id.basket_item) {
			Intent intent = new Intent(MainActivity.m_Activity, BasketActivity.class);
			ActivityCompat.startActivity(MainActivity.m_Activity, intent, null);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(true);
		tileView.clear();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(false);
		tileView.resume();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		beaconManager.unbind(this);
		tileView.destroy();
		tileView = null;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		m_DrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return m_DrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
	}

	@Override
	public void onBeaconServiceConnect() {
		beaconManager.addRangeNotifier(new RangeNotifier() {
			@Override
			public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
				if (beacons.size() > 0) {
					Beacon firstBeacon = beacons.iterator().next();
					int tmpBeaconRangeDistance = distanceToRange(firstBeacon.getDistance());
					if(currentBeaconRangeDistance != tmpBeaconRangeDistance) {
						Intent intent = new Intent(MainActivity.m_Activity, NewRoomActivity.class);
						intent.putExtra(Tools.ROOM, tmpBeaconRangeDistance);
						ActivityCompat.startActivity(MainActivity.m_Activity, intent, null);
					}
					currentBeaconRangeDistance = tmpBeaconRangeDistance;
				}
			}
		});

		try {
			beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
		} catch (RemoteException e) {   }
	}
}
