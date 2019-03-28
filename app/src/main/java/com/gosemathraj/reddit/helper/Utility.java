package com.gosemathraj.reddit.helper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.gosemathraj.reddit.models.comments.Comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by iamsparsh on 29/3/19.
 */

public class Utility {

    private static final String DATA = "data";
    private static final String CHILDREN = "children";
    private static final String REPLIES = "replies";
    private static final String KIND = "kind";
    public static final String T1 = "t1";
    public static final String MORE = "more";

    public static List<Comment> parseComments(ResponseBody responseBody) {
        Gson gson = new Gson();
        List<Comment> redditCommentsResponses = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        JsonArray entries = null;
        try {
            entries = (JsonArray) jsonParser.parse(responseBody.string());
            JsonArray jsonArray = entries.get(1).getAsJsonObject().get(DATA)
                    .getAsJsonObject().get(CHILDREN).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    if (jsonObject.get(DATA) != null) {
                        Comment commentsWithType = gson.fromJson(jsonObject.get(DATA),
                                new TypeToken<Comment>() {
                                }.getType());
                        if (jsonObject.get(KIND) != null)
                            commentsWithType.setKind(jsonObject.get(KIND).getAsString());

                        redditCommentsResponses.add(commentsWithType);

                        List<Comment> subCommentsList =
                                getSubComments(jsonObject.get(DATA).getAsJsonObject(), gson);
                        redditCommentsResponses.addAll(subCommentsList);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return redditCommentsResponses;
    }


    private static List<Comment> getSubComments(JsonObject jsonObject, Gson gson) {

        List<Comment> innerCommentsList = new ArrayList<>();

        if (jsonObject.has(REPLIES)) {
            JsonElement repliesElement = jsonObject.get(REPLIES);
            if (repliesElement != null && repliesElement.isJsonObject()) {
                JsonObject repliesObject = repliesElement.getAsJsonObject();

                if (repliesObject.get(DATA) != null && repliesObject.get(DATA).isJsonObject()) {

                    JsonObject repliesDataObject = repliesObject.get(DATA).getAsJsonObject();
                    if (repliesDataObject.get(CHILDREN) != null && repliesDataObject.get(
                            CHILDREN).isJsonArray()) {
                        JsonArray repliesDataArray =
                                repliesObject.get(DATA).getAsJsonObject().get(
                                        CHILDREN).getAsJsonArray();

                        for (JsonElement jsonElement : repliesDataArray) {
                            JsonObject innerObj = jsonElement.getAsJsonObject();

                            if (innerObj.get(DATA) != null) {
                                JsonElement dataElement = innerObj.get(DATA);
                                Comment commentsWithType = gson.fromJson(dataElement,
                                        new TypeToken<Comment>() {
                                        }.getType());
                                if (innerObj.get(KIND) != null)
                                    commentsWithType.setKind(innerObj.get(KIND).getAsString());

                                innerCommentsList.add(commentsWithType);

                                if (dataElement.isJsonObject()) {
                                    innerCommentsList.addAll(getSubComments(innerObj.get(DATA).getAsJsonObject(), gson));
                                }
                            }
                        }
                    }
                }
            }

        }
        return innerCommentsList;
    }
}
