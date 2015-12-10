package app.studentorganizer.activities;

/**
 * Created by gideon on 09.12.15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.adapters.TestListAdapter;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.decorations.BaseItemDecoration;
import app.studentorganizer.entities.Test;

public class TestListActivity extends BaseListActivity implements TestListAdapter.OnDeleteListener {

    private List<Test> mTests;
    private TestListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTests = new ArrayList<>();
        super.onCreate(savedInstanceState);

        findViewById(R.id.fab).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TestListActivity.this, EditT);
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
        mAdapter = new TestListAdapter(this, mTests, this);
        return mAdapter;
    }

    @Override
    protected RecyclerView.ItemDecoration initializeDecoration() {
        return new BaseItemDecoration(this, R.drawable.list_items_divider);
    }

    @Override
    public int getContentView() {
        return R.layout.test_list;
    }

    @Override
    public void loadDataFromDB() {
        mTests.clear();
        mTests.addAll(DBFactory.getFactory().getTestDAO().getAllEntities());
    }

    @Override
    public int getListView() {
        return R.id.test_list;
    }


    @Override
    public void onDelete(Long subjectId) {
        DBFactory.getFactory().getSubjectDAO().deleteEntity(subjectId);
        loadDataFromDB();
        mAdapter.notifyDataSetChanged();
    }
}
