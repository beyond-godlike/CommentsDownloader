package com.unava.dia.commentsdownloader.network;

import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.unava.dia.commentsdownloader.model.Comment;
import com.unava.dia.commentsdownloader.ui.adapters.CommentAdapter;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class NetManager {

    Retrofit retrofit;
    Context c;
    RecyclerView rv;
    ArrayList<Comment> commentList;

    public NetManager(Retrofit retrofit, Context c, RecyclerView rv) {
        this.retrofit = retrofit;
        this.c = c;
        this.rv = rv;
    }

    public void getSomeComments(String url) {
        Observable<ArrayList<Comment>> call = retrofit.create(APIInterface.class).getComments(url);

        Observer<ArrayList<Comment>> observer = new Observer<ArrayList<Comment>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ArrayList<Comment> resultList) {
                commentList = resultList;
            }

            @Override
            public void onError(Throwable e) {
                Log.d("CALL", e.getMessage());
            }

            @Override
            public void onComplete() {
                try {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(c, LinearLayoutManager.VERTICAL ,false);
                    CommentAdapter adapter = new CommentAdapter(commentList);
                    rv.setLayoutManager(layoutManager);
                    rv.setAdapter(adapter);
                }
                catch (Exception e) {
                    Log.d("CALL", e.getMessage());
                }
            }
        };


        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
