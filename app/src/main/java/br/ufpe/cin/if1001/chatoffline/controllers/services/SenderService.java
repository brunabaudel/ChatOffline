package br.ufpe.cin.if1001.chatoffline.controllers.services;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SenderService extends IntentService implements WifiP2pManager.ActionListener {
    
    private final String LOG_TAG = "LOG_DEBUG";
    private static final int SOCKET_TIMEOUT = 5000;
    public static final String ACTION_SEND_MESSAGE = "br.ufpe.cin.if1001.go_msg";
    public static final String EXTRAS_MAC = "go_mac";
    public static final String EXTRAS_MESSAGE = "go_msg";
    public static final String EXTRAS_ADDRESS = "go_host";
    public static final String EXTRAS_PORT = "go_port";


    String host,jsonMessage,mac;
    int port;

    public SenderService() {
        super("SenderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(LOG_TAG, "ENTROU!!!!");


        if (intent.getAction().equals(ACTION_SEND_MESSAGE)) {            
            host = intent.getExtras().getString(EXTRAS_ADDRESS);
            jsonMessage = intent.getExtras().getString(EXTRAS_MESSAGE);
            mac = intent.getExtras().getString(EXTRAS_MAC);
            port = intent.getExtras().getInt(EXTRAS_PORT);
            WifiP2pConfig configuration = getP2PConnectionConfiguration(mac);
            connect(configuration);
        }
    }

    private void connect(WifiP2pConfig configuration) {
        WifiP2pManager manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        WifiP2pManager.Channel channel = manager.initialize(this, getMainLooper(), null);
        manager.connect(channel, configuration,this);
    }

    private WifiP2pConfig getP2PConnectionConfiguration(String mac) {
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = mac;
        config.wps.setup = WpsInfo.PBC;
        Log.i("Degug", "SenderService: WIFIP2PConfig ok");

        return config;
    }

    @Override
    public void onSuccess() {
        Log.i("DEGUG","SENDER SERVICE: conexão p2p ativada (não confundir com o socket");
        sendData();
    }

    @Override
    public void onFailure(int reason) {
        Log.i("DEGUG","SENDER SERVICE: FALHA de conexão via p2p");
    }

    void sendData()
    {


        Context context = getApplicationContext();
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket();
                        Log.d(LOG_TAG, "Opening client socket - ");
                        socket.bind(null);
                        socket.connect((new InetSocketAddress(host, port)), SOCKET_TIMEOUT);

                        Log.d(LOG_TAG, "Client socket - " + socket.isConnected());
                        OutputStream stream = socket.getOutputStream();
                        ContentResolver cr = context.getContentResolver();
                        InputStream is = new ByteArrayInputStream(jsonMessage.getBytes("UTF-8"));


                        Log.d(LOG_TAG, "Client: Data written");

                        if (socket != null) {
                            if (socket.isConnected()) {
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                    Log.e(LOG_TAG, e.getMessage());
                                }
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }).run();

        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }
}
