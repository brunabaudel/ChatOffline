package br.ufpe.cin.if1001.chatoffline.gui.listpeers;

public class Peer {

	private String message;
	private String name;

    public Peer() {
    }
    
    public Peer(String name, String message) {
    	this.name = name;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getName() {
		return name;
	}

    public void setName(String name) {
        this.name = name;
    }
    
}
