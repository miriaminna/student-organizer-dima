package app.studentorganizer.com;

import app.studentorganizer.R;

/**
 * Created by dmytroberezin on 12/5/15.
 */
public enum ColorTag {
    ORANGE {
        @Override
        public int getDrawableId() {
            return R.drawable.round_shape_orange;
        }
    },
    BLUE {
        @Override
        public int getDrawableId() {
            return R.drawable.round_shape_blue;
        }
    },
    GREEN {
        @Override
        public int getDrawableId() {
            return R.drawable.round_shape_green;
        }
    };
    public abstract int getDrawableId();
}
