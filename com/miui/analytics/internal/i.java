package com.miui.analytics.internal;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.LogEvent.IdType;
import com.miui.analytics.internal.LogEvent.LogType;
import com.miui.analytics.internal.util.o;
import org.json.JSONObject;

public class i {
    private static final String a = "LogEventCreator";
    private static final String b = "v";
    private static final String c = "sessionId";
    private static final String d = "appId";
    private static final String e = "configKey";
    private static final String f = "content";
    private static final String g = "eventTime";
    private static final String h = "logType";
    private static final String i = "extra";
    private static final String j = "idType";

    public static LogEvent a(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("v");
            if (i == 1) {
                return a(context, jSONObject);
            }
            if (i == 2) {
                o.a(a, "json version is 2");
                return b(context, jSONObject);
            }
            throw new RuntimeException("should not be reached.");
        } catch (Throwable e) {
            Log.e(o.a(a), "createFromJson e", e);
            return null;
        }
    }

    private static LogEvent a(Context context, JSONObject jSONObject) {
        try {
            LogEvent aVar;
            String string = jSONObject.getString(e);
            String string2 = jSONObject.getString(d);
            String optString = jSONObject.optString(f);
            if (LogType.a(jSONObject.optInt(h, LogType.TYPE_EVENT.a())) == LogType.TYPE_AD) {
                aVar = new a(context, string2, string, optString);
                aVar.b(jSONObject.optString(i));
            } else {
                aVar = new LogEvent(context, string2, string, optString);
            }
            aVar.a(jSONObject.optString(c));
            long optLong = jSONObject.optLong("eventTime");
            if (optLong <= 0) {
                return aVar;
            }
            aVar.a(optLong);
            return aVar;
        } catch (Throwable e) {
            Log.e(o.a(a), "fromJsonV1 e", e);
            return null;
        }
    }

    private static LogEvent b(Context context, JSONObject jSONObject) {
        try {
            LogEvent aVar;
            String string = jSONObject.getString(e);
            String string2 = jSONObject.getString(d);
            String optString = jSONObject.optString(f);
            int optInt = jSONObject.optInt(h, LogType.TYPE_EVENT.a());
            int optInt2 = jSONObject.optInt(j, IdType.TYPE_DEFAULT.a());
            if (optInt == LogType.TYPE_AD.a()) {
                aVar = new a(context, string2, string, optString, optInt2);
                aVar.b(jSONObject.optString(i));
            } else {
                aVar = new LogEvent(context, string2, string, optString, optInt, optInt2);
            }
            aVar.a(jSONObject.optString(c));
            long optLong = jSONObject.optLong("eventTime");
            if (optLong <= 0) {
                return aVar;
            }
            aVar.a(optLong);
            return aVar;
        } catch (Throwable e) {
            Log.e(o.a(a), "fromJsonV2 e", e);
            return null;
        }
    }
}
