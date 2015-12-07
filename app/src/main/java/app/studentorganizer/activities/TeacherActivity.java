package app.studentorganizer.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import app.studentorganizer.R;
import app.studentorganizer.com.TeacherType;
import app.studentorganizer.com.TeacherUtil;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.db.DatabaseManager;
import app.studentorganizer.entities.Teacher;

public class TeacherActivity extends BaseActivity {
    public static final String ID_EXTRA = "id";

    private int teacherId;
    protected Teacher mTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        teacherId = getIntent().getIntExtra(ID_EXTRA, 0);

        super.onCreate(savedInstanceState);

        ((ImageButton)findViewById(R.id.teacher_icon)).setImageResource(
                TeacherUtil.getTypeIcon(TeacherType.valueOf(mTeacher.getType())));
        ((TextView)findViewById(R.id.teacher_name)).setText(mTeacher.getName());
        ((TextView)findViewById(R.id.teacher_type)).setText(mTeacher.getType());

        ((TextView)findViewById(R.id.subject_names)).setText(getString(R.string.subjects)
                + ": " + mTeacher.getSubjects().toString());
        ((TextView)findViewById(R.id.contact_list)).setText(mTeacher.getContactsAsString());
    }

    @Override
    public int getContentView() {
        return R.layout.teacher_page;
    }

    @Override
    public void loadDataFromDB() {
        mTeacher = DBFactory.getFactory().getTeacherDAO().getByID(teacherId);
    }
}
