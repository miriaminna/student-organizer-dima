package app.studentorganizer;

import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import app.studentorganizer.activities.EditSubjectActivity;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.db.DBSeed;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Teacher;
import app.studentorganizer.util.DateUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by akalinichenko on 6/9/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SubjectAndDBIntegrationTest {

    @Rule
    public ActivityTestRule<EditSubjectActivity> mActivityRule =
            new ActivityTestRule<>(EditSubjectActivity.class);

    private String SUBJECT_NAME = "SubjectName";
    private String SUBJECT_TYPE = "CREDIT";
    private Teacher mTeacher;

    @Before
    public void seedDB() {
        DBSeed.clear();
        DBSeed.seed();
        mTeacher = DBFactory.getFactory().getTeacherDAO().getAllEntities().get(0);

        List<Teacher> teachers = DBFactory.getFactory().getTeacherDAO().getAllEntities();
        for (int i = 1; i < teachers.size(); ++i) {
            DBFactory.getFactory().getTeacherDAO().deleteEntity(teachers.get(i).getId());
        }
    }

    @Test
    public void editSubjectTest() {
        DBFactory.getFactory().getSubjectDAO().clear();

        Assert.assertEquals(0,
                DBFactory.getFactory().getSubjectDAO().getAllEntities().size());

        onView(withId(R.id.name)).perform(typeText(SUBJECT_NAME));
        onView(withId(R.id.subject_type)).perform(click());
        onView(withText(SUBJECT_TYPE)).perform(click());

        pressBack();

        onView(withId(R.id.teacher_select_button)).perform(click());
        onView(withId(R.id.teacher_icon)).perform(click());

        onView(withId(R.id.subject_start_date_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(1997, 9, 13));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.subject_end_date_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(1996, 9, 13));
        onView(withText("OK")).perform(click());

        // Assert start date is not older than end date
        onView(withId(R.id.subject_start_date)).
                check(matches(withText(DateUtils.dateToString(1996, 9, 13))));

        onView(withId(R.id.subject_start_date_button)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(1995, 9, 13));
        onView(withText("OK")).perform(click());

        // Assert start date has  changed and end date has NOT changed
        onView(withId(R.id.subject_start_date)).
                check(matches(withText(DateUtils.dateToString(1995, 9, 13))));
        onView(withId(R.id.subject_end_date)).
                check(matches(withText(DateUtils.dateToString(1996, 9, 13))));

        onView(withId(R.id.save_button)).perform(click());

        // Test whether Subject was saved to the DB
        List<Subject> subjects = DBFactory.getFactory().getSubjectDAO().getAllEntities();
        Assert.assertEquals(1, subjects.size());

        Subject subject = subjects.get(0);

        Assert.assertEquals(SUBJECT_NAME, subject.getName());
        Assert.assertEquals(SubjectType.valueOf(SUBJECT_TYPE), subject.getType());
        Assert.assertEquals(mTeacher.getId(), subject.getTeacherId());
        Assert.assertEquals(new LocalDate(1995, 9, 13), subject.getStartDate());
        Assert.assertEquals(new LocalDate(1996, 9, 13), subject.getEndDate());
    }
}
