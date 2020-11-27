package ru.geekbrains.githubclient;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.geekbrains.githubclient.mvp.model.api.IDataSource;
import ru.geekbrains.githubclient.mvp.model.api.IRepoSource;

public class ApiHolder {
    private IDataSource dataSource;
    private IRepoSource repoSource;

    ApiHolder() {
        Gson gson = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        dataSource = retrofit.create(IDataSource.class);
        repoSource = retrofit.create(IRepoSource.class);
    }


    public IDataSource getDataSource() {
        return dataSource;
    }

    public IRepoSource getRepoSource() { return repoSource; }
}
