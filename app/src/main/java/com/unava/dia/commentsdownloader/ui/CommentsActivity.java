package com.unava.dia.commentsdownloader.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.unava.dia.commentsdownloader.App;
import com.unava.dia.commentsdownloader.R;
import com.unava.dia.commentsdownloader.network.NetManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

import javax.inject.Inject;

public class CommentsActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    // робить запити до сервера
    NetManager nm;

    String firstComment;
    String lastComment;

    @BindView(R.id.commentsRecyclerView)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);

        ((App) getApplication()).getNetComponent().inject(this);

        initData();
    }

    private void initData() {
        // нет менеджер зробить запит та запхає результат в ресайклерв’ю
        nm = new NetManager(retrofit, getApplicationContext(), rv);

        firstComment = this.getIntent().getStringExtra("FIRST_COMMENT");
        lastComment = this.getIntent().getStringExtra("LAST_COMMENT");

        // юзер може не ввести дiапазон коментарiв, тому ми їх перевiремо
        if(firstComment.isEmpty()) firstComment = "1";
        if(lastComment.isEmpty()) lastComment = "10";

        String url = makeParams(Integer.parseInt(firstComment), Integer.parseInt(lastComment));

        prepeareRecyclerView(); // add the listener

        // Take first 10 comments from the server
        nm.getSomeComments(url);
    }

    // лiснер, що детектить кiнець прокрутки ресайклерв’ю
    private void prepeareRecyclerView() {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                // 1 - direction up
                // -1 - down
                if (!recyclerView.canScrollVertically(1)) {
                    // LOAD NEW 10 COMMENTS
                    Integer f = Integer.parseInt(lastComment) + 1;
                    Integer l = Integer.parseInt(lastComment) + 10;

                    String url = makeParams(f, l);

                    lastComment = l.toString();
                    firstComment = f.toString();

                    // Take more 10 comments from the server
                    nm.getSomeComments(url);
                }
            }
        });
    }
//робить с дiапазону iнтежерiв url запит до сервера
    private String makeParams(Integer first, Integer last) {
        String params = "/posts/1/comments?";
        for (Integer i = first; i <= last; i++) {
            params += "id=" + i.toString() + "&";
        }
        params = params.substring(0, params.length() - 1);

        return params;
    }
}
