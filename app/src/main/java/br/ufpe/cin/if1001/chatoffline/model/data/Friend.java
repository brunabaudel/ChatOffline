package br.ufpe.cin.if1001.chatoffline.model.data;

public class Friend {

    public enum Status {
        STATUS_ONLINE,
        STATUS_OFFLINE
    }

	private String id;
	private String name;
	private String macAddress;
    private Status status;

    public Friend() {}


    public Friend(String id, String name, String macAddress, Status status) {
        this.id = id;
        this.name = name;
        this.macAddress = macAddress;
        this.status = status;
    }

    public Friend(String id, String name, String macAddress) {
        this.id = id;
        this.name = name;
        this.macAddress = macAddress;
        this.status = Status.STATUS_OFFLINE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
