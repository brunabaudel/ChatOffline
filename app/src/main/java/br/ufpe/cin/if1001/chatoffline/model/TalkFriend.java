package br.ufpe.cin.if1001.chatoffline.model;

import br.ufpe.cin.if1001.chatoffline.model.data.gui.Friend;

/**
 * Created by brunabaudel on 06/07/15.
 */
public class TalkFriend {

    private static TalkFriend instance;

    private Friend friend;

    private TalkFriend() {

    }

    public static TalkFriend getInstance(){
        if(instance == null)
            instance = new TalkFriend();
        return instance;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}
