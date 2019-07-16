package com.unava.dia.commentsdownloader.di.component;

import com.unava.dia.commentsdownloader.di.PerActivity;
import com.unava.dia.commentsdownloader.di.modules.ActivityModule;
import com.unava.dia.commentsdownloader.ui.comments.CommentsActivityPresenter;
import com.unava.dia.commentsdownloader.ui.comments.CommentsActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(CommentsActivity activity);

    void inject(CommentsActivityPresenter presenter);
}
