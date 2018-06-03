package com.miui.analytics.internal.util;

public class i {
    private static final double a = 3.1415926535898d;
    private static final long b = 6371393;

    public double a(double d, double d2, double d3, double d4) {
        double a = a(d);
        double a2 = a(d2);
        double a3 = a(d3);
        return Math.sqrt(((1.0d - (Math.cos(a(d4) - a2) * (Math.cos(a) * Math.cos(a3)))) - (Math.sin(a) * Math.sin(a3))) * 2.0d) * 6371393.0d;
    }

    private double a(double d) {
        return (a * d) / 180.0d;
    }
}
