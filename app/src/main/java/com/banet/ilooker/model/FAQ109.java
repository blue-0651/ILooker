package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
}
