package br.ufpe.cin.if1001.chatoffline.gui.base.navdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import br.ufpe.cin.if1001.chatoffline.R;


public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.NavDrawerViewHolder> {

    List<NavDrawerItem> items = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> items) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.items = items;
    }

    public void delete(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public NavDrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        NavDrawerViewHolder navDrawerHolder = new NavDrawerViewHolder(view);
        return navDrawerHolder;
    }

    @Override
    public void onBindViewHolder(NavDrawerViewHolder navDrawerHolder, int position) {
        NavDrawerItem current = items.get(position);
        navDrawerHolder.title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class NavDrawerViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public NavDrawerViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }
}