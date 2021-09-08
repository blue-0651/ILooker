package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
//신고 이력 조회 응답
public class ReportHistory102 implements Serializable {

    @SerializedName("Seq")
    @Expose
    public String Seq ="";

    @SerializedName("RcvDate")
    @Expose
    public String RcvDate = "";

    @SerializedName("RcvTime")
    @Expose
    public String RcvTime = "";

    @SerializedName("PhnNo")
    @Expose
    public String PhnNo = "";

    @SerializedName("GoodYN")
    @Expose
    public String GoodYN = "";

    @SerializedName("BlockYN")
    @Expose
    public String BlockYN = "";

    @SerializedName("RptTpClsCd")
    @Expose
    public String RptTpClsCd = "";

    @SerializedName("RptTpClsNm")
    @Expose
    public String RptTpClsNm = "";
    //기타 유형 입력내용
    @SerializedName("EtcTpInpDesc")
    @Expose
    public String EtcTpInpDesc = "";
}
