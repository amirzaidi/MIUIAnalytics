package com.miui.analytics.internal.policy.a;

import com.miui.analytics.internal.policy.k;
import com.miui.analytics.internal.util.o;

public class h implements k {
    private static final String a = "TriggerByCount";
    private int b;

    public h(int i) {
        this.b = i;
    }

    public boolean a(com.miui.analytics.internal.b.h hVar, int i) {
        o.a(a, "check trigger , threshold = " + this.b);
        if (hVar != null) {
            if ((i == 1 ? hVar.a() : hVar.c()) <= ((long) this.b)) {
                return false;
            }
        }
        return true;
    }
}
