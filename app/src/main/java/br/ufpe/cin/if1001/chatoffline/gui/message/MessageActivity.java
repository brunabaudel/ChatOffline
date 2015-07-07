package br.ufpe.cin.if1001.chatoffline.gui.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.controllers.ChatController;
import br.ufpe.cin.if1001.chatoffline.model.data.communication.TransMessage;
import br.ufpe.cin.if1001.chatoffline.model.data.communication.User;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Friend;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Message;

public class MessageActivity extends AppCompatActivity implements MessageFragment.OnMessageListener {

    private static String TAG = MessageActivity.class.getSimpleName();

    private Toolbar mToolbar;

    public static ChatController chatController;

    private MessageFragment mMessageFragment;

    private Friend mFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        User user = new User(1, "Bruna", "");

        chatController = ChatController.getInstance(user, getApplicationContext());

        mMessageFragment = (MessageFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_messages);

        Intent i = getIntent();

        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(i.getExtras() != null){
            mFriend = i.getParcelableExtra("FRIEND");
            getSupportActionBar().setTitle(mFriend.getName());

            mMessageFragment.loadMessages(chatController.getAllMessages(mFriend.getId()));
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

    @Override
    public void sendMessage(Message message) {
        message.setIdFriend(mFriend.getId());
        chatController.insertMessage(message);

        TransMessage transMessage = new TransMessage();
        transMessage.setMacSender(mFriend.getMacAddress());
        transMessage.setContentMessage("Oi, tudo bem?");
        receiveMessage(transMessage);
    }

    public void receiveMessage(TransMessage message) {
        Friend friend = chatController.getFriendByMac(message.getMacSender());

        if(friend == null) {
            //Salva o usuário no banco
        } else if(!friend.getName().equals(message.getName())){
            //atualiza usuário
        }

        //mFriend = friend;

        Message mes = new Message(message.getContentMessage(), Message.TypeMessage.RECEIVED_MESSAGE);
        mes.setIdFriend(mFriend.getId());
        chatController.insertMessage(mes);
        mMessageFragment.receiveMessage(mes);
    }
}
