package com.banet.ilooker.model;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.banet.ilooker.adapter.MenuListAdapter;
import com.banet.ilooker.common.AppDef;
import com.banet.ilooker.net.DataInterface;
import com.banet.ilooker.net.ResponseData;
import com.banet.ilooker.util.Util;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class News107 extends MenuListObject {

    //순번
    @SerializedName("Seq")
    @Expose
    public String Seq = "";


    @SerializedName("NewsNo")
    @Expose
    public String NewsNo = "";


    @SerializedName("NewsStaDate")
    @Expose
    public String NewsStaDate = "";

    @SerializedName("NewsEndDate")
    @Expose
    public String NewsEndDate = "";

    @SerializedName("NewsTitl")
    @Expose
    public String NewsTitl = "";

    @SerializedName("NewsDescPath")
    @Expose
    public String NewsDescPath = "";





    public void request107(Context context, RecyclerView rv, TextView tv) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(context.getApplicationContext()));

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

    public void request113NewsDetail(Context context, TextView tvDate, TextView tvSeq, TextView tvTitle, ImageView ivContent) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(context.getApplicationContext()));
        params.put("NewsNo",    NewsNo   );


        DataInterface.getInstance().getApi113_NewsDetail(context, params, new DataInterface.ResponseCallback<ResponseData<Object>>() {

            @Override
            public void onSuccess(ResponseData<Object> response) {

                if (response.getProcRsltCd().equals("113-000")) {
                        tvDate.setText(NewsStaDate+ " ~ " + NewsEndDate);
                        tvSeq.setText("No. : " + NewsNo);
                        tvTitle.setText(NewsTitl);
                        Glide.with(context)
                                .load(NewsDescPath)
                                .into(ivContent);

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
       ListNo = NewsNo;
       ListTitle = NewsTitl;
       ListStartDate = NewsStaDate;
       ListEndDate =  NewsEndDate;
       ListContent = NewsDescPath;
    }

}
