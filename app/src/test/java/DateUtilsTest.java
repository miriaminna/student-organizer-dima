import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import app.studentorganizer.util.DateUtils;

/**
 * Created by akalinichenko on 6/9/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class DateUtilsTest {
    @Test
    public void dateToStringTest() {
        String s = DateUtils.dateToString(2016, 10, 20);
        Assert.assertEquals(s, "2016-10-20");
    }

    @Test
    public void localDateToStringTest() {
        String s =
                DateUtils.localDateToString(new LocalDate(2016, 6, 12).plusDays(15));
        Assert.assertEquals(s, "2016-6-27");
    }
}
