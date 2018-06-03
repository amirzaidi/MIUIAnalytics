package com.miui.analytics.internal.collection;

import android.app.usage.UsageEvents;
import android.app.usage.UsageEvents.Event;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.LongSparseArray;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.ae;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.z;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class m {
    public static boolean a = false;
    private static final String b = "UsageStatsHelper";

    public static class a {
        String a;
        c b;
        LongSparseArray<c> c;
    }

    interface b {
        Map<String, a> a(Context context);
    }

    public static class c {
        String a;
        long b;
        long c;
    }

    static class d implements b {
        d() {
        }

        public Map<String, a> a(Context context) {
            return b(context);
        }

        private static Map<String, a> b(Context context) {
            try {
                Class cls = Class.forName("com.android.internal.app.IUsageStats");
                Method method = Class.forName("com.android.internal.app.IUsageStats$Stub").getMethod("asInterface", new Class[]{IBinder.class});
                Object invoke = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", new Class[]{String.class}).invoke(null, new Object[]{"usagestats"});
                Object[] objArr = (Object[]) cls.getDeclaredMethod("getAllPkgUsageStats", new Class[0]).invoke(method.invoke(null, new Object[]{invoke}), new Object[0]);
                if (objArr == null || objArr.length == 0) {
                    return null;
                }
                Class cls2 = Class.forName("com.android.internal.os.PkgUsageStats");
                Field declaredField = cls2.getDeclaredField("packageName");
                declaredField.setAccessible(true);
                Field declaredField2 = cls2.getDeclaredField("usageTime");
                declaredField2.setAccessible(true);
                Field declaredField3 = cls2.getDeclaredField("launchCount");
                declaredField3.setAccessible(true);
                Map<String, a> hashMap = new HashMap();
                for (Object obj : objArr) {
                    int i = declaredField3.getInt(obj);
                    if (i > 0) {
                        String str = (String) declaredField.get(obj);
                        c cVar = new c();
                        cVar.a = str;
                        cVar.b = declaredField2.getLong(obj);
                        cVar.c = (long) i;
                        a aVar = new a();
                        aVar.a = str;
                        aVar.b = cVar;
                        hashMap.put(str, aVar);
                    }
                }
                return hashMap;
            } catch (Throwable e) {
                o.b(m.b, "queryToadyUsageStatsV19 exception:", e);
                return null;
            }
        }

        private static void c(Context context) {
            o.d(m.b, "\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            o.d(m.b, "~~~ printToadyUsages for android sdk " + VERSION.SDK_INT + " ~~~");
            Map b = b(context);
            o.d(m.b, "@@@ cusages: ");
            if (b == null || b.size() <= 0) {
                o.d(m.b, "usages empty");
                return;
            }
            for (a aVar : b.values()) {
                o.d(m.b, String.format("package:%s, totalTimeInForeground:%d, launchCount:%d", new Object[]{aVar.b.a, Long.valueOf(aVar.b.b), Long.valueOf(aVar.b.c)}));
            }
            o.d(m.b, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n");
        }
    }

    static class e implements b {

        static class a {
            long a;
            long b;

            a() {
            }

            public static long a(a aVar, a aVar2) {
                if (aVar.a >= aVar.b || aVar2.a >= aVar2.b || aVar.b <= aVar2.a || aVar2.b <= aVar.a) {
                    return 0;
                }
                if (aVar.a <= aVar2.a && aVar.b > aVar2.a && aVar.b <= aVar2.b) {
                    return aVar.b - aVar2.a;
                }
                if (aVar2.a <= aVar.a && aVar2.b > aVar.a && aVar2.b <= aVar.b) {
                    return aVar2.b - aVar.a;
                }
                if (aVar.a <= aVar2.a && aVar.b >= aVar2.b) {
                    return aVar2.b - aVar2.a;
                }
                if (aVar2.a <= aVar.a && aVar2.b >= aVar.b) {
                    return aVar.b - aVar.a;
                }
                if (!m.a) {
                    return 0;
                }
                RuntimeException runtimeException = new RuntimeException("never run here!!!");
                return 0;
            }
        }

        e() {
        }

        public Map<String, a> a(Context context) {
            return b(context);
        }

        private static long a() {
            if (VERSION.SDK_INT < 23) {
                return ac.d();
            }
            return ac.a();
        }

        private static long b() {
            if (VERSION.SDK_INT < 23) {
                return ac.e();
            }
            return ac.b();
        }

        public static Map<String, a> b(Context context) {
            return a(context, a(), b());
        }

        public static Map<String, a> a(Context context, long j, long j2) {
            o.a(m.b, "usage window beingTime: " + j + " endTime: " + j2);
            Map hashMap = new HashMap();
            List b = b(context, j, j2);
            Map d = d(b);
            b(j, hashMap, d);
            Map e = e(b);
            a(j, hashMap, e);
            if (m.a) {
                c(b);
                a(e);
                b(d);
            }
            return hashMap;
        }

        public static List<Event> c(Context context) {
            long a = a();
            long b = b();
            List arrayList = new ArrayList();
            List<Event> b2 = b(context, a, b);
            if (b2 != null) {
                for (Event event : b2) {
                    if (a(event)) {
                        arrayList.add(event);
                    } else {
                        o.a(m.b, String.format("Skip event pkg:%s,event:%d,ts:%d", new Object[]{event.getPackageName(), Integer.valueOf(event.getEventType()), Long.valueOf(event.getTimeStamp())}));
                    }
                }
            }
            if (m.a) {
                a((List) b2);
                b(arrayList);
                o.a(m.b, String.format("dayUsageEventSequence dayBegin:%d,%s, dayEnd:%d,%s", new Object[]{Long.valueOf(a), new Date(a).toString(), Long.valueOf(b), new Date(b)}));
            }
            return arrayList;
        }

        private static void a(List<Event> list) {
            o.d(m.b, "#### BEGIN print yesterday usage event raw sequence####");
            if (list != null) {
                for (Event event : list) {
                    o.d(m.b, String.format("RAW pkg:%s, event:%d, ts:%d", new Object[]{event.getPackageName(), Integer.valueOf(event.getEventType()), Long.valueOf(event.getTimeStamp())}));
                }
            }
            o.d(m.b, "#### END print yesterday usage event raw sequence####");
        }

        private static void b(List<Event> list) {
            o.d(m.b, "#### BEGIN print yesterday usage event sequence####");
            if (list != null) {
                for (Event event : list) {
                    o.d(m.b, String.format("EVENT pkg:%s, event:%d, ts:%d", new Object[]{event.getPackageName(), Integer.valueOf(event.getEventType()), Long.valueOf(event.getTimeStamp())}));
                }
            }
            o.d(m.b, "#### END print yesterday usage event sequence####");
        }

        private static void a(long j, Map<String, a> map, Map<String, List<a>> map2) {
            if (map2 != null) {
                for (Entry entry : map2.entrySet()) {
                    String str = (String) entry.getKey();
                    List<a> list = (List) entry.getValue();
                    a aVar = (a) map.get(str);
                    if (aVar != null) {
                        c cVar = aVar.b;
                        LongSparseArray longSparseArray = aVar.c;
                        for (int i = 0; i < 24; i++) {
                            long j2 = 0;
                            for (a aVar2 : list) {
                                a aVar3 = new a();
                                aVar3.a = (((long) i) * 3600000) + j;
                                aVar3.b = aVar3.a + 3600000;
                                j2 = a.a(aVar3, aVar2) + j2;
                            }
                            if (j2 > 0) {
                                c cVar2 = (c) longSparseArray.get((long) i);
                                if (cVar2 == null) {
                                    cVar2 = new c();
                                    cVar2.a = str;
                                    longSparseArray.put((long) i, cVar2);
                                }
                                cVar2.b = j2;
                                cVar.b += j2;
                            }
                        }
                    }
                }
            }
        }

        private static void b(long j, Map<String, a> map, Map<String, List<Long>> map2) {
            if (map2 != null) {
                for (Entry entry : map2.entrySet()) {
                    String str = (String) entry.getKey();
                    List<Long> list = (List) entry.getValue();
                    a aVar = new a();
                    aVar.a = str;
                    map.put(str, aVar);
                    c cVar = new c();
                    cVar.a = str;
                    aVar.b = cVar;
                    LongSparseArray longSparseArray = new LongSparseArray();
                    aVar.c = longSparseArray;
                    for (Long longValue : list) {
                        long longValue2 = (longValue.longValue() - j) / 3600000;
                        if (longValue2 >= 0 && longValue2 <= 23) {
                            c cVar2 = (c) longSparseArray.get(longValue2);
                            if (cVar2 == null) {
                                cVar2 = new c();
                                longSparseArray.put(longValue2, cVar2);
                                cVar2.a = str;
                            }
                            cVar2.c++;
                            cVar.c++;
                        }
                    }
                }
            }
        }

        private static void a(Map<String, List<a>> map) {
            if (map != null) {
                o.b(m.b, "### event intervals");
                for (Entry entry : map.entrySet()) {
                    String str = (String) entry.getKey();
                    for (a aVar : (List) entry.getValue()) {
                        o.b(m.b, "package:" + str + ", begin:" + aVar.a + " end:" + aVar.b);
                        if (!z.C()) {
                            try {
                                Thread.sleep(10);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                o.b(m.b, "###");
            }
        }

        private static void b(Map<String, List<Long>> map) {
            if (map != null) {
                o.b(m.b, "### new launch time");
                for (Entry entry : map.entrySet()) {
                    String str = (String) entry.getKey();
                    for (Long longValue : (List) entry.getValue()) {
                        o.b(m.b, "package:" + str + ", newLaunchTime:" + longValue.longValue());
                        if (!z.C()) {
                            try {
                                Thread.sleep(10);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                o.b(m.b, "###");
            }
        }

        private static void c(List<Event> list) {
            if (list != null) {
                o.b(m.b, "### stateful usage");
                for (Event event : list) {
                    o.b(m.b, "p:" + event.getPackageName() + ", c:" + event.getClassName() + ", t:" + event.getTimeStamp() + ", eventType:" + event.getEventType());
                    if (!z.C()) {
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                        }
                    }
                }
                o.b(m.b, "###");
            }
        }

        private static Map<String, List<Long>> d(List<Event> list) {
            Object obj = null;
            if (list == null) {
                return null;
            }
            Map<String, List<Long>> hashMap = new HashMap();
            Object obj2 = null;
            for (Event event : list) {
                String packageName = event.getPackageName();
                int eventType = event.getEventType();
                if (a(event)) {
                    Object obj3;
                    if ((UploadUsageHelper.a(event) || eventType != 1 || packageName.equals(obj2)) && !(UploadUsageHelper.a(event) && eventType == 1 && UploadUsageHelper.e.equals(obj2) && !packageName.equals(obj))) {
                        obj3 = obj;
                    } else {
                        List list2 = (List) hashMap.get(packageName);
                        if (list2 == null) {
                            list2 = new ArrayList();
                            hashMap.put(packageName, list2);
                        }
                        list2.add(Long.valueOf(event.getTimeStamp()));
                        obj3 = obj2;
                        obj2 = packageName;
                    }
                    obj = obj3;
                }
            }
            return hashMap;
        }

        private static Map<String, List<a>> e(List<Event> list) {
            if (list == null) {
                return null;
            }
            Map<String, List<a>> hashMap = new HashMap();
            Map hashMap2 = new HashMap();
            for (Event event : list) {
                if (a(event)) {
                    String packageName = event.getPackageName();
                    if (event.getEventType() == 2) {
                        Event event2 = (Event) hashMap2.get(packageName);
                        if (event2 != null && event2.getEventType() == 1) {
                            long timeStamp = event2.getTimeStamp();
                            long timeStamp2 = event.getTimeStamp();
                            if (timeStamp > 0 && timeStamp2 >= timeStamp) {
                                List list2 = (List) hashMap.get(packageName);
                                if (list2 == null) {
                                    list2 = new ArrayList();
                                    hashMap.put(packageName, list2);
                                }
                                a aVar = new a();
                                aVar.a = timeStamp;
                                aVar.b = timeStamp2;
                                list2.add(aVar);
                            }
                        }
                    }
                    hashMap2.put(packageName, event);
                }
            }
            return hashMap;
        }

        private static boolean a(Event event) {
            if (TextUtils.isEmpty(event.getPackageName()) || (event.getEventType() != 1 && event.getEventType() != 2)) {
                return false;
            }
            return true;
        }

        private static List<Event> b(Context context, long j, long j2) {
            try {
                UsageEvents queryEvents = ((UsageStatsManager) context.getSystemService("usagestats")).queryEvents(j, j2);
                if (queryEvents != null) {
                    List<Event> arrayList = new ArrayList();
                    while (queryEvents.hasNextEvent()) {
                        Event event = new Event();
                        queryEvents.getNextEvent(event);
                        if (a(event)) {
                            arrayList.add(event);
                        }
                    }
                    if (m.a) {
                        o.a(m.b, "### origin event###");
                        for (Event event2 : arrayList) {
                            o.b(m.b, "p:" + event2.getPackageName() + ", c:" + event2.getClassName() + ", t:" + event2.getTimeStamp() + ", eventType:" + event2.getEventType());
                            if (!z.C()) {
                                try {
                                    Thread.sleep(10);
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                    return UploadUsageHelper.a(context).a(j, j2, (List) arrayList);
                }
            } catch (Throwable e2) {
                ae.a(context, m.b, "queryStatefulEvent exception:", e2);
            }
            return new ArrayList();
        }

        private static void d(Context context) {
            o.d(m.b, "\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            o.d(m.b, "~~~ printYesterdayUsagesAndEvents for android sdk " + VERSION.SDK_INT + " ~~~");
            Map b = b(context);
            o.d(m.b, "@@@ cusages: ");
            if (b == null || b.size() <= 0) {
                o.d(m.b, "usages empty");
                return;
            }
            for (a aVar : b.values()) {
                o.d(m.b, String.format("package:%s, totalTimeInForeground:%d, launchCount:%d", new Object[]{aVar.b.a, Long.valueOf(aVar.b.b), Long.valueOf(aVar.b.c)}));
                LongSparseArray longSparseArray = aVar.c;
                if (longSparseArray != null) {
                    for (int i = 0; i < longSparseArray.size(); i++) {
                        c cVar = (c) longSparseArray.get(longSparseArray.keyAt(i));
                        o.d(m.b, String.format("\th:%d, totalTimeInForeground:%d, launchCount:%d", new Object[]{Long.valueOf(r6), Long.valueOf(cVar.b), Long.valueOf(cVar.c)}));
                    }
                }
            }
            o.d(m.b, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n");
        }
    }

    public static JSONArray a(Context context) {
        try {
            return a(b(context));
        } catch (Throwable e) {
            ae.a(context, b, "getAssembleDailyUsage method1 exception:", e);
            return new JSONArray();
        }
    }

    public static JSONArray a(Context context, long j, long j2) {
        try {
            if (VERSION.SDK_INT <= 19) {
                return new JSONArray();
            }
            return a(e.a(context, j, j2));
        } catch (Throwable e) {
            ae.a(context, b, "getAssembleDailyUsage method2 exception:", e);
            return new JSONArray();
        }
    }

    private static JSONArray a(Map<String, a> map) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (map != null && map.size() > 0) {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                a aVar = (a) entry.getValue();
                if (aVar.b != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONArray.put(jSONObject);
                    jSONObject.put(k.b, str);
                    jSONObject.put(k.c, aVar.b.b);
                    jSONObject.put(k.d, aVar.b.c);
                    if (VERSION.SDK_INT >= 16 && aVar.c != null && aVar.c.size() > 0) {
                        for (int i = 0; i < aVar.c.size(); i++) {
                            long keyAt = aVar.c.keyAt(i);
                            c cVar = (c) aVar.c.get(keyAt);
                            if (cVar != null) {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put(k.c, cVar.b);
                                jSONObject2.put(k.d, cVar.c);
                                jSONObject.put(String.valueOf(keyAt), jSONObject2);
                            }
                        }
                    }
                }
            }
        }
        if (a) {
            o.b(b, "##### assembleDailyUsage :\n" + jSONArray.toString());
        }
        return jSONArray;
    }

    public static Map<String, a> b(Context context) {
        b dVar;
        if (VERSION.SDK_INT <= 19) {
            dVar = new d();
        } else {
            dVar = new e();
        }
        return dVar.a(context);
    }

    public static List<Event> c(Context context) {
        if (VERSION.SDK_INT > 19) {
            return e.c(context);
        }
        o.a(b, "UsageEventSequence not available for SDK " + VERSION.SDK_INT);
        return new ArrayList();
    }

    public static void d(final Context context) {
        try {
            if (a) {
                ab.a(new Runnable() {
                    public void run() {
                        if (VERSION.SDK_INT <= 19) {
                            o.a(m.b, "print today usage, API:" + VERSION.SDK_INT);
                            m.a(context);
                            return;
                        }
                        o.a(m.b, "print today usage, API:" + VERSION.SDK_INT);
                        m.a(context, ac.d(), ac.e());
                    }
                });
            }
        } catch (Exception e) {
        }
    }
}
