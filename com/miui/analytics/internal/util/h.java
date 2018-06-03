package com.miui.analytics.internal.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class h {
    static final int a = 15;
    static final int b = 14;
    static final int c = 15;
    static final int d = 6;
    public static String e = "1";
    public static String f = "2";
    public static String g = "3";
    public static String h = "-1";
    private static final String i = "DeviceIdHelper";
    private static Method j;
    private static Method k;
    private static Method l;
    private static Method m;
    private static Object n;
    private static String o = null;
    private static String p = null;
    private static Method q;

    static {
        try {
            j = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class});
        } catch (Throwable th) {
            Log.e(o.a(i), "clsSystemProperties e", th);
        }
        try {
            Class cls = Class.forName("miui.telephony.TelephonyManagerEx");
            n = cls.getMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
            k = cls.getMethod("getImeiList", new Class[0]);
            l = cls.getMethod("getMeidList", new Class[0]);
            m = cls.getMethod("getSubscriberIdForSlot", new Class[]{Integer.TYPE});
        } catch (Throwable th2) {
            Log.e(o.a(i), "clsTelephonyManagerEx e", th2);
        }
        try {
            if (VERSION.SDK_INT >= 21) {
                q = Class.forName("android.telephony.TelephonyManager").getMethod("getImei", new Class[]{Integer.TYPE});
            }
        } catch (Throwable th22) {
            Log.e(o.a(i), "clsTelephonyManager e", th22);
        }
    }

    public static String[] a(Context context) {
        try {
            String a;
            String a2;
            String a3 = a("ro.ril.miui.imei0");
            if (a()) {
                a = a("ro.ril.miui.imei1");
                String a4 = a("ro.ril.miui.imei");
                if (!TextUtils.isEmpty(a4)) {
                    String[] split = a4.split(",");
                    if (split.length == 2) {
                        if (TextUtils.isEmpty(a3)) {
                            a3 = split[0];
                        }
                        if (TextUtils.isEmpty(a)) {
                            a = split[1];
                        }
                    }
                }
                if (TextUtils.isEmpty(a3)) {
                    a3 = a("ro.ril.oem.imei1");
                }
                if (TextUtils.isEmpty(a3)) {
                    a3 = a("persist.radio.imei1");
                }
                if (TextUtils.isEmpty(a)) {
                    a = a("ro.ril.oem.imei2");
                }
                if (TextUtils.isEmpty(a)) {
                    a = a("persist.radio.imei2");
                }
                if (VERSION.SDK_INT < 21) {
                    a2 = a("ro.product.mod_device");
                    if (a2 == null || !a2.contains("_global")) {
                        if (b(a3)) {
                            o = a3;
                            return new String[]{a3, e};
                        } else if (b(a)) {
                            o = a;
                            return new String[]{a, e};
                        }
                    }
                    if (b(a4)) {
                        o = a4;
                        return new String[]{a4, e};
                    }
                }
                if (b(a3) && b(a)) {
                    if (a3.compareTo(a) <= 0) {
                        a4 = a3;
                    } else {
                        a4 = a;
                    }
                    o = a4;
                    if (o == a3) {
                        a2 = a;
                    } else {
                        a2 = a3;
                    }
                    p = a2;
                    return new String[]{a4, e};
                }
            }
            CharSequence a5;
            if (TextUtils.isEmpty(a3)) {
                a5 = a("ro.ril.miui.imei");
                if (a5 != null && a5.contains(",")) {
                    a5 = a5.split(",")[0];
                }
            } else {
                Object a52 = a3;
            }
            if (TextUtils.isEmpty(a52)) {
                a52 = a("ro.ril.oem.imei");
            }
            if (TextUtils.isEmpty(a52)) {
                a = a("persist.radio.imei");
            } else {
                CharSequence charSequence = a52;
            }
            if (b(a)) {
                o = a;
                return new String[]{a, e};
            }
            List c = c(context);
            if (c == null || c.size() <= 0) {
                a = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                a2 = h;
                if (a == null || !a.matches("^0*$")) {
                    a3 = a;
                } else {
                    a3 = null;
                }
                if (c(a3)) {
                    a = g;
                } else {
                    a = a2;
                }
                return new String[]{a3, a};
            }
            return new String[]{(String) c.get(0), e};
        } catch (Throwable th) {
            Log.e(o.a(i), "getDeviceId e", th);
            return null;
        }
    }

    public static String b(Context context) {
        try {
            String[] a = a(context);
            if (a != null && a.length > 1) {
                a[0] = af.a(a[0]);
                return Arrays.toString(a);
            }
        } catch (Throwable th) {
            Log.e(o.a(i), "getDeviceIdMd5 e", th);
        }
        return "";
    }

    public static List<String> c(Context context) {
        if (!(k == null || b())) {
            try {
                List list = (List) k.invoke(n, new Object[0]);
                if (!(list == null || list.size() <= 0 || a(list))) {
                    Collections.sort(list);
                    return list;
                }
            } catch (Throwable th) {
                Log.e(o.a(i), "getImeiList invoke e", th);
            }
        }
        String str;
        if (VERSION.SDK_INT >= 21) {
            if (q != null) {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    List<String> arrayList = new ArrayList();
                    String str2 = (String) q.invoke(telephonyManager, new Object[]{Integer.valueOf(0)});
                    if (b(str2)) {
                        if (a()) {
                            str = (String) q.invoke(telephonyManager, new Object[]{Integer.valueOf(1)});
                            if (b(str)) {
                                if (str2.compareTo(str) > 0) {
                                    arrayList.add(str);
                                    arrayList.add(str2);
                                } else {
                                    arrayList.add(str2);
                                    arrayList.add(str);
                                }
                                return arrayList;
                            }
                        }
                        arrayList.add(str2);
                        return arrayList;
                    }
                } catch (Throwable th2) {
                    Log.e(o.a(i), "getImeiList Build.VERSION.SDK_INT >= 21 e", th2);
                }
                List<String> arrayList2;
                if (!a() && o != null) {
                    arrayList2 = new ArrayList();
                    arrayList2.add(o);
                    return arrayList2;
                } else if (!(o == null || p == null)) {
                    arrayList2 = new ArrayList();
                    arrayList2.add(o);
                    arrayList2.add(p);
                    return arrayList2;
                }
            }
        } else if (VERSION.SDK_INT < 21) {
            try {
                List<String> arrayList3 = new ArrayList();
                Class cls = Class.forName("android.telephony.TelephonyManager");
                if (a()) {
                    String deviceId = ((TelephonyManager) cls.getMethod("getDefault", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(0)})).getDeviceId();
                    str = ((TelephonyManager) cls.getMethod("getDefault", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(1)})).getDeviceId();
                    String a = a("ro.product.mod_device");
                    if (a == null || !a.contains("_global")) {
                        if (b(deviceId)) {
                            arrayList3.add(deviceId);
                            arrayList3.add(deviceId);
                        } else if (b(str)) {
                            arrayList3.add(str);
                            arrayList3.add(str);
                        } else if (o != null) {
                            arrayList3.add(o);
                            arrayList3.add(o);
                        }
                    } else if (b(deviceId) && b(str)) {
                        arrayList3.add(deviceId);
                        arrayList3.add(str);
                    } else if (!(o == null || p == null)) {
                        arrayList3.add(o);
                        arrayList3.add(p);
                    }
                    if (arrayList3.size() > 0) {
                        return arrayList3;
                    }
                }
                str = ((TelephonyManager) cls.getMethod("getDefault", new Class[0]).invoke(null, new Object[0])).getDeviceId();
                if (b(str)) {
                    arrayList3.add(str);
                    return arrayList3;
                } else if (o != null) {
                    arrayList3.add(o);
                    return arrayList3;
                }
            } catch (Throwable th22) {
                Log.e(o.a(i), "getImeiList Build.VERSION.SDK_INT < 21 e", th22);
            }
        }
        return null;
    }

    public static String d(Context context) {
        try {
            List c = c(context);
            if (c != null) {
                Collections.sort(c, new Comparator<String>() {
                    public /* synthetic */ int compare(Object obj, Object obj2) {
                        return a((String) obj, (String) obj2);
                    }

                    public int a(String str, String str2) {
                        return str.compareToIgnoreCase(str2);
                    }
                });
                for (int i = 0; i < c.size(); i++) {
                    c.set(i, af.a((String) c.get(i)));
                }
                return c.toString();
            }
        } catch (Throwable th) {
            Log.e(o.a(i), "getImeiListMd5 e", th);
        }
        return "";
    }

    public static List<String> e(Context context) {
        String deviceId;
        if (VERSION.SDK_INT < 21 && a()) {
            try {
                List<String> arrayList = new ArrayList();
                Class cls = Class.forName("android.telephony.TelephonyManager");
                deviceId = ((TelephonyManager) cls.getMethod("getDefault", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(0)})).getDeviceId();
                if (c(deviceId)) {
                    arrayList.add(deviceId);
                    return arrayList;
                }
                deviceId = ((TelephonyManager) cls.getMethod("getDefault", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(1)})).getDeviceId();
                if (c(deviceId)) {
                    arrayList.add(deviceId);
                    return arrayList;
                }
            } catch (Throwable th) {
                Log.e(o.a(i), "getMeidList Build.VERSION.SDK_INT < 21 e", th);
            }
        }
        if (l != null) {
            try {
                List list = (List) l.invoke(n, new Object[0]);
                if (!(list == null || list.size() <= 0 || b(list))) {
                    Collections.sort(list);
                    return list;
                }
            } catch (Throwable th2) {
                Log.e(o.a(i), "getMeidList sGetMeidList.invoke e", th2);
            }
        }
        try {
            String a;
            List<String> arrayList2;
            deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (!c(deviceId)) {
                deviceId = a("persist.radio.meid");
                if (!c(deviceId)) {
                    a = a("ro.ril.oem.meid");
                    if (c(a)) {
                        arrayList2 = new ArrayList();
                        arrayList2.add(a);
                        return arrayList2;
                    }
                    return null;
                }
            }
            a = deviceId;
            if (c(a)) {
                arrayList2 = new ArrayList();
                arrayList2.add(a);
                return arrayList2;
            }
        } catch (Throwable th22) {
            Log.e(o.a(i), "getMeidList e", th22);
        }
        return null;
    }

    public static String f(Context context) {
        try {
            List e = e(context);
            if (e != null) {
                Collections.sort(e, new Comparator<String>() {
                    public /* synthetic */ int compare(Object obj, Object obj2) {
                        return a((String) obj, (String) obj2);
                    }

                    public int a(String str, String str2) {
                        return str.compareToIgnoreCase(str2);
                    }
                });
                for (int i = 0; i < e.size(); i++) {
                    e.set(i, af.a((String) e.get(i)));
                }
                return e.toString();
            }
        } catch (Throwable th) {
            Log.e(o.a(i), "getMeidListMd5 e", th);
        }
        return "";
    }

    private static boolean a() {
        if ("dsds".equals(a("persist.radio.multisim.config"))) {
            return true;
        }
        String str = Build.DEVICE;
        if ("lcsh92_wet_jb9".equals(str) || "lcsh92_wet_tdd".equals(str) || "HM2013022".equals(str) || "HM2013023".equals(str) || "armani".equals(str) || "HM2014011".equals(str) || "HM2014012".equals(str)) {
            return true;
        }
        return false;
    }

    private static String a(String str) {
        String str2 = null;
        try {
            if (j != null) {
                str2 = String.valueOf(j.invoke(null, new Object[]{str}));
            }
        } catch (Throwable th) {
            Log.e(o.a(i), "getProp e", th);
        }
        return str2;
    }

    private static boolean b() {
        if (VERSION.SDK_INT >= 21) {
            return false;
        }
        String str = Build.DEVICE;
        String a = a("persist.radio.modem");
        if ("HM2014812".equals(str) || "HM2014821".equals(str) || (("gucci".equals(str) && "ct".equals(a("persist.sys.modem"))) || "CDMA".equals(a) || "HM1AC".equals(a) || "LTE-X5-ALL".equals(a) || "LTE-CT".equals(a) || "MI 3C".equals(Build.MODEL))) {
            return true;
        }
        return false;
    }

    private static boolean a(List<String> list) {
        for (String b : list) {
            if (!b(b)) {
                return true;
            }
        }
        return false;
    }

    private static boolean b(List<String> list) {
        for (String c : list) {
            if (!c(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean b(String str) {
        return (str == null || str.length() != 15 || str.matches("^0*$")) ? false : true;
    }

    private static boolean c(String str) {
        return (str == null || str.length() != b || str.matches("^0*$")) ? false : true;
    }

    private static boolean d(String str) {
        return str != null && str.length() >= d && str.length() <= 15 && !str.matches("^0*$");
    }

    public static String g(Context context) {
        try {
            List c = c(context);
            if (c != null && c.size() > 0) {
                String str = (String) c.get(0);
                if (b(str)) {
                    return str;
                }
            }
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Throwable e) {
            Log.e(o.a(i), "getImei exception", e);
            return "";
        }
    }

    public static String h(Context context) {
        try {
            List<String> e = e(context);
            if (e != null) {
                for (String str : e) {
                    if (!TextUtils.isEmpty(str)) {
                        return str;
                    }
                }
            }
        } catch (Throwable e2) {
            Log.e(o.a(i), "getMeid exception", e2);
        }
        return "";
    }

    public static List<String> i(Context context) {
        String str;
        List<String> arrayList = new ArrayList();
        try {
            if (a()) {
                Object subscriberId;
                Object subscriberId2;
                Class cls = Class.forName("android.telephony.TelephonyManager");
                if (VERSION.SDK_INT < 21) {
                    subscriberId = ((TelephonyManager) cls.getMethod("getDefault", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(0)})).getSubscriberId();
                    subscriberId2 = ((TelephonyManager) cls.getMethod("getDefault", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(1)})).getSubscriberId();
                } else {
                    String str2;
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
                    Class cls2 = Class.forName("android.telephony.SubscriptionManager");
                    if (VERSION.SDK_INT == 21) {
                        long[] jArr = (long[]) cls2.getMethod("getSubId", new Class[]{Integer.TYPE}).invoke(subscriptionManager, new Object[]{Integer.valueOf(0)});
                        str2 = (String) cls.getMethod("getSubscriberId", new Class[]{Long.TYPE}).invoke(telephonyManager, new Object[]{Long.valueOf(jArr[0])});
                    } else {
                        int[] iArr = (int[]) cls2.getMethod("getSubId", new Class[]{Integer.TYPE}).invoke(subscriptionManager, new Object[]{Integer.valueOf(0)});
                        str2 = (String) cls.getMethod("getSubscriberId", new Class[]{Integer.TYPE}).invoke(telephonyManager, new Object[]{Integer.valueOf(iArr[0])});
                    }
                    String subscriberId3;
                    if (!d(str2)) {
                        str = (String) m.invoke(n, new Object[]{Integer.valueOf(0)});
                        String str3 = (String) m.invoke(n, new Object[]{Integer.valueOf(1)});
                        subscriberId3 = str;
                        str = str3;
                    } else if (VERSION.SDK_INT == 21) {
                        long[] jArr2 = (long[]) cls2.getMethod("getSubId", new Class[]{Integer.TYPE}).invoke(subscriptionManager, new Object[]{Integer.valueOf(1)});
                        str = (String) cls.getMethod("getSubscriberId", new Class[]{Long.TYPE}).invoke(telephonyManager, new Object[]{Long.valueOf(jArr2[0])});
                        subscriberId3 = str2;
                    } else {
                        int[] iArr2 = (int[]) cls2.getMethod("getSubId", new Class[]{Integer.TYPE}).invoke(subscriptionManager, new Object[]{Integer.valueOf(1)});
                        str = (String) cls.getMethod("getSubscriberId", new Class[]{Integer.TYPE}).invoke(telephonyManager, new Object[]{Integer.valueOf(iArr2[0])});
                        subscriberId3 = str2;
                    }
                }
                if (!d((String) subscriberId3)) {
                    subscriberId3 = "";
                }
                arrayList.add(subscriberId3);
                if (!d((String) subscriberId2)) {
                    subscriberId2 = "";
                }
                arrayList.add(subscriberId2);
                return arrayList;
            }
        } catch (Throwable th) {
            Log.e(o.a(i), "getImsiList for multi sim failed!", th);
        }
        str = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        if (d(str)) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public static String j(Context context) {
        try {
            List i = i(context);
            if (i != null) {
                for (int i2 = 0; i2 < i.size(); i2++) {
                    i.set(i2, af.a((String) i.get(i2)));
                }
                return i.toString();
            }
        } catch (Throwable th) {
            Log.e(o.a(i), "getImeiListMd5 failed!", th);
        }
        return "";
    }
}
