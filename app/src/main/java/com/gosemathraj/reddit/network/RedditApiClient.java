package com.gosemathraj.reddit.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iamsparsh on 28/3/19.
 */

public class RedditApiClient {

    private static final String BASE_URL = "https://www.reddit.com/";

    private static Retrofit retrofit = null;

    public static Retrofit getRedditApiClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
