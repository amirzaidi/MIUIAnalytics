package miui.external;

import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.ContextWrapper;
import android.content.res.Configuration;

public abstract class b extends ContextWrapper implements ComponentCallbacks2 {
    private a mApplication;

    public b() {
        super(null);
    }

    void attach(a aVar) {
        this.mApplication = aVar;
        attachBaseContext(aVar);
    }

    public a getApplication() {
        return this.mApplication;
    }

    public void onCreate() {
        this.mApplication.superOnCreate();
    }

    public void onTerminate() {
        this.mApplication.superOnTerminate();
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mApplication.superOnConfigurationChanged(configuration);
    }

    public void onLowMemory() {
        this.mApplication.superOnLowMemory();
    }

    public void onTrimMemory(int i) {
        this.mApplication.superOnTrimMemory(i);
    }

    public void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {
        this.mApplication.registerComponentCallbacks(componentCallbacks);
    }

    public void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {
        this.mApplication.unregisterComponentCallbacks(componentCallbacks);
    }

    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.mApplication.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.mApplication.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }
}
