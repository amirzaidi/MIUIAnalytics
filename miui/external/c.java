package miui.external;

import android.util.Log;

class c implements SdkConstants {
    private static final String b = "miui.core.SdkManager";
    private static final String c = "com.miui.internal.core.SdkManager";

    c() {
    }

    public static Class<?> a() throws ClassNotFoundException {
        Class<?> cls;
        try {
            cls = Class.forName(b);
        } catch (ClassNotFoundException e) {
            try {
                cls = Class.forName(c);
                Log.w(SdkConstants.a, "using legacy sdk");
            } catch (ClassNotFoundException e2) {
                Log.e(SdkConstants.a, "no sdk found");
                throw e2;
            }
        }
        return cls;
    }
}
