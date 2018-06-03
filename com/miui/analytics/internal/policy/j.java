package com.miui.analytics.internal.policy;

import java.util.Random;

public class j {
    private double a;

    public j(double d) {
        this.a = d;
    }

    public boolean a() {
        return ((double) new Random().nextFloat()) <= this.a;
    }
}
