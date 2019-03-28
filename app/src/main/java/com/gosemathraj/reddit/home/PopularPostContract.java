package com.gosemathraj.reddit.home;

import com.gosemathraj.reddit.models.post.PopularPostResponse;

/**
 * Created by iamsparsh on 28/3/19.
 */

public class PopularPostContract {

    public interface IPopularPostPresenter{
        void getPopularPost();
        void getMorePopularPost();
    }

    public interface IPopularPostView{
        void showProgressbar();
        void hideProgressbar();
        void popularListLoaded(PopularPostResponse popularPostResponse);
        void onError();
    }
}
