package com.banet.ilooker.common;

public class AppDef {

    public final static int ACTIVITY_CLOSE = 9000;

    public static final String FRAGMENT_TITLE_NAME = "fragment_title";
    public static final String incoming_number_extra = "incoming_number_extra";
    public static final String incoming_date_time = "called_date_time";

    //각 프래그먼트화면 이를을 정의한다. 번들에서 사용
    public static final String title_main_fragment = "메인" ;
    public static final String title_block_phone_number_fragment = "신고내역" ;
    public static final String title_block_and_report_phone_number_fragment = "신고/차단" ;
    public static final String title_block_noti_fragment = "공지사항" ;


    public enum ReportBlockCategory {
        Loan, Gamble, Adult, PhoneSales, Insurance, Alter_Driving, InternetSales, Stock
        ,Delivery, Advertisement, Goverment, Bank, Telemarketing, VoicePissing, Call, Sensus,
        UsedProductFake, Smissing, Micellaneous
    }

}
