package com.miui.analytics.internal.collection;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.b.i;
import com.miui.analytics.internal.b.j;
import com.miui.analytics.internal.k;
import com.miui.analytics.internal.policy.f;
import com.miui.analytics.internal.service.HttpRequest;
import com.miui.analytics.internal.service.HttpRequest.Method;
import com.miui.analytics.internal.service.d;
import com.miui.analytics.internal.service.g;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import com.miui.analytics.internal.util.z;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

public class h {
    private static final String a = "UploadNearestStore";
    private static final String b = "https://storeconfig.mistat.xiaomi.com/api/store/location_config";
    private static final String c = "imeiMd5";
    private static final String d = "lastVersion";
    private static final String e = "expiredDays";
    private static final String f = "nearest_store_last_ts";
    private static volatile h g;
    private static final Object j = new Object();
    private Context h;
    private v i = new v(this.h, u.d);

    private class a extends Thread {
        private static final int b = 10000;
        final /* synthetic */ h a;
        private LocationManager c;
        private LocationListener d;
        private Location e;
        private Looper f;

        private a(h hVar) {
            this.a = hVar;
        }

        public void run() {
            Looper.prepare();
            this.f = Looper.myLooper();
            try {
                this.e = null;
                try {
                    if (this.a.h != null) {
                        if (c.c(this.a.h, "android.permission.ACCESS_FINE_LOCATION") || c.c(this.a.h, "android.permission.ACCESS_COARSE_LOCATION")) {
                            this.c = (LocationManager) this.a.h.getSystemService("location");
                            if (this.c != null) {
                                this.d = new LocationListener(this) {
                                    final /* synthetic */ a a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onStatusChanged(String str, int i, Bundle bundle) {
                                    }

                                    public void onProviderEnabled(String str) {
                                    }

                                    public void onProviderDisabled(String str) {
                                    }

                                    public void onLocationChanged(Location location) {
                                        o.a(h.a, "onLChanged:" + location.toString());
                                        try {
                                            this.a.e = location;
                                            if (this.a.d == null) {
                                                return;
                                            }
                                            if (c.c(this.a.a.h, "android.permission.ACCESS_FINE_LOCATION") || c.c(this.a.a.h, "android.permission.ACCESS_COARSE_LOCATION")) {
                                                this.a.c.removeUpdates(this.a.d);
                                            }
                                        } catch (Throwable e) {
                                            Log.e(o.a(h.a), "onLChanged e", e);
                                            this.a.a();
                                        }
                                    }
                                };
                                if (r.b(this.a.h)) {
                                    o.a(h.a, "net work accessible, get l use NETWORK_PROVIDER");
                                    if (this.c.isProviderEnabled(f.g)) {
                                        this.c.requestLocationUpdates(f.g, 0, 0.0f, this.d);
                                    } else {
                                        o.a(h.a, "isProviderEnabled(LocationManager.NETWORK_PROVIDER) == false");
                                    }
                                }
                                Looper.loop();
                                return;
                            }
                            return;
                        }
                        o.a(h.a, "when run LGetter,not have get l permission! skip get l");
                    }
                } catch (Throwable e) {
                    Log.e(o.a(h.a), "LocationGetter requestLocationUpdates e", e);
                    a();
                }
            } catch (Throwable e2) {
                Log.e(o.a(h.a), "LocationGetter run e", e2);
            }
        }

        public void a() {
            try {
                o.a(h.a, "LocationGetter cancel");
                if (c.c(this.a.h, "android.permission.ACCESS_FINE_LOCATION") || c.c(this.a.h, "android.permission.ACCESS_COARSE_LOCATION")) {
                    if (this.e == null && this.c.isProviderEnabled(f.g)) {
                        Log.w(o.a(h.a), " get network l fail,to getLastKnownLocation NETWORK_PROVIDER");
                        this.e = this.c.getLastKnownLocation(f.g);
                    }
                    if (this.e == null && this.c.isProviderEnabled("gps")) {
                        Log.w(o.a(h.a), " get network l fail,to getLastKnownLocation GPS_PROVIDER");
                        this.e = this.c.getLastKnownLocation("gps");
                    }
                    if (this.e == null) {
                        Log.w(o.a(h.a), " can't get available l");
                    }
                    this.c.removeUpdates(this.d);
                    return;
                }
                o.a(h.a, "when cancel LocationGetter, not have get l permission! skip get l");
            } catch (Throwable e) {
                Log.e(o.a(h.a), "LocationGetter cancel e", e);
            }
        }

