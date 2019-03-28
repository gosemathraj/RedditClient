package com.gosemathraj.reddit.splash;

/**
 * Created by iamsparsh on 28/3/19.
 */

public class SplashPresenter {

    private SplashView splashView;

    public SplashPresenter(SplashView splashView) {
        this.splashView = splashView;
    }

    public void onLoad(){
        splashView.navigateToHome();
    }
    interface SplashView{
        void navigateToHome();
    }
}
