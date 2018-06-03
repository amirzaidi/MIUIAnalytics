package miui.external;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import com.miui.analytics.internal.service.i;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import miui.external.SdkConstants.SdkError;

public class a extends Application implements SdkConstants {
    private static final String PACKAGE_NAME = "com.miui.core";
    private b mApplicationDelegate;
    private boolean mInitialized;
    private boolean mStarted;

    public a() {
        if (loadSdk() && initializeSdk()) {
            this.mInitialized = true;
        }
    }

    private boolean loadSdk() {
        try {
            if (f.a() || g.a(f.a(null, PACKAGE_NAME, i.n), null, f.a(null, PACKAGE_NAME), a.class.getClassLoader())) {
                return true;
            }
            e.a(SdkError.NO_SDK);
            return false;
        } catch (Throwable th) {
            handleGenericError(th);
            return false;
        }
    }

    private boolean initializeSdk() {
        try {
            HashMap hashMap = new HashMap();
            int intValue = ((Integer) c.a().getMethod("initialize", new Class[]{Application.class, Map.class}).invoke(null, new Object[]{this, hashMap})).intValue();
            if (intValue == 0) {
                return true;
            }
            handleUnknownError("initialize", intValue);
            return false;
        } catch (Throwable th) {
            handleGenericError(th);
            return false;
        }
    }

    private boolean startSdk() {
        try {
            HashMap hashMap = new HashMap();
            int intValue = ((Integer) c.a().getMethod("start", new Class[]{Map.class}).invoke(null, new Object[]{hashMap})).intValue();
            if (intValue == 1) {
                e.a(SdkError.LOW_SDK_VERSION);
                return false;
            } else if (intValue == 0) {
                return true;
            } else {
                handleUnknownError("start", intValue);
                return false;
            }
        } catch (Throwable th) {
            handleGenericError(th);
            return false;
        }
    }

    private void handleGenericError(Throwable th) {
        while (th != null && th.getCause() != null) {
            if (!(th instanceof InvocationTargetException)) {
                if (!(th instanceof ExceptionInInitializerError)) {
                    break;
                }
                th = th.getCause();
            } else {
                th = th.getCause();
            }
        }
        Log.e(SdkConstants.a, "MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support.", th);
        e.a(SdkError.GENERIC);
    }

    private void handleUnknownError(String str, int i) {
        Log.e(SdkConstants.a, "MIUI SDK encounter errors, please contact miuisdk@xiaomi.com for support. phase: " + str + " code: " + i);
        e.a(SdkError.GENERIC);
    }

    public b onCreateApplicationDelegate() {
        return null;
    }

    public final b getApplicationDelegate() {
        return this.mApplicationDelegate;
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        if (this.mInitialized && startSdk()) {
            this.mApplicationDelegate = onCreateApplicationDelegate();
            if (this.mApplicationDelegate != null) {
                this.mApplicationDelegate.attach(this);
            }
            this.mStarted = true;
        }
    }

    public final void onCreate() {
        if (!this.mStarted) {
            return;
        }
        if (this.mApplicationDelegate != null) {
            this.mApplicationDelegate.onCreate();
        } else {
            superOnCreate();
        }
    }

    final void superOnCreate() {
        super.onCreate();
    }

    public final void onTerminate() {
        if (this.mApplicationDelegate != null) {
            this.mApplicationDelegate.onTerminate();
        } else {
            superOnTerminate();
        }
    }

    final void superOnTerminate() {
        super.onTerminate();
    }

    public final void onLowMemory() {
        if (this.mApplicationDelegate != null) {
            this.mApplicationDelegate.onLowMemory();
        } else {
            superOnLowMemory();
        }
    }

    final void superOnLowMemory() {
        super.onLowMemory();
    }

    public final void onTrimMemory(int i) {
        if (this.mApplicationDelegate != null) {
            this.mApplicationDelegate.onTrimMemory(i);
        } else {
            superOnTrimMemory(i);
        }
    }

    final void superOnTrimMemory(int i) {
        super.onTrimMemory(i);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        if (this.mApplicationDelegate != null) {
            this.mApplicationDelegate.onConfigurationChanged(configuration);
        } else {
            superOnConfigurationChanged(configuration);
        }
    }

    final void superOnConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
}
