package app.studentorganizer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import app.studentorganizer.R;
import app.studentorganizer.decorations.BaseItemDecoration;

public abstract class BaseListActivity extends BaseActivity {
    protected RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeRecyclerView();
    }

    protected void initializeRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(getListView());
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(initializeAdapter());
        mRecyclerView.addItemDecoration(initializeDecoration());
    }

    protected abstract RecyclerView.Adapter initializeAdapter();
    protected RecyclerView.ItemDecoration initializeDecoration() {
        return new BaseItemDecoration(this, R.drawable.list_items_divider);
    }
    public abstract int getListView();
}
