package com.example.mfinal.navigation;

import androidx.fragment.app.Fragment;

import com.example.mfinal.ui.fragments.StatsFragment;
import com.example.mfinal.ui.fragments.CasesFragment;
import com.example.mfinal.ui.fragments.DeathsFragment;
import com.example.mfinal.ui.fragments.SearchFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    public static class StatsScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() { return StatsFragment.getInstance(0);}

    }

    public static class SearchScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return SearchFragment.getInstance(0);
        }

    }

    public static class DeathsScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return DeathsFragment.getInstance(0);
        }
    }

    public static class CasesScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return CasesFragment.getInstance(0);
        }
    }
}
