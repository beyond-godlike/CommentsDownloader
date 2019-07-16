package com.unava.dia.commentsdownloader;

import android.app.Application;
import android.content.Context;

import com.unava.dia.commentsdownloader.di.component.DaggerApplicationComponent;
import com.unava.dia.commentsdownloader.di.component.Injector;
import com.unava.dia.commentsdownloader.di.component.ApplicationComponent;
import com.unava.dia.commentsdownloader.di.modules.ApplicationModule;

public class App extends Application {
    private static ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.initializeAppComponent(this);
        Injector.getAppComponent().inject(this);


        // after building
        appComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return appComponent;
    }

}