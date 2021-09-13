package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question108 implements Serializable {
    //순번
    @SerializedName("Seq")
    @Expose
    public String Seq ="";


    @SerializedName("InqNo")
    @Expose
    public String InqNo ="";


    @SerializedName("InqDate")
    @Expose
    public String InqDate ="";

    @SerializedName("Email")
    @Expose
    public String Email ="";

    @SerializedName("InqTitl")
    @Expose
    public String InqTitl ="";

    @SerializedName("InqDesc")
    @Expose
    public String InqDesc  ="";

    @SerializedName("AnsDate")
    @Expose
    public String AnsDate  ="";

    @SerializedName("AnsStatNm")
    @Expose
    public String AnsStatNm  ="";
}
