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
import app.studentorganizer.activities.SubjectActivity;
import app.studentorganizer.entities.Subject;

/**
 * Created by henko on 07.12.15.
 */
public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.ViewHolder> {

    private Context mContext;
    private List<Subject> mSubjects;

    public SubjectListAdapter(Context context, List<Subject> subjects) {
        this.mContext = context;
        this.mSubjects = subjects;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageButton mCategoryIcon;
        public TextView mName;
        public TextView mType;

        public ViewHolder(View itemView) {
            super(itemView);

            mCategoryIcon = (ImageButton) itemView.findViewById(R.id.category_icon);
            mName = (TextView) itemView.findViewById(R.id.name);
            mType = (TextView) itemView.findViewById(R.id.type);
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

        holder.mName.setText(subject.getName());
        holder.mType.setText(subject.getType().toString());
        // FIXME : not getting color tags out of DB
        //holder.mCategoryIcon.setImageResource(subject.getColorTag().getDrawableId());
        holder.mCategoryIcon.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, SubjectActivity.class);
                        intent.putExtra(SubjectActivity.SUBJECT_ID_EXTRA, subject.getId());
                        mContext.startActivity(intent);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }
}
