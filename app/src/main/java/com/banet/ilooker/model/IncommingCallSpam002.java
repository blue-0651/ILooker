package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * 수신전화 스팸여부 응답
 */
public class IncommingCallSpam002 implements Serializable {
    @SerializedName("TopTpClsNm")
    @Expose
    public String TopTpClsNm = "";

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
