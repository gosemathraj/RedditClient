package com.gosemathraj.reddit.home;

import com.gosemathraj.reddit.helper.Constants;
import com.gosemathraj.reddit.models.post.PopularPostResponse;
import com.gosemathraj.reddit.network.RedditApi;
import com.gosemathraj.reddit.network.RedditApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iamsparsh on 28/3/19.
 */

public class PopularPostPresenter implements PopularPostContract.IPopularPostPresenter {

    private PopularPostContract.IPopularPostView iPopularPostView;

    public PopularPostPresenter(PopularPostContract.IPopularPostView iPopularPostView) {
        this.iPopularPostView = iPopularPostView;
    }

    @Override
    public void getPopularPost() {
        iPopularPostView.showProgressbar();
        RedditApi redditApi = RedditApiClient.getRedditApiClient().create(RedditApi.class);
        Call<PopularPostResponse> popularPostList = redditApi.getPopularPosts(Constants.POSTS_LIMIT);
        popularPostList.enqueue(new Callback<PopularPostResponse>() {
            @Override
            public void onResponse(Call<PopularPostResponse> call, Response<PopularPostResponse> response) {
                iPopularPostView.hideProgressbar();
                iPopularPostView.popularListLoaded(response.body());
            }

            @Override
            public void onFailure(Call<PopularPostResponse> call, Throwable t) {
                iPopularPostView.hideProgressbar();
                iPopularPostView.onError();
            }
        });
    }

    @Override
    public void getMorePopularPost() {
    }
}
