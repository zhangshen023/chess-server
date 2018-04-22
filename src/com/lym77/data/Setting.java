package com.lym77.data;

import java.io.*;

public class Setting {
    public static int port = 8888;
    public static int size = 300;
    public static String encode = "utf8";
    public static int timeout = 10000;
    public static int restarttime = 5;
    public static String password = "lym77";

    public static void initSetting() {
        File file = new File("config/setting.ini");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            String set = "size=300\n" +
                    "encode=utf8\n" +
                    "port=8888\n" +
                    "timeout=10000\n" +
                    "restarttime=5\n" +
                    "password=lym77";
            OutputStream out = null;
            try {
                out = new FileOutputStream(file);
                out.write(set.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        } else {
            InputStream in;
            try {
                in = new FileInputStream(file);
                byte by[] = new byte[1024];
                String set = "";
                while (in.read(by) != -1) {
                    set += new String(by);
                }
                set = set.trim();
                String sets[] = set.split("\n");
                for (int i = 0; i < sets.length; i++) {
                    String tag[] = sets[i].split("=");
                    if ("port".equals(tag[0])) {
                        try {
                            port = Integer.parseInt(tag[1].trim());
                        } catch (Exception e) {
                        }
                    } else if ("size".equals(tag[0])) {
                        try {
                            size = Integer.parseInt(tag[1].trim());
                        } catch (Exception e) {
                        }
                    } else if ("encode".equals(tag[0])) {
                        try {
                            encode = tag[1].trim();
                        } catch (Exception e) {
                        }
                    } else if ("timeout".equals(tag[0])) {
                        try {
                            timeout = Integer.parseInt(tag[1].trim());
                        } catch (Exception e) {
                        }
                    } else if ("restarttime".equals(tag[0])) {
                        try {
                            restarttime = Integer.parseInt(tag[1].trim());
                        } catch (Exception e) {
                        }
                    } else if ("password".equals(tag[0])) {
                        try {
                            password = tag[1].trim();
                        } catch (Exception e) {
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
