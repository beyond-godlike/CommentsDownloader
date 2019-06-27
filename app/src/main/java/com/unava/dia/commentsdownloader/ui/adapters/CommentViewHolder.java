package com.unava.dia.commentsdownloader.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.unava.dia.commentsdownloader.R;

public class CommentViewHolder extends RecyclerView.ViewHolder  {
    TextView allInfo;

    public CommentViewHolder(View itemView) {
        super(itemView);

        allInfo = (TextView) itemView.findViewById(R.id.cardTextView);

    }
}
