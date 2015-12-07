package app.studentorganizer.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.adapters.SubjectListAdapter;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.decorations.BaseItemDecoration;
import app.studentorganizer.entities.Subject;

public class SubjectListActivity extends BaseActivity {

    private DatabaseManager mDatabaseManager;
    private List<Subject> mSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected RecyclerView.Adapter initializeAdapter() {
        mSubjects = new ArrayList<>();
        return new SubjectListAdapter(this, mSubjects);
    }

    @Override
    protected RecyclerView.ItemDecoration initializeDecoration() {
        return new BaseItemDecoration(this, R.drawable.list_items_divider);
    }

    @Override
    protected void initializeDB() {
        mDatabaseManager = new DatabaseManager(this);
        mDatabaseManager.open();

        // FIXME : mocking database get
        mSubjects.addAll(mDatabaseManager.getAllSubjects());
    }

    @Override
    public int getContentView() {
        return R.layout.subject_list;
    }

    @Override
    public int getListView() {
        return R.id.subject_list;
    }
}
