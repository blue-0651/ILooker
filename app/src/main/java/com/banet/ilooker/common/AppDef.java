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



}
