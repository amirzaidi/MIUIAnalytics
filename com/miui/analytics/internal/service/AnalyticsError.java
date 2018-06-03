package com.miui.analytics.internal.service;

public class AnalyticsError extends Exception {
    private static final long serialVersionUID = 1;

    public AnalyticsError(String str, Throwable th) {
        super(str, th);
    }

    public AnalyticsError(String str) {
        super(str);
    }

    public AnalyticsError(Throwable th) {
        super(th);
    }
}
