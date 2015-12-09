package app.studentorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.OnTaskCheckedInListener;
import app.studentorganizer.R;
import app.studentorganizer.adapters.TaskListAdapter;
import app.studentorganizer.com.ColorTag;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Teacher;

public class SubjectActivity extends BaseActivity implements OnTaskCheckedInListener {

    public static final String SUBJECT_ID_EXTRA = "_SUBJECT_ID";

    protected static final int DEFAULT_SUBJECT_ID = -1;

    protected Subject mSubject;
    protected Long mSubjectId;
    protected TaskListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSubjectId = getIntent().getLongExtra(SUBJECT_ID_EXTRA, DEFAULT_SUBJECT_ID);

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
        final Teacher teacher = DBFactory.getFactory().getTeacherDAO().getByID(mSubject.getTeacherId());
        if (teacher != null) {
            ((TextView) findViewById(R.id.teacher_name)).setText(teacher.getName());
            // TODO: get teacher type string from strings.xml
            ((TextView) findViewById(R.id.teacher_type)).setText(teacher.getType().toString());
            // Show teacher activity when teacher clicked
            ImageButton teacherIcon = (ImageButton) findViewById(R.id.teacher_icon);
            teacherIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SubjectActivity.this, TeacherActivity.class);
                    intent.putExtra(TeacherActivity.ID_EXTRA, teacher.getId());
                    startActivity(intent);
                }
            });
            teacherIcon.setImageResource(teacher.getType().getDrawable());
        }

        // Setup tasks recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.goals_list);
        recyclerView.setLayoutManager(layoutManager);

        // Fetch tasks
        List<Task> tasks = DBFactory.getFactory().getTaskDAO().getBySubjectId(mSubjectId);
        mAdapter = new TaskListAdapter(tasks, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onTaskCheckedIn(Task task) {
        DBFactory.getFactory().getTaskDAO().updateEntity(task);
        mAdapter.notifyDataSetChanged();
    }
}
