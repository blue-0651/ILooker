package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//sms에 url이 포함된경우
public class SMSUrlMsg004 implements Serializable {
    //순번
    @SerializedName("OrgNm")
    @Expose
    public String OrgNm ="";

    @SerializedName("GoodTotCnt")
    @Expose
    public String GoodTotCnt ="";

    @SerializedName("BadTotCnt")
    @Expose
    public String BadTotCnt ="";

    @SerializedName("TopTpClsNm")
    @Expose
    public String TopTpClsNm ="";

    //대표유형 좋아요 건수
    @SerializedName("TopTpGoodCnt")
    @Expose
    public String TopTpGoodCnt ="";

    @SerializedName("TopTpBadCnt")
    @Expose
    public String TopTpBadCnt ="";

    @SerializedName("SmisDoubtYN")
    @Expose
    public String SmisDoubtYN ="";

    //2021.10.08 신규추가 (팝업에 사이트 이미지 캡쳐 보기)
    @SerializedName("UrlImgPath")
    @Expose
    public String UrlImgPath ="";

}
