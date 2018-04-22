package com.lym77.model;

import com.lym77.data.Setting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Msg {
    private String from;
    private String to;
    private String content;
    //�ո�ָ���
    private final String SPLIT = " ";
    //�ո�
    private final String REPLACE_SPLIT = "&nbsp;";
    //����
    private final String NEW_LINE = "<br>";

    public Msg(String info) throws Exception {
        if (info == null) {
            throw new Exception("������Ϣʧ��");
        }
        String tag[] = info.split(" ");
        if (tag.length < 3) {
            throw new Exception("������Ϣʧ��");
        }
        this.from = tag[0];
        this.to = tag[1];
        this.content = tag[2];
    }

    public Msg(String from, String to, String content) {
        this.from = encode(from);
        this.to = encode(to);
        this.content = encode(content);
    }

    /**
     * ����Ϣת��Ϊ�����ַ���ʽ
     *
     * @param str
     * @return
     */
    private String decode(String str) {
        str = str.replace(REPLACE_SPLIT, SPLIT);
        return str.replace(NEW_LINE, "\n");
    }

    /**
     * ���ַ�ת��Ϊ��Ϣ��ʽ
     *
     * @param str
     * @return
     */
    private String encode(String str) {
        str = str.replace(SPLIT, REPLACE_SPLIT);
        return str.replace("\n", NEW_LINE);
    }

    public String getContent() {
        return decode(content);
    }

    public String getFrom() {
        return decode(from);
    }

    public String getTo() {
        return decode(to);
    }

    public void setContent(String content) {
        this.content = encode(content);
    }

    public void setFrom(String from) {
        this.from = encode(from);
    }

    public void setTo(String to) {
        this.to = encode(to);
    }

    @Override
    public String toString() {
        return encode(this.from) + " " + encode(this.to) + " "
                + encode(this.content);
    }

    public static void send(Socket socket, String msg) throws Exception {
        msg += "\n\r";
        socket.getOutputStream().write(msg.getBytes(Setting.encode));
    }

    public static String read(Socket socket)  {
        try {
            return new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), Setting.encode)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
            return null;
        }
    }
}
