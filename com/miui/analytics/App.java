package com.miui.analytics;

import miui.external.a;
import miui.external.b;

public class App extends a {
    private static final String TAG = "App";

    public b onCreateApplicationDelegate() {
        return new AnalyticsApp();
    }
}
