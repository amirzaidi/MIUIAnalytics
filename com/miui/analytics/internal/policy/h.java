package com.miui.analytics.internal.policy;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.l;
import com.miui.analytics.internal.policy.PolicyGroup.Serializer;
import com.miui.analytics.internal.policy.a.b;
import com.miui.analytics.internal.policy.a.d;
import com.miui.analytics.internal.policy.a.e;
import com.miui.analytics.internal.policy.a.i;
import com.miui.analytics.internal.service.g;
import com.miui.analytics.internal.service.j;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.af;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.s;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class h {
    public static final int a = 60000;
    public static final int b = 600000;
    private static final String c = "PolicyManager";
    private static final String d = "/policy.cache";
    private static volatile h e;
    private static final List<String> j = new ArrayList();
    private Context f;
    private Map<String, PolicyGroup> g = new ConcurrentHashMap();
    private Map<String, Long> h = new ConcurrentHashMap();
    private Object i = new Object();

    private class a extends Thread {
        final /* synthetic */ h a;
        private String b;

        public a(h hVar, String str) {
            this.a = hVar;
            this.b = str;
        }

        public void run() {
            try {
                if (!n.a(this.a.f, h.c)) {
                    g c = new com.miui.analytics.internal.service.a(this.a.f, this.b).c();
                    if (c != null && c.a()) {
                        this.a.a(this.b, (PolicyGroup) c.a);
                    }
                }
            } catch (Throwable e) {
                Log.e(o.a(h.c), "PolicyLoader.run() e: ", e);
            }
        }
    }

    static {
        j.add(f.f);
        j.add(f.g);
        j.add(f.q);
        j.add(f.i);
        j.add(f.k);
    }

    private h(Context context) {
        this.f = c.a(context);
        if (this.f == null) {
            throw new IllegalArgumentException("Context must contain application context.");
        }
    }

    public static h a(Context context) {
        synchronized (h.class) {
            if (e == null) {
                e = new h(context);
            }
        }
        return e;
    }

    private String f() {
        return this.f.getCacheDir().getAbsolutePath();
    }

    private String d(String str) {
        String str2 = f() + "/" + af.a(str);
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdir();
        }
        return str2 + d;
    }

    public void a() {
        File[] listFiles = new File(f()).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    File file2 = new File(file, d);
                    if (file2.exists()) {
                        file2.setLastModified(0);
                    }
                }
            }
        }
    }

    public void a(String str) {
        synchronized (this.i) {
            a(str, true);
        }
    }

    public PolicyGroup b(String str) {
        return a(str, false);
    }

    private PolicyGroup a(String str, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            File file = new File(d(str));
            if (file.exists()) {
                if (b(file.lastModified())) {
                    b(str, z);
                }
                if (this.g.get(str) == null) {
                    Object a = s.a(file.getAbsolutePath());
                    if ((a instanceof Serializer) && ((Serializer) a).a != null) {
                        this.g.put(str, new PolicyGroup((Serializer) a));
                    }
                }
            } else if (this.g.get(str) == null) {
                b(str, z);
            }
            return (PolicyGroup) this.g.get(str);
        } catch (Exception e) {
            return null;
        }
    }

    private void b(String str, boolean z) {
        if (!n.a(this.f, c)) {
            Long l = (Long) this.h.get(str);
            if (l == null || System.currentTimeMillis() - l.longValue() > 600000) {
                this.h.put(str, Long.valueOf(System.currentTimeMillis()));
                if (z) {
                    new a(this, str).run();
                } else {
                    new a(this, str).start();
                }
            }
        }
    }

    public a c(String str) {
        PolicyGroup b = b(str);
        if (b != null) {
            return b.e;
        }
        return null;
    }

    public f a(String str, LogEvent logEvent) {
        PolicyGroup b = b(str);
        if (b != null) {
            return b.a(logEvent);
        }
        return null;
    }

    public f a(String str, String str2) {
        PolicyGroup b = b(str);
        if (b != null) {
            return b.a(str2);
        }
        return null;
    }

    private void a(String str, PolicyGroup policyGroup) {
        if (!TextUtils.isEmpty(str) && policyGroup != null) {
            this.g.put(str, policyGroup);
            s.a(d(str), policyGroup.a());
        }
    }

    private boolean b(long j) {
        if (ac.a(j, ac.b + (new Random().nextLong() % (ac.b / 2)))) {
            return true;
        }
        return false;
    }

    public String a(LogEvent logEvent) {
        if (logEvent != null) {
            f a = a(logEvent.c(), logEvent);
            if (a != null) {
                return a.h();
            }
        }
        return "";
    }

    public j b(LogEvent logEvent) {
        if (logEvent != null) {
            f a = a(logEvent.c(), logEvent);
            if (a != null) {
                return new j(a.b());
            }
        }
        return new j(1.0d);
    }

    public j b(String str, String str2) {
        f a = a(str, str2);
        if (a != null) {
            return new j(a.b());
        }
        return new j(1.0d);
    }

    public e b() {
        a c = c(this.f.getPackageName());
        if (c == null || c.b() == null) {
            return new b();
        }
        return c.b();
    }

    public g c(LogEvent logEvent) {
        String str = f.m;
        f a = a(logEvent.c(), logEvent);
        if (a != null && f.m.equals(a.g())) {
            return new com.miui.analytics.internal.policy.a.f(this.f, logEvent);
        }
        if (logEvent instanceof com.miui.analytics.internal.a) {
            return new com.miui.analytics.internal.policy.a.f(this.f, logEvent);
        }
        return new e(this.f, logEvent);
    }

    public com.miui.analytics.internal.c.a.a a(Context context, LogEvent logEvent, com.miui.analytics.internal.c.b bVar) {
        if (h(logEvent)) {
            return new com.miui.analytics.internal.c.a.c(context, bVar);
        }
        return new com.miui.analytics.internal.c.a.b(context, bVar);
    }

    public boolean d(LogEvent logEvent) {
        if (logEvent != null) {
            f a = a(logEvent.c(), logEvent);
            if (a != null) {
                return a.e();
            }
        }
        return n.a();
    }

    public String e(LogEvent logEvent) {
        PolicyGroup b = b(logEvent.c());
        if (b == null || b.a(logEvent) == null) {
            return j.c;
        }
        return b.a(logEvent).d();
    }

    public i f(LogEvent logEvent) {
        PolicyGroup b = b(logEvent.c());
        if (b == null || b.a(logEvent) == null) {
            if (b == null && (logEvent instanceof com.miui.analytics.internal.a)) {
                return new d();
            }
        } else if (b.a(logEvent).c() != null) {
            return b.a(logEvent).c();
        }
        return b.a();
    }

    public i c(String str, String str2) {
        PolicyGroup b = b(str);
        if (b != null) {
            f a = b.a(str2);
            if (!(a == null || a.c() == null)) {
                return a.c();
            }
        }
        return new d();
    }

    public d c() {
        PolicyGroup b = b(this.f.getPackageName());
        if (b == null || b.e == null || b.e.f() == null) {
            return new d(g(), h());
        }
        return new d(g(), b.e.f());
    }

    private l g() {
        a c = c(this.f.getPackageName());
        if (c == null || c.d() == null) {
            return new l(j());
        }
        return new l(c.d());
    }

    public l a(long j) {
        a c = c(this.f.getPackageName());
        List i;
        if (c == null || c.e() == null || c.e().size() <= 0) {
            i = i();
        } else {
            i = c.e();
        }
        if (i != null) {
            for (k kVar : i) {
                if (kVar instanceof i) {
                    ((i) kVar).a(j);
                }
            }
        }
        return new l(i);
    }

    private List<c> h() {
        List<c> arrayList = new ArrayList();
        arrayList.add(new com.miui.analytics.internal.policy.a.c());
        return arrayList;
    }

    private List<k> i() {
        List<k> arrayList = new ArrayList();
        arrayList.add(new i(b));
        arrayList.add(new com.miui.analytics.internal.policy.a.h(10));
        arrayList.add(new com.miui.analytics.internal.policy.a.j(100000));
        return arrayList;
    }

    private List<k> j() {
        List<k> arrayList = new ArrayList();
        arrayList.add(new com.miui.analytics.internal.policy.a.h(10000));
        arrayList.add(new com.miui.analytics.internal.policy.a.j(10000000));
        return arrayList;
    }

    public boolean d() {
        a c = c(this.f.getPackageName());
        if (c != null) {
            return c.g();
        }
        return false;
    }

    public boolean g(LogEvent logEvent) {
        if (logEvent != null) {
            f a = a(logEvent.c(), logEvent);
            if (a != null) {
                return a.f();
            }
            if (a == null && (logEvent instanceof com.miui.analytics.internal.a)) {
                return false;
            }
        }
        return true;
    }

    public boolean h(LogEvent logEvent) {
        if (logEvent != null) {
            f a = a(logEvent.c(), logEvent);
            String str = f.m;
            if ((a != null && f.m.equals(a.g())) || (logEvent instanceof com.miui.analytics.internal.a)) {
                return true;
            }
            if (a == null && j.contains(logEvent.b())) {
                return true;
            }
        }
        return false;
    }

    public int e() {
        int i;
        PolicyGroup b = b(this.f.getPackageName());
        if (b == null || b.e == null) {
            i = b;
        } else {
            i = b.e.c();
        }
        return l.a(i);
    }

    public int i(LogEvent logEvent) {
        PolicyGroup b = b(logEvent.c());
        if (b == null || b.a(logEvent) == null) {
            return 0;
        }
        return b.a(logEvent).a();
    }

    public static List<g> a(List<g> list) {
        if (list != null) {
            try {
                List<g> arrayList = new ArrayList();
                HashMap hashMap = new HashMap();
                for (int i = 0; i < list.size(); i++) {
                    g gVar = (g) list.get(i);
                    if (gVar != null) {
                        g gVar2 = (g) hashMap.get(gVar.getClass().getName());
                        if (gVar2 != null) {
                            gVar2.a(gVar);
                        } else {
                            arrayList.add(gVar);
                            hashMap.put(gVar.getClass().getName(), gVar);
                        }
                    }
                }
                return arrayList;
            } catch (Throwable e) {
                Log.e(o.a(c), "mergeSenders e", e);
            }
        }
        return null;
    }
}
