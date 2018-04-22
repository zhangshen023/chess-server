package com.lym77.main;

import com.lym77.data.Setting;
import com.lym77.ui.MsgServerView;

public class Main {
	public static void main(String[] args) {
		Setting.initSetting();
		new MsgServerView(Setting.size).setVisible(true);
	}

}
