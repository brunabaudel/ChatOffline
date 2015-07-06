package br.ufpe.cin.if1001.chatoffline.model.data.communication;

import android.util.Log;

import java.util.ArrayList;

public class Group extends User {
    User admin;
    ArrayList<User> participants;

    public Group(String groupName, String shortDescription, User admin, long GroupId)
    {
        super(GroupId,groupName,shortDescription);
        this.admin = admin;
        participants = new ArrayList<>();
    }

    public String getGroupDescription()
    {
        return getStatusMessage();
    }

    public void setGroupDescription(String description)
    {
        super.setStatusMessage(description);
    }

    @Override
    public void setStatusMessage(String statusMessage) {
        //protegendo a setStatusMessage
        Log.i("DEBUG", "acesso ao método proibido");
    }
}
