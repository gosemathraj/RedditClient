package com.gosemathraj.reddit.comments;

import com.gosemathraj.reddit.models.comments.Comment;
import com.gosemathraj.reddit.models.post.PopularPostResponse;

import java.util.List;

/**
 * Created by iamsparsh on 29/3/19.
 */

public class CommentsContract {

    public interface ICommentsPresenter{
        void getComments(String id);
    }

    public interface ICommentsView{
        void showProgressbar();
        void hideProgressbar();
        void CommentsLoaded(List<Comment> commentList);
        void onError();
    }
}
