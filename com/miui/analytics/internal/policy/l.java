package com.miui.analytics.internal.policy;

import com.miui.analytics.internal.b.h;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.List;

public class l implements k {
    private static final String a = "TriggerPolicy";
    private List<k> b = new ArrayList();

    public l(List<k> list) {
        if (list != null) {
            this.b = list;
        }
    }

    public boolean a(h hVar, int i) {
        for (k kVar : this.b) {
            if (kVar.a(hVar, i)) {
                o.a(a, "trigger:" + kVar.getClass().getName());
                return true;
            }
        }
        return false;
    }
}
