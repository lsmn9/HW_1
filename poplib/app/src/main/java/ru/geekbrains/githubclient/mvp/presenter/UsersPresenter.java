package ru.geekbrains.githubclient.mvp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.geekbrains.githubclient.GithubApplication;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;
import ru.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;
import ru.geekbrains.githubclient.mvp.presenter.list.IUserListPresenter;
import ru.geekbrains.githubclient.mvp.view.UserItemView;
import ru.geekbrains.githubclient.mvp.view.UsersView;
import ru.geekbrains.githubclient.navigation.Screens;
import ru.terrakok.cicerone.Router;

public class UsersPresenter extends MvpPresenter<UsersView> {
    private static final String TAG = UsersPresenter.class.getSimpleName();

    private static final boolean VERBOSE = true;
    private static String chosen;
    private Router router = GithubApplication.getApplication().getRouter();

    private final IGithubUsersRepo usersRepo;
    private final Scheduler scheduler;

    public UsersPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;

        this.usersRepo = new RetrofitGithubUsersRepo(GithubApplication.INSTANCE.getApi());

        System.out.println(GithubApplication.INSTANCE.getApi());
    }

    private class UsersListPresenter implements IUserListPresenter {

        private List<GithubUser> users = new ArrayList<>();

        @Override
        public void onItemClick(UserItemView view) {
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos());
            }
            GithubUser user = users.get(view.getPos());
            chosen = user.getLogin();

            router.navigateTo(new Screens.UserOwnScreen());

        }

        @Override
        public void bindView(UserItemView view) {

            GithubUser user = users.get(view.getPos());
            view.setLogin(user.getLogin());
            view.loadAvatar(user.getAvatarUrl());

        }

        @Override
        public int getCount() {
            return users.size();
        }
    }


    private UsersListPresenter usersListPresenter = new UsersListPresenter();

    public UsersListPresenter getUsersListPresenter() {
        return usersListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        loadData();

    }


    private void loadData() {
        usersRepo.getUsers().observeOn(scheduler).subscribe(repos -> {
            usersListPresenter.users.clear();
            usersListPresenter.users.addAll(repos);
            getViewState().updateList();
        }, (e) -> {
            Log.w(TAG, "Error" + e.getMessage());
        });
    }

    private void addUserToList(GithubUser githubUser){
        usersListPresenter.users.add(githubUser);
    }

    public static String getChosen() {
        return chosen;
    }

    public boolean backPressed() {
       // router.exit();
        return true;

    }
}
