package com.unava.dia.commentsdownloader.di;

import com.unava.dia.commentsdownloader.model.Comment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.*;
import io.reactivex.Observable;

public interface APIInterface {
    @GET("/posts/1/comments")
    Observable<ArrayList<Comment>> getComments(@Query("id") List<Integer> filters);

}
