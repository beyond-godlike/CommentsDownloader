package com.unava.dia.commentsdownloader.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Comment {
    @SerializedName("postId")
    @Expose
    public Integer postId;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("body")
    @Expose
    public String body;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("postId", postId).append("id", id).append("name", name).append("email", email).append("body", body).toString();
    }
}
