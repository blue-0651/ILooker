package com.banet.ilooker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.banet.ilooker.R;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.service.CallingService;
import com.banet.ilooker.util.DateUtils;
import com.banet.ilooker.util.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

       @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

           Log.i(TAG, "aaaaa");
           if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED
                   || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
               ActivityCompat.requestPermissions(MainActivity.this
                       , new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.INTERNET
                               , Manifest.permission.READ_PHONE_STATE, Manifest.permission.FOREGROUND_SERVICE}
                       , 1);
           }

           Intent serviceIntent = new Intent(this, CallingService.class);
           //   serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");

           ContextCompat.startForegroundService(this, serviceIntent);

           //request001Install("KOR", Util.getLineNumber(MainActivity.this),"홍길동", "추천인");

       }

    private void request001Install(String UseLangCd, String UserPhnNo, String UserNm , String RecPhnNo){
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", UseLangCd);  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", UserPhnNo);   //사용자 전화번호
        params.put("UserNm", UserNm);        //사용자 이름
        params.put("RecPhnNo", RecPhnNo);   //추천인 전화번호

        DataInterface.getInstance().get001Install( MainActivity.this, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if(response.getProcRsltCd().equals(100-000)){

                }
            }

            @Override
            public void onError(ResponseData<Object> response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }

        });

    }


}