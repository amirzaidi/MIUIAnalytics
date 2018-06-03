package com.miui.analytics.internal.policy.a;

import android.content.Context;
import android.text.TextUtils;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.b.k;
import com.miui.analytics.internal.policy.c;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.policy.l;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a implements c {
    private static final String a = "BasicDiscardJob";
    private double b;
    private long c;
    private int d;

    public a(long j, double d, int i) {
        this.b = d;
        this.c = j;
        this.d = i;
    }

    public void a(Context context, l lVar, int i) {
        if (i == 1) {
            a(context, lVar);
        } else if (i == 2) {
            b(context, lVar);
        }
    }

    private void a(Context context, l lVar) {
        o.a(a, "run discard job int db 1");
        k a = k.a(context);
        List<LogEvent> g = a.g();
        List arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (LogEvent logEvent : g) {
            if (!(logEvent == null || TextUtils.isEmpty(logEvent.b()))) {
                if (hashMap.get(logEvent.b()) == null) {
                    hashMap.put(logEvent.b(), Integer.valueOf(0));
                }
                hashMap.put(logEvent.b(), Integer.valueOf(((Integer) hashMap.get(logEvent.b())).intValue() + 1));
            }
        }
        h a2 = h.a(context);
        for (LogEvent logEvent2 : g) {
            if (!(logEvent2 == null || TextUtils.isEmpty(logEvent2.b()))) {
                if ((this.c <= 0 || System.currentTimeMillis() - logEvent2.e() >= this.c) && ((this.d <= 0 || this.d >= a2.i(logEvent2)) && ((this.b <= 0.0d || ((double) ((Integer) hashMap.get(logEvent2.b())).intValue()) / ((double) g.size()) >= this.b) && !a()))) {
                    arrayList.add(logEvent2);
                }
            }
        }
        if (arrayList.size() > 0) {
            a.e(arrayList);
        }
    }

    private void b(Context context, l lVar) {
        o.a(a, "run discard job in db 2");
        k a = k.a(context);
        a.m();
        a.d(this.c);
    }

    public boolean a() {
        return this.b <= 0.0d && this.c <= 0 && this.d <= 0;
    }
}
