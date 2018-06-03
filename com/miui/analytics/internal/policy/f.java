package com.miui.analytics.internal.policy;

import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.policy.a.d;
import com.miui.analytics.internal.policy.a.k;
import com.miui.analytics.internal.service.j;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.z;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class f {
    public static final String a = "privacy_policy";
    public static final String b = "upload_url";
    public static final String c = "upload_url_use_https";
    public static final String d = "sample_rate";
    public static final String e = "log_level";
    public static final String f = "upload_policy";
    public static final String g = "network";
    public static final String h = "local_config";
    public static final String i = "wifi";
    public static final String j = "no";
    public static final String k = "v";
    public static final String l = "ext_v";
    public static final String m = "realtime";
    private static final String n = "KeyPolicy";
    private int o = 0;
    private String p;
    private String q;
    private double r = 1.0d;
    private i s = null;
    private String t = null;
    private String u = null;
    private ArrayList<String> v = new ArrayList();

    public f(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                a(jSONObject);
                b(jSONObject);
                d(jSONObject);
                e(jSONObject);
                f(jSONObject);
                g(jSONObject);
                c(jSONObject);
            } catch (Throwable e) {
                Log.e(o.a(n), "KeyPolicy exception:", e);
            }
        }
    }

    public int a() {
        return this.o;
    }

    public double b() {
        return this.r;
    }

    public i c() {
        return this.s;
    }

    public String d() {
        return this.t;
    }

    public boolean e() {
        String str = "global";
        for (int i = 0; i < this.v.size(); i++) {
            String str2 = (String) this.v.get(i);
            o.a(n, "https : " + str2);
            if (str.equalsIgnoreCase(str2)) {
                return n.a();
            }
            String i2 = z.i();
            if (!TextUtils.isEmpty(str2) && str2.equalsIgnoreCase(i2)) {
                return true;
            }
        }
        return false;
    }

    private void a(JSONObject jSONObject) {
        try {
            if (j.equals(jSONObject.getJSONObject("privacy_policy").getString("v"))) {
                this.s = new d();
            } else {
                this.s = new k();
            }
        } catch (Throwable e) {
            Log.e(o.a(n), "parsePrivacyPolicy exception:", e);
        }
    }

    private void b(JSONObject jSONObject) {
        try {
            this.t = jSONObject.optString(b, j.c);
            this.v.clear();
            JSONArray optJSONArray = jSONObject.optJSONArray(c);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (!(optJSONObject == null || TextUtils.isEmpty(optJSONObject.optString("v")))) {
                        this.v.add(optJSONObject.getString("v"));
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(n), "parseTrackingServer exception:", e);
        }
    }

    private void c(JSONObject jSONObject) {
        try {
            this.u = jSONObject.optString(h);
        } catch (Throwable e) {
            Log.e(o.a(n), "parseLocalConfig exception:", e);
        }
    }

    private void d(JSONObject jSONObject) {
        try {
            this.r = Double.parseDouble(jSONObject.optString(d));
        } catch (Throwable e) {
            Log.e(o.a(n), "parseSampleRate exception:", e);
        }
    }

    private void e(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(e);
            if (jSONObject2 != null) {
                this.o = Integer.parseInt(jSONObject2.optString("v"));
            }
        } catch (Throwable e) {
            Log.e(o.a(n), "parseLogLevel exception:", e);
        }
    }

    private void f(JSONObject jSONObject) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject(f);
            if (optJSONObject != null) {
                this.p = optJSONObject.optString("v");
            }
        } catch (Throwable e) {
            Log.e(o.a(n), "parseUploadPolicy exception:", e);
        }
    }

    private void g(JSONObject jSONObject) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject(g);
            if (optJSONObject != null) {
                this.q = optJSONObject.optString("v");
            }
        } catch (Throwable e) {
            Log.e(o.a(n), "parseNetworkPolicy exception:", e);
        }
    }

    public boolean f() {
        return TextUtils.isEmpty(this.q) || this.q.equalsIgnoreCase(i);
    }

    public String g() {
        return this.p;
    }

    public String h() {
        return this.u;
    }
}
