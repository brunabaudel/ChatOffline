package br.ufpe.cin.if1001.chatoffline.gui.contact;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.gui.message.MessageActivity;


public class ContactActivity extends Activity implements OnItemClickListener {

    private ListView mListViewContact;
    private List<Contact> mListContact;
    private ContactAdapter mContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        initViews();

        mListContact = new ArrayList<Contact>();

        mContactAdapter = new ContactAdapter(this, mListContact);
        mListViewContact.setAdapter(mContactAdapter);
        
        
        addItemsToList();
    }

    private void initViews() {
        mListViewContact = (ListView) findViewById(R.id.listViewContact);
        
        mListViewContact.setOnItemClickListener(this);
    }

/*
 * ListView
 */

    private void addItemsToList() {
    	mListContact.add(new Contact("Bruna Carolina Baudel de Santana", "huauauhahuahuhauuheueuheuh", Contact.Status.STATUS_ONLINE));

    	mListContact.add(new Contact("Leu", "Oi, tudo bem...", Contact.Status.STATUS_ONLINE));
        mContactAdapter.notifyDataSetChanged();
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent i = new Intent(getApplicationContext(), MessageActivity.class);
		startActivity(i);
	}

    /**
     * Menu
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
