package com.miui.analytics.internal.policy;

import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.util.o;
import java.io.Serializable;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class PolicyGroup implements Serializable {
    public static final String a = "key_detail";
    public static final String b = "key_policy";
    public static final String c = "config_key";
    public static final String d = "app_policy";
    private static final String f = "PolicyGroup";
    private static final long serialVersionUID = 1;
    a e;
    private HashMap<String, f> g = new HashMap();
    private Serializer h = new Serializer();

    public static class Serializer implements Serializable {
        private static final long serialVersionUID = 1;
        public String a;
    }

    public PolicyGroup(Serializer serializer) {
        if (serializer != null) {
            try {
                if (serializer.a != null) {
                    a(new JSONObject(serializer.a));
                }
            } catch (Throwable e) {
                Log.e(o.a(f), "PolicyGroup e", e);
            }
        }
    }

    public PolicyGroup(JSONObject jSONObject) {
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject(d);
                if (optJSONObject != null) {
                    this.e = new a(optJSONObject);
                }
                JSONArray optJSONArray = jSONObject.optJSONArray(a);
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        if (optJSONObject2 != null) {
                            this.g.put(optJSONObject2.getString("config_key"), new f(optJSONObject2.getJSONObject(b)));
                        }
                    }
                }
                this.h.a = jSONObject.toString();
            } catch (Throwable e) {
                Log.e(o.a(f), "fromJson e", e);
            }
        }
    }

    public Serializable a() {
        return this.h;
    }

    public f a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (f) this.g.get(str);
    }

    public f a(LogEvent logEvent) {
        if (logEvent == null || TextUtils.isEmpty(logEvent.b())) {
            return null;
        }
        return (f) this.g.get(logEvent.b());
    }
}
