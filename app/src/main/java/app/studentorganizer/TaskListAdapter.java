package app.studentorganizer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.com.TaskUtil;
import app.studentorganizer.entities.Task;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private Context mContext;
    private List<Task> mTasks;

    public TaskListAdapter(List<Task> tasks, Context context) {
        mContext = context;
        mTasks = tasks;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton mCategoryIcon;
        public TextView mTaskName;
        public TextView mCheckInDue;
        public TextView mTaskProgress;
        //public ImageButton mCheckInButton;
        public ProgressBar mProgressBar;

        public ViewHolder(View v) {
            super(v);
            mCategoryIcon = (ImageButton)v.findViewById(R.id.category_icon);
            mTaskName = (TextView)v.findViewById(R.id.task_name);
            mCheckInDue = (TextView)v.findViewById(R.id.check_in_due);
            mTaskProgress = (TextView)v.findViewById(R.id.task_progress);
            //mCheckInButton = (ImageButton)v.findViewById(R.id.check_in_button);
            mProgressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        }
    }

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item,
                parent,
                false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task task = mTasks.get(position);

        holder.mCategoryIcon.setImageResource(
                TaskUtil.getCategoryIconId(task.getSubject().getColorTag()));
        holder.mTaskName.setText(task.getName());
        holder.mCheckInDue.setText(TaskUtil.getCheckInDue(task.getDeadline(), mContext));
        holder.mTaskProgress.setText(
                String.valueOf(task.getProgress() + " / " + task.getTarget()));
        holder.mProgressBar.setProgress(
                (int) (((1.0 * task.getProgress()) / task.getTarget()) * 100));
        holder.mProgressBar.setProgressDrawable(
                mContext.getDrawable(R.drawable.progress_bar_drawable));
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }
}
