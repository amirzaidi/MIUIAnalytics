package com.miui.analytics.internal.a;

import android.content.Context;
import android.text.TextUtils;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class a {
    private static final String a = "AdEventBuilder";

    public List<e> a(Context context, List<LogEvent> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null || context == null) {
            return arrayList;
        }
        if (list != null) {
            o.a(a, "param events size: " + list.size());
            for (LogEvent logEvent : list) {
                if (logEvent != null && (logEvent instanceof com.miui.analytics.internal.a)) {
                    arrayList.addAll(a((com.miui.analytics.internal.a) logEvent));
                }
            }
        }
        return arrayList;
    }

    private List<e> a(com.miui.analytics.internal.a aVar) {
        List arrayList = new ArrayList();
        try {
            Object a = aVar.a();
            o.a(a, "parseAdMonitor adMonitor field: " + a);
            if (!TextUtils.isEmpty(a)) {
                String[] split = a.split("\\|");
                o.a(a, "adMonitor url size: " + split.length);
                for (String str : split) {
                    String str2;
                    if (!TextUtils.isEmpty(str2)) {
                        e eVar = new e();
                        eVar.a(aVar.e());
                        if (str2.contains("api.ad.xiaomi.com") && !str2.contains("_sn_")) {
                            str2 = str2.contains("?") ? str2 + "&_sn_=" + UUID.randomUUID().toString() : str2 + "?_sn_=" + UUID.randomUUID().toString();
                        }
                        o.a(a, "adMonitor " + str2);
                        eVar.a(str2);
                        eVar.b(aVar.c());
                        arrayList.add(eVar);
                    }
                }
            }
        } catch (Exception e) {
        }
        return arrayList;
    }
}
