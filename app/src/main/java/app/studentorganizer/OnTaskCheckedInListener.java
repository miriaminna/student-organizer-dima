package app.studentorganizer;

import app.studentorganizer.entities.Task;

/**
 * Created by dmytroberezin on 12/6/15.
 */
public interface OnTaskCheckedInListener {
    void onTaskCheckedIn(Task task);
}
