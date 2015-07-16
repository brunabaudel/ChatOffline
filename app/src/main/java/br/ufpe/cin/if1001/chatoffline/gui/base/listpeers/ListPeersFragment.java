package br.ufpe.cin.if1001.chatoffline.gui.base.listpeers;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
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
import br.ufpe.cin.if1001.chatoffline.controllers.ChatController;
import br.ufpe.cin.if1001.chatoffline.gui.base.activity.MainActivity;
import br.ufpe.cin.if1001.chatoffline.gui.message.MessageActivity;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Friend;
import br.ufpe.cin.if1001.chatoffline.model.user.UserPreferences;


public class ListPeersFragment extends Fragment {

    private static String TAG = ListPeersFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private ListPeersAdapter adapter;
    private static List<WifiP2pDevice> peers = null;

    public static ChatController chatController;

    public ListPeersFragment() {

    }
/*
    public static List<Friend> getData() {
        List<Friend> data = new ArrayList<>();

        for (Friend f : peers) {
            Friend peer = new Friend();
            peer.setName(f.getName());
            peer.setMacAddress(f.getMacAddress());
            data.add(peer);
        }

        return data;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chatController = ChatController.getInstance(UserPreferences.getUser(getActivity()), getActivity());

        peers = new ArrayList<WifiP2pDevice>();
//        peers.add(new Friend("Bruna", "155:123:123:876"));
//        peers.add(new Friend("Leu", "152:123:123:876"));
//        peers.add(new Friend("Rafa", "154:123:123:876"));
//        peers.add(new Friend("Rodrigo", "156:123:123:876"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_list_peers, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.list_peers);

        adapter = new ListPeersAdapter(getActivity(), this.peers);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(new ListPeersAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                Intent i = new Intent(getActivity(), MessageActivity.class);

                WifiP2pDevice peer = adapter.getItem(position);

                Friend friend = new Friend(peer.deviceName, peer.deviceAddress, peer);

                boolean success = chatController.insertPeer(friend);

                if (success) {
                    Toast.makeText(getActivity(), friend.getName(), Toast.LENGTH_LONG).show();
                }

                friend = chatController.getFriendByMac(friend.getMacAddress());

                i.putExtra("WIFIP2P", (Parcelable)peer);
                i.putExtra("FRIEND", (Parcelable)friend);

                getActivity().startActivity(i);
            }
        });

        return layout;
    }

    public void setPeersList(WifiP2pDeviceList peersList) {

        peers.clear();

        peers.addAll(peersList.getDeviceList());

        /*for (WifiP2pDevice device : peersList.getDeviceList()) {
            Friend peer = new Friend(device.deviceName, device.deviceAddress, device);
            //this.peers.add(peer);
        }*/

        adapter.notifyDataSetChanged();

    }

}
