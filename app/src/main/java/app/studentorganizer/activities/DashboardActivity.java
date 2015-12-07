package app.studentorganizer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import app.studentorganizer.R;

public class DashboardActivity extends BaseActivity {
    @Override
    public int getContentView() {
        return R.layout.activity_dashboard;
    }

    @Override
    public void loadDataFromDB() {
        // no data for dashboard
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeButtons();
    }

    private void initializeButtons() {
        TextView tasksButton = (TextView) findViewById(R.id.tasks);
        tasksButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                        DashboardActivity.this.startActivity(intent);
                    }
                }
        );

        TextView subjectsButton = (TextView) findViewById(R.id.subjects);

        TextView teachersButton = (TextView) findViewById(R.id.teachers);
        teachersButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DashboardActivity.this, TeachersActivity.class);
                        DashboardActivity.this.startActivity(intent);
                    }
                }
        );

        TextView marksButton = (TextView) findViewById(R.id.marks);

        TextView myScheduleButton = (TextView) findViewById(R.id.my_schedule);

        TextView UniversityScheduleButton = (TextView) findViewById(R.id.univ_schedule);
    }

}