package br.ufpe.cin.if1001.chatoffline.gui.base.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.gui.contact.ListPeersAdapter;
import br.ufpe.cin.if1001.chatoffline.gui.contact.Peer;


public class ListPeersFragment extends Fragment {

    private static String TAG = ListPeersFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private ListPeersAdapter adapter;
    private static String[] peers = null;

    public ListPeersFragment() {

    }

    public static List<Peer> getData() {
        List<Peer> data = new ArrayList<>();

        for (String p : peers) {
            Peer peer = new Peer();
            peer.setName(p);
            data.add(peer);
        }

        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        peers = getActivity().getResources().getStringArray(R.array.list_peers_labels);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_list_peers, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.list_peers);

        adapter = new ListPeersAdapter(getActivity(), getData());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

}
