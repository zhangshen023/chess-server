package com.lym77.server;

import com.lym77.data.Setting;
import com.lym77.model.Msg;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Sockets {
    private ExecutorService es;
    private ServerSocket ss;
    private ServerListener serverListener;

    public interface ServerListener {
        void onStop(String err);

        void onReceive(Msg msg);

        void onLogin(Socket socket, String name);

        void onLogout(Socket socket);
    }

    public Server(int size, ServerListener serverListener) {
        super(size);
        this.serverListener = serverListener;
        this.es = Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        try {
            this.ss = new ServerSocket(Setting.port);
            while (true) {
                Socket socket = this.ss.accept();
                es.execute(new UserService(socket, this));
            }
        } catch (IOException e) {
            serverListener.onStop(e.getMessage());
        }
    }

    public void logout(Socket socket) {
        remove(socket);
        try {
            socket.close();
        } catch (IOException e) {
        }
        serverListener.onLogout(socket);
    }

    public int connect2Server(Socket socket, List<String> data) {
        if (isFull()) {
            return 1;
        }
        byte[] by = new byte[64];
        try {
            if (socket.getInputStream().read(by) != -1) {
                if (data == null) {
                    data = new ArrayList<>();
                }
                data.add(new String(by, Setting.encode));
                if (data.get(0).indexOf("LOGIN:") == 0) {
                    return 3;//不需要连接（这是一个登陆信息）
                }
                if (data.get(0).contains(Setting.password)) {
                    add(socket);
                    try {
                        Msg.send(socket, "success");
                    } catch (Exception e) {
                    }
                    return 2;
                }
            }
        } catch (IOException e) {
            return 1;
        }
        try {
            Msg.send(socket, "failed");
        } catch (Exception e1) {
        }
        try {
            socket.close();
        } catch (IOException e) {
        }
        return 1;
    }

    public void dealMsg(Msg msg) {
        serverListener.onReceive(msg);
    }
}
