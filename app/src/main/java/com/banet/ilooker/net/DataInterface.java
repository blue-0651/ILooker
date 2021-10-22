package com.banet.ilooker.net;

import android.content.Context;

import com.banet.ilooker.model.Advertise100;
import com.banet.ilooker.model.AppVersion200;
import com.banet.ilooker.model.Event106;
import com.banet.ilooker.model.FAQ109;
import com.banet.ilooker.model.IncommingCall;
import com.banet.ilooker.model.MainUserInfo101;
import com.banet.ilooker.model.News107;
import com.banet.ilooker.model.Noti104;
import com.banet.ilooker.model.Point103;
import com.banet.ilooker.model.Question108;
import com.banet.ilooker.model.ReportHistory102;
import com.banet.ilooker.model.SMSUrlMsg004;

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

	public void get003IncommingTxtSMS(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
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

	public void get004IncommingUrlSMS(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<SMSUrlMsg004>> call = service.api004requestUrlSMS(params);

			call.enqueue(new Callback<ResponseData<SMSUrlMsg004>>() {
				@Override
				public void onResponse(Call<ResponseData<SMSUrlMsg004>> call, Response<ResponseData<SMSUrlMsg004>> response) {
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
				public void onFailure(Call<ResponseData<SMSUrlMsg004>> call, Throwable t) {
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

	public void getapi108requestQuestion(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Question108>> call = service.api108_ApiQuestionInq(params);

			call.enqueue(new Callback<ResponseData<Question108>>() {
				@Override
				public void onResponse(Call<ResponseData<Question108>> call, Response<ResponseData<Question108>> response) {
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
				public void onFailure(Call<ResponseData<Question108>> call, Throwable t) {
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

	public void getapi109requestFAQ(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<FAQ109>> call = service.api109_ApiFAQ109(params);

			call.enqueue(new Callback<ResponseData<FAQ109>>() {
				@Override
				public void onResponse(Call<ResponseData<FAQ109>> call, Response<ResponseData<FAQ109>> response) {
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
				public void onFailure(Call<ResponseData<FAQ109>> call, Throwable t) {
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

	public void getapi006requestReportSMSTxtMsg(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api006requestReportPhoneNo(params);

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

	public void getapi007requestReportSMSUrlMsg(Context context,  HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api007requestReportPhoneNo(params);

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

	public void getApi112_EventDetail(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
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

	public void getApi114_questionDetail(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api114_ApiQuestionDtl(params);

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

	public void getApi115_FAQDetail(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api115_ApiFAQDtl(params);

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

	public void getApi116_AdverCheck(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api116_ApiAdvertCheck(params);

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


	public void getApi117_QuestionRegister(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api117_ApiQuestionReg(params);

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

	public void getApi118_QuestionUpdate(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api118_ApiQuestionChg(params);

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

	public void getApi119_QuestionDelete(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<Object>> call = service.api119_ApiQuestionDel(params);

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

	public void getApi200_Appver(Context context, HashMap<String, Object> params, final ResponseCallback callback ){
		try {
			Call<ResponseData<AppVersion200>> call = service.api200_ApiVerChk(params);

			call.enqueue(new Callback<ResponseData<AppVersion200>>() {
				@Override
				public void onResponse(Call<ResponseData<AppVersion200>> call, Response<ResponseData<AppVersion200>> response) {
					if (callback == null) return;

					if (response.isSuccessful()) {
						callback.onSuccess(response.body());
					} else {
						//		Logger.log(Logger.LogState.E, "error getUserInfo = " + response.errorBody().toString());
						callback.onError( response);
					}
				}

				@Override
				public void onFailure(Call<ResponseData<AppVersion200>> call, Throwable t) {
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
