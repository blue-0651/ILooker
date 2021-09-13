package com.banet.ilooker.model;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;

public class Noti104 implements Serializable {
    @SerializedName("Seq")
    @Expose
    public String Seq ="";

    @SerializedName("NtcNo")
    @Expose
    public String NtcNo ="";

    @SerializedName("NtcStaDate")
    @Expose
    public String NtcStaDate ="";

    @SerializedName("NtcEndDate")
    @Expose
    public String NtcEndDate ="";

    @SerializedName("NtcTpNm")
    @Expose
    public String NtcTpNm ="";

    @SerializedName("NtcTitl")
    @Expose
    public String NtcTitl ="";

    @SerializedName("NtcDescPath")
    @Expose
    public String NtcDescPath  ="";


    public  void request110GeneralNoticedetail(Context context, TextView tvDate, TextView tvSeq, TextView tvTitle, ImageView ivContent) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", AppDef.USER_LANGUAGE_CODE);  //사용자 국가코드 "KOR"
        params.put("UserPhnNo", Util.getLineNumber(context));   //사용자 전화번호
        params.put("NtcNo", NtcNo);        //공지번호
        DataInterface.getInstance().getApi110GeneralNotice(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {
            @Override
            public void onSuccess(ResponseData<Object> response) {
                if (response.getProcRsltCd().equals("110-000")) {
                    tvDate.setText(NtcStaDate + " ~ " + NtcEndDate);
                    tvSeq.setText("No. : " + NtcNo);
                    tvTitle.setText(NtcTitl);
                    Glide.with(context)
                            .load(NtcDescPath)
                            .into(ivContent);
                }
            }

            @Override
            public void onError(ResponseData<Object> response) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, "공지내역 수신 실패. 관리자에게 문의하십시요.", Toast.LENGTH_SHORT).show();
            }

        });

    }


}
