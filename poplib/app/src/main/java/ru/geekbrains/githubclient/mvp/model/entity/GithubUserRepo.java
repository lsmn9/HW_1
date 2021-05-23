package ru.geekbrains.githubclient.mvp.model.entity;

import com.google.gson.annotations.Expose;


public class GithubUserRepo {
    @Expose private String id;
    @Expose private String name;
    @Expose private String fullName;
    @Expose private int forksCount;


    public GithubUserRepo (String name, int forksCount){
        this.name = name;
        this.forksCount = forksCount;
    }

    public int getForksCount(){return forksCount;}
    public String getId() {return id;}
    public String getName() {return  name;}
    public String getFullName() {return fullName;}
}
