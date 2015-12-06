package app.studentorganizer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import app.studentorganizer.R;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton mTeacherIcon;
        public TextView mTeacherName;
        public TextView mTeacherType;

        public ViewHolder(View itemView) {
            super(itemView);
            mTeacherIcon = (ImageButton)itemView.findViewById(R.id.teacher_icon);
            mTeacherName = (TextView)itemView.findViewById(R.id.teacher_name);
            mTeacherType = (TextView)itemView.findViewById(R.id.teacher_type);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.teacher_item,
                parent,
                false
        );
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Teacher teacher = mTeachers.get(position);
        holder.mTeacherIcon.setImageResource(R.drawable.round_shape_blue);
        holder.mTeacherName.setText(teacher.getName());
        holder.mTeacherType.setText(teacher.getType());
    }

    @Override
    public int getItemCount() {
        return mTeachers.size();
    }
}
