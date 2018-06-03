package com.miui.analytics.internal.c;

import com.miui.analytics.internal.LogEvent;
import java.util.List;
import org.json.JSONObject;

public interface b {
    void a();

    void a(List<LogEvent> list);

    void a(JSONObject jSONObject);

    void a(boolean z);

    void a(boolean z, List<LogEvent> list);

    void b(List<LogEvent> list);

    void b(boolean z);

    void c(List<LogEvent> list);

    void d(List<LogEvent> list);

    void e(List<LogEvent> list);
}
