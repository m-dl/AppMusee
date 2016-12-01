package com.ceri.visitemusee.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.entities.musee.InterestPoint;
import com.ceri.visitemusee.entities.musee.Location;
import com.ceri.visitemusee.entities.musee.Visit;
import com.ceri.visitemusee.files.FileManager;
import com.ceri.visitemusee.info.InfoActivity;
import com.ceri.visitemusee.overview.OverviewActivity;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tileview.TileViewTools;
import com.ceri.visitemusee.tool.ScreenParam;
import com.ceri.visitemusee.tool.TakePicture;
import com.qozix.tileview.TileView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ceri.visitemusee.tool.ConnectionManager.isNetworkAvailable;

public class MainActivity extends AppCompatActivity {

	// intent result code
	private static final int LAUNCH_VISIT = 100;
	private int updateActivityNb = 0;
	private Resources resources;
	private Context m_Context;
	public static Activity m_Activity;
	private static MainActivity instance;
	private ActionBarDrawerToggle m_DrawerToggle;
	private ScreenParam param;
	private FileManager FM;

	@Bind(R.id.drawer_layout)
	DrawerLayout m_DrawerLayout;

	@Bind(R.id.navigationView)
	NavigationView m_NavigationView;

	@Bind(R.id.toolbar)
	Toolbar m_Toolbar;

	@Bind(R.id.info)
	FloatingActionButton m_FABInfo;

	@Bind(R.id.map_floors_up)
	FloatingActionButton m_FABFloorsUp;

