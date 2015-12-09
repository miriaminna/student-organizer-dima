package app.studentorganizer.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.adapters.MyScheduleDaysAdapter;
import app.studentorganizer.com.DayConstants;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.StudentScheduleEntry;

public class MyScheduleActivity extends BaseListActivity {
    protected MyScheduleDaysAdapter mMyScheduleDaysAdapter;
    protected List<List<StudentScheduleEntry>> mStudentScheduleDays;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadDataFromDB();
        mMyScheduleDaysAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mStudentScheduleDays = new ArrayList<>();
        super.onCreate(savedInstanceState);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyScheduleActivity.this, EditStudentScheduleActivity.class);
                MyScheduleActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public int getContentView() {
        return R.layout.my_schedule;
    }

    @Override
    public void loadDataFromDB() {
        mStudentScheduleDays.clear();
        List<StudentScheduleEntry> entries = DBFactory.getFactory().getStudentScheduleDAO().getAllEntities();
        for (int i = DayConstants.MONDAY; i <= DayConstants.SUNDAY; ++i) {
            mStudentScheduleDays.add(new ArrayList<StudentScheduleEntry>());
        }
        for (StudentScheduleEntry entry: entries) {
            mStudentScheduleDays.get(entry.getDay()).add(entry);
        }
    }

    @Override
    protected RecyclerView.Adapter initializeAdapter() {
        mMyScheduleDaysAdapter = new MyScheduleDaysAdapter(mStudentScheduleDays, this);
        return mMyScheduleDaysAdapter;
    }

    @Override
    public int getListView() {
        return R.id.schedule_days_list;
    }
}
