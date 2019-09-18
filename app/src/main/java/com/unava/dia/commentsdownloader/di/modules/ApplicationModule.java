package com.unava.dia.commentsdownloader.di.modules;

import android.app.Application;
import android.content.Context;

import com.unava.dia.commentsdownloader.data.api.APIInterface;
import com.unava.dia.commentsdownloader.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApplicationModule {
    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }


    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }


    @Provides
    Application provideApplication() {
        return mApplication;
    }
}