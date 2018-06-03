package com.miui.analytics.internal.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class t {
    private static t a = null;
    private static final String b = "PPMonitor";
    private static final long c = 0;
    private static final float d = 0.0f;
    private Context e;
    private boolean f;
    private final b g = new b();
    private final LocationManager h = ((LocationManager) this.e.getSystemService("location"));
    private List<a> i = new ArrayList();

    public interface a {
        void a(Location location);
    }

    private class b implements LocationListener {
        final /* synthetic */ t a;

        private b(t tVar) {
            this.a = tVar;
        }

        public void onLocationChanged(Location location) {
            if (this.a.f) {
                this.a.a(location);
            }
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onProviderDisabled(String str) {
        }
    }

    public static synchronized t a(Context context) {
        t tVar;
        synchronized (t.class) {
            if (a == null) {
                a = new t(context);
            }
            tVar = a;
        }
        return tVar;
    }

    private t(Context context) {
        this.e = context;
    }

    public void a() {
        try {
            this.f = true;
            o.a(b, String.format("start with recordInterval:%d,recordMinDistance:%f", new Object[]{Long.valueOf(0), Float.valueOf(0.0f)}));
            if (this.e.checkPermission("android.permission.ACCESS_FINE_LOCATION", Binder.getCallingPid(), Binder.getCallingUid()) == 0 || this.e.checkPermission("android.permission.ACCESS_COARSE_LOCATION", Binder.getCallingPid(), Binder.getCallingUid()) == 0) {
                this.h.requestLocationUpdates("passive", 0, 0.0f, this.g);
            }
        } catch (Throwable e) {
            Log.e(o.a(b), "PassiveProviderMonitor start exception:", e);
        }
    }

    public void b() {
        try {
            this.f = false;
            if (this.e.checkPermission("android.permission.ACCESS_FINE_LOCATION", Binder.getCallingPid(), Binder.getCallingUid()) == 0 || this.e.checkPermission("android.permission.ACCESS_COARSE_LOCATION", Binder.getCallingPid(), Binder.getCallingUid()) == 0) {
                this.h.removeUpdates(this.g);
            }
        } catch (Throwable e) {
            Log.e(o.a(b), "PassiveProviderMonitor stop exception:", e);
        }
    }

    public void a(a aVar) {
        synchronized (this.i) {
            this.i.add(aVar);
            o.a(b, String.format("reigster listener, mListeners size: %d ", new Object[]{Integer.valueOf(this.i.size())}));
        }
    }

    private void a(Location location) {
        for (a a : this.i) {
            try {
                a.a(location);
            } catch (Throwable e) {
                Log.e(o.a(b), "onPassiveMdsChanged exception:", e);
            }
        }
    }
}
