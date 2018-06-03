package com.xiaomi.security.devicecredential;

import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

public class SecurityDeviceCredentialManager {
    public static final String DESCRIPTOR = "com.xiaomi.security.devicecredential.ISecurityDeviceCredentialManager.v1";
    private static final int KEY_TYPE_FINANCIAL = 0;
    private static final int KEY_TYPE_ORDINARY = 1;
    private static final long RETRY_GET_SERVICE_INTERVAL_MILLISEC = 500;
    private static final long RETRY_ON_HARDWARE_SERVICE_NOT_AVAILABLE_INTERVAL_MILLISEC = 500;
    public static final String SERVICE_NAME = "miui.sedc";
    private static final String TAG = "SecurityDeviceCredentialManager";
    public static final int TRANSACTION_forceReload = 4;
    public static final int TRANSACTION_getSecurityDeviceId = 2;
    public static final int TRANSACTION_isThisDeviceSupported = 1;
    public static final int TRANSACTION_sign = 3;
    private static IBinder sService;

    private static class OnForceReloadFinishedListener extends OnRemoteCallFinishedListener {
        private OnForceReloadFinishedListener() {
        }

        public void onGetSecurityDeviceIdFinished(String str) {
            throw new IllegalStateException("wrong callback!");
        }

        public void onSignFinished(byte[] bArr) {
            throw new IllegalStateException("wrong callback!");
        }

        protected void onForceReloadFinished() {
        }

        private void checkReloadResult() throws OperationFailedException, InterruptedException, RemoteException {
            waitForResult();
            checkResultCode();
        }
    }

    private static class OnGetSecurityDeviceIdFinishListener extends OnRemoteCallFinishedListener {
        private String mSecurityDeviceId;

        private OnGetSecurityDeviceIdFinishListener() {
        }

        public void onGetSecurityDeviceIdFinished(String str) {
            this.mSecurityDeviceId = str;
        }

        public void onSignFinished(byte[] bArr) {
            throw new IllegalStateException("wrong callback!");
        }

        public void onForceReloadFinished() {
            throw new IllegalStateException("wrong callback!");
        }

        private String getSecurityDeviceId() throws OperationFailedException, InterruptedException, RemoteException {
            waitForResult();
            checkResultCode();
            return this.mSecurityDeviceId;
        }
    }

    private static class OnSignFinishedListener extends OnRemoteCallFinishedListener {
        private byte[] mSignResult;

        private OnSignFinishedListener() {
        }

        public void onGetSecurityDeviceIdFinished(String str) {
            throw new IllegalStateException("wrong callback!");
        }

        public void onSignFinished(byte[] bArr) {
            this.mSignResult = bArr;
        }

        public void onForceReloadFinished() {
            throw new IllegalStateException("wrong callback!");
        }

        private byte[] getSignResult() throws OperationFailedException, InterruptedException, RemoteException {
            waitForResult();
            checkResultCode();
            return this.mSignResult;
        }
    }

    public static class OperationFailedException extends Exception {
        public static final int ERROR_CODE_EMPTY_DATA = -103;
        public static final int ERROR_CODE_FORCE_RELOAD_REFUSED = -108;
        public static final int ERROR_CODE_KEY_TYPE_NOT_SUPPORTED = -102;
        public static final int ERROR_CODE_NOT_SUPPORTED = -100;
        public static final int ERROR_CODE_RELOAD_FAILURE_INTERNAL = -107;
        public static final int ERROR_CODE_RELOAD_FAILURE_NETWORK = -105;
        public static final int ERROR_CODE_RELOAD_FAILURE_NO_AVAILABLE_KEY_ON_SERVER = -106;
        public static final int ERROR_CODE_SIGN_FAIL = -104;
        public static final int ERROR_CODE_TRUST_ZONE_SERVICE_NOT_AVALIABLE = -101;
        public static final int ERROR_CODE_UNKNOWN = -1;
        public final int errorCode;

        public OperationFailedException(int i) {
            this.errorCode = i;
        }

        public String toString() {
            return "OperationFailedException{errorCode=" + this.errorCode + "}";
        }
    }

