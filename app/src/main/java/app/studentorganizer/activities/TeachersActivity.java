package app.studentorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.db.DBFactory;
import app.studentorganizer.R;
import app.studentorganizer.adapters.TeachersListAdapter;
import app.studentorganizer.entities.Teacher;

public class TeachersActivity extends BaseListActivity {
    private List<Teacher> mTeachers;
    private TeachersListAdapter mTeachersListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTeachers = new ArrayList<>();
        super.onCreate(savedInstanceState);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeachersActivity.this, EditTeacherActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadDataFromDB();
        System.out.println("Resuming");
        mTeachersListAdapter.notifyDataSetChanged();
    }

    @Override
    protected RecyclerView.Adapter initializeAdapter() {
        mTeachersListAdapter = new TeachersListAdapter(mTeachers, this, true);
        return mTeachersListAdapter;
    }

    @Override
    public int getContentView() {
        return R.layout.teachers;
    }

    @Override
    public void loadDataFromDB() {
        mTeachers.clear();
        mTeachers.addAll(DBFactory.getFactory().getTeacherDAO().getAllEntities());
    }

    @Override
    public int getListView() {
        return R.id.teachers_list;
    }
}
