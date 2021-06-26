package ru.geekbrains.githubclient;

import android.view.View;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import ru.geekbrains.githubclient.ui.fragments.UsersFragment;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class UsersFragmentTest {

    @Before
    public void setup() {
        FragmentScenario.launchInContainer(UsersFragment.class);
    }

    @Test
    public void recViewIsExist(){
      onView(withId(R.id.rv_users))
              .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void recScrollToPosTest() {
        onView(withId(R.id.rv_users))
                .perform(RecyclerViewActions.
                        scrollToPosition(10));
    }

    @Test
    public void recScrollToTest() {
        onView(isRoot()).perform(delay());
        onView(withId(R.id.rv_users))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("nitay"))));
    }


    @Test
    public void positionClickTest(){
        onView(isRoot()).perform(delay());
        onView(withId(R.id.rv_users))
                .perform(RecyclerViewActions.actionOnItemAtPosition(29, click()));
    }


    private ViewAction delay(){
        ViewAction viewAction = new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for $10 seconds";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(3000L);
            }
        };
        return viewAction;
    }

}
