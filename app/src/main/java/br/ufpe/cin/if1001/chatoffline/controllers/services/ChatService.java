package br.ufpe.cin.if1001.chatoffline.controllers.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import br.ufpe.cin.if1001.chatoffline.controllers.ChatController;

public class ChatService extends Service {
    ChatController controller;

    public ChatService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
