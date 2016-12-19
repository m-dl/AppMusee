package com.ceri.visitemusee.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ceri.visitemusee.R;
import com.ceri.visitemusee.params.AppParams;
import com.ceri.visitemusee.tileview.TileViewTools;
import com.ceri.visitemusee.tool.ScreenParam;
import com.ceri.visitemusee.tool.Tools;
import com.qozix.tileview.TileView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewRoomActivity extends AppCompatActivity {

    private ScreenParam param;
    private int room;

    private TileView tileView;
    private LinearLayout linearLayout;

    @Bind(R.id.newroomtext)
    TextView newRoomText;

    @Bind(R.id.newroombutton)
    Button newRoomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initObjects();
    }

    // initiate the objects and design
    private void initObjects() {
        setContentView(R.layout.activity_new_room);
        ButterKnife.bind(this);
        param = new ScreenParam();
        param.paramWindowFullScreen(getWindow());

        //Getting the room number
        room = getIntent().getIntExtra(Tools.ROOM, Tools.MAP_ONE);

        // set text depending language
        if(AppParams.getInstance().getM_french())
            newRoomText.setText(getString(R.string.new_room_fr_1) + room + " " + getString(R.string.new_room_fr_2));
        else
            newRoomText.setText(getString(R.string.new_room_en_1) + room + " " + getString(R.string.new_room_en_2));

        // init map
        initMap(room);

        newRoomButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tileView.destroy();
        tileView = null;
    }
}