package com.untitled.untitled;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    public static void info(String LOG, String msg) {
        System.out.println(new SimpleDateFormat("HH:mm.ss.SSS").format(new Date()) + " [" + LOG + "] " + msg);
    }

}
