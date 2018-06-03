package com.miui.analytics.internal.service;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class b {
    private static final String a = "Dispatcher";
    private List<LogEvent> b;
    private Context c;

    public b(Context context, List<LogEvent> list) {
        this.b = list;
        this.c = context;
    }

    public List<LogEvent> a() {
        o.a(a, "dispatch start.");
        if (r.b(this.c)) {
            Object arrayList = new ArrayList();
            Object arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            try {
                if (this.b != null) {
                    o.a(a, "eventCount: " + this.b.size());
                    for (int i = 0; i < this.b.size(); i++) {
                        LogEvent logEvent = (LogEvent) this.b.get(i);
                        if (g.a(this.c).a(logEvent.c(), logEvent.b())) {
                            arrayList.add(logEvent);
                        } else {
                            arrayList2.add(logEvent);
                        }
                    }
                }
                if (arrayList2.size() > 0) {
                    arrayList3.addAll(a(arrayList2, false));
                }
                if (arrayList.size() > 0) {
                    arrayList3.addAll(a(arrayList, true));
                }
                o.a(a, String.format("eventsOffCount: %d,eventsOnCount: %d", new Object[]{Integer.valueOf(arrayList2.size()), Integer.valueOf(arrayList.size())}));
            } catch (Throwable e) {
                Log.e(o.a(a), "Fail to dispatch: ", e);
            }
            return arrayList3;
        }
        o.a(a, "network not accessible, stop dispatch");
        return new ArrayList();
    }

    public List<LogEvent> a(List<LogEvent> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (n.a(this.c, a)) {
            return arrayList;
        }
        if (list != null) {
            try {
                for (Entry entry : b((List) list).entrySet()) {
                    for (Entry entry2 : d((List) entry.getValue()).entrySet()) {
                        for (Entry entry3 : c((List) entry2.getValue()).entrySet()) {
                            Map a = a((List) entry3.getValue());
                            h a2 = h.a(this.c);
                            for (Entry entry4 : a.entrySet()) {
                                for (List list2 : a2.b().a((List) entry4.getValue())) {
                                    if (a((String) entry2.getKey(), (String) entry4.getKey(), list2, z, ((Integer) entry.getKey()).intValue(), ((Integer) entry3.getKey()).intValue())) {
                                        arrayList.addAll(list2);
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                Log.e(o.a(a), "dispatch exception:", e);
            }
        }
        o.a(a, "dispatch end.");
        return arrayList;
    }

    private Map<String, List<LogEvent>> a(List<LogEvent> list) {
        o.a(a, "joinEventsByUrl start.");
        Map<String, List<LogEvent>> hashMap = new HashMap();
        h a = h.a(this.c);
        for (int i = 0; i < list.size(); i++) {
            LogEvent logEvent = (LogEvent) list.get(i);
            if (a.f(logEvent).a(this.c)) {
                String a2 = i.a(a.e(logEvent), a.d(logEvent));
                if (TextUtils.isEmpty(a2)) {
                    a2 = a(a2);
                }
                if (com.miui.analytics.internal.h.a) {
                    a2 = a2.replaceFirst("://api.xiaomi.com/", "://test.xiaomi.com/").replaceFirst("://api.ad.xiaomi.com/", "://test.ad.xiaomi.com/").replaceFirst("://log.ad.xiaomi.com/", "://test.log.ad.xiaomi.com/");
                }
                o.a(a, "url : " + a2);
                if (hashMap.get(a2) == null) {
                    hashMap.put(a2, new ArrayList());
                }
                ((List) hashMap.get(a2)).add(logEvent);
            }
        }
        o.a(a, "joinEventsByUrl end.");
        return hashMap;
    }

    private Map<Integer, List<LogEvent>> b(List<LogEvent> list) {
        o.a(a, "joinEventsByEventType start.");
        Map<Integer, List<LogEvent>> hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            LogEvent logEvent = (LogEvent) list.get(i);
            int o = logEvent.o();
            if (hashMap.get(Integer.valueOf(o)) == null) {
                hashMap.put(Integer.valueOf(o), new ArrayList());
            }
            ((List) hashMap.get(Integer.valueOf(o))).add(logEvent);
        }
        o.a(a, "joinEventsByEventType end.");
        return hashMap;
    }

    private Map<Integer, List<LogEvent>> c(List<LogEvent> list) {
        o.a(a, "joinEventsByIdType start.");
        Map<Integer, List<LogEvent>> hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            LogEvent logEvent = (LogEvent) list.get(i);
            int p = logEvent.p();
            if (hashMap.get(Integer.valueOf(p)) == null) {
                hashMap.put(Integer.valueOf(p), new ArrayList());
            }
            ((List) hashMap.get(Integer.valueOf(p))).add(logEvent);
        }
        o.a(a, "joinEventsByIdType end.");
        return hashMap;
    }

    private Map<String, List<LogEvent>> d(List<LogEvent> list) {
        o.a(a, "joinEventsByPackageName start.");
        Map<String, List<LogEvent>> hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            LogEvent logEvent = (LogEvent) list.get(i);
            String c = logEvent.c();
            if (hashMap.get(c) == null) {
                hashMap.put(c, new ArrayList());
            }
            ((List) hashMap.get(c)).add(logEvent);
        }
        o.a(a, "joinEventsByPackageName end.");
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.lang.String r5, java.lang.String r6, java.util.List<com.miui.analytics.internal.LogEvent> r7, boolean r8, int r9, int r10) {
        /*
        r4 = this;
        if (r7 == 0) goto L_0x000e;
    L_0x0002:
        r0 = android.text.TextUtils.isEmpty(r6);
        if (r0 != 0) goto L_0x000e;
    L_0x0008:
        r0 = r4.b(r6);
        if (r0 == 0) goto L_0x0010;
    L_0x000e:
        r0 = 1;
    L_0x000f:
        return r0;
    L_0x0010:
        r0 = "Dispatcher";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "sendEvents ";
        r1 = r1.append(r2);
        r2 = r7.size();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.miui.analytics.internal.util.o.a(r0, r1);
        r0 = r4.c;	 Catch:{ Exception -> 0x006a }
        com.miui.analytics.internal.util.c.h(r0, r5);	 Catch:{ Exception -> 0x006a }
        r0 = new com.miui.analytics.internal.service.j;	 Catch:{ Exception -> 0x006a }
        r1 = r4.c;	 Catch:{ Exception -> 0x006a }
        r0.<init>(r1, r6, r7);	 Catch:{ Exception -> 0x006a }
        r0.a(r8);	 Catch:{ Exception -> 0x006a }
        r0.a(r9);	 Catch:{ Exception -> 0x006a }
        r0.b(r10);	 Catch:{ Exception -> 0x006a }
        r0.b(r5);	 Catch:{ Exception -> 0x006a }
        r0 = r0.c();	 Catch:{ Exception -> 0x006a }
        if (r0 == 0) goto L_0x00a6;
    L_0x004a:
        r1 = r0.a();	 Catch:{ Exception -> 0x006a }
        if (r1 == 0) goto L_0x007d;
    L_0x0050:
        r2 = r7.iterator();	 Catch:{ Exception -> 0x006a }
    L_0x0054:
        r0 = r2.hasNext();	 Catch:{ Exception -> 0x006a }
        if (r0 == 0) goto L_0x009e;
    L_0x005a:
        r0 = r2.next();	 Catch:{ Exception -> 0x006a }
        r0 = (com.miui.analytics.internal.LogEvent) r0;	 Catch:{ Exception -> 0x006a }
        r3 = r4.c;	 Catch:{ Exception -> 0x006a }
        r3 = com.miui.analytics.internal.d.c.a(r3);	 Catch:{ Exception -> 0x006a }
        r3.a(r0);	 Catch:{ Exception -> 0x006a }
        goto L_0x0054;
    L_0x006a:
        r0 = move-exception;
        r1 = "Dispatcher";
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ all -> 0x0097 }
        r2 = "sendEvents e";
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x0097 }
        r0 = r4.c;
        com.miui.analytics.internal.util.c.g(r0);
    L_0x007b:
        r0 = 0;
        goto L_0x000f;
    L_0x007d:
        r2 = r7.iterator();	 Catch:{ Exception -> 0x006a }
    L_0x0081:
        r0 = r2.hasNext();	 Catch:{ Exception -> 0x006a }
        if (r0 == 0) goto L_0x009e;
    L_0x0087:
        r0 = r2.next();	 Catch:{ Exception -> 0x006a }
        r0 = (com.miui.analytics.internal.LogEvent) r0;	 Catch:{ Exception -> 0x006a }
        r3 = r4.c;	 Catch:{ Exception -> 0x006a }
        r3 = com.miui.analytics.internal.d.c.a(r3);	 Catch:{ Exception -> 0x006a }
        r3.b(r0);	 Catch:{ Exception -> 0x006a }
        goto L_0x0081;
    L_0x0097:
        r0 = move-exception;
        r1 = r4.c;
        com.miui.analytics.internal.util.c.g(r1);
        throw r0;
    L_0x009e:
        r0 = r4.c;
        com.miui.analytics.internal.util.c.g(r0);
        r0 = r1;
        goto L_0x000f;
    L_0x00a6:
        r0 = r4.c;
        com.miui.analytics.internal.util.c.g(r0);
        goto L_0x007b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.service.b.a(java.lang.String, java.lang.String, java.util.List, boolean, int, int):boolean");
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "null";
        }
        return str;
    }

    private boolean b(String str) {
        return "null".equals(str);
    }
}
