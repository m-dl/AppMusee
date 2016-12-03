//package com.ceri.visitemusee.beacon;
//
//import java.util.Collection;
//
//import android.app.Activity;
//
//import android.os.Bundle;
//import android.os.RemoteException;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.ceri.visitemusee.R;
//import com.ceri.visitemusee.main.MainActivity;
//
//import org.altbeacon.beacon.Beacon;
//import org.altbeacon.beacon.BeaconConsumer;
//import org.altbeacon.beacon.BeaconManager;
//import org.altbeacon.beacon.RangeNotifier;
//import org.altbeacon.beacon.Region;
//
//public class RangingActivity extends Activity implements BeaconConsumer {
//    protected static final String TAG = "RangingActivity";
//    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        beaconManager.bind(this);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        beaconManager.unbind(this);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(true);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(false);
//    }
//
//}
