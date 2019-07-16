package com.unava.dia.commentsdownloader.ui.comments;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.unava.dia.commentsdownloader.R;
import com.unava.dia.commentsdownloader.data.CommentsRepository;
import com.unava.dia.commentsdownloader.data.api.APIInterface;
import com.unava.dia.commentsdownloader.di.component.Injector;
import com.unava.dia.commentsdownloader.di.modules.NetworkModule;
import com.unava.dia.commentsdownloader.model.Comment;
import com.unava.dia.commentsdownloader.ui.adapters.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;

public class CommentsActivity extends AppCompatActivity implements CommentsMvpView{

    private CommentsActivityPresenter presenter;
    private CommentAdapter adapter;

    private String firstComment;
    private String lastComment;

    @Inject
    APIInterface apiInterface;


    @BindView(R.id.commentsRecyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);

        Injector.getAppComponent().inject(this);

        init();
    }

    private void init() {
        CommentsModel model = new CommentsModel(new CommentsRepository(apiInterface));

        presenter = new CommentsActivityPresenter(this, model);

        firstComment = this.getIntent().getStringExtra("FIRST_COMMENT");
        lastComment = this.getIntent().getStringExtra("LAST_COMMENT");

        if(firstComment.isEmpty()) firstComment = "1";
        if(lastComment.isEmpty()) lastComment = "10";

        prepeareRecyclerView();

        presenter.loadData(firstComment, lastComment);

        // init views
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false);
        adapter = new CommentAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    private void prepeareRecyclerView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                // 1 - direction up
                // -1 - down
                if (!recyclerView.canScrollVertically(1)) {
                    // LOAD NEW 10 COMMENTS
                    Integer f = Integer.parseInt(lastComment) + 1;
                    Integer l = Integer.parseInt(lastComment) + 10;

                    lastComment = l.toString();
                    firstComment = f.toString();

                    presenter.loadData(firstComment, lastComment);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void addComments(ArrayList<Comment> resultList) {
        adapter.insertComments(resultList);
    }

    @Override
    public void showError(String message) {

    }
}
