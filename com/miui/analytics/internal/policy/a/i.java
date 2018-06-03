package com.miui.analytics.internal.policy.a;

import com.miui.analytics.internal.b.h;
import com.miui.analytics.internal.policy.k;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.o;

public class i implements k {
    private static final String a = "TriggerBySendTime";
    private int b;
    private long c = 0;

    public i(int i) {
        this.b = i;
        this.c = 0;
    }

    public void a(long j) {
        this.c = j;
    }

    public boolean a(h hVar, int i) {
        o.a(a, "check trigger , mInterval = " + this.b + "ms mLastTrigger:" + this.c);
        if (this.c <= 0 || !ac.a(this.c, (long) this.b)) {
            return false;
        }
        return true;
    }
}
