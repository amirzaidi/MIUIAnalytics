package com.miui.analytics.internal.collection;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.storage.StorageManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.service.i;
import com.miui.analytics.internal.util.aa;
import com.miui.analytics.internal.util.ae;
import com.miui.analytics.internal.util.af;
import com.miui.analytics.internal.util.b;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.h;
import com.miui.analytics.internal.util.l;
import com.miui.analytics.internal.util.m;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.p;
import com.miui.analytics.internal.util.r;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.w;
import com.miui.analytics.internal.util.z;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONObject;

public class f {
    public static final String a = "UploadActiveHelper";
    private static final String b = "com.xiaomi";
    private static final int c = 1;
    private static HashMap<String, Integer> d = new HashMap();
    private static final int e = 8;
    private static final int f = 8192;
    private static final String g = "com.miui.micloud";
    private static final Uri h = Uri.parse("content://com.miui.micloud");
    private static final Uri i = Uri.withAppendedPath(h, "cloud_backup_info");
    private static final String j = "com.miui.securitycenter.provider";
    private static final Uri k = Uri.parse("content://com.miui.securitycenter.provider");
    private static final Uri l = Uri.withAppendedPath(k, "hideapp");
    private static final String m = "piew4Zei-qu0Ait{";
    private static final int n = 15;
    private static final int o = 14;

    static {
        d.put("com.android.contacts", Integer.valueOf(2));
        d.put("sms", Integer.valueOf(4));
        d.put("notes", Integer.valueOf(16));
        d.put("com.miui.gallery.cloud.provider", Integer.valueOf(32));
        d.put(com.miui.analytics.internal.policy.f.i, Integer.valueOf(64));
        d.put("call_log", Integer.valueOf(128));
        d.put("records", Integer.valueOf(256));
        d.put("antispam", Integer.valueOf(512));
        d.put("com.android.calendar", Integer.valueOf(1024));
        d.put("com.miui.browser", Integer.valueOf(2048));
        d.put("com.miui.player", Integer.valueOf(4096));
    }

    private static boolean b() {
        return n.f() ? true : n.a("is_pad", false);
    }

