package com.gosemathraj.reddit.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.gosemathraj.reddit.R;
import com.gosemathraj.reddit.models.post.Child;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iamsparsh on 28/3/19.
 */

public class PopularPostAdapter extends RecyclerView.Adapter<PopularPostAdapter.PopularPostViewHolder> {
    private Context context;
    private List<Child> popularPostList = new ArrayList<>();
    private CommentAction commentAction;

    public PopularPostAdapter(Context context, List<Child> popularPostList) {
        this.context = context;
        this.popularPostList = popularPostList;
        commentAction = (CommentAction) context;
    }

    @NonNull
    @Override
    public PopularPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_popular_post,parent,false);
        return new PopularPostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularPostViewHolder holder, final int position) {
        holder.subreddit.setText("r/" + popularPostList.get(position).getData().getSubreddit() + "");
        holder.postedby.setText("posted by " + popularPostList.get(position).getData().getAuthorFullname() + "");
        holder.title.setText(popularPostList.get(position).getData().getTitle() + "");
        holder.comments.setText(popularPostList.get(position).getData().getNumComments() + " comments");
        holder.score.setText(popularPostList.get(position).getData().getScore() + "");
        if(popularPostList.get(position).getData().getThumbnail() != null && !popularPostList.get(position).getData().getThumbnail().isEmpty()
                && URLUtil.isHttpsUrl(popularPostList.get(position).getData().getThumbnail())){
            Picasso.get().load(popularPostList.get(position).getData().getThumbnail()).into(holder.imgPost);
        }else{
            holder.imgPost.setVisibility(View.GONE);
        }

        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentAction.onCommentsClicked(popularPostList.get(position).getData().getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularPostList.size();
    }

    class PopularPostViewHolder extends RecyclerView.ViewHolder{

        private TextView subreddit,postedby,title,score,comments;
        private ImageView imgPost;
        public PopularPostViewHolder(View itemView) {
            super(itemView);

            subreddit = itemView.findViewById(R.id.subreddit);
            postedby = itemView.findViewById(R.id.posted_by);
            title = itemView.findViewById(R.id.title);
            score = itemView.findViewById(R.id.score);
            comments = itemView.findViewById(R.id.comments);
            imgPost = itemView.findViewById(R.id.post_img);
        }
    }

    public interface CommentAction{
        void onCommentsClicked(String id);
    }
}
