package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//광고창 내용 조회
public class Advertise100 implements Serializable {
    //광고번호
    @SerializedName("AdvtNo")
    @Expose
    public String AdvtNo = "";
    //광고제목
    @SerializedName("AdvtTitl")
    @Expose
    public String AdvtTitl = "";
    //광고내용 경로
    @SerializedName("AdvtDescPath")
    @Expose
    public String AdvtDescPath = "";


}
