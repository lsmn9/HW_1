package ru.geekbrains.githubclient.mvp.model.entity;

import com.google.gson.annotations.Expose;


public class GithubUserRepo {
    @Expose private String id;
    @Expose private String name;
    @Expose private String fullName;

    public GithubUserRepo (String name){this.name = name;}

    public String getId() {return id;}
    public String getName() {return  name;}
    public String getFullName() {return fullName;}
}
