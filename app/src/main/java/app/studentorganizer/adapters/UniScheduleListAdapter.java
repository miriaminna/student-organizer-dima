package app.studentorganizer.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.UnivScheduleEntry;

/**
 * Created by Vitalii on 08-Dec-15.
 */
public class UniScheduleListAdapter extends RecyclerView.Adapter<UniScheduleListAdapter.ViewHolder> {
    private Context mContext;
    private List<UnivScheduleEntry> mUnivScheduleEntries;

    public UniScheduleListAdapter(List<UnivScheduleEntry> univScheduleEntries, Context context) {
        this.mContext = context;
        this.mUnivScheduleEntries = univScheduleEntries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.uni_schedule_entry,
                parent,
                false
        );
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void getTime(final UnivScheduleEntry entry, final boolean isStart) {
        View dView = ((Activity)mContext)
                .getLayoutInflater()
                .inflate(R.layout.time_chooser_dialog, null);

        final AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .setView(dView)
                .create();

        final TimePicker picker = (TimePicker) dView.findViewById(R.id.timePicker);

        Button okButton = (Button) dView.findViewById(R.id.button_ok);
        Button cancelButton = (Button) dView.findViewById(R.id.button_cancel);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalTime time = new LocalTime(picker.getHour(), picker.getMinute());
                if (isStart) {
                    entry.setStart(time);
                } else {
                    entry.setEnd(time);
                }
                DBFactory.getFactory().getUnivScheduleDAO().updateEntity(entry);
//                System.out.println(time.toString("HH:mm"));
                UniScheduleListAdapter.this.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UnivScheduleEntry entry = mUnivScheduleEntries.get(position);

        holder.mPairNum.setText(String.format("Pair %d:", position+1));
        holder.mPairStart.setText(entry.getStart().toString("HH:mm"));
        holder.mPairEnd.setText(entry.getEnd().toString("HH:mm"));

        holder.mPairStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(entry, true);
            }
        });
        holder.mPairEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(entry, false);
            }
        });
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBFactory.getFactory().getUnivScheduleDAO().deleteEntity(entry.getId());
                mUnivScheduleEntries.remove(entry);
                UniScheduleListAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUnivScheduleEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mPairNum;
        public TextView mPairStart;
        public TextView mPairEnd;
        public ImageButton mDeleteButton;


        public ViewHolder(View itemView) {
            super(itemView);

            mPairNum = ((TextView)itemView.findViewById(R.id.pair_num));
            mPairStart = ((TextView)itemView.findViewById(R.id.pair_start));
            mPairEnd = ((TextView)itemView.findViewById(R.id.pair_end));
            mDeleteButton = ((ImageButton)itemView.findViewById(R.id.button_delete));
        }
    }
}
