package com.unava.dia.commentsdownloader.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unava.dia.commentsdownloader.R;
import com.unava.dia.commentsdownloader.model.Comment;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    ArrayList<Comment> commentArrayList;

    public CommentAdapter(ArrayList<Comment> l) {
        commentArrayList = new ArrayList<>();
        this.commentArrayList = l;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_model, parent, false);


        //TODO
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CommentViewHolder holder, int position) {
        holder.allInfo.setText(commentArrayList.get(position).email);
    }


    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }

}
