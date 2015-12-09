package app.studentorganizer.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.com.TeacherType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Teacher;

public class EditTeacherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher teacher = new Teacher();
                teacher.setName(((TextView)findViewById(R.id.teacher_name)).getText().toString());
                teacher.setType(TeacherType.valueOf(((Spinner) findViewById(R.id.teacher_type)).getSelectedItem().toString()));

                System.out.println("Adding teacher");
                DBFactory.getFactory().getTeacherDAO().addEntity(teacher);

                finish();
            }
        });

        ((Spinner)findViewById(R.id.teacher_type)).setAdapter(
                new ArrayAdapter<>(this, R.layout.spinner_item, TeacherType.values())
        );
    }

    @Override
    public int getContentView() {
        return R.layout.teacher_edit_layout;
    }

    @Override
    public void loadDataFromDB() {
    }

    public void onTeacherAdd(View v) {
    }
}
