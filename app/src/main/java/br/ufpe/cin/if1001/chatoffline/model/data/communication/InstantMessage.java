package br.ufpe.cin.if1001.chatoffline.model.data.communication;

import org.json.JSONException;
import org.json.JSONObject;

public class InstantMessage {

    private String macSender;
    private String name;
    private String contentMessage;

    public InstantMessage() {
    }

    public InstantMessage(String macSender, String name, String contentMessage) {
        this.macSender = macSender;
        this.name = name;
        this.contentMessage = contentMessage;
    }

    public String getMacSender() {
        return macSender;
    }

    public void setMacSender(String macSender) {
        this.macSender = macSender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentMessage() {
        return contentMessage;
    }

    public void setContentMessage(String contentMessage) {
        this.contentMessage = contentMessage;
    }

    public JSONObject toJSON() throws JSONException
    {
        JSONObject json = new JSONObject();

        json.put("macSender", macSender);
        json.put("name", name);
        json.put("contentMessage", contentMessage);

        return json;
    }

    public static InstantMessage fromJSON(String json) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(json);

        String macSender = jsonObject.getString("macSender");
        String name = jsonObject.getString("name");
        String contentMessage = jsonObject.getString("contentMessage");

        return new InstantMessage(macSender, name, contentMessage);
    }
}
