package com.banet.ilooker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class BlockedPhoneNumber extends RealmObject implements Serializable {


    public BlockedPhoneNumber() {
        Seq = "";
        RcvDate = "";
        RcvTime = "";
        PhnNo = "";
        GoodYN = "";
        BlockYN = "Y";
        RptTpClsCd = "";
        RptTpClsNm = "";
        EtcTpInpDesc = "";
        MedPartCd = "";

    }

    public String getBlockYN(){
        return this.BlockYN;
    }

    public void setBlockYN(String BlockYN){
        this.BlockYN = BlockYN;
    }

    public String getPhnNo(){
        return this.PhnNo;
    }

    public void setPhnNo(String phnNo){
        this.PhnNo = phnNo;
    }

    public String getRptTpClsNm(){
        return this.RptTpClsNm;
    }

    public void setRptTpClsNm(String RptTpClsNm){
        this.RptTpClsNm = RptTpClsNm;
    }

    public String getRcvDate(){
        return this.RcvDate;
    }

    public void setRcvDate(String date){
        this.RcvDate = date;
    }

    public String getRcvTime(){
        return this.RcvTime;
    }

    public void setRcvTime(String time){
        this.RcvTime = time;
    }



    @SerializedName("Seq")
    @Expose
    public String Seq = "";

    @SerializedName("RcvDate")
    @Expose
    public String RcvDate = "";

    @SerializedName("RcvTime")
    @Expose
    public String RcvTime = "";

    @SerializedName("PhnNo")
    @Expose
    public String PhnNo = "";

    @SerializedName("GoodYN")
    @Expose
    public String GoodYN = "";

    @SerializedName("BlockYN")
    @Expose
    public String BlockYN = "";

    @SerializedName("RptTpClsCd")
    @Expose
    public String RptTpClsCd = "";

    @SerializedName("RptTpClsNm")
    @Expose
    public String RptTpClsNm = "";
    //기타 유형 입력내용
    @SerializedName("EtcTpInpDesc")
    @Expose
    public String EtcTpInpDesc = "";

    @SerializedName("MedPartCd")
    @Expose
    public String MedPartCd = "";

}
