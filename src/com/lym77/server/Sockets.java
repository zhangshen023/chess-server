package com.lym77.server;

import java.net.Socket;

public class Sockets extends Thread {
    protected Socket sockets[];
    protected int num = 0;

    public Sockets(int size) {
        this.sockets = new Socket[size];
    }

    public synchronized void add(Socket socket) {
        for (int i = 0; i < sockets.length; i++) {
            if (sockets[i] == null) {
                sockets[i] = socket;
                num++;
                break;
            }
        }
    }

    public synchronized void remove(Socket socket) {
        for (int i = 0; i < sockets.length; i++) {
            if (socket.equals(sockets[i])) {
                sockets[i] = null;
                num--;
                break;
            }
        }
    }

    public int getNum() {
        return num;
    }

    public boolean isFull() {
        return num >= sockets.length;
    }
}
