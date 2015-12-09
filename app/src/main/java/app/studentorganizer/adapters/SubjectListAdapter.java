package app.studentorganizer.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.activities.SubjectActivity;
import app.studentorganizer.com.SubjectCommon;
import app.studentorganizer.entities.Subject;

/**
 * Created by henko on 07.12.15.
 */
public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.ViewHolder> {

    public interface OnDeleteListener {
        void onDelete(Long subjectId);
    }

    private Context mContext;
    private List<Subject> mSubjects;
    private OnDeleteListener mListener;

    public SubjectListAdapter(Context context, List<Subject> subjects, OnDeleteListener listener) {
        this.mContext = context;
        this.mSubjects = subjects;
        this.mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageButton mCategoryIcon;
        public TextView mName;
        public TextView mType;
        public ImageButton mDeleteButton;

        public Long mSubjectId;

        public ViewHolder(View itemView) {
            super(itemView);

            mCategoryIcon = (ImageButton) itemView.findViewById(R.id.category_icon);
            mCategoryIcon.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, SubjectActivity.class);
                            intent.putExtra(SubjectCommon.SUBJECT_ID_EXTRA, mSubjectId);
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
