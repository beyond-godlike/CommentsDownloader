package com.unava.dia.commentsdownloader.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.unava.dia.commentsdownloader.R;

public class CommentViewHolder extends RecyclerView.ViewHolder  {
    TextView postId;
    TextView id;
    TextView name;
    TextView email;
    TextView body;


    public CommentViewHolder(View itemView) {
        super(itemView);

        postId = (TextView) itemView.findViewById(R.id.postId);
        id = (TextView) itemView.findViewById(R.id.id);
        name  = (TextView) itemView.findViewById(R.id.name);
        email = (TextView) itemView.findViewById(R.id.email);
        body = (TextView) itemView.findViewById(R.id.body);
    }
}
