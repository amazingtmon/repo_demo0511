package com.example.fcmpushnotice801;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {

        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage messgae){
        super.onMessageReceived(messgae);
        Log.i(this.getClass().getName(), "remoteMessage::"+messgae);
    }
}
