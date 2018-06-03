package com.miui.analytics.internal.policy.a;

import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.policy.e;
import java.util.ArrayList;
import java.util.List;

public class b implements e {
    private static final int c = 20;
    private static final int d = 50000;
    private int a;
    private int b;

    public b() {
        this(c, d);
    }

    public b(int i, int i2) {
        if (i <= 0) {
            i = c;
        }
        this.a = i;
        if (i2 <= 0) {
            i2 = d;
        }
        this.b = i2;
    }

    public List<List<LogEvent>> a(List<LogEvent> list) {
        List<List<LogEvent>> arrayList = new ArrayList();
        if (list != null) {
            int i = 0;
            int i2 = 0;
            ArrayList arrayList2 = new ArrayList();
            for (int i3 = 0; i3 < list.size(); i3++) {
                LogEvent logEvent = (LogEvent) list.get(i3);
                if (logEvent != null) {
                    i2++;
                    i = (int) (((long) i) + logEvent.n());
                    arrayList2.add(logEvent);
                    if (i2 >= this.a || i >= this.b) {
                        arrayList.add(arrayList2);
                        arrayList2 = new ArrayList();
                        i = 0;
                        i2 = 0;
                    }
                }
            }
            if (arrayList2.size() > 0) {
                arrayList.add(arrayList2);
            }
        }
        return arrayList;
    }
}
