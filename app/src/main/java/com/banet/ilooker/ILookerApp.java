package com.banet.ilooker;

import android.app.Application;

import com.banet.ilooker.common.UIThread;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ILookerApp extends Application {
    public ILookerApp() {
        super(); // 다른 생성자가 있는 경우 super() 호출 포함
    }
    @Override
    public void onCreate() {

        super.onCreate();
        UIThread.initializeHandler();

        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("ai-looker.realm")
                .deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(config);



    }
}
