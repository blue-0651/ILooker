package com.banet.ilooker.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.provider.Telephony;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.MainActivity;
import com.banet.ilooker.activity.PopUpActivity;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.common.Global;
import com.banet.ilooker.model.IncommingCall;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.HashMap;

public class CallingService extends Service {
    public static final String CHANNEL_ID = "ILOOKER_00";
    public static final String EXTRA_CALL_NUMBER = "call_number";
    public static final String TAG = "CallingService";
    public static String mLastState;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive()");
            if (intent.getAction().equals("android.intent.action.RECEIVE_SMS")) {

                SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                if (messages != null) {
                    if (messages.length == 0)
                        return;
                    StringBuilder sb = new StringBuilder();
                    for (SmsMessage smsMessage : messages) {
                        sb.append(smsMessage.getMessageBody());
                    }
                    String senderPhoneNo = messages[0].getOriginatingAddress();
                    String message = sb.toString();

                    send003TxtMsg(context, intent, senderPhoneNo, message);
                }else{
                    Log.d(TAG, "Massage not found");
                }

            } else if (intent.getAction().equals("android.intent.action.PHONE_STATE") || intent.getAction().equals("android.intent.action.OUTGOING_CALL")) {

                /**
                 * http://mmarvick.github.io/blog/blog/lollipop-multiple-broadcastreceiver-call-state/
                 * 2번 호출되는 문제 해결
                 */
                String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

                if (state.equals(mLastState) && phoneNumber == null) {
                    return;

                } else {
                    mLastState = state;

                }

                if (phoneNumber == null) {
                    Log.i(TAG, " : NULL");
                } else {
                    if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
                        Log.i(TAG, " :" + state);
                        requestIncommingCallSmissing(context, intent, state, phoneNumber);

                    } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
                        Log.i(TAG, " :" + state);
                        sendCallStatus(state);

                    } else if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                        Toast.makeText(context, "Ringing State", Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, phoneNumber, Toast.LENGTH_SHORT).show();
                        Log.i(TAG, " :" + state);
                        requestIncommingCallSmissing(context, intent, state, phoneNumber);

                    }
                }
            }
        }
    };

    private void sendCallStatus(String state) {
        Log.d(TAG, "Broadcasting call state");
        Intent intent = new Intent(AppDef.action_phone_state_changed);
        intent.putExtra(AppDef.phone_state, state);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    @Override
    public IBinder onBind(Intent intent) {

        // Not used
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter();

        // filter.setPriority(2147483647);
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        filter.addAction("android.intent.action.RECEIVE_SMS");
        filter.addAction("android.intent.action.PHONE_STATE");
        filter.addAction("android.intent.action.NEW_OUTGOING_CALL");

        registerReceiver(receiver, filter);
    }

    private void showIncomingPhoneSMSUI(Context context, Intent intent, String  incomingPhoneNumber, IncommingCall incommingCall) {

        if (intent.getAction().equals("android.intent.action.RECEIVE_SMS")) {
            String incomingNumber = incomingPhoneNumber;

            Intent intent_popup = new Intent(context, PopUpActivity.class);
            intent_popup.putExtra(Global.EXTRA_INCOMING_CALL_NUMBER, incomingNumber);
            intent_popup.putExtra(Global.EXTRA_INCOMING_CALL_DATA, incommingCall);
            intent_popup.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent_popup, PendingIntent.FLAG_UPDATE_CURRENT);
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }

    private void showIncomingPhoneUI(Context context, Intent intent, String state, IncommingCall result) {

        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state) || TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            String phone_number = PhoneNumberUtils.formatNumber(incomingNumber);

            Intent intent_popup = new Intent(context, PopUpActivity.class);
            intent_popup.putExtra(Global.EXTRA_INCOMING_CALL_NUMBER, phone_number);
            intent_popup.putExtra(Global.EXTRA_INCOMING_CALL_DATA, result);
            intent_popup.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent_popup, PendingIntent.FLAG_UPDATE_CURRENT);
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
            sendCallStatus(state);
        }
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("AILooker")
                .setContentText("아이루커가 실행중입니다.")
                .setSmallIcon(R.drawable.ic_launcher_ailooker_white)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        return START_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);

    }

    private void requestIncommingCallSmissing(Context context, Intent intent, String state, String incomingCallNumber) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(this));
        params.put("PhnNo", incomingCallNumber);
        params.put("MedPartCd", "001");
        DataInterface.getInstance().get002IncommingCallSmissing(this, params, new DataInterface.ResponseCallback<ResponseData<IncommingCall>>() {


            @Override
            public void onSuccess(ResponseData<IncommingCall> response) {

                if (response.getProcRsltCd().equals("002-000")) {
                    IncommingCall incommingCall = (IncommingCall) response.getData();
                    showIncomingPhoneUI(context, intent, state, incommingCall);
                }

            }

            @Override
            public void onError(ResponseData<IncommingCall> response) {
                Toast.makeText(getApplicationContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void send003TxtMsg(Context context, Intent intent,  String incomingCallNumber, String msg) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(this));
        params.put("PhnNo", incomingCallNumber);
        params.put("MedPartCd", "002");
        params.put("Msg", msg);

        DataInterface.getInstance().get003IncommingSMS(this, params, new DataInterface.ResponseCallback<ResponseData<IncommingCall>>() {


            @Override
            public void onSuccess(ResponseData<IncommingCall> response) {

                if (response.getProcRsltCd().equals("003-000")) {
                    IncommingCall txtMsg003 = (IncommingCall) response.getData();
                    showIncomingPhoneSMSUI(context, intent, incomingCallNumber, txtMsg003);
                }

            }

            @Override
            public void onError(ResponseData<IncommingCall> response) {
                Toast.makeText(getApplicationContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
