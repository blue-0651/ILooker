package com.banet.ilooker.activity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.common.Global;
import com.banet.ilooker.databinding.ActivitySettingsBinding;
import com.banet.ilooker.model.IncommingCall;
import com.banet.ilooker.model.SMSTxt003;
import com.banet.ilooker.model.SMSUrlMsg004;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.PreferenceStore;
import com.banet.ilooker.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class SettingsActivity extends BaseActivity<ActivitySettingsBinding> {
    PreferenceStore pStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pStore = new PreferenceStore(this);
        getBinding().tvTitle.setText("설정");
        getBinding().btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getBinding().name.getText().toString().equals(""))
                    request001Install();
                else
                    Toast.makeText(SettingsActivity.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
            }
        });

        getBinding().btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> containedUrls = extractUrls(getBinding().etSms.getText().toString());
                 if(containedUrls.size() > 0 ){
                     send004UrlMsg(SettingsActivity.this, Util.getLineNumber(getApplicationContext()), getBinding().etSms.getText().toString(), containedUrls.toString());
                 }else{
                     send003TxtMsg(SettingsActivity.this, Util.getLineNumber(getApplicationContext()), getBinding().etSms.getText().toString());
                       //임시로 폰 사용자 번호로 넣음
                 }

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;

    }


    private void request001Install() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", AppDef.USER_LANGUAGE_CODE);  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", Util.getLineNumber(this));   //사용자 전화번호
        params.put("UserNm", getBinding().name.getText().toString());        //사용자 이름
        params.put("RecPhnNo", getBinding().recommendation.getText().toString().trim().replace("-", ""));   //추천인 전화번호

        DataInterface.getInstance().get001Install(SettingsActivity.this, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if (response.getProcRsltCd().equals("001-000")) {
                    //인스톨 성공후에 preference에 저장
                    pStore.writePrefBoolean("isInstalled", true);
                }
                Toast.makeText(getApplicationContext(), "사용자등록이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(ResponseData<Object> response) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(SettingsActivity.this, "사용자등록 실패. 관리자에게 문의하십시요.", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void send003TxtMsg(Context context,  String incomingCallNumber, String msg) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(this));
        params.put("PhnNo", incomingCallNumber);
        params.put("MedPartCd", "002");
        params.put("Msg", msg);

        DataInterface.getInstance().get003IncommingTxtSMS(this, params, new DataInterface.ResponseCallback<ResponseData<SMSTxt003>>() {

            @Override
            public void onSuccess(ResponseData<SMSTxt003> response) {

                if (response.getProcRsltCd().equals("003-000")) {
                    SMSTxt003 incommingSms = (SMSTxt003) response.getData();
                    incommingSms.ProcessResultCd = response.getProcRsltCd();
                    incommingSms.smsContent = msg;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   // 마시멜로우 이상일 경우
                        if (Settings.canDrawOverlays(SettingsActivity.this)) {              // 체크
                            showIncomingPhoneSMSUI(context,  incomingCallNumber, incommingSms);
                        }
                    }
                }

            }

            @Override
            public void onError(ResponseData<SMSTxt003> response) {
                Toast.makeText(getApplicationContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void send004UrlMsg(Context context,  String incomingCallNumber, String msg, String msgInUrl) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(this));
        params.put("PhnNo", incomingCallNumber);
        params.put("MedPartCd", "003");
        params.put("Msg", msg);
        params.put("MsgInURL", msgInUrl); //메시지내 url

        DataInterface.getInstance().get004IncommingUrlSMS(this, params, new DataInterface.ResponseCallback<ResponseData<SMSUrlMsg004>>() {

            @Override
            public void onSuccess(ResponseData<SMSUrlMsg004> response) {

                if (response.getProcRsltCd().equals("004-000")) {
                    SMSUrlMsg004 smsUrlMsg004 = (SMSUrlMsg004) response.getData();
                    smsUrlMsg004.ProcessResultCd = response.getProcRsltCd();
                    smsUrlMsg004.smsContent = msg;
                    smsUrlMsg004.phnNumber = incomingCallNumber.replace("-", "");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   // 마시멜로우 이상일 경우
                        if (Settings.canDrawOverlays(SettingsActivity.this)) {              // 체크
                            showIncomingPhoneSMSUI(context,  incomingCallNumber, smsUrlMsg004);
                        }
                    }
                }

            }

            @Override
            public void onError(ResponseData<SMSUrlMsg004> response) {
                Toast.makeText(getApplicationContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showIncomingPhoneSMSUI(Context context, String incomingPhoneNumber, IncommingCall incommingMsg) {

     //   if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            String incomingNumber = incomingPhoneNumber;

            Intent intent_popup = new Intent(context, PopUpActivity.class);
            intent_popup.putExtra(Global.EXTRA_INCOMING_CALL_NUMBER, incomingNumber);
            intent_popup.putExtra(Global.EXTRA_INCOMING_CALL_DATA, incommingMsg);
            intent_popup.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent_popup, PendingIntent.FLAG_UPDATE_CURRENT);
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
     //   }
       // sendCallStatus(AppDef.PhoneCallStatus.valueOf("SMS"));
    }

    private void sendCallStatus(AppDef.PhoneCallStatus status) {
        Log.d(TAG, "Broadcasting call state");
        Intent intent = new Intent(AppDef.action_phone_state_changed);
        intent.putExtra(AppDef.phone_state, status.name());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    public static List<String> extractUrls(String sms) {
        List<String> containedUrls = new ArrayList<String>();
        String text = sms;

       // Split the sms to analyze if each part is a URL
        String[] split = text.split(" ");

        Pattern p = Pattern.compile("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?");

        // Attempt to convert each item into an URL
        for (int i = 0; i < split.length; i++) {
            if (p.matcher(split[i]).matches()) containedUrls.add(split[i]);
        }
        return containedUrls;
    }
}