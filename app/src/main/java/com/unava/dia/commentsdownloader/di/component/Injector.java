package com.unava.dia.commentsdownloader.di.component;

import android.support.v7.app.AppCompatActivity;

import com.unava.dia.commentsdownloader.App;
import com.unava.dia.commentsdownloader.di.modules.ActivityModule;
import com.unava.dia.commentsdownloader.di.modules.ApplicationModule;

public class Injector {

    private static ApplicationComponent appComponent;
    private static ActivityComponent activityComponent;

    private Injector() {
    }

    public static void initializeAppComponent(App app) {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(app))
                .build();
    }

    public static void initializeAppComponent(AppCompatActivity activity) {
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(activity))
                .build();
    }

    public static ApplicationComponent getAppComponent() {
        return appComponent;
    }

    public static ActivityComponent getActivityComponent() {
        return activityComponent;
    }



}