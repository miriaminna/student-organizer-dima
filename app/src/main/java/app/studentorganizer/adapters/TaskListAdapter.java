package app.studentorganizer.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import app.studentorganizer.OnTaskCheckedInListener;
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
        public ImageButton mCheckInButton;
        public ProgressBar mProgressBar;

        public ViewHolder(View v) {
            super(v);
            mCategoryIcon = (ImageButton)v.findViewById(R.id.category_icon);
            mTaskName = (TextView)v.findViewById(R.id.task_name);
            mCheckInDue = (TextView)v.findViewById(R.id.check_in_due);
            mTaskProgress = (TextView)v.findViewById(R.id.task_progress);
            mCheckInButton = (ImageButton)v.findViewById(R.id.check_in_button);
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
        holder.mCheckInButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        View dView = ((Activity) mContext)
                                .getLayoutInflater()
                                .inflate(R.layout.check_in_dialog, null);

                        final AlertDialog dialog = new AlertDialog.Builder(mContext)
                                .setCancelable(false)
                                .setView(dView)
                                .create();


                        SeekBar seekBar = (SeekBar) dView.findViewById(R.id.seek_bar);
                        final TextView textView =
                                (TextView) dView.findViewById(R.id.current_value);
                        final TextView leftValue =
                                (TextView) dView.findViewById(R.id.progress_value_left);
                        final TextView rightValue =
                                (TextView) dView.findViewById(R.id.progress_value_right);

                        leftValue.setText("0");
                        rightValue.setText(String.valueOf(task.getTarget()));
                        textView.setText(String.valueOf(task.getProgress()));

                        seekBar.setMax(task.getTarget());
                        seekBar.setLeft(0);
                        seekBar.setRight(task.getTarget());
                        seekBar.setProgress(task.getProgress());

                        seekBar.setOnSeekBarChangeListener(
                                new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                        textView.setText(String.valueOf(i));
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {

                                    }
                                }
                        );

                        final TextView saveButton =
                                (TextView) dView.findViewById(R.id.save_button);
                        final TextView cancelButton =
                                (TextView) dView.findViewById(R.id.cancel_button);

                        saveButton.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        task.setProgress(
                                                Integer.valueOf(textView.getText().toString()));
                                        ((OnTaskCheckedInListener) mContext).onTaskCheckedIn();
                                        dialog.dismiss();
                                    }
                                }
                        );

                        cancelButton.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.cancel();
                                    }
                                }
                        );

                        dialog.show();
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }
}
