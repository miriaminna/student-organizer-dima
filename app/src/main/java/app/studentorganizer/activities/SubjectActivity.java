package app.studentorganizer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import app.studentorganizer.R;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.entities.Subject;

public class SubjectActivity extends AppCompatActivity {
    public static final String SUBJECT_ID_EXTRA = "_SUBJECT_ID";
    protected static final int DEFAULT_SUBJECT_ID = -1;

    protected Subject mSubject;
    protected DatabaseManager mDatabaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject);
        initializeToolbar();
        initializeDatabase();

        int subjectId = getIntent().getIntExtra(SUBJECT_ID_EXTRA, DEFAULT_SUBJECT_ID);
        if (subjectId == DEFAULT_SUBJECT_ID) {
            // No subject id passed. Finish activity
            finish();
        } else {
            mSubject = mDatabaseManager.getSubjectById(subjectId);
            initializeView(mSubject);
        }
    }

    private void initializeView(Subject subject) {
        ((TextView) findViewById(R.id.name)).setText(subject.getName());
        // todo : create conversion from enum to real name
        ((TextView) findViewById(R.id.type)).setText(subject.getType().toString());
        if (subject.getTeacher() != null) {
            ((TextView) findViewById(R.id.teacher_name)).setText(subject.getTeacher().getName());
            ((TextView) findViewById(R.id.teacher_type)).setText(subject.getTeacher().getType());
        }
        /*TaskListAdapter adapter = new TaskListAdapter(subject.getTasks(), this);
        ((RecyclerView) findViewById(R.id.goals_list)).setAdapter(adapter);*/
    }

    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initializeDatabase() {
        mDatabaseManager = new DatabaseManager(this);
        mDatabaseManager.open();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
