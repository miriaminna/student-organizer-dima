package app.studentorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.OnTaskCheckedInListener;
import app.studentorganizer.OnTestCheckedInListener;
import app.studentorganizer.OnTestCreatedListener;
import app.studentorganizer.R;
import app.studentorganizer.adapters.TaskListAdapter;
import app.studentorganizer.adapters.TestListAdapter;
import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.SubjectCommon;
import app.studentorganizer.com.SubjectTab;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Content;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Teacher;
import app.studentorganizer.entities.Test;

public class SubjectActivity extends BaseActivity implements
        OnTaskCheckedInListener,
        OnTaskCreatedListener,
        OnTestCheckedInListener,
        OnTestCreatedListener {

    protected RecyclerView mRecyclerView;
    protected Subject mSubject;
    protected Long mSubjectId;
    protected TaskListAdapter mTasksAdapter;
    protected TestListAdapter mTestsAdapter;

    protected SubjectTab mSubjectTab;

    protected List<Task> mTasks;
    protected List<Content> mContents;
    protected List<Test> mTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSubjectId = getIntent().getLongExtra(
                SubjectCommon.SUBJECT_ID_EXTRA,
                SubjectCommon.DEFAULT_SUBJECT_ID);

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
        // Fetch tasks, materials and tests
        mTasks = DBFactory.getFactory().getTaskDAO().getBySubjectId(mSubjectId);
        mTasksAdapter = new TaskListAdapter(mTasks, this);
        mTests = DBFactory.getFactory().getTestDAO().getBySubjectId(mSubjectId);
        mTestsAdapter = new TestListAdapter(mTests, this);
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
            ((TextView) findViewById(R.id.teacher_type)).setText(teacher.getType().name());
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
            // Hide delete button
            findViewById(R.id.button_delete).setVisibility(View.GONE);
        }

        final TextView tasksTab = (TextView) findViewById(R.id.tasks);
        final TextView materialsTab = (TextView) findViewById(R.id.materials);
        final TextView testsTab = (TextView) findViewById(R.id.tests);

        tasksTab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mSubjectTab = SubjectTab.TASKS;
                        onSubjectTabChanged();
                    }
                }
        );

        materialsTab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mSubjectTab = SubjectTab.MATERIALS;
                        onSubjectTabChanged();
                    }
                }
        );

        testsTab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mSubjectTab = SubjectTab.TESTS;
                        onSubjectTabChanged();
                    }
                }
        );

        // Setup tasks recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(layoutManager);

        mSubjectTab = SubjectTab.TASKS;

        onSubjectTabChanged();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadDataFromDB();
        System.out.println("Resuming");
        mSubjectTab = SubjectTab.TASKS;
        onSubjectTabChanged();
    }

    private void onSubjectTabChanged() {
        ((TextView) findViewById(R.id.tasks)).setBackgroundResource(R.drawable.dashboard_button_background);
        ((TextView) findViewById(R.id.materials)).setBackgroundResource(R.drawable.dashboard_button_background);
        ((TextView) findViewById(R.id.tests)).setBackgroundResource(R.drawable.dashboard_button_background);
        switch (mSubjectTab) {
            case TASKS:
                ((TextView) findViewById(R.id.tasks)).setBackgroundResource(R.drawable.dashboard_button_background_active);

                mRecyclerView.setAdapter(mTasksAdapter);
                // mAdapter.notifyDataSetChanged();

                findViewById(R.id.fab).setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(
                                        SubjectActivity.this,
                                        EditTaskActivity.class);
                                intent.putExtra(SubjectCommon.SUBJECT_ID_EXTRA, mSubjectId);;
                                startActivity(intent);
                            }
                        }
                );

                break;
            case MATERIALS:
                ((TextView) findViewById(R.id.materials)).setBackgroundResource(R.drawable.dashboard_button_background_active);
                break;
            case TESTS:
                ((TextView) findViewById(R.id.tests)).setBackgroundResource(R.drawable.dashboard_button_background_active);

                mRecyclerView.setAdapter(mTestsAdapter);

                findViewById(R.id.fab).setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(
                                        SubjectActivity.this,
                                        EditTestActivity.class);
                                intent.putExtra(SubjectCommon.SUBJECT_ID_EXTRA, mSubjectId);
                                ;
                                startActivity(intent);
                            }
                        }
                );
                break;
        }
    }


    @Override
    public void onTaskCheckedIn(Task task) {
        DBFactory.getFactory().getTaskDAO().updateEntity(task);
        mTasksAdapter.notifyDataSetChanged();
    }

    // does Dima really needed this?
    @Override
    public void onTaskCreated(Task task) {
        mTasks.add(task);
        mTasksAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTestCheckedIn(Test test) {
        DBFactory.getFactory().getTestDAO().updateEntity(test);
        mTestsAdapter.notifyDataSetChanged();
    }

    // does Dima really needed this?
    @Override
    public void onTestCreated(Test test) {
        mTests.add(test);
        mTestsAdapter.notifyDataSetChanged();
    }
}
