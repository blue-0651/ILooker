package com.banet.ilooker.net;

import android.content.Context;

import com.banet.ilooker.model.IncommingCallSpam002;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataInterface extends BasicDataInterface {
	private static DataInterface instance;
//	String auth_str = "Bearer " + MacaronApp.chauffeur.accessToken;
protected String TAG = getClass().getSimpleName();
	public interface ResponseCallback<T> {
		void onSuccess(T response);
		void onError(T response);
		void onFailure(Throwable t);
	}

	public static DataInterface getInstance() {

		if (instance == null) {
			synchronized (DataInterface.class) {
				if (instance == null) {
					instance = new DataInterface();
				}
			}
		}

		return instance;
	}

	public static DataInterface getInstance(String url) {

		if (instance == null) {
			synchronized (DataInterface.class) {
				if (instance == null) {
					instance = new DataInterface(url);
				}
			}
		}

		return instance;
	}

	public DataInterface() {
		super();
	}

	public DataInterface(String url) {
		super(url);
	}

	public static boolean isCallSuccess(Response response) {
		return response.isSuccessful();
	}


	public void get002IncommingCallSmissing(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<IncommingCallSpam002>> call = service.api200requestIncommingCallSpam(params);

			call.enqueue(new Callback<ResponseData<IncommingCallSpam002>>() {
				@Override
				public void onResponse(Call<ResponseData<IncommingCallSpam002>> call, Response<ResponseData<IncommingCallSpam002>> response) {
					if (callback == null) return;

					if (response.isSuccessful()) {
						callback.onSuccess(response.body());
					} else {
						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
						callback.onError( response);
					}
				}

				@Override
				public void onFailure(Call<ResponseData<IncommingCallSpam002>> call, Throwable t) {
					if (callback == null) return;

					t.printStackTrace();
					callback.onFailure(t);
				}
			});
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void get001Install(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api100requestInstall(params);

			call.enqueue(new Callback<ResponseData<Object>>() {
				@Override
				public void onResponse(Call<ResponseData<Object>> call, Response<ResponseData<Object>> response) {
					if (callback == null) return;

					if (response.isSuccessful()) {
						callback.onSuccess(response.body());
					} else {
						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
						callback.onError( response);
					}
				}

				@Override
				public void onFailure(Call<ResponseData<Object>> call, Throwable t) {
					if (callback == null) return;

					t.printStackTrace();
					callback.onFailure(t);
				}
			});
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}


//
//	public void getAppVersion(Context context,  HashMap<String, Object> params,final ResponseCallback callback ){
//		try {
//			Call<kst.macaron.chauffeur.net.ResponseData<AppInfo>> call = service.getAppInfo();
//
//			call.enqueue(new Callback<kst.macaron.chauffeur.net.ResponseData<AppInfo>>() {
//				@Override
//				public void onResponse(Call<kst.macaron.chauffeur.net.ResponseData<AppInfo>> call, Response<kst.macaron.chauffeur.net.ResponseData<AppInfo>> response) {
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError(response);
//					}
//				}
//
//				@Override
//				public void onFailure(Call<kst.macaron.chauffeur.net.ResponseData<AppInfo>> call, Throwable t) {
//					if (callback == null) return;
//
//					t.printStackTrace();
//					callback.onFailure(t);
//				}
//			});
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}
//
//	public void sendChauffeurStatus(Context context, HashMap<String, Object> params, final ResponseCallback callback)
//	{
//		String str = "Bearer " + MacaronApp.chauffeur.accessToken;
//		try {
//			Call<kst.macaron.chauffeur.net.ResponseData<Object>> call = service.sendChauffeurStatus( params, str );
//
//			call.enqueue(new kst.macaron.chauffeur.net.RetryableCallback<kst.macaron.chauffeur.net.ResponseData<Object>>(call, context) {
//				@Override
//				public void onFinalResponse(Call<kst.macaron.chauffeur.net.ResponseData<Object>> call, Response<kst.macaron.chauffeur.net.ResponseData<Object>> response) {
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//				//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError(response);
//					}
//				}
//
//				@Override
//				public void onFinalFailure(Call<kst.macaron.chauffeur.net.ResponseData<Object>> call, Throwable t) {
//					if (callback == null) return;
//
//					t.printStackTrace();
//					callback.onFailure(t);
//				}
//			});
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}
//    /*
//    * 배차상태 변경 api
//     */
//	public void changeAllocationStatus(Context context, HashMap<String, Object> params, final ResponseCallback callback)
//	{
//		String str = "Bearer " + MacaronApp.chauffeur.accessToken;
//		try {
//			Call<kst.macaron.chauffeur.net.ResponseData<Object>> call = service.changeAllocStatus(params, str);
//
//			call.enqueue(new kst.macaron.chauffeur.net.RetryableCallback<kst.macaron.chauffeur.net.ResponseData<Object>>(call, context) {
//				@Override
//				public void onFinalResponse(Call<kst.macaron.chauffeur.net.ResponseData<Object>> call, Response<kst.macaron.chauffeur.net.ResponseData<Object>> response) {
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {   //response return true code() is in the range 200-300.
//						callback.onSuccess(response.body());
//					} else {
//						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError(response);
//					}
//				}
//
//				@Override
//				public void onFinalFailure(Call<kst.macaron.chauffeur.net.ResponseData<Object>> call, Throwable t) {
//					if (callback == null) return;
//
//					t.printStackTrace();
//					callback.onFailure(t);
//				}
//			});
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}

