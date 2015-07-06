package br.ufpe.cin.if1001.chatoffline.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.if1001.chatoffline.model.data.gui.Friend;
import br.ufpe.cin.if1001.chatoffline.model.data.gui.Message;


public class ChatDAO {

    private ChatDatabaseHelper mHelper;
    private SQLiteDatabase db;
    private Context context;

    public ChatDAO(Context context){
        mHelper = new ChatDatabaseHelper(context);
    }

    public boolean insertPeer(Friend friend) {

        db = mHelper.getWritableDatabase();
        ContentValues values = null;

        values = new ContentValues();
        values.put(mHelper.NAME, friend.getName());
        values.put(mHelper.MAC_ADDRESS, friend.getMacAddress());

        boolean success = db.insertOrThrow(mHelper.TABLE_FRIEND, null, values) > 0;

        db.close();

        return success;
    }

    public boolean deletePeer(Friend friend) {

        db = mHelper.getWritableDatabase();

        //boolean success = deletar todas as menssagens daquele amigo

        //if(success){

        boolean success = db.delete(mHelper.TABLE_FRIEND, mHelper.MAC_ADDRESS + "=" +
                friend.getMacAddress(), null) > 0;
        //}

        db.close();

        return success;
    }

    public boolean insertMessage(Message message) {

        db = mHelper.getWritableDatabase();
        ContentValues values = null;

        values = new ContentValues();
        values.put(mHelper.ID_FRIEND, message.getIdFriend());
        values.put(mHelper.MESSAGE, message.getMessage());
        values.put(mHelper.RECEIVED, message.isReceived());
        values.put(mHelper.DATE, message.getDate());

        boolean success = db.insertOrThrow(mHelper.TABLE_MESSAGES, null, values) > 0;

        db.close();

        return success;
    }

    public List<Friend> getAllFriends() {

        List<Friend> friendsList = new ArrayList<Friend>();

        String selectQuery = "SELECT * FROM " + mHelper.TABLE_FRIEND;

        db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                Friend friend = new Friend(cursor.getString(1), cursor.getString(2));
                friendsList.add(friend);

            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return friendsList;
    }


}
