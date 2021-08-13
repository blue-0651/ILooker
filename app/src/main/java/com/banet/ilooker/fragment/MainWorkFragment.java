package com.banet.ilooker.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import kst.macaron.chauffeur.MacaronApp;
import kst.macaron.chauffeur.R;
import kst.macaron.chauffeur.databinding.MainFragmentBinding;
import kst.macaron.chauffeur.net.DataInterface;
import kst.macaron.chauffeur.net.ResponseData;
import kst.macaron.chauffeur.utility.Logger;
import kst.macaron.chauffeur.utility.OnSingleClickListener;
import kst.macaron.chauffeur.utility.PrefUtil;
import kst.macaron.chauffeur.utility.Util;


public class MainWorkFragment extends NativeFragment {
    MainFragmentBinding mBind;
    protected String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetTitle("");
        SetDividerVisibility(false);
        setDrawerLayoutEnable(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBind = MainFragmentBinding.bind(getView());

        mBind.btnStartWork.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                PrefUtil.setBackKeyCheck(getActivity(), true);
             //   sendWorkStatus(AppDef.ChauffeurStatus.INOFFICE.toString());
                GoNativeScreenAdd(new DrivingFragment(), null);
            }
        });

        mBind.title.btnDrawerOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventForTitleView(v);
            }
        });
    }

    private void sendWorkStatus(String status) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("chauffeurStatusCat", status);
        params.put("lat", MacaronApp.lastLocation.getLatitude());
        params.put("lon", MacaronApp.lastLocation.getLongitude());
        params.put("poi", Util.GetLocationAddress(getActivity(), MacaronApp.lastLocation));

        DataInterface.getInstance().sendChauffeurStatus(getActivity(), params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if ("S000".equals(response.getResultCode())) {
                    Logger.i(TAG, "출근상태 전환 성공");

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        MacaronApp.nearByDriveStartCheck = true;
                    } else {
                        MacaronApp.nearByDriveStartCheck = false;
                    }

                    GoNativeScreenAdd(new DrivingFragment(), null);
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}


