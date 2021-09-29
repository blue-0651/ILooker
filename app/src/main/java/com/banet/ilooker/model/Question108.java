package com.banet.ilooker.model;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
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

public class Question108 extends MenuListObject {
    //순번
    @SerializedName("Seq")
    @Expose
    public String Seq ="";


    @SerializedName("InqNo")
    @Expose
    public String InqNo ="";


    @SerializedName("InqDate")
    @Expose
    public String InqDate ="";

    @SerializedName("Email")
    @Expose
    public String Email ="";

    @SerializedName("InqTitl")
    @Expose
    public String InqTitl ="";

    @SerializedName("InqDesc")
    @Expose
    public String InqDesc  ="";

    @SerializedName("AnsDate")
    @Expose
    public String AnsDate  ="";

    @SerializedName("AnsStatNm")
    @Expose
    public String AnsStatNm  ="";

    public void request108(Context context, RecyclerView rv, TextView tv) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(context.getApplicationContext()));

        DataInterface.getInstance().getapi108requestQuestion(context, params, new DataInterface.ResponseCallback<ResponseData<Question108>>() {

            @Override
            public void onSuccess(ResponseData<Question108> response) {

                if (response.getProcRsltCd().equals("108-000")) {
                    List<Question108> Question108List = (List<Question108>) response.getList();
                    for(int i = 0; Question108List.size() >i ; i++){
                        Question108List.get(i).setMenuListField();
                    }
                    MenuListAdapter menuListAdapter = new MenuListAdapter(context, Collections.singletonList(Question108List), AppDef.title_questions_fragment);
                    rv.setAdapter(menuListAdapter);
                    tv.setText("문의 총 :" + Question108List.size());

                }else{
                    Toast.makeText(context, response.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(ResponseData<Question108> response) {
                Toast.makeText(context, response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void request114QuestionDetail(Context context, TextView tvDate, TextView tvSeq, TextView tvTitle, ImageView ivContent, TextView tvContent) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(context.getApplicationContext()));
        params.put("InqNo",    InqNo   );


        DataInterface.getInstance().getApi114_questionDetail(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {

            @Override
            public void onSuccess(ResponseData<Object> response) {

                if (response.getProcRsltCd().equals("114-000")) {
                    tvDate.setText(ListStartDate);
                    tvSeq.setText("No. : " + InqNo);
                    tvTitle.setText(InqTitl);
                    if(tvContent.getVisibility() == View.GONE) {
                        ivContent.setVisibility(View.GONE);
                        tvContent.setVisibility(View.VISIBLE);
                        tvContent.setText(InqDesc);
                    }

                }
            }

            @Override
            public void onError(ResponseData<Object> response) {
                Toast.makeText(context, response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setMenuListField() {
        ListSeq = Seq;
        ListNo = InqNo;
        ListTitle = InqTitl;
        ListStartDate = InqDate;
        ListContent = InqDesc;
    }
}
