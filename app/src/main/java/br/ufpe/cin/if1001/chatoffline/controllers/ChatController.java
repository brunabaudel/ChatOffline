package br.ufpe.cin.if1001.chatoffline.controllers;

import br.ufpe.cin.if1001.chatoffline.gui.contact.Contact;
import br.ufpe.cin.if1001.chatoffline.model.User;

public class ChatController {
    static ChatController instance = null;
    public static ChatController getInstance(User user)
    {
        if(instance==null)
        {
            instance = new ChatController(user);
        }

        return instance;
    }

    private ChatController(User me)
    {
        this.me = me;
    }

    // VARS //
    long id;
    User me;
}
