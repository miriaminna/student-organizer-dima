package app.studentorganizer.com;

import app.studentorganizer.R;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public enum SubjectType {
    CREDIT {
        @Override
        public int getStringId() {
            return R.string.subject_type_credit;
        }
    },
    EXAM {
        @Override
        public int getStringId() {
            return R.string.subject_type_exam;
        }
    };
    public abstract int getStringId();
}
