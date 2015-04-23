package br.ufpe.cin.if1001.chatoffline.gui.message;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.if1001.chatoffline.R;

public class MessageActivity extends Activity implements View.OnClickListener {

    private ListView mListViewMessage;
    private Button mButtonSend;
    private EditText mEditMessage;
    private List<Message> mListMessage;
    private MessageAdapter mChatMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        initViews();

        mListMessage = new ArrayList<Message>();

        mChatMessageAdapter = new MessageAdapter(this, mListMessage);
        mListViewMessage.setAdapter(mChatMessageAdapter);
    }

    private void initViews() {
        mListViewMessage = (ListView) findViewById(R.id.listViewMessage);
        mButtonSend = (Button) findViewById(R.id.buttonSend);
        mEditMessage = (EditText) findViewById(R.id.editMessage);

        mButtonSend.setOnClickListener(this);
    }

/*
 * ListView
 */

    private void addItemsToList() {
        Message.TypeMessage typeMsg;
        
        if(flagTypeMessage){
        	typeMsg = Message.TypeMessage.SEND_MESSAGE;
        	flagTypeMessage = false;
        } else {
        	typeMsg = Message.TypeMessage.RECEIVED_MESSAGE;
        	flagTypeMessage = true;
        }
    	
    	mListMessage.add(new Message(mEditMessage.getText().toString(), typeMsg));
        mChatMessageAdapter.notifyDataSetChanged();
    }

    /**
     *
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

    //Teste

    private boolean flagTypeMessage = false;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSend:
                addItemsToList();
                mEditMessage.setText("");
                break;
        }
    }
}
