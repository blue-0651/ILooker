package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//sms에 url이 포함된경우
public class SMSUrlMsg004 extends IncommingCall   implements Serializable {


    @SerializedName("SmisDoubtYN")
    @Expose
    public String SmisDoubtYN ="";

    //2021.10.08 신규추가 (팝업에 사이트 이미지 캡쳐 보기)
    @SerializedName("UrlImgPath")
    @Expose
    public String UrlImgPath ="";

}
