package com.example.aayushsrivastava.project12.main;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.aayushsrivastava.project12.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Aayush Srivastava on 04-07-2016.
 */
    public class GCMRegistrationIntentService extends IntentService {
    public static final String REGISTRATION_SUCCESS = "RegistrationSuccess";
    public static final String REGISTRATION_ERROR = "RegistrationError";
    public static final String TAG="GCMTOKEN";

    public GCMRegistrationIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        registerGCM();
    }

    private void registerGCM(){
        SharedPreferences sharedPreferences=getSharedPreferences("GCM", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        Intent registrationComplete = null;
        String token= null;

        try{
            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.v("GCMRegIntentService", "token:" + token);

            registrationComplete = new Intent(REGISTRATION_SUCCESS);
            registrationComplete.putExtra("token", token);
            String oldToken =sharedPreferences.getString(TAG,"");
            if (!"".equals(token)&&!oldToken.equals(token))
            {
                saveTokenToServer(token);
                editor.putString(TAG,token);
                editor.commit();
            }else{
                Log.w("GCMRegistrationService","Old token");
            }
        }catch (Exception e){
            Log.w("GCMRegIntentService","Registration error");
            registrationComplete = new Intent(REGISTRATION_ERROR);
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
    private  void saveTokenToServer(String token)
    {
        Map paramPost=new HashMap();
        paramPost.put("action","add");
        paramPost.put("tokenid",token);
        try{

            String msgResult=getStringResultFromService_POST("http://www.gcmproject.16mb.com/gcm.php",paramPost);
            Log.w("ServiceResponding",msgResult);
        }catch (Exception e)
        {
            e.printStackTrace();

        }
    }
    public String getStringResultFromService_POST(String serviceURL, Map<String,String> params) {
       // String resultString = null;
        HttpURLConnection cnn = null;
        String line = null;
        URL url;
        try {
            url = new URL(serviceURL);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("URL invalid:" + serviceURL);
        }

        StringBuilder bodyBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,String> param = iterator.next();
            bodyBuilder.append(param.getKey()).append('=').append(param.getValue());
            if(iterator.hasNext())
            {
                bodyBuilder.append('&');
            }

        }
        String body= bodyBuilder.toString();
        Log.w("AccessService","param:"+body);
        byte[]bytes=body.getBytes();
        try{

            cnn=(HttpURLConnection)url.openConnection();
            cnn.setDoOutput(true);
            cnn.setUseCaches(false);
            cnn.setFixedLengthStreamingMode(bytes.length);
            cnn.setRequestMethod("POST");
            cnn.setRequestProperty("Content-Type","application/x-www-form-urlencoded:charset=UTF-8");
            //
            OutputStream outputStream=cnn.getOutputStream();
            outputStream.write(bytes);
            outputStream.close();
            int status=cnn.getResponseCode();
            if(status!=200)
            {
                throw new IOException("post fail with error code :"+status);
            }
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(cnn.getInputStream()));
            StringBuilder stringBuilder=new StringBuilder();
            while ((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }
             return stringBuilder.toString();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


}
