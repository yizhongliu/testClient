package com.iview.testclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.iview.testclient.taskdata.ControlBean;
import com.iview.testclient.taskdata.TaskData;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
    private final static String TAG = "MainActivity";

    Button sendButton;
    Button send;

    Button settingStartButton;
    Button settingStopButton;

    Button upButton;
    Button downButton;
    Button leftButton;
    Button rightButton;

    ExecutorService exec = Executors.newCachedThreadPool();
    TcpClient tcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tcpClient = new TcpClient("192.168.0.138", 8092);
//        exec.execute(tcpClient);
        initView();

    }

    public void initView() {
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);

        send = findViewById(R.id.send);
        send.setOnClickListener(this);
        //send.setOnTouchListener(this);

        settingStartButton = findViewById(R.id.settingStart);
        settingStartButton.setOnClickListener(this);

        settingStopButton = findViewById(R.id.settingStop);
        settingStopButton.setOnClickListener(this);

        upButton = findViewById(R.id.up);
        upButton.setOnTouchListener(this);

        downButton = findViewById(R.id.down);
        downButton.setOnTouchListener(this);

        leftButton = findViewById(R.id.left);
        leftButton.setOnTouchListener(this);

        rightButton = findViewById(R.id.right);
        rightButton.setOnTouchListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendButton:


                sendSocket();

                //send();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while (true) {
//                            sendSocket();
////                            try {
////                                Thread.sleep(80);
////                            } catch (InterruptedException e) {
////                                e.printStackTrace();
////                            }
//                        }
//                    }
//                }).start();
                break;
            case R.id.send:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            send();
//                            try {
//                                Thread.sleep(80);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
                        }
                    }
                }).start();
            //    send();
                break;

            case R.id.settingStart: {
                Gson gson = new Gson();
                SocketMsg<String> socketMsg = new SocketMsg<>("Setting", "Start", null);
                String msg = gson.toJson(socketMsg);
                sendSocket(msg);
                break;
            }

            case R.id.settingStop: {
                Gson gson = new Gson();
                SocketMsg<String> socketMsg = new SocketMsg<>("Setting", "Stop", null);
                String msg = gson.toJson(socketMsg);
                sendSocket(msg);
                break;
            }
        }
    }

    int testId = 1;
    void send() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", "Control");
            jsonObject.put("action", "Show");

            JSONObject imgObj = new JSONObject();
            imgObj.put("url", "test2.png");
            imgObj.put("rotation", 30);
            imgObj.put("imgTime", -1);
       //     imgObj.put("imgTime", testId++);

        } catch (Exception e) {
            e.printStackTrace();
        }

        final String msg = jsonObject.toString();
