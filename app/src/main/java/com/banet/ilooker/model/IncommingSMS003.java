package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IncommingSMS003 {
    @SerializedName("TopTpClsNm")
    @Expose
    String TopTpClsNm;    //대표유형분류명

    @SerializedName("WhtListYN")
    @Expose
    String WhtListYN;  //화이트리스트여부

    @SerializedName("OrgNm")
    @Expose
    String OrgNm;     //기관명
    @SerializedName("GoodTotCnt")
    @Expose
    String GoodTotCnt;  //좋아요총건수

    @SerializedName("BadTotCnt")
    @Expose
    String BadTotCnt;   //싫어요총건수


}
