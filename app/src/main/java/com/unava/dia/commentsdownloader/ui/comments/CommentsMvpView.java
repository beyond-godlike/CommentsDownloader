package com.unava.dia.commentsdownloader.ui.comments;

import com.unava.dia.commentsdownloader.model.Comment;

import java.util.ArrayList;

public interface CommentsMvpView {
    void addComments(ArrayList<Comment> resultList);
}
