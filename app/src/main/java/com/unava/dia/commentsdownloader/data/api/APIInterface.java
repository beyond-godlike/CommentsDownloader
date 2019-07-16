package com.unava.dia.commentsdownloader.data.api;

import com.unava.dia.commentsdownloader.model.Comment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.*;

public interface APIInterface {
    @GET("/posts/1/comments")
    Single<ArrayList<Comment>> getComments(@Query("id") List<Integer> filters);

}
