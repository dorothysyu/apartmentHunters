package hciadk.apartmenthunters.settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hciadk.apartmenthunters.R;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupViewHolder> {

    class GroupViewHolder extends RecyclerView.ViewHolder {
        private final TextView groupItemView;

        private GroupViewHolder(View itemView) {
            super(itemView);
            groupItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Group> mGroups; // Cached copy of words

    GroupListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new GroupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        if (mGroups != null) {
            Group current = mGroups.get(position);
            holder.groupItemView.setText(current.getGroup());
        } else {
            // Covers the case of data not being ready yet.
            holder.groupItemView.setText("No Word");
        }
    }

    void setGroups(List<Group> groups){
        mGroups = groups;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mGroups != null)
            return mGroups.size();
        else return 0;
    }
}