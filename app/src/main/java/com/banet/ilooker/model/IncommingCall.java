package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * 수신전화/문자 스팸여부 응답
 */
public class IncommingCall implements Serializable {

    public IncommingCall(){
        this.TopTpClsNm = "진상손님";
         this.TopTpGoodCnt = "10";
        this.TopTpBadCnt = "5";
        this.WhtListYN = "N";
        this.OrgNm = "기관12";
        this.GoodTotCnt = "100";
        this.BadTotCnt = "20";

    }

    public IncommingCall(String phoneNumber){
        this.phnNumber = phoneNumber;
    }

    public IncommingCall(String processResultCd, String phoneNumber){
        this.ProcessResultCd = processResultCd;
        this.phnNumber = phoneNumber;
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
    public String GoodTotCnt = "0";

    @SerializedName("BadTotCnt")
    @Expose
    public String BadTotCnt = "0";

    public boolean isInSystem = false;
    public String ProcessResultCd = "";
    public String phnNumber ="";

}
