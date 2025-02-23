package com.banet.ilooker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class SmsReceiver extends BroadcastReceiver {

    static final String logTag = "SmsReceiver";
    static final String ACTION = "Telephony.Sms.Intents.SMS_RECEIVED_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION)) {

            //Bundel 널 체크
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                return;
            }

            Object[] pdusObj = (Object[]) bundle.get("pdus");
            if (pdusObj == null) {
                return;
            }

            //message 처리
            SmsMessage[] smsMessages = new SmsMessage[pdusObj.length];

            for (int i = 0; i < pdusObj.length; i++) {

                smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                Log.e(logTag, "NEW SMS " + i + "th");

                Log.e(logTag, "DisplayOriginatingAddress : "

                        + smsMessages[i].getDisplayOriginatingAddress());

                Log.e(logTag, "DisplayMessageBody : "

                        + smsMessages[i].getDisplayMessageBody());

                Log.e(logTag, "EmailBody : "

                        + smsMessages[i].getEmailBody());

                Log.e(logTag, "EmailFrom : "

                        + smsMessages[i].getEmailFrom());

                Log.e(logTag, "OriginatingAddress : "

                        + smsMessages[i].getOriginatingAddress());

                Log.e(logTag, "MessageBody : "

                        + smsMessages[i].getMessageBody());

                Log.e(logTag, "ServiceCenterAddress : "

                        + smsMessages[i].getServiceCenterAddress());

                Log.e(logTag, "TimestampMillis : "

                        + smsMessages[i].getTimestampMillis());

                Toast.makeText(context.getApplicationContext(), smsMessages[i].getMessageBody(), Toast.LENGTH_SHORT).show();


            }

        }

    }

}



