package com.miui.analytics.internal.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.k;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SimpleTimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public class e {
    private static final int A = 0;
    private static final int B = 1;
    private static final int C = 0;
    private static final Object D = new Object();
    private static volatile e E = null;
    private static final String a = "BatteryStatsHelper";
    private static final String b = "batterystats";
    private static final String c = "screen";
    private static final String d = "batteryLevel";
    private static final String e = "boot";
    private static final String f = "t";
    private static final String g = "a";
    private static final String h = "t";
    private static final String i = "b";
    private static final String j = "m";
    private static final String k = "f";
    private static final String l = "en";
    private static final String m = "ef";
    private static final String n = "nn";
    private static final String o = "nt";
    private static final String p = "b";
    private static final String q = "t";
    private static final String r = "b";
    private static final String s = "s";
    private static final String t = ",";
    private static final String u = "[";
    private static final String v = "]";
    private static final int w = 0;
    private static final int x = 1;
    private static final int y = 2;
    private static final int z = 1;
    private Context F;
    private v G = new v(this.F, u.d);

    class a {
        final /* synthetic */ e a;
        private String b = "";
        private String c = "";
        private String d = "";
        private String e = "";
        private String f = "";
        private String g = "";
        private String h = "";

        public a(e eVar) {
            this.a = eVar;
        }

        public a(e eVar, String str) {
            this.a = eVar;
            if (str != null && str.length() > 0) {
                String[] split = str.split(e.t);
                for (int i = 0; i < split.length; i++) {
                    if (i == 0) {
                        this.b = split[0];
                    } else if (i == 1) {
                        this.c = split[1];
                    } else if (i == 2) {
                        this.d = split[2];
                    } else if (i == 3) {
                        this.e = split[3];
                    } else if (i == 4) {
                        this.f = split[4];
                    } else if (i == 5) {
                        this.g = split[5];
                    } else if (i == 6) {
                        this.h = split[6];
                    }
                }
            }
        }

        public void a(double d) {
            if (d >= 0.0d) {
                this.b = String.valueOf(d);
            }
        }

        public void a(String str) {
            this.c = str;
        }

        public void b(String str) {
            this.d = str;
        }

        public void a(int i) {
            if (i >= 0) {
                this.e = String.valueOf(i);
            }
        }

        public void b(int i) {
            if (i >= 0) {
                this.f = String.valueOf(i);
            }
        }

        public void c(int i) {
            if (i >= 0) {
                this.g = String.valueOf(i);
            }
        }

        public void a(long j) {
            if (j >= 0) {
                this.h = String.valueOf(j);
            }
        }

        public String toString() {
            if (TextUtils.isEmpty(this.b) && TextUtils.isEmpty(this.c) && TextUtils.isEmpty(this.d) && TextUtils.isEmpty(this.e) && TextUtils.isEmpty(this.f) && TextUtils.isEmpty(this.g) && TextUtils.isEmpty(this.h)) {
                return "";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.b);
            stringBuilder.append(e.t + this.c);
            stringBuilder.append(e.t + this.d);
            stringBuilder.append(e.t + this.e);
            stringBuilder.append(e.t + this.f);
            stringBuilder.append(e.t + this.g);
            stringBuilder.append(e.t + this.h);
            return stringBuilder.toString();
        }
    }

    private e(Context context) {
        this.F = context;
    }

    public static e a(Context context) {
        if (E == null) {
            synchronized (e.class) {
                if (E == null) {
                    E = new e(c.a(context));
                }
            }
        }
        return E;
    }

    private String a(int i) {
        if (i == 0) {
            return this.F.getFilesDir().getAbsolutePath() + "/" + b + "/" + c;
        }
        if (i == 1) {
            return this.F.getFilesDir().getAbsolutePath() + "/" + b + "/" + d;
        }
        if (i == 2) {
            return this.F.getFilesDir().getAbsolutePath() + "/" + b + "/" + e;
        }
        return "";
    }

    public void a() {
        if (d()) {
            ab.a(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    synchronized (e.D) {
                        try {
                            if (ac.a(this.a.f())) {
                                o.a(e.a, "already upload battery stats today, skip");
                                return;
                            }
                            k.a(this.a.F).a(new LogEvent(this.a.F, "com.miui.analytics", f.w, this.a.b().toString()));
                            this.a.h();
                            this.a.g();
                        } catch (Throwable e) {
                            Log.e(o.a(e.a), "upload exception", e);
                        }
                    }
                }
            });
        }
    }

    private long f() {
        return this.G.b(u.aa, 0);
    }

    private void g() {
        this.G.a(u.aa, System.currentTimeMillis());
    }

    private void h() {
        a(new File(a(0)));
        a(new File(a(1)));
        a(new File(a(2)));
    }

    private void a(File file) {
        long a = ac.a();
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (Long.parseLong(file2.getName()) <= a) {
                        file2.delete();
                    }
                }
            }
        }
    }

    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        while (i < 24) {
            try {
                jSONObject.put("" + i, new a(this).toString());
                i++;
            } catch (Throwable e) {
                Log.e(o.a(a), "getYesterdayBatteryStats exception", e);
            }
        }
        b(jSONObject, i());
        a(jSONObject, k());
        a(jSONObject, j());
        o.a(a, "======================================\nyesterday battery stats:" + jSONObject.toString());
        return jSONObject;
    }

    private void a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            if (jSONObject2.has("s")) {
                jSONObject.put("s", jSONObject2.optJSONObject("s"));
                jSONObject2.remove("s");
            }
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject optJSONObject = jSONObject2.optJSONObject(str);
                int optInt = optJSONObject.optInt(n, -1);
                int optInt2 = optJSONObject.optInt(l, -1);
                int optInt3 = optJSONObject.optInt(m, -1);
                long optLong = optJSONObject.optLong(o, -1);
                a aVar = new a(this, jSONObject.optString(str));
                aVar.a(optInt);
                aVar.b(optInt2);
                aVar.c(optInt3);
                aVar.a(optLong);
                jSONObject.put(str, aVar.toString());
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "packScreenInfo exception", e);
        }
    }

    private void b(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject optJSONObject = jSONObject2.optJSONObject(str);
                double optDouble = optJSONObject.optDouble("b", -1.0d);
                String optString = optJSONObject.optString("m");
                String optString2 = optJSONObject.optString("f");
                a aVar = new a(this, jSONObject.optString(str));
                aVar.a(optDouble);
                aVar.a(optString);
                aVar.b(optString2);
                jSONObject.put(str, aVar.toString());
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "packBatteryLevelAndStorage exception", e);
        }
    }

    private void a(JSONObject jSONObject, JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    JSONObject jSONObject2 = new JSONObject();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        long optLong = optJSONObject.optLong(f.J);
                        jSONObject2.put(optLong + "", optJSONObject.optInt("b"));
                    }
                    jSONObject.put("b", jSONObject2);
                }
            } catch (Throwable e) {
                Log.e(o.a(a), "packBootActions exception", e);
            }
        }
    }

    public void c() {
        if (d()) {
            try {
                double n = z.n(this.F);
                String[] a = x.a(this.F);
                String str = a[0];
                String str2 = a[1];
                o.a(a, "saveBatteryLevelAndStorage:" + n + t + str + t + str2);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f.J, System.currentTimeMillis());
                jSONObject.put("b", n);
                jSONObject.put("m", str);
                jSONObject.put("f", str2);
                new j(this.F, a(1), "" + ac.d()).b(t + jSONObject.toString());
            } catch (Throwable e) {
                Log.e(o.a(a), "saveBatteryLevel exception", e);
            }
        }
    }

    private JSONObject i() {
        JSONObject jSONObject;
        Throwable e;
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONArray a = a(ac.a(), 1);
            if (a == null || a.length() <= 0) {
                jSONObject = jSONObject2;
            } else {
                jSONObject = a(a);
            }
            try {
                o.a(a, "battery level & storage day stats: \n" + jSONObject.toString());
            } catch (Exception e2) {
                e = e2;
                Log.e(o.a(a), "getYesterdayBatteryLevelAndStorage exception", e);
                return jSONObject;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            jSONObject = jSONObject2;
            e = th;
            Log.e(o.a(a), "getYesterdayBatteryLevelAndStorage exception", e);
            return jSONObject;
        }
        return jSONObject;
    }

    private JSONObject a(JSONArray jSONArray) {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        int i2 = 0;
        while (i2 < 24) {
            try {
                jSONObject.put("" + i2, new JSONObject());
                i2++;
            } catch (Throwable e) {
                Log.e(o.a(a), "partBatteryAndStorageByHour exception", e);
            }
        }
        while (i < jSONArray.length()) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            jSONObject.put(a(optJSONObject.optLong(f.J)), optJSONObject);
            i++;
        }
        return jSONObject;
    }

    public void a(boolean z) {
        if (d()) {
            try {
                o.a(a, "saveBootAction bootCompleted:" + z);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f.J, System.currentTimeMillis());
                jSONObject.put("b", z ? 1 : 0);
                new j(this.F, a(2), "" + ac.d()).b(t + jSONObject.toString());
            } catch (Throwable e) {
                Log.e(o.a(a), "saveBatteryLevel exception", e);
            }
        }
    }

    private JSONArray j() {
        try {
            JSONArray a = a(ac.a(), 2);
            if (a != null && a.length() > 0) {
                o.a(a, "boot day stats array: \n" + a.toString());
                return a;
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "getYesterdayBatteryLevelAndStorage exception", e);
        }
        return null;
    }

    public void b(boolean z) {
        int i = 0;
        if (d()) {
            try {
                o.a(a, "saveScreenAction:" + z);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f.J, System.currentTimeMillis());
                String str = g;
                if (z) {
                    i = 1;
                }
                jSONObject.put(str, i);
                new j(this.F, a(0), "" + ac.d()).b(t + jSONObject.toString());
            } catch (Throwable e) {
                Log.e(o.a(a), "saveScreenAction exception", e);
            }
        }
    }

    private JSONObject k() {
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        while (i < 24) {
            try {
                jSONObject.put("" + i, new JSONObject());
                i++;
            } catch (Throwable e) {
                Log.e(o.a(a), "getYesterdayScreenAction exception", e);
            }
        }
        long a = ac.a();
        long b = ac.b();
        JSONArray a2 = a(a, 0);
        if (a2 != null && a2.length() > 0) {
            b(jSONObject, a2);
            List b2 = b(a2);
            List a3 = a(jSONObject, b2, a, b);
            o.a(a, "sortLists: + \n");
            for (i = 0; i < b2.size(); i++) {
                o.a(a, a((JSONObject) b2.get(i)));
            }
            o.a(a, "error dayStats:" + jSONObject.toString());
            JSONObject a4 = a(a3);
            o.a(a, "normal hourGroups:" + a4.toString());
            a(jSONObject, a4, a3);
        }
        o.a(a, "screen dayStats:" + jSONObject.toString());
        return jSONObject;
    }

    private void b(JSONObject jSONObject, JSONArray jSONArray) {
        try {
            if (g.y()) {
                JSONObject jSONObject2 = new JSONObject();
                for (int i = 0; i < jSONArray.length(); i++) {
                    int i2;
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    long b = b(optJSONObject);
                    boolean c = c(optJSONObject);
                    String str = b + "";
                    if (c) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    jSONObject2.put(str, i2);
                }
                jSONObject.put("s", jSONObject2);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "packOriginData exception", e);
        }
    }

    private String a(JSONObject jSONObject) {
        long b = b(jSONObject);
        Calendar instance = Calendar.getInstance(new SimpleTimeZone(0, "UTC"));
        instance.setTimeInMillis(b);
        int i = instance.get(1);
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        return i + "-" + i2 + "-" + i3 + " " + i4 + ":" + i5 + ":" + i6 + ":" + instance.get(14) + "      " + c(jSONObject);
    }

    private long b(JSONObject jSONObject) {
        return jSONObject.optLong(f.J);
    }

    private boolean c(JSONObject jSONObject) {
        return jSONObject.optInt(g) == 1;
    }

    private JSONObject a(JSONObject jSONObject, String str) {
        if (jSONObject.has(str)) {
            return jSONObject.optJSONObject(str);
        }
        return new JSONObject();
    }

    private JSONArray b(JSONObject jSONObject, String str) {
        if (jSONObject.has(str)) {
            return jSONObject.optJSONArray(str);
        }
        return new JSONArray();
    }

    private String a(long j) {
        Calendar instance = Calendar.getInstance(new SimpleTimeZone(0, "UTC"));
        instance.setTimeInMillis(j);
        return instance.get(11) + "";
    }

    private long b(long j) {
        Calendar instance = Calendar.getInstance(new SimpleTimeZone(0, "UTC"));
        instance.setTimeInMillis(j);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    private long c(long j) {
        Calendar instance = Calendar.getInstance(new SimpleTimeZone(0, "UTC"));
        instance.setTimeInMillis(j);
        instance.add(11, 1);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    private JSONObject a(List<JSONObject> list) {
        JSONObject jSONObject = new JSONObject();
        int i = 0;
        while (i < 24) {
            try {
                jSONObject.put("" + i, new JSONArray());
                i++;
            } catch (Throwable e) {
                Log.e(o.a(a), "partValidScreenInfoByHour exception", e);
            }
        }
        for (i = 0; i < list.size(); i++) {
            JSONObject jSONObject2 = (JSONObject) list.get(i);
            String a = a(b(jSONObject2));
            JSONArray b = b(jSONObject, a);
            b.put(jSONObject2);
            jSONObject.put(a, b);
        }
        return jSONObject;
    }

    private List<JSONObject> b(JSONArray jSONArray) {
        List<JSONObject> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optJSONObject(i));
        }
        Collections.sort(arrayList, new Comparator<JSONObject>(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((JSONObject) obj, (JSONObject) obj2);
            }

            public int a(JSONObject jSONObject, JSONObject jSONObject2) {
                if (jSONObject == null && jSONObject2 == null) {
                    return 0;
                }
                if (jSONObject == null) {
                    return -1;
                }
                if (jSONObject2 == null) {
                    return 1;
                }
                if (this.a.b(jSONObject) >= this.a.b(jSONObject2)) {
                    return this.a.b(jSONObject) == this.a.b(jSONObject2) ? 0 : 1;
                } else {
                    return -1;
                }
            }
        });
        return arrayList;
    }

    private List<JSONObject> a(JSONObject jSONObject, List<JSONObject> list, long j, long j2) {
        List<JSONObject> arrayList = new ArrayList();
        try {
            JSONObject jSONObject2;
            if (!c((JSONObject) list.get(0))) {
                jSONObject2 = new JSONObject();
                jSONObject2.put(f.J, j);
                jSONObject2.put(g, 1);
                list.add(0, jSONObject2);
            }
            if (c((JSONObject) list.get(list.size() - 1))) {
                jSONObject2 = new JSONObject();
                jSONObject2.put(f.J, j2);
                jSONObject2.put(g, 0);
                list.add(list.size(), jSONObject2);
            }
            int i = 0;
            while (i < list.size()) {
                jSONObject2 = (JSONObject) list.get(i);
                String a;
                JSONObject a2;
                if (c(jSONObject2)) {
                    if (c(jSONObject2) && i + 1 < list.size() && c((JSONObject) list.get(i + 1))) {
                        a = a(b(jSONObject2));
                        a2 = a(jSONObject, "" + a);
                        a2.put(l, a2.optInt(l) + 1);
                        jSONObject.put(a, a2);
                    }
                    arrayList.add(jSONObject2);
                } else {
                    if (i - 1 > 0 && !c((JSONObject) list.get(i - 1))) {
                        a = a(b(jSONObject2));
                        a2 = a(jSONObject, "" + a);
                        a2.put(m, a2.optInt(m) + 1);
                        jSONObject.put(a, a2);
                    }
                    arrayList.add(jSONObject2);
                }
                i++;
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "dealErrorInfo exception", e);
        }
        return arrayList;
    }

    private void a(JSONObject jSONObject, JSONObject jSONObject2, List<JSONObject> list) {
        try {
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                int i;
                String str = (String) keys.next();
                JSONArray optJSONArray = jSONObject2.optJSONArray(str);
                long j = 0;
                if (optJSONArray.length() == 0) {
                    int size = list.size() - 1;
                    while (size >= 0) {
                        JSONObject jSONObject3 = (JSONObject) list.get(size);
                        if (Integer.valueOf(a(b(jSONObject3))).intValue() < Integer.valueOf(str).intValue()) {
                            if (c(jSONObject3)) {
                                j = 3600;
                            }
                            i = 0;
                        } else {
                            size--;
                        }
                    }
                    i = 0;
                } else {
                    i = 0;
                    long j2 = 0;
                    int i2 = 0;
                    while (i < optJSONArray.length()) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        long b = b(optJSONObject);
                        boolean c = c(optJSONObject);
                        if (c) {
                            i2++;
                        }
                        if (i == 0 && !c) {
                            j2 += (b - b(b)) / 1000;
                        } else if (i >= optJSONArray.length() - 1 && c) {
                            j2 += (c(b) - b) / 1000;
                        } else if (c) {
                            i++;
                            optJSONObject = optJSONArray.getJSONObject(i);
                            boolean c2 = c(optJSONObject);
                            if (c2) {
                                i2++;
                            }
                            if (!c2) {
                                j2 += (b(optJSONObject) - b) / 1000;
                            }
                        }
                        i++;
                    }
                    i = i2;
                    j = j2;
                }
                JSONObject a = a(jSONObject, str);
                if (i > 0) {
                    a.put(n, i);
                }
                if (j > 0) {
                    a.put(o, j);
                }
                jSONObject.put(str, a);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "calculateScreenTimeLength exception", e);
        }
    }

    private JSONArray a(long j, int i) {
        try {
            String a = new j(this.F, a(i), "" + j).a();
            o.a(a, "type: " + i + " saved: " + a);
            if (TextUtils.isEmpty(a)) {
                return null;
            }
            if (a.startsWith(t)) {
                a = a.substring(1, a.length());
            }
            JSONArray jSONArray = new JSONArray(u + a + v);
            o.a(a, "saved jsonArray :" + jSONArray.toString());
            return jSONArray;
        } catch (Throwable e) {
            Log.e(o.a(a), "getSavedJSONArray exception", e);
            return null;
        }
    }

    public static boolean d() {
        if (!n.a()) {
            return g.F();
        }
        o.a(a, "Not allow to upload scanedspot in international build.");
        return false;
    }
}
