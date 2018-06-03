package com.miui.analytics.internal;

import android.content.Context;
import com.miui.analytics.internal.util.k;
import java.io.File;

public class d {
    public static String a(Context context) {
        File file = new File(context.getFilesDir(), "shared");
        if (!file.exists()) {
            file.mkdirs();
        }
        k.c(file);
        return k.a(file.getPath());
    }

    public static String a(Context context, String str) {
        File file = new File(a(context), str);
        if (!file.exists()) {
            file.mkdirs();
        }
        k.c(file);
        return k.a(file.getPath());
    }
}
