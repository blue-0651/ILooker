package com.banet.ilooker.fragment;

import android.content.Context;
import android.os.Bundle;
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

    List<Noti104> noti104List = null;

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
        request104NormalNoti(getActivity());
    }

    private void request104NormalNoti(Context context) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(getActivity()));

        DataInterface.getInstance().getapi104requestNormalNoti(context, params, new DataInterface.ResponseCallback<ResponseData<Noti104>>() {


            @Override
            public void onSuccess(ResponseData<Noti104> response) {

                if( response.getProcRsltCd().equals("104-000")){
                    noti104List =  (List<Noti104>) response.getList();
                    NotiAdapter notiAdapter = new NotiAdapter(getActivity(), noti104List);
                    getBinding().rvNoti.setAdapter(notiAdapter);
                    getBinding().tvTotalNoti.setText("일반공지 총 :" + noti104List.size());


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
    }


}