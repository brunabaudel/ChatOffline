package br.ufpe.cin.if1001.chatoffline.model.data;

import java.sql.Date;

public class Message {

    public enum TypeMessage{
        SENT_MESSAGE,
    	RECEIVED_MESSAGE
    }

    private int id;
    private int idFriend;
	private String message;
    private boolean received;
    private Date date;
	private TypeMessage typeMessage;

    public Message(int id, int idFriend, String message, boolean received, Date date) {
        this.id = id;
        this.idFriend = idFriend;
        this.message = message;
        this.received = received;
        this.date = date;
        this.typeMessage = received ? TypeMessage.RECEIVED_MESSAGE : TypeMessage.SENT_MESSAGE;
    }

    public Message(int idFriend, String message, Date date, TypeMessage typeMessage) {
        this.idFriend = idFriend;
        this.message = message;
        this.received = (typeMessage == TypeMessage.RECEIVED_MESSAGE);
        this.date = date;
        this.typeMessage = typeMessage;
    }

    public Message(String message, TypeMessage typeMessage) {
        this.message = message;
        this.typeMessage = typeMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(int idFriend) {
        this.idFriend = idFriend;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message+" ";
    }

	public TypeMessage getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(TypeMessage typeMessage) {
		this.typeMessage = typeMessage;
	}
    
}
