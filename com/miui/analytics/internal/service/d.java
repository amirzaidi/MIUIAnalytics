package com.miui.analytics.internal.service;

import java.io.InputStream;

public class d {
    private int a;
    private int b = 0;
    private InputStream c;

    public d(int i, int i2, InputStream inputStream) {
        this.a = i;
        this.c = inputStream;
        this.b = i2;
    }

    public InputStream a() {
        return this.c;
    }

    public boolean b() {
        return this.a == 200;
    }

    public int c() {
        return this.b;
    }
}
