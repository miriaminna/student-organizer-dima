package app.studentorganizer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.entities.Task;


public class MainActivity extends ActionBarActivity {

    private DatabaseManager mDatabaseManager;
    private TaskListAdapter mTaskListAdapter;
    private ArrayList<Task> mTasks;
    private RecyclerView mRecyclerView;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeToolBar();
        initializeRecyclerView();
        initializeDB();
    }

    private void initializeDB() {
        mDatabaseManager = new DatabaseManager(this);
        mDatabaseManager.open();
        loadTasksFromDatabaseSync();
    }

    private void loadTasksFromDatabaseSync() {
        mTasks.addAll(mDatabaseManager.getAllTasksTest());
    }

    private void initializeRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.goals_list);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mTasks = new ArrayList<>();
        mTaskListAdapter = new TaskListAdapter(mTasks, this);
        mRecyclerView.setAdapter(mTaskListAdapter);
        mRecyclerView.addItemDecoration(
                new TaskItemDecorator(this, R.drawable.list_items_divider));
    }

    private void initializeToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.navigation_back_25));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
