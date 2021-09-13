package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class Event106 implements Serializable {
    @SerializedName("Seq")
    @Expose
    public String Seq ="";

    @SerializedName("EvtNo")
    @Expose
    public String EvtNo ="";

    @SerializedName("EvtStaDate")
    @Expose
    public String EvtStaDate ="";

    @SerializedName("EvtEndDate")
    @Expose
    public String EvtEndDate ="";

    @SerializedName("EvtKindNm")
    @Expose
    public String EvtKindNm ="";

    @SerializedName("EvtTitl")
    @Expose
    public String EvtTitl ="";

    @SerializedName("EvtDescPath")
    @Expose
    public String EvtDescPath  ="";


}

