package com.gosemathraj.reddit.comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gosemathraj.reddit.R;
import com.gosemathraj.reddit.models.comments.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iamsparsh on 29/3/19.
 */

public class CommentsActivity extends AppCompatActivity implements CommentsContract.ICommentsView{

    private String redditId = "";

    private CommentsAdapter commentsAdapter;
    private RecyclerView commentsRecyclerview;
    private ProgressBar progressBar;

    private CommentsPresenter commentsPresenter;
    private List<Comment> commentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        
        try{
            init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init() {
        getIntentData();
        findViewById();
        initVariables();
    }

    private void initVariables() {
        commentsPresenter = new CommentsPresenter(this);
        commentsPresenter.getComments(redditId);
    }

    private void findViewById() {
        progressBar = findViewById(R.id.progressbar);
        commentsRecyclerview = findViewById(R.id.comments_recyclerview);
    }

    private void getIntentData() {
        if(getIntent() != null){
            redditId = getIntent().getStringExtra("redditId");
        }
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
    public void CommentsLoaded(List<Comment> commentList) {
        commentList.addAll(commentList);
        commentsAdapter = new CommentsAdapter(this,commentList);
        commentsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        commentsRecyclerview.setAdapter(commentsAdapter);
        commentsRecyclerview.setAdapter(commentsAdapter);
    }

    @Override
    public void onError() {
        Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();
    }
}
