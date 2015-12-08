package app.studentorganizer.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.adapters.TaskListAdapter;
import app.studentorganizer.com.ColorTag;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;

public class SubjectActivity extends BaseActivity {

    public static final String SUBJECT_ID_EXTRA = "_SUBJECT_ID";

    protected static final int DEFAULT_SUBJECT_ID = -1;

    protected Subject mSubject;
    protected int mSubjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSubjectId = getIntent().getIntExtra(SUBJECT_ID_EXTRA, DEFAULT_SUBJECT_ID);

        super.onCreate(savedInstanceState);

        if (mSubject == null) {
            // No subjects with given id found. Finish activity.
            finish();
        } else {
            initializeView();
        }
    }

    @Override
    public int getContentView() {
        return R.layout.subject;
    }

    @Override
    public void loadDataFromDB() {
        mSubject = DBFactory.getFactory().getSubjectDAO().getByID(mSubjectId);
    }

    private void initializeView() {

        // Initialize general info
        ((ImageButton) findViewById(R.id.category_icon)).
                setImageResource(mSubject.getColorTag().getDrawableId());
        ((TextView) findViewById(R.id.name)).setText(mSubject.getName());
        ((TextView) findViewById(R.id.type)).setText(mSubject.getType().getStringId());

        // If no teacher provided display default teacher
        if (mSubject.getTeacher() != null) {
            ((TextView) findViewById(R.id.teacher_name)).setText(mSubject.getTeacher().getName());
            ((TextView) findViewById(R.id.teacher_type)).setText(mSubject.getTeacher().getType());
        }

        // Setup tasks recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.goals_list);
        TaskListAdapter adapter = new TaskListAdapter(mSubject.getTasks(), this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
