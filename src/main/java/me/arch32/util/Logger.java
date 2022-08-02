package me.arch32.util;

public class Logger {

    public static void printInfo(Object object) {
        System.out.println("CustomRPC/INF | " + object);
    }

    public static void printError(Object object) {
        System.err.println("CustomRPC/ERR | " + object);
    }

}
