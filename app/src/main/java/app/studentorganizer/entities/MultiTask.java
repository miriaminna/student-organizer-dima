package app.studentorganizer.entities;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class MultiTask extends Task {
    private List<Task> mSubtasks;

    public MultiTask() {
    }

    public List<Task> getSubtasks() {
        return mSubtasks;
    }

    public void setSubtasks(List<Task> subtasks) {
        this.mSubtasks = subtasks;
    }

    public void addSubtask(Task subtask) {
        this.mSubtasks.add(subtask);
    }

    @Override
    public Period getDue() {
        mDeadline = new LocalDate(Long.MAX_VALUE);
        for (Task task : mSubtasks) {
            if (task.getDeadline().compareTo(mDeadline) == -1 && !task.isCompleted()) {
                mDeadline = task.getDeadline();
            }
        }
        return new Period(new LocalDate(), mDeadline);
    }

    @Override
    public Integer getProgress() {
        this.mProgress = 0;
        for (Task task : this.mSubtasks) {
            this.mProgress += task.isCompleted() ? 1 : 0;
        }
        return this.mProgress;
    }

    @Override
    public Integer getTarget() {
        return this.mSubtasks.size();
    }

    @Override
    public Double getPoints() {
        mPoints = .0;
        for (Task task : mSubtasks) {
            mPoints += task.getPoints();
        }
        return mPoints;
    }
}