        public Location b() {
            return this.e;
        }

        public Looper c() {
            return this.f;
        }
    }

    private class b implements Runnable {
        final /* synthetic */ h a;

        private b(h hVar) {
            this.a = hVar;
        }

        public void run() {
            try {
                if (!n.a(this.a.h, h.a) && this.a.a(true)) {
                    g c = new com.miui.analytics.internal.service.h<String>(this, h.b) {
                        final /* synthetic */ b a;

                        public HttpRequest a() {
                            String f;
                            HttpRequest httpRequest = new HttpRequest(this.i);
                            httpRequest.a(Method.GET);
                            if (this.a.a.h()) {
                                f = z.f(this.a.a.h);
                            } else {
                                f = z.d(this.a.a.h);
                            }
                            String e = this.a.a.f();
                            String str = com.miui.analytics.internal.util.g.p() + "";
                            httpRequest.a(h.c, f);
                            httpRequest.a(h.d, e);
                            httpRequest.a(h.e, str);
                            httpRequest.a(com.miui.analytics.internal.service.h.h, a(new NameValuePair[]{new BasicNameValuePair(h.c, f), new BasicNameValuePair(h.d, e), new BasicNameValuePair(h.e, str)}));
                            o.a(h.a, "url: " + httpRequest.e());
                            return httpRequest;
                        }

                        public g<String> a(d dVar) {
                            Object b = b(dVar);
                            o.a(h.a, "request update stores result:" + b);
                            if (TextUtils.isEmpty(b)) {
                                return null;
                            }
                            return g.a(b);
                        }
                    }.c();
                    if (c != null) {
                        String str = (String) c.a;
                        JSONObject jSONObject = new JSONObject(str);
                        this.a.a(jSONObject.optString("curVersion"));
                        z.a(this.a.h, jSONObject.optLong("activeTime"));
                        int optInt = jSONObject.optInt("code");
                        if (optInt < 0 || optInt > 1) {
                            Log.e(o.a(h.a), "update stores error:" + str);
                            return;
                        }
                        i.a(this.a.h).a(j.a(jSONObject.optJSONArray("stores")));
                    }
                }
            } catch (Throwable e) {
                Log.e(o.a(h.a), "updateStoreLocationInfos e", e);
            }
        }
    }

    public static h a(Context context) {
        if (g == null) {
            synchronized (h.class) {
                if (g == null) {
                    g = new h(context);
                }
            }
        }
        return g;
    }

    private h(Context context) {
        this.h = context;
    }

    public void a() {
        ab.a(new b());
    }

    private boolean d() {
        long p = z.p(this.h);
        o.a(a, "activeTime:" + p);
        if (p == 0) {
            return false;
        }
        long p2 = ((long) com.miui.analytics.internal.util.g.p()) * ac.b;
        o.a(a, "interval:" + p2);
        if (ac.a(p, p2)) {
            return true;
        }
        return false;
    }

    private void a(long j) {
        this.i.a(f, j);
    }

    private long e() {
        return this.i.b(f, 0);
    }

    private void a(String str) {
        try {
            this.i.a("store_info_version", str);
        } catch (Throwable e) {
            Log.e(o.a(a), "setStoreInfoVersion", e);
        }
    }

    private String f() {
        try {
            return this.i.b("store_info_version", "");
        } catch (Throwable e) {
            Log.e(o.a(a), "getStoreInfoVersion e", e);
            return "";
        }
    }

    public boolean a(boolean z) {
        if (n.a()) {
            o.a(a, "Not allow to upload in international build.");
            return false;
        }
        if (z) {
            com.miui.analytics.internal.policy.h.a(this.h).a("com.miui.analytics");
        }
        if (!com.miui.analytics.internal.util.g.r()) {
            o.a(a, "Upload NearestStore unable! skip");
            return false;
        } else if (d()) {
            o.a(a, "upload NearestStores time expired! skip");
            return false;
        } else if (com.miui.analytics.internal.util.g.q() > 0) {
            return true;
        } else {
            o.a(a, "upload NearestStores count <=0! skip");
            return false;
        }
    }

