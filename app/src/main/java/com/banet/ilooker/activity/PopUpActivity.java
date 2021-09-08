package com.banet.ilooker.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.common.Global;
import com.banet.ilooker.databinding.CallPopupTopBinding;
import com.banet.ilooker.model.Advertise100;
import com.banet.ilooker.model.IncommingCall;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.service.CallingService;
import com.banet.ilooker.util.Util;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class PopUpActivity extends BaseActivity<CallPopupTopBinding> {
    TextView tvPhoneNumber;
    ImageView btnClose, mIvCallStatus, mIvLeft, mIvRight;
    LinearLayout mllFavorite, mllWhiteList, mllCallDeny, mllReturnCall, mllSendSms, mllReportBlock;
    String incomingCallNumber = "";
    TextView mTvTime, mTvLeft, mTvLeftNum, mTvRight, mTvRightNum, mTvWhitelist, mTvOrg, mTvType, mTvTypeNum, mTvCallDeny;
    IncommingCall mIncomingCall;
    TelecomManager tcm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_popup_top);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            incomingCallNumber = bundle.getString(Global.EXTRA_INCOMING_CALL_NUMBER);
            mIncomingCall = (IncommingCall) bundle.getSerializable(Global.EXTRA_INCOMING_CALL_DATA);

        }
        tcm = (TelecomManager) PopUpActivity.this.getSystemService(Context.TELECOM_SERVICE);

        tvPhoneNumber = findViewById(R.id.tv_call_number);
        tvPhoneNumber.setText(incomingCallNumber);

        mIvCallStatus = findViewById(R.id.iv_call_status);
        mIvCallStatus.setImageDrawable(Util.getDrawable(this, R.drawable.ic_calling));

        mllFavorite = findViewById(R.id.ll_favorite);
        mllWhiteList = findViewById(R.id.ll_whitelist);

        mTvTime = findViewById(R.id.tv_time);
        mTvTime.setText(getDateTime());

        mIvLeft = findViewById(R.id.iv_left);
        mTvLeft = findViewById(R.id.tv_favorite_left);

        mTvLeft.setTextColor(Util.getColor(this, R.color.good_number_color));

        mTvLeftNum = findViewById(R.id.tv_favorite_left_num);

        mIvRight = findViewById(R.id.iv_right);
        mTvRight = findViewById(R.id.tv_favorite_right);
        mTvRightNum = findViewById(R.id.tv_favorite_right_num);

        //유형분류 -- 투자/주식등
        mTvType = findViewById(R.id.tv_type_cate);
        //  mTvTypeNum = findViewById(R.id.tv_type_num);


        mTvOrg = findViewById(R.id.tv_org_name);
        mTvWhitelist = findViewById(R.id.tv_whitelist);


        mllCallDeny = findViewById(R.id.ll_call_deny);
        mllCallDeny.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {

                if (checkSelfPermission(Manifest.permission.ANSWER_PHONE_CALLS) == PackageManager.PERMISSION_GRANTED) {

                    if (tcm != null) {
                        try {
                            if (incomingCallNumber != null) {
                                tcm.endCall();
                                Log.d(TAG, "Incoming Call Blocked " + incomingCallNumber);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        mllSendSms = findViewById(R.id.ll_send_sms);
        mllSendSms.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                if (CallingService.mLastState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    endCall();
                }
                Uri uri = Uri.parse("smsto:" + incomingCallNumber); //sms 문자와 관련된 Data는 'smsto:'로 시작. 이후는 문자를 받는 사람의 전화번호

                Intent i = new Intent(Intent.ACTION_SENDTO, uri); //시스템 액티비티인 SMS문자보내기 Activity의 action값

                //  i.putExtra("sms_body", "Hello...");  //보낼 문자내용을 추가로 전송, key값은 반드시 'sms_body'

                startActivity(i);//액티비티 실행

            }
        });


        mllReturnCall = findViewById(R.id.ll_call_return);
        mllReturnCall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                if (CallingService.mLastState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    endCall();
                }

                Uri uri = Uri.parse("tel:" + incomingCallNumber);
                Intent i = new Intent(Intent.ACTION_DIAL, uri);

                startActivity(i);//액티비티 실행
                finish();
            }
        });

        mllReportBlock = findViewById(R.id.ll_report_block);
        mllReportBlock.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                endCall();
                Intent intent = new Intent(PopUpActivity.this, MainActivity.class);
                intent.putExtra(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_main_fragment);
                intent.putExtra(AppDef.MOVE_TO_FRAGMENT, AppDef.title_block_and_report_phone_number_fragment);
                intent.putExtra(AppDef.MOVE_TO_BLOCK_PHONE_NUMBER, incomingCallNumber);

                startActivity(intent);
                finish();
            }
        });

        btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setPhoneNumberColor(mIncomingCall.WhtListYN);
        setPhoneNumberColor("Y");
        showFavoritePart();
        showOrgWhiteList();
        request100Advertisement(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void endCall() {
        if (tcm != null) {
            try {
                if (incomingCallNumber != null) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ANSWER_PHONE_CALLS) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    tcm.endCall();
                    Log.d(TAG, "Incoming Call Blocked " + incomingCallNumber);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter(AppDef.action_phone_state_changed));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(AppDef.phone_state);

            if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
                // Log.i(TAG, " :" + state);


            } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
                //Log.i(TAG, " :" + state);
                mIvCallStatus.setImageDrawable(Util.getDrawable(PopUpActivity.this, R.drawable.ic_call_not_present));
                mllCallDeny.setEnabled(false);

            } else if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {


            }else if("SMS".equals(state)){
                mIvCallStatus.setImageDrawable(Util.getDrawable(PopUpActivity.this, R.drawable.ic_receive_sms));
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.call_popup_top;
    }

    private String getDateTime() {

        SimpleDateFormat format2 = new SimpleDateFormat("MM/dd HH:mm:ss");
        String format_time2 = format2.format(System.currentTimeMillis());
        return format_time2;
    }

    void setPhoneNumberColor(String isWhiteList) {

        if ("Y".equals(isWhiteList))
            tvPhoneNumber.setTextColor(Util.getColor(this, R.color.good_number_color));
        else
            tvPhoneNumber.setTextColor(Util.getColor(this, R.color.bad_number_color));
    }

    void showFavoritePart() {

        if (Integer.valueOf(mIncomingCall.GoodTotCnt) == 0 && Integer.valueOf(mIncomingCall.GoodTotCnt) == 0) {
            mllFavorite.setVisibility(View.INVISIBLE);
            return;
        } else
            mllFavorite.setVisibility(View.VISIBLE);

        if (Integer.valueOf(mIncomingCall.GoodTotCnt) >= Integer.valueOf(mIncomingCall.BadTotCnt)) {
            mIvLeft.setImageDrawable(Util.getDrawable(this, R.drawable.ic_like_hand));
            mTvLeft.setText(getResources().getString(R.string.like));
            mTvLeft.setTextColor(Util.getColor(this, R.color.good_number_color));
            mTvLeftNum.setText(mIncomingCall.GoodTotCnt);

            mIvRight.setImageDrawable(Util.getDrawable(this, R.drawable.ic_dislike_hand));
            mTvRight.setText(getResources().getString(R.string.dislike));
            mTvRight.setTextColor(Util.getColor(this, R.color.bad_number_color));
            mTvRightNum.setText(mIncomingCall.GoodTotCnt);

            mTvType.setText(" (" + mIncomingCall.TopTpClsNm + " " + mIncomingCall.TopTpGoodCnt + ")");


        } else { //싫어요 수가 더클때
            mIvLeft.setImageDrawable(Util.getDrawable(this, R.drawable.ic_dislike_hand));
            mTvLeft.setText(getResources().getString(R.string.dislike));
            mTvLeft.setTextColor(Util.getColor(this, R.color.bad_number_color));
            mTvLeftNum.setText(mIncomingCall.BadTotCnt);

            mIvRight.setImageDrawable(Util.getDrawable(this, R.drawable.ic_like_hand));
            mTvRight.setText(getResources().getString(R.string.like));
            mTvRight.setTextColor(Util.getColor(this, R.color.good_number_color));
            mTvRightNum.setText(mIncomingCall.GoodTotCnt);

            mTvType.setText(" (" + mIncomingCall.TopTpClsNm + " " + mIncomingCall.TopTpBadCnt + ")");

        }


    }

    void showOrgWhiteList() {
        if (mIncomingCall.OrgNm == null || "".equals(mIncomingCall.OrgNm))
            return;
        if (mIncomingCall.WhtListYN.equals("Y")) {
            mllWhiteList.setVisibility(View.VISIBLE);
            mTvOrg.setVisibility(View.VISIBLE);
            mTvOrg.setText(mIncomingCall.OrgNm + "는(은) ");
            mTvWhitelist.setText("화이트리스트 기관입니다.");
            mTvWhitelist.setTextColor(Util.getColor(this, R.color.good_number_color));
        }
    }

    void request100Advertisement(Context context){
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(this));
        DataInterface.getInstance().get100Advertise(context, params, new DataInterface.ResponseCallback<ResponseData<Advertise100>>() {


            @Override
            public void onSuccess(ResponseData<Advertise100> response) {

                if (response.getProcRsltCd().equals("100-000")) {
                    Advertise100 advertise100 = (Advertise100) response.getData();
                    Glide.with(PopUpActivity.this)
                            .load(advertise100.AdvtDescPath)
                            .into(getBinding().ivAdvertise);
                }

            }

            @Override
            public void onError(ResponseData<Advertise100> response) {
                Toast.makeText(getApplicationContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

