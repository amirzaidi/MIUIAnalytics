package com.miui.analytics.internal.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class a {
    private static final String a = "AdvertisingIdHelper";
    private static volatile a b = null;
    private static final String c = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";

    interface b extends IInterface {
        String a() throws RemoteException;

        boolean a(boolean z) throws RemoteException;
    }

    private static class a implements b {
        private IBinder a;

        public boolean a(boolean r8) throws android.os.RemoteException {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:83)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:51)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:43)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
            /*
            r7 = this;
            r0 = 1;
            r1 = 0;
            r3 = android.os.Parcel.obtain();
            r4 = android.os.Parcel.obtain();
            r2 = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            r3.writeInterfaceToken(r2);	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            if (r8 == 0) goto L_0x002c;	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
        L_0x0011:
            r2 = r0;	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
        L_0x0012:
            r3.writeInt(r2);	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            r2 = r7.a;	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            r5 = 2;	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            r6 = 0;	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            r2.transact(r5, r3, r4, r6);	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            r4.readException();	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            r2 = r4.readInt();	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            if (r2 == 0) goto L_0x002e;
        L_0x0025:
            r4.recycle();
            r3.recycle();
        L_0x002b:
            return r0;
        L_0x002c:
            r2 = r1;
            goto L_0x0012;
        L_0x002e:
            r0 = r1;
            goto L_0x0025;
        L_0x0030:
            r0 = move-exception;
            r2 = "AdvertisingIdHelper";	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            r2 = com.miui.analytics.internal.util.o.a(r2);	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            r5 = "AdvertisingIdImpl isLimitAdTrackingEnabled exception";	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            android.util.Log.e(r2, r5, r0);	 Catch:{ Exception -> 0x0030, all -> 0x0044 }
            r4.recycle();
            r3.recycle();
            r0 = r1;
            goto L_0x002b;
        L_0x0044:
            r0 = move-exception;
            r4.recycle();
            r3.recycle();
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.util.a.a.a(boolean):boolean");
        }

        a(IBinder iBinder) {
            this.a = iBinder;
        }

        public IBinder asBinder() {
            return this.a;
        }

        public String a() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            String str = "";
            try {
                obtain.writeInterfaceToken(a.c);
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                str = obtain2.readString();
            } catch (Throwable e) {
                Log.e(o.a(a.a), "AdvertisingIdImpl getId exception", e);
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return str;
        }
    }

    static class c implements ServiceConnection {
        boolean a = false;
        private final BlockingQueue<IBinder> b = new LinkedBlockingQueue();

        c() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder a() throws InterruptedException {
            if (this.a) {
                throw new IllegalStateException();
            }
            this.a = true;
            return (IBinder) this.b.take();
        }
    }

    private a() {
    }

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    private static boolean b(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            return true;
        } catch (Throwable e) {
            Log.e(o.a(a), "isGooglePlayAvailable exception", e);
            return false;
        }
    }

    private static c c(Context context) {
        if (!b(context)) {
            return null;
        }
        try {
            ServiceConnection cVar = new c();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            if (context.bindService(intent, cVar, 1)) {
                return cVar;
            }
            return null;
        } catch (Throwable e) {
            Log.e(o.a(a), "connection exception", e);
            return null;
        }
    }

    private static IInterface a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(c);
        return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new a(iBinder) : queryLocalInterface;
    }

    public String a(Context context) {
        ServiceConnection c = c(context);
        if (c == null) {
            o.c(a, "can not connect to google play service");
            return "";
        }
        try {
            String a = ((b) a(c.a())).a();
            try {
                context.unbindService(c);
                return a;
            } catch (Throwable e) {
                Log.e(o.a(a), "getGAID exception", e);
                return a;
            }
        } catch (Throwable e2) {
            Log.e(o.a(a), "getGAID exception", e2);
            try {
                context.unbindService(c);
            } catch (Throwable e22) {
                Log.e(o.a(a), "getGAID exception", e22);
            }
            return "";
        } catch (Throwable e222) {
            try {
                context.unbindService(c);
            } catch (Throwable e3) {
                Log.e(o.a(a), "getGAID exception", e3);
            }
            throw e222;
        }
    }
}
