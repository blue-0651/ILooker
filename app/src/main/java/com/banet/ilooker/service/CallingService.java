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
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.MainActivity;
import com.banet.ilooker.activity.PopUpActivity;
import com.banet.ilooker.common.Global;
import com.banet.ilooker.common.UIThread;
import com.banet.ilooker.model.IncommingCallSpam002;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CallingService extends Service {
    public static final String CHANNEL_ID = "ILOOKER_00";
    public static final String EXTRA_CALL_NUMBER = "call_number";
    public static final String TAG = "PHONE_STATE";
    private static String mLastState;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG,"onReceive()");

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

            if (phoneNumber == null ) {
                Log.i(TAG, " : NULL");
            } else {
                if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
                    Log.i(TAG, " :" + state);
                    request002IncommingCallSmissing(context, intent, state, phoneNumber);

                } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
                    Log.i(TAG, " :" + state);

                } else if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                    Toast.makeText(context, "Ringing State", Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, phoneNumber, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, " :" + state);
                    request002IncommingCallSmissing(context, intent, state, phoneNumber);

                }
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {

        // Not used
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PHONE_STATE");
        filter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        registerReceiver(receiver, filter);
    }

    private void showIncomingPhoneUI(Context context, Intent intent, String state, IncommingCallSpam002 result002){

        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state) || TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            String phone_number = PhoneNumberUtils.formatNumber(incomingNumber);

            Intent intent_popup = new Intent(context, PopUpActivity.class);
            intent_popup.putExtra(Global.EXTRA_INCOMING_CALL_NUMBER, phone_number );
            intent_popup.putExtra(Global.EXTRA_INCOMING_CALL_DATA, result002 );
            intent_popup.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent_popup, PendingIntent.FLAG_UPDATE_CURRENT);
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }

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

    }
    //     MacaronApp.currAllocation = response.getData();
  //  showIncomingPhoneUI(context, intent, state);
    private void request002IncommingCallSmissing(Context context, Intent intent, String state, String incomingCallNumber) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(this));
        params.put("PhnNo", incomingCallNumber);
        params.put("MedPartCd", "001");
        DataInterface.getInstance().get002IncommingCallSmissing(this, params, new DataInterface.ResponseCallback<ResponseData<IncommingCallSpam002>>() {


            @Override
            public void onSuccess(ResponseData<IncommingCallSpam002> response) {

                if( response.getProcRsltCd().equals("002-000") && response.getMsgPopupYN().equals("Y")){
                 IncommingCallSpam002 incommingCallSpam002 =  (IncommingCallSpam002) response.getData();
                   showIncomingPhoneUI(context, intent, state, incommingCallSpam002);
               }

            }

            @Override
            public void onError(ResponseData<IncommingCallSpam002> response) {
                       Toast.makeText(getApplicationContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
