package br.ufpe.cin.if1001.chatoffline.model.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "ChatDatabase";

    static final String TABLE_FRIEND = "table_friend";
    static final String _ID_FRIEND = "_id";
    static final String NAME = "name";
    static final String MAC_ADDRESS = "mac_address";

    static final String TABLE_MESSAGES = "table_messages";
    static final String _ID_MESSAGES = "_id";
    static final String ID_FRIEND = "id_friend";
    static final String MESSAGE = "message";
    static final String RECEIVED = "received";
    static final String DATE = "date";

    private String CREATE_TABLE_FRIEND = "CREATE TABLE " + TABLE_FRIEND
            + "("
            + _ID_FRIEND + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME + " TEXT,"
            + MAC_ADDRESS + " TEXT"
            + ")";

    private String CREATE_TABLE_MESSAGES = "CREATE TABLE " + TABLE_MESSAGES
            + "("
            + _ID_MESSAGES + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_FRIEND + " INTEGER ,"
            + MESSAGE + " TEXT,"
            + RECEIVED + " BOOLEAN,"
            + DATE + " DATETIME,"
            + "FOREIGN KEY(" + ID_FRIEND + ") REFERENCES " + TABLE_FRIEND + "(" + _ID_FRIEND + ")"
            + ")";

    public ChatDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FRIEND);
        db.execSQL(CREATE_TABLE_MESSAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
