package com.banet.ilooker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BlockedPhoneNumber extends RealmObject implements Parcelable {


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

    protected BlockedPhoneNumber(Parcel in) {
        Seq = in.readString();
        RcvDate = in.readString();
        RcvTime = in.readString();
        PhnNo = in.readString();
        GoodYN = in.readString();
        BlockYN = in.readString();
        RptTpClsCd = in.readString();
        RptTpClsNm = in.readString();
        EtcTpInpDesc = in.readString();
        MedPartCd = in.readString();
    }

    public static final Creator<BlockedPhoneNumber> CREATOR = new Creator<BlockedPhoneNumber>() {
        @Override
        public BlockedPhoneNumber createFromParcel(Parcel in) {
            return new BlockedPhoneNumber(in);
        }

        @Override
        public BlockedPhoneNumber[] newArray(int size) {
            return new BlockedPhoneNumber[size];
        }
    };

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

    @PrimaryKey
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Seq);
        dest.writeString(RcvDate);
        dest.writeString(RcvTime);
        dest.writeString(PhnNo);
        dest.writeString(GoodYN);
        dest.writeString(BlockYN);
        dest.writeString(RptTpClsCd);
        dest.writeString(RptTpClsNm);
        dest.writeString(EtcTpInpDesc);
        dest.writeString(MedPartCd);
    }
}
