package com.unava.dia.commentsdownloader.di.modules;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.unava.dia.commentsdownloader.di.ActivityContext;
import com.unava.dia.commentsdownloader.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return activity;
    }

}