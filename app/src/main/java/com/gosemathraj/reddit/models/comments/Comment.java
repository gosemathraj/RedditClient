package com.gosemathraj.reddit.models.comments;

/**
 * Created by iamsparsh on 29/3/19.
 */

public class Comment {

    private String kind;
    private String author;
    private String body;
    private int count;
    private int depth;

    public Comment(String kind, String author, String body, int count, int depth) {
        this.kind = kind;
        this.author = author;
        this.body = body;
        this.count = count;
        this.depth = depth;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