    public static boolean isThisDeviceSupported() throws RemoteException, InterruptedException, OperationFailedException {
        boolean z = true;
        IBinder service = getService();
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(DESCRIPTOR);
            service.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() == 0) {
                z = false;
            }
            obtain.recycle();
            obtain2.recycle();
            return z;
        } catch (Throwable th) {
            obtain.recycle();
            obtain2.recycle();
        }
    }

    public static String getSecurityDeviceId() throws RemoteException, InterruptedException, OperationFailedException {
        IBinder service = getService();
        Object onGetSecurityDeviceIdFinishListener = new OnGetSecurityDeviceIdFinishListener();
        loop0:
        while (true) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeStrongBinder(onGetSecurityDeviceIdFinishListener);
                service.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                try {
                    break loop0;
                } catch (OperationFailedException e) {
                    if (e.errorCode != OperationFailedException.ERROR_CODE_TRUST_ZONE_SERVICE_NOT_AVALIABLE) {
                        throw e;
                    }
                    Log.e(TAG, "getSecurityDeviceId: Hardware service not ready, retry...");
                    Thread.sleep(500);
                }
            } finally {
                obtain.recycle();
                obtain2.recycle();
            }
        }
        return onGetSecurityDeviceIdFinishListener.getSecurityDeviceId();
    }

    public static byte[] sign(int i, byte[] bArr, boolean z) throws RemoteException, InterruptedException, OperationFailedException {
        IBinder service = getService();
        Object onSignFinishedListener = new OnSignFinishedListener();
        loop0:
        while (true) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeStrongBinder(onSignFinishedListener);
                obtain.writeInt(i);
                obtain.writeByteArray(bArr);
                obtain.writeInt(z ? 1 : 0);
                service.transact(3, obtain, obtain2, 0);
                obtain2.readException();
                try {
                    break loop0;
                } catch (OperationFailedException e) {
                    if (e.errorCode != OperationFailedException.ERROR_CODE_TRUST_ZONE_SERVICE_NOT_AVALIABLE) {
                        throw e;
                    }
                    Log.e(TAG, "sign: Hardware service not ready, retry...");
                    Thread.sleep(500);
                }
            } finally {
                obtain.recycle();
                obtain2.recycle();
            }
        }
        return onSignFinishedListener.getSignResult();
    }

    public static byte[] signWithDeviceCredential(byte[] bArr) throws InterruptedException, RemoteException, OperationFailedException {
        return sign(1, bArr, false);
    }

    public static byte[] signWithDeviceCredential(byte[] bArr, boolean z) throws InterruptedException, RemoteException, OperationFailedException {
        return sign(1, bArr, z);
    }

    public static byte[] signWithFinancialCredential(byte[] bArr) throws InterruptedException, RemoteException, OperationFailedException {
        return sign(0, bArr, false);
    }

    public static byte[] signWithFinancialCredential(byte[] bArr, boolean z) throws InterruptedException, RemoteException, OperationFailedException {
        return sign(0, bArr, z);
    }

    public static void forceReload() throws RemoteException, InterruptedException, OperationFailedException {
        IBinder service = getService();
        Object onForceReloadFinishedListener = new OnForceReloadFinishedListener();
        loop0:
        while (true) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(DESCRIPTOR);
                obtain.writeStrongBinder(onForceReloadFinishedListener);
                service.transact(4, obtain, obtain2, 0);
                obtain2.readException();
                try {
                    onForceReloadFinishedListener.checkReloadResult();
                    break loop0;
                } catch (OperationFailedException e) {
                    if (e.errorCode != OperationFailedException.ERROR_CODE_TRUST_ZONE_SERVICE_NOT_AVALIABLE) {
                        throw e;
                    }
                    Log.e(TAG, "forceReload: Hardware service not ready, retry...");
                    Thread.sleep(500);
                }
            } finally {
                obtain.recycle();
                obtain2.recycle();
            }
        }
    }

    private static synchronized IBinder getService() throws InterruptedException, OperationFailedException {
        IBinder iBinder;
        synchronized (SecurityDeviceCredentialManager.class) {
            if (VERSION.SDK_INT < 21) {
                throw new OperationFailedException(-100);
            }
            boolean z = false;
            if (sService != null) {
                Log.i(TAG, "getService: sService != null. ");
                z = sService.pingBinder();
            } else {
                Log.i(TAG, "getService: sService == null. ");
            }
            if (z) {
                Log.i(TAG, "getService: binder alive. ");
            } else {
                Log.w(TAG, "getService: binder not alive. ");
                while (true) {
                    sService = ServiceManager.getService(SERVICE_NAME);
                    if (sService != null) {
                        break;
                    }
                    Log.e(TAG, "getService: NULL binder, retry...");
                    Thread.sleep(500);
                }
            }
            iBinder = sService;
        }
        return iBinder;
    }
}
