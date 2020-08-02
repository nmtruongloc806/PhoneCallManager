package com.example.phonecallmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.security.Permission;
import java.util.Date;

public class CallerReceiver extends BroadcastReceiver {

    private final String mTag = "CallerReceiver MTL";
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(mTag, "IS UP AGAIN....");
        String action = intent.getAction();
        String phoneNumber = "UNKNOWN";

        if (action.equals("android.intent.action.NEW_OUTGOING_CALL")) {
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Log.d(mTag, "Outgoing number : " + phoneNumber);
        }
        else if(action.equals("android.intent.action.PHONE_STATE")){
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            phoneNumber = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            int state = 0;
            if(stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Log.d(mTag, "CALL ENDED... ");
                state = TelephonyManager.CALL_STATE_IDLE;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                Log.d(mTag, "ACTIVE OUTGOING CALL : ");
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Log.d(mTag, "Incoming number : " + phoneNumber);
                state = TelephonyManager.CALL_STATE_RINGING;
            }
        }
        else {

        }
    }

}
