package com.banet.ilooker.net;

import com.banet.ilooker.model.Advertise100;
import com.banet.ilooker.model.Event106;
import com.banet.ilooker.model.FAQ109;
import com.banet.ilooker.model.IncommingCall;
import com.banet.ilooker.model.MainUserInfo101;
import com.banet.ilooker.model.News107;
import com.banet.ilooker.model.Noti104;
import com.banet.ilooker.model.Point103;
import com.banet.ilooker.model.Question108;
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


    //광고
    @POST("app/100_ApiAdvertInq/")
    Call<ResponseData<Advertise100>> api100_ApiAdvertInq(@Body Map<String, Object> params);

    @POST("app/102_ApiReportHistoryInq/")
    Call<ResponseData<ReportHistory102>> api102_ApiReportHistoryInq(@Body Map<String, Object> params);

    @POST("app/103_ApiPointInq/")
    Call<ResponseData<Point103>> api103_ApiPointInq(@Body Map<String, Object> params);

    @POST("app/104_ApiGeneralNoticeInq/")
    Call<ResponseData<Noti104>> api104requestNormalNoti(@Body Map<String, Object> params);

    //104와 동일한 모델사용
    @POST("app/105_ApiIndividualNoticeInq/")
    Call<ResponseData<Noti104>> api105_ApiIndividualNoticeInq(@Body Map<String, Object> params);

    @POST("app/106_ApiEventInq/")
    Call<ResponseData<Event106>> api106requestEvent(@Body Map<String, Object> params);

    @POST("app/107_ApiNewsInq/")
    Call<ResponseData<News107>> api107_ApiNewsInq(@Body Map<String, Object> params);

    @POST("app/108_ApiQuestionInq/")
    Call<ResponseData<Question108>> api108_ApiQuestionInq(@Body Map<String, Object> params);

    @POST("app/109_ApiFAQInq/")
    Call<ResponseData<FAQ109>> api109_ApiFAQ109(@Body Map<String, Object> params);

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

    @POST("app/114_ApiQuestionDtl/")
    Call<ResponseData<Object>> api114_ApiQuestionDtl(@Body Map<String, Object> params);

    @POST("app/115_ApiQuestionDtl/")
    Call<ResponseData<Object>> api115_ApiQuestionDtl(@Body Map<String, Object> params);

    @POST("app/116_ApiAdvertCheck/")
    Call<ResponseData<Object>> api116_ApiAdvertCheck(@Body Map<String, Object> params);
     //문의등록
    @POST("app/117_ApiQuestionReg/")
    Call<ResponseData<Object>> api117_ApiQuestionReg(@Body Map<String, Object> params);
    //문의내용 변경
    @POST("app/118_ApiQuestionChg/")
    Call<ResponseData<Object>> api118_ApiQuestionChg(@Body Map<String, Object> params);

    //문의내용 삭제
    @POST("app/119_ApiQuestionDel/")
    Call<ResponseData<Object>> api119_ApiQuestionDel(@Body Map<String, Object> params);

    @POST("app/200_ApiVerChk/")
    Call<ResponseData<Object>> api200_ApiVerChk(@Body Map<String, Object> params);





}
