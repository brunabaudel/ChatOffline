package br.ufpe.cin.if1001.chatoffline.gui.base.initial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.gui.base.activity.MainActivity;
import br.ufpe.cin.if1001.chatoffline.model.user.UserPreferences;


public class UsernameActivity extends Activity {

    private static String TAG = UsernameActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_initial);

        final EditText edtUsername = (EditText) findViewById(R.id.username);

        final Button button = (Button) findViewById(R.id.btn_username);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserPreferences.setUser(getApplicationContext(), edtUsername.getText().toString());
                Intent intent = new Intent(UsernameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}