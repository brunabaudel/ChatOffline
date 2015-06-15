package br.ufpe.cin.if1001.chatoffline.gui.listpeers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import br.ufpe.cin.if1001.chatoffline.R;


public class ListPeersAdapter extends RecyclerView.Adapter<ListPeersAdapter.ListPeersViewHolder> {

    List<Peer> items = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    OnItemClickListener mItemClickListener;

    public ListPeersAdapter(Context context, List<Peer> items) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.items = items;
    }

    public void delete(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ListPeersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_peers_cardview_row, parent, false);
        ListPeersViewHolder listPeersHolder = new ListPeersViewHolder(view);
        return listPeersHolder;
    }

    @Override
    public void onBindViewHolder(ListPeersViewHolder listPeersHolder, int position) {
        Peer current = items.get(position);
        listPeersHolder.name.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ListPeersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;

        public ListPeersViewHolder(View view) {
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
        void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}