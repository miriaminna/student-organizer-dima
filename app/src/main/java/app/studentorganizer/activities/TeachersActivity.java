package app.studentorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.decorations.BaseItemDecoration;
import app.studentorganizer.R;
import app.studentorganizer.adapters.TeachersListAdapter;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.entities.Teacher;

public class TeachersActivity extends BaseListActivity {
    private List<Teacher> mTeachers;
    private TeachersListAdapter mTeachersListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTeachers = new ArrayList<>();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected RecyclerView.Adapter initializeAdapter() {
        mTeachersListAdapter = new TeachersListAdapter(mTeachers, this);
        return mTeachersListAdapter;
    }

    @Override
    protected RecyclerView.ItemDecoration initializeDecoration() {
        return new BaseItemDecoration(this, R.drawable.list_items_divider);
    }

    @Override
    public int getContentView() {
        return R.layout.teachers;
    }

    @Override
    public void loadDataFromDB() {
        mTeachers.addAll(mDatabaseManager.getAllTeachersTest());
    }

    @Override
    public int getListView() {
        return R.id.teachers_list;
    }

    public void onTeacherNew(View v) {
        Intent intent = new Intent(this, NewTeacherActivity.class);
        startActivity(intent);
    }
}
