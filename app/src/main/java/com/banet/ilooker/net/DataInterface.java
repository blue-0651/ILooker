package com.banet.ilooker.net;

import android.content.Context;

import com.banet.ilooker.model.Advertise100;
import com.banet.ilooker.model.Event106;
import com.banet.ilooker.model.IncommingCall;
import com.banet.ilooker.model.MainUserInfo101;
import com.banet.ilooker.model.News107;
import com.banet.ilooker.model.Noti104;
import com.banet.ilooker.model.Point103;
import com.banet.ilooker.model.ReportHistory102;

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

	public void getapi105_ApiIndividualNoticeInq(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Noti104>> call = service.api105_ApiIndividualNoticeInq(params);

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

	public void getapi106requestEvent(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Event106>> call = service.api106requestEvent(params);

			call.enqueue(new Callback<ResponseData<Event106>>() {
				@Override
				public void onResponse(Call<ResponseData<Event106>> call, Response<ResponseData<Event106>> response) {
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
				public void onFailure(Call<ResponseData<Event106>> call, Throwable t) {
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

	public void getapi107requestNews(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<News107>> call = service.api107_ApiNewsInq(params);

			call.enqueue(new Callback<ResponseData<News107>>() {
				@Override
				public void onResponse(Call<ResponseData<News107>> call, Response<ResponseData<News107>> response) {
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
				public void onFailure(Call<ResponseData<News107>> call, Throwable t) {
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

	public void getapi102_ApiReportHistoryInq(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<ReportHistory102>> call = service.api102_ApiReportHistoryInq(params);

			call.enqueue(new Callback<ResponseData<ReportHistory102>>() {
				@Override
				public void onResponse(Call<ResponseData<ReportHistory102>> call, Response<ResponseData<ReportHistory102>> response) {
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
				public void onFailure(Call<ResponseData<ReportHistory102>> call, Throwable t) {
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

	public void get103_ApiPointInq(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Point103>> call = service.api103_ApiPointInq(params);

			call.enqueue(new Callback<ResponseData<Point103>>() {
				@Override
				public void onResponse(Call<ResponseData<Point103>> call, Response<ResponseData<Point103>> response) {
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
				public void onFailure(Call<ResponseData<Point103>> call, Throwable t) {
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


	public void getApi008_ApiUnblock(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api008_ApiUnblock(params);

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

	public void getApi009SafeNumberReg(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api009SafeNumberReg(params);

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

	public void getApi110GeneralNotice(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api110_ApiGeneralNoticeDtl(params);

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

	public void getApi112_EventDtail(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api112_ApiEventDtl(params);

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

	public void getApi113_NewsDetail(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api113_ApiNewsDtl(params);

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




}
