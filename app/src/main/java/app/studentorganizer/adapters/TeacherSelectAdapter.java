package app.studentorganizer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.entities.Teacher;

/**
 * Created by henko on 08.12.15.
 */
// fixme : shitty solution
public class TeacherSelectAdapter extends TeachersListAdapter {

    public interface OnTeacherSelectListener {
        void onTeacherSelected(Long teacherId);
    }

    private OnTeacherSelectListener mListener;

    public TeacherSelectAdapter(List<Teacher> teachers, Context context,
                                OnTeacherSelectListener mListener) {
        super(teachers, context, false);
        this.mListener = mListener;
    }

    public class ViewHolder extends TeachersListAdapter.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            mListener.onTeacherSelected(mTeacherID);
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
}

