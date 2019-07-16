package com.unava.dia.commentsdownloader.data;

import com.unava.dia.commentsdownloader.model.Comment;
import com.unava.dia.commentsdownloader.data.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * @author Bogdan Ustyak (bogdan.ustyak@gmail.com)
 */
public class CommentsRepository {

    private final APIInterface apiInterface;

    public CommentsRepository(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Single<ArrayList<Comment>> getComments(List<Integer> url) {
        return this.apiInterface.getComments(url);
    }
}
