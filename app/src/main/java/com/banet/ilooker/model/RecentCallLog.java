package com.banet.ilooker.model;

import java.io.Serializable;

public class RecentCallLog implements Serializable {
    public RecentCallLog(String phoneNumber, String mediaType, String callType, String cachedName , String date, String time, String smsContent){
        this.phoneNumber = phoneNumber;
        this.mediaType = mediaType ;
        this.callType = callType;
        this.cachedName = cachedName;
        this.date = date;
        this.time = time;
        this.smsContent = smsContent;
    }

    public String phoneNumber;
    public String mediaType  ; //001, 002
    public String callType   ;//outgoing, incoming
    public String cachedName ;
    public String date;
    public String time;
    public String smsContent;

}
