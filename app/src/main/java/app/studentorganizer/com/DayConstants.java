package app.studentorganizer.com;

import java.util.Arrays;

import app.studentorganizer.R;

/**
 * Created by henko on 04.12.15.
 */
public class DayConstants {
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;

    public static int getConstant(String[] names, String name) {
        return Arrays.asList(names).indexOf(name);
    }
}
