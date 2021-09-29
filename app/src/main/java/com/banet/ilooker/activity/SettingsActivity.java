package com.banet.ilooker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.ActivitySettingsBinding;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.HashMap;

public class SettingsActivity extends BaseActivity<ActivitySettingsBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBinding().btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getBinding().name.getText().toString().equals(""))
                    request001Install();
                else
                    Toast.makeText(SettingsActivity.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
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
        params.put("RecPhnNo", "01023456789");   //추천인 전화번호

        DataInterface.getInstance().get001Install(SettingsActivity.this, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if (response.getProcRsltCd().equals("001-000")) {
                    Toast.makeText(getApplicationContext(), "사용자등록이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                    finish();
                }
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
}