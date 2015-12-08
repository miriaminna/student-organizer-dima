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
import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Subject;

public class EditSubjectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> subjectTypes = new ArrayList<>();
        for (SubjectType type : SubjectType.values()) {
            subjectTypes.add(type.toString());
        }
        ((Spinner) findViewById(R.id.subject_type)).setAdapter(
                new ArrayAdapter<>(this, R.layout.spinner_item, subjectTypes)
        );
        List<String> subjectTags = new ArrayList<>();
        for (ColorTag tag : ColorTag.values()) {
            subjectTags.add(tag.toString());
        }
        ((Spinner) findViewById(R.id.subject_color_tag)).setAdapter(
                new ArrayAdapter<>(this, R.layout.spinner_item, subjectTags)
        );

        findViewById(R.id.save_button).setOnClickListener(new SubjectSaver());
    }

    @Override
    public int getContentView() {
        return R.layout.subject_edit;
    }

    @Override
    public void loadDataFromDB() {
    }

    private class SubjectSaver implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Subject subject = new Subject();
            subject.setName(((TextView) findViewById(R.id.name)).getText().toString());
            subject.setType(SubjectType.valueOf(
                    ((Spinner) findViewById(R.id.subject_type)).
                            getSelectedItem().toString()));
            subject.setColorTag(ColorTag.valueOf(
                    ((Spinner) findViewById(R.id.subject_color_tag)).
                            getSelectedItem().toString()));

            DBFactory.getFactory().getSubjectDAO().addEntity(subject);

            finish();
        }
    }
}
