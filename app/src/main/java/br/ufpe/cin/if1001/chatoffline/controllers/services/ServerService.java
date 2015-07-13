package br.ufpe.cin.if1001.chatoffline.controllers.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerService extends Service {
    private static final int PORT = 8898;
    public static final String IP_SERVER = "192.168.49.1";

    private final IBinder mBinder = new ServiceBinder();


    private static ServerService me=null;
    public static ServerService getInstance()
    {
        if(me == null)
        {
            me = new ServerService();
        }
        return me;
    }
    private ServerService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class ServiceBinder extends Binder{
        ServerService getServerService()
        {
            return ServerService.this;
        }
    }

    //METODOS AQUI DO SERVICE


    /////

    private static class ServerAsyncTask extends AsyncTask<Void,Void,Void> {



        @Override
        protected void onPreExecute() {
           Log.i("DEGUG", "Opening a server socket");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try
            {
                ServerSocket serverSocket = new ServerSocket(PORT);
                Log.i("DEGUG","SERVER SOCKET OPENED, GG WP");
                Socket client = serverSocket.accept(); //fica escutando a porta aqui, aguardando conexão
                Log.i("DEGUG","UHUL, CHEOGU CONEXAO");

                String readMessage(client.getInputStream());

            }
            catch (Exception e)
            {

            }
            return null;
        }

        private void readMessage(InputStream inputStream) {
        }
    }

}
