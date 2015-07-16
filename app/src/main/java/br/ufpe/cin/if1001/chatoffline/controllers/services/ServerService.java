package br.ufpe.cin.if1001.chatoffline.controllers.services;

import android.app.Service;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

import br.ufpe.cin.if1001.chatoffline.model.data.communication.InstantMessage;
import br.ufpe.cin.if1001.chatoffline.model.util.Utils;

public class ServerService extends Service implements WifiP2pManager.ConnectionInfoListener {
    private static final int PORT = 8898;
    public static final String IP_SERVER = "192.168.49.1";

    private final IBinder mBinder = new ServiceBinder();
    private static ServerAsyncTask asyncTask = null;

    public ServerService() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {
        if(asyncTask==null)
        {
            asyncTask = new ServerAsyncTask();
            asyncTask.execute();
        }
    }

    public class ServiceBinder extends Binder {
        public ServerService getServerService() {
            return ServerService.this;
        }
    }

    //METODOS AQUI DO SERVICE

    /**
     * Tenta enviar a mensagem usando service de envio WifiP2P
     * @param recipient destinatário
     * @param msg mensagem
     * @return se a chamada foi bem sucedida
     */
    public boolean sendMessage(WifiP2pDevice recipient, InstantMessage msg)
    {
        String localIP = Utils.getLocalIPAddress();
        Log.i("DEGUG", "sendMessage: Recipient = "+recipient);
        String client_mac_fixed = new String(recipient.deviceAddress).replace("99", "19"); //workaround para pegar o ip do /proc/net/arp
        String clientIP = Utils.getIPFromMac(client_mac_fixed);
        Intent serviceIntent = new Intent(this,SenderService.class);
        serviceIntent.setAction(SenderService.ACTION_SEND_MESSAGE);
        if(localIP.equals(IP_SERVER)){
            serviceIntent.putExtra(SenderService.EXTRAS_ADDRESS, clientIP);
        }else{
            serviceIntent.putExtra(SenderService.EXTRAS_ADDRESS, IP_SERVER);
        }
        serviceIntent.putExtra(SenderService.EXTRAS_PORT, PORT);

        try {
            serviceIntent.putExtra(SenderService.EXTRAS_MESSAGE, msg.toJSON().toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        startService(serviceIntent);
        return true;

    }

    /////

    private static class ServerAsyncTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            Log.i("DEGUG", "Opening a server socket");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                Log.i("DEGUG", "SERVER SOCKET OPENED, GG WP");
                Socket client = serverSocket.accept(); //fica escutando a porta aqui, aguardando conexão
                Log.i("DEGUG", "UHUL, CHEOGU CONEXAO");

                String message = readMessage(client.getInputStream());
                Log.i("DEGUG", "MSg=  "+message);
                //TODO: enviar isso pro DAO



            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            asyncTask=null;
        }

        private String readMessage(InputStream inputStream) {
            StringBuilder out = new StringBuilder();
            char[] buffer = new char[1024];
            try {
                Reader in = new InputStreamReader(inputStream, "UTF-8");
                for (int count = 0; (count = in.read(buffer, 0, buffer.length)) > -1; ) { //lendo json de entrada
                        out.append(buffer, 0, count);

                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return out.toString();
        }
    }

}
