package app.studentorganizer.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.adapters.TeacherSelectAdapter;
import app.studentorganizer.com.ColorTag;
import app.studentorganizer.com.SubjectType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Subject;
import app.studentorganizer.entities.Teacher;
import app.studentorganizer.util.DateUtils;

public class EditSubjectActivity extends BaseActivity {

    protected TeacherSelector mTeacherSelector;
    protected SubjectType mSubjectType;
    protected ColorTag mColorTag;
    protected Context mContext;


    protected TextView mStartDateText;
    protected TextView mEndDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // sorry mama
        mContext = this;

        mSubjectType = SubjectType.CREDIT;
        mColorTag = ColorTag.ORANGE;

        findViewById(R.id.color_tag).setOnClickListener(new ColorPicker());

        Spinner subjectType = ((Spinner)findViewById(R.id.subject_type));
        String[] s = new String[] {SubjectType.CREDIT.name(), SubjectType.EXAM.name() };
        subjectType.setAdapter(new ArrayAdapter<>(mContext, R.layout.spinner_item, s));

        findViewById(R.id.save_button).setOnClickListener(new SubjectSaver());

        findViewById(R.id.teacher_select_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTeacherSelector == null) {
                    mTeacherSelector = new TeacherSelector
                            (EditSubjectActivity.this,
                                    DBFactory.getFactory().getTeacherDAO().getAllEntities(),
                                    findViewById(R.id.teacher_view));
                }
                mTeacherSelector.showDialog();
            }
        });

        mStartDateText = (TextView) findViewById(R.id.subject_start_date);
        mEndDateText = (TextView) findViewById(R.id.subject_end_date);
        View startDatePicker = findViewById(R.id.subject_start_date_button);
        View endDatePicker = findViewById(R.id.subject_end_date_button);

        initializeDatePicker(mStartDateText, startDatePicker);
        initializeDatePicker(mEndDateText, endDatePicker);
    }

    private void initializeDatePicker(final TextView dateText, View pickerButton) {
        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener startListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (dateText != null) {
                            dateText.setText(DateUtils.dateToString(year, monthOfYear + 1, dayOfMonth));
                        }
                    }
                };
                new DatePickerDialog(
                        EditSubjectActivity.this,
                        startListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }

    @Override
    public int getContentView() {
        return R.layout.subject_edit;
    }

    @Override
    public void loadDataFromDB() {
    }

    private class TeacherSelector implements TeacherSelectAdapter.OnTeacherSelectListener {

        private AlertDialog mDialog;
        private Long mTeacherId = -1L;
        private View mTeacherView;

        public TeacherSelector(Activity activity, List<Teacher> teachers, View teacherView) {
            this.mTeacherView = teacherView;
            View view = activity.getLayoutInflater().inflate(
                    R.layout.teacher_select_dialog, null
            );

            mDialog = new AlertDialog.Builder(activity).
                    setCancelable(true).
                    setView(view).
                    create();

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.teacher_list);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            recyclerView.setLayoutManager(linearLayoutManager);

            TeacherSelectAdapter adapter = new TeacherSelectAdapter(teachers, activity, this);
            recyclerView.setAdapter(adapter);
        }

        public void showDialog() {
            mDialog.show();
        }

        @Override
        public void onTeacherSelected(Long teacherId) {
            mDialog.dismiss();
            this.mTeacherId = teacherId;

            Teacher teacher = DBFactory.getFactory().getTeacherDAO().getByID(teacherId);

            if (mTeacherView != null) {
                ((ImageButton) mTeacherView.findViewById(R.id.teacher_icon)).
                        setImageResource(teacher.getType().getDrawable());
                ((TextView) mTeacherView.findViewById(R.id.teacher_name)).
                        setText(teacher.getName());
                ((TextView) mTeacherView.findViewById(R.id.teacher_type)).
                        setText(teacher.getType().toString());

                // Disable teacher deletion from subject edit activity
                mTeacherView.findViewById(R.id.button_delete).setVisibility(View.GONE);
            }
        }

        public Long getTeacherId() {
            return mTeacherId;
        }
    }

    private class SubjectSaver implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Subject subject = new Subject();
            subject.setName(((TextView) findViewById(R.id.name)).getText().toString());
            subject.setType(
                    SubjectType.valueOf(((Spinner)findViewById(R.id.subject_type)).getSelectedItem().toString()));
            subject.setColorTag(mColorTag);

            // fixme : debug
            // TODO : validate so that teacher always would be selected
            if (mTeacherSelector != null) {
                subject.setTeacherId(EditSubjectActivity.this.mTeacherSelector.getTeacherId());
            }

            subject.setStartDate(LocalDate.parse(mStartDateText.getText().toString()));
            subject.setEndDate(LocalDate.parse(mEndDateText.getText().toString()));

            DBFactory.getFactory().getSubjectDAO().addEntity(subject);

            finish();
        }
    }

    private class ColorPicker implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final ColorTag old = mColorTag;
            View dialogView = getLayoutInflater().inflate(R.layout.pick_color_tag_dialog, null);

            final ImageButton orange = (ImageButton)dialogView.findViewById(R.id.orange);
            final ImageButton green = (ImageButton)dialogView.findViewById(R.id.green);
            final ImageButton blue = (ImageButton)dialogView.findViewById(R.id.blue);

            orange.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mColorTag = ColorTag.ORANGE;
                            onStateChanged(orange, green, blue);
                        }
                    }
            );

            green.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mColorTag = ColorTag.GREEN;
                            onStateChanged(orange, green, blue);
                        }
                    }
            );

            blue.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mColorTag = ColorTag.BLUE;
                            onStateChanged(orange, green, blue);
                        }
                    }
            );

            TextView cancelButton = (TextView) dialogView.findViewById(R.id.cancel_button);
            TextView saveButton = (TextView) dialogView.findViewById(R.id.save_button);

            final AlertDialog dialog =  new AlertDialog.Builder(mContext)
                    .setCancelable(false)
                    .create();

            cancelButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mColorTag = old;
                            dialog.cancel();
                        }
                    }
            );

            saveButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ImageButton colorTag = ((ImageButton)findViewById(R.id.color_tag));
                            colorTag.setBackgroundResource(mColorTag.getDrawableId());
                            dialog.dismiss();
                        }
                    }
            );

            dialog.setView(dialogView);
            dialog.show();
        }

        private void onStateChanged(
                final ImageButton orange,
                final ImageButton green,
                final ImageButton blue) {
            orange.setBackgroundResource(R.drawable.round_shape);
            green.setBackgroundResource(R.drawable.round_shape);
            blue.setBackgroundResource(R.drawable.round_shape);
            switch (mColorTag) {
                case ORANGE:
                    orange.setBackgroundResource(R.drawable.round_shape_selected);
                    break;
                case GREEN:
                    green.setBackgroundResource(R.drawable.round_shape_selected);
                    break;
                case BLUE:
                    blue.setBackgroundResource(R.drawable.round_shape_selected);
                    break;
            }
        }
    }
}
