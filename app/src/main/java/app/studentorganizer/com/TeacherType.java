package app.studentorganizer.com;

import app.studentorganizer.R;

/**
 * Created by Vitalii on 06-Dec-15.
 */
public enum TeacherType {
    PROFESSOR {
        @Override
        public int getDrawable() {
            return R.drawable.round_shape_orange;
        }
    },
    DOCENT {
        @Override
        public int getDrawable() {
            return R.drawable.round_shape_blue;
        }
    },
    POSTGRADUATE {
        @Override
        public int getDrawable() {
            return R.drawable.round_shape_green;
        }
    };

    public abstract int getDrawable();
}
