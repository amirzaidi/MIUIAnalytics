package com.miui.analytics.internal.policy;

import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.policy.a.b;
import com.miui.analytics.internal.policy.a.c;
import com.miui.analytics.internal.policy.a.h;
import com.miui.analytics.internal.policy.a.i;
import com.miui.analytics.internal.policy.a.j;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class a {
    public static final String a = "polling_interval";
    public static final String b = "discard_policy";
    public static final String c = "discard_threshold";
    public static final String d = "upload_trigger_policy";
    public static final String e = "join_policy";
    public static final String f = "upload_pipeline";
    public static final String g = "disable_headers";
    public static final String h = "startup";
    public static final String i = "time";
    public static final String j = "log_count";
    public static final String k = "log_size";
    public static final String l = "time";
    public static final String m = "count";
    public static final String n = "size";
    public static final String o = "time";
    public static final String p = "level";
    public static final String q = "size";
    public static final String r = "time_level_size";
    public static final String s = "time_iter";
    public static final String t = "v";
    public static final String u = "ext_v";
    private static final String v = "AppPolicy";
    private List<k> A = null;
    private List<c> B = null;
    private e C;
    private boolean D = true;
    private List<String> w = new ArrayList();
    private List<String> x = new ArrayList();
    private int y = h.b;
    private List<k> z = null;

    public a(JSONObject jSONObject) {
        try {
            a(jSONObject);
            b(jSONObject);
            c(jSONObject);
            d(jSONObject);
            e(jSONObject);
            f(jSONObject);
            h(jSONObject);
            g(jSONObject);
        } catch (Throwable e) {
            Log.e(o.a(v), "AppPolicy construct e", e);
        }
    }

    private void a(JSONObject jSONObject) {
        try {
            this.y = Integer.parseInt(jSONObject.optString(a)) * ac.f;
            o.a(v, "polling time is " + this.y + "ms");
        } catch (Throwable e) {
            Log.e(o.a(v), "parsePollingTime e", e);
        }
    }

    private void b(JSONObject jSONObject) {
        try {
            List arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray(c);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("v");
                    if (j.equals(optString)) {
                        int parseInt = Integer.parseInt(jSONObject2.getString("ext_v"));
                        arrayList.add(new h(parseInt));
                        o.a(v, "discard count  is " + parseInt);
                    } else if (k.equals(optString)) {
                        arrayList.add(new j((long) Integer.parseInt(jSONObject2.getString("ext_v"))));
                    }
                }
                this.A = arrayList;
            }
        } catch (Throwable e) {
            Log.e(o.a(v), "parseDiscardTrigger e", e);
        }
    }

    private void c(JSONObject jSONObject) {
        try {
            List arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray(b);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("v");
                    if (r.equals(optString)) {
                        JSONObject jSONObject3 = new JSONObject(jSONObject2.getString("ext_v"));
                        o.a(v, "discard policy " + jSONObject3.toString());
                        arrayList.add(new com.miui.analytics.internal.policy.a.a((long) (jSONObject3.getInt("time") * ac.f), jSONObject3.getDouble("size"), jSONObject3.getInt("level")));
                    } else if (s.equals(optString)) {
                        arrayList.add(new c());
                    }
                }
                this.B = arrayList;
            }
        } catch (Throwable e) {
            Log.e(o.a(v), "parseDiscardPolicy e", e);
        }
    }

    private void d(JSONObject jSONObject) {
        try {
            List arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray(d);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("v");
                    if (m.equals(optString)) {
                        arrayList.add(new h(Integer.parseInt(jSONObject2.getString("ext_v"))));
                    } else if ("size".equals(optString)) {
                        arrayList.add(new j((long) Integer.parseInt(jSONObject2.getString("ext_v"))));
                    } else if ("time".equals(optString)) {
                        arrayList.add(new i(Integer.parseInt(jSONObject2.getString("ext_v")) * ac.f));
                    }
                }
                this.z = arrayList;
            }
        } catch (Throwable e) {
            Log.e(o.a(v), "parseUploadTrigger e", e);
        }
    }

    private void e(JSONObject jSONObject) {
        int i = 0;
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(e);
            if (optJSONArray != null) {
                int i2 = 0;
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i3);
                    String optString = jSONObject2.optString("v");
                    if (m.equals(optString)) {
                        i2 = Integer.parseInt(jSONObject2.getString("ext_v"));
                    } else if ("size".equals(optString)) {
                        i = Integer.parseInt(jSONObject2.getString("ext_v"));
                    }
                }
                this.C = new b(i2, i);
            }
        } catch (Throwable e) {
            Log.e(o.a(v), "parseJoinPolicy e", e);
        }
    }

    private void f(JSONObject jSONObject) {
        try {
            this.w.clear();
            JSONArray optJSONArray = jSONObject.optJSONArray(f);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    Object optString = optJSONArray.getJSONObject(i).optString("v");
                    if (!TextUtils.isEmpty(optString)) {
                        this.w.add(optString.toLowerCase(Locale.getDefault()));
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(v), "parsePipeline e", e);
        }
    }

    private void g(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(g);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject == null) {
                        CharSequence optString = optJSONArray.optString(i);
                        if (!TextUtils.isEmpty(optString)) {
                            this.x.add(optString);
                        }
                    } else if (!TextUtils.isEmpty(optJSONObject.optString("v"))) {
                        this.x.add(optJSONObject.getString("v"));
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private void h(JSONObject jSONObject) {
        boolean z = true;
        try {
            Object optString;
            JSONObject optJSONObject = jSONObject.optJSONObject(h);
            if (optJSONObject != null) {
                optString = optJSONObject.optString("v");
            } else {
                optString = jSONObject.optString(h);
            }
            if (!TextUtils.isEmpty(optString)) {
                if (Integer.parseInt(optString) != 1) {
                    z = false;
                }
                this.D = z;
            }
        } catch (Throwable e) {
            Log.e(o.a(v), "parseStartup e", e);
        }
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str) || !this.x.contains(str)) {
            return false;
        }
        return true;
    }

    public List<String> a() {
        return this.x;
    }

    public e b() {
        return this.C;
    }

    public int c() {
        return this.y;
    }

    public List<k> d() {
        return this.A;
    }

    public List<k> e() {
        return this.z;
    }

    public List<c> f() {
        return this.B;
    }

    public boolean g() {
        if (this.w.contains("zip")) {
            return true;
        }
        return false;
    }

    public boolean h() {
        return this.D;
    }
}
