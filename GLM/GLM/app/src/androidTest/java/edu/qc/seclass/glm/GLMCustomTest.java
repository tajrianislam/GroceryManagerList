package edu.qc.seclass.glm;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.regex.Pattern.matches;
import static org.hamcrest.Matchers.anything;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)

public class GLMCustomTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    private String testlist1, testlist2,testlist3;

    @Before
    public void initial(){
        testlist1 = "test1";
        testlist2 = "test2";
        testlist3 = "test3";
    }

    @Test
    public void CreateNewList1(){
        // Create New List 1
        onView(withId(R.id.createList)).perform(click());
        onView(withId(R.id.listName)).perform(typeText(testlist1), closeSoftKeyboard());
        onView(withId(R.id.createList)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listsListView)).atPosition(0).onChildView(withId(R.id.listLabel));
    }

    @Test
    public void CreateNewList2(){
        // Create New List 2
        onView(withId(R.id.createList)).perform(click());
        onView(withId(R.id.listName)).perform(typeText(testlist2), closeSoftKeyboard());
        onView(withId(R.id.createList)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listsListView)).atPosition(1).onChildView(withId(R.id.listLabel));
    }

    @Test
    public void CreateNewList3(){
        // Create New List 3
        onView(withId(R.id.createList)).perform(click());
        onView(withId(R.id.listName)).perform(typeText(testlist3), closeSoftKeyboard());
        onView(withId(R.id.createList)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listsListView)).atPosition(2).onChildView(withId(R.id.listLabel));
    }

    @Test
    public void AddItemType(){
      //  onView(withId(R.id.listLabel)).perform(click());
     //   onData(anything()).inAdapterView(withId(R.id.listLabel)).atPosition(0).onChildView(withId(R.id.listLabel)).perform(click());
    }


}
