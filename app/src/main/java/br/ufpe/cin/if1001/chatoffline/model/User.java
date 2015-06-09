package br.ufpe.cin.if1001.chatoffline.model;

public class User {
    public final long id;
    public String name;
    public String statusMessage;

    public User(long id, String name, String statusMessage) {
        this.id = id;
        this.name = name;
        this.statusMessage = statusMessage;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
