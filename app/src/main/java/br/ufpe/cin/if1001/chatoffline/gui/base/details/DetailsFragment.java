package br.ufpe.cin.if1001.chatoffline.gui.base.details;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.model.user.UserPreferences;


public class DetailsFragment extends Fragment {

    private TextView configUsername;
    private TextView configMacaddress;

    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        configUsername = (TextView) rootView.findViewById(R.id.config_edt_name);
        configMacaddress = (TextView) rootView.findViewById(R.id.config_edt_macaddress);

        configUsername.setText(UserPreferences.getUser(getActivity()).getName());


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
