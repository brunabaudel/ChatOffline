package br.ufpe.cin.if1001.chatoffline.controllers;

public class ChatController {
    static ChatController instance = null;

    public static ChatController getInstance()
    {
        if(instance==null)
        {
            instance = new ChatController();
        }

        return instance;
    }

    private ChatController()
    {

    }

}
