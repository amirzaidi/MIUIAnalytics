package com.miui.analytics.internal.collection;

import android.app.usage.UsageEvents.Event;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.b.k;
import com.miui.analytics.internal.e;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.j;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import org.json.JSONObject;

public class UploadUsageHelper {
    public static final String a = "usage_sequence";
    public static final String b = "usage";
    public static final String c = "installed";
    public static final String d = "[]";
    public static final String e = "com.miui.hybrid";
    private static final String f = "UUsageHelper";
    private static final String g = "usage_";
    private static final String h = "usage_sequence_";
    private static volatile UploadUsageHelper i;
    private Context j;
    private String k;
    private String l;
    private String m;

    public static class HybridInfo implements Serializable {
        static final long serialVersionUID = 1;
        public String a;
        public long b;
        public String c;

        public HybridInfo(String str, long j, String str2) {
            this.a = str;
            this.b = j;
            this.c = str2;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{");
            stringBuilder.append("p:" + this.a + ", ");
            stringBuilder.append("t:" + this.b + ", ");
            stringBuilder.append("c:" + this.c);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    public class a {
        public String a = "";
        public String b = "";
        public String c = "";
        final /* synthetic */ UploadUsageHelper d;

        public a(UploadUsageHelper uploadUsageHelper) {
            this.d = uploadUsageHelper;
        }
    }

    public static UploadUsageHelper a(Context context) {
        if (i == null) {
            synchronized (UploadUsageHelper.class) {
                if (i == null) {
                    i = new UploadUsageHelper(context);
                }
            }
        }
        return i;
    }

    private UploadUsageHelper(Context context) {
        try {
            this.j = c.a(context);
            c();
        } catch (Throwable e) {
            Log.e(o.a(f), "UploadUsageHelper exception", e);
        }
    }

    private String c() {
        if (TextUtils.isEmpty(this.k) && this.j != null) {
            this.k = this.j.getFilesDir() + "/usage";
        }
        return this.k;
    }

    private String d() {
        if (TextUtils.isEmpty(this.l) && this.j != null) {
            this.l = this.j.getFilesDir() + "/usageSequence";
        }
        return this.l;
    }

    private String e() {
        if (TextUtils.isEmpty(this.m) && this.j != null) {
            this.m = this.j.getFilesDir() + "/installed";
        }
        return this.m;
    }

    public void a() {
        try {
            v vVar = new v(this.j, u.d);
            if (!vVar.b(u.u, false) && "2.4.0".equals(e.b(this.j))) {
                File file = new File(this.j.getFilesDir().getAbsolutePath() + "/" + this.j.getFilesDir().getAbsolutePath());
                o.a(f, "fixUsageV2FileError dir:" + file.getAbsolutePath());
                b(file);
                vVar.a(u.u, true);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "fixUsageV2FileError exception:", e);
        }
    }

    private static void b(File file) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (File b : listFiles) {
                    b(b);
                }
                file.delete();
            }
        }
    }

