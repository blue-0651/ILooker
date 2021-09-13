package com.banet.ilooker.common;

public class AppDef {

    public final static int ACTIVITY_CLOSE = 9000;

    public static String[] AILookerMediaType = new String[]{
            "001", "002"
    };

    public static final String FRAGMENT_TITLE_NAME = "fragment_title";
    public static final String incoming_number_extra = "incoming_number_extra";
    public static final String incoming_date_time = "called_date_time";
    public static final String action_phone_state_changed = "action_phone_state_changed";
    public static final String phone_state = "phone_state";
    public static final String MOVE_TO_FRAGMENT = "move_to_fragment";
    public static final String MOVE_TO_BLOCK_PHONE_NUMBER = "move_to_block_phone_number";
    public static final String USER_LANGUAGE_CODE = "KOR";
    public static final String NOTICE_104 = "notice_104";
    public static final String EVENT_106 = "event_106";

    public static final String RecentCallLog_Extra = "recent_call_log_extra";
    public static final String BlockedPhoneNumber_Extra = "blocked_phone_number_extra";



    //각 프래그먼트화면 이를을 정의한다. 번들에서 사용
    public static final String title_main_fragment = "메인" ;
    public static final String title_block_phone_number_history_fragment = "신고내역" ;
    public static final String title_block_and_report_phone_number_fragment = "신고/차단" ;
    public static final String title_noti_fragment = "공지사항" ;
    public static final String title_block_fragment = "차단관리" ;
    public static final String title_event_fragment = "이벤트" ;

    public static final String titl_latest_call_log_fragment = "최근기록" ;
    public static final String title_latest_call_log_detail = "최근기록상세" ;
    public static final String title_blocked_phone_number_detail = "차단내역상세" ;


    public enum ReportBlockCategory {
        Loan, Gamble, Adult, PhoneSales, Insurance, Alter_Driving, InternetSales, Stock
        ,Delivery, Advertisement, Goverment, Bank, Telemarketing, VoicePissing, Call, Sensus,
        UsedProductFake, Smissing, Micellaneous
    }


    public enum PhoneCallStatus{
        RINGING  , IDLE, OFFHOOK, SMS
    }

    public static enum MediaTypeEnum {
        PhoneCall, SMS
    }



}
