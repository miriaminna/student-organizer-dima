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

    public TestListAdapter(Context context, List<Test> subjects, OnDeleteListener listener) {
        this.mContext = context;
        this.mTests = subjects;
        this.mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageButton mCategoryIcon;
        public TextView mName;
        public TextView mType;
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
                            intent.putExtra(TestListActivity.SUBJECT_ID_EXTRA, mSubjectId);
                            mContext.startActivity(intent);
                        }
                    }
            );
            mName = (TextView) itemView.findViewById(R.id.name);
            mType = (TextView) itemView.findViewById(R.id.type);
            mDeleteButton = (ImageButton) itemView.findViewById(R.id.subject_delete);

            if (mListener != null) {
                mDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SubjectListAdapter.this.mListener.onDelete(mSubjectId);
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
        final Subject subject = mSubjects.get(position);

        holder.mSubjectId = subject.getId();
        holder.mName.setText(subject.getName());
        holder.mType.setText(subject.getType().toString());
        holder.mCategoryIcon.setImageResource(subject.getColorTag().getDrawableId());
    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }
}
