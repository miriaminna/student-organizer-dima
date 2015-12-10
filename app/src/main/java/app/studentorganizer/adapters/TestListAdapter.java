package app.studentorganizer.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.activities.TestListActivity;
import app.studentorganizer.activities.TestActivity;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Test;

/**
 * Created by gideon on 09.12.15.
 */
public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.ViewHolder> {

    public interface OnDeleteListener {
        void onDelete(Long subjectId);
    }

    private Context mContext;
    private List<Test> mTests;
    private OnDeleteListener mListener;

    public TestListAdapter(Context context, List<Test> tests, OnDeleteListener listener) {
        this.mContext = context;
        this.mTests = tests;
        this.mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageButton mCategoryIcon;
        public TextView mSubject;
        public TextView mType;
        public TextView mDate;
        public ImageButton mDeleteButton;

        public Long mTestId;

        public ViewHolder(View itemView) {
            super(itemView);

            mCategoryIcon = (ImageButton) itemView.findViewById(R.id.category_icon);
            mCategoryIcon.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, TestActivity.class);
                            intent.putExtra(TestActivity.TEST_ID_EXTRA, mTestId);
                            mContext.startActivity(intent);
                        }
                    }
            );
            mSubject = (TextView) itemView.findViewById(R.id.subject);
            mType = (TextView) itemView.findViewById(R.id.type);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mDeleteButton = (ImageButton) itemView.findViewById(R.id.test_delete);

            if (mListener != null) {
                mDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TestListAdapter.this.mListener.onDelete(mTestId);
                    }
                });
            } else {
                mDeleteButton.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.subject_list_item,
                parent,
                false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Test test = mTests.get(position);

        holder.mTestId = test.getId();
        holder.mSubject.setText(DBFactory.getFactory().getSubjectDAO().getByID(test.getSubjectId()).getName());
        holder.mType.setText(test.getTestType().toString());
        holder.mDate.setText(test.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return mTests.size();
    }
}
