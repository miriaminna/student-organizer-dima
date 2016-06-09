package app.studentorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.adapters.SubjectListAdapter;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.decorations.BaseItemDecoration;
import app.studentorganizer.entities.Subject;

public class SubjectListActivity extends BaseListActivity implements SubjectListAdapter.OnDeleteListener {

    private List<Subject> mSubjects;
    private SubjectListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSubjects = new ArrayList<>();
        super.onCreate(savedInstanceState);

        findViewById(R.id.fab).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SubjectListActivity.this, EditSubjectActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDataFromDB();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected RecyclerView.Adapter initializeAdapter() {
        mAdapter = new SubjectListAdapter(this, mSubjects, this);
        return mAdapter;
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
        mSubjects.clear();
        mSubjects.addAll(DBFactory.getFactory().getSubjectDAO().getAllEntities());
    }

    @Override
    public int getListView() {
        return R.id.subject_list;
    }


    @Override
    public void onDelete(Long subjectId) {
        DBFactory.getFactory().getSubjectDAO().deleteEntity(subjectId);
        DBFactory.getFactory().getTaskDAO().deleteBySubjectId(subjectId);
        DBFactory.getFactory().getStudentScheduleDAO().deleteBySubjectId(subjectId);

        loadDataFromDB();
        mAdapter.notifyDataSetChanged();
    }
}
