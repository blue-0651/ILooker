package com.banet.ilooker.net;

import com.banet.ilooker.model.IncommingCallSpam002;
import com.banet.ilooker.model.IncommingSMS003;
import com.banet.ilooker.model.Noti104;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface HttpService {


    @POST("app/001_ApiInstall/")
    Call<ResponseData<Object>> api100requestInstall(@Body Map<String, Object> params);

    @POST("app/002_ApiIncomingPhnCall/")
    Call<ResponseData<IncommingCallSpam002>> api200requestIncommingCallSpam(@Body Map<String, Object> params);

    @POST("app/ApiReceiveTxtMsg/")
    Call<ResponseData<IncommingSMS003>> api300requestIncommingSMS(@Body Map<String, Object> params);

    @POST("app/ApiReceiveUrlMsg/")
    Call<ResponseData<Object>> api400requestUrlSMS(@Body Map<String, Object> params);

    //신고전화
    @POST("app/ApiReportRegPhn/")
    Call<ResponseData<Object>> api500requestReportPhoneNo(@Body Map<String, Object> params);

    @POST("app/104_ApiGeneralNoticeInq/")
    Call<ResponseData<Noti104>> api104requestNormalNoti(@Body Map<String, Object> params);

    @POST("app/005_ApiReportRegPhn/")
    Call<ResponseData<Object>> api005requestRegPhonNo(@Body Map<String, Object> params);



}
