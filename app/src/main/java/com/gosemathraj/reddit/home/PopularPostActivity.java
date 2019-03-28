package com.gosemathraj.reddit.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gosemathraj.reddit.R;
import com.gosemathraj.reddit.comments.CommentsActivity;
import com.gosemathraj.reddit.models.post.Child;
import com.gosemathraj.reddit.models.post.PopularPostResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iamsparsh on 28/3/19.
 */

public class PopularPostActivity extends AppCompatActivity implements PopularPostContract.IPopularPostView,
        PopularPostAdapter.CommentAction{

    private PopularPostPresenter popularPostPresenter = null;
    private PopularPostAdapter popularPostAdapter;

    private List<Child> popularPostList = new ArrayList<>();

    private RecyclerView popularPostRecyclerview;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popularpost);
        
        try{
            init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init() {
        findViewById();
        initVariables();
    }

    private void initVariables() {
        popularPostPresenter = new PopularPostPresenter(this);
        popularPostAdapter = new PopularPostAdapter(this,popularPostList);
        popularPostRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        popularPostRecyclerview.setAdapter(popularPostAdapter);

        popularPostPresenter.getPopularPost();
    }

    private void findViewById() {
        progressBar = findViewById(R.id.progress_bar);
        popularPostRecyclerview = findViewById(R.id.popular_post_recyclerview);
    }

    @Override
    public void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void popularListLoaded(PopularPostResponse popularPostResponse) {
        if(popularPostResponse != null){
            popularPostList.addAll(popularPostResponse.getData().getChildren());
            popularPostAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError() {
        Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCommentsClicked(String id) {
        Intent intent = new Intent(this,CommentsActivity.class);
        intent.putExtra("redditId",id);
        startActivity(intent);
    }
}
