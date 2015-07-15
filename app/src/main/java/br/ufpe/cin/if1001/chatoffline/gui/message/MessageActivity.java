package br.ufpe.cin.if1001.chatoffline.gui.message;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;

import br.ufpe.cin.if1001.chatoffline.R;
import br.ufpe.cin.if1001.chatoffline.controllers.ChatController;
import br.ufpe.cin.if1001.chatoffline.controllers.services.ServerService;
import br.ufpe.cin.if1001.chatoffline.model.data.communication.InstantMessage;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Friend;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Message;
import br.ufpe.cin.if1001.chatoffline.model.user.UserPreferences;

public class MessageActivity extends AppCompatActivity implements MessageFragment.OnMessageListener {

    private static String TAG = MessageActivity.class.getSimpleName();

    private Toolbar mToolbar;
    boolean mBound;

    ServerService serverService;

    public static ChatController chatController;

    private MessageFragment mMessageFragment;
    ServerService.ServiceBinder serviceBinder;
    private Friend mFriend;

    private String[] testMessages = new String[]{"Oi", "Tudo bem e vc?", "Meu nome é Gabriela", "e o seu?", "sim", "não", "talvez", "quem sabe...", "sou de Olinda", "e aee"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mBound = false;
        Intent it = new Intent(MessageActivity.this, ServerService.class);
        bindService(it,mConnection, Context.BIND_AUTO_CREATE );


        chatController = ChatController.getInstance(UserPreferences.getUser(getApplicationContext()), getApplicationContext());

        mMessageFragment = (MessageFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_messages);

        Intent i = getIntent();

        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (i.getExtras() != null) {
            mFriend = i.getParcelableExtra("FRIEND");
            getSupportActionBar().setTitle(mFriend.getName());

            mMessageFragment.loadMessages(chatController.getAllMessages(mFriend.getId()));
        }

    }

    /**
     * Service
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            serviceBinder = (ServerService.ServiceBinder) service;
            serverService = serviceBinder.getServerService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    /**
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
        } else if (id == R.id.action_remove) {
            //chatController.deletePeer(mFriend);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void sendMessage(Message message) {
        message.setIdFriend(mFriend.getId());
        chatController.insertMessage(message);

        //TODO: verificar fila de envio (tá implementada no service)

        if (mBound) //se tenho service
        {
            InstantMessage instantMessage = new InstantMessage();
            instantMessage.setMacSender(mFriend.getMacAddress());
            instantMessage.setContentMessage(message.getMessage());
            serverService.sendMessage(mFriend.getDevice(), instantMessage);
        }
//        InstantMessage instantMessage = new InstantMessage();
//        instantMessage.setMacSender(mFriend.getMacAddress());
//        instantMessage.setContentMessage(testMessages[random()]);
//        receiveMessage(instantMessage);
    }

    public void receiveMessage(InstantMessage message) {
        Friend friend = chatController.getFriendByMac(message.getMacSender());

        if (friend == null) {
            //Salva o usuário no banco
        } else if (!friend.getName().equals(message.getName())) {
            //atualiza usuário
        }

        //mFriend = friend;

        Message mes = new Message(message.getContentMessage(), Message.TypeMessage.RECEIVED_MESSAGE);
        mes.setIdFriend(mFriend.getId());
        chatController.insertMessage(mes);
        mMessageFragment.receiveMessage(mes);
    }

    private int random() {
        Random gerador = new Random();

        return gerador.nextInt(10);
    }
}
