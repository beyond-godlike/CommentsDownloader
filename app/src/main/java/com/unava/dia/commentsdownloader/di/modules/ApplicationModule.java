package com.unava.dia.commentsdownloader.di.modules;

import android.app.Application;
import android.content.Context;

import com.unava.dia.commentsdownloader.BuildConfig;
import com.unava.dia.commentsdownloader.di.ApplicationContext;
import com.unava.dia.commentsdownloader.network.APIInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private final static String baseUrl = BuildConfig.SERVER_URL;
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

    @Provides
    APIInterface getApiInterface(Retrofit retrofit) {
        return retrofit.create(APIInterface.class);
    }

    @Provides
    @Singleton
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}