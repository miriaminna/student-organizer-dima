import android.content.Context;
import android.content.res.Resources;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.studentorganizer.R;
import app.studentorganizer.com.TaskUtil;

import static org.mockito.Mockito.when;

/**
 * Test for the {@link app.studentorganizer.com.TaskUtil} class.
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskUtilTest {
    @Mock
    private Context mContext;
    @Mock
    private Resources mResources;

    private final String DUES_IN_STR = "Dues in";
    private final String DUE_STR = "Due";
    private final String MONTH_STR = "month";
    private final String MONTHS_STR = "months";
    private final String WEEK_STR = "week";
    private final String WEEKS_STR = "weeks";
    private final String DAY_STR = "day";
    private final String DAYS_STR = "days";
    private final String HOUR_STR = "hr";
    private final String HOURS_STR = "hrs";

    @Before
    public void resourcesSetup() {
        when(mContext.getResources()).thenReturn(mResources);
        when(mResources.getString(R.string.dues_in)).thenReturn(DUES_IN_STR);
        when(mResources.getString(R.string.due)).thenReturn(DUE_STR);
        when(mResources.getString(R.string.month)).thenReturn(MONTH_STR);
        when(mResources.getString(R.string.months)).thenReturn(MONTHS_STR);
        when(mResources.getString(R.string.week)).thenReturn(WEEK_STR);
        when(mResources.getString(R.string.weeks)).thenReturn(WEEKS_STR);
        when(mResources.getString(R.string.day)).thenReturn(DAY_STR);
        when(mResources.getString(R.string.days)).thenReturn(DAYS_STR);
    }

    @Test
    public void getCheckInDue_testMonths() {
        LocalDate deadlineDate = new LocalDate();
        // Move deadline one month from now.
        deadlineDate = deadlineDate.plusMonths(1);

        String deadlineString = TaskUtil.getCheckInDue(deadlineDate, mContext);
        Assert.assertEquals(DUES_IN_STR + " 1 " + MONTH_STR, deadlineString);

        // Move deadline one more month from now. Total 2 months.
        deadlineDate = deadlineDate.plusMonths(1);

        deadlineString = TaskUtil.getCheckInDue(deadlineDate, mContext);
        Assert.assertEquals(DUES_IN_STR + " 2 " + MONTHS_STR, deadlineString);
    }

    @Test
    public void getCheckInDue_testWeeks() {
        LocalDate deadlineDate = new LocalDate();
        // Move deadline one week from now.
        deadlineDate = deadlineDate.plusWeeks(1);

        String deadlineString = TaskUtil.getCheckInDue(deadlineDate, mContext);
        Assert.assertEquals(DUES_IN_STR + " 1 " + WEEK_STR, deadlineString);

        // Move deadline one more week from now. Total 2 weeks.
        deadlineDate = deadlineDate.plusWeeks(1);

        deadlineString = TaskUtil.getCheckInDue(deadlineDate, mContext);
        Assert.assertEquals(DUES_IN_STR + " 2 " + WEEKS_STR, deadlineString);
    }

    @Test
    public void getCheckInDue_testDays() {
        LocalDate deadlineDate = new LocalDate();
        // Move deadline one week from now.
        deadlineDate = deadlineDate.plusDays(1);

        String deadlineString = TaskUtil.getCheckInDue(deadlineDate, mContext);
        Assert.assertEquals(DUES_IN_STR + " 1 " + DAY_STR, deadlineString);

        // Move deadline one more day from now. Total 2 days.
        deadlineDate = deadlineDate.plusDays(1);

        deadlineString = TaskUtil.getCheckInDue(deadlineDate, mContext);
        Assert.assertEquals(DUES_IN_STR + " 2 " + DAYS_STR, deadlineString);
    }

    @Test
    public void getCheckInDue_testLessThanHour() {
        LocalDate deadlineDate = new LocalDate();
        String deadlineString = TaskUtil.getCheckInDue(deadlineDate, mContext);
        Assert.assertEquals(DUE_STR, deadlineString);
    }
}
