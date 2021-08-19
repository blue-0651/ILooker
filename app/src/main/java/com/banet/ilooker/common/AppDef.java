package com.banet.ilooker.common;

public class AppDef {

    public final static int ACTIVITY_CLOSE = 9000;

    /*
    * LEAVE:퇴근, WORK:근무대기, ALLOC:배차, LOAD:승차(고객), REST:휴식, CONNECT:접속, RETIRE:차고지이동
    *  WORK2: 출발지이동
     */
    public enum ChauffeurStatus {

        LEAVE, WORK, REST, LOAD, RETIRE, CONNECT, ALLOC, ACCIDENT

    }

    public enum CarStatus{
        NORMAL, ACCIDENT
    }
    public static final String FRAGMENT_TITLE_NAME = "fragment_title";
    //각 프래그먼트화면 이를을 정의한다. 번들에서 사용
    public static final String title_main_fragment = "메인" ;
    public static final String title_block_phone_number_fragment = "신고내역" ;




}
