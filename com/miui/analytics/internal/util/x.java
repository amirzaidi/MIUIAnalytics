package com.miui.analytics.internal.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class x {
    public static final String a = "sys.memory.threshold.low";
    private static final String b = "StorageHelper";
    private static final int c = 0;
    private static final int d = 2;
    private static List<String> e = new ArrayList();

    public static class a {
        public long a;
        public long b;
    }

    public static class b {
        private String a;
        private String b;
        private String c;
        private boolean d;
        private String e;
        private boolean f;
        private boolean g;

        public b(String str, String str2, String str3) {
            this.a = str;
            this.c = str2;
            this.b = str3;
        }

        public boolean a() {
            return "mounted".equals(this.b);
        }

        public String b() {
            return this.a == null ? "" : this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public String c() {
            return this.b == null ? "" : this.b;
        }

        public String d() {
            return this.c == null ? "" : this.c;
        }

        public void b(String str) {
            this.c = str;
        }

        public void a(boolean z) {
            this.d = z;
        }

        public boolean e() {
            return this.d;
        }

        public String f() {
            return this.e;
        }

        public void c(String str) {
            this.e = str;
        }

        public void b(boolean z) {
            this.f = z;
        }

        public boolean g() {
            return this.f;
        }

        public void c(boolean z) {
            this.g = z;
        }

        public boolean h() {
            return this.g;
        }
    }

    public static String[] a(Context context) {
        String[] strArr = new String[2];
        try {
            a b = b(context);
            if (b != null) {
                strArr[0] = z.a(b.a, false);
                strArr[1] = z.a(b.b, false);
                o.a(b, "total:" + strArr[0] + ", free:" + strArr[1]);
            }
        } catch (Throwable e) {
            Log.e(o.a(b), "getMemoryInfo exception", e);
        }
        return strArr;
    }

    public static a a(b bVar) {
        if (bVar == null || bVar.b() == null) {
            return new a();
        }
        String b = bVar.b();
        if (bVar.a()) {
            return a(b);
        }
        return new a();
    }

    public static a a(String str) {
        a aVar = new a();
        if (TextUtils.isEmpty(str)) {
            return new a();
        }
        try {
            StatFs statFs = new StatFs(str);
            long blockSize = (long) statFs.getBlockSize();
            long availableBlocks = (long) statFs.getAvailableBlocks();
            aVar.a = ((long) statFs.getBlockCount()) * blockSize;
            aVar.b = availableBlocks * blockSize;
            if (new StatFs(Environment.getDataDirectory().getPath()).getBlockCount() != statFs.getBlockCount()) {
                return aVar;
            }
            aVar.b -= aa.a(a, Long.valueOf(0));
            return aVar;
        } catch (IllegalArgumentException e) {
            return new a();
        }
    }

    public static a b(Context context) {
        a aVar = new a();
        List c = c(context);
        for (int i = 0; i < c.size(); i++) {
            a a = a((b) c.get(i));
            if (a != null) {
                aVar.b += a.b;
                aVar.a += a.a;
            }
        }
        if (!a()) {
            a = a(Environment.getDataDirectory().getPath());
            aVar.a += a.a;
            aVar.b = a.b + aVar.b;
        }
        return aVar;
    }

    static {
        e.add("mione");
        e.add("mione_plus");
        e.add("taurus");
        e.add("taurus_td");
        e.add("pisces");
        e.add("HM2013022");
        e.add("HM2013023");
    }

    public static boolean a() {
        if (!e.contains(Build.DEVICE) || "mixed".equals(aa.a("ro.boot.sdcard.type", ""))) {
            return true;
        }
        return false;
    }

    public static List<b> c(Context context) {
        return VERSION.SDK_INT >= 23 ? e(context) : d(context);
    }

    public static List<b> d(Context context) {
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        List arrayList = new ArrayList();
        try {
            Method method = StorageManager.class.getMethod("getVolumeList", new Class[0]);
            method.setAccessible(true);
            Object[] objArr = (Object[]) method.invoke(storageManager, new Object[0]);
            if (objArr != null) {
                for (Object obj : objArr) {
                    String str = (String) obj.getClass().getMethod("getPath", new Class[0]).invoke(obj, new Object[0]);
                    File file = new File(str);
                    if (file.exists() && file.isDirectory() && file.canWrite()) {
                        String str2 = (String) StorageManager.class.getMethod("getVolumeState", new Class[]{String.class}).invoke(storageManager, new Object[]{str});
                        String str3 = (String) obj.getClass().getMethod("getDescription", new Class[]{Context.class}).invoke(obj, new Object[]{context});
                        boolean booleanValue = ((Boolean) obj.getClass().getMethod("isPrimary", new Class[0]).invoke(obj, new Object[0])).booleanValue();
                        boolean booleanValue2 = ((Boolean) obj.getClass().getMethod("isRemovable", new Class[0]).invoke(obj, new Object[0])).booleanValue();
                        if (!booleanValue || str.equalsIgnoreCase(Environment.getExternalStorageDirectory().getPath())) {
                            String str4 = (String) obj.getClass().getMethod("getUuid", new Class[0]).invoke(obj, new Object[0]);
                            b bVar = new b(str, str3, str2);
                            bVar.a(booleanValue);
                            bVar.c(booleanValue2);
                            bVar.c(str4);
                            bVar.b(true);
                            arrayList.add(bVar);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayList.trimToSize();
        return arrayList;
    }

    public static List<b> e(Context context) {
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        List arrayList = new ArrayList();
        try {
            Method method = StorageManager.class.getMethod("getVolumes", new Class[0]);
            method.setAccessible(true);
            List list = (List) method.invoke(storageManager, new Object[0]);
            if (list != null) {
                for (Object next : list) {
                    int intValue = ((Integer) next.getClass().getMethod("getType", new Class[0]).invoke(next, new Object[0])).intValue();
                    File file = (File) next.getClass().getMethod("getPath", new Class[0]).invoke(next, new Object[0]);
                    if (file != null) {
                        File file2 = new File(file.getPath());
                        if ((intValue == 0 || intValue == 2) && file2.exists() && file2.isDirectory()) {
                            int intValue2 = ((Integer) next.getClass().getMethod("getState", new Class[0]).invoke(next, new Object[0])).intValue();
                            Class cls = Class.forName("android.os.storage.VolumeInfo");
                            String str = (String) next.getClass().getMethod("getFsUuid", new Class[0]).invoke(next, new Object[0]);
                            b bVar = new b(file2.getPath(), (String) next.getClass().getMethod("getDescription", new Class[0]).invoke(next, new Object[0]), (String) cls.getMethod("getEnvironmentForState", new Class[]{Integer.TYPE}).invoke(cls, new Object[]{Integer.valueOf(intValue2)}));
                            bVar.a(intValue == 2);
                            bVar.c(str);
                            if (bVar.e()) {
                                bVar.b(true);
                            } else {
                                bVar.b(((Boolean) next.getClass().getMethod("isVisible", new Class[0]).invoke(next, new Object[0])).booleanValue());
                            }
                            next.getClass().getMethod("getDisk", new Class[0]).invoke(next, new Object[0]);
                            if (bVar.e() && bVar.b() != null && "mounted".equals(Environment.getExternalStorageState())) {
                                String path = Environment.getExternalStorageDirectory().getPath();
                                if (!bVar.b().equalsIgnoreCase(path)) {
                                    bVar.a(path);
                                }
                            }
                            arrayList.add(bVar);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayList.trimToSize();
        return arrayList;
    }
}
