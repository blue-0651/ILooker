package com.banet.ilooker.model;

//005
public class AILookerPhoneNumber {

    public AILookerPhoneNumber(String UserPhnNo,  String BlockCategory, String BlockYN , String  BlockDateTime){
        this.UserPhnNo = UserPhnNo;
        this.BlockCategory = BlockCategory;
        this.BlockYN = BlockYN;
        this.BlockDateTime = BlockDateTime;
    }

    public  String UseLangCd = "";
    public String UserPhnNo = "";
    public String PhnNo = "";
    public String MedPartCd = "";
    public String GoodYN = "";
    public  String BlockYN = "";   //Y일시 차단번호
    public String RptTpClsCd = "";
    public String EtcTpInpDesc = "";

    public String BlockDateTime = "";
    public String BlockCategory ="" ;

}
