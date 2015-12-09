package app.studentorganizer.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.decorations.BaseItemDecoration;
import app.studentorganizer.entities.StudentScheduleEntry;

/**
 * Created by Vitalii on 09-Dec-15.
 */
public class MyScheduleDaysAdapter extends RecyclerView.Adapter<MyScheduleDaysAdapter.ViewHolder> {
    protected List<List<StudentScheduleEntry>> mStudentScheduleDays;
    protected Context mContext;

    public MyScheduleDaysAdapter(List<List<StudentScheduleEntry>> studentScheduleDays, Context context) {
        mStudentScheduleDays = studentScheduleDays;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.my_schedule_day,
                parent,
                false
        );
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final List<StudentScheduleEntry> daySchedule = mStudentScheduleDays.get(position);

        holder.mMonthTitle.setText(
                mContext.getResources().getStringArray(R.array.day_names)[position]);
        holder.mRecyclerView.setAdapter(new MyScheduleListAdapter(daySchedule, mContext));
        holder.mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mStudentScheduleDays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mMonthTitle;
        public RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);

            mMonthTitle = (TextView) itemView.findViewById(R.id.month_title);

            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.schedule_list);
            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.addItemDecoration(
                    new BaseItemDecoration(mContext, R.drawable.list_items_divider));
        }
    }
}
