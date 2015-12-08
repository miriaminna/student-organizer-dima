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

    // fixme : bad style - hardcoded strings
    public static ColorTag parse(String string) {
        switch (string) {
            case "ORANGE":
                return ORANGE;
            case "BLUE":
                return BLUE;
            case "GREEN":
                return GREEN;
            default:
                return null;
        }
    }
}
