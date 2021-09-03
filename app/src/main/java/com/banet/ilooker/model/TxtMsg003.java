package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TxtMsg003 implements Serializable {
    //분류유형
    @SerializedName("TopTpClsNm")
    @Expose
    public String TopTpClsNm = "";

    @SerializedName("TopTpGoodCnt")
    @Expose
    public String TopTpGoodCnt = "";

    @SerializedName("TopTpBadCnt")
    @Expose
    public String TopTpBadCnt = "";

    @SerializedName("WhtListYN")
    @Expose
    public String WhtListYN = "";

    @SerializedName("OrgNm")
    @Expose
    public String OrgNm = "";

    @SerializedName("GoodTotCnt")
    @Expose
    public String GoodTotCnt = "";

    @SerializedName("BadTotCnt")
    @Expose
    public String BadTotCnt = "";

}
