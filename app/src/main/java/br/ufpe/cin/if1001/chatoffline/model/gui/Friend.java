package br.ufpe.cin.if1001.chatoffline.model.gui;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable {

    public enum Status {
        STATUS_ONLINE,
        STATUS_OFFLINE
    }

	private int id;
	private String name;
	private String macAddress;
    private Status status;

    public Friend() {}

    public Friend(int id, String name, String macAddress, Status status) {
        this.id = id;
        this.name = name;
        this.macAddress = macAddress;
        this.status = status;
    }

    public Friend(int id, String name, String macAddress) {
        this.id = id;
        this.name = name;
        this.macAddress = macAddress;
        this.status = Status.STATUS_OFFLINE;
    }

    public Friend(String name, String macAddress) {
        this.name = name;
        this.macAddress = macAddress;
        this.status = Status.STATUS_ONLINE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public Friend(Parcel in){

        this.id = in.readInt();
        this.name = in.readString();
        this.macAddress = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(macAddress);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };
}
