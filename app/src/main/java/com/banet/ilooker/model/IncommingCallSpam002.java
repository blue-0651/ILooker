package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * 수신전화 스팸여부 응답
 */
public class IncommingCallSpam002 implements Serializable {

    public IncommingCallSpam002(){
        this.TopTpClsNm = "도박";
         this.TopTpGoodCnt = "10";
        this.TopTpBadCnt = "5";
        this.WhtListYN = "N";
        this.OrgNm = "기관12";
        this.GoodTotCnt = "100";
        this.BadTotCnt = "20";

    }

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
