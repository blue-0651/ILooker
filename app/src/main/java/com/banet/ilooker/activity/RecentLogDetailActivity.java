package com.banet.ilooker.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.ActivityRecentLogDetailBinding;
import com.banet.ilooker.fragment.BaseBindingFragment;
import com.banet.ilooker.fragment.BlockedPhoneNumberListFragment;
import com.banet.ilooker.model.BlockedPhoneNumber;
import com.banet.ilooker.model.RecentCallLog;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecentLogDetailActivity extends BaseActivity<ActivityRecentLogDetailBinding> {
    RecentCallLog mRecentCallLog;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        if (getIntent().getSerializableExtra(AppDef.RecentCallLog_Extra) != null) {
            mRecentCallLog = (RecentCallLog) getIntent().getSerializableExtra(AppDef.RecentCallLog_Extra);
        }
        getBinding().titleBar.tvTitle.setText(AppDef.title_latest_call_log_detail);
        getBinding().titleBar.btnTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getBinding().tvDate.setText(mRecentCallLog.date + " " + mRecentCallLog.time);
        getBinding().tvRecentLogPhoneNumber.setText(Util.formatPhoneNumberWithHyPen(mRecentCallLog.phoneNumber));
        if (mRecentCallLog.smsContent != null && !mRecentCallLog.smsContent.equals("")) {
            getBinding().smsContent.setVisibility(View.VISIBLE);
            getBinding().smsContent.setText(mRecentCallLog.smsContent);
        }

        getBinding().llReportBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecentLogDetailActivity.this, MainActivity.class);
                intent.putExtra(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_main_fragment);
                intent.putExtra(AppDef.MOVE_TO_FRAGMENT, AppDef.title_block_and_report_phone_number_fragment);
                intent.putExtra(AppDef.MOVE_TO_BLOCK_INCOMMING_CALL, mRecentCallLog.phoneNumber.replace("-", ""));

                startActivity(intent);
                finish();
            }
        });


        getBinding().llReportSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestApi009SafeNumberReg(RecentLogDetailActivity.this);
            }
        });
        getBinding().llUnblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                unblockRequest008(RecentLogDetailActivity.this);
            }
        });
        getBinding().llCallReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:" + mRecentCallLog.phoneNumber);
                Intent i = new Intent(Intent.ACTION_DIAL, uri);

                startActivity(i);//액티비티 실행
            }
        });

        getBinding().llSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:" + mRecentCallLog.phoneNumber); //sms 문자와 관련된 Data는 'smsto:'로 시작. 이후는 문자를 받는 사람의 전화번호

                Intent i = new Intent(Intent.ACTION_SENDTO, uri); //시스템 액티비티인 SMS문자보내기 Activity의 action값

                //  i.putExtra("sms_body", "Hello...");  //보낼 문자내용을 추가로 전송, key값은 반드시 'sms_body'

                startActivity(i);//액티비티 실행
            }
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recent_log_detail;
    }

    //안심등록 번호 등록
    private void requestApi009SafeNumberReg(Context context) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(this).replace("-", ""));
        params.put("PhnNo", mRecentCallLog.phoneNumber.replace("-", ""));

        DataInterface.getInstance().getApi009SafeNumberReg(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {

            @Override
            public void onSuccess(ResponseData<Object> response) {

                if (response.getProcRsltCd().equals("009-000")) {
                    if (Util.isThePhoneNumberAlreadyBlocked(mRecentCallLog.phoneNumber.replace("-", "")))
                        deleteBlockedData(mRecentCallLog.phoneNumber.replace("-", ""));

                    Toast.makeText(context, "안심등록이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_fragment);
                    GoNativeScreen((BaseBindingFragment) new BlockedPhoneNumberListFragment(), bundle);
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {
                Toast.makeText(RecentLogDetailActivity.this, response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RecentLogDetailActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void unblockRequest008(Context context) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(this).replace("-", ""));
        params.put("PhnNo", mRecentCallLog.phoneNumber.replace("-", ""));

        DataInterface.getInstance().getApi008_ApiUnblock(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {

            @Override
            public void onSuccess(ResponseData<Object> response) {

                if (response.getProcRsltCd().equals("008-000")) {
                    // unblockPhoneNumber(mRecentCallLog.phoneNumber.replace("-", ""));
                    deleteBlockedData(mRecentCallLog.phoneNumber.replace("-", ""));
                    Toast.makeText(context, "차단해제가 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {
                Toast.makeText(RecentLogDetailActivity.this, response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RecentLogDetailActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });

    }


   //  전화번호 정보 삭제
    private void deleteBlockedData(String phoneNumber) {

        realm.beginTransaction();
        RealmResults<BlockedPhoneNumber> List = realm.where(BlockedPhoneNumber.class)
                .equalTo("PhnNo", phoneNumber.replace("-", ""))
                .findAll();

        List.deleteAllFromRealm();
        realm.commitTransaction();
    }

    private void unblockPhoneNumber(String phoneNumber) {
        BlockedPhoneNumber result2 = realm.where(BlockedPhoneNumber.class)
                .equalTo("PhnNo", phoneNumber.replace("-", ""))
//                .or()
//                .equalTo("name", "Peter")
                .findFirst();
        realm.beginTransaction();
        result2.setBlockYN("N");
        realm.commitTransaction();


    }


}