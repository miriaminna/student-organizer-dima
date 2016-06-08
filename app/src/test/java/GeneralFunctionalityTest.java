import android.content.Context;
import android.content.res.Resources;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import app.studentorganizer.R;
import app.studentorganizer.com.TaskUtil;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Teacher;
import app.studentorganizer.util.DateUtils;
import app.studentorganizer.util.Pair;

import static org.mockito.Mockito.when;

/**
 * Created by Vitalii on 07-Jun-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class GeneralFunctionalityTest {
    @Test
    public void checkDateUtil() {
        String s = DateUtils.dateToString(2016, 10, 20);
        Assert.assertEquals(s, "2016-10-20");
        s = DateUtils.localDateToString(new LocalDate(2016, 6, 12).plusDays(15));
        Assert.assertEquals(s, "2016-6-27");
    }

    @Mock
    Context mMockContext;
    @Mock
    Resources mMockResources;

    @Test
    public void checkTaskUtil() {
        when(mMockContext.getResources()).thenReturn(mMockResources);
        when(mMockResources.getString(R.string.dues_in)).thenReturn("Dues in");
        when(mMockResources.getString(R.string.months)).thenReturn("months");
        when(mMockResources.getString(R.string.day)).thenReturn("day");
        when(mMockResources.getString(R.string.weeks)).thenReturn("weeks");

        String s = TaskUtil.getCheckInDue(new LocalDate().plusDays(15).plusMonths(2), mMockContext);
        Assert.assertEquals(s, "Dues in 2 months");
        s = TaskUtil.getCheckInDue(new LocalDate().plusDays(1), mMockContext);
        Assert.assertEquals(s, "Dues in 1 day");
        s = TaskUtil.getCheckInDue(new LocalDate().plusWeeks(2), mMockContext);
        Assert.assertEquals(s, "Dues in 2 weeks");
    }

    @Test
    public void checkTeacher() {
        Teacher teacher = new Teacher();
        teacher.setContacts("a:a\nb:c\nff:0");
        teacher.removeContact(new Pair<>("ff", "0"));
        teacher.addContact(new Pair<>("abc", "def"));
        teacher.removeContact(new Pair<>("x", "y"));
        Assert.assertEquals(teacher.getContactsAsString(), "a:a\nb:c\nabc:def");
    }
}
