package com.banet.ilooker.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.ActivityBlockPhoneNumberDetailBinding;
import com.banet.ilooker.model.BlockedPhoneNumber;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;

public class BlockPhoneNumberDetailActivity extends BaseActivity<ActivityBlockPhoneNumberDetailBinding> {
    Realm realm;
    BlockedPhoneNumber mBlockedPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        if (getIntent().getParcelableExtra(AppDef.BlockedPhoneNumber_Extra) != null) {
            mBlockedPhoneNumber = (BlockedPhoneNumber) getIntent().getParcelableExtra(AppDef.BlockedPhoneNumber_Extra);
        }
        getBinding().titleBar.tvTitle.setText(AppDef.title_blocked_phone_number_detail);
        getBinding().titleBar.btnTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getBinding().blockPhoneNo.setText(mBlockedPhoneNumber.PhnNo);

        setLikeChecked(mBlockedPhoneNumber.GoodYN);
        setCategoryChecked(mBlockedPhoneNumber.RptTpClsCd);


        getBinding().llReportSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestApi009SafeNumberReg(BlockPhoneNumberDetailActivity.this);
            }
        });
        getBinding().llUnblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                unblockRequest008(BlockPhoneNumberDetailActivity.this);
            }
        });
        getBinding().llCallReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:" + mBlockedPhoneNumber.PhnNo);
                Intent i = new Intent(Intent.ACTION_DIAL, uri);

                startActivity(i);//액티비티 실행
            }
        });

        getBinding().llSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:" + mBlockedPhoneNumber.PhnNo); //sms 문자와 관련된 Data는 'smsto:'로 시작. 이후는 문자를 받는 사람의 전화번호

                Intent i = new Intent(Intent.ACTION_SENDTO, uri); //시스템 액티비티인 SMS문자보내기 Activity의 action값

                //  i.putExtra("sms_body", "Hello...");  //보낼 문자내용을 추가로 전송, key값은 반드시 'sms_body'

                startActivity(i);//액티비티 실행
            }
        });

    }

    void setLikeChecked(String likeChecked) {
        setLikeRdAllFalse();
        switch (likeChecked) {
            case "Y":
                getBinding().rdLike.setChecked(true);
            case "N":
                getBinding().rdDisLike.setChecked(true);
            default:

        }
        getBinding().rdLike.setEnabled(false);
        getBinding().rdDisLike.setEnabled(false);
    }

    void setLikeRdAllFalse() {
        getBinding().rdLike.setChecked(false);
        getBinding().rdDisLike.setChecked(false);
    }

    //안심등록 번호 등록
    private void requestApi009SafeNumberReg(Context context) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(this).replace("-", ""));
        params.put("PhnNo", mBlockedPhoneNumber.PhnNo.replace("-", ""));

        DataInterface.getInstance().getApi009SafeNumberReg(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {

            @Override
            public void onSuccess(ResponseData<Object> response) {

                if (response.getProcRsltCd().equals("009-000")) {
                    if (Util.isThePhoneNumberAlreadyBlocked(mBlockedPhoneNumber.PhnNo.replace("-", "")))
                        deleteBlockedData(mBlockedPhoneNumber.PhnNo.replace("-", ""));

                    Toast.makeText(context, "안심등록이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();

//                    Bundle bundle = new Bundle();
//                    bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_fragment);
//                    GoNativeScreen((BaseBindingFragment) new BlockedPhoneNumberListFragment(), bundle);
//                    Intent intent = new Intent(BlockPhoneNumberDetailActivity.this, MainActivity.class);
//                    intent.putExtra(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_main_fragment);
//                    intent.putExtra(AppDef.MOVE_TO_FRAGMENT, AppDef.title_block_and_report_phone_number_fragment);
                //    intent.putExtra(AppDef.MOVE_TO_BLOCK_PHONE_NUMBER, incomingCallNumber);

//                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {
                Toast.makeText(BlockPhoneNumberDetailActivity.this, response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(BlockPhoneNumberDetailActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void unblockRequest008(Context context) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(this).replace("-", ""));
        params.put("PhnNo", mBlockedPhoneNumber.PhnNo.replace("-", ""));

        DataInterface.getInstance().getApi008_ApiUnblock(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {

            @Override
            public void onSuccess(ResponseData<Object> response) {

                if (response.getProcRsltCd().equals("008-000")) {
                    // unblockPhoneNumber(mRecentCallLog.phoneNumber.replace("-", ""));
                    deleteBlockedData(mBlockedPhoneNumber.PhnNo.replace("-", ""));
                    Toast.makeText(context, "차단해제가 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {
                Toast.makeText(BlockPhoneNumberDetailActivity.this, response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(BlockPhoneNumberDetailActivity.this, "failure", Toast.LENGTH_SHORT).show();
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_block_phone_number_detail;
    }

    void setCategoryChecked(String ReportTypeClassCode) {
        allCategoryRadioButtonsMakeFalse();
        switch (ReportTypeClassCode) {
            case "001":
                getBinding().rd001Loan.setChecked(true);
                break;
            case "002":
                getBinding().rd002Gamble.setChecked(true);
                break;
            case "003":
                getBinding().rd003Adult.setChecked(true);
                break;

            case "004":
                getBinding().rd004PhoneSales.setChecked(true);
                break;

            case "005":
                getBinding().rd005Insurance.setChecked(true);
                break;

            case "006":
                getBinding().rd006AltDriving.setChecked(true);
                break;

            case "007":
                getBinding().rd007InternetSales.setChecked(true);
                break;

            case "008":
                getBinding().rd008Stock.setChecked(true);
                break;

            case "009":
                getBinding().rd009Delivery.setChecked(true);
                break;

            case "010":
                getBinding().rd010Ad.setChecked(true);
                break;

            case "011":
                getBinding().rd011Login.setChecked(true);
                break;
            case "012":
                getBinding().rd012NotFound.setChecked(true);
                break;
            case "013":
                getBinding().rd013PoliceBlock.setChecked(true);
                break;

            case "101":
                getBinding().rd101Telemarketing.setChecked(true);
                break;

            case "102":
                getBinding().rd102VoicePissing.setChecked(true);
                break;
            case "103":
                getBinding().rd103Call.setChecked(true);
                break;
            case "104":
                getBinding().rd104Census.setChecked(true);
                break;
            case "105":
                getBinding().rd105UsedProductFake.setChecked(true);
                break;
            case "106":
                getBinding().rd106Smissing.setChecked(true);
                break;

            case "999":
                getBinding().rd999Micell.setChecked(true);
                break;

            default:

        }
        allCategoryRadioButtonsMakeEnabledFalse();

    }

    void allCategoryRadioButtonsMakeFalse() {
        getBinding().rd001Loan.setChecked(false);
        getBinding().rd002Gamble.setChecked(false);
        getBinding().rd003Adult.setChecked(false);
        getBinding().rd004PhoneSales.setChecked(false);
        getBinding().rd005Insurance.setChecked(false);
        getBinding().rd006AltDriving.setChecked(false);
        getBinding().rd007InternetSales.setChecked(false);
        getBinding().rd008Stock.setChecked(false);
        getBinding().rd009Delivery.setChecked(false);
        getBinding().rd010Ad.setChecked(false);
        getBinding().rd011Login.setChecked(false);
        getBinding().rd012NotFound.setChecked(false);
        getBinding().rd013PoliceBlock.setChecked(false);
        getBinding().rd101Telemarketing.setChecked(false);
        getBinding().rd102VoicePissing.setChecked(false);
        getBinding().rd103Call.setChecked(false);
        getBinding().rd104Census.setChecked(false);
        getBinding().rd105UsedProductFake.setChecked(false);
        getBinding().rd106Smissing.setChecked(false);
        getBinding().rd999Micell.setChecked(false);

    }

    void allCategoryRadioButtonsMakeEnabledFalse() {
        getBinding().rd001Loan.setEnabled(false);
        getBinding().rd002Gamble.setEnabled(false);
        getBinding().rd003Adult.setEnabled(false);
        getBinding().rd004PhoneSales.setEnabled(false);
        getBinding().rd005Insurance.setEnabled(false);
        getBinding().rd006AltDriving.setEnabled(false);
        getBinding().rd007InternetSales.setEnabled(false);
        getBinding().rd008Stock.setEnabled(false);
        getBinding().rd009Delivery.setEnabled(false);
        getBinding().rd010Ad.setEnabled(false);
        getBinding().rd011Login.setEnabled(false);
        getBinding().rd012NotFound.setEnabled(false);
        getBinding().rd013PoliceBlock.setEnabled(false);
        getBinding().rd101Telemarketing.setEnabled(false);
        getBinding().rd102VoicePissing.setEnabled(false);
        getBinding().rd103Call.setEnabled(false);
        getBinding().rd104Census.setEnabled(false);
        getBinding().rd105UsedProductFake.setEnabled(false);
        getBinding().rd106Smissing.setEnabled(false);
        getBinding().rd999Micell.setEnabled(false);

    }
}