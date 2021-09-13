package com.banet.ilooker.net;

import com.banet.ilooker.model.Advertise100;
import com.banet.ilooker.model.Event106;
import com.banet.ilooker.model.IncommingCall;
import com.banet.ilooker.model.MainUserInfo101;
import com.banet.ilooker.model.Noti104;
import com.banet.ilooker.model.ReportHistory102;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface HttpService {


    @POST("app/001_ApiInstall/")
    Call<ResponseData<Object>> api001requestInstall(@Body Map<String, Object> params);

    @POST("app/101_ApiMainPageInq/")
    Call<ResponseData<MainUserInfo101>> api101UserInfo(@Body Map<String, Object> params);

    @POST("app/002_ApiIncomingPhnCall/")
    Call<ResponseData<IncommingCall>> api200requestIncommingCallSpam(@Body Map<String, Object> params);
    //수신문자 스팸여부
    @POST("app/003_ApiReceiveTxtMsg/")
    Call<ResponseData<IncommingCall>> api003requestIncommingSMS(@Body Map<String, Object> params);

    @POST("app/004_ApiReceiveUrlMsg/")
    Call<ResponseData<Object>> api400requestUrlSMS(@Body Map<String, Object> params);

    //신고등록(전화)
    @POST("app/005_ApiReportRegPhn/")
    Call<ResponseData<Object>> api005requestReportPhoneNo(@Body Map<String, Object> params);

    //신고등록(일반문자)
    @POST("app/006_ApiReportRegTxtMsg/")
    Call<ResponseData<Object>> api006requestReportPhoneNo(@Body Map<String, Object> params);

    //신고등록(url)
    @POST("app/007_ApiReportRegUrlMsg/")
    Call<ResponseData<Object>> api007requestReportPhoneNo(@Body Map<String, Object> params);

    @POST("app/104_ApiGeneralNoticeInq/")
    Call<ResponseData<Noti104>> api104requestNormalNoti(@Body Map<String, Object> params);

    @POST("app/106_ApiEventInq/")
    Call<ResponseData<Event106>> api106requestEvent(@Body Map<String, Object> params);

    //광고
    @POST("app/100_ApiAdvertInq/")
    Call<ResponseData<Advertise100>> api100_ApiAdvertInq(@Body Map<String, Object> params);

    @POST("app/102_ApiReportHistoryInq/")
    Call<ResponseData<ReportHistory102>> api102_ApiReportHistoryInq(@Body Map<String, Object> params);


    @POST("app/008_ApiUnblock/")
    Call<ResponseData<Object>>  api008_ApiUnblock(@Body Map<String, Object> params);

    @POST("app/009_ApiSafeNumberReg/")
    Call<ResponseData<Object>> api009SafeNumberReg(@Body Map<String, Object> params);

    //일반공지 상세 내역
    @POST("app/110_ApiGeneralNoticeDtl/")
    Call<ResponseData<Object>> api110_ApiGeneralNoticeDtl(@Body Map<String, Object> params);

    //개인공지 상세 내역
    @POST("app/111_ApiIndividualNoticeDtl/")
    Call<ResponseData<Object>> api111_ApiIndividualNoticeDtl(@Body Map<String, Object> params);

    //이벤트내역확인
    @POST("app/112_ApiEventDtl/")
    Call<ResponseData<Object>> api112_ApiEventDtl(@Body Map<String, Object> params);

    @POST("app/113_ApiNewsDtl/")
    Call<ResponseData<Object>> api113_ApiNewsDtl(@Body Map<String, Object> params);



}
