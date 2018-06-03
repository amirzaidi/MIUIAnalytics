package com.miui.analytics;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.miui.analytics.ICore.Stub;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.o;

public class AnalyticsService extends Service {
    private static final String TAG = "AnalyticsService";
    private Stub mBinder = new Stub() {
        public int getVersion() throws RemoteException {
            int i = 0;
            try {
                return AnalyticsService.this.getPackageManager().getPackageInfo(AnalyticsService.this.getPackageName(), 0).versionCode;
            } catch (Exception e) {
                return i;
            }
        }

        public String getVersionName() throws RemoteException {
            try {
                return AnalyticsService.this.getPackageManager().getPackageInfo(AnalyticsService.this.getPackageName(), 0).versionName;
            } catch (Exception e) {
                return "0.0.0";
            }
        }

        public void trackEvent(final String str) throws RemoteException {
            ab.a(new Runnable() {
                public void run() {
                    Analytics.trackEvent(str);
                }
            });
        }

        public void trackEvents(final String[] strArr) throws RemoteException {
            ab.a(new Runnable() {
                public void run() {
                    Analytics.trackEvents(strArr);
                }
            });
        }

        public void setDebugOn(final boolean z) throws RemoteException {
            ab.a(new Runnable() {
                public void run() {
                    Analytics.setDebugOn(z);
                }
            });
        }

        public void setDefaultPolicy(final String str, final String str2) throws RemoteException {
            ab.a(new Runnable() {
                public void run() {
                    Analytics.setDefaultPolicy(str, str2);
                }
            });
        }

        public String getClientExtra(String str, String str2) throws RemoteException {
            return Analytics.getClientExtra(str, str2);
        }

        public boolean isPolicyReady(String str, String str2) throws RemoteException {
            return Analytics.isPolicyReady(str, str2);
        }

        public void deleteAllEvents(final String str) {
            ab.a(new Runnable() {
                public void run() {
                    Analytics.deleteAllEvents(str);
                }
            });
        }
    };

    public void onCreate() {
        super.onCreate();
        o.a(TAG, "onCreate");
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
