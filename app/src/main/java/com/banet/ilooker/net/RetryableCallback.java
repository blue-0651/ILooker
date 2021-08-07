package com.banet.ilooker.net;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class RetryableCallback<T> implements Callback<T> {
    private final Call<T> call;
    private Context context;

    public RetryableCallback(Call<T> call, Context context) {
        this.call = call;
        this.context = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!DataInterface.isCallSuccess(response) && (context instanceof Activity))
        {
            onFinalResponse(call,response);
           // retryPopup();
        }
        else onFinalResponse(call,response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (context instanceof Activity) //retryPopup();
        onFinalFailure(call, t);
    }

    public void onFinalResponse(Call<T> call, Response<T> response) {

    }

    public void onFinalFailure(Call<T> call, Throwable t) {
    }

    private void retry() {
        call.clone().enqueue(this);
    }

//    private void retryPopup()
//    {
//        try {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//            dialog.setTitle(context.getString(R.string.sdk_name)).setMessage("데이터 요청에 실패하였습니다.\n사용중인 네트워크 상태를 확인해 주세요.\n다시 요청하시겠습니까?").setCancelable(false).setPositiveButton("예", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    retry();
//                }
//            }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            }).create().setCanceledOnTouchOutside(false);
//
//            if(context != null && !((Activity)context).isFinishing()) dialog.show();
//        }
//        catch (Exception ex)
//        {
//
//        }
//    }
}
