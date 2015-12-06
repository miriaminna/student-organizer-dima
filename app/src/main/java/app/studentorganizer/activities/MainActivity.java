package app.studentorganizer.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import app.studentorganizer.OnTaskCheckedInListener;
import app.studentorganizer.R;
import app.studentorganizer.activities.BaseActivity;
import app.studentorganizer.adapters.TaskListAdapter;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.decorations.BaseItemDecoration;
import app.studentorganizer.entities.Task;


public class MainActivity extends BaseActivity implements OnTaskCheckedInListener {

    private DatabaseManager mDatabaseManager;
    private TaskListAdapter mTaskListAdapter;
    private ArrayList<Task> mTasks;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initializeDB() {
        mDatabaseManager = new DatabaseManager(this);
        mDatabaseManager.open();
        loadTasksFromDatabaseSync();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public int getListView() {
        return R.id.goals_list;
    }

    private void loadTasksFromDatabaseSync() {
        mTasks.addAll(mDatabaseManager.getAllTasksTest());
    }

    @Override
    protected RecyclerView.Adapter initializeAdapter() {
        mTasks = new ArrayList<>();
        mTaskListAdapter = new TaskListAdapter(mTasks, this);
        return mTaskListAdapter;
    }

    @Override
    protected RecyclerView.ItemDecoration initializeDecoration() {
        return new BaseItemDecoration(this, R.drawable.list_items_divider);
    }

    @Override
    public void onTaskCheckedIn() {
        // gamno-code

        for (int i = 0; i < mTasks.size(); i++) {
            mTasks.set(i, mDatabaseManager.updateSimpleTask(mTasks.get(i)));
        }
        mTaskListAdapter.notifyDataSetChanged();
    }
}
