package com.unava.dia.commentsdownloader.di;

import android.content.Context;

import com.unava.dia.commentsdownloader.App;
import com.unava.dia.commentsdownloader.presenter.CommentsActivityPresenter;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Component;
import dagger.Subcomponent;

//@Singleton
//@Subcomponent(modules = {ApplicationModule.class})
@ApplicationScope
@Singleton
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {
    APIInterface getApiInterface();
    @ApplicationContext
    Context getContext();
    void injectApplication(CommentsActivityPresenter presenter);

}