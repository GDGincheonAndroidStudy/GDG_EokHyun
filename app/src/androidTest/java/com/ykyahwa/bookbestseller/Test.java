package com.ykyahwa.bookbestseller;

import android.support.test.espresso.DataInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by eokhyunlee on 2016. 1. 21..
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class Test {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @org.junit.Test
    public void test() {
        onView(withId(R.id.MAIN_LV_BOOK_LIST)).check(matches(isDisplayed()));
    }

    @org.junit.Test
    public void testListSelect(){
        onData(allOf(is(instanceOf(String.class)), is("지금 이 순간"))).perform(click());
//        onRow("지금 이 순간").onChildView(withId(R.id.BOOK_ITEM_TITLE)).perform(click());
    }

    private static DataInteraction onRow(String str) {
        return onData(hasEntry(equalTo("title"), is(str)));
    }

}
