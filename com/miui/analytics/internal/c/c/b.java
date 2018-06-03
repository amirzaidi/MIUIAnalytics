package com.miui.analytics.internal.c.c;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.service.i;
import com.miui.analytics.internal.service.j;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class b extends a {
    private static final String c = "Packer";

    public b(Context context, com.miui.analytics.internal.c.b bVar) {
        super(context, bVar);
    }

    protected List<LogEvent> a(List<LogEvent> list) {
        o.a(c, "---------------checkPackConditions: " + (list == null ? "null" : Integer.valueOf(list.size())));
        List<LogEvent> arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        try {
            if (n.e()) {
                return arrayList;
            }
            if (n.a(this.a, c)) {
                return arrayList;
            }
            h a = h.a(this.a);
            for (LogEvent logEvent : list) {
                if (!a.b(logEvent.c(), logEvent.b()).a()) {
                    o.a(c, "key :" + logEvent.b() + " not meet sample");
                } else if (a.f(logEvent).a(this.a)) {
                    arrayList.add(logEvent);
                } else {
                    o.a(c, "key :" + logEvent.b() + " not meet privacyPolicy");
                }
            }
            return arrayList;
        } catch (Throwable e) {
            Log.e(o.a(c), "checkPackConditions e", e);
        }
    }

    protected void c(List<LogEvent> list) {
        o.a(c, "---------------joinEvents---------------");
        if (list != null) {
            try {
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    LogEvent logEvent = (LogEvent) list.get(i);
                    if (g.a(this.a).a(logEvent.c(), logEvent.b())) {
                        arrayList.add(logEvent);
                    } else {
                        arrayList2.add(logEvent);
                    }
                }
                a(arrayList, true);
                a(arrayList2, false);
            } catch (Throwable e) {
                Log.e(o.a(c), "joinEvents e", e);
            }
        }
    }

    private void a(List<LogEvent> list, boolean z) {
        o.a(c, "---------------joinWithDeviceId---------------");
        if (list != null) {
            try {
                o.a(c, "joinWithDeviceId " + z + ":" + list.size());
                for (Entry entry : f(list).entrySet()) {
                    for (Entry entry2 : e(list).entrySet()) {
                        for (Entry entry3 : g((List) entry2.getValue()).entrySet()) {
                            Map d = d((List) entry3.getValue());
                            h a = h.a(this.a);
                            for (Entry entry4 : d.entrySet()) {
                                for (List a2 : a.b().a((List) entry4.getValue())) {
                                    String str = (String) entry2.getKey();
                                    String str2 = (String) entry4.getKey();
                                    a(str, str2, a2, z, ((Integer) entry.getKey()).intValue(), ((Integer) entry3.getKey()).intValue());
                                }
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                Log.e(o.a(c), "joinWithDeviceId e", e);
            }
        }
    }

    private Map<String, List<LogEvent>> d(List<LogEvent> list) {
        Map<String, List<LogEvent>> hashMap = new HashMap();
        try {
            h a = h.a(this.a);
            for (LogEvent logEvent : list) {
                String a2 = i.a(a.e(logEvent), a.d(logEvent));
                if (TextUtils.isEmpty(a2)) {
                    a2 = a(a2);
                }
                if (com.miui.analytics.internal.h.a) {
                    a2 = a2.replaceFirst("://api.xiaomi.com/", "://test.xiaomi.com/").replaceFirst("://api.ad.xiaomi.com/", "://test.ad.xiaomi.com/").replaceFirst("://log.ad.xiaomi.com/", "://test.log.ad.xiaomi.com/");
                }
                o.a(c, "url : " + a2);
                if (hashMap.get(a2) == null) {
                    hashMap.put(a2, new ArrayList());
                }
                ((List) hashMap.get(a2)).add(logEvent);
            }
        } catch (Throwable e) {
            Log.e(o.a(c), "joinWithUrl e", e);
        }
        return hashMap;
    }

    private Map<String, List<LogEvent>> e(List<LogEvent> list) {
        o.a(c, "joinEventsByPackageName start.");
        Map<String, List<LogEvent>> hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            LogEvent logEvent = (LogEvent) list.get(i);
            String c = logEvent.c();
            if (hashMap.get(c) == null) {
                hashMap.put(c, new ArrayList());
            }
            ((List) hashMap.get(c)).add(logEvent);
        }
        o.a(c, "joinEventsByPackageName end.");
        return hashMap;
    }

    private Map<Integer, List<LogEvent>> f(List<LogEvent> list) {
        o.a(c, "joinEventsByEventType start.");
        Map<Integer, List<LogEvent>> hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            LogEvent logEvent = (LogEvent) list.get(i);
            int o = logEvent.o();
            if (hashMap.get(Integer.valueOf(o)) == null) {
                hashMap.put(Integer.valueOf(o), new ArrayList());
            }
            ((List) hashMap.get(Integer.valueOf(o))).add(logEvent);
        }
        o.a(c, "joinEventsByEventType end.");
        return hashMap;
    }

    private Map<Integer, List<LogEvent>> g(List<LogEvent> list) {
        o.a(c, "joinEventsByIdType start.");
        Map<Integer, List<LogEvent>> hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            LogEvent logEvent = (LogEvent) list.get(i);
            int p = logEvent.p();
            if (hashMap.get(Integer.valueOf(p)) == null) {
                hashMap.put(Integer.valueOf(p), new ArrayList());
            }
            ((List) hashMap.get(Integer.valueOf(p))).add(logEvent);
        }
        o.a(c, "joinEventsByIdType end.");
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void a(java.lang.String r4, java.lang.String r5, java.util.List<com.miui.analytics.internal.LogEvent> r6, boolean r7, int r8, int r9) {
        /*
        r3 = this;
        if (r6 == 0) goto L_0x000e;
    L_0x0002:
        r0 = android.text.TextUtils.isEmpty(r5);	 Catch:{ Exception -> 0x0060 }
        if (r0 != 0) goto L_0x000e;
    L_0x0008:
        r0 = r3.b(r5);	 Catch:{ Exception -> 0x0060 }
        if (r0 == 0) goto L_0x0014;
    L_0x000e:
        r0 = r3.a;
        com.miui.analytics.internal.util.c.g(r0);
    L_0x0013:
        return;
    L_0x0014:
        r0 = r3.a;	 Catch:{ Exception -> 0x0060 }
        com.miui.analytics.internal.util.c.h(r0, r4);	 Catch:{ Exception -> 0x0060 }
        r0 = new com.miui.analytics.internal.service.j;	 Catch:{ Exception -> 0x0060 }
        r1 = r3.a;	 Catch:{ Exception -> 0x0060 }
        r0.<init>(r1, r5, r6);	 Catch:{ Exception -> 0x0060 }
        r0.a(r7);	 Catch:{ Exception -> 0x0060 }
        r0.a(r8);	 Catch:{ Exception -> 0x0060 }
        r0.b(r4);	 Catch:{ Exception -> 0x0060 }
        r0.b(r9);	 Catch:{ Exception -> 0x0060 }
        r1 = r3.b;	 Catch:{ Exception -> 0x0060 }
        r0.a(r1);	 Catch:{ Exception -> 0x0060 }
        r0 = r0.c();	 Catch:{ Exception -> 0x0060 }
        if (r0 == 0) goto L_0x00a4;
    L_0x0037:
        r0 = r0.a();	 Catch:{ Exception -> 0x0060 }
        if (r0 == 0) goto L_0x0072;
    L_0x003d:
        r0 = r3.a;	 Catch:{ Exception -> 0x0060 }
        r0 = com.miui.analytics.internal.b.k.a(r0);	 Catch:{ Exception -> 0x0060 }
        r0.h(r6);	 Catch:{ Exception -> 0x0060 }
        r1 = r6.iterator();	 Catch:{ Exception -> 0x0060 }
    L_0x004a:
        r0 = r1.hasNext();	 Catch:{ Exception -> 0x0060 }
        if (r0 == 0) goto L_0x00a4;
    L_0x0050:
        r0 = r1.next();	 Catch:{ Exception -> 0x0060 }
        r0 = (com.miui.analytics.internal.LogEvent) r0;	 Catch:{ Exception -> 0x0060 }
        r2 = r3.a;	 Catch:{ Exception -> 0x0060 }
        r2 = com.miui.analytics.internal.d.c.a(r2);	 Catch:{ Exception -> 0x0060 }
        r2.a(r0);	 Catch:{ Exception -> 0x0060 }
        goto L_0x004a;
    L_0x0060:
        r0 = move-exception;
        r1 = "Packer";
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ all -> 0x009d }
        r2 = "sendEvents e";
        android.util.Log.e(r1, r2, r0);	 Catch:{ all -> 0x009d }
        r0 = r3.a;
        com.miui.analytics.internal.util.c.g(r0);
        goto L_0x0013;
    L_0x0072:
        r0 = r3.a;	 Catch:{ Exception -> 0x0060 }
        r0 = com.miui.analytics.internal.util.r.b(r0);	 Catch:{ Exception -> 0x0060 }
        if (r0 == 0) goto L_0x0083;
    L_0x007a:
        r0 = r3.a;	 Catch:{ Exception -> 0x0060 }
        r0 = com.miui.analytics.internal.b.k.a(r0);	 Catch:{ Exception -> 0x0060 }
        r0.i(r6);	 Catch:{ Exception -> 0x0060 }
    L_0x0083:
        r1 = r6.iterator();	 Catch:{ Exception -> 0x0060 }
    L_0x0087:
        r0 = r1.hasNext();	 Catch:{ Exception -> 0x0060 }
        if (r0 == 0) goto L_0x00a4;
    L_0x008d:
        r0 = r1.next();	 Catch:{ Exception -> 0x0060 }
        r0 = (com.miui.analytics.internal.LogEvent) r0;	 Catch:{ Exception -> 0x0060 }
        r2 = r3.a;	 Catch:{ Exception -> 0x0060 }
        r2 = com.miui.analytics.internal.d.c.a(r2);	 Catch:{ Exception -> 0x0060 }
        r2.b(r0);	 Catch:{ Exception -> 0x0060 }
        goto L_0x0087;
    L_0x009d:
        r0 = move-exception;
        r1 = r3.a;
        com.miui.analytics.internal.util.c.g(r1);
        throw r0;
    L_0x00a4:
        r0 = r3.a;
        com.miui.analytics.internal.util.c.g(r0);
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.c.c.b.a(java.lang.String, java.lang.String, java.util.List, boolean, int, int):void");
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return j.c;
        }
        return str;
    }

    private boolean b(String str) {
        return "null".equals(str);
    }
}
