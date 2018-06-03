package com.miui.analytics.internal.policy;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.b.h;
import com.miui.analytics.internal.b.k;
import com.miui.analytics.internal.policy.a.c;
import com.miui.analytics.internal.policy.a.g;
import java.util.ArrayList;
import java.util.List;

public class d {
    private static final String a = "DiscardPolicy";
    private l b;
    private List<c> c = new ArrayList();
    private g d;

    public d(l lVar, List<c> list) {
        this.b = lVar;
        if (list != null) {
            this.c.addAll(list);
        }
        this.c.add(new c());
        this.d = new g();
    }

    public void a(Context context, int i) {
        if (this.d != null) {
            this.d.a(context, null, i);
        }
        if (this.b != null) {
            h a = k.a(context);
            if (this.b.a(a, i)) {
                Log.d(a, "discard is triggered.");
                for (c a2 : this.c) {
                    a2.a(context, this.b, i);
                    if (!this.b.a(a, i)) {
                        return;
                    }
                }
            }
        }
    }
}
