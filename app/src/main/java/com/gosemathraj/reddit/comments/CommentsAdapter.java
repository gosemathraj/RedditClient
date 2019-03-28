package com.gosemathraj.reddit.comments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gosemathraj.reddit.R;
import com.gosemathraj.reddit.models.comments.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iamsparsh on 29/3/19.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>{

    private Context context;
    private List<Comment> commentsList = new ArrayList<>();

    public CommentsAdapter(Context context, List<Comment> commentsList) {
        this.context = context;
        this.commentsList = commentsList;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_comment,parent,false);
        return new CommentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(commentsList.get(position).getDepth() * (int)context.getResources().getDimension(R.dimen.left_padding), 0, 0, 0);
        holder.parentContainer.setLayoutParams(params);

        if(commentsList.get(position).getKind().equalsIgnoreCase("T1")){
            holder.body.setText(commentsList.get(position).getBody());
            holder.author.setText(commentsList.get(position).getAuthor());
        }else{
            holder.body.setText("Load " + commentsList.get(position).getCount() + " Comments");
            holder.author.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder{

        private TextView author,body;
        private LinearLayout parentContainer;
        public CommentsViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            body = itemView.findViewById(R.id.body);
            parentContainer = itemView.findViewById(R.id.parent_container);
        }
    }
}
