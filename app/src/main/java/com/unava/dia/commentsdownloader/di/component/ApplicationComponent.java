package com.unava.dia.commentsdownloader.di.component;

import android.app.Application;
import android.content.Context;

import com.unava.dia.commentsdownloader.App;
import com.unava.dia.commentsdownloader.di.ApplicationContext;
import com.unava.dia.commentsdownloader.di.modules.ApplicationModule;
import com.unava.dia.commentsdownloader.di.ApplicationScope;
import com.unava.dia.commentsdownloader.ui.comments.CommentsActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@ApplicationScope
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);
    void inject(CommentsActivityPresenter app);

    @ApplicationContext
    Context context();

    Application application();

    Retrofit getRetrofit();
}