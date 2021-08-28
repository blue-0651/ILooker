package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MainUserInfo101 implements Serializable {

    @SerializedName("UserNm")
    @Expose
    public String UserNm ="";

    @SerializedName("CurrPnt")
    @Expose
    public String CurrPnt ="";

    @SerializedName("RptCnt")
    @Expose
    public String RptCnt ="";

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



}
