package com.example.aayushsrivastava.project12.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.aayushsrivastava.project12.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class MainActivity2 extends AppCompatActivity {

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRegistrationBroadcastReceiver =new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().endsWith(GCMRegistrationIntentService.REGISTRATION_SUCCESS)){
                    String token = intent.getStringExtra("token");
                    Toast.makeText(getApplicationContext(),"GCM token:" + token, Toast.LENGTH_LONG).show();

                }
                else if (intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_ERROR)){

                    Toast.makeText(getApplicationContext(),"GCM registration error!!:", Toast.LENGTH_LONG).show();


                }
                else
                {

                }
            }
        };

        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(ConnectionResult.SUCCESS != resultCode) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                Toast.makeText(getApplicationContext(), "google play service not found", Toast.LENGTH_LONG).show();
                GooglePlayServicesUtil.showErrorNotification(resultCode, getApplicationContext());
            } else {
                Toast.makeText(getApplicationContext(), "this device does not support google play services", Toast.LENGTH_LONG).show();

        }}
            else {
                Intent intent =new Intent(this,GCMRegistrationIntentService.class);
            startService(intent);
            }


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("MainActivity2", "onResume");
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,new IntentFilter(GCMRegistrationIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,new IntentFilter(GCMRegistrationIntentService.REGISTRATION_ERROR));

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("MainActivity2", "onPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }
}
