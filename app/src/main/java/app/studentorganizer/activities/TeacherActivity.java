package app.studentorganizer.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import app.studentorganizer.util.Pair;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.adapters.SubjectListAdapter;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.decorations.BaseItemDecoration;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Teacher;

public class TeacherActivity extends BaseListActivity {
    public static final String ID_EXTRA = "id";

    private Long mTeacherId;
    protected Teacher mTeacher;
    private List<Subject> mSubjects;
    private SubjectListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTeacherId = getIntent().getLongExtra(ID_EXTRA, 0);

        super.onCreate(savedInstanceState);

        ((ImageButton)findViewById(R.id.teacher_icon)).setImageResource(
                mTeacher.getType().getDrawable());
        ((TextView)findViewById(R.id.teacher_name)).setText(mTeacher.getName());
        ((TextView)findViewById(R.id.teacher_type)).setText(mTeacher.getType().toString());
        ((TextView)findViewById(R.id.contact_list)).setText(mTeacher.getContactsAsString());

        findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeacher.addContact(new Pair<>(
                        ((TextView)findViewById(R.id.contact_name)).getText().toString(),
                        ((TextView)findViewById(R.id.contact_value)).getText().toString()
                ));
                DBFactory.getFactory().getTeacherDAO().updateEntity(mTeacher);
                ((TextView)findViewById(R.id.contact_list)).setText(mTeacher.getContactsAsString());
            }
        });
        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeacher.removeContact(new Pair<>(
                        ((TextView)findViewById(R.id.contact_name)).getText().toString(),
                        ((TextView)findViewById(R.id.contact_value)).getText().toString()
                ));
                DBFactory.getFactory().getTeacherDAO().updateEntity(mTeacher);
                ((TextView)findViewById(R.id.contact_list)).setText(mTeacher.getContactsAsString());
            }
        });
    }

    @Override
    protected RecyclerView.Adapter initializeAdapter() {
        mAdapter = new SubjectListAdapter(this, mSubjects, null);
        return mAdapter;
    }

    @Override
    protected RecyclerView.ItemDecoration initializeDecoration() {
        return new BaseItemDecoration(this, R.drawable.list_items_divider);
    }

    @Override
    public int getListView() {
        return R.id.subject_list;
    }

    @Override
    public int getContentView() {
        return R.layout.teacher_page;
    }

    @Override
    public void loadDataFromDB() {
        mTeacher = DBFactory.getFactory().getTeacherDAO().getByID(mTeacherId);
        mSubjects = DBFactory.getFactory().getSubjectDAO()
                .getByTeacherId(mTeacherId);
    }
}
