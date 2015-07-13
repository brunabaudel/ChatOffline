package br.ufpe.cin.if1001.chatoffline.model.data.communication;

import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

public class InstantMessage {
    private final String msgContent;
    private final User sender, recipient;
    private final long sentTimestamp;

    public InstantMessage(String msgContent, User sender, User recipient, long sentTimestamp) {
        this.msgContent = msgContent;
        this.sender = sender;
        this.recipient = recipient;
        this.sentTimestamp = sentTimestamp;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public long getSentTimestamp() {
        return sentTimestamp;
    }

    public JSONObject toJSON() throws JSONException
    {
        JSONObject json = new JSONObject();

        json.put("msgContent",msgContent);
        //...


        return json;
    }

    public InstantMessage fromJSON(String json) throws JSONException
    {
        JSONObject olar = new JSONObject(json);

        return new InstantMessage(null, null, null, 0);
    }
}
