package com.unava.dia.commentsdownloader.ui.comments;

import com.unava.dia.commentsdownloader.model.Comment;
import com.unava.dia.commentsdownloader.ui.base.BaseMvpView;

import java.util.ArrayList;

public interface CommentsMvpView extends BaseMvpView {
    void addComments(ArrayList<Comment> resultList);

    void showError(String message);
}
