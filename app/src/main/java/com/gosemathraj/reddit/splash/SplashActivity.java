package com.gosemathraj.reddit.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gosemathraj.reddit.R;
import com.gosemathraj.reddit.home.PopularPostActivity;

/**
 * Created by iamsparsh on 28/3/19.
 */

public class SplashActivity extends AppCompatActivity implements SplashPresenter.SplashView{

    private SplashPresenter splashPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try{
            init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init() {
        splashPresenter = new SplashPresenter(this);
        splashPresenter.onLoad();
    }

    @Override
    public void navigateToHome() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,PopularPostActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
    }
}
