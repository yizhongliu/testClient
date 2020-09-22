package com.iview.testclient;

import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;

public class TcpClient implements Runnable {

    private final static String TAG = "TcpClient";

    private String serverIp = "192.168.0.117";
    private int serverPort = 8091;

    private PrintWriter pw;
    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream dataInputStream;

    private Writer writer;

    private boolean isRun = true;

    protected Socket socket = null;
    byte buff[] = new byte[4096];
    private String rcvMsg;
    private int rcvLen;

    public TcpClient(String ip, int port) {
        this.serverIp = ip;
        this.serverPort = port;
    }

    public void closeSelf() {
        isRun = false;
    }

    public void send(String msg) {

        if (writer != null) {
//            pw.println(msg);
//            Log.e(TAG, "befflushore  msg");
//            pw.flush();
//            Log.e(TAG, "send msg" + msg);
            try {
                Log.e(TAG, "before send msg" + msg);
                writer.write(msg);
                writer.flush();
                Log.e(TAG, "send msg" + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    @Override
    public void run() {
        try {
            socket = new Socket(serverIp, serverPort);

        //    socket.setReceiveBufferSize(100);
            socket.setSendBufferSize(100);
            socket.setTcpNoDelay(true);
            socket.setSoTimeout(5000);
            outputStream = socket.getOutputStream();
     //       pw =new PrintWriter(outputStream, false);

            writer = new OutputStreamWriter(outputStream);


            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }

        while (isRun) {
//            try {
//                rcvLen = dataInputStream.read(buff);
//                rcvMsg = new String(buff, 0, rcvLen, "utf-8");
//                Log.d(TAG, "receive new msg:" + rcvMsg);
//            } catch (Exception e) {
//
//            }
            try {
                Thread.sleep(110);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            Log.e(TAG, "close client");
           // pw.close();
            writer.close();
            outputStream.close();
            inputStream.close();
            dataInputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
