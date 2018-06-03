package com.miui.analytics.internal.collection;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.service.e;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.m;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import org.json.JSONException;
import org.json.JSONObject;

public class j {
    private static final String a = "UploadScene";
    private static final String b = "t";
    private static final String c = "l";
    private static final String d = "f";
    private static final String e = "m";
    private static final String f = "r";
    private static final String g = "w";
    private static final String h = "bl";
    private static final String i = "b";
    private static final String j = "c";
    private static final String k = "v";
    private static final String l = "bt";
    private static final String m = "m";
    private static final String n = "c";
    private static final String o = "r";
    private static final String p = "m";
    private static final String q = "s";
    private static final String r = "b";
    private static volatile j t;
    private static final Object y = new Object();
    private v s;
    private Context u;
    private long v = g.d;
    private boolean w = false;
    private BroadcastReceiver x;

    public static j a(Context context) {
        if (t == null) {
            synchronized (j.class) {
                if (t == null) {
                    t = new j(context);
                }
            }
        }
        return t;
    }

    private j(Context context) {
        this.u = context;
        this.s = new v(this.u, u.d, u.g);
    }

    public void a() {
        try {
            if (this.w) {
                this.x = new BroadcastReceiver(this) {
                    final /* synthetic */ j a;

                    {
                        this.a = r1;
                    }

                    public void onReceive(Context context, Intent intent) {
                        if (intent != null) {
                            String action = intent.getAction();
                            o.a(j.a, "action:" + action);
                            if (action.equals("android.intent.action.SCREEN_OFF")) {
                                this.a.a(System.currentTimeMillis());
                            }
                        }
                    }
                };
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                this.u.registerReceiver(this.x, intentFilter);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "registerReceiver exception", e);
        }
    }

    public void b() {
        try {
            if (this.u != null && this.x != null) {
                this.u.unregisterReceiver(this.x);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "unregisterReceiver exception", e);
        }
    }

    private boolean h() {
        try {
            return ((PowerManager) this.u.getSystemService("power")).isScreenOn();
        } catch (Throwable e) {
            Log.e(o.a(a), "getScreenState exception", e);
            return true;
        }
    }

    public void a(long j) {
        try {
            if (this.s != null) {
                this.s.a(u.W, j);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "setScreenOffTime exception", e);
        }
    }

    public long c() {
        long j = 0;
        try {
            if (this.s != null) {
                j = this.s.b(u.W, 0);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "getScreenOffTime exception", e);
        }
        return j;
    }

    public long d() {
        return this.v;
    }

    public void b(long j) {
        this.v = j;
    }

    public boolean e() {
        return this.w;
    }

    public void a(boolean z) {
        this.w = z;
    }

    private void i() {
        try {
            Intent intent = new Intent("com.xiaomi.xmsf.push.XMSF_UPLOAD_ACTIVE");
            intent.putExtra("pkgname", this.u.getPackageName());
            intent.putExtra("category", "analytics_app_scene");
            intent.putExtra("name", "scene");
            intent.putExtra(e.b, b(this.u));
            this.u.sendBroadcast(intent, "com.xiaomi.xmsf.permission.USE_XMSF_UPLOAD");
        } catch (Throwable e) {
            Log.e(o.a(a), "uploadThroughSystemKeepAliveService exception", e);
        }
    }

    private boolean j() {
        try {
            if (!n.e(this.u)) {
                o.a(a, "uploadThroughSystemKeepAliveService is not SupportXMSFUpload");
                return false;
            } else if (!g.b(this.u)) {
                o.a(a, "unable upload scene, skip upload");
                return false;
            } else if (h() || !ac.a(c(), g.k())) {
                return true;
            } else {
                o.a(a, "处于锁屏状态，而且锁屏超过5min,则不上传");
                return false;
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "upload e", e);
            return false;
        }
    }

    public void f() {
        if (j()) {
            ab.a(new Runnable(this) {
                final /* synthetic */ j a;

                {
                    this.a = r1;
                }

                public void run() {
                    synchronized (j.y) {
                        if (ac.a(this.a.k(), (long) g.c)) {
                            this.a.n();
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                            this.a.i();
                            this.a.o();
                            this.a.l();
                            return;
                        }
                        o.a(j.a, "interval less min interval, skip");
                    }
                }
            });
        }
    }

    private long k() {
        long j = 0;
        try {
            j = this.s.b(u.Y, 0);
        } catch (Throwable e) {
            Log.e(o.a(a), "getLastUploadTime e", e);
        }
        return j;
    }

    private void l() {
        try {
            this.s.a(u.Y, System.currentTimeMillis());
        } catch (Throwable e) {
            Log.e(o.a(a), "saveLastUploadTime e", e);
        }
    }

