package com.unava.dia.commentsdownloader.ui.comments;

import com.unava.dia.commentsdownloader.data.CommentsRepository;
import com.unava.dia.commentsdownloader.model.Comment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * @author Bogdan Ustyak (bogdan.ustyak@gmail.com)
 */
public class CommentsModel {

    private final CommentsRepository repository;

    public CommentsModel(CommentsRepository repository) {
        this.repository = repository;
    }

    public Single<ArrayList<Comment>> getComments(List<Integer> url) {
        return this.repository.getComments(url);
    }
}
