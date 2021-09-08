package com.banet.ilooker;

import android.app.Application;

import com.banet.ilooker.common.UIThread;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ILookerApp extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        UIThread.initializeHandler();

        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder().name("ai-looker.realm").build();
        Realm.setDefaultConfiguration(config);


    }
}
