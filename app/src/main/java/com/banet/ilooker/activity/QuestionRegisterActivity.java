package com.banet.ilooker.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.ActivityQuestionRegisterBinding;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.HashMap;

public class QuestionRegisterActivity extends BaseActivity<ActivityQuestionRegisterBinding>  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBinding().titleBar.tvTitle.setText(AppDef.title_question_register);
        getBinding().btnQuestionRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   if(  getBinding().etEmail.getText().toString().trim() == null|| getBinding().etEmail.getText().toString().trim().equals(""))
                request117QuestionRegister(QuestionRegisterActivity.this);
            }
        });

        getBinding().titleBar.btnTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_question_register;
    }

    public void request117QuestionRegister(Context context) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(context.getApplicationContext()));
        params.put("Email",    getBinding().etEmail.getText().toString().trim());
        params.put("InqTitl",    getBinding().etTitle.getText().toString() );
        params.put("InqDesc",    getBinding().etQuestionContent.getText().toString());
        showProgress("");
        DataInterface.getInstance().getApi117_QuestionRegister(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {

            @Override
            public void onSuccess(ResponseData<Object> response) {

                if (response.getProcRsltCd().equals("117-000")) {
                    Toast.makeText(context, "문의등록이 성공적으로 완료되었읍니다.", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }

            @Override
            public void onError(ResponseData<Object> response) {
                Toast.makeText(context, response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();
            }
        });
        hideProgress();
    }

}