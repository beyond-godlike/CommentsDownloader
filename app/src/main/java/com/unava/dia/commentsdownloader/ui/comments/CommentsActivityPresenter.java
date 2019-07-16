package com.unava.dia.commentsdownloader.ui.comments;

import android.content.Context;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.unava.dia.commentsdownloader.di.component.Injector;
import com.unava.dia.commentsdownloader.network.APIInterface;
import com.unava.dia.commentsdownloader.model.Comment;
import com.unava.dia.commentsdownloader.ui.base.BaseMvpPresenter;
import com.unava.dia.commentsdownloader.ui.comments.CommentsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class CommentsActivityPresenter  {
    private CommentsActivity mCommentsActivity;

    @Inject
    Retrofit retrofit;

    private ArrayList<Comment> commentList = new ArrayList<>();

    public  CommentsActivityPresenter (RecyclerView rv, Context c) {
        Injector.getAppComponent().inject(this);
        //App.getActivityComponent().inject(this);
    }

    public void attachView(CommentsActivity view) {
        this.mCommentsActivity = view;
    }

    public void deatachView() {
        mCommentsActivity = null;
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
                    mCommentsActivity.addComments(commentList);

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
