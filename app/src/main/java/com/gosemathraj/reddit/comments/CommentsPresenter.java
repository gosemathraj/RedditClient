package com.gosemathraj.reddit.comments;

import com.gosemathraj.reddit.helper.Constants;
import com.gosemathraj.reddit.helper.Utility;
import com.gosemathraj.reddit.home.PopularPostContract;
import com.gosemathraj.reddit.models.post.PopularPostResponse;
import com.gosemathraj.reddit.network.RedditApi;
import com.gosemathraj.reddit.network.RedditApiClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iamsparsh on 29/3/19.
 */

public class CommentsPresenter implements CommentsContract.ICommentsPresenter{

    private CommentsContract.ICommentsView iCommentsView;

    public CommentsPresenter(CommentsContract.ICommentsView iCommentsView) {
        this.iCommentsView = iCommentsView;
    }

    @Override
    public void getComments(final String id) {
        iCommentsView.showProgressbar();
        RedditApi redditApi = RedditApiClient.getRedditApiClient().create(RedditApi.class);
        Call<ResponseBody> commentsList = redditApi.getComments(id,Constants.POSTS_LIMIT);
        commentsList.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                iCommentsView.CommentsLoaded(Utility.parseComments(response.body()));
                iCommentsView.hideProgressbar();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                iCommentsView.hideProgressbar();
                iCommentsView.onError();
            }
        });
    }
}
