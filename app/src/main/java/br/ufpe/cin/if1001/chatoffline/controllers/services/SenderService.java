package br.ufpe.cin.if1001.chatoffline.controllers.services;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SenderService extends IntentService {
    
    private final String LOG_TAG = "LOG_DEBUG";
    private static final int SOCKET_TIMEOUT = 5000;
    public static final String ACTION_SEND_MESSAGE = "br.ufpe.cin.if1001.go_msg";
    public static final String EXTRAS_MESSAGE = "go_msg";
    public static final String EXTRAS_ADDRESS = "go_host";
    public static final String EXTRAS_PORT = "go_port";

    public SenderService() {
        super("SenderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(LOG_TAG, "ENTROU!!!!");

        Context context = getApplicationContext();
        if (intent.getAction().equals(ACTION_SEND_MESSAGE)) {            
            String host = intent.getExtras().getString(EXTRAS_ADDRESS);
            String jsonMessage = intent.getExtras().getString(EXTRAS_MESSAGE);
            Socket socket = new Socket();
            int port = intent.getExtras().getInt(EXTRAS_PORT);

            try {
                Log.d(LOG_TAG, "Opening client socket - ");
                socket.bind(null);
                socket.connect((new InetSocketAddress(host, port)), SOCKET_TIMEOUT);

                Log.d(LOG_TAG, "Client socket - " + socket.isConnected());
                OutputStream stream = socket.getOutputStream();
                ContentResolver cr = context.getContentResolver();
                InputStream is = new ByteArrayInputStream(jsonMessage.getBytes("UTF-8"));


                Log.d(LOG_TAG, "Client: Data written");
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getMessage());
            } finally {
                if (socket != null) {
                    if (socket.isConnected()) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }


}
