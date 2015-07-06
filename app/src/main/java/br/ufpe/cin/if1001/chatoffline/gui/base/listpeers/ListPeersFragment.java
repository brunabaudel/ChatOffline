package br.ufpe.cin.if1001.chatoffline.gui.base.listpeers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.gui.base.activity.MainActivity;
import br.ufpe.cin.if1001.chatoffline.gui.message.MessageActivity;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Friend;


public class ListPeersFragment extends Fragment {

    private static String TAG = ListPeersFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private ListPeersAdapter adapter;
    private static List<Friend> peers = null;

    public ListPeersFragment() {

    }

    public static List<Friend> getData() {
        List<Friend> data = new ArrayList<>();

        for (Friend f : peers) {
            Friend peer = new Friend();
            peer.setName(f.getName());
            peer.setMacAddress(f.getMacAddress());
            data.add(peer);
        }

        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        peers = new ArrayList<Friend>();
        peers.add(new Friend("Bruna", "155:123:123:876"));
        peers.add(new Friend("Leu", "152:123:123:876"));
        peers.add(new Friend("Rafa", "154:123:123:876"));
        peers.add(new Friend("Rodrigo", "156:123:123:876"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_list_peers, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.list_peers);

        adapter = new ListPeersAdapter(getActivity(), getData());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(new ListPeersAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                Intent i = new Intent(getActivity(), MessageActivity.class);

                Friend peer = adapter.getItem(position);

                if (MainActivity.chatController.insertPeer(peer)) {
                    Toast.makeText(getActivity(), peer.getName(), Toast.LENGTH_LONG).show();
                }

                i.putExtra("FRIEND", (Parcelable)peer);

                getActivity().startActivity(i);
            }
        });

        return layout;
    }

}
