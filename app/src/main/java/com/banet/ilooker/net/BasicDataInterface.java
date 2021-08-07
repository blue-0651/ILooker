package com.banet.ilooker.net;



import com.banet.ilooker.common.Global;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BasicDataInterface {
	public HttpService service;

	public BasicDataInterface() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);  //상용으로 올릴때는 false로

		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(Global.HOST_ADDRESS_DEV).addConverterFactory(GsonConverterFactory.create()).client(client).build();
		service = retrofit.create(HttpService.class);
	}

	public BasicDataInterface(String url) {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);  //상용으로 올릴때는 false로

		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(client).build();
		service = retrofit.create(HttpService.class);
	}


}