//    public void getChauffeurInfo(Context context, HashMap<String, Object> params, final ResponseCallback callback){
//		try{
//			Call<ResponseData<ChauffeurInfo>> call = service.getChauffeur(params);
//
//			call.enqueue(new Callback<ResponseData<ChauffeurInfo>>() {
//				@Override
//				public void onResponse(Call<ResponseData<ChauffeurInfo>> call, Response<ResponseData<ChauffeurInfo>> response) {
//
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError(response);
//					}
//				}
//
//				@Override
//				public void onFailure(Call<ResponseData<ChauffeurInfo>> call, Throwable t) {
//					if (callback == null) return;
//
//					t.printStackTrace();
//					callback.onFailure(t);
//				}
//			});
//		}catch (Exception ex){
//			ex.printStackTrace();
//		}
//	}

//	public void informCarAccident(Context context, HashMap<String, Object> params, final ResponseCallback callback)
//	{
//		String str = "Bearer " + MacaronApp.chauffeur.accessToken;
//		try {
//			Call<kst.macaron.chauffeur.net.ResponseData<Object>> call = service.informCarAccident( params, str);
//
//			call.enqueue(new kst.macaron.chauffeur.net.RetryableCallback<kst.macaron.chauffeur.net.ResponseData<Object>>(call, context) {
//				@Override
//				public void onFinalResponse(Call<kst.macaron.chauffeur.net.ResponseData<Object>> call, Response<kst.macaron.chauffeur.net.ResponseData<Object>> response) {
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError(response);
//					}
//				}
//
//				@Override
//				public void onFinalFailure(Call<kst.macaron.chauffeur.net.ResponseData<Object>> call, Throwable t) {
//					if (callback == null) return;
//
//					t.printStackTrace();
//					callback.onFailure(t);
//				}
//			});
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}
//
//	public void receiveAllocSchedule(Context context, HashMap<String, Object> params, final ResponseCallback callback){
//		try{
//			String str = "Bearer " + MacaronApp.chauffeur.accessToken;
//			Call<kst.macaron.chauffeur.net.ResponseData<AllocationSchedule>> call = service.getAllocSchedule(params, str);
//
//			call.enqueue(new Callback<kst.macaron.chauffeur.net.ResponseData<AllocationSchedule>>() {
//				@Override
//				public void onResponse(Call<kst.macaron.chauffeur.net.ResponseData<AllocationSchedule>> call, Response<kst.macaron.chauffeur.net.ResponseData<AllocationSchedule>> response) {
//
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError(response.body());
//					}
//				}
//
//				@Override
//				public void onFailure(Call<kst.macaron.chauffeur.net.ResponseData<AllocationSchedule>> call, Throwable t) {
//					if (callback == null) return;
//
//					t.printStackTrace();
//					callback.onFailure(t);
//				}
//			});
//		}catch (Exception ex){
//			ex.printStackTrace();
//		}
//	}
//
//	public void getAllocDetail(Context context, HashMap<String, Object> params, final ResponseCallback callback){
//		try{
//			String str = "Bearer " + MacaronApp.chauffeur.accessToken;
//			Call<kst.macaron.chauffeur.net.ResponseData<AllocationSchedule>> call = service.getAllocDetail(params, str);
//
//			call.enqueue(new Callback<kst.macaron.chauffeur.net.ResponseData<AllocationSchedule>>() {
//				@Override
//				public void onResponse(Call<kst.macaron.chauffeur.net.ResponseData<AllocationSchedule>> call, Response<kst.macaron.chauffeur.net.ResponseData<AllocationSchedule>> response) {
//
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError(response);
//					}
//				}
//
//				@Override
//				public void onFailure(Call<kst.macaron.chauffeur.net.ResponseData<AllocationSchedule>> call, Throwable t) {
//					if (callback == null) return;
//
//					t.printStackTrace();
//					callback.onFailure(t);
//				}
//			});
//		}catch (Exception ex){
//			ex.printStackTrace();
//		}
//	}
//
//	public void login(Context context, HashMap<String, Object> params, final ResponseCallback callback){
//		try{
//			Call<kst.macaron.chauffeur.net.ResponseData<ChauffeurInfo>> call = service.login(params);
//
//			call.enqueue(new Callback<kst.macaron.chauffeur.net.ResponseData<ChauffeurInfo>>() {
//				@Override
//				public void onResponse(Call<kst.macaron.chauffeur.net.ResponseData<ChauffeurInfo>> call, Response<kst.macaron.chauffeur.net.ResponseData<ChauffeurInfo>> response) {
//
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError(response);
//					}
//				}
//
//				@Override
//				public void onFailure(Call<kst.macaron.chauffeur.net.ResponseData<ChauffeurInfo>> call, Throwable t) {
//					if (callback == null) return;
//
//					t.printStackTrace();
//					callback.onFailure(t);
//				}
//			});
//		}catch (Exception ex){
//			ex.printStackTrace();
//		}
//	}
//
//	public void sendLocationInfo(String id, String valid, double lat, double lon, String timestamp, double hdop,
//								 double altitude, double speed, double heading , double accuracy, double batt,
//								 final ResponseCallback callback){
//		try{
//			Call<kst.macaron.chauffeur.net.ResponseData<Object>> call = service.sendLocationInfo(id, valid, lat, lon, timestamp, hdop, altitude, speed, heading, accuracy, batt);
//
//			call.enqueue(new Callback<kst.macaron.chauffeur.net.ResponseData<Object>>() {
//				@Override
//				public void onResponse(Call<kst.macaron.chauffeur.net.ResponseData<Object>> call, Response<kst.macaron.chauffeur.net.ResponseData<Object>> response) {
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//						Logger.i(TAG,"error getUserInfo = " + response.errorBody().toString());
//						callback.onError(response);
//					}
//				}
//
//				@Override
//				public void onFailure(Call<kst.macaron.chauffeur.net.ResponseData<Object>> call, Throwable t) {
//					if (callback == null) return;
//
//					t.printStackTrace();
//					callback.onFailure(t);
//				}
//			});
//
//		}catch (Exception ex){
//			ex.printStackTrace();
//		}
//	}

}
