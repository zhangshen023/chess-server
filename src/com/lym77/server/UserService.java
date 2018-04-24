package com.lym77.server;

import com.lym77.data.Setting;
import com.lym77.model.Msg;
import com.lym77.model.User;
import com.lym77.ui.MsgServerView;

import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements Runnable {

    private Socket socket;
    private Server server;

    public UserService(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            socket.setSoTimeout(Setting.timeout);
        } catch (SocketException e1) {
        }
        // 1.连接到服务器
        List<String> data = new ArrayList<>();
        int status = server.connect2Server(socket, data);
        if (status == 1) {//失败
            return;
        }
        if (status == 2) {//连接获取在线列表
            while (true) {

                if (isServerClose(socket)) {
                    break;
                }

                sendOnlinePersonsInfo();

            }
            server.logout(socket);

        }

        if (status == 3) {
            String userName = data.get(0).substring("LOGIN:".length(), data.get(0).indexOf("\n\r"));
            User user = new User(socket, userName);
            System.out.println(userName);
            MsgServerView.msgServer.add(user);
            String data2 = null;
            while (true) {
                if (isServerClose(socket)) {
                    break;
                }
                if ((data2 = Msg.read(socket)) != null) {
                    System.out.println(data2);
                    if (data2.indexOf("INVITE:") == 0) { //邀请
//                        Msg msg = new Msg(data1);
//                        server.dealMsg(msg);
                    }
                }
            }
            server.logout(socket);
        } else {//其他消息
            // 读取消息
            try {
                String data1 = null;
                while (true) {

                    if (isServerClose(socket)) {
                        break;
                    }

                    if ((data1 = Msg.read(socket)) != null) {
                        if (data1.indexOf("INVITE:") == 0) { //邀请
                            Msg msg = new Msg(data1);
                            server.dealMsg(msg);
                        }
                    }

                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
            server.logout(socket);

        }

    }


    public void sendOnlinePersonsInfo() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Msg.send(socket, "ONLINE:" + MsgServer.getUsers()
                    .stream()
                    .map(user -> user.getName())
                    .reduce((s1, s2) -> s1 + ":" + s2).orElse(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Boolean isServerClose(Socket socket) {
        try {
            socket.sendUrgentData(0);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            return false;
        } catch (Exception se) {
            return true;
        }
    }
}
