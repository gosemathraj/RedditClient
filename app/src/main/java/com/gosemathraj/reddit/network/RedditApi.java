package com.gosemathraj.reddit.network;

import com.gosemathraj.reddit.models.post.PopularPostResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by iamsparsh on 28/3/19.
 */

public interface RedditApi {

    // By default the limit is 25
    public static final String POSTS_LIMIT = "limit";

    @GET("r/popular.json")
    Call<PopularPostResponse> getPopularPosts(@Query(POSTS_LIMIT) int limit);

    @GET("comments/{redditId}.json")
    Call<ResponseBody> getComments(@Path("redditId") String redditId,@Query(POSTS_LIMIT) int limit);
}
