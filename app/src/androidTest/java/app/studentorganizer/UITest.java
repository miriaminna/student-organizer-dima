package app.studentorganizer;

import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.hamcrest.Matchers;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import app.studentorganizer.activities.DashboardActivity;
import app.studentorganizer.activities.UniScheduleActivity;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.db.DBSeed;
import app.studentorganizer.entities.UnivScheduleEntry;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Vitalii on 07-Jun-16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {
    @Rule
    public ActivityTestRule<DashboardActivity> mActivityRule =
            new ActivityTestRule<>(DashboardActivity.class);

    @Before
    public void seed() {
        DBSeed.clear();
        DBSeed.seed();
    }

    @Test
    public void checkUniSchedule() {
        DBFactory.getFactory().getUnivScheduleDAO().clear();

        Assert.assertEquals(
                DBFactory.getFactory().getUnivScheduleDAO().getAllEntities().size(),
                0
        );

        onView(withId(R.id.univ_schedule)).perform(click());
        onView(withId(R.id.fab)).perform(click());


        onView(withId(R.id.pair_start)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(15, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.pair_end)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(14, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.pair_start)).check(matches(withText("14:23")));

        List<UnivScheduleEntry> entries =
                DBFactory.getFactory().getUnivScheduleDAO().getAllEntities();
        Assert.assertEquals(entries.size(), 1);
        Assert.assertEquals(entries.get(0).getStart(), new LocalTime(14, 23));

        onView(withId(R.id.pair_start)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(15, 23));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.pair_end)).check(matches(withText("15:23")));
    }

    @Test
    public void checkTasks() {
//        onView(withId(R.id.tasks)).perform(click());
    }
}
