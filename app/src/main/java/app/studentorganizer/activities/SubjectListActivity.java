package app.studentorganizer.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.adapters.SubjectListAdapter;
import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.decorations.BaseItemDecoration;
import app.studentorganizer.entities.Subject;

public class SubjectListActivity extends BaseListActivity {

    private List<Subject> mSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSubjects = new ArrayList<>();
        super.onCreate(savedInstanceState);
        findViewById(R.id.fab).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("FUCKING SUCCESS!");
                    }
                }
        );
    }

    @Override
    protected RecyclerView.Adapter initializeAdapter() {
        return new SubjectListAdapter(this, mSubjects);
    }

    @Override
    protected RecyclerView.ItemDecoration initializeDecoration() {
        return new BaseItemDecoration(this, R.drawable.list_items_divider);
    }

    @Override
    public int getContentView() {
        return R.layout.subject_list;
    }

    @Override
    public void loadDataFromDB() {
        mSubjects.addAll(mDatabaseManager.getAllSubjects());
    }

    @Override
    public int getListView() {
        return R.id.subject_list;
    }
}
