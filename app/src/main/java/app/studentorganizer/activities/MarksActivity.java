package app.studentorganizer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.adapters.MarksListAdapter;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Subject;

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
        calcMarks();
    }

    private void calcMarks() {
        mGPA = 0.;
        mMarks.clear();
        for (Subject s: mSubjects) {
            Double cur = 100.;

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
