package app.studentorganizer;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.studentorganizer.activities.DashboardActivity;
import app.studentorganizer.activities.UniScheduleActivity;
import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.DayConstants;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.com.TeacherType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.db.DBSeed;
import app.studentorganizer.entities.StudentScheduleEntry;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Teacher;
import app.studentorganizer.entities.UnivScheduleEntry;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DBTest {
    @Rule
    public ActivityTestRule<DashboardActivity> mActivityRule =
            new ActivityTestRule<>(DashboardActivity.class);

    @Before
    public void clearDB() {
        DBSeed.clear();
    }

    public void seed() {

        Teacher teacher = new Teacher();
        teacher.setName("New Teacher 1");
        teacher.setType(TeacherType.POSTGRADUATE);
        DBFactory.getFactory().getTeacherDAO().addEntity(teacher);

        Subject subject = new Subject();
        subject.setTeacherId(teacher.getId());
        subject.setName("New Subject 1");
        subject.setType(SubjectType.EXAM);
        subject.setColorTag(ColorTag.GREEN);
        subject.setStartDate(new LocalDate());
        subject.setEndDate(new LocalDate().plusMonths(1));
        DBFactory.getFactory().getSubjectDAO().addEntity(subject);

        Task task = new Task();
        task.setSubjectId(subject.getId());
        task.setPoints(20.0);
        task.setName("New task 1");
        task.setDeadline(new LocalDate().plusDays(4));
        DBFactory.getFactory().getTaskDAO().addEntity(task);

        UnivScheduleEntry uniEntry = new UnivScheduleEntry();
        uniEntry.setStart(new LocalTime(10, 15));
        uniEntry.setEnd(new LocalTime(12, 10));
        uniEntry.setLessonNumber(2);
        DBFactory.getFactory().getUnivScheduleDAO().addEntity(uniEntry);

        StudentScheduleEntry entry = new StudentScheduleEntry();
        entry.setClassroom(200);
        entry.setUnivScheduleEntryId(uniEntry.getId());
        entry.setDay(DayConstants.FRIDAY);
        entry.setSubjectId(subject.getId());
        DBFactory.getFactory().getStudentScheduleDAO().addEntity(entry);
    }

    @Test
    public void dependencySubjectDeletion() {
        seed();

        onView(withId(R.id.subjects)).perform(click());
        onView(withId(R.id.subject_delete)).perform(click());
        pressBack();
        onView(withId(R.id.tasks)).perform(click());
        onView(withId(R.id.check_in_button)).check(doesNotExist());
        pressBack();
        onView(withId(R.id.my_schedule)).perform(click());
        onView(allOf(withId(R.id.name), withText("Sunject Name"))).check(doesNotExist());
    }

    @Test
    public void dependencyTeacherDeletion() {
        seed();

        onView(withId(R.id.teachers)).perform(click());
        onView(withId(R.id.button_delete)).perform(click());
        pressBack();
        onView(withId(R.id.subjects)).perform(click());
        onView(withId(R.id.category_icon)).check(doesNotExist());
    }
}
