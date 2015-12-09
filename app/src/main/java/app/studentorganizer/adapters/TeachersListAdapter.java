package app.studentorganizer.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.activities.TeacherActivity;
import app.studentorganizer.com.TeacherType;
import app.studentorganizer.com.TeacherUtil;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.Teacher;

/**
 * Created by Vitalii on 06-Dec-15.
 */
public class TeachersListAdapter extends RecyclerView.Adapter<TeachersListAdapter.ViewHolder> {
    private Context mContext;
    private List<Teacher> mTeachers;

    public TeachersListAdapter(List<Teacher> teachers, Context context) {
        mTeachers = teachers;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageButton mTeacherIcon;
        public TextView mTeacherName;
        public TextView mTeacherType;
        public Long mTeacherID;
        public ImageButton mDeleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mTeacherIcon = (ImageButton)itemView.findViewById(R.id.teacher_icon);
            mTeacherName = (TextView)itemView.findViewById(R.id.teacher_name);
            mTeacherType = (TextView)itemView.findViewById(R.id.teacher_type);
            mDeleteButton = (ImageButton)itemView.findViewById(R.id.button_delete);
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, TeacherActivity.class);
            intent.putExtra(TeacherActivity.ID_EXTRA, mTeacherID);
            mContext.startActivity(intent);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.teacher_item,
                parent,
                false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Teacher teacher = mTeachers.get(position);
        holder.mTeacherID = teacher.getId();
        holder.mTeacherIcon.setImageResource(teacher.getType().getDrawable());
        holder.mTeacherName.setText(teacher.getName());
        holder.mTeacherType.setText(teacher.getType().toString());

        holder.mTeacherIcon.setOnClickListener(holder);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBFactory.getFactory().getTeacherDAO().deleteEntity(teacher.getId());
                mTeachers.remove(teacher);
                TeachersListAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTeachers.size();
    }
}
