package com.unava.dia.commentsdownloader.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.unava.dia.commentsdownloader.R;
import com.unava.dia.commentsdownloader.ui.comments.CommentsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editTextFrom)
    EditText firstComment;
    @BindView(R.id.editTextTo)
    EditText lastComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void onButtonGoClick(View view) {
        Intent intent = new Intent(getApplicationContext(), CommentsActivity.class);
        intent.putExtra("FIRST_COMMENT", firstComment.getText().toString());
        intent.putExtra("LAST_COMMENT", lastComment.getText().toString());
        startActivity(intent);
    }
}
