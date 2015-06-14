package br.ufpe.cin.if1001.chatoffline.gui.message;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.ufpe.cin.if1001.chatoffline.R;

public class MessageActivity extends AppCompatActivity {

    private static String TAG = MessageActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private MessageFragment messageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_message);

        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        //messageFragment = (MessageFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_messages);
        //getSupportActionBar().setTitle("Nome do peer");


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
