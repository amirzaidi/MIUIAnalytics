package com.miui.analytics.internal.util;

import java.io.File;

public class k {
    public static void a(File file) {
        file.setReadable(true, false);
        file.setWritable(true, false);
    }

    public static void b(File file) {
        file.setReadable(true, false);
        file.setWritable(true, false);
        file.setExecutable(true, false);
    }

    public static void c(File file) {
        file.setReadable(true, false);
        file.setExecutable(true, false);
    }

    public static void d(File file) {
        file.setReadable(true, false);
    }

    public static String a(String str) {
        return str.endsWith("/") ? str : str + "/";
    }
}
