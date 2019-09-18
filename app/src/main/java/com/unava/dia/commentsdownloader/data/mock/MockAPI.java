package com.unava.dia.commentsdownloader.data.mock;

import com.unava.dia.commentsdownloader.data.api.APIInterface;
import com.unava.dia.commentsdownloader.model.Comment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;

/**
 * @author Bogdan Ustyak (bogdan.ustyak@gmail.com)
 */
public class MockAPI implements APIInterface {
    @Override
    public Single<ArrayList<Comment>> getComments(List<Integer> filters) {

        return new Single<ArrayList<Comment>>() {
            @Override
            protected void subscribeActual(SingleObserver<? super ArrayList<Comment>> observer) {

            }
        };
    }
}
