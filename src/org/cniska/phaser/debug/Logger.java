package org.cniska.phaser.debug;

import android.util.Log;

public class Logger {

    // Member variables
    // ----------------------------------------

    private static boolean logging = true;
    private static int level = Log.ERROR;

    // Methods
    // ----------------------------------------

    private Logger() {
    }

    public static int debug(String tag, String msg) {
        return logging && level >= Log.DEBUG ? Log.d(tag, msg) : 0;
    }

    public static int error(String tag, String msg) {
        return logging && level >= Log.ERROR  ? Log.e(tag, msg) : 0;
    }

    public static int info(String tag, String msg) {
        return logging && level >= Log.INFO ? Log.i(tag, msg) : 0;
    }

    public static int verbose(String tag, String msg) {
        return logging && level >= Log.VERBOSE ? Log.v(tag, msg) : 0;
    }

    public static int warn(String tag, String msg) {
        return logging && level >= Log.WARN ? Log.w(tag, msg) : 0;
    }

    // Getters and setters
    // ----------------------------------------

    public static void setLogging(boolean logging) {
        Logger.logging = logging;
    }

    public static void setLevel(int level) {
        Logger.level = level;
    }
}
