package com.unava.dia.commentsdownloader.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unava.dia.commentsdownloader.App;
import com.unava.dia.commentsdownloader.R;
import com.unava.dia.commentsdownloader.model.Comment;
import com.unava.dia.commentsdownloader.network.APIInterface;
import com.unava.dia.commentsdownloader.ui.adapters.CommentAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import javax.inject.Inject;

public class CommentsActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    ArrayList<Comment> commentList;

    String firstComment = "1";
    String lastComment = "10";

    @BindView(R.id.commentsRecyclerView)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);

        ((App) getApplication()).getNetComponent().inject(this);

        firstComment = this.getIntent().getStringExtra("FIRST_COMMENT");
        lastComment = this.getIntent().getStringExtra("LAST_COMMENT");

        getSomeComments(firstComment);
    }

    private void getSomeComments( String id) {
        Observable<ArrayList<Comment>> call = retrofit.create(APIInterface.class).getComments(id);

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
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL ,false);
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
