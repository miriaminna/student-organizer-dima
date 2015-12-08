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
    private List<Long> mSubtasksIds;

    public MultiTask() {
        mSubtasksIds = new ArrayList<>();
    }

    public List<Long> getSubtasksIds() {
        return mSubtasksIds;
    }

    public void setSubtasksIds(List<Long> subtasksIds) {
        this.mSubtasksIds = subtasksIds;
    }

    public void addSubtaskId(Long subtaskId) {
        this.mSubtasksIds.add(subtaskId);
    }

    // TODO: Fetch subtasks from DB
    @Override
    public Period getDue() {
        mDeadline = new LocalDate(Long.MAX_VALUE);
        for (Long task : mSubtasksIds) {
//            if (task.getDeadline().compareTo(mDeadline) == -1 && !task.isCompleted()) {
//                mDeadline = task.getDeadline();
//            }
        }
        return new Period(new LocalDate(), mDeadline);
    }

    @Override
    public Integer getProgress() {
        this.mProgress = 0;
        for (Long task : this.mSubtasksIds) {
//            this.mProgress += task.isCompleted() ? 1 : 0;
        }
        return this.mProgress;
    }

    @Override
    public Integer getTarget() {
        return this.mSubtasksIds.size();
    }

    @Override
    public Double getPoints() {
        mPoints = .0;
        for (Long task : mSubtasksIds) {
//            mPoints += task.getPoints();
        }
        return mPoints;
    }

    @Override
    public String toString() {
        return super.toString() + " (multi)";
    }
}
