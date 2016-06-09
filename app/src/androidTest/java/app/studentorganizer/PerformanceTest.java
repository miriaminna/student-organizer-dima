package app.studentorganizer;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import app.studentorganizer.activities.DashboardActivity;
import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.db.DBSeed;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PerformanceTest {
    @Rule
    public ActivityTestRule<DashboardActivity> mActivityRule =
            new ActivityTestRule<>(DashboardActivity.class);

    @Before
    public void clearDB() {
        DBSeed.clear();
    }

    private final int ITEMS_NUM = 1000;
    private final int NAVIGATIONS = 100;

    private void loadDB() {
        for (int i = 0; i < ITEMS_NUM; ++i) {
            Subject subject = new Subject();
            subject.setType(SubjectType.EXAM);
            subject.setName("NewSubject" + i);
            subject.setColorTag(ColorTag.GREEN);
            subject.setStartDate(new LocalDate().minusDays(5));
            subject.setEndDate(new LocalDate().plusDays(i + 5));
            DBFactory.getFactory().getSubjectDAO().addEntity(subject);
            DBSeed.seed();
        }
    }

    @Test
    public void stressTest() {
        loadDB();
        long start = System.currentTimeMillis();
        onView(withText(R.string.subjects)).perform(click());
        onView(withId(R.id.subject_list)).perform(
                RecyclerViewActions.scrollToPosition(ITEMS_NUM));
        pressBack();
        onView(withText(R.string.teachers)).perform(click());
        onView(withId(R.id.teachers_list)).perform(
                RecyclerViewActions.scrollToPosition(ITEMS_NUM));
        pressBack();
        onView(withText(R.string.my_schedule)).perform(click());
        onView(withId(R.id.schedule_days_list)).perform(
                RecyclerViewActions.scrollToPosition(ITEMS_NUM));
        pressBack();
        onView(withText(R.string.uni_schedule)).perform(click());
        onView(withId(R.id.schedule_list)).perform(
                RecyclerViewActions.scrollToPosition(ITEMS_NUM));
        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start <= 5000);
    }

    @Test
    public void soakTest() {
        loadDB();
        Random random = new Random();
        for (int i = 0; i < NAVIGATIONS; ++i) {
            switch (random.nextInt(4)) {
                case 0:
                    onView(withText(R.string.subjects)).perform(click());
                    onView(withId(R.id.subject_list)).perform(
                            RecyclerViewActions.scrollToPosition(ITEMS_NUM));
                    break;
                case 1:
                    onView(withText(R.string.teachers)).perform(click());
                    onView(withId(R.id.teachers_list)).perform(
                            RecyclerViewActions.scrollToPosition(ITEMS_NUM));
                    break;
                case 2:
                    onView(withText(R.string.my_schedule)).perform(click());
                    onView(withId(R.id.schedule_days_list)).perform(
                            RecyclerViewActions.scrollToPosition(ITEMS_NUM));
                    break;
                case 3:
                    onView(withText(R.string.uni_schedule)).perform(click());
                    onView(withId(R.id.schedule_list)).perform(
                            RecyclerViewActions.scrollToPosition(ITEMS_NUM));
                    break;
            }
            pressBack();
        }
    }
}
