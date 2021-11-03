package com.banet.ilooker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.banet.ilooker.R;
import com.banet.ilooker.adapter.EventAdapter;
import com.banet.ilooker.databinding.FragmentEvent106Binding;
import com.banet.ilooker.model.Event106;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;

import java.util.HashMap;
import java.util.List;


public class EventFragment_106 extends BaseBindingFragment<FragmentEvent106Binding> {

    List<Event106> event106List = null;

//    public NotiFragment_104() {
//        // Required empty public constructor
//    }


    public static EventFragment_106 newInstance(String param1, String param2) {
        EventFragment_106 fragment = new EventFragment_106();
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

        return R.layout.fragment_event_106 ;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        request106Event(getActivity());
    }

    private void request106Event(Context context) {
        showProgress("");
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(getActivity()));

        DataInterface.getInstance().getapi106requestEvent(context, params, new DataInterface.ResponseCallback<ResponseData<Event106>>() {


            @Override
            public void onSuccess(ResponseData<Event106> response) {

                if( response.getProcRsltCd().equals("106-000")){
                    event106List =  (List<Event106>) response.getList();
                    EventAdapter eventAdapter = new EventAdapter(getActivity(), event106List);
                    getBinding().rvEvent.setAdapter(eventAdapter);
                    getBinding().tvTotalEvent.setText("이벤트 총 :" + event106List.size() + " 건");


                }
            }

            @Override
            public void onError(ResponseData<Event106> response) {
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