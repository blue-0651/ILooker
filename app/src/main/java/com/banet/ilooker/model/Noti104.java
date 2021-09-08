package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Noti104 implements Serializable {
    @SerializedName("Seq")
    @Expose
    public String Seq ="";

    @SerializedName("NtcNo")
    @Expose
    public String NtcNo ="";

    @SerializedName("NtcStaDate")
    @Expose
    public String NtcStaDate ="";

    @SerializedName("NtcEndDate")
    @Expose
    public String NtcEndDate ="";

    @SerializedName("NtcTpNm")
    @Expose
    public String NtcTpNm ="";

    @SerializedName("NtcTitl")
    @Expose
    public String NtcTitl ="";

    @SerializedName("NtcDescPath")
    @Expose
    public String NtcDescPath  ="";


}
