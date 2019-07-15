package com.unava.dia.commentsdownloader;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.unava.dia.commentsdownloader.di.ApplicationComponent;
import com.unava.dia.commentsdownloader.di.ContextModule;

import dagger.android.DaggerActivity;

public class App extends Application {
    private static ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //appComponent = DaggerApplicationComponent.builder()
        //        .contextModule(new ContextModule(this)).build();
        //appComponent.injectApplication(this);


        // after building
        //appComponent = DaggerApplicationComponent
        // appComponent = DaggerNetComponent
        //        .builder()
       //         .applicationModule(new ApplicationModule(getApplicationContext()))
       //         .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return appComponent;
    }

}