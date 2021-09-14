package com.banet.ilooker.model;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.banet.ilooker.adapter.MenuListAdapter;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FAQ109 extends MenuListObject {

    //순번
    @SerializedName("Seq")
    @Expose
    public String Seq ="";


    @SerializedName("FaqNo")
    @Expose
    public String FaqNo ="";

    @SerializedName("FaqKindNm")
    @Expose
    public String FaqKindNm ="";
    //문의내용
    @SerializedName("InqDesc")
    @Expose
    public String InqDesc ="";

    @SerializedName("AnsDescPath")
    @Expose
    public String AnsDescPath ="";

    @SerializedName("AddDate")
    @Expose
    public String AddDate ="";


    @Override
    public void setMenuListField() {
        ListSeq = Seq;
        ListNo = FaqNo;
        ListTitle = InqDesc;
        ListStartDate = AddDate;
        ListContent = AnsDescPath;
    }

//    109_ApiFAQInq : FAQ 목록 조회
//    115_ApiFAQDtl : FAQ 내역 확인


    public void request109(Context context, RecyclerView rv, TextView tv, EditText etSearch) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(context.getApplicationContext()));
        params.put("FaqKindCd", "001");
        params.put("UserPhnNo", etSearch.getText().toString().trim());

        DataInterface.getInstance().getapi107requestNews(context, params, new DataInterface.ResponseCallback<ResponseData<News107>>() {

            @Override
            public void onSuccess(ResponseData<News107> response) {

                if (response.getProcRsltCd().equals("107-000")) {
                    List<News107> news107List = (List<News107>) response.getList();
                    for(int i = 0; news107List.size() >i ; i++){
                        news107List.get(i).setMenuListField();
                    }
                    MenuListAdapter menuListAdapter = new MenuListAdapter(context, Collections.singletonList(news107List), AppDef.title_news_fragment);
                    rv.setAdapter(menuListAdapter);
                    tv.setText("뉴스내역 총 :" + news107List.size());

                }
            }

            @Override
            public void onError(ResponseData<News107> response) {
                Toast.makeText(context, response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
