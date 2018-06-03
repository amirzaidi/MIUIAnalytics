package com.miui.analytics.internal.b;

import android.util.Log;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class j {
    private static final String e = "StoreLocationInfo";
    public int a;
    public double b;
    public double c;
    public double d;

    public j(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.a = jSONObject.optInt("id");
                this.b = jSONObject.optDouble("lgn");
                this.c = jSONObject.optDouble("lat");
            } catch (Throwable e) {
                Log.e(o.a(e), "Constructor e", e);
            }
        }
    }

    public static List<j> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        try {
            List<j> arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(new j(jSONArray.getJSONObject(i)));
            }
            return arrayList;
        } catch (Throwable e) {
            Log.e(o.a(e), "parseStoreInfos e", e);
            return null;
        }
    }

    public String toString() {
        return "[" + this.a + "," + this.b + "," + this.c + "," + this.d + "]";
    }
}
