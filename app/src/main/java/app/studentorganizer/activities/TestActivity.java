package app.studentorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import app.studentorganizer.OnTaskCheckedInListener;
import app.studentorganizer.R;
import app.studentorganizer.adapters.TaskListAdapter;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Teacher;
import app.studentorganizer.entities.Test;

/**
 * Created by gideon on 09.12.15.
 */
public class TestActivity extends BaseActivity implements OnTaskCheckedInListener {

    public static final String TEST_ID_EXTRA = "_TEST_ID";

    protected static final int DEFAULT_TEST_ID = -1;

    protected Test mTest;
    protected Long mTestId;
    protected TaskListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTestId = getIntent().getLongExtra(TEST_ID_EXTRA, DEFAULT_TEST_ID);

        super.onCreate(savedInstanceState);

        if (mTest == null) {
            // No subjects with given id found. Finish activity.
            finish();
        } else {
            initializeView();
        }
    }

    @Override
    public int getContentView() {
        return R.layout.test;
    }

    @Override
    public void loadDataFromDB() {
        mTest = DBFactory.getFactory().getTestDAO().getByID(mTestId);
    }

    private void initializeView() {

        // Initialize general info
        ((ImageButton) findViewById(R.id.category_icon)).
                setImageResource(mTest.getSubjectId().intValue());
        ((TextView) findViewById(R.id.subject)).setText(DBFactory.getFactory().getSubjectDAO().getByID(mTest.getSubjectId()).getName());
        ((TextView) findViewById(R.id.date)).setText(mTest.getDate().toString());
        ((TextView) findViewById(R.id.type)).setText(mTest.getTestType().toString());


        // Setup tasks recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.goals_list);
        recyclerView.setLayoutManager(layoutManager);

        // Fetch tasks
       // List<Task> content = DBFactory.getFactory().getContentsDAO().getByID(mTest.getContentId());
//        mAdapter = new ContentListAdapter(content, this);
//        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onTaskCheckedIn(Task task) {
        DBFactory.getFactory().getTaskDAO().updateEntity(task);
        mAdapter.notifyDataSetChanged();
    }
}
