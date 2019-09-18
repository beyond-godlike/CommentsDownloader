package com.unava.dia.commentsdownloader.di.modules;

import com.unava.dia.commentsdownloader.BuildConfig;
import com.unava.dia.commentsdownloader.data.api.APIInterface;
import com.unava.dia.commentsdownloader.data.mock.MockAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Bogdan Ustyak (bogdan.ustyak@gmail.com)
 */
@Module
public class NetworkModule {
    private final static String baseUrl = BuildConfig.SERVER_URL;

    @Provides
    @Singleton
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    APIInterface providesRestAPI(Retrofit retrofit) {
        return retrofit.create(APIInterface.class);
        //return new MockAPI();
    }
}
