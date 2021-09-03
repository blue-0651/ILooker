package com.banet.ilooker.net;

import com.banet.ilooker.model.IncommingCall;
import com.banet.ilooker.model.MainUserInfo101;
import com.banet.ilooker.model.Noti104;
import com.banet.ilooker.model.TxtMsg003;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface HttpService {


    @POST("app/001_ApiInstall/")
    Call<ResponseData<Object>> api100requestInstall(@Body Map<String, Object> params);
    @POST("app/101_ApiMainPageInq/")
    Call<ResponseData<MainUserInfo101>> api101UserInfo(@Body Map<String, Object> params);

    @POST("app/002_ApiIncomingPhnCall/")
    Call<ResponseData<IncommingCall>> api200requestIncommingCallSpam(@Body Map<String, Object> params);

    @POST("app/ApiReceiveTxtMsg/")
    Call<ResponseData<TxtMsg003>> api300requestIncommingSMS(@Body Map<String, Object> params);

    @POST("app/ApiReceiveUrlMsg/")
    Call<ResponseData<Object>> api400requestUrlSMS(@Body Map<String, Object> params);

    //신고전화
    @POST("app/ApiReportRegPhn/")
    Call<ResponseData<Object>> api500requestReportPhoneNo(@Body Map<String, Object> params);

    @POST("app/104_ApiGeneralNoticeInq/")
    Call<ResponseData<Noti104>> api104requestNormalNoti(@Body Map<String, Object> params);

    @POST("app/005_ApiReportRegPhn/")
    Call<ResponseData<Object>> api005requestRegPhonNo(@Body Map<String, Object> params);

    @POST("app/006_ApiReportRegTxtMsg/")
    Call<ResponseData<Object>> api005sendTxtMsg(@Body Map<String, Object> params);



}
