package com.example.mfinal.navigation;

import androidx.fragment.app.Fragment;

import com.example.mfinal.ui.fragments.ActStatsFragment;
import com.example.mfinal.ui.fragments.SearchFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    public static class ActStatsScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return ActStatsFragment.getInstance(0);
        }

    }

    public static class SearchScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return SearchFragment.getInstance(0);
        }

    }
}
