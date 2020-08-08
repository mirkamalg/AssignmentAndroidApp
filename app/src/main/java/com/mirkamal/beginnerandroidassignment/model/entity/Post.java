package com.mirkamal.beginnerandroidassignment.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class Post {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "ownerid")
    private String ownerID;

    @ColumnInfo(name = "comments")
    private Boolean comments;

    public Post(String id, String description, String ownerID, Boolean comments) {
        this.id = id;
        this.description = description;
        this.ownerID = ownerID;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getComments() {
        return comments;
    }

    public String getOwnerID() {
        return ownerID;
    }
}