    private boolean m() {
        if (VERSION.SDK_INT < 21) {
            return false;
        }
        return true;
    }

    private void n() {
        try {
            if (m()) {
                MusicStateListener.a(this.u);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "enableListenMusicState exception:", e);
        }
    }

    private void o() {
        try {
            if (m()) {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName(this.u, MusicStateListener.class);
                intent.setComponent(componentName);
                MusicStateListener.a(this.u, componentName);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "disableListenMusicState exception:", e);
        }
    }

    private String b(Context context) {
        String encodeToString;
        Throwable e;
        JSONObject jSONObject = new JSONObject();
        String str = "";
        try {
            jSONObject.put("t", System.currentTimeMillis());
            jSONObject.put("f", c(context));
            if (m() && MusicStateListener.b(context)) {
                jSONObject.put(f.T, 1);
            }
            if (d(context)) {
                jSONObject.put(f.S, 1);
            }
            JSONObject f = f(context);
            if (f != null && f.length() > 0) {
                jSONObject.put(h, f);
            }
            jSONObject.put("b", (double) g(context));
            if (h(context)) {
                jSONObject.put(com.miui.analytics.internal.b.e.d, 1);
            }
            jSONObject.put("v", i(context));
            jSONObject.put(l, j(context));
            String jSONObject2 = jSONObject.toString();
            byte[] a = m.a(jSONObject2);
            if (a != null) {
                encodeToString = Base64.encodeToString(a, 10);
            } else {
                encodeToString = str;
            }
            try {
                o.a(a, "originalString:" + jSONObject2 + "\ngzip before :" + jSONObject2.toCharArray().length + "  after:" + a.length);
            } catch (JSONException e2) {
                e = e2;
                Log.e(o.a(a), "getContent exception", e);
                return encodeToString;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            encodeToString = str;
            e = th;
            Log.e(o.a(a), "getContent exception", e);
            return encodeToString;
        }
        return encodeToString;
    }

    private String c(Context context) {
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.importance == 100) {
                    String[] strArr = runningAppProcessInfo.pkgList;
                    if (0 < strArr.length) {
                        String str = strArr[0];
                        o.a(a, str);
                        return str;
                    }
                    return "";
                }
            }
        } catch (Throwable th) {
            Log.e(o.a(a), "getForegroundApp exception", th);
        }
        return "";
    }

    private boolean d(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).isNetworkRoaming();
        } catch (Throwable e) {
            Log.e(o.a(a), "isRoaming exception", e);
            return false;
        }
    }

    private String e(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService(com.miui.analytics.internal.policy.f.i)).getConnectionInfo();
            jSONObject.put("s", connectionInfo.getSSID());
            jSONObject.put("b", connectionInfo.getBSSID());
        } catch (Throwable e) {
            Log.e(o.a(a), "getWifiInfo exception", e);
        }
        return jSONObject.toString();
    }

    private JSONObject f(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (VERSION.SDK_INT >= 18) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter.isEnabled()) {
                    int i = 0;
                    for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("n", bluetoothDevice.getName());
                        jSONObject2.put(f.U, bluetoothDevice.getBluetoothClass().getDeviceClass());
                        jSONObject.put(i + "", jSONObject2);
                        i++;
                    }
                }
            }
            if (jSONObject.length() > 0) {
                return jSONObject;
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "getBluetoothInfo exception", e);
        }
        return null;
    }

    private float g(Context context) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            return ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
        } catch (Throwable e) {
            Log.e(o.a(a), "getBatteryInfo exception", e);
            return 0.0f;
        }
    }

    private boolean h(Context context) {
        try {
            int intExtra = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("status", -1);
            if (intExtra == 2 || intExtra == 5) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            Log.e(o.a(a), "isCharging exception", e);
            return false;
        }
    }

    private JSONObject i(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            int streamMaxVolume = audioManager.getStreamMaxVolume(2);
            int streamVolume = audioManager.getStreamVolume(2);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(f.T, streamMaxVolume);
            jSONObject2.put(com.miui.analytics.internal.b.e.d, streamVolume);
            jSONObject.put(f.S, jSONObject2);
            streamMaxVolume = audioManager.getStreamMaxVolume(3);
            int streamVolume2 = audioManager.getStreamVolume(3);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(f.T, streamMaxVolume);
            jSONObject3.put(com.miui.analytics.internal.b.e.d, streamVolume2);
            jSONObject.put(f.T, jSONObject3);
        } catch (Throwable e) {
            Log.e(o.a(a), "getVolume exception", e);
        }
        return jSONObject;
    }

    private int j(Context context) {
        int i = 0;
        try {
            i = System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (Throwable e) {
            Log.e(o.a(a), "getScreenBrightness exception", e);
        }
        return i;
    }
}