    public void a(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str2) && this.j != null) {
                long d;
                File file = new File(str.equals(a) ? d() : c());
                if (!file.exists()) {
                    file.mkdirs();
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                if (VERSION.SDK_INT <= 22) {
                    d = ac.d();
                } else {
                    d = ac.a();
                }
                j jVar = new j(this.j, file.getAbsolutePath(), (str.equals(a) ? h : g) + simpleDateFormat.format(new Date(d)));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f.J, d);
                jSONObject.put("u", str2);
                jVar.a(jSONObject.toString());
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "save exception", e);
        }
    }

    public void a(String str) {
        try {
            new j(this.j, e(), c).a(str);
        } catch (Throwable e) {
            Log.e(o.a(f), "saveInstalledPackages exception", e);
        }
    }

    public ArrayList<a> b(String str) {
        try {
            File file = new File(str.equals(a) ? d() : c());
            if (file == null || !file.exists()) {
                return null;
            }
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                return null;
            }
            ArrayList<a> arrayList = new ArrayList();
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    if (((!file2.canRead() ? 1 : 0) | (!file2.exists() ? 1 : 0)) != 0) {
                        continue;
                    } else {
                        j jVar = new j(this.j, file.getAbsolutePath(), file2.getName());
                        try {
                            a aVar = new a(this);
                            Object a = jVar.a();
                            if (!TextUtils.isEmpty(a)) {
                                JSONObject jSONObject = new JSONObject(a);
                                long j = jSONObject.getLong(f.J);
                                if (!ac.a(j)) {
                                    if (System.currentTimeMillis() - j > ac.b * ((long) g.m())) {
                                        a(file2);
                                        o.a(f, "配置的丢弃天数（默认3天）以前的usage数据，直接删除不再上报:" + file2.getAbsolutePath());
                                    } else {
                                        aVar.c = jSONObject.getString("u");
                                        aVar.a = file2.getName();
                                        aVar.b = file2.getAbsolutePath();
                                        arrayList.add(aVar);
                                    }
                                }
                            }
                        } catch (Throwable e) {
                            Log.e(o.a(f), "getNotUploadUsageExceptToday exception", e);
                        }
                    }
                }
            }
            return arrayList;
        } catch (Throwable e2) {
            Log.e(o.a(f), "getNotUploadUsageExceptToday exception", e2);
            return null;
        }
    }

    public String b() {
        try {
            String a = new j(this.j, e(), c).a();
            if (TextUtils.isEmpty(a)) {
                return "[]";
            }
            return a;
        } catch (Throwable e) {
            Log.e(o.a(f), "getInstalledPackages", e);
            return "[]";
        }
    }

    public boolean a(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        o.a(f, "delete file:" + file.getAbsolutePath());
        return file.delete();
    }

    public boolean c(String str) {
        return a(new File(str));
    }

    private String b(Event event) {
        return event.getPackageName() + "," + event.getClassName() + "," + event.getTimeStamp() + "," + event.getEventType();
    }

    private ArrayList<ArrayList<Event>> a(Comparator<Event> comparator, List<Event> list) {
        ArrayList<ArrayList<Event>> arrayList = new ArrayList();
        try {
            Event event;
            List arrayList2 = new ArrayList();
            for (Event event2 : list) {
                if (c(event2)) {
                    arrayList2.add(event2);
                }
            }
            Collections.sort(arrayList2, comparator);
            int i = 0;
            while (i < arrayList2.size()) {
                event2 = (Event) arrayList2.get(i);
                if (c(event2) && event2.getEventType() == 1) {
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(event2);
                    i++;
                    if (i < arrayList2.size()) {
                        event2 = (Event) arrayList2.get(i);
                        if (c(event2) && event2.getEventType() == 2) {
                            arrayList3.add(event2);
                        }
                    }
                    arrayList.add(arrayList3);
                }
                i++;
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getHybridEventsPairList e", e);
        }
        return arrayList;
    }

    private ArrayList<ArrayList<Event>> a(Comparator<Event> comparator, List<HybridInfo> list, ArrayList<ArrayList<Event>> arrayList) {
        try {
            for (HybridInfo hybridInfo : list) {
                ArrayList arrayList2;
                Object a;
                int i;
                Event a2 = a(hybridInfo.a, hybridInfo.c, hybridInfo.b, 1);
                int i2 = 0;
                while (i2 < arrayList.size()) {
                    arrayList2 = (ArrayList) arrayList.get(i2);
                    if (arrayList2.size() != 0) {
                        Collections.sort(arrayList2, comparator);
                        if (a2.getClassName().equals(((Event) arrayList2.get(0)).getClassName()) && a2.getTimeStamp() >= ((Event) arrayList2.get(0)).getTimeStamp() && a2.getTimeStamp() <= ((Event) arrayList2.get(arrayList2.size() - 1)).getTimeStamp()) {
                            arrayList2.add(a2);
                            break;
                        }
                    }
                    i2++;
                }
                Event a3;
                if (i2 == arrayList.size()) {
                    int i3 = 0;
                    while (i3 < arrayList.size()) {
                        arrayList2 = (ArrayList) arrayList.get(i3);
                        if (arrayList2.size() != 0) {
                            Collections.sort(arrayList2, comparator);
                            if (a2.getClassName().equals(((Event) arrayList2.get(0)).getClassName()) && ((Event) arrayList2.get(0)).getTimeStamp() >= a2.getTimeStamp()) {
                                if (m.a) {
                                    o.a(f, "~~~hybrid child app start(move foreground) info comes earlier than hybrid app foreground event~~~" + b(a2));
                                }
                                a3 = a(a2.getPackageName(), a2.getClassName(), ((Event) arrayList2.get(0)).getTimeStamp(), a2.getEventType());
                                arrayList2.set(0, a3);
                                i = i3;
                            }
                        }
                        i3++;
                    }
                    i = i3;
                    a3 = a2;
                } else {
                    i = i2;
                    a3 = a2;
                }
                if (i == arrayList.size()) {
                    arrayList2 = new ArrayList();
                    arrayList2.add(a3);
                    arrayList.add(arrayList2);
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "mergeChildAppStartInfos e", e);
        }
        return arrayList;
    }

    private ArrayList<ArrayList<Event>> a(Comparator<Event> comparator, ArrayList<ArrayList<Event>> arrayList) {
        try {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ArrayList arrayList2 = (ArrayList) it.next();
                if (!(arrayList2 == null || arrayList2.size() == 0)) {
                    Event event;
                    Collections.sort(arrayList2, comparator);
                    ListIterator listIterator = arrayList2.listIterator();
                    while (listIterator.hasNext()) {
                        event = (Event) listIterator.next();
                        if (event.getEventType() == 1 && e.equals(event.getPackageName())) {
                            listIterator.remove();
                        }
                    }
                    ListIterator listIterator2 = arrayList2.listIterator();
                    while (listIterator2.hasNext()) {
                        event = (Event) listIterator2.next();
                        if (e.equals(event.getPackageName())) {
                            String className = event.getClassName();
                            int i = 0;
                            while (i < arrayList2.size()) {
                                event = (Event) arrayList2.get(i);
                                if (!event.getPackageName().equals(e) && event.getClassName().equals(className)) {
                                    break;
                                }
                                i++;
                            }
                            if (i >= arrayList2.size()) {
                                listIterator2.remove();
                            }
                        }
                    }
                }
            }
            Collections.sort(arrayList, new Comparator<ArrayList<Event>>(this) {
                final /* synthetic */ UploadUsageHelper a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((ArrayList) obj, (ArrayList) obj2);
                }

                public int a(ArrayList<Event> arrayList, ArrayList<Event> arrayList2) {
                    if (arrayList == arrayList2) {
                        return 0;
                    }
                    if (arrayList == null || arrayList.size() == 0) {
                        return -1;
                    }
                    if (arrayList2 == null || arrayList2.size() == 0) {
                        return 1;
                    }
                    int i = ((Event) arrayList.get(0)).getTimeStamp() < ((Event) arrayList2.get(0)).getTimeStamp() ? -1 : ((Event) arrayList.get(0)).getTimeStamp() == ((Event) arrayList2.get(0)).getTimeStamp() ? 0 : 1;
                    return i;
                }
            });
        } catch (Throwable e) {
            Log.e(o.a(f), "filterHybridInfos e", e);
        }
        return arrayList;
    }

    private ArrayList<ArrayList<Event>> b(Comparator<Event> comparator, ArrayList<ArrayList<Event>> arrayList) {
        Object arrayList2 = new ArrayList();
        try {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ArrayList arrayList3 = (ArrayList) it.next();
                List arrayList4 = new ArrayList();
                Collections.sort(arrayList3, comparator);
                ListIterator listIterator = arrayList3.listIterator();
                while (listIterator.hasNext()) {
                    Event event = (Event) listIterator.next();
                    if (event.getEventType() == 1) {
                        if (listIterator.hasNext()) {
                            Event event2 = (Event) listIterator.next();
                            if (event2.getEventType() == 2) {
                                if (event2.getClassName().equals(event.getClassName())) {
                                    arrayList4.add(event);
                                    arrayList4.add(a(event.getPackageName(), event2.getClassName(), event2.getTimeStamp(), event2.getEventType()));
                                }
                            } else if (event2.getEventType() == 1) {
                                Event event3;
                                if (event.getPackageName().equals(event2.getPackageName())) {
                                    event3 = event;
                                } else {
                                    event3 = event2;
                                }
                                Object obj = null;
                                while (listIterator.hasNext()) {
                                    event = (Event) listIterator.next();
                                    if (event.getEventType() != 2) {
                                        if (!event.getPackageName().equals(event3.getPackageName())) {
                                            obj = a(event3.getPackageName(), event3.getClassName(), event.getTimeStamp(), 2);
                                            break;
                                        }
                                        obj = a(event.getPackageName(), event.getClassName(), event.getTimeStamp(), 2);
                                    } else {
                                        event2 = a(event3.getPackageName(), event.getClassName(), event.getTimeStamp(), event.getEventType());
                                        if (event.getClassName().equals(event3.getClassName())) {
                                            obj = event2;
                                            break;
                                        }
                                        obj = event2;
                                    }
                                }
                                if (obj == null) {
                                    obj = a(event3.getPackageName(), event3.getClassName(), event3.getTimeStamp(), 2);
                                }
                                arrayList4.add(event3);
                                arrayList4.add(obj);
                            }
                        } else {
                            arrayList4.add(event);
                            arrayList4.add(a(event.getPackageName(), event.getClassName(), event.getTimeStamp(), 2));
                        }
                    }
                }
                Collections.sort(arrayList4, comparator);
                arrayList2.add(arrayList4);
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "getHybridChildAppUsage e", e);
        }
        try {
            Collections.sort(arrayList2, new Comparator<ArrayList<Event>>(this) {
                final /* synthetic */ UploadUsageHelper a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((ArrayList) obj, (ArrayList) obj2);
                }

                public int a(ArrayList<Event> arrayList, ArrayList<Event> arrayList2) {
                    if (arrayList == arrayList2) {
                        return 0;
                    }
                    if (arrayList == null || arrayList.size() == 0) {
                        return -1;
                    }
                    if (arrayList2 == null || arrayList2.size() == 0) {
                        return 1;
                    }
                    int i = ((Event) arrayList.get(0)).getTimeStamp() < ((Event) arrayList2.get(0)).getTimeStamp() ? -1 : ((Event) arrayList.get(0)).getTimeStamp() == ((Event) arrayList2.get(0)).getTimeStamp() ? 0 : 1;
                    return i;
                }
            });
        } catch (Throwable e2) {
            Log.e(o.a(f), "getHybridChildAppUsage e", e2);
        }
        return arrayList2;
    }

    public synchronized List<Event> a(long j, long j2, List<Event> list) {
        try {
            o.a(f, "beginTime:" + j + " " + new Date(j).toString());
            if (list == null || list.size() == 0) {
                list = null;
            } else {
                if (m.a) {
                    o.a(f, "*****************origin events*****************");
                    o.a(f, "origin events size:" + list.size());
                    for (Event b : list) {
                        o.a(f, b(b));
                    }
                }
                List<HybridInfo> a = k.a(this.j).a(j, j2);
                if (!(a == null || a.size() == 0)) {
                    Iterator it;
                    Iterator it2;
                    ArrayList arrayList;
                    Iterator it3;
                    if (m.a) {
                        o.a(f, "*****************origin hybrid*****************");
                        o.a(f, "origin hybrid size:" + a.size());
                        for (HybridInfo hybridInfo : a) {
                            o.a(f, hybridInfo.toString());
                        }
                    }
                    Comparator anonymousClass3 = new Comparator<Event>(this) {
                        final /* synthetic */ UploadUsageHelper a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ int compare(Object obj, Object obj2) {
                            return a((Event) obj, (Event) obj2);
                        }

                        public int a(Event event, Event event2) {
                            if (event == event2) {
                                return 0;
                            }
                            if (event.getTimeStamp() < event2.getTimeStamp()) {
                                return -1;
                            }
                            if (event.getTimeStamp() != event2.getTimeStamp()) {
                                return 1;
                            }
                            return 0;
                        }
                    };
                    ArrayList a2 = a(anonymousClass3, (List) list);
                    if (m.a) {
                        o.a(f, "*****************hybridEventPairList*****************");
                        o.a(f, "hybridEventPairList list size :" + a2.size());
                        it2 = a2.iterator();
                        while (it2.hasNext()) {
                            arrayList = (ArrayList) it2.next();
                            o.a(f, "hybridEventPairList list");
                            Iterator it4 = arrayList.iterator();
                            while (it4.hasNext()) {
                                o.a(f, b((Event) it4.next()));
                            }
                        }
                    }
                    ArrayList a3 = a(anonymousClass3, (List) a, a2);
                    if (m.a) {
                        o.a(f, "*****************merge half*****************");
                        it3 = a3.iterator();
                        while (it3.hasNext()) {
                            arrayList = (ArrayList) it3.next();
                            o.a(f, "merge list");
                            it2 = arrayList.iterator();
                            while (it2.hasNext()) {
                                o.a(f, b((Event) it2.next()));
                            }
                        }
                    }
                    a3 = a(anonymousClass3, a3);
                    if (m.a) {
                        o.a(f, "*****************filter*****************");
                        it3 = a3.iterator();
                        while (it3.hasNext()) {
                            arrayList = (ArrayList) it3.next();
                            o.a(f, "filter list");
                            it2 = arrayList.iterator();
                            while (it2.hasNext()) {
                                o.a(f, b((Event) it2.next()));
                            }
                        }
                    }
                    a3 = b(anonymousClass3, a3);
                    if (m.a) {
                        o.a(f, "*****************childAppUsageLists*****************");
                        it = a3.iterator();
                        while (it.hasNext()) {
                            arrayList = (ArrayList) it.next();
                            o.a(f, "child");
                            it3 = arrayList.iterator();
                            while (it3.hasNext()) {
                                o.a(f, b((Event) it3.next()));
                            }
                        }
                    }
                    a((List) list, a3);
                    if (m.a) {
                        o.a(f, "***************** after events *****************");
                        o.a(f, "after events size:" + list.size());
                        for (Event b2 : list) {
                            o.a(f, b(b2));
                        }
                    }
                    k.a(this.j).n();
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(f), "mergeHybridToUsage e", e);
        }
        return list;
    }

    private void a(List<Event> list, ArrayList<ArrayList<Event>> arrayList) {
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Iterator it;
            Event event = (Event) listIterator.next();
            long j = 0;
            if (event.getPackageName().equals(e) && event.getEventType() == 1) {
                long timeStamp = event.getTimeStamp();
                while (listIterator.hasNext()) {
                    event = (Event) listIterator.next();
                    if (!event.getPackageName().equals(e)) {
                        listIterator.previous();
                        break;
                    }
                    j = event.getTimeStamp();
                }
                ListIterator listIterator2 = arrayList.listIterator();
                while (listIterator2.hasNext()) {
                    ArrayList arrayList2 = (ArrayList) listIterator2.next();
                    if (arrayList2.size() != 0 && ((Event) arrayList2.get(arrayList2.size() - 1)).getTimeStamp() >= timeStamp && ((Event) arrayList2.get(arrayList2.size() - 1)).getTimeStamp() <= j) {
                        it = arrayList2.iterator();
                        while (it.hasNext()) {
                            listIterator.add((Event) it.next());
                        }
                        listIterator2.remove();
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            it = arrayList.iterator();
            while (it.hasNext()) {
                list.addAll((ArrayList) it.next());
            }
        }
    }

    private Event a(String str, String str2, long j, int i) {
        Event event = new Event();
        try {
            Field declaredField = event.getClass().getDeclaredField("mPackage");
            declaredField.setAccessible(true);
            declaredField.set(event, str);
        } catch (Exception e) {
        }
        try {
            declaredField = event.getClass().getDeclaredField("mClass");
            declaredField.setAccessible(true);
            declaredField.set(event, str2);
        } catch (Exception e2) {
        }
        try {
            declaredField = event.getClass().getDeclaredField("mTimeStamp");
            declaredField.setAccessible(true);
            declaredField.set(event, Long.valueOf(j));
        } catch (Exception e3) {
        }
        try {
            declaredField = event.getClass().getDeclaredField("mEventType");
            declaredField.setAccessible(true);
            declaredField.set(event, Integer.valueOf(i));
        } catch (Exception e4) {
        }
        return event;
    }

    private boolean c(Event event) {
        return event != null && e.equals(event.getPackageName());
    }

    public static boolean a(Event event) {
        return (event == null || event.getPackageName().contains(e) || !event.getClassName().contains(e)) ? false : true;
    }
}