    public static JSONObject a(Context context, JSONObject jSONObject) {
        String f;
        try {
            f = z.f(context);
            jSONObject.put("mac", f);
            if (b()) {
                jSONObject.put("imei", f);
            } else {
                jSONObject.put("imei", z.d(context));
            }
            jSONObject.put("android_id", z.k(context));
            jSONObject.put(i.p, z.i());
            jSONObject.put("language", z.h());
            jSONObject.put(com.miui.analytics.internal.policy.f.g, String.valueOf(r.d(context)));
            jSONObject.put(u.C, z.i(context));
            jSONObject.put("real_model", z.f());
            jSONObject.put("pn", Build.PRODUCT);
            jSONObject.put("uuid", z.l(context));
            jSONObject.put(com.miui.analytics.internal.f.z, c());
            jSONObject.put("ul", b(context));
            jSONObject.put("radio", e());
            jSONObject.put("sig", c(context));
            jSONObject.put("sd", d(context));
            jSONObject.put("device_model", z.k());
            jSONObject.put("device_v", z.l());
            jSONObject.put("avn", z.m());
            jSONObject.put("cmz", z.n());
            jSONObject.put("rad", z.p());
            jSONObject.put("rams", z.m(context));
            jSONObject.put("roms", z.q());
            jSONObject.put("pla", z.r());
            jSONObject.put("isgb", z.v());
            jSONObject.put("fb", a());
            jSONObject.put("imsi", h.j(context));
            jSONObject.put(i.i, Uri.encode(z.j()));
            jSONObject.put(u.V, Uri.encode(z.c()));
            jSONObject.put(i.t, n.a() ? "1" : "0");
            jSONObject.put("bc", z.d());
            jSONObject.put("isd", n.c());
            jSONObject.put("iss", n.d());
            jSONObject.put("isab", n.b());
            jSONObject.put("isbind", String.valueOf(a(context, a(context))));
            jSONObject.put("cn", n.g());
            jSONObject.put("radio2", a(1));
            jSONObject.put("ui_ver", f());
            jSONObject.put("cc", z.o());
            jSONObject.put("pc", z.w());
            jSONObject.put("mcc", z.a());
            jSONObject.put("xmsv", n.h());
            jSONObject.put("pro_mf", z.D());
            Bundle a = b.a(context).a("com.miui.analytics");
            if (a != null) {
                if (a.containsKey(b.b) && a.getBoolean(b.b)) {
                    jSONObject.put("ddt", "timeout");
                }
                Object string = a.getString(b.a);
                if (!TextUtils.isEmpty(string)) {
                    jSONObject.put("ddt", new JSONObject(string).getString(b.c));
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "getRomJsonParams ddt e", e);
        } catch (Throwable e2) {
            Log.e(o.a(a), "getRomJsonParams e", e2);
            ae.a(context, a, "getRomJsonParams Exception: ", e2);
        }
        if (d()) {
            int e3 = e(context);
            if (e3 >= 0) {
                jSONObject.put("customized", e3);
            }
        }
        if (!n.a(context, a) && af.a()) {
            f = z.B();
            jSONObject.put("triggerId", m.a(w.a(f.getBytes())));
            jSONObject.put("curloc", p.a(context).a(f));
        }
        jSONObject.put("hi", f(context));
        jSONObject.put("hm", g(context));
        return jSONObject;
    }

    public static boolean a() {
        try {
            IBinder iBinder = (IBinder) Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", new Class[]{String.class}).invoke(null, new Object[]{"package"});
            Object invoke = Class.forName("android.content.pm.IPackageManager$Stub").getDeclaredMethod("asInterface", new Class[]{IBinder.class}).invoke(null, new Object[]{iBinder});
            boolean booleanValue = ((Boolean) invoke.getClass().getDeclaredMethod("isFirstBoot", new Class[0]).invoke(invoke, new Object[0])).booleanValue();
            o.c(a, "is first boot:" + booleanValue);
            return booleanValue;
        } catch (Throwable e) {
            Log.e(o.a(a), "isFirstBoot exception:", e);
            return false;
        }
    }

    private static int a(Context context, Account account) {
        Throwable e;
        if (account == null) {
            return 0;
        }
        int i = 1;
        int intValue;
        try {
            for (Entry entry : d.entrySet()) {
                String str = (String) entry.getKey();
                intValue = ((Integer) entry.getValue()).intValue();
                if (a(account, str)) {
                    intValue |= i;
                } else {
                    intValue = i;
                }
                i = intValue;
            }
            if (c.c(context, "miui.cloud.finddevice.AccessFindDevice")) {
                if (l.a(context)) {
                    intValue = i | e;
                }
                intValue = i;
            } else {
                if (c.c(context, "android.permission.AUTHENTICATE_ACCOUNTS") && "true".equals(((AccountManager) context.getSystemService("account")).getUserData(account, "extra_find_device_enabled"))) {
                    intValue = i | e;
                }
                intValue = i;
            }
            try {
                if (b(context, account)) {
                    return intValue | f;
                }
                return intValue;
            } catch (Exception e2) {
                e = e2;
                ae.a(context, a, "getXiaomiServiceType Exception: ", e);
                return intValue;
            }
        } catch (Throwable e3) {
            e = e3;
            intValue = i;
            ae.a(context, a, "getXiaomiServiceType Exception: ", e);
            return intValue;
        }
    }

    private static boolean b(Context context, Account account) {
        Exception e;
        Throwable th;
        Closeable query;
        try {
            int i;
            boolean z;
            query = context.getContentResolver().query(i, null, "account_name = ?", new String[]{account.name}, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        i = query.getInt(query.getColumnIndex("is_open"));
                        if (i != 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        m.a(query);
                        return z;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Log.e(o.a(a), "isMicloudBackupOpened exception:" + e.getMessage());
                        m.a(query);
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        m.a(query);
                        throw th;
                    }
                }
            }
            i = 0;
            if (i != 1) {
                z = false;
            } else {
                z = true;
            }
            m.a(query);
            return z;
        } catch (Exception e3) {
            e = e3;
            query = null;
            Log.e(o.a(a), "isMicloudBackupOpened exception:" + e.getMessage());
            m.a(query);
            return false;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            m.a(query);
            throw th;
        }
    }

    private static boolean a(Account account, String str) {
        try {
            return ((Boolean) ContentResolver.class.getDeclaredMethod("getSyncAutomatically", new Class[]{Account.class, String.class}).invoke(null, new Object[]{account, str})).booleanValue();
        } catch (Throwable th) {
            Log.e(o.a(a), "isSyncAutomatically exception:", th);
            return false;
        }
    }

