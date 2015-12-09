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
import app.studentorganizer.OnTestCheckedInListener;
import app.studentorganizer.R;
import app.studentorganizer.com.TaskUtil;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Test;

public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.ViewHolder> {

    private Context mContext;
    private List<Test> mTests;

    public TestListAdapter(List<Test> tests, Context context) {
        mContext = context;
        mTests = tests;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton mCategoryIcon;
        public TextView mTestName;
        public TextView mTestType;
        public TextView mTestProgress;
        public ImageButton mCheckInButton;
        public ProgressBar mProgressBar;

        public ViewHolder(View v) {
            super(v);
            mCategoryIcon = (ImageButton)v.findViewById(R.id.category_icon);
            mTestName = (TextView)v.findViewById(R.id.test_name);
            mTestType = (TextView)v.findViewById(R.id.test_type);
            mTestProgress = (TextView)v.findViewById(R.id.task_progress);
            mCheckInButton = (ImageButton)v.findViewById(R.id.check_in_button);
            mProgressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        }
    }

    @Override
    public TestListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.test_item,
                parent,
                false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Test test = mTests.get(position);

        // TODO: Fetch subject
//        holder.mCategoryIcon.setImageResource(
//                TaskUtil.getCategoryIconId(task.getSubject().getColorTag()));
        holder.mTestName.setText(
                DBFactory.getFactory().getSubjectDAO().getByID(test.getSubjectId()).getName()
                + " test");
        holder.mTestType.setText(test.getTestType().name() + ", " + test.getDate().toString());
        holder.mTestProgress.setText(
                String.valueOf(test.getResult() + " / " + test.getPoints()));
        holder.mProgressBar.setProgress(
                (int) (((1.0 * test.getResult()) / test.getPoints()) * 100));
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
                        rightValue.setText(String.valueOf(test.getPoints()));
                        textView.setText(String.valueOf(test.getResult()));

                        seekBar.setMax(test.getPoints());
                        seekBar.setLeft(0);
                        seekBar.setRight(test.getPoints());
                        seekBar.setProgress(test.getResult());

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
                                        test.setResult(Integer.valueOf(textView.getText().toString()));
                                        ((OnTestCheckedInListener) mContext).onTestCheckedIn(test);
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
        return mTests.size();
    }
}
