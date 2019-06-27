package com.unava.dia.commentsdownloader.network;

import com.unava.dia.commentsdownloader.model.Comment;

import java.util.ArrayList;

import retrofit2.http.*;
import io.reactivex.Observable;

public interface APIInterface {
    @GET("/posts/1/comments?")
    Observable<ArrayList<Comment>> getComments( @Query("id") String id);

    /*
    getComments(int offset, int limit)
    @GET("/group/{id}/users")
    List<User> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
    @QueryMap Map<String, Integer>
    <"id", 31313>
     */

}
