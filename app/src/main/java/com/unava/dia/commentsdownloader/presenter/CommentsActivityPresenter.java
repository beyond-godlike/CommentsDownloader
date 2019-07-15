package com.unava.dia.commentsdownloader.presenter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.unava.dia.commentsdownloader.App;
import com.unava.dia.commentsdownloader.di.APIInterface;
import com.unava.dia.commentsdownloader.model.Comment;
import com.unava.dia.commentsdownloader.model.CommentsActivityView;
import com.unava.dia.commentsdownloader.ui.CommentsActivity;
import com.unava.dia.commentsdownloader.ui.adapters.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class CommentsActivityPresenter <V extends CommentsActivityView> {
    private CommentsActivity view;

    @Inject
    Retrofit retrofit;
    @Inject
    Context c;

    private RecyclerView rv;
    private ArrayList<Comment> commentList = new ArrayList<>();

    public  CommentsActivityPresenter (RecyclerView rv, Context c) {
        //App.getNetComponent().inject(this);
        //App.getActivityComponent().inject(this);
        this.c = c;
        this.rv = rv;
    }

    public void attachView(CommentsActivity view) {
        this.view = view;
    }

    public void deatachView() {
        view = null;
    }

    public void loadData(String firstComment, String lastComment) {
        List<Integer> query = makeParams(Integer.parseInt(firstComment), Integer.parseInt(lastComment));
        // Take 10 comments from the server
        getComments(query);
    }

    protected void getComments(List<Integer> url) {
        Observable<ArrayList<Comment>> call = retrofit.create(APIInterface.class).getComments(url);

        DisposableObserver<ArrayList<Comment>> observer = new DisposableObserver<ArrayList<Comment>>() {

            @Override
            public void onNext(ArrayList<Comment> resultList) {
                // prevent memory leaks
                if(resultList.isEmpty()) dispose();

                // must fix null pointer exception while calling adapter.getItemCount()
                commentList.addAll(resultList);
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

    private List<Integer> makeParams(Integer first, Integer last) {
        List<Integer> list = new ArrayList<>();
        for (Integer i = first; i <= last; i++) {
            list.add(i);
        }

        return list;
    }
}
