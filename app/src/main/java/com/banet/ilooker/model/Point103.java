package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//포인트
public class Point103 implements Serializable {
     //순번
    @SerializedName("Seq")
    @Expose
    public String Seq ="";

    @SerializedName("CurrPnt")
    @Expose
    public String CurrPnt ="";

    //포인트가감일자
    @SerializedName("PntAdjDate")
    @Expose
    public String PntAdjDate ="";

    @SerializedName("PntAdjRsnNm")
    @Expose
    public String PntAdjRsnNm ="";

    @SerializedName("Pnt")
    @Expose
    public String Pnt ="";

    @SerializedName("PntAdjRsnDescPath")
    @Expose
    public String PntAdjRsnDescPath  ="";
}
