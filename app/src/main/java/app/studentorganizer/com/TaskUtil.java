package app.studentorganizer.com;

import android.content.Context;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import app.studentorganizer.R;

/**
 * Created by dmytroberezin on 12/5/15.
 */
public class TaskUtil {
    public static int getCategoryIconId(ColorTag colorTag) {
        switch (colorTag) {
            case BLUE: return R.drawable.round_shape_blue;
            case GREEN: return R.drawable.round_shape_green;
            case ORANGE: return R.drawable.round_shape_orange;
        }
        return 0;
    }

    public static String getCheckInDue(LocalDate deadline, Context context) {
        StringBuilder s = new StringBuilder();
        s.append(context.getResources().getString(R.string.dues));
        s.append(" ");
        Period checkInDue = new Period(new LocalDate(), deadline);
        if (checkInDue.getMonths() > 0) {
            s.append(checkInDue.getMonths());
            s.append(" ");
            s.append(checkInDue.getMonths() == 1
                    ? context.getResources().getString(R.string.month)
                    : context.getResources().getString(R.string.months));
        } else
        if (checkInDue.getWeeks() > 0) {
            s.append(checkInDue.getWeeks());
            s.append(" ");
            s.append(checkInDue.getWeeks() == 1
                    ? context.getResources().getString(R.string.week)
                    : context.getResources().getString(R.string.weeks));
        } else
        if (checkInDue.getDays() > 0) {
            s.append(checkInDue.getDays());
            s.append(" ");
            s.append(checkInDue.getDays() == 1
                    ? context.getResources().getString(R.string.day)
                    : context.getResources().getString(R.string.days));
        } else
        if (checkInDue.getHours() > 0) {
            s.append(checkInDue.getHours());
            s.append(" ");
            s.append(checkInDue.getHours() == 1
                    ? context.getResources().getString(R.string.hr)
                    : context.getResources().getString(R.string.hrs));
        }
        return s.toString();
    }
}
