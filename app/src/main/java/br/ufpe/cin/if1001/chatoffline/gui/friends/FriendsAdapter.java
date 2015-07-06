package br.ufpe.cin.if1001.chatoffline.gui.friends;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.model.gui.Friend;


public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder> {

    List<Friend> items = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    OnItemClickListener mItemClickListener;

    public FriendsAdapter(Context context, List<Friend> items) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.items = items;
    }

    public void delete(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public FriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.friends_cardview_row, parent, false);
        FriendsViewHolder friendsHolder = new FriendsViewHolder(view);
        return friendsHolder;
    }

    @Override
    public void onBindViewHolder(FriendsViewHolder friendsViewHolder, int position) {
        Friend current = items.get(position);
        friendsViewHolder.name.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Friend getItem(int position) {
        return items.get(position);
    }

    class FriendsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;

        public FriendsViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}