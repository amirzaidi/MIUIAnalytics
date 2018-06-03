package com.miui.analytics.internal;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.analytics.internal.LogEvent.LogType;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class a extends LogEvent {
    protected static final String a = "_ad_monitor_";

    public a(Context context, String str, String str2, String str3, int i) {
        super(context, str, str2, str3, LogType.TYPE_AD.a(), i);
    }

    public a(Context context, String str, String str2, String str3) {
        super(context, str, str2, str3, LogType.TYPE_AD.a());
    }

    public a(Cursor cursor) {
        super(cursor);
    }

    public String a() {
        try {
            if (!TextUtils.isEmpty(this.k)) {
                return new JSONObject(this.k).getString(a);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static List<LogEvent> a(List<LogEvent> list) {
        List arrayList = new ArrayList();
        if (list != null) {
            for (LogEvent logEvent : list) {
                if (logEvent != null && (logEvent instanceof a)) {
                    arrayList.add(logEvent);
                }
            }
        }
        return arrayList;
    }

    public static List<LogEvent> a(LogEvent[] logEventArr) {
        List arrayList = new ArrayList();
        if (logEventArr != null) {
            for (Object obj : logEventArr) {
                if (obj != null && (obj instanceof a)) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }
}
