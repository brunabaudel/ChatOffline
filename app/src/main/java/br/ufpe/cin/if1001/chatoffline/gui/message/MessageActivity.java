package br.ufpe.cin.if1001.chatoffline.gui.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Friend;

public class MessageActivity extends AppCompatActivity {

    private static String TAG = MessageActivity.class.getSimpleName();

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent i = getIntent();

        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Friend friend = null;

        if(i.getExtras() != null){
            friend = i.getParcelableExtra("FRIEND");
            getSupportActionBar().setTitle(friend.getName());
        }

    }

    /**
     *
     * Menu
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
