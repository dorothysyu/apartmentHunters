package hciadk.apartmenthunters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ApartmentListAdapter extends RecyclerView.Adapter<ApartmentListAdapter.ApartmentViewHolder> {

    class ApartmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private ApartmentViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Apartment> mWords; // Cached copy of words

    ApartmentListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ApartmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ApartmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ApartmentViewHolder holder, int position) {
        if (mWords != null) {
            Apartment current = mWords.get(position);
            holder.wordItemView.setText(current.getAddress());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No apartment");
        }
    }

    void setWords(List<Apartment> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}
