package app.studentorganizer.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.LocalDate;

import app.studentorganizer.R;
import app.studentorganizer.com.SubjectCommon;
import app.studentorganizer.com.TeacherType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Task;

public class EditTaskActivity extends BaseActivity {
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


    }

    @Override
    public int getContentView() {
        return R.layout.task_edit_layout;
    }

    @Override
    public void loadDataFromDB() {
    }
}
