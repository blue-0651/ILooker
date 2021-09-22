package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MainUserInfo101 implements Serializable {
    //        신고횟수	RptCnt
//        광고확인횟수	AdvtChkCnt
//        공지확인횟수	NtcChkCnt
//        이벤트확인횟수	EvtChkCnt
//        뉴스확인횟수	NewsChkCnt
//        문의요청횟수	InqChkCnt
//        이벤트참여횟수	EvtJoinCnt
//        최고현재Point	TopCurrPnt
//        최고신고횟수	TopRptCnt
//        최고광고확인횟수	TopAdvtChkCnt
//        최고공지확인횟수	TopNtcChkCnt
//        최고이벤트확인횟수	TopEvtChkCnt
//        최고뉴스확인횟수	TopNewsChkCnt
//        최고문의요청횟수	TopInqChkCnt
//        최고이벤트참여횟수	TopEvtJoinCnt

    @SerializedName("UserNm")
    @Expose
    public String UserNm ="";

    @SerializedName("CurrPnt")
    @Expose
    public String CurrPnt ="";

    @SerializedName("RptCnt")
    @Expose
    public String RptCnt ="";

    @SerializedName("CurrRank")
    @Expose
    public String CurrRank ="";

    @SerializedName("AdvtChkCnt")
    @Expose
    public String AdvtChkCnt ="";

    @SerializedName("NtcChkCnt")
    @Expose
    public String NtcChkCnt ="";

    @SerializedName("EvtChkCnt")
    @Expose
    public String EvtChkCnt ="";

    @SerializedName("NewsChkCnt")
    @Expose
    public String NewsChkCnt ="";

    @SerializedName("InqChkCnt")
    @Expose
    public String InqChkCnt ="";

    @SerializedName("EvtJoinCnt")
    @Expose
    public String EvtJoinCnt ="";

    @SerializedName("TopCurrPnt")
    @Expose
    public String TopCurrPnt ="";

    @SerializedName("TopRptCnt")
    @Expose
    public String TopRptCnt ="";

    @SerializedName("TopAdvtChkCnt")
    @Expose
    public String TopAdvtChkCnt ="";

    @SerializedName("TopNtcChkCnt")
    @Expose
    public String TopNtcChkCnt ="";

    @SerializedName("TopEvtChkCnt")
    @Expose
    public String TopEvtChkCnt ="";

    @SerializedName("TopNewsChkCnt")
    @Expose
    public String TopNewsChkCnt ="";

    @SerializedName("TopInqChkCnt")
    @Expose
    public String TopInqChkCnt ="";

    @SerializedName("TopEvtJoinCnt")
    @Expose
    public String TopEvtJoinCnt ="";

}
