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
import app.studentorganizer.com.TeacherType;
import app.studentorganizer.com.TestType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Test;
import app.studentorganizer.util.DateUtils;

public class EditTestActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {
    public long mSubjectId;

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
                        Test test = new Test();
                        test.setTestType(TestType.valueOf(((Spinner) findViewById(R.id.test_type)).getSelectedItem().toString()));
                        test.setResult(0);
                        test.setSubjectId(mSubjectId);
                        test.setPoints(Math.max(1,
                                Integer.valueOf(((TextView) findViewById(R.id.test_points)).getText().toString())));
                        test.setDate(
                                LocalDate.parse(((TextView) findViewById(R.id.test_date)).getText().toString()));

                        System.out.println("Adding test");
                        DBFactory.getFactory().getTestDAO().addEntity(test);

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

        ((Spinner)findViewById(R.id.test_type)).setAdapter(
                new ArrayAdapter<>(this, R.layout.spinner_item, TestType.values())
        );

        mDateView = (TextView) findViewById(R.id.test_date);

        // Initially set current date to date view
        Calendar calendar = Calendar.getInstance();
        setDateView(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));

        // Start calendar dialog
        findViewById(R.id.start_date_pick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(
                        EditTestActivity.this,
                        EditTestActivity.this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }

    @Override
    public int getContentView() {
        return R.layout.test_edit;
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
            mDateView.setText(DateUtils.dateToString(year, monthOfYear, dayOfMonth));
        }
    }
}
