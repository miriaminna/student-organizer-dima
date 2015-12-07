package app.studentorganizer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.com.TeacherType;
import app.studentorganizer.entities.Teacher;

public class NewTeacherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> items = new ArrayList<>();
        for (TeacherType t : TeacherType.values()) {
            items.add(t.toString());
        }
        ((Spinner)findViewById(R.id.teacher_type)).setAdapter(
                new ArrayAdapter<>(this, R.layout.spinner_item, items)
        );
    }

    @Override
    public int getContentView() {
        return R.layout.new_teacher_layout;
    }

    @Override
    public void loadDataFromDB() {
    }

    public void onTeacherAdd(View v) {
        Teacher teacher = new Teacher();
        teacher.setName(((TextView)findViewById(R.id.teacher_name)).getText().toString());
        teacher.setType(((Spinner)findViewById(R.id.teacher_type)).getSelectedItem().toString());
//        mDatabaseManager.addTeacher(teacher);
        finish();
    }
}