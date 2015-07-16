package br.ufpe.cin.if1001.chatoffline.gui.base.listpeers;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import br.ufpe.cin.if1001.chatoffline.R;

public class ListPeersAdapter extends RecyclerView.Adapter<ListPeersAdapter.ListPeersViewHolder> {

    List<WifiP2pDevice> items = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    OnItemClickListener mItemClickListener;

    public ListPeersAdapter(Context context, List<WifiP2pDevice> items) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public ListPeersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_peers_cardview_row, parent, false);
        ListPeersViewHolder listPeersHolder = new ListPeersViewHolder(view);
        return listPeersHolder;
    }

    @Override
    public void onBindViewHolder(ListPeersViewHolder listPeersHolder, int position) {
        WifiP2pDevice current = items.get(position);
        listPeersHolder.name.setText(current.deviceName);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public WifiP2pDevice getItem(int position) {
        return items.get(position);
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