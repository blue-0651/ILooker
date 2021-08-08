package com.banet.ilooker.common;

public class Global {

    public static String DEV_SERVER_IP = "";
    public static String DEV_SERVER_PORT = "";
    /**
     * 운영용으로 컴파일 할시에 true 로 세팅해준다. 호스트 접속페이지 처리.
     */
  //  public static final boolean RealSetting = BuildConfig.IS_REAL;

    /**
     * 접속 호스트 주소
     */
  //  public static String HOST_ADDRESS = (RealSetting ? HOST_ADDRESS_REAL : HOST_ADDRESS_DEV);

    /**
     * 접속 호스트 주소. 운영
     */
    public static final String HOST_ADDRESS_REAL = "https://";
    /**
     * 접속 호스트 주소. 개발계
     */
    public static final String HOST_ADDRESS_DEV = "http://222.112.109.110:8080" +
            "";

    public static final String COMPANY_PHONENO = "01067841226" ;
    private static Global instance;

    public static final String EXTRA_INCOMING_CALL_NUMBER = "extra_incoming_call_number" ;
    public static final String  EXTRA_INCOMING_CALL_DATA ="extra_incoming_call_data" ;
    public Global(){

    }

    public static Global instance() {
        if (instance == null) {
            instance = new Global();
        }
        return instance;
    }

    public static void destroy() {

        if(instance != null)
            instance = null;
    }


}
