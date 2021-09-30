package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppVersion200 extends MenuListObject{

    @SerializedName("CurrVer")
    @Expose
    public String currVer = "";


    @Override
    public void setMenuListField() {

    }
}
