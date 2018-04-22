package com.lym77.ui;

import com.lym77.server.MsgServer;
import com.lym77.server.MsgServer.MsgServerListener;

import java.awt.*;

public class MsgServerView extends MFrame implements MsgServerListener {
    public static MsgServer msgServer;

    public MsgServerView(int size) {
        super();
        msgServer = new MsgServer(size, this);
        this.moveToCenter();
    }

    private void moveToCenter() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2
                - this.getHeight() / 2);
    }

    @Override
    public void start() {
        super.start();
        msgServer.start();
        this.setBtnEnable(false);
    }

    @Override
    public void onCountChange(int count) {
        setCount(count);
    }

    @Override
    public void onErr(String err) {
        addMsg(err);
    }

    @Override
    public void onLogin(String name) {
        addMsg(name + "µÇÂ½¡£¡£¡£");
    }

    @Override
    public void onLogout(String name) {
        addMsg(name + "ÍË³ö¡£¡£¡£");
    }
}
