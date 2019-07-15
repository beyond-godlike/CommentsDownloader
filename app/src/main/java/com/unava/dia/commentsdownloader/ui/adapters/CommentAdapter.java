package com.unava.dia.commentsdownloader.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unava.dia.commentsdownloader.R;
import com.unava.dia.commentsdownloader.model.Comment;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private ArrayList<Comment> commentArrayList;

    public CommentAdapter(ArrayList<Comment> l) {
        this.commentArrayList = l;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_model, parent, false);

        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CommentViewHolder holder, int position) {
        holder.postId.setText(String.format("post id: %s", commentArrayList.get(position).getPostId().toString()));
        holder.id.setText(String.format("id: %s", commentArrayList.get(position).getId().toString()));
        holder.name.setText(String.format("name: %s", commentArrayList.get(position).getName()));
        holder.email.setText(String.format("email: %s", commentArrayList.get(position).getEmail()));
        holder.body.setText(String.format("body: %s", commentArrayList.get(position).getBody()));
    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }


    static class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView postId;
        private TextView id;
        private TextView name;
        private TextView email;
        private TextView body;

        public CommentViewHolder(View itemView) {
            super(itemView);

            postId = (TextView) itemView.findViewById(R.id.postId);
            id = (TextView) itemView.findViewById(R.id.id);
            name  = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            body = (TextView) itemView.findViewById(R.id.body);
        }

    }

}
