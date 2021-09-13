
package com.banet.ilooker.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.ActivityNotiDetailInfoBinding;
import com.banet.ilooker.model.Noti104;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;
import com.bumptech.glide.Glide;

import java.util.HashMap;


public class NotiDetailInfoActivity extends BaseActivity<ActivityNotiDetailInfoBinding> {
    Noti104 mNoti104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getSerializableExtra(AppDef.NOTICE_104) != null) {

            mNoti104 = (Noti104) getIntent().getSerializableExtra(AppDef.NOTICE_104);
        }
        if (mNoti104 != null)
            request110GeneralNoticedetail(mNoti104.NtcNo);
        else {
            Toast.makeText(NotiDetailInfoActivity.this, "공지번호가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_noti_detail_info;
    }

    private void request110GeneralNoticedetail(String NoticeNumber) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", AppDef.USER_LANGUAGE_CODE);  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", Util.getLineNumber(this));   //사용자 전화번호
        params.put("NtcNo", NoticeNumber);        //공지번호
        showProgress("");
        DataInterface.getInstance().getApi110GeneralNotice(NotiDetailInfoActivity.this, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if (response.getProcRsltCd().equals("110-000")) {
                    getBinding().date.setText(mNoti104.NtcStaDate + " ~ " + mNoti104.NtcEndDate);
                    getBinding().sequenceNumber.setText("No. : " + mNoti104.NtcNo);
                    getBinding().tvTitle.setText(mNoti104.NtcTitl);
                    Glide.with(NotiDetailInfoActivity.this)
                            .load(mNoti104.NtcDescPath)
                            .into(getBinding().ivContent);
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(NotiDetailInfoActivity.this, "공지내역 수신 실패. 관리자에게 문의하십시요.", Toast.LENGTH_SHORT).show();
            }

        });
        hideProgress();

    }

}