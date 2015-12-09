package app.studentorganizer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.com.DayConstants;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.StudentScheduleEntry;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.UnivScheduleEntry;

public class EditStudentScheduleActivity extends BaseActivity {
    protected List<Subject> mSubjects;
    protected List<UnivScheduleEntry> mUnivSchedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentScheduleEntry studentScheduleEntry = new StudentScheduleEntry();

                studentScheduleEntry.setSubjectId(
                        ((Subject) ((Spinner) findViewById(R.id.spinnerSubjects)).getSelectedItem()).getId()
                );

                studentScheduleEntry.setUnivScheduleEntryId(
                        ((UnivScheduleEntry) ((Spinner) findViewById(R.id.spinnerPairs)).getSelectedItem()).getId()
                );

                studentScheduleEntry.setDay(DayConstants.getConstant(
                        getResources().getStringArray(R.array.day_names),
                        ((Spinner) findViewById(R.id.spinnerDays)).getSelectedItem().toString()
                ));

                studentScheduleEntry.setClassroom(Integer.parseInt(
                        ((TextView) findViewById(R.id.classroom)).getText().toString()
                ));

                DBFactory.getFactory().getStudentScheduleDAO().addEntity(studentScheduleEntry);

                finish();
            }
        });

        ((Spinner)findViewById(R.id.spinnerPairs)).setAdapter(
                new ArrayAdapter<>(this, R.layout.spinner_item, mUnivSchedules)
        );

        ((Spinner)findViewById(R.id.spinnerSubjects)).setAdapter(
                new ArrayAdapter<>(this, R.layout.spinner_item, mSubjects)
        );

        ((Spinner)findViewById(R.id.spinnerDays)).setAdapter(
                new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.day_names))
        );
    }

    @Override
    public int getContentView() {
        return R.layout.my_schedule_edit;
    }

    @Override
    public void loadDataFromDB() {
        mSubjects = DBFactory.getFactory().getSubjectDAO().getAllEntities();
        mUnivSchedules = DBFactory.getFactory().getUnivScheduleDAO().getAllEntities();
    }
}
