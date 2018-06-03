package com.miui.analytics.internal.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.policy.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class q {
    private static final String a = "MDSM";
    private static volatile q b = null;
    private static final String f = "c";
    private static final String g = "w";
    private static final String h = "p";
    private static final String i = "t";
    private static final String j = "cellids";
    private static final String k = "bssids";
    private static final String l = "ssids";
    private static final String m = "lately_ssids";
    private static final String n = "lately_bssids";
    private static final String o = "lately_time";
    private static final String p = "timeRange";
    private static final String q = "startTime";
    private static final String r = "endTime";
    private p c;
    private WifiManager d;
    private Context e;
    private String s;
    private Lock t;
    private BroadcastReceiver u = new BroadcastReceiver(this) {
        final /* synthetic */ q a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                o.a(q.a, "Action:" + action);
                if ("android.net.wifi.SCAN_RESULTS".equals(action)) {
                    List scanResults = this.a.d.getScanResults();
                    if (scanResults != null && scanResults.size() != 0) {
                        q.a(context).a(context, scanResults);
                    }
                }
            } catch (Throwable e) {
                Log.e(o.a(q.a), "receive scan_results_available_action, exception:", e);
            }
        }
    };

    private q(Context context) {
        try {
            this.e = c.a(context);
            this.c = p.a(this.e);
            this.d = (WifiManager) this.e.getSystemService(f.i);
            this.t = new ReentrantLock();
            this.s = "";
        } catch (Throwable e) {
            Log.e(o.a(a), "mdsManager exception:", e);
        }
    }

    public static synchronized q a(Context context) {
        q qVar;
        synchronized (q.class) {
            if (b == null) {
                b = new q(context);
            }
            qVar = b;
        }
        return qVar;
    }

    private boolean a(long j) {
        try {
            if (j - this.c.b() > g.d()) {
                this.c.a(j);
                return true;
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "checkMdsMatchInterval exception:", e);
        }
        return false;
    }

    public void a() {
        if (!n.a(this.e, a)) {
            o.a(a, "registerWifiReceiver");
            this.e.registerReceiver(this.u, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
        }
    }

    private JSONObject a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            long optLong = jSONObject.optLong(q);
            long optLong2 = jSONObject.optLong(r);
            if (optLong < 0 || optLong > optLong2 || optLong > System.currentTimeMillis() + ac.a) {
                return null;
            }
            if (optLong2 > System.currentTimeMillis() + ac.a) {
                optLong2 = System.currentTimeMillis() + ac.a;
            }
            if (optLong2 - optLong > ac.b) {
                optLong2 = optLong + ac.b;
            }
            jSONObject.put(q, optLong);
            jSONObject.put(r, optLong2);
            return jSONObject;
        } catch (Throwable e) {
            Log.e(o.a(a), "verifyTimeRange exception;", e);
            return null;
        }
    }

    private boolean a(String str, JSONObject jSONObject) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (TextUtils.isEmpty(str)) {
                jSONObject.put(p, "");
                jSONObject.put("t", currentTimeMillis);
            } else {
                JSONObject a = a(str);
                if (a == null) {
                    return false;
                }
                jSONObject.put(p, a);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "checkRegisterTimeRange exception:", e);
        }
        return true;
    }

    public boolean a(long j, String str, JSONObject jSONObject, String str2) throws JSONException {
        try {
            o.a(a, "saveMdsRegisterInfo..");
            JSONObject jSONObject2 = new JSONObject();
            if (!a(str2, jSONObject2)) {
                return false;
            }
            jSONObject2.put("c", j);
            jSONObject2.put("w", jSONObject);
            jSONObject2.put("p", str);
            JSONArray jSONArray = new JSONArray();
            this.t.lock();
            if (TextUtils.isEmpty(this.s)) {
                this.s = this.c.e();
            }
            Object obj = this.s;
            if (!TextUtils.isEmpty(obj)) {
                JSONArray jSONArray2 = new JSONArray(obj);
                if (jSONArray2.length() > 100) {
                    o.a(a, String.format("prefs.length > 100 registerInfo.lenth:%d", new Object[]{Integer.valueOf(jSONObject2.length())}));
                    for (int i = 1; i < jSONArray2.length(); i++) {
                        jSONArray.put(jSONArray2.get(i));
                    }
                } else {
                    jSONArray = jSONArray2;
                }
            }
            jSONArray.put(jSONObject2);
            this.s = jSONArray.toString();
            this.c.b(this.s);
            this.t.unlock();
            this.c.c(System.currentTimeMillis());
            o.a(a, String.format("into local register info requestCode:%d", new Object[]{Long.valueOf(j)}));
            return true;
        } catch (Throwable e) {
            Log.e(o.a(a), "saveMdsRegisterInfo exception:", e);
            return false;
        } catch (Throwable th) {
            this.t.unlock();
        }
    }

    private static void a(List<ScanResult> list) {
        Collections.sort(list, new Comparator<ScanResult>() {
            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((ScanResult) obj, (ScanResult) obj2);
            }

            public int a(ScanResult scanResult, ScanResult scanResult2) {
                return scanResult2.level - scanResult.level;
            }
        });
    }

    public void a(final Context context, final List<ScanResult> list) {
        if (af.a() && a(System.currentTimeMillis())) {
            ab.a(new Runnable(this) {
                final /* synthetic */ q c;

                public void run() {
                    try {
                        this.c.t.lock();
                        JSONArray b = this.c.b(context, list);
                        if (b.length() != 0) {
                            this.c.a(context, b);
                        }
                        this.c.c.b(this.c.s);
                    } finally {
                        this.c.t.unlock();
                    }
                }
            });
        }
    }

    public JSONArray b(Context context, List<ScanResult> list) {
        JSONArray jSONArray = new JSONArray();
        JSONArray a;
        try {
            if (list.isEmpty() || TextUtils.isEmpty(this.c.e())) {
                return jSONArray;
            }
            a((List) list);
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray2 = new JSONArray();
            JSONArray jSONArray3 = new JSONArray();
            for (ScanResult scanResult : list) {
                hashSet.add(scanResult.BSSID);
                hashSet2.add(scanResult.SSID);
                jSONArray2.put(scanResult.BSSID);
                jSONArray3.put(scanResult.SSID);
            }
            jSONObject.put(m, jSONArray3);
            jSONObject.put(n, jSONArray2);
            jSONObject.put(o, System.currentTimeMillis());
            this.c.c(jSONObject.toString());
            a = a(context, hashSet, hashSet2);
            return a;
        } catch (Throwable e) {
            Log.e(o.a(a), "matchScannedSpot exception:", e);
            a = jSONArray;
        }
    }

    private JSONArray a(Context context, HashSet<String> hashSet, HashSet<String> hashSet2) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONArray jSONArray2 = new JSONArray();
            long currentTimeMillis = System.currentTimeMillis();
            if (TextUtils.isEmpty(this.s)) {
                this.s = this.c.e();
            }
            JSONArray jSONArray3 = new JSONArray(this.s);
            if (jSONArray3.length() <= 0) {
                return jSONArray;
            }
            for (int length = jSONArray3.length() - 1; length >= 0; length--) {
                JSONObject jSONObject = (JSONObject) jSONArray3.get(length);
                if (!(jSONObject.get(p) instanceof String)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(p);
                    if (currentTimeMillis <= jSONObject2.getLong(r)) {
                        jSONArray2.put(jSONObject);
                        if (currentTimeMillis > jSONObject2.getLong(q) && currentTimeMillis < jSONObject2.getLong(r)) {
                            int i;
                            String string = jSONObject.getString("p");
                            long j = jSONObject.getLong("c");
                            jSONObject = jSONObject.getJSONObject("w");
                            JSONArray jSONArray4 = jSONObject.getJSONArray(k);
                            JSONArray jSONArray5 = jSONObject.getJSONArray(l);
                            JSONArray optJSONArray = jSONObject.optJSONArray(j);
                            for (i = 0; i < jSONArray4.length(); i++) {
                                if (hashSet.contains(jSONArray4.getString(i))) {
                                    a(jSONArray, string, j);
                                }
                            }
                            for (i = 0; i < jSONArray5.length(); i++) {
                                if (hashSet2.contains(jSONArray5.getString(i))) {
                                    a(jSONArray, string, j);
                                }
                            }
                            Object i2 = this.c.i();
                            if (!TextUtils.isEmpty(i2)) {
                                for (i = 0; i < optJSONArray.length(); i++) {
                                    if (i2.equals(optJSONArray.getString(i))) {
                                        a(jSONArray, string, j);
                                    }
                                }
                            }
                        }
                    }
                } else if (!ac.a(jSONObject.getLong("t"), (long) ac.a)) {
                    jSONArray2.put(jSONObject);
                }
            }
            this.s = jSONArray2.toString();
            return jSONArray;
        } catch (Throwable e) {
            Log.e(o.a(a), "getMatchedAsyncMdsRegistry exception", e);
        }
    }

    private void a(JSONArray jSONArray, String str, long j) {
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (j != jSONObject.getLong("c") || !str.equals(jSONObject.getString("p"))) {
                    i++;
                } else {
                    return;
                }
            } catch (Throwable e) {
                Log.e(o.a(a), "addToMatched exception", e);
                return;
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("c", j);
        jSONObject2.put("p", str);
        jSONArray.put(jSONObject2);
    }

    public void a(final Context context, final CellLocation cellLocation) {
        if (af.a() && a(System.currentTimeMillis())) {
            ab.a(new Runnable(this) {
                final /* synthetic */ q c;

                public void run() {
                    try {
                        this.c.t.lock();
                        JSONArray b = this.c.b(context, cellLocation);
                        if (b.length() != 0) {
                            this.c.a(context, b);
                        }
                        this.c.c.b(this.c.s);
                    } finally {
                        this.c.t.unlock();
                    }
                }
            });
        }
    }

    public JSONArray b(Context context, CellLocation cellLocation) {
        JSONArray jSONArray = new JSONArray();
        try {
            o.a(a, "matchCellLocation..");
            if (TextUtils.isEmpty(this.c.e()) || cellLocation == null) {
                return jSONArray;
            }
            JSONArray jSONArray2 = new JSONArray();
            String a = this.c.a(cellLocation);
            if (TextUtils.isEmpty(this.s)) {
                this.s = this.c.e();
            }
            JSONArray jSONArray3 = new JSONArray(this.s);
            long currentTimeMillis = System.currentTimeMillis();
            for (int i = 0; i < jSONArray3.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray3.get(i);
                if (!(jSONObject.get(p) instanceof String)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(p);
                    if (currentTimeMillis <= jSONObject2.getLong(r)) {
                        jSONArray2.put(jSONObject);
                        if (currentTimeMillis > jSONObject2.getLong(q) && currentTimeMillis < jSONObject2.getLong(r)) {
                            JSONArray optJSONArray = ((JSONObject) jSONObject.get("w")).optJSONArray(j);
                            String string = jSONObject.getString("p");
                            long j = jSONObject.getLong("c");
                            if (!a.isEmpty()) {
                                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                    if (a.equals(optJSONArray.getString(i2))) {
                                        a(jSONArray, string, j);
                                    }
                                }
                            }
                        }
                    }
                } else if (!ac.a(jSONObject.getLong("t"), (long) ac.a)) {
                    jSONArray2.put(jSONObject);
                }
            }
            this.s = jSONArray2.toString();
            return jSONArray;
        } catch (Throwable e) {
            Log.e(o.a(a), "matchCellLocation exception:", e);
        }
    }

    private void a(Context context, JSONArray jSONArray) {
        try {
            List arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("p");
                if (!arrayList.contains(string)) {
                    arrayList.add(string);
                    long j = jSONObject.getLong("c");
                    List arrayList2 = new ArrayList();
                    arrayList2.add(Long.valueOf(j));
                    for (int i2 = i + 1; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                        String string2 = jSONObject2.getString("p");
                        long j2 = jSONObject2.getLong("c");
                        if (string2.equals(string)) {
                            arrayList2.add(Long.valueOf(j2));
                        }
                    }
                    Intent intent = new Intent("miui.intent.action.CONDITION_SERVICE");
                    Bundle bundle = new Bundle();
                    long[] jArr = new long[arrayList2.size()];
                    for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                        jArr[i3] = ((Long) arrayList2.get(i3)).longValue();
                    }
                    bundle.putLongArray("c", jArr);
                    intent.putExtras(bundle);
                    intent.setPackage(string);
                    context.startService(intent);
                }
            }
            a(jSONArray);
        } catch (Throwable e) {
            Log.e(o.a(a), "notifyRecipients exception:", e);
        }
    }

    private void a(JSONArray jSONArray) {
        int i = 0;
        try {
            JSONArray jSONArray2 = new JSONArray();
            if (TextUtils.isEmpty(this.s)) {
                this.s = this.c.e();
            }
            Object obj = this.s;
            if (!TextUtils.isEmpty(obj)) {
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    long j = jSONObject.getLong("c");
                    String string = jSONObject.getString("p");
                    arrayList.add(Long.valueOf(j));
                    arrayList2.add(string);
                }
                JSONArray jSONArray3 = new JSONArray(obj);
                while (i < jSONArray3.length()) {
                    JSONObject jSONObject2 = jSONArray3.getJSONObject(i);
                    long j2 = jSONObject2.getLong("c");
                    String string2 = jSONObject2.getString("p");
                    if (arrayList.contains(Long.valueOf(j2)) && arrayList2.contains(string2)) {
                        o.a(a, String.format("remove match requestCode:%d pkg:%s", new Object[]{Long.valueOf(j2), string2}));
                    } else {
                        jSONArray2.put(jSONObject2);
                    }
                    i++;
                }
                this.s = jSONArray2.toString();
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "matchSyncModeMds exception:", e);
        }
    }

    public boolean a(long j, String str) {
        try {
            if (!af.a()) {
                return false;
            }
            Object e = this.c.e();
            if (TextUtils.isEmpty(e)) {
                return false;
            }
            JSONArray jSONArray = new JSONArray(e);
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                    long j2 = jSONObject.getLong("c");
                    String string = jSONObject.getString("p");
                    if ((jSONObject.get(p) instanceof String) && j == j2 && string.equals(str)) {
                        String str2;
                        int i2;
                        o.a(a, String.format("go sync mode match :%d", new Object[]{Long.valueOf(j)}));
                        jSONObject = (JSONObject) jSONObject.get("w");
                        JSONArray jSONArray2 = jSONObject.getJSONArray(k);
                        JSONArray jSONArray3 = jSONObject.getJSONArray(l);
                        JSONArray jSONArray4 = jSONObject.getJSONArray(j);
                        WifiInfo h = this.c.h();
                        string = "";
                        String str3 = "";
                        if (h != null) {
                            string = h.getBSSID();
                            str3 = h.getSSID();
                            str2 = string;
                            string = str3;
                        } else {
                            str2 = string;
                            string = str3;
                        }
                        String i3 = this.c.i();
                        if (!str2.isEmpty()) {
                            for (i2 = 0; i2 < jSONArray2.length(); i2++) {
                                if (jSONArray2.getString(i2).equals(str2)) {
                                    o.a(a, String.format("bssid sync matched:%s", new Object[]{r9}));
                                    return true;
                                }
                            }
                        }
                        if (!string.isEmpty()) {
                            for (i2 = 0; i2 < jSONArray3.length(); i2++) {
                                if (jSONArray3.getString(i2).equals(string)) {
                                    o.a(a, String.format("ssid sync matched:%s", new Object[]{str2}));
                                    return true;
                                }
                            }
                        }
                        if (!i3.isEmpty()) {
                            for (i2 = 0; i2 < jSONArray4.length(); i2++) {
                                if (jSONArray4.getString(i2).equals(i3)) {
                                    o.a(a, String.format("cellid sync matched:%s", new Object[]{string}));
                                    return true;
                                }
                            }
                        }
                        if (a(jSONArray3, jSONArray2)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Throwable e2) {
            Log.e(o.a(a), "matchSyncModeMds exception;", e2);
        }
    }

    private boolean a(JSONArray jSONArray, JSONArray jSONArray2) {
        try {
            if (!TextUtils.isEmpty(this.c.f())) {
                JSONObject jSONObject = new JSONObject(this.c.f());
                if (System.currentTimeMillis() - jSONObject.getLong(o) < 60000) {
                    int i;
                    JSONArray jSONArray3 = jSONObject.getJSONArray(n);
                    JSONArray jSONArray4 = jSONObject.getJSONArray(m);
                    List arrayList = new ArrayList();
                    List arrayList2 = new ArrayList();
                    for (i = 0; i < jSONArray3.length(); i++) {
                        arrayList.add(jSONArray3.getString(i));
                    }
                    for (i = 0; i < jSONArray4.length(); i++) {
                        arrayList2.add(jSONArray4.getString(i));
                    }
                    for (i = 0; i < jSONArray2.length(); i++) {
                        if (arrayList.contains(jSONArray2.getString(i))) {
                            o.a(a, String.format("bssid scaned spot match:", new Object[]{r3}));
                            return true;
                        }
                    }
                    for (i = 0; i < jSONArray.length(); i++) {
                        if (arrayList2.contains(jSONArray.getString(i))) {
                            o.a(a, String.format("ssid scaned spot match:", new Object[]{r3}));
                            return true;
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "matchLatelyScanedSpot exception:", e);
        }
        return false;
    }
}
