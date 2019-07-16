package com.unava.dia.commentsdownloader.ui.comments;

import com.unava.dia.commentsdownloader.ui.base.BaseMvpPresenter;

public interface CommentsMvpPresenter <V extends CommentsMvpView> extends BaseMvpPresenter {
    void onEndScroll();
}