    private static int c() {
        Throwable th;
        long j = 0;
        Closeable bufferedReader;
        try {
            Object readLine;
            bufferedReader = new BufferedReader(new FileReader("/proc/partitions"), 256);
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } while (!readLine.endsWith("mmcblk0"));
            if (!TextUtils.isEmpty(readLine)) {
                j = Long.valueOf(readLine.split(" +")[3]).longValue();
            }
            m.a(bufferedReader);
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            m.a(bufferedReader);
            throw th;
        }
        return (int) ((((j / 4) / 1000000) + 1) * 4);
    }

    private static Account a(Context context) {
        if (c.c(context, "android.permission.GET_ACCOUNTS") || c.c(context, "android.permission.GET_ACCOUNTS_PRIVILEGED")) {
            try {
                Account[] accountsByType = AccountManager.get(context).getAccountsByType(b);
                if (accountsByType.length > 0) {
                    return accountsByType[0];
                }
            } catch (SecurityException e) {
                o.b(a, e.toString());
            } catch (Exception e2) {
                o.b(a, e2.toString());
            }
        } else {
            o.b(a, "can't grant GET_ACCOUNTS permission");
        }
        return null;
    }

    private static boolean d() {
        try {
            return ((Boolean) Class.forName("android.content.pm.PackageHideManager").getDeclaredMethod("isValidDevice", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Throwable th) {
            return false;
        }
    }

    private static int b(Context context) {
        int i = n.c() ? 1 : 0;
        try {
            i = Secure.getInt(context.getContentResolver(), "upload_log_pref", i);
        } catch (Exception e) {
        }
        return i;
    }

    private static String e() {
        try {
            Object invoke = TelephonyManager.class.getDeclaredMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
            return (String) invoke.getClass().getDeclaredMethod("getNetworkOperator", new Class[0]).invoke(invoke, new Object[0]);
        } catch (Throwable th) {
            Log.e(o.a(a), "getSimType e", th);
            return "";
        }
    }

    private static String a(int i) {
        try {
            Class cls = Class.forName("miui.telephony.TelephonyManager");
            if (cls != null) {
                Object invoke = cls.getDeclaredMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
                return (String) cls.getDeclaredMethod("getNetworkOperatorForSlot", new Class[]{Integer.TYPE}).invoke(invoke, new Object[]{Integer.valueOf(i)});
            }
        } catch (Throwable th) {
            Log.e(o.a(a), "getSimType e", th);
        }
        return "";
    }

    private static String c(Context context) {
        try {
            for (Signature toByteArray : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
                Object a = af.a(toByteArray.toByteArray());
                if (!TextUtils.isEmpty(a)) {
                    return a.substring(0, e);
                }
            }
        } catch (Throwable th) {
        }
        return "";
    }

    private static int d(Context context) {
        try {
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String str = System.getenv("SECONDARY_STORAGE");
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            if (!a(context, str) || str.equals(absolutePath)) {
                return 2;
            }
            return 1;
        } catch (Throwable th) {
            return 0;
        }
    }

    private static boolean a(Context context, String str) {
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            if ("mounted".equals((String) storageManager.getClass().getMethod("getVolumeState", new Class[]{String.class}).invoke(storageManager, new Object[]{str}))) {
                return true;
            }
        } catch (Throwable th) {
        }
        return false;
    }

    @TargetApi(16)
    private static int e(Context context) {
        Throwable e;
        Closeable query;
        try {
            query = context.getContentResolver().query(l, null, null, null, null);
            if (query == null) {
                m.a(query);
                return -1;
            }
            try {
                if (query.moveToFirst()) {
                    Object obj;
                    int i;
                    Object obj2 = query.getInt(query.getColumnIndex("isFunctionOpen")) == 1 ? 1 : null;
                    if (query.getInt(query.getColumnIndex("isHide")) == 1) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj2 != null) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if (obj != null) {
                        i += 2;
                    }
                    m.a(query);
                    return i;
                }
                m.a(query);
                return -1;
            } catch (Exception e2) {
                e = e2;
                try {
                    o.b(a, "getH2xCMCCHidenAppShow Exception: ", e);
                    m.a(query);
                    return 0;
                } catch (Throwable th) {
                    e = th;
                    m.a(query);
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            o.b(a, "getH2xCMCCHidenAppShow Exception: ", e);
            m.a(query);
            return 0;
        } catch (Throwable th2) {
            e = th2;
            query = null;
            m.a(query);
            throw e;
        }
    }

    private static String f() {
        return aa.a("ro.miui.ui.version.name", "");
    }

    private static String f(Context context) {
        int i = 0;
        try {
            List c = h.c(context);
            if (c != null) {
                int B = g.B();
                if (B <= 0) {
                    return "";
                }
                if (B > n) {
                    B = n;
                }
                while (i < c.size()) {
                    String str = (String) c.get(i);
                    if (TextUtils.isEmpty(str) || str.length() < B) {
                        c.set(i, "");
                    } else {
                        c.set(i, b.a(str.substring(0, B), m));
                    }
                    i++;
                }
                return c.toString();
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "getEncryptedImeiList exception: ", e);
        }
        return "";
    }

    private static String g(Context context) {
        int i = o;
        try {
            String h = h.h(context);
            if (!TextUtils.isEmpty(h)) {
                int C = g.C();
                if (C <= 0) {
                    return "";
                }
                if (C <= o) {
                    i = C;
                }
                if (h.length() >= i) {
                    return b.a(h.substring(0, i), m);
                }
            }
        } catch (Throwable e) {
            Log.e(a, "getEncryptedMeid exception: ", e);
        }
        return "";
    }
}
