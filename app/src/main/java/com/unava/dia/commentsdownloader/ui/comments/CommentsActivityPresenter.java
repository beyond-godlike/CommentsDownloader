package com.unava.dia.commentsdownloader.ui.comments;

import android.util.Log;

import com.unava.dia.commentsdownloader.ui.base.BaseMvpView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CommentsActivityPresenter implements CommentsMvpPresenter {
    private final CommentsMvpView view;  // CommentsActivity
    private final CommentsModel model;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CommentsActivityPresenter(CommentsMvpView view, CommentsModel model) {
        this.view = view;
        this.model = model;
    }

public void loadData(String firstComment, String lastComment) {
    List<Integer> query = makeParams(Integer.parseInt(firstComment), Integer.parseInt(lastComment));
       // Take 10 comments from the server
    final boolean call = compositeDisposable.add(
            this.model.getComments(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(resultList -> view.addComments(resultList),
                            throwable -> Log.e("TAG", String.valueOf(throwable)))

    );

}



    private List<Integer> makeParams(Integer first, Integer last) {
        List<Integer> list = new ArrayList<>();
        for (Integer i = first; i <= last; i++) {
            list.add(i);
        }

        return list;
    }

    @Override
    public void onEndScroll() {

    }

    @Override
    public void onAttach(BaseMvpView mvpView) {

    }

    @Override
    public void onDetach() {
        compositeDisposable.clear();
    }
}
