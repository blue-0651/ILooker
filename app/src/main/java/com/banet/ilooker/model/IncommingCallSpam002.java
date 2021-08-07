package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
* 수신전화 스팸여부 응답
 */
public class IncommingCallSpam002 implements Serializable {
    @SerializedName("isSpam")
    @Expose
    String isSpam = "";
}
