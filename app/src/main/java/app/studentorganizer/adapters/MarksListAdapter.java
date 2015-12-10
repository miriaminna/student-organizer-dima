package app.studentorganizer.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.activities.SubjectActivity;
import app.studentorganizer.com.SubjectCommon;
import app.studentorganizer.entities.Subject;

/**
 * Created by Vitalii on 09-Dec-15.
 */
public class MarksListAdapter extends RecyclerView.Adapter<MarksListAdapter.ViewHolder> {
    protected List<Pair<Subject, Double>> mMarks;
    protected Context mContext;

    public MarksListAdapter(List<Pair<Subject, Double>> marks, Context context) {
        this.mMarks = marks;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.marks_list_semester_item,
                parent,
                false
        );
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pair<Subject, Double> mark = mMarks.get(position);

        int bg_resource = mark.first.getEndDate().isBefore(new LocalDate())
                ? R.drawable.square_boards_green
                : R.drawable.square_boards_orange;

        holder.mType.setText(mark.first.getType().toString().substring(0, 1));
        holder.mType.setBackgroundResource(bg_resource);

        holder.mName.setText(mark.first.getName());
        holder.mName.setBackgroundResource(bg_resource);

        holder.mMark.setText(String.format("%.2f", mark.second));
        holder.mMark.setBackgroundResource(bg_resource);

        holder.mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SubjectActivity.class);
                intent.putExtra(SubjectCommon.SUBJECT_ID_EXTRA, mark.first.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMarks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mType;
        public TextView mName;
        public TextView mMark;

        public ViewHolder(View itemView) {
            super(itemView);

            mType = (TextView) itemView.findViewById(R.id.type);
            mName = (TextView) itemView.findViewById(R.id.name);
            mMark = (TextView) itemView.findViewById(R.id.mark);
        }
    }
}
