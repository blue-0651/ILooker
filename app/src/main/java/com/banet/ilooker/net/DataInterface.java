package com.banet.ilooker.net;

import android.content.Context;

import com.banet.ilooker.model.Advertise100;
import com.banet.ilooker.model.IncommingCall;
import com.banet.ilooker.model.MainUserInfo101;
import com.banet.ilooker.model.Noti104;

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

	private void processCommonError(Context context, ResponseCallback callback, Response response) {
		processCommonError(context, callback, response, true);
	}

	private void processCommonError(Context context, ResponseCallback callback, Response response, boolean isCommonError) {
		if (callback == null) {
			return;
		}

		ResponseData data = (ResponseData)response.body();

		if (response.isSuccessful()) {
			if (data != null) {
			//	if (isCommonError && !data.getProcRsltCd().equals("S000")) {
				//	showDialog(context, null, data.getError());
			//	}
				callback.onSuccess(data);
			} else {
				callback.onError(null);
			}
		} else {
			if(isCommonError) {
				if (data != null) {
				//	showDialog(context, null, data.getError());
				} else {
				//	showDialog(context, null, "네트웍상태를 확인해주세요.");
				}
			} else {
				callback.onError(null);
			}
		}
	}


	public void get002IncommingCallSmissing(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<IncommingCall>> call = service.api200requestIncommingCallSpam(params);

			call.enqueue(new Callback<ResponseData<IncommingCall>>() {
				@Override
				public void onResponse(Call<ResponseData<IncommingCall>> call, Response<ResponseData<IncommingCall>> response) {
					processCommonError(context, callback, response);
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError( response);
//					}
				}

				@Override
				public void onFailure(Call<ResponseData<IncommingCall>> call, Throwable t) {
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

	public void get003IncommingSMS(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<IncommingCall>> call = service.api003requestIncommingSMS(params);

			call.enqueue(new Callback<ResponseData<IncommingCall>>() {
				@Override
				public void onResponse(Call<ResponseData<IncommingCall>> call, Response<ResponseData<IncommingCall>> response) {
					processCommonError(context, callback, response);
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError( response);
//					}
				}

				@Override
				public void onFailure(Call<ResponseData<IncommingCall>> call, Throwable t) {
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

	public void getapi104requestNormalNoti(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Noti104>> call = service.api104requestNormalNoti(params);

			call.enqueue(new Callback<ResponseData<Noti104>>() {
				@Override
				public void onResponse(Call<ResponseData<Noti104>> call, Response<ResponseData<Noti104>> response) {
					processCommonError(context, callback, response);
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError( response);
//					}
				}

				@Override
				public void onFailure(Call<ResponseData<Noti104>> call, Throwable t) {
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

	public void getapi005requestReportPhonNo(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api005requestReportPhoneNo(params);

			call.enqueue(new Callback<ResponseData<Object>>() {
				@Override
				public void onResponse(Call<ResponseData<Object>> call, Response<ResponseData<Object>> response) {
					processCommonError(context, callback, response);
//					if (callback == null) return;
//
//					if (response.isSuccessful()) {
//						callback.onSuccess(response.body());
//					} else {
//						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
//						callback.onError( response);
//					}
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


	public void get001Install(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api001requestInstall(params);

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

	public void get101UserInfo(HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<MainUserInfo101>> call = service.api101UserInfo(params);

			call.enqueue(new Callback<ResponseData<MainUserInfo101>>() {
				@Override
				public void onResponse(Call<ResponseData<MainUserInfo101>> call, Response<ResponseData<MainUserInfo101>> response) {
					if (callback == null) return;

					if (response.isSuccessful()) {
						callback.onSuccess(response.body());
					} else {
						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
						callback.onError( response);
					}
				}

				@Override
				public void onFailure(Call<ResponseData<MainUserInfo101>> call, Throwable t) {
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

	public void get100Advertise(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Advertise100>> call = service.api100_ApiAdvertInq(params);

			call.enqueue(new Callback<ResponseData<Advertise100>>() {
				@Override
				public void onResponse(Call<ResponseData<Advertise100>> call, Response<ResponseData<Advertise100>> response) {
					if (callback == null) return;

					if (response.isSuccessful()) {
						callback.onSuccess(response.body());
					} else {
						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
						callback.onError( response);
					}
				}

				@Override
				public void onFailure(Call<ResponseData<Advertise100>> call, Throwable t) {
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
