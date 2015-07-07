package br.ufpe.cin.if1001.chatoffline.model.data.communication;

/**
 * Created by brunabaudel on 06/07/15.
 */
public class TransMessage {

    private String macSender;
    private String name;
    private String contentMessage;

    public TransMessage() {
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
}
