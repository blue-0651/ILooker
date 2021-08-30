package com.banet.ilooker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.databinding.FragmentReportReg005Binding;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.HashMap;

/*
* 005 전화번호 신고 차단
 */
public class Report_RegFragment_005 extends BaseBindingFragment<FragmentReportReg005Binding> {
     String mIncomingPhoneNumber;
     String mImcommingDateTime;
     int mRadioCategoryCheckedId = 0;
    int mRadioLikeCheckedId = 1;
    public Report_RegFragment_005() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Report_RegFragment_005 newInstance(String param1, String param2) {
        Report_RegFragment_005 fragment = new Report_RegFragment_005();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           Bundle bundle = getArguments();
            mIncomingPhoneNumber = bundle.getString(AppDef.incoming_number_extra);
            mImcommingDateTime = bundle.getString(AppDef.incoming_date_time);
        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_report_reg_005, container, false);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_report_reg_005;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getBinding().blockPhoneNo.setText(mIncomingPhoneNumber);
       // getBinding()..setText(mImcommingDateTime);
        getBinding().rdCategoryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mRadioCategoryCheckedId = checkedId;
            }
        });
        getBinding().rdLikeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mRadioLikeCheckedId = checkedId;
            }
        });
        getBinding().llReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request005RequestReportPhoneNo(getActivity());
            }
        });

    }
    public  void request005RequestReportPhoneNo(Context context) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(getActivity()));
        params.put("MedPartCd", "001");
        params.put("GoodYN", String.valueOf(mRadioLikeCheckedId));
        params.put("BlockYN", "N");
        params.put("RptTpClsCd", String.valueOf(mRadioCategoryCheckedId));
        params.put("PhnNo", mIncomingPhoneNumber);

        DataInterface.getInstance().getapi005requestReportPhonNo(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {

            @Override
            public void onSuccess(ResponseData<Object> response) {

                if( response.getProcRsltCd().equals("005-000")){
                    Toast.makeText(context, "신고가 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString(AppDef.FRAGMENT_TITLE_NAME, AppDef.title_block_phone_number_history_fragment);
                    GoNativeScreen((BaseBindingFragment)new BlockPhoneNumberFragment(), bundle);
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {
                Toast.makeText(getContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}