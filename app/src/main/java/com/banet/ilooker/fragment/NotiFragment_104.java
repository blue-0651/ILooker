package com.banet.ilooker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.banet.ilooker.R;
import com.banet.ilooker.adapter.NotiAdapter;
import com.banet.ilooker.databinding.FragmentNoti104Binding;
import com.banet.ilooker.model.Noti104;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.HashMap;
import java.util.List;


public class NotiFragment_104 extends BaseBindingFragment<FragmentNoti104Binding> {

    List<Noti104> normalNoti104List = null;
    List<Noti104> individualNoti105List = null;

//    public NotiFragment_104() {
//        // Required empty public constructor
//    }


    public static NotiFragment_104 newInstance(String param1, String param2) {
        NotiFragment_104 fragment = new NotiFragment_104();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_noti_104 ;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        setButtonBackGroundStable();

        getBinding().btnNormalPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request105IndividualNoti(getActivity());
                setButtonBackGroundStable();
                getBinding().btnNormalPersonal.setTextColor(Util.getColor(getActivity(),  R.color.good_number_color));
            }
        });

        getBinding().btnNormalNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request104NormalNoti(getActivity());
                setButtonBackGroundStable();
                getBinding().btnNormalNoti.setTextColor(Util.getColor(getActivity(), R.color.good_number_color));
            }
        });

        getBinding().btnNormalNoti.performClick();
    }

    void setButtonBackGroundStable(){
        getBinding().btnNormalPersonal.setTextColor(Util.getColor(getActivity(), R.color.black));
        getBinding().btnNormalNoti.setTextColor(Util.getColor(getActivity(), R.color.black));

    }

    private void request104NormalNoti(Context context) {
        showProgress("");
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(getActivity()));

        DataInterface.getInstance().getapi104requestNormalNoti(context, params, new DataInterface.ResponseCallback<ResponseData<Noti104>>() {


            @Override
            public void onSuccess(ResponseData<Noti104> response) {
                normalNoti104List =  (List<Noti104>) response.getList();
                getBinding().tvTotalNoti.setText("일반공지 총 :" + normalNoti104List.size() + " 건");
                getBinding().rvNoti.setAdapter(null);
                if( response.getProcRsltCd().equals("104-000")){
                    NotiAdapter notiAdapter = new NotiAdapter(getActivity(), normalNoti104List);
                    getBinding().rvNoti.setAdapter(notiAdapter);

                }
            }

            @Override
            public void onError(ResponseData<Noti104> response) {
                Toast.makeText(getContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
        hideProgress();
    }

    private void request105IndividualNoti(Context context) {
        showProgress("");
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(getActivity()));

        DataInterface.getInstance().getapi105_ApiIndividualNoticeInq(context, params, new DataInterface.ResponseCallback<ResponseData<Noti104>>() {

            @Override
            public void onSuccess(ResponseData<Noti104> response) {
                individualNoti105List =  (List<Noti104>) response.getList();
                getBinding().tvTotalNoti.setText("개인공지 총 :" + individualNoti105List.size());
                getBinding().rvNoti.setAdapter(null);
                if( response.getProcRsltCd().equals("105-000")){
                    NotiAdapter notiAdapter = new NotiAdapter(getActivity(), individualNoti105List);
                    getBinding().rvNoti.setAdapter(notiAdapter);
                }
            }

            @Override
            public void onError(ResponseData<Noti104> response) {
                Toast.makeText(getContext(), response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
            }
        });
        hideProgress();
    }


}