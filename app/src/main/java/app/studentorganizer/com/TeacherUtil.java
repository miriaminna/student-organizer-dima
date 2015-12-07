package app.studentorganizer.com;

import app.studentorganizer.R;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public class TeacherUtil {
    public static int getTypeIcon(TeacherType type) {
        switch (type) {
            case PROFESSOR: return R.drawable.round_shape_orange;
            case DOCENT: return R.drawable.round_shape_blue;
            case POSTGRADUATE: return R.drawable.round_shape_green;
        }
        return 0;
    }
}
