package com.lym77.server;

import com.lym77.data.Setting;
import com.lym77.model.Msg;
import com.lym77.model.User;
import com.lym77.server.Server.ServerListener;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class MsgServer implements ServerListener {
    public interface MsgServerListener {
        void onCountChange(int count);

        void onErr(String err);

        void onLogin(String name);

        void onLogout(String name);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        users = users;
    }

    private Server server;
    private int size;
    private static ArrayList<User> users;
    private MsgServerListener msgServerListener;

    public MsgServer(int size, MsgServerListener msgServerListener) {
        this.size = size;
        this.msgServerListener = msgServerListener;
    }

    public void start() {
        users = new ArrayList();
        server = new Server(size, this);
        server.start();
    }

    @Override
    public void onStop(String err) {
        for (int i = 0; i < Setting.restarttime; i++) {
            msgServerListener.onErr("·þÎñÍ£Ö¹£¬" + (Setting.restarttime - i) + "Ãëºó³¢ÊÔÆô¶¯£¡");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        start();
    }

    @Override
    public void onReceive(Msg msg) {
        User old = indexOf(msg.getTo());
        try {
            old.sendMsg(msg);
        } catch (Exception e) {
        }
    }

    @Override
    public void onLogin(Socket socket, String name) {
        User user = new User(socket, name);
        User old = null;
        synchronized (this) {
            old = indexOf(name);
        }
        if (old != null) {
            try {
                Msg.send(old.getSocket(), "ÒìµØµÇÂ¼");
            } catch (Exception e1) {
            }
            try {
                old.getSocket().close();
            } catch (IOException e) {
            }
        }
        add(user);
    }

    @Override
    public void onLogout(Socket socket) {
        remove(socket);
    }

    public synchronized void add(User user) {
        msgServerListener.onLogin(user.getName());
        users.add(user);
        msgServerListener.onCountChange(server.getNum());
    }

    public synchronized void remove(Socket socket) {
        try {
            int i = indexOf(socket);
            msgServerListener.onLogout(users.get(i).getName());
            users.remove(i);
            msgServerListener.onCountChange(server.getNum());

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public User indexOf(String name) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(name)) {
                return users.get(i);
            }
        }
        return null;
    }

    public int indexOf(Socket socket) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getSocket().equals(socket)) {
                return i;
            }
        }
        return -1;
    }
}
