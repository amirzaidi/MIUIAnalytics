package miui.external;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;

public class f {
    static final String a = "miui";
    private static final String b = "miuisdk";
    private static final String c = "ro.miui.ui.version.code";

    private f() {
    }

    public static boolean a() {
        return !TextUtils.isEmpty(b(c, ""));
    }

    static String a(Context context, String str, String str2) {
        if (context == null) {
            return a(str, str2);
        }
        PackageInfo b = b(context, str);
        if (b != null) {
            return b.applicationInfo.publicSourceDir;
        }
        return null;
    }

    private static String a(String str, String str2) {
        String a = a(str);
        if (a == null) {
            return b(str2);
        }
        return a;
    }

    private static String a(String str) {
        return a(new String[]{"/data/app/" + str + "-1.apk", "/data/app/" + str + "-2.apk", "/data/app/" + str + "-1/base.apk", "/data/app/" + str + "-2/base.apk"});
    }

    private static String b(String str) {
        return a(new String[]{"/system/app/" + str + ".apk", "/system/priv-app/" + str + ".apk", "/system/app/" + str + "/" + str + ".apk", "/system/priv-app/" + str + "/" + str + ".apk"});
    }

    private static String a(String[] strArr) {
        for (String str : strArr) {
            if (new File(str).exists()) {
                return str;
            }
        }
        return null;
    }

    static String a(Context context, String str) {
        if (context == null) {
            context = b();
        }
        if (context == null) {
            return c(str);
        }
        PackageInfo b = b(context, str);
        if (b != null) {
            return b.applicationInfo.nativeLibraryDir;
        }
        return null;
    }

    private static String c(String str) {
        return "/data/data/" + str + "/lib/";
    }

    private static PackageInfo b(Context context, String str) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 128);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    private static Context b() {
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            return (Context) cls.getDeclaredMethod("getSystemContext", new Class[0]).invoke(cls.getDeclaredMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
        } catch (Throwable e) {
            Log.e("miuisdk", "getSystemContext error", e);
            return null;
        }
    }

    private static String b(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
        } catch (Throwable e) {
            Log.e("miuisdk", "getSystemProperty error", e);
            return str2;
        }
    }
}
