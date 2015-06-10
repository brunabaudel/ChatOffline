package br.ufpe.cin.if1001.chatoffline.controllers;

import java.util.ArrayDeque;
import java.util.Queue;

import br.ufpe.cin.if1001.chatoffline.model.InstantMessage;
import br.ufpe.cin.if1001.chatoffline.model.User;

public class ChatController {
    static ChatController instance = null;
    public static ChatController getInstance(User user)
    {
        if(instance==null)
        {
            instance = newInstance(user);
        }

        return instance;
    }

    public static ChatController newInstance(User user)
    {
        instance = new ChatController(user);
        return instance;
    }

    private ChatController(User me)
    {
        this.me = me;
        instantMessageQueue = new ArrayDeque<InstantMessage>();
    }



    // VARS //
    private long id;
    private User me;
    Queue<InstantMessage> instantMessageQueue;

    /**
     * Enfileira uma mensagem para envio
     * @param message mensagem a ser enviada
     */
    public void QueueMessage(InstantMessage message)
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


}
