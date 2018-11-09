package example.ghouse.com.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Ghouse on 11/8/16.
 */

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] mDataset;
    private MainActivity.OnItemClickListener listener;
    private static final int HEADER_VIEW = 1;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleTextView;
        public TextView mDesTextView;
        public ImageView mImageView;
        public MyViewHolder(View v) {
            super(v);
            mImageView = (ImageView)v.findViewById(R.id.list_adapter_image_view);
            mTitleTextView = (TextView) v.findViewById(R.id.list_adapter_title_text_view);
            mDesTextView = (TextView) v.findViewById(R.id.list_adapter_des_text_view);
        }

        public void bind(final String item, final MainActivity.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleTextView;
        public TextView mDesTextView;
        public ImageView mImageView;
        public HeaderViewHolder(View v) {
            super(v);
            mImageView = (ImageView)v.findViewById(R.id.list_adapter_image_view);
            mTitleTextView = (TextView) v.findViewById(R.id.list_adapter_title_text_view);
            mDesTextView = (TextView) v.findViewById(R.id.list_adapter_des_text_view);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(String[] myDataset, MainActivity.OnItemClickListener listener) {
        this.listener = listener;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        RecyclerView.ViewHolder vh;
        Log.e("ListAdapter", "onCreateViewHolder: viewType: "+viewType);
        if(viewType == HEADER_VIEW) {
            View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pager_adapter, parent, false);
            vh = new HeaderViewHolder(v);
        } else {
            View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter, parent, false);
            vh = new MyViewHolder(v);
        }
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            // This is where we'll add footer.
            return HEADER_VIEW;
        }

        return super.getItemViewType(position);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if(holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder)holder).mTitleTextView.setText("Title: "+mDataset[position]);
            ((HeaderViewHolder)holder).mDesTextView.setText("Description: "+mDataset[position]);
//            ((HeaderViewHolder)holder).bind(mDataset[position], listener);
        } else if(holder instanceof MyViewHolder) {
            ((MyViewHolder)holder).mTitleTextView.setText("Title");
            ((MyViewHolder)holder).mDesTextView.setText("Description");
//            holder.bind(mDataset[position], listener);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
