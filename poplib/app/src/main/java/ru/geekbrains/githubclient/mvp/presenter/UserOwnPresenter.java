package ru.geekbrains.githubclient.mvp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo;
import ru.geekbrains.githubclient.mvp.model.repo.IRepoRepo;
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitRepoRepo;
import ru.geekbrains.githubclient.mvp.presenter.list.IUserOwnListPresenter;
import ru.geekbrains.githubclient.mvp.view.UserOwnItemView;
import ru.geekbrains.githubclient.mvp.view.UserOwnView;


public class UserOwnPresenter extends MvpPresenter<UserOwnView> {

    private final IRepoRepo repoRepo;
    private final Scheduler scheduler;
    private String login = UsersPresenter.getChosen();
    private String path = "/users/" + login + "/repos";

    public UserOwnPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
        this.repoRepo = new RetrofitRepoRepo(GithubApplication.INSTANCE.getRepoApi(), path);
        System.out.println(GithubApplication.INSTANCE.getRepoApi());
    }

    private class UserOwnListPresenter implements IUserOwnListPresenter {

        private List<GithubUserRepo> userRepos = new ArrayList<>();


        @Override
        public void onItemClick(UserOwnItemView view) {

        }

        @Override
        public void bindView(UserOwnItemView view) {
            GithubUserRepo userRepo = userRepos.get(view.getPos());
            view.setName(userRepo.getName());
        }

        @Override
        public int getCount() {
            return userRepos.size();
        }
    }

    private UserOwnListPresenter usersOwnListPresenter = new UserOwnListPresenter();

    public UserOwnListPresenter getUserOwnListPresenter() {
        return usersOwnListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadRepo();
        System.out.println(path);
    }

    private void loadRepo() {
        repoRepo.getUsersRepo().observeOn(scheduler).
                subscribe(
                        r -> {
                            usersOwnListPresenter.userRepos.clear();
                            usersOwnListPresenter.userRepos.addAll(r);
                            getViewState().updateList();
                        }, (e) -> {
                            Log.w("!!!!!!!", "Error " + e.getMessage());
                        });
    }

    public String getLogin() {
        return login;
    }


    public boolean backPressed() {
        // на будущее
        return true;

    }
}