	@Bind(R.id.map_floors_down)
	FloatingActionButton m_FABFloorsDown;

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
		// empty visitors pictures folder
		TakePicture.updateVisitorPhoto();
		// if wifi -> try to updata media
		if(isNetworkAvailable()) {
			FileManager.UpdateMedia();
		}
		initObjects();
		selectLanguage();
		// create visits dynamically and the menu
		FileManager.ListVisits(m_NavigationView, AppParams.getInstance().getM_french());
	}

	// initiate the objects and design
	private void initObjects() {
		setContentView(R.layout.main);
		ButterKnife.bind(this);
		FM = FileManager.getInstance();
		resources = getResources();
		m_Context = getContext();
		m_Activity = MainActivity.this;
		param = new ScreenParam();
		param.paramWindowFullScreen(getWindow());
		param.paramSetSupportActionBar(m_Toolbar, this);
		m_DrawerToggle = new ActionBarDrawerToggle(this, m_DrawerLayout, 0, 0);
		setDrawer();
		presentTheDrawer();
		initMap(Location.FLOOR_ONE);
		// hide info visit button, no visit launched by default
		m_FABInfo.hide();
	}

	// initiate the map design for the current floor and add the pins
	private void initMap(int f) {
		// change these path to change map plans
		String floorPath = "maps/floor_" + f + "/%col%_%row%.jpg";
		String floorPath2 = "maps/floor_" + f + "/planchateau.jpg";
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
		initPins(f);
	}

	// add pins from IP on the map
	private void initPins(int f) {
		// if a visit is running, list the IP on the map for the current floor
		if(AppParams.getInstance().getCurrentVisit() != null) {
			ArrayList<InterestPoint> IPArray = new ArrayList<InterestPoint>();
			if(f == Location.FLOOR_ONE)
				IPArray = AppParams.getInstance().getCurrentVisit().getIP1();
			else if(f == Location.FLOOR_TWO)
				IPArray = AppParams.getInstance().getCurrentVisit().getIP2();
			else if(f == Location.FLOOR_THREE)
				IPArray = AppParams.getInstance().getCurrentVisit().getIP3();
			for(InterestPoint IP : IPArray) {
				TileViewTools.addPin(tileView, getContext(), IP);
			}
			// set en or fr text
			if(AppParams.getInstance().getM_french())
				renameActionBar(AppParams.getInstance().getCurrentVisit().getName() +
						resources.getString(R.string.etage_toolbar) +
						AppParams.getInstance().getCurrentFloor() +
						resources.getString(R.string.total_etage));
			else
				renameActionBar(AppParams.getInstance().getCurrentVisit().getNameEN() +
						resources.getString(R.string.etage_toolbar_en) +
						AppParams.getInstance().getCurrentFloor() +
						resources.getString(R.string.total_etage));
		}
		else {
			// set en or fr text
			if (AppParams.getInstance().getM_french())
				renameActionBar(resources.getString(R.string.app_name) +
						resources.getString(R.string.etage_toolbar) +
						AppParams.getInstance().getCurrentFloor() +
						resources.getString(R.string.total_etage));
			else
				renameActionBar(resources.getString(R.string.app_name_en) +
						resources.getString(R.string.etage_toolbar_en) +
						AppParams.getInstance().getCurrentFloor() +
						resources.getString(R.string.total_etage));
		}
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
						m_NavigationView.getMenu().clear();
						FileManager.ListVisits(m_NavigationView, AppParams.getInstance().getM_french());
						renameActionBar(resources.getString(R.string.app_name_en) +
								resources.getString(R.string.etage_toolbar_en) +
								AppParams.getInstance().getCurrentFloor() +
								resources.getString(R.string.total_etage));
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

	// open the menu
	private void presentTheDrawer() {
		m_DrawerLayout.openDrawer(GravityCompat.START);
	}

	// launch visit overview activity
	private void prepareVisit(String title) {
		Visit visit = FM.getChateauWorkspace().searchVisit(title, AppParams.getInstance().getM_french());
		launchVisitOverview(visit);
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
		m_DrawerLayout.isDrawerOpen(m_NavigationView);
		m_NavigationView.setNavigationItemSelectedListener(
				new NavigationView.OnNavigationItemSelectedListener() {
					MenuItem m_menuItem;

					@Override
					public boolean onNavigationItemSelected(MenuItem menuItem) {
						m_DrawerLayout.closeDrawers();
						m_menuItem = menuItem;
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								m_menuItem.setChecked(true);
								navigationDrawerItemSelected(m_menuItem.getItemId(), m_menuItem.getTitle().toString());
							}
						}, 250);
						return false;
					}
				});
	}

	// get item clicked in the menu
	public void navigationDrawerItemSelected(int position, String title) {
		if (position > 0) {
			// a visit is clicked
			if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
				// set the visit and initiate it with overview
				prepareVisit(title);
			} else
				Toast.makeText(m_Context, resources.getString(R.string.memory_access_error), Toast.LENGTH_LONG).show();
		} else {
			// take picture item
			if (title.equals(resources.getString(R.string.action_section_1)) || title.equals(resources.getString(R.string.action_section_english_1))) {
				TakePicture takePicture = new TakePicture(m_Activity);
				takePicture.photo();
			}
		}
	}

	// button visit info
	@OnClick(R.id.info)
	public void onInfoClick() {
		// launch visit info activity
		launchVisitInfo();
	}

	// button to go up a floor
	@OnClick(R.id.map_floors_up)
	public void onFloorUpClick() {
		if(AppParams.getInstance().getCurrentFloor() < Location.FLOOR_THREE) {
			int tmpFloor = AppParams.getInstance().getCurrentFloor() + 1;
			AppParams.getInstance().setCurrentFloor(tmpFloor);
			// remove all view or it doesn't work
			linearLayout.removeAllViewsInLayout();
			// rebuild the new map
			initMap(tmpFloor);
		}
	}

	// button to go down a floor
	@OnClick(R.id.map_floors_down)
	public void onFloorDownClick() {
		if(AppParams.getInstance().getCurrentFloor() > Location.FLOOR_ONE) {
			int tmpFloor = AppParams.getInstance().getCurrentFloor() - 1;
			AppParams.getInstance().setCurrentFloor(tmpFloor);
			// remove all view or it doesn't work
			linearLayout.removeAllViewsInLayout();
			// rebuild the new map
			initMap(tmpFloor);
		}
	}

	// if overview confirms, we launch the visit, or we do nothing
	private void launchVisit(Intent aData) {
		if (aData != null) {
			// get the bool response
			boolean b = aData.getBooleanExtra("LaunchFlag", false);
			Visit visit = (Visit) aData.getSerializableExtra("Visit");
			if(b && visit != null) {
				AppParams.getInstance().setCurrentVisit(visit);
				// remove all view or it doesn't work
				linearLayout.removeAllViewsInLayout();
				// rebuild the new map
				initMap(AppParams.getInstance().getCurrentFloor());
				if (!AppParams.getInstance().getM_french())
					renameActionBar(visit.getNameEN() + resources.getString(R.string.etage_toolbar_en) +
							AppParams.getInstance().getCurrentFloor() + resources.getString(R.string.total_etage));
				else
					renameActionBar(visit.getName() + resources.getString(R.string.etage_toolbar) +
							AppParams.getInstance().getCurrentFloor() + resources.getString(R.string.total_etage));
				// show info visit button
				m_FABInfo.show();
			}
		}
	}

	// launch overview activity
	private void launchVisitOverview(Visit v) {
		Intent intent = new Intent(m_Activity, OverviewActivity.class);
		intent.putExtra("Overview", v);
		ActivityCompat.startActivityForResult(m_Activity, intent, LAUNCH_VISIT, null);
	}

	// launch info activity
	private void launchVisitInfo() {
		Intent intent = new Intent(m_Activity, InfoActivity.class);
		ActivityCompat.startActivity(m_Activity, intent, null);
	}

	@Override
	public void onPause() {
		super.onPause();
		tileView.clear();
	}

	@Override
	public void onResume() {
		super.onResume();
		tileView.resume();
		// reload menu if update medias - it's activity nb < 4
		if(updateActivityNb < 3) {
			m_NavigationView.getMenu().clear();
			FileManager.ListVisits(m_NavigationView, AppParams.getInstance().getM_french());
			FM.Init();
			updateActivityNb++;
		}
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
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

	// activity result manager
	@Override
	protected void onActivityResult(
			int requestCode, int resultCode, Intent data
	) {
		switch (requestCode) {
			// case overview visit returns result to start or cancel the visit
			case LAUNCH_VISIT:
				launchVisit(data);
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
