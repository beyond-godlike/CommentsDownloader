package com.unava.dia.commentsdownloader.network;

import com.unava.dia.commentsdownloader.ui.CommentsActivity;

import dagger.Component;

@Component(modules = {NetModule.class})
public interface NetComponent {
    void inject(CommentsActivity commentActivity);

}