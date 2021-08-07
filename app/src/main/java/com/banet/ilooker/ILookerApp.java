package com.banet.ilooker;

import android.app.Application;

import androidx.annotation.UiThread;

import com.banet.ilooker.common.UIThread;

public class ILookerApp extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        UIThread.initializeHandler();
    }
}
