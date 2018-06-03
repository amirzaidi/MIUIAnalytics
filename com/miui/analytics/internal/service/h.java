package com.miui.analytics.internal.service;

import android.text.TextUtils;
import com.miui.analytics.internal.util.af;
import com.miui.analytics.internal.util.m;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import org.apache.http.NameValuePair;

public abstract class h<T> {
    private static final String a = "Server";
    private static final String b = "miui_sdkconfig_jafej!@#)(*e@!#";
    public static final String f = "ts";
    public static final String g = "nonce";
    public static final String h = "sign";
    protected String i;
    protected long j = System.currentTimeMillis();

    public abstract HttpRequest a();

    public abstract g<T> a(d dVar);

    public h(String str) {
        this.i = i.a(str);
    }

    public String b() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }

    public g<T> a(c cVar) {
        return a(cVar.a(a()));
    }

    public g<T> c() {
        return a(new k());
    }

    public String b(d dVar) {
        if (dVar != null) {
            byte[] b = m.b(dVar.a());
            if (b != null) {
                String str = new String(b);
                o.a(a, "server response string=" + str);
                return str;
            }
            o.a(a, "no response");
        }
        return null;
    }

    protected long d() {
        return this.j;
    }

    protected String e() {
        Random random = new Random(System.nanoTime());
        try {
            return af.a(UUID.randomUUID().toString() + ":" + random.nextLong());
        } catch (Exception e) {
            return af.a(random.nextLong() + "");
        }
    }

    protected String a(NameValuePair[] nameValuePairArr) {
        List arrayList = new ArrayList();
        if (nameValuePairArr != null) {
            for (Object add : nameValuePairArr) {
                arrayList.add(add);
            }
        }
        return a(arrayList);
    }

    protected String a(List<NameValuePair> list) {
        StringBuilder stringBuilder = new StringBuilder();
        if (list != null) {
            HashMap hashMap = new HashMap();
            List<String> arrayList = new ArrayList();
            for (NameValuePair nameValuePair : list) {
                if (!(nameValuePair == null || TextUtils.isEmpty(nameValuePair.getName()))) {
                    String name = nameValuePair.getName();
                    if (!hashMap.containsKey(name)) {
                        arrayList.add(name);
                    }
                    hashMap.put(name, nameValuePair);
                }
            }
            Collections.sort(arrayList);
            for (String str : arrayList) {
                stringBuilder.append(str + ((NameValuePair) hashMap.get(str)).getValue());
            }
        }
        stringBuilder.append(b);
        return af.a(stringBuilder.toString()).toLowerCase(Locale.getDefault());
    }
}