//        tcpClient.send(msg);
        exec.execute(new Runnable() {
            @Override
            public void run() {
                tcpClient.send(msg);
            }
        });
    }

    void sendSocket() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {

                    //1.创建监听指定服务器地址以及指定服务器监听的端口号
                    Socket socket = new Socket("192.168.0.132", 8091);

                    Log.e(TAG, "socket :" + socket);
                    //2.拿到客户端的socket对象的输出流发送给服务器数据
                    OutputStream os = socket.getOutputStream();

                    String message = sendSocketMsg();
                    //写入要发送给服务器的数据
                    os.write(message.getBytes("UTF-8"));
                    os.flush();
                    socket.shutdownOutput();


                    Log.e(TAG, "send msg");
                    //拿到socket的输入流，这里存储的是服务器返回的数据
                    InputStream is = socket.getInputStream();
                    //解析服务器返回的数据
                    InputStreamReader reader = new InputStreamReader(is);
                    BufferedReader bufReader = new BufferedReader(reader);
                    String s = null;
                    final StringBuffer sb = new StringBuffer();
                    while ((s = bufReader.readLine()) != null) {
                        Log.e(TAG, "getMsgLine:" + s);
                        sb.append(s);
                    }

                    Log.e(TAG, "---getMsg---:");
                    parseGetInfo(sb.toString());


                    //3、关闭IO资源（注：实际开发中需要放到finally中）
                    bufReader.close();
                    reader.close();
                    is.close();
                    os.close();
                    socket.close();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    void sendSocket(final String message) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {

                    //1.创建监听指定服务器地址以及指定服务器监听的端口号
                    Socket socket = new Socket("192.168.0.132", 8091);

                    Log.e(TAG, "socket :" + socket);
                    //2.拿到客户端的socket对象的输出流发送给服务器数据
                    OutputStream os = socket.getOutputStream();

               //     String message = sendSocketMsg();
                    //写入要发送给服务器的数据
                    os.write(message.getBytes("UTF-8"));
                    os.flush();
                    socket.shutdownOutput();


                    Log.e(TAG, "send msg");
                    //拿到socket的输入流，这里存储的是服务器返回的数据
                    InputStream is = socket.getInputStream();
                    //解析服务器返回的数据
                    InputStreamReader reader = new InputStreamReader(is);
                    BufferedReader bufReader = new BufferedReader(reader);
                    String s = null;
                    final StringBuffer sb = new StringBuffer();
                    while ((s = bufReader.readLine()) != null) {
                        Log.e(TAG, "getMsgLine:" + s);
                        sb.append(s);
                    }

                    Log.e(TAG, "---getMsg---:");
                    parseGetInfo(sb.toString());


                    //3、关闭IO资源（注：实际开发中需要放到finally中）
                    bufReader.close();
                    reader.close();
                    is.close();
                    os.close();
                    socket.close();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public String sendMsg() {
        Gson gson = new Gson();
        DataTemplate<String> dataTemplate = new DataTemplate<>("aa", "bb", null);
        String msg = gson.toJson(dataTemplate);
        return msg;
    }

    public String sendMsg2() {
        Gson gson = new Gson();
        TestTempDate testTempDate = new TestTempDate("alex", "male");
        DataTemplate<TestTempDate> dataTemplate = new DataTemplate<>("aa", "bb", testTempDate);
        String msg = gson.toJson(dataTemplate);
        return msg;
    }

    public String sendSocketMsg() {
        Gson gson = new Gson();
        SocketMsg<String> socketMsg = new SocketMsg<>("Get", "GetPathInfo", null);
        String msg = gson.toJson(socketMsg);
        return msg;
    }

    public void parseGetInfo(String msg) {
        Gson gson = new Gson();
        ResultBean<String> result = gson.fromJson(msg, ResultBean.class);
    //    Log.e(TAG, "result:" + result);

        TaskData taskData = gson.fromJson(result.getContent(), TaskData.class);
    //    Log.e(TAG, "taskData:" + taskData);
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                break;
        }
        return false;
    }


    // 长按触发handle
    boolean bLongTouch = false;
    final Handler handler = new Handler();
    final Runnable mLongPressed = new Runnable() {
        public void run() {
            // 长按处理
          //
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (bLongTouch) {
                        send();
                    }
                }
            }).start();



        }
    };

    public class LongConnectThread extends Thread {
        String msg;
        LongConnectThread(String msg) {
            this.msg = msg;
        }
        @Override
        public void run() {
            while (bLongTouch) {
                tcpClient.send(msg);
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.send: {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    exec.execute(tcpClient);
                    bLongTouch = true;
                    handler.postDelayed(mLongPressed, 0);
                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    bLongTouch = false;
                    handler.removeCallbacks(mLongPressed);
                    tcpClient.closeSelf();
                    // 放开处理
                }
                return true;

            }
            case R.id.up: {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    bLongTouch = true;

                    tcpClient = new TcpClient("192.168.0.132", 8092);
                    exec.execute(tcpClient);

                    Gson gson = new Gson();
                    ControlBean bean = new ControlBean(270);
                    SocketMsg<ControlBean> socketMsg = new SocketMsg<>("Control", "Move", bean);
                    String msg = gson.toJson(socketMsg);
                    LongConnectThread longConnectThread = new LongConnectThread(msg);
                    longConnectThread.start();

                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    bLongTouch = false;
                    tcpClient.closeSelf();
                    // 放开处理
                }
                break;
            }

            case R.id.down: {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    bLongTouch = true;

                    tcpClient = new TcpClient("192.168.0.132", 8092);
                    exec.execute(tcpClient);

                    Gson gson = new Gson();
                    ControlBean bean = new ControlBean(90);
                    SocketMsg<ControlBean> socketMsg = new SocketMsg<>("Control", "Move", bean);
                    String msg = gson.toJson(socketMsg);
                    LongConnectThread longConnectThread = new LongConnectThread(msg);
                    longConnectThread.start();

                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    bLongTouch = false;
                    tcpClient.closeSelf();
                    // 放开处理
                }
                break;
            }

            case R.id.left: {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    bLongTouch = true;

                    tcpClient = new TcpClient("192.168.0.132", 8092);
                    exec.execute(tcpClient);

                    Gson gson = new Gson();
                    ControlBean bean = new ControlBean(180);
                    SocketMsg<ControlBean> socketMsg = new SocketMsg<>("Control", "Move", bean);
                    String msg = gson.toJson(socketMsg);
                    LongConnectThread longConnectThread = new LongConnectThread(msg);
                    longConnectThread.start();

                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    bLongTouch = false;
                    tcpClient.closeSelf();
                    // 放开处理
                }
                break;
            }

            case R.id.right: {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    bLongTouch = true;

                    tcpClient = new TcpClient("192.168.0.132", 8092);
                    exec.execute(tcpClient);

                    Gson gson = new Gson();
                    ControlBean bean = new ControlBean(0);
                    SocketMsg<ControlBean> socketMsg = new SocketMsg<>("Control", "Move", bean);
                    String msg = gson.toJson(socketMsg);
                    LongConnectThread longConnectThread = new LongConnectThread(msg);
                    longConnectThread.start();

                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    bLongTouch = false;
                    tcpClient.closeSelf();
                    // 放开处理
                }
                break;
            }
        }
        return false;
    }
}
