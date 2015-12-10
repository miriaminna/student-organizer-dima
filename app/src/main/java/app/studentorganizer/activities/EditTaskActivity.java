package app.studentorganizer.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.util.Calendar;

import app.studentorganizer.R;
import app.studentorganizer.com.SubjectCommon;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Task;

public class EditTaskActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {

    protected long mSubjectId;

    protected TextView mDateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSubjectId = getIntent().getLongExtra(
                SubjectCommon.SUBJECT_ID_EXTRA,
                SubjectCommon.DEFAULT_SUBJECT_ID);

        if (mSubjectId == -1) {
            finish();
        } else {
            initializeView();
        }
    }

    private void initializeView() {

        findViewById(R.id.save_button).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Task task = new Task();
                            task.setName(((TextView) findViewById(R.id.task_name)).getText().toString());
                            task.setTarget(
                                    Integer.valueOf(((TextView) findViewById(R.id.task_target)).getText().toString()));
                            task.setProgress(0);
                            task.setSubjectId(mSubjectId);
                            task.setPoints(
                                    Double.valueOf(((TextView) findViewById(R.id.task_points)).getText().toString()));
                            task.setDeadline(
                                    LocalDate.parse(((TextView) findViewById(R.id.task_deadline)).getText().toString()));

                            System.out.println("Adding task");
                            DBFactory.getFactory().getTaskDAO().addEntity(task);


                            finish();
                         }
                    });
        findViewById(R.id.cancel_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        mDateView = (TextView) findViewById(R.id.task_deadline);

        // Initially set current date to date view
        Calendar calendar = Calendar.getInstance();
        setDateView(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));

        // Start calendar dialog
        findViewById(R.id.start_date_pick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(
                        EditTaskActivity.this,
                        EditTaskActivity.this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }

    @Override
    public int getContentView() {
        return R.layout.task_edit_layout;
    }

    @Override
    public void loadDataFromDB() {
    }

    // Called when date in DatePickerDialog is chosen
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // Update date in view
        setDateView(year, monthOfYear + 1, dayOfMonth);
    }

    // Updates date view if the view is initialized
    protected void setDateView(int year, int monthOfYear, int dayOfMonth) {
        if (mDateView != null) {
            mDateView.setText(
                    new StringBuilder().
                            append(year).append('-').
                            append(monthOfYear).append('-').
                            append(dayOfMonth)
            );
        }
    }
}
