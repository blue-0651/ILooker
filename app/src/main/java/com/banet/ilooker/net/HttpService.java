package com.banet.ilooker.net;

import com.banet.ilooker.model.IncommingCallSpam002;
import com.banet.ilooker.model.IncommingSMS003;
import com.banet.ilooker.model.Noti104;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface HttpService {


    @POST("ApiInstall/")
    Call<ResponseData<Object>> api100requestInstall(@Body Map<String, Object> params);

    @POST("app/002_ApiIncomingPhnCall/")
    Call<ResponseData<IncommingCallSpam002>> api200requestIncommingCallSpam(@Body Map<String, Object> params);

    @GET("app/002_ApiIncomingPhnCallView/?UseLangCd=KOR&UserPhnNo=01011111111&PhnNo=01022222222&MedPartCd=003")
    Call<ResponseData<IncommingCallSpam002>> api200requestIncommingCallSpam();

    @POST("ApiReceiveTxtMsg/")
    Call<ResponseData<IncommingSMS003>> api300requestIncommingSMS(@Body Map<String, Object> params);

    @POST("ApiReceiveUrlMsg/")
    Call<ResponseData<Object>> api400requestUrlSMS(@Body Map<String, Object> params);

    //신고전화
    @POST("ApiReportRegPhn/")
    Call<ResponseData<Object>> api500requestUrlSMS(@Body Map<String, Object> params);

    @GET("app/104_ApiGeneralNoticeInq/?UseLangCd=KOR&UserPhnNo=01012340005")
    Call<ResponseData<Noti104>> api104requestNormalNoti();



}
