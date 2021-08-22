package com.banet.ilooker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.banet.ilooker.model.Noti104;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;

import java.util.List;


public class NotiFragment_104 extends Fragment {


    public NotiFragment_104() {
        // Required empty public constructor
    }


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

    private void request104NormalNoti(Context context) {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("UseLangCd", "KOR");
//        params.put("UserPhnNo", Util.getLineNumber(this));
//        params.put("PhnNo", incomingCallNumber);
//        params.put("MedPartCd", "001");
        DataInterface.getInstance().getapi104requestNormalNoti(this, new DataInterface.ResponseCallback<ResponseData<Noti104>>() {


            @Override
            public void onSuccess(ResponseData<Noti104> response) {

                if( response.getProcRsltCd().equals("104-000")){
                   List<Noti104>  noti104List =  (List<Noti104>) response.getList();

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