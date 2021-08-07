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
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class PopUpActivity extends AppCompatActivity {
    TextView tv ;
    ImageView btnClose;
    String incomingCallNumber = "";
    TextView mTvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_popup_top);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            incomingCallNumber = bundle.getString(Global.EXTRA_INCOMING_CALL_NUMBER);
        }

        tv =  findViewById(R.id.tv_call_number);
        tv.setText(incomingCallNumber);

        mTvTime=  findViewById(R.id.tv_time);
        mTvTime.setText(getDateTime());

        btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        request001Install("KOR", Util.getLineNumber(PopUpActivity.this),"홍길동", "추천인");
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


    private void request001Install(String UseLangCd, String UserPhnNo, String UserNm , String RecPhnNo){
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", UseLangCd);  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", UserPhnNo);   //사용자 전화번호
        params.put("UserNm", UserNm);        //사용자 이름
        params.put("RecPhnNo", RecPhnNo);   //추천인 전화번호

        DataInterface.getInstance().get001Install( PopUpActivity.this, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
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