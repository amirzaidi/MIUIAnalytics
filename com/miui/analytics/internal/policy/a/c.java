package com.miui.analytics.internal.policy.a;

import android.content.Context;
import com.miui.analytics.internal.b.k;
import com.miui.analytics.internal.policy.l;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.o;

public class c implements com.miui.analytics.internal.policy.c {
    private static final String a = "IterateDiscard";

    public void a(Context context, l lVar, int i) {
        k a = k.a(context);
        if (lVar != null && lVar.a(a, i)) {
            o.a(a, "discard is triggered.");
            long currentTimeMillis = System.currentTimeMillis();
            long max = Math.max(currentTimeMillis - 1209600000, i == 1 ? a.h() : a.l());
            while (lVar.a(a, i)) {
                if (max < currentTimeMillis - ac.a) {
                    a(a, i, max);
                    max += ac.a;
                } else if (max < currentTimeMillis - ac.b) {
                    a(a, i, max);
                    max += ac.b;
                } else if (max < currentTimeMillis - 3600000) {
                    a(a, i, max);
                    max += 3600000;
                } else {
                    return;
                }
            }
        }
    }

    private void a(k kVar, int i, long j) {
        if (i == 1) {
            kVar.b(j);
        } else if (i == 2) {
            kVar.c(j);
        }
    }
}
