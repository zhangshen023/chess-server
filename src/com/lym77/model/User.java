package com.lym77.model;

import java.net.Socket;

public class User {
    private Socket socket;
    private String name;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(Socket socket, String name) {
        super();
        this.socket = socket;
        this.name = name;
    }

    public void sendMsg(Msg msg) throws Exception {
        Msg.send(socket, msg.toString());
    }
}
