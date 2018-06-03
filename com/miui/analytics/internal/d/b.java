package com.miui.analytics.internal.d;

import android.content.Context;

public class b implements Runnable {
    private a a = null;

    public b(Context context) {
        this.a = a.a(context);
    }

    public void run() {
        while (true) {
            try {
                this.a.a();
                Thread.sleep(18000000);
            } catch (Exception e) {
            }
        }
    }
}
