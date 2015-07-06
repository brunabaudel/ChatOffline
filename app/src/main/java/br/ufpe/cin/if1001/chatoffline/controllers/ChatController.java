package br.ufpe.cin.if1001.chatoffline.controllers;

import android.content.Context;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import br.ufpe.cin.if1001.chatoffline.model.data.communication.InstantMessage;
import br.ufpe.cin.if1001.chatoffline.model.data.communication.User;
import br.ufpe.cin.if1001.chatoffline.model.database.ChatDAO;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Friend;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Message;

public class ChatController {

    private static ChatController instance = null;

    public static ChatController getInstance(User user, Context ctx)
    {
        if(instance==null)
        {
            instance = newInstance(user, ctx);
        }

        return instance;
    }

    public static ChatController newInstance(User user, Context ctx)
    {
        instance = new ChatController(user, ctx);
        return instance;
    }

    private ChatController(User me, Context ctx)
    {
        this.me = me;
        instantMessageQueue = new ArrayDeque<InstantMessage>();
        dao = new ChatDAO(ctx);
    }

    // VARS //
    private long id;
    private User me;
    private Queue<InstantMessage> instantMessageQueue;
    private ChatDAO dao;


    // QUEUE CONTROL //
    /**
     * Enfileira uma mensagem para envio
     * @param message mensagem a ser enviada
     */
    public void enqueueMessage(InstantMessage message)
    {
        instantMessageQueue.add(message);
    }

    /**
     * Pega a próxima mensagem na fila de envio, retorna null caso não haja mais mensagens
     * @return InstantMessage ou null
     */
    public InstantMessage getNextMessage()
    {
        return instantMessageQueue.poll();
    }


    // BANCO DE DADOS CONTROL //

    public boolean insertPeer(Friend friend){
        return dao.insertPeer(friend);
    }

    public boolean deletePeer(Friend friend){
        return dao.deletePeer(friend);
    }

    public boolean insertMessage(Message message){
        return dao.insertMessage(message);
    }

    public List<Friend> getAllFriends(){
        return dao.getAllFriends();
    }

}
