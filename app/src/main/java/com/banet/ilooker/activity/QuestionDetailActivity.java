package com.banet.ilooker.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.ActivityQuestionDetailBinding;
import com.banet.ilooker.model.Question108;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.HashMap;

public class QuestionDetailActivity extends BaseActivity<ActivityQuestionDetailBinding>  {
     Question108 mQuestion108 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getSerializableExtra(AppDef.MENU_LIST_ITEM_108) != null) {

            mQuestion108 = (Question108) getIntent().getSerializableExtra(AppDef.MENU_LIST_ITEM_108);
        }
        getBinding().titleBar.btnTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getBinding().titleBar.tvTitle.setText(AppDef.title_question_detail);


        if (mQuestion108 != null)
            request114QuestionDetail(mQuestion108.InqNo);

        else {
            Toast.makeText(QuestionDetailActivity.this, "문의번호가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        getBinding().btnQuestionUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request118QuestionUpdate(mQuestion108.InqNo);
            }
        });

        getBinding().btnQuestionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request119QuestionDelete(mQuestion108.InqNo);
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_question_detail;

    }

    private void request114QuestionDetail(String InqNo) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", AppDef.USER_LANGUAGE_CODE);  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", Util.getLineNumber(this));   //사용자 전화번호
        params.put("InqNo", InqNo);
        showProgress("");
        DataInterface.getInstance().getApi114_questionDetail(QuestionDetailActivity.this, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if (response.getProcRsltCd().equals("114-000")) {
                    getBinding().etTitle.setText(mQuestion108.InqTitl);
                    getBinding().etEmail.setText(mQuestion108.Email);
                    getBinding().tvAnswerState.setText("답변상태: " + mQuestion108.AnsStatNm);
                    getBinding().tvAnswerDate.setText("답변일자: " + mQuestion108.AnsDate);
                    getBinding().etQuestionContent.setText(mQuestion108.InqDesc);

                }
            }

            @Override
            public void onError(ResponseData<Object> response) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(QuestionDetailActivity.this, "문의내역 수신 실패. 관리자에게 문의하십시요.", Toast.LENGTH_SHORT).show();
            }

        });
        hideProgress();

    }

    private void request118QuestionUpdate(String InqNo) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", AppDef.USER_LANGUAGE_CODE);  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", Util.getLineNumber(this));   //사용자 전화번호
        params.put("InqNo", InqNo);
        params.put("Email",    getBinding().etEmail.getText().toString().trim());
        params.put("InqTitl",    getBinding().etTitle.getText().toString() );
        params.put("InqDesc",    getBinding().etQuestionContent.getText().toString());
        showProgress("");
        DataInterface.getInstance().getApi118_QuestionUpdate(QuestionDetailActivity.this, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if (response.getProcRsltCd().equals("118-000")) {
                    Toast.makeText(QuestionDetailActivity.this, "문의 업데이트 완료", Toast.LENGTH_SHORT).show();

                }else if(response.getProcRsltCd().equals("118-999")){ //답변이 접수된 문의내역은 수정이 불가
                    Toast.makeText(QuestionDetailActivity.this, response.getResultMsg() , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(QuestionDetailActivity.this, "문의 업데이트  실패. 관리자에게 문의하십시요.", Toast.LENGTH_SHORT).show();
            }

        });
        hideProgress();

    }

    private void request119QuestionDelete(String InqNo) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", AppDef.USER_LANGUAGE_CODE);  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", Util.getLineNumber(this));   //사용자 전화번호
        params.put("InqNo", InqNo);
        showProgress("");
        DataInterface.getInstance().getApi119_QuestionDelete(QuestionDetailActivity.this, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if (response.getProcRsltCd().equals("119-000")) {
                    Toast.makeText(QuestionDetailActivity.this, "문의내역 삭제 성공", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onError(ResponseData<Object> response) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(QuestionDetailActivity.this, "문의내역 삭제 실패. 관리자에게 문의하십시요.", Toast.LENGTH_SHORT).show();
            }

        });
        hideProgress();

    }
}