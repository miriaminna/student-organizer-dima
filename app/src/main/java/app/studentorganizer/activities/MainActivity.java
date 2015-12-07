package app.studentorganizer.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import app.studentorganizer.R;
import app.studentorganizer.adapters.TaskListAdapter;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.decorations.BaseItemDecoration;
import app.studentorganizer.entities.Task;


public class MainActivity extends BaseListActivity {
    private TaskListAdapter mTaskListAdapter;
    private ArrayList<Task> mTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTasks = new ArrayList<>();
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void loadDataFromDB() {
        mTasks.addAll(mDatabaseManager.getAllTasksTest());
    }

    @Override
    public int getListView() {
        return R.id.goals_list;
    }

    @Override
    protected RecyclerView.Adapter initializeAdapter() {
        mTaskListAdapter = new TaskListAdapter(mTasks, this);
        return mTaskListAdapter;
    }

    @Override
    protected RecyclerView.ItemDecoration initializeDecoration() {
        return new BaseItemDecoration(this, R.drawable.list_items_divider);
    }
}
