package com.example.datasiswa;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DbConfig extends Application {
    public void onCreate (){
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("siswa  db")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
