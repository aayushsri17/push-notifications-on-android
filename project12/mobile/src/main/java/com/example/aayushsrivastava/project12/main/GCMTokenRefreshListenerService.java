package com.example.aayushsrivastava.project12.main;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Aayush Srivastava on 04-07-2016.
 */
public class GCMTokenRefreshListenerService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this,GCMRegistrationIntentService.class);
        startService(intent);
    }
}
