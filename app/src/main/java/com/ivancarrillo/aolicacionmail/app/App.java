package com.ivancarrillo.aolicacionmail.app;

import android.app.Application;

import com.ivancarrillo.aolicacionmail.models.Mail;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class App extends Application {

    public static AtomicInteger mailId = new AtomicInteger(); // Uno por cada id diferente

    public void onCreate(){

        super.onCreate();
        setUpRealmConfig();
        Realm realm = Realm.getDefaultInstance();

        mailId = getIdByTable(realm, Mail.class); // Uno por cada id diferente

        realm.close();

    }
    private void setUpRealmConfig() {

        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

    }
    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){

        RealmResults<T> results = realm.where(anyClass).findAll();

        Util.insertData();

        if (results.size()>0){
            return new AtomicInteger(results.max("id").intValue());
        }else{
            return  new AtomicInteger(0);
        }

    }

}
