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
            initializeView(mSubject);
        }
    }

    @Override
    public int getContentView() {
        return R.layout.subject;
    }

    @Override
    public void loadDataFromDB() {
        mSubject = mDatabaseManager.getSubjectById(mSubjectId);
    }

    private void initializeView(Subject subject) {
        // FIXME : not getting color tags out of DB
        /*((ImageButton) findViewById(R.id.category_icon)).
                setImageResource(subject.getColorTag().getDrawableId());*/
        ((TextView) findViewById(R.id.name)).setText(subject.getName());
        ((TextView) findViewById(R.id.type)).setText(subject.getType().getStringId());
        if (subject.getTeacher() != null) {
            ((TextView) findViewById(R.id.teacher_name)).setText(subject.getTeacher().getName());
            ((TextView) findViewById(R.id.teacher_type)).setText(subject.getTeacher().getType());
        }
        List<Task> tasks = subject.getTasks();
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        /*Task task1 = new Task();
        task1.setId(1);
        task1.setName("Programming - Labs");
        task1.setDeadline(new LocalDate().plusDays(1));
        task1.setPoints(30.0);
        task1.setProgress(10);
        task1.setTarget(40);
        task1.setSubject(new Subject());
        task1.getSubject().setColorTag(ColorTag.GREEN);
        tasks.add(task1);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.goals_list);
        TaskListAdapter adapter = new TaskListAdapter(tasks, this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
