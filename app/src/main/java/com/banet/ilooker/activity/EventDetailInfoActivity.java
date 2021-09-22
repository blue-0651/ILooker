package com.banet.ilooker.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.ActivityEventDetailInfoBinding;
import com.banet.ilooker.model.Event106;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;
import com.bumptech.glide.Glide;

import java.util.HashMap;


public class EventDetailInfoActivity extends BaseActivity<ActivityEventDetailInfoBinding> implements OnTitleListener {
    Event106 mEvent106;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBinding().titleBar.tvTitle.setText(AppDef.title_event_fragment);
        getBinding().titleBar.btnTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (getIntent().getSerializableExtra(AppDef.EVENT_106) != null) {

            mEvent106 = (Event106) getIntent().getSerializableExtra(AppDef.EVENT_106);
        }
        if (mEvent106 != null)
            request112_ApiEventDtl(mEvent106.EvtNo);
        else {
            Toast.makeText(EventDetailInfoActivity.this, "이벤트가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }



    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_detail_info;
    }

    private void request112_ApiEventDtl(String EventNumber) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", AppDef.USER_LANGUAGE_CODE);  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", Util.getLineNumber(this));   //사용자 전화번호
        params.put("EvtNo", EventNumber);
        showProgress("");
        DataInterface.getInstance().getApi112_EventDtail(EventDetailInfoActivity.this, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if (response.getProcRsltCd().equals("112-000")) {
                    getBinding().tvEventDate.setText(mEvent106.EvtStaDate + " ~ " + mEvent106.EvtEndDate);
                    getBinding().tvEventNumber.setText("No. : " + mEvent106.EvtNo);
                    getBinding().tvEventTitle.setText(mEvent106.EvtTitl);
                    Glide.with(EventDetailInfoActivity.this)
                            .load(mEvent106.EvtDescPath)
                            .into(getBinding().ivContent);
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(EventDetailInfoActivity.this, "이벤트 수신 실패. 관리자에게 문의하십시요.", Toast.LENGTH_SHORT).show();
            }

        });
        hideProgress();

    }

    @Override
    public void onTitleBackPress() {

    }

    @Override
    public void onTitleClosePress() {

    }

    @Override
    public void onSidelistClicked() {

    }

    @Override
    public void doBack() {

    }
}