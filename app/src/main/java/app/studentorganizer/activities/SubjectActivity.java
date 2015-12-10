package app.studentorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.OnTaskCheckedInListener;
import app.studentorganizer.R;
import app.studentorganizer.adapters.ContentItemAdapter;
import app.studentorganizer.adapters.TaskListAdapter;
import app.studentorganizer.com.ContentParent;
import app.studentorganizer.com.SubjectCommon;
import app.studentorganizer.com.SubjectTab;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.ContentItem;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Teacher;
import app.studentorganizer.entities.Test;

public class SubjectActivity extends BaseActivity implements
        OnTaskCheckedInListener,
        OnTaskCreatedListener {

    protected RecyclerView mRecyclerView;
    protected Subject mSubject;
    protected Long mSubjectId;
    protected RecyclerView.Adapter mAdapter;

    protected SubjectTab mSubjectTab;

    protected List<Task> mTasks;
    protected List<ContentItem> mContents;
    protected List<Test> mTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSubjectId = getIntent().getLongExtra(
                SubjectCommon.SUBJECT_ID_EXTRA,
                SubjectCommon.DEFAULT_SUBJECT_ID);

        mTasks = new ArrayList<>();
        mContents = new ArrayList<>();

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
        mTasks.clear();
        mTasks.addAll(DBFactory.getFactory().getTaskDAO().getBySubjectId(mSubjectId));
        mContents.clear();
        mContents.addAll(DBFactory.getFactory().getContentItemDAO().
                getByParent(mSubjectId, ContentParent.SUBJECT));
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

        // Setup tasks recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(layoutManager);

        findViewById(R.id.tasks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubjectTab = SubjectTab.TASKS;
                onSubjectTabChanged();
            }
        });
        findViewById(R.id.materials).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubjectTab = SubjectTab.MATERIALS;
                onSubjectTabChanged();
            }
        });

        mSubjectTab = SubjectTab.TASKS;


        onSubjectTabChanged();
        mAdapter = new TaskListAdapter(mTasks, this);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadDataFromDB();
        System.out.println("Resuming");
        /*mSubjectTab = SubjectTab.TASKS;
        onSubjectTabChanged();*/
        mAdapter.notifyDataSetChanged();
    }

    private void onSubjectTabChanged() {
        switch (mSubjectTab) {
            case TASKS:
                mAdapter = new TaskListAdapter(mTasks, this);
                mRecyclerView.setAdapter(mAdapter);
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
                mAdapter = new ContentItemAdapter(mContents, this);
                mRecyclerView.setAdapter(mAdapter);

                findViewById(R.id.fab).setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(
                                        SubjectActivity.this,
                                        EditContentItemActivity.class);

                                intent.putExtra(EditContentItemActivity.PARENT_ID_EXTRA, mSubjectId);
                                intent.putExtra(EditContentItemActivity.PARENT_TYPE_EXTRA,
                                        ContentParent.SUBJECT.toString());

                                startActivity(intent);
                            }
                        }
                );
                break;
            case TESTS:
                break;
        }
    }

    @Override
    public void onTaskCheckedIn(Task task) {
        DBFactory.getFactory().getTaskDAO().updateEntity(task);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTaskCreated(Task task) {
        mTasks.add(task);
        mAdapter.notifyDataSetChanged();
    }
}
