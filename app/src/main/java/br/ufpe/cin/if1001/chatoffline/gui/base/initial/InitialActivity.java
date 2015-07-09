package br.ufpe.cin.if1001.chatoffline.gui.base.initial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.gui.base.activity.MainActivity;
import br.ufpe.cin.if1001.chatoffline.model.user.UserPreferences;

/**
 * Created by brunabaudel on 09/07/15.
 */
public class InitialActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent intent = new Intent();

                if(!UserPreferences.getInitial(getApplicationContext())) {

                    UserPreferences.setInitial(getApplicationContext(), true);
                    intent.setClass(InitialActivity.this, UsernameActivity.class);

                } else {
                    intent.setClass(InitialActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 100);
    }


}
