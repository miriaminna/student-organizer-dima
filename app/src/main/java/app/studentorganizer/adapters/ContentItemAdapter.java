package app.studentorganizer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.ContentItem;

/**
 * Created by henko on 10.12.15.
 */
public class ContentItemAdapter extends RecyclerView.Adapter<ContentItemAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mContentText;
        public TextView mContentType;
        public ImageButton mDeleteButton;

        public ViewHolder(View itemView) {
            super(itemView);

            mContentText = (TextView) itemView.findViewById(R.id.content_text);
            mContentType = (TextView) itemView.findViewById(R.id.content_type);
            mDeleteButton = (ImageButton) itemView.findViewById(R.id.delete_button);
        }
    }

    protected List<ContentItem> mContentItems;
    protected Context mContext;

    public ContentItemAdapter(List<ContentItem> mContentItems, Context mContext) {
        this.mContentItems = mContentItems;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.content_item,
                parent,
                false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ContentItem item = mContentItems.get(position);

        holder.mContentText.setText(item.getText());
        holder.mContentType.setText(item.getType().toString());
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBFactory.getFactory().getContentItemDAO().deleteEntity(item.getId());
                mContentItems.remove(item);
                ContentItemAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContentItems.size();
    }
}
