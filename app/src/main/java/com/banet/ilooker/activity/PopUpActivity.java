package com.banet.ilooker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.banet.ilooker.R;
import com.banet.ilooker.common.Global;
import com.banet.ilooker.model.IncommingCallSpam002;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class PopUpActivity extends AppCompatActivity {
    TextView tv ;
    ImageView btnClose;
    String incomingCallNumber = "";
    TextView mTvTime, mTvGoodNumber , mTvBadNumber, mTvWhitelist;
    IncommingCallSpam002 mIncommingCallSpam002 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_popup_top);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            incomingCallNumber = bundle.getString(Global.EXTRA_INCOMING_CALL_NUMBER);
            mIncommingCallSpam002 = (IncommingCallSpam002) bundle.getSerializable(Global.EXTRA_INCOMING_CALL_DATA);
        }

        tv =  findViewById(R.id.tv_call_number);
        tv.setText(incomingCallNumber);

        mTvTime=  findViewById(R.id.tv_time);
        mTvTime.setText(getDateTime());

        mTvGoodNumber = findViewById(R.id.tv_like_num);
        mTvGoodNumber.setText(mIncommingCallSpam002.GoodTotCnt);

        mTvBadNumber = findViewById(R.id.tv_dislike_num);
        mTvBadNumber.setText(mIncommingCallSpam002.BadTotCnt);

        mTvWhitelist = findViewById(R.id.tv_whitelist);
        if(mIncommingCallSpam002.WhtListYN == "Y") {
            mTvWhitelist.setText("화이트리스트 기관입니다.");
          //  mTvWhitelist.setTextColor(R.color.good_number_color);
        }
        else if(mIncommingCallSpam002.WhtListYN == "N")
            mTvWhitelist.setText("블랙리스트 기관입니다.");

        btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private String getDateTime(){

        SimpleDateFormat format2 = new SimpleDateFormat ( "MM/dd HH:mm:ss");
        String format_time2 = format2.format (System.currentTimeMillis());
        return format_time2;
    }


}