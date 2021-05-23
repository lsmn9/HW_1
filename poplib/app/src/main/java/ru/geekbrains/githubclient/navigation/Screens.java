package ru.geekbrains.githubclient.navigation;

import androidx.fragment.app.Fragment;

import ru.geekbrains.githubclient.ui.fragments.RepoFragment;
import ru.geekbrains.githubclient.ui.fragments.UserOwnFragment;
import ru.geekbrains.githubclient.ui.fragments.UsersFragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static class UsersScreen extends SupportAppScreen {

        @Override
        public Fragment getFragment() {
            return UsersFragment.getInstance(0);
        }


    }
    public static class UserOwnScreen extends SupportAppScreen{

        @Override
        public Fragment getFragment() {
            return UserOwnFragment.getInstance(0);
        }

    }

    public static class UserRepoScreen extends SupportAppScreen{

        @Override
        public Fragment getFragment() {
            return RepoFragment.getInstance(0);
        }

    }

}
