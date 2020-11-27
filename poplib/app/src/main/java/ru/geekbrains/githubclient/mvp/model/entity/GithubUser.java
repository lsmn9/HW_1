package ru.geekbrains.githubclient.mvp.model.entity;

import com.google.gson.annotations.Expose;

public class GithubUser {
    @Expose private String id;
    @Expose private String login;
    @Expose private String avatarUrl;
    @Expose private String reposUrl;

    public GithubUser(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }
}
