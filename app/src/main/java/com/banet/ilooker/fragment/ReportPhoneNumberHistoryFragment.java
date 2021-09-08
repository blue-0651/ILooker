package com.banet.ilooker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.banet.ilooker.R;
import com.banet.ilooker.activity.MainActivity;
import com.banet.ilooker.adapter.ReportPhoneNumberListAdapter;
import com.banet.ilooker.databinding.FragmentBlockPhoneNumberBinding;
import com.banet.ilooker.model.ReportHistory102;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//102 신고내역
public class ReportPhoneNumberHistoryFragment extends BaseBindingFragment<FragmentBlockPhoneNumberBinding>{

    private ReportPhoneNumberListAdapter mReportHistoryAdapter;
    private List<ReportHistory102> mReportHistoryList = new ArrayList<>();

    public ReportPhoneNumberHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_block_phone_number;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ( (MainActivity)getActivity()).setBottomTabBarVisible(false);

        getBinding().searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request102ReportHistory(getActivity());
            }
        });

        request102ReportHistory(getActivity());
    }

    private void request102ReportHistory(Context context) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(getActivity()));
        params.put("SrchCond", getBinding().etSearchCond.getText().toString().trim());

        DataInterface.getInstance().getapi102_ApiReportHistoryInq(context, params, new DataInterface.ResponseCallback<ResponseData<ReportHistory102>>() {

            @Override
            public void onSuccess(ResponseData<ReportHistory102> response) {

                if( response.getProcRsltCd().equals("102-000")){
                    mReportHistoryList =  (List<ReportHistory102>) response.getList();
                    mReportHistoryAdapter = new ReportPhoneNumberListAdapter(getActivity(), mReportHistoryList);
                    getBinding().rvSearchBlockNumber.setAdapter(mReportHistoryAdapter);
                    getBinding().tvTotalNo.setText(" 총 신고내역  :" + mReportHistoryList.size());

                }
            }

            @Override
            public void onError(ResponseData<ReportHistory102> response) {
                Toast.makeText(getContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }




}