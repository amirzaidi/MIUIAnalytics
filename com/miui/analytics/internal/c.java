package com.miui.analytics.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import com.miui.analytics.internal.util.ae;
import com.miui.analytics.internal.util.o;
import java.io.File;

public class c {
    private static final String a = "AppInstaller";

    public void a(Context context, String str) {
        try {
            b(context, str);
            context.getPackageManager().getClass().getMethod("installPackage", new Class[]{Uri.class, Class.forName("android.content.pm.IPackageInstallObserver"), Integer.TYPE, String.class}).invoke(context.getPackageManager(), new Object[]{Uri.fromFile(new File(str)), null, context.getPackageManager().getClass().getField("INSTALL_REPLACE_EXISTING").get(null), null});
        } catch (Throwable e) {
            ae.a(context, a, "install apk.", e);
        }
    }

    private void b(Context context, String str) {
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 0);
            String valueOf = String.valueOf(packageArchiveInfo.versionCode);
            String str2 = packageArchiveInfo.versionName;
            String str3 = packageArchiveInfo.packageName;
            o.a(a, String.format("start to install apk: %s, package:%s, new version code:%s, new version name:%s ", new Object[]{str, str3, valueOf, str2}));
            ae.b(context, str3, valueOf);
        } catch (Throwable e) {
            ae.a(context, a, "trackUpgrade exception:", e);
        }
    }
}
