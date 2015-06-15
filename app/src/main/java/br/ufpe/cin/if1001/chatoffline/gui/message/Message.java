package br.ufpe.cin.if1001.chatoffline.gui.message;

public class Message {

    public enum TypeMessage{
    	SEND_MESSAGE,
    	RECEIVED_MESSAGE
    }
	
	private String message;
	private TypeMessage typeMessage;
    
    public Message(String message, TypeMessage typeMessage) {
        this.message = message;
        this.typeMessage = typeMessage;
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
