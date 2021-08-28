package com.banet.ilooker.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.common.Global;
import com.banet.ilooker.databinding.CallPopupTopBinding;
import com.banet.ilooker.model.IncommingCallSpam002;
import com.banet.ilooker.util.Util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

public class PopUpActivity extends BaseActivity<CallPopupTopBinding> {
    TextView tvPhoneNumber;
    ImageView btnClose, mIvCallStatus, mIvLeft, mIvRight;
    LinearLayout mllFavorite, mllWhiteList, mllCallDeny, mllReturnCall, mllSendSms, mllReportBlock;
    String incomingCallNumber = "";
    TextView mTvTime, mTvLeft, mTvLeftNum, mTvRight, mTvRightNum, mTvWhitelist, mTvOrg, mTvType, mTvTypeNum, mTvCallDeny;
    IncommingCallSpam002 mIncommingCallSpam002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_popup_top);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            incomingCallNumber = bundle.getString(Global.EXTRA_INCOMING_CALL_NUMBER);
            mIncommingCallSpam002 = (IncommingCallSpam002) bundle.getSerializable(Global.EXTRA_INCOMING_CALL_DATA);

        }
        //  mIncommingCallSpam002 = new IncommingCallSpam002();

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

        mTvCallDeny = findViewById(R.id.tv_call_deny);

        mllCallDeny = findViewById(R.id.ll_call_deny);
        mllCallDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TelecomManager tm = (TelecomManager) getApplicationContext().getSystemService(Context.TELECOM_SERVICE);
                try {
                    Method m = tm.getClass().getDeclaredMethod("endCall");

                    m.setAccessible(true);

                    if ((incomingCallNumber != null && incomingCallNumber != "")) {
                        boolean temp = (boolean) m.invoke(tm);
                        Toast.makeText(getApplicationContext(), Boolean.toString(temp), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        getBinding().llSnedSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("smsto:" + incomingCallNumber); //sms 문자와 관련된 Data는 'smsto:'로 시작. 이후는 문자를 받는 사람의 전화번호

                Intent i = new Intent(Intent.ACTION_SENDTO, uri); //시스템 액티비티인 SMS문자보내기 Activity의 action값

                //  i.putExtra("sms_body", "Hello...");  //보낼 문자내용을 추가로 전송, key값은 반드시 'sms_body'

                startActivity(i);//액티비티 실행

            }
        });


        mllReturnCall = findViewById(R.id.ll_call_return);
        mllReturnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent("android.intent.action.CALL", Uri.parse(incomingCallNumber)));
                Uri uri= Uri.parse("tel:" + incomingCallNumber); //전화와 관련된 Data는 'Tel:'으로 시작. 이후는 전화번호

               Intent i= new Intent(Intent.ACTION_DIAL,uri); //시스템 액티비티인 Dial Activity의 action값

                startActivity(i);//액티비티 실행
                finish();
            }
        });

     //   mllReportBlock = findViewById(R.id.ll_report_block);
        getBinding().llReportBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_phone_number_fragment);
//                GoNativeScreen((BaseBindingFragment) new BlockPhoneNumberFragment(), bundle);
                Toast.makeText(getApplicationContext(), "공사중입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setPhoneNumberColor(mIncommingCallSpam002.WhtListYN);
        setPhoneNumberColor("Y");
        showFavoritePart();
        showOrgWhiteList();
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

        if (Integer.valueOf(mIncommingCallSpam002.GoodTotCnt) == 0 && Integer.valueOf(mIncommingCallSpam002.GoodTotCnt) == 0) {
            mllFavorite.setVisibility(View.INVISIBLE);
            return;
        } else
            mllFavorite.setVisibility(View.VISIBLE);

        if (Integer.valueOf(mIncommingCallSpam002.GoodTotCnt) >= Integer.valueOf(mIncommingCallSpam002.BadTotCnt)) {
            mIvLeft.setImageDrawable(Util.getDrawable(this, R.drawable.ic_like_hand));
            mTvLeft.setText(getResources().getString(R.string.like));
            mTvLeft.setTextColor(Util.getColor(this, R.color.good_number_color));
            mTvLeftNum.setText(mIncommingCallSpam002.GoodTotCnt);

            mIvRight.setImageDrawable(Util.getDrawable(this, R.drawable.ic_dislike_hand));
            mTvRight.setText(getResources().getString(R.string.dislike));
            mTvRight.setTextColor(Util.getColor(this, R.color.bad_number_color));
            mTvRightNum.setText(mIncommingCallSpam002.GoodTotCnt);

            mTvType.setText(" (" + mIncommingCallSpam002.TopTpClsNm + " " + mIncommingCallSpam002.TopTpGoodCnt + ")");


        } else { //싫어요 수가 더클때
            mIvLeft.setImageDrawable(Util.getDrawable(this, R.drawable.ic_dislike_hand));
            mTvLeft.setText(getResources().getString(R.string.dislike));
            mTvLeft.setTextColor(Util.getColor(this, R.color.bad_number_color));
            mTvLeftNum.setText(mIncommingCallSpam002.BadTotCnt);

            mIvRight.setImageDrawable(Util.getDrawable(this, R.drawable.ic_like_hand));
            mTvRight.setText(getResources().getString(R.string.like));
            mTvRight.setTextColor(Util.getColor(this, R.color.good_number_color));
            mTvRightNum.setText(mIncommingCallSpam002.GoodTotCnt);

            mTvType.setText(" (" + mIncommingCallSpam002.TopTpClsNm + " " + mIncommingCallSpam002.TopTpBadCnt + ")");

        }


    }

    void showOrgWhiteList() {
        if (mIncommingCallSpam002.OrgNm.equals(""))
            return;
        if (mIncommingCallSpam002.WhtListYN.equals("Y")) {
            mllWhiteList.setVisibility(View.VISIBLE);
            mTvOrg.setVisibility(View.VISIBLE);
            mTvOrg.setText(mIncommingCallSpam002.OrgNm + "는(은) ");
            mTvWhitelist.setText("화이트리스트 기관입니다.");
            mTvWhitelist.setTextColor(Util.getColor(this, R.color.good_number_color));
        }
    }
}

