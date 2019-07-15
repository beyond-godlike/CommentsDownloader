package com.unava.dia.commentsdownloader.di;

import android.content.Context;
import com.unava.dia.commentsdownloader.ui.CommentsActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private CommentsActivity activity;
    private Context context;

    public ContextModule(CommentsActivity activity) {
        this.activity = activity;
        context = activity;
    }

    @Provides
    @ActivityScope
    public CommentsActivity providesCommentsActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}