package com.banet.ilooker.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.ActivityMainBinding;
import com.banet.ilooker.fragment.BaseBindingFragment;
import com.banet.ilooker.fragment.LastCallLogFragment;
import com.banet.ilooker.fragment.MainWorkFragment;
import com.banet.ilooker.fragment.Report_RegFragment_005;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.service.CallingService;
import com.banet.ilooker.util.DateUtils;
import com.banet.ilooker.util.Util;

import java.util.HashMap;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    public static final String TAG = "MainActivity";
    public static String MOVE_TO_FRAGMENT_NAME = "";
    public static String MOVE_TO_BLOCK_PHONE_NUMBER = "";
    boolean firstInit = true; //Preference로 변경
    private final int REQ_CODE_OVERLAY_PERMISSION = 101;
    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle_main = getIntent().getExtras();


        if (bundle_main != null) {
            MOVE_TO_FRAGMENT_NAME = bundle_main.getString(AppDef.MOVE_TO_FRAGMENT);
            MOVE_TO_BLOCK_PHONE_NUMBER = bundle_main.getString(AppDef.MOVE_TO_BLOCK_PHONE_NUMBER);
        }

        if (bundle_main == null) {
            init();
            firstInit = false;
        }


        getBinding().bottomTabBar.llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_main_fragment);
                GoHomeScreen();
            }
        });

        getBinding().bottomTabBar.recentCallLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.titl_latest_call_log_fragment);
                GoNativeScreen((BaseBindingFragment) new LastCallLogFragment(), bundle);
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

        //popup에서 번호 차단시 바로 차단 프래그먼트로 이동
        if (MOVE_TO_FRAGMENT_NAME != "") {  //신고차단 타이틀 만들것
            if (MOVE_TO_FRAGMENT_NAME.equals(AppDef.title_block_and_report_phone_number_fragment)) {
                Bundle bundle_move_to_005_block_report = new Bundle();
                bundle_move_to_005_block_report.putString(AppDef.incoming_number_extra, MOVE_TO_BLOCK_PHONE_NUMBER);
                bundle_move_to_005_block_report.putString(AppDef.incoming_date_time, DateUtils.getDateTime());
                bundle_move_to_005_block_report.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_and_report_phone_number_fragment);
                GoNativeScreen((BaseBindingFragment) new Report_RegFragment_005(), bundle_move_to_005_block_report);
            }
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_main_fragment);
            requestPermissionSystemAlertWindow();

        }

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
        params.put("UserPhnNo", Util.getLineNumber(this));   //사용자 전화번호
        params.put("UserNm", UserNm);        //사용자 이름
        params.put("RecPhnNo", RecPhnNo);   //추천인 전화번호

        DataInterface.getInstance().get001Install(MainActivity.this, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if (response.getProcRsltCd().equals("001-000")) {
                //    Toast.makeText(MainActivity.this, "사용자등록이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, "사용자등록 실패. 관리자에게 문의하십시요.", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        if (getTopFragment() != null && getTopFragment() instanceof MainWorkFragment) {
            backPressCloseHandler.onBackPressed();
        } else
            ((BaseActivity) this).fragmentManager.popBackStack();
    }

    private void requirePerms(){
        String[] permissions = {Manifest.permission.RECEIVE_SMS};
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }

    private void init() {

        requirePerms();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this
                    , new String[]{Manifest.permission.READ_CALL_LOG, Manifest.permission.INTERNET, Manifest.permission.ANSWER_PHONE_CALLS
                            , Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECEIVE_MMS, Manifest.permission.RECEIVE_WAP_PUSH,
                            Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_CONTACTS,  Manifest.permission.READ_SMS, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.FOREGROUND_SERVICE}
                    , 1);
        }

        Intent serviceIntent = new Intent(this, CallingService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
        request001Install("KOR", Util.getLineNumber(MainActivity.this), "홍길동", "추천인");
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermissionSystemAlertWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(MainActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQ_CODE_OVERLAY_PERMISSION);
            } else {//이미 권한 획득이면
                GoHomeScreen();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_OVERLAY_PERMISSION: {
                if (Settings.canDrawOverlays(this)) {
                    GoHomeScreen();
                } else {
                    Toast.makeText(MainActivity.this, "앱을 정상적으로 이용하려면 overlay 권한동의 설정이 필요합니다.", Toast.LENGTH_SHORT).show();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1000);
                }
                break;
            }
        }
    }

}

