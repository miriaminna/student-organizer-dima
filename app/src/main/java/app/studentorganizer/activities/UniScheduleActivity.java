package app.studentorganizer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.adapters.UniScheduleListAdapter;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.UnivScheduleEntry;

public class UniScheduleActivity extends BaseListActivity {
    private UniScheduleListAdapter mUniScheduleListAdapter;
    private List<UnivScheduleEntry> mUnivScheduleEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mUnivScheduleEntries = new ArrayList<>();
        super.onCreate(savedInstanceState);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnivScheduleEntry entry = new UnivScheduleEntry();
                entry.setLessonNumber(mUnivScheduleEntries.size() + 1);
                DBFactory.getFactory().getUnivScheduleDAO().addEntity(entry);
                mUnivScheduleEntries.add(entry);
                mUniScheduleListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getContentView() {
        return R.layout.university_schedule;
    }

    @Override
    public void loadDataFromDB() {
        mUnivScheduleEntries.clear();
        mUnivScheduleEntries.addAll(DBFactory.getFactory().getUnivScheduleDAO().getAllEntities());
    }

    @Override
    protected RecyclerView.Adapter initializeAdapter() {
        mUniScheduleListAdapter = new UniScheduleListAdapter(mUnivScheduleEntries, this);
        return mUniScheduleListAdapter;
    }

    @Override
    public int getListView() {
        return R.id.schedule_list;
    }
}
