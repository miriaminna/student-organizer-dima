package app.studentorganizer.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.activities.SubjectActivity;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.StudentScheduleEntry;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.UnivScheduleEntry;

/**
 * Created by Vitalii on 09-Dec-15.
 */
public class MyScheduleListAdapter extends RecyclerView.Adapter<MyScheduleListAdapter.ViewHolder> {
    protected List<StudentScheduleEntry> mStudentScheduleEntries;
    protected Context mContext;

    public MyScheduleListAdapter(List<StudentScheduleEntry> studentScheduleEntries, Context context) {
        this.mStudentScheduleEntries = studentScheduleEntries;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.my_schedule_item,
                parent,
                false
        );
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final StudentScheduleEntry scheduleEntry = mStudentScheduleEntries.get(position);
        final Subject subject = DBFactory.getFactory().getSubjectDAO()
                .getByID(scheduleEntry.getSubjectId());
        final UnivScheduleEntry univScheduleEntry = DBFactory.getFactory().getUnivScheduleDAO()
                .getByID(scheduleEntry.getUnivScheduleEntryId());

        holder.mClassroom.setText(String.format("%d", scheduleEntry.getClassroom()));
        if (subject != null) {
            holder.mName.setText(subject.getName());
            holder.mName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SubjectActivity.class);
                    intent.putExtra(SubjectActivity.SUBJECT_ID_EXTRA, subject.getId());
                    mContext.startActivity(intent);
                }
            });
        }
        if (univScheduleEntry != null) {
            holder.mPair.setText(String.format("%d", univScheduleEntry.getLessonNumber()));
            holder.mTime.setText(String.format("%s-%s",
                    univScheduleEntry.getStart().toString("HH:mm"),
                    univScheduleEntry.getEnd().toString("HH:mm")));
        }

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBFactory.getFactory().getStudentScheduleDAO().deleteEntity(scheduleEntry.getId());
                mStudentScheduleEntries.remove(scheduleEntry);
                MyScheduleListAdapter.this.notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mStudentScheduleEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mPair;
        public TextView mTime;
        public TextView mName;
        public TextView mClassroom;
        public ImageButton mDeleteButton;

        public ViewHolder(View itemView) {
            super(itemView);

            mPair = (TextView) itemView.findViewById(R.id.pair);
            mTime = (TextView) itemView.findViewById(R.id.time);
            mName = (TextView) itemView.findViewById(R.id.name);
            mClassroom = (TextView) itemView.findViewById(R.id.classroom);
            mDeleteButton = (ImageButton) itemView.findViewById(R.id.button_delete);
        }
    }
}
