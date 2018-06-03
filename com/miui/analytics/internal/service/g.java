package com.miui.analytics.internal.service;

public class g<T> {
    public final T a;
    public final AnalyticsError b;

    public interface a {
        void a(AnalyticsError analyticsError);
    }

    public interface b<T> {
        void a(T t);
    }

    private g(T t) {
        this.a = t;
        this.b = null;
    }

    private g(AnalyticsError analyticsError) {
        this.a = null;
        this.b = analyticsError;
    }

    public boolean a() {
        return this.b == null;
    }

    public static <T> g<T> a(T t) {
        return new g((Object) t);
    }

    public static <T> g<T> a(AnalyticsError analyticsError) {
        return new g(analyticsError);
    }
}
