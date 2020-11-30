package ru.geekbrains.githubclient.mvp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo;
import ru.geekbrains.githubclient.mvp.model.repo.IRepoRepo;
import ru.geekbrains.githubclient.mvp.presenter.list.IUserOwnListPresenter;
import ru.geekbrains.githubclient.mvp.view.lists.UserOwnItemView;
import ru.geekbrains.githubclient.mvp.view.UserOwnView;
import ru.geekbrains.githubclient.navigation.Screens;
import ru.terrakok.cicerone.Router;


public class UserOwnPresenter extends MvpPresenter<UserOwnView> {

    private Router router;
    private final IRepoRepo repoRepo;
    private final Scheduler scheduler;
    private  String  login = UsersPresenter.getChosen();
    private static String repoName;
    private static int forksCount;
    private String path = "/users/" + login + "/repos";

    public UserOwnPresenter(Scheduler scheduler, IRepoRepo repoRepo, Router router) {
        this.scheduler = scheduler;
        this.repoRepo = repoRepo;
        this.router = router;
    }

    private class UserOwnListPresenter implements IUserOwnListPresenter {

        private List<GithubUserRepo> userRepos = new ArrayList<>();


        @Override
        public void onItemClick(UserOwnItemView view) {
            GithubUserRepo userRepo = userRepos.get(view.getPos());
            repoName = userRepo.getName();
            forksCount = userRepo.getForksCount();
            router.navigateTo(new Screens.UserRepoScreen());
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
        repoRepo.getUsersRepo(path).observeOn(scheduler).
                subscribe(
                        r -> {
                            usersOwnListPresenter.userRepos.clear();
                            usersOwnListPresenter.userRepos.addAll(r);
                            getViewState().updateList();
                        }, (e) -> {
                            Log.w("!!!!!!!", "Error " + e.getMessage());
                        });
    }

    public  String getLogin() {
        return login;
    }

    public static String getRepoName(){return repoName;}

    public static int getForksCount(){return forksCount;}


    public boolean backPressed() {
        // на будущее
        return true;

    }
}
