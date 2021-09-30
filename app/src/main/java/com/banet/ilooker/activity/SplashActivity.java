package com.banet.ilooker.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.banet.ilooker.BuildConfig;
import com.banet.ilooker.R;
import com.banet.ilooker.common.AiLookerCustomDialog;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.common.Global;
import com.banet.ilooker.model.AppVersion200;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.PreferenceStore;
import com.banet.ilooker.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class SplashActivity extends AppCompatActivity {
   // Manifest.permission.SYSTEM_ALERT_WINDOW,
    private String TAG = this.getClass().getSimpleName();
    private String[] permissions = { Manifest.permission.READ_CALL_LOG, Manifest.permission.INTERNET, Manifest.permission.ANSWER_PHONE_CALLS
            , Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECEIVE_MMS,
            Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_CONTACTS, Manifest.permission.ANSWER_PHONE_CALLS, Manifest.permission.READ_SMS
            ,Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.FOREGROUND_SERVICE};

    private final int PERMISSIONS_REQUEST_ACCOUNTS = 100;
    private final int REQ_CODE_OVERLAY_PERMISSION = 101;

    private AiLookerCustomDialog aiLookerCustomDialog;

    private final Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startLoading();
    }

    private void checkVersionAndGoToInitActivity(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", AppDef.USER_LANGUAGE_CODE);  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", Util.getLineNumber(this));   //사용자 전화번호
        params.put("CurrVer", BuildConfig.VERSION_NAME);        //현재버전 올려줌

        DataInterface.getInstance().getApi200_Appver(SplashActivity.this, params, new DataInterface.ResponseCallback<ResponseData<AppVersion200>>(){

            @Override
            public void onSuccess(ResponseData<AppVersion200> response) {
                Log.i(TAG, "response: " + response.getProcRsltCd());
                if(response.getProcRsltCd().equals("200-000")){
                    AppVersion200 appVersion200 = (AppVersion200) response.getData();
                    appVersionCheck(appVersion200.currVer);

                }
            }

            @Override
            public void onError(ResponseData<AppVersion200> response) {
                showDialog(SplashActivity.this, null, "네트웍상태를 확인해주세요.");
            }

            @Override
            public void onFailure(Throwable t) {
                showDialog(SplashActivity.this, null, "네트웍상태를 확인해주세요.");
            }
        });
    }


    private void showDialog(Context context, String title, String msg) {
        aiLookerCustomDialog = new AiLookerCustomDialog(context, title, msg, "확인", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aiLookerCustomDialog.dismiss();
                finish();
            }
        });

        try {
            aiLookerCustomDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    // 시스템 알림
//    private void systemAlertCheck(ArrayList<AppInfo> appInfo) {
//        HashMap<String, String> appVersionDetail = getAppVersionDetailInfo(appInfo, true);
//
//        if (!appVersionDetail.isEmpty()) {
//            final String systemAction = appVersionDetail.get(Global.AppVersionCheck.ACTION);
//            aiLookerCustomDialog = new MacaronCustomDialog(this, null, appVersionDetail.get(Global.AppVersionCheck.MESSAGE), "이동", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    aiLookerCustomDialog.dismiss();
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(systemAction));
//                    startActivity(intent);
//                    finish();
//                }
//            }, true);
//
//            aiLookerCustomDialog.setCancelable(false);
//            aiLookerCustomDialog.show();
//
//        } else {
//            appVersionCheck(appInfo);
//        }
//    }

    // 일반 버전체크
    private void appVersionCheck(String AppVersionFromServer) {

        if(!AppVersionFromServer.isEmpty()) {
            int appVersion = changeVersionNameToInt(BuildConfig.VERSION_NAME);
            if(appVersion < changeVersionNameToInt(AppVersionFromServer)) {

                aiLookerCustomDialog = new AiLookerCustomDialog(SplashActivity.this,
                        "AILooker 버전 확인",
                        " 상위 버전이 존재합니다. 업그레이드를 하시겠습니까?" ,
                        "취소",
                        "확인",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                aiLookerCustomDialog.dismiss();
                                startActivity();
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                aiLookerCustomDialog.dismiss();
                                goGoogleMarket();
                                finish();
                            }
                        });

                aiLookerCustomDialog.setCancelable(false);
                aiLookerCustomDialog.show();
            } else {
                startLoading();
            }

        } else {
            terminationToDataReceiveFail();
        }
    }

    /**
     * AppInfo 정보를 못받았거나 앱이랑 맞지 않았을때 앱종료
     */
    private void terminationToDataReceiveFail() {
        Toast.makeText(SplashActivity.this, "서버앱버전이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1500);
    }

//    private HashMap<String, String> getAppVersionDetailInfo(ArrayList<AppInfo> appInfo, boolean systemCheck) {
//        HashMap<String, String> hashMap = new HashMap<>();
//
//        for(int i=0; i<appInfo.size(); i++) {
//            if(systemCheck) {
//                if(appInfo.get(i).confCat.equalsIgnoreCase(Global.AppVersionCheck.CHAUFFEUR_SYSTEM)) {
//                    if(appInfo.get(i).name.equalsIgnoreCase(Global.AppVersionCheck.MESSAGE)) {
//                        hashMap.put(Global.AppVersionCheck.MESSAGE, appInfo.get(i).confValue);
//                    }
//                    if(appInfo.get(i).name.equalsIgnoreCase(Global.AppVersionCheck.ACTION)) {
//                        hashMap.put(Global.AppVersionCheck.ACTION, appInfo.get(i).confValue);
//                    }
//                }
//
//            } else {
//                if(appInfo.get(i).confCat.equalsIgnoreCase(Global.AppVersionCheck.CHAUFFEUR_VERSION)) {
//                    if(appInfo.get(i).name.equalsIgnoreCase(Global.AppVersionCheck.VERSION)) {
//                        hashMap.put(Global.AppVersionCheck.VERSION, appInfo.get(i).confValue);
//                    }
//                    if(appInfo.get(i).name.equalsIgnoreCase(Global.AppVersionCheck.MESSAGE)) {
//                        hashMap.put(Global.AppVersionCheck.MESSAGE, appInfo.get(i).confValue);
//                    }
//                    if(appInfo.get(i).name.equalsIgnoreCase(Global.AppVersionCheck.ACTION)) {
//                        hashMap.put(Global.AppVersionCheck.ACTION, appInfo.get(i).confValue);
//                    }
//                }
//            }
//        }
//
//        return hashMap;
//    }

    private void goGoogleMarket() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(Global.HOST_GOOGLE_MARKET + getPackageName()));
        startActivity(i);
    }

    private int changeVersionNameToInt(String version) {
        String tmp[] = version.split("\\.");
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<tmp.length; i++) {
            stringBuilder.append(tmp[i]);
        }

        return Integer.parseInt(stringBuilder.toString());
    }
   //최종적으로 다음화면으로 넘어갈때 이루틴 통과하고 넘어가야함.
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(arePermissionsEnabled()) {
                        checkVersionAndGoToInitActivity();

                    } else {
                        requestMultiplePermissions();
                    }
                } else {
                    checkVersionAndGoToInitActivity();
                }

            }
        }, 1000);
    }


    private void startActivity(){
        Intent intent;
        PreferenceStore pStore  = new PreferenceStore(this);
        if(pStore.readPrefBoolean("isInstalled", false) == false) {
             intent=new Intent(SplashActivity.this, SettingsActivity.class);

        }else {
             intent = new Intent(SplashActivity.this, MainActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }


    private void requestMultiplePermissions(){
        boolean check = false;
        List<String> remainingPermissions = new ArrayList<>();
        for(String permission : permissions){
            if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                remainingPermissions.add(permission);
                check = true;
            }
        }

        if(check) {
            requestPermissions(remainingPermissions.toArray(new String[remainingPermissions.size()]), PERMISSIONS_REQUEST_ACCOUNTS);
        } else {
            if (!Settings.canDrawOverlays(this)) {
                requestPermissionSystemAlertWindow();
            }
        }
    }


    private boolean arePermissionsEnabled(){
        for(String permission : permissions){
            if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }

        if (!Settings.canDrawOverlays(this)) {
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCOUNTS:
                if (grantResults.length > 0) {
                    boolean permissionCheck = true;
                    for (int result : grantResults) {
                        // 필수권한 중 거부된 권한이 있는지 체크.
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            permissionCheck = false;
                        }
                    }

                    if (permissionCheck) {
                        checkSystemAlertWindow();

                    } else {
                        Toast.makeText(SplashActivity.this, "앱을 정상적으로 이용하려면 권한동의 설정이 필요합니다.", Toast.LENGTH_SHORT).show();
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 1000);
                    }
                }
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkSystemAlertWindow() {
        if (!Settings.canDrawOverlays(this)) {
            requestPermissionSystemAlertWindow();
        } else {
            startActivity();
        }
    }

    /**
     * SYSTEM_ALERT_WINDOW 권한 요청
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermissionSystemAlertWindow() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQ_CODE_OVERLAY_PERMISSION);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_OVERLAY_PERMISSION: {
                if (Settings.canDrawOverlays(this)) {
                    startLoading();
                } else {
                    Toast.makeText(SplashActivity.this, "앱을 정상적으로 이용하려면 권한동의 설정이 필요합니다.", Toast.LENGTH_SHORT).show();
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

//    private void checkUpdate(){
//        DataInterface.getInstance().
//
//    }


//    private class getMarketVersion extends AsyncTask<Void, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//
//            try {
//                Document doc = Jsoup
//                        .connect(
//                                "https://play.google.com/store/apps/details?id=패키지명 적으세요" )
//                        .get();
//                Elements Version = doc.select(".content");
//
//                for (Element v : Version) {
//                    if (v.attr("itemprop").equals("softwareVersion")) {
//                        marketVersion = v.text();
//                    }
//                }
//                return marketVersion;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            PackageInfo pi = null;
//            try {
//                pi = getPackageManager().getPackageInfo(getPackageName(), 0);
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//            verSion = pi.versionName;
//            marketVersion = result;
//
//            if (!verSion.equals(marketVersion)) {
//                mDialog.setMessage("업데이트 후 사용해주세요.")
//                        .setCancelable(false)
//                        .setPositiveButton("업데이트 바로가기",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,
//                                                        int id) {
//                                        Intent marketLaunch = new Intent(
//                                                Intent.ACTION_VIEW);
//                                        marketLaunch.setData(Uri
//                                                .parse("https://play.google.com/store/apps/details?id=패키지명 적으세요"));
//                                        startActivity(marketLaunch);
//                                        finish();
//                                    }
//                                });
//                AlertDialog alert = mDialog.create();
//                alert.setTitle("안 내");
//                alert.show();
//            }
//
//            super.onPostExecute(result);
//        }
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(aiLookerCustomDialog != null) aiLookerCustomDialog.dismiss();
    }
}
