package com.miui.analytics.internal.policy.a;

import com.miui.analytics.internal.b.h;
import com.miui.analytics.internal.policy.k;
import com.miui.analytics.internal.util.o;

public class j implements k {
    private static final String a = "TriggerBySize";
    private long b;

    public j(long j) {
        this.b = j;
    }

    public boolean a(h hVar, int i) {
        o.a(a, "check trigger , mThreshold = " + this.b);
        if (hVar != null) {
            if ((i == 1 ? hVar.b() : hVar.d()) <= this.b) {
                return false;
            }
        }
        return true;
    }
}
