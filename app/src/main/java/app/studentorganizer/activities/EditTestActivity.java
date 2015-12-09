package app.studentorganizer.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.LocalDate;

import app.studentorganizer.R;
import app.studentorganizer.com.SubjectCommon;
import app.studentorganizer.com.TestType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Task;
import app.studentorganizer.entities.Test;

public class EditTestActivity extends BaseActivity {
    public long mSubjectId;

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
                        test.setPoints(Integer.valueOf(((TextView) findViewById(R.id.test_points)).getText().toString()));
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


    }

    @Override
    public int getContentView() {
        return R.layout.test_edit;
    }

    @Override
    public void loadDataFromDB() {
    }
}
