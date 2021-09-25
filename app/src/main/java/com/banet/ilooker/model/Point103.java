package com.banet.ilooker.model;

import android.content.Context;
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

//포인트
public class Point103 extends MenuListObject {
     //순번
    @SerializedName("Seq")
    @Expose
    public String Seq ="";

    //포인트가감일자
    @SerializedName("PntAdjDate")
    @Expose
    public String PntAdjDate ="";

    @SerializedName("PntAdjRsnNm")
    @Expose
    public String PntAdjRsnNm ="";

    @SerializedName("Pnt")
    @Expose
    public String Pnt ="";

    @SerializedName("PntAdjRsnDescPath")
    @Expose
    public String PntAdjRsnDescPath  ="";


    public void request103(Context context, RecyclerView rv, TextView tvPointSize , TextView tvCurrPoint) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("UseLangCd", "KOR");
        params.put("UserPhnNo", Util.getLineNumber(context.getApplicationContext()));

        DataInterface.getInstance().get103_ApiPointInq(context, params, new DataInterface.ResponseCallback<ResponseData<Point103>>() {

            @Override
            public void onSuccess(ResponseData<Point103> response) {

                if (response.getProcRsltCd().equals("103-000")) {
                    List<Point103> Points103List = (List<Point103>) response.getList();

                    for(int i = 0; Points103List.size() >i ; i++){
                        Points103List.get(i).setMenuListField();
                    }
                    MenuListAdapter menuListAdapter = new MenuListAdapter(context, Collections.singletonList(Points103List), AppDef.title_point_fragment);
                    rv.setAdapter(menuListAdapter);
                    tvPointSize.setText("포인트이력 총 : " + Points103List.size() + "건");
                    tvCurrPoint.setText("현재포인트 " + response.getCurrPnt() + "점");

                }
            }

            @Override
            public void onError(ResponseData<Point103> response) {
                Toast.makeText(context, response.getError(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //상세내역 api 없음

    @Override
    public void setMenuListField() {
        ListSeq = Seq;
        ListTitle = PntAdjRsnNm;
        ListStartDate = PntAdjDate;
        ListContent = PntAdjRsnDescPath;
        ListPoint = Pnt;
    }

}
