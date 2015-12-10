package app.studentorganizer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.adapters.MarksListAdapter;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Test;

public class MarksActivity extends BaseListActivity {
    protected List<Subject> mSubjects;
    protected List<Pair<Subject, Double>> mMarks;
    protected MarksListAdapter mMarksListAdapter;
    protected Double mGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSubjects = new ArrayList<>();
        mMarks = new ArrayList<>();
        super.onCreate(savedInstanceState);

        ((TextView)findViewById(R.id.gpa)).setText(String.format("GPA = %.2f", mGPA));
    }

    @Override
    public int getContentView() {
        return R.layout.marks_list_semester;
    }

    @Override
    public void loadDataFromDB() {
        mSubjects.clear();
        mSubjects.addAll(DBFactory.getFactory().getSubjectDAO().getAllEntities());
        Collections.sort(mSubjects, new Comparator<Subject>() {
            @Override
            public int compare(Subject lhs, Subject rhs) {
                boolean fst = lhs.getEndDate().isBefore(new LocalDate());
                boolean snd = rhs.getEndDate().isBefore(new LocalDate());
                if (fst == snd) {
                    return lhs.getName().compareTo(rhs.getName());
                }
                return !fst ? -1 : 1;
            }
        });
        calcMarks();
    }

    private void calcMarks() {
        mGPA = 0.;
        mMarks.clear();
        for (Subject s: mSubjects) {
            List<Test> tests = DBFactory.getFactory().getTestDAO().getBySubjectId(s.getId());
            List<Task> tasks = DBFactory.getFactory().getTaskDAO().getBySubjectId(s.getId());
            Double earned = 0., total = 0.;

            for (Test t: tests) {
                earned += t.getResult();
                total += t.getPoints();
            }
            for (Task t: tasks) {
                earned += t.getPoints() * t.getProgress() / t.getTarget();
                total += t.getPoints();
            }

            Double cur = Math.abs(total) < 1e-3 ? 0. : 100. * earned / total;
            mMarks.add(new Pair<>(s, cur));
            mGPA += cur;
        }
        mGPA /= mMarks.size();
    }

    @Override
    protected RecyclerView.Adapter initializeAdapter() {
        mMarksListAdapter = new MarksListAdapter(mMarks, this);
        return mMarksListAdapter;
    }

    @Override
    public int getListView() {
        return R.id.marks_list;
    }
}