    public void b() {
        ab.a(new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void run() {
                synchronized (h.j) {
                    try {
                        if (ac.b(this.a.e())) {
                            o.a(h.a, "already upload NearestStores today! skip upload");
                        } else if (n.a(this.a.h, h.a)) {
                        } else if (this.a.a(true)) {
                            JSONArray c = this.a.g();
                            if (c == null || c.length() == 0) {
                                o.a(h.a, "get content is null or empty, skip upload!");
                                return;
                            }
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(b.g, c);
                            k.a(this.a.h).a(new LogEvent(this.a.h, "com.miui.analytics", com.miui.analytics.internal.f.r, jSONObject.toString()));
                            this.a.a(System.currentTimeMillis());
                            o.a(h.a, "upload NearestStores content :" + jSONObject.toString());
                        }
                    } catch (Throwable e) {
                        Log.e(o.a(h.a), "upload e", e);
                    }
                }
            }
        });
    }

    private JSONArray g() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.e(o.a(a), "please do not getNearestStores in main thread!");
            return null;
        }
        try {
            a aVar = new a();
            aVar.start();
            try {
                aVar.join(10000);
                aVar.a();
            } catch (Throwable e) {
                Log.e(o.a(a), "LocationGetter thread is interrupted mLocation", e);
            }
            Location b = aVar.b();
            aVar.c().quit();
            if (b == null) {
                o.a(a, "get l = null, set next alarm");
                com.miui.analytics.internal.b.g(this.h);
                return null;
            }
            i a = i.a(this.h);
            List a2 = a.a();
            if (a2 == null || a2.size() == 0) {
                o.b(a, "db storeLocationInfoList is null or size = 0,try to get from net");
                Thread thread = new Thread(new b());
                thread.start();
                thread.join();
            }
            List a3 = a.a();
            if (a3 == null || a3.size() == 0) {
                o.b(a, "after try to get from net, db storeLocationInfoList is still null or size = 0");
                return null;
            }
            List<j> a4 = a(b, a3);
            if (a4.size() == 0) {
                o.a(a, "get nearest stores is null! skip upload");
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            DecimalFormat decimalFormat = new DecimalFormat(".####");
            for (j jVar : a4) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", jVar.a);
                jSONObject.put("dis", decimalFormat.format(jVar.d));
                jSONArray.put(jSONObject);
            }
            return jSONArray;
        } catch (Throwable e2) {
            Log.e(o.a(a), "getNearestShops e", e2);
            return null;
        }
    }

    private List<j> a(Location location, List<j> list) {
        o.a("UploadNearestStorecal", "size: " + list.size());
        List arrayList = new ArrayList();
        com.miui.analytics.internal.util.i iVar = new com.miui.analytics.internal.util.i();
        int q = com.miui.analytics.internal.util.g.q();
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            j jVar = (j) list.get(i);
            if (jVar == null) {
                o.a(a, "for info = null");
            } else {
                jVar.d = iVar.a(location.getLatitude(), location.getLongitude(), jVar.c, jVar.b);
                if (i < q) {
                    arrayList.add(jVar);
                    if (i == q - 1) {
                        a(arrayList);
                    }
                } else if (jVar.d < ((j) arrayList.get(q - 1)).d) {
                    arrayList.set(q - 1, jVar);
                    a(arrayList);
                }
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        o.a("UploadNearestStorecal", currentTimeMillis + "  " + currentTimeMillis2 + "    " + (currentTimeMillis2 - currentTimeMillis));
        o.a(a, "min stores:" + arrayList.toString());
        return arrayList;
    }

    private void a(List<j> list) {
        Collections.sort(list, new Comparator<j>(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((j) obj, (j) obj2);
            }

            public int a(j jVar, j jVar2) {
                return Double.compare(jVar.d, jVar2.d);
            }
        });
    }

    private boolean h() {
        return n.f() || n.a("is_pad", false);
    }
}
