package com.banet.ilooker.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.ActivityMainBinding;
import com.banet.ilooker.databinding.MainFragmentBinding;
import com.banet.ilooker.fragment.MainWorkFragment;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.service.CallingService;
import com.banet.ilooker.util.PrefUtil;
import com.banet.ilooker.util.Util;

import java.util.HashMap;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "aaaaa");
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this
                    , new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.INTERNET
                            , Manifest.permission.READ_PHONE_STATE, Manifest.permission.FOREGROUND_SERVICE}
                    , 1);
        }

        Intent serviceIntent = new Intent(this, CallingService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
        request001Install("KOR", Util.getLineNumber(MainActivity.this),"홍길동", "추천인");

        getBinding().bottomTabBar.llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_main_fragment);
                GoHomeScreen();
            }
        });

        getBinding().titleBar.btnTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_main_fragment);
                GoHomeScreen();
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_main_fragment);
        GoHomeScreen();


    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void setTitleName(String titleName) {
        getBinding().titleBar.tvTitle.setText(titleName);
    }


    private void request001Install(String UseLangCd, String UserPhnNo, String UserNm, String RecPhnNo) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", UseLangCd);  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", UserPhnNo);   //사용자 전화번호
        params.put("UserNm", UserNm);        //사용자 이름
        params.put("RecPhnNo", RecPhnNo);   //추천인 전화번호

        DataInterface.getInstance().get001Install(MainActivity.this, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if (response.getProcRsltCd().equals("100-000")) {

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

    public void setBottomTabBarVisible(Boolean isVisible) {
        if (!isVisible) {
            getBinding().bottomTabBar.getRoot().setVisibility(View.GONE);
        } else {
            getBinding().bottomTabBar.getRoot().setVisibility(View.VISIBLE);
        }
    }

//    @Override
//    public void onBackPressed() {
//        if( getTopFragment() != null && getTopFragment() instanceof MainWorkFragment){
//                backPressCloseHandler.onBackPressed();
//            }
//        else
//            ( (BaseActivity)this).fragmentManager.popBackStack();
//    }

}

