package com.unava.dia.commentsdownloader.network;

import com.unava.dia.commentsdownloader.model.Comment;

import java.util.ArrayList;

import retrofit2.http.*;
import io.reactivex.Observable;

public interface APIInterface {
    @GET()
    Observable<ArrayList<Comment>>  getComments(@Url String url);

}
