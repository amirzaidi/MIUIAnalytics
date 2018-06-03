package com.xiaomi.security.devicecredential;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaomi.security.devicecredential.SecurityDeviceCredentialManager.OperationFailedException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class OnRemoteCallFinishedListener extends Binder implements IOnRemoteCallFinishedListener {
    private static final long REMOTE_CALL_TIMEOUT_DELAY = 300000;
    private CountDownLatch mCountDownLatch = new CountDownLatch(1);
    private int mResultCode;

    protected abstract void onForceReloadFinished();

    protected abstract void onGetSecurityDeviceIdFinished(String str);

    protected abstract void onSignFinished(byte[] bArr);

    protected void waitForResult() throws InterruptedException, RemoteException {
        if (!this.mCountDownLatch.await(300000, TimeUnit.MILLISECONDS)) {
            throw new RemoteException("remotecall timeout.");
        }
    }

    protected void checkResultCode() throws OperationFailedException {
        if (this.mResultCode != 0) {
            throw new OperationFailedException(this.mResultCode);
        }
    }

    public static IOnRemoteCallFinishedListener asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        return new OnRemoteCallFinishedListenerProxy(iBinder);
    }

    public IBinder asBinder() {
        return this;
    }

    protected boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                parcel.enforceInterface(IOnRemoteCallFinishedListener.DESCRIPTOR);
                onGetSecurityDeviceIdFinished(parcel.readInt(), parcel.readString());
                return true;
            case 2:
                parcel.enforceInterface(IOnRemoteCallFinishedListener.DESCRIPTOR);
                onSignFinished(parcel.readInt(), parcel.createByteArray());
                return true;
            case 3:
                parcel.enforceInterface(IOnRemoteCallFinishedListener.DESCRIPTOR);
                onForceReloadFinished(parcel.readInt());
                return true;
            case 1598968902:
                parcel2.writeString(IOnRemoteCallFinishedListener.DESCRIPTOR);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }

    public final void onGetSecurityDeviceIdFinished(int i, String str) throws RemoteException {
        this.mResultCode = i;
        onGetSecurityDeviceIdFinished(str);
        notifyResultArrive();
    }

    public final void onSignFinished(int i, byte[] bArr) throws RemoteException {
        this.mResultCode = i;
        onSignFinished(bArr);
        notifyResultArrive();
    }

    public final void onForceReloadFinished(int i) throws RemoteException {
        this.mResultCode = i;
        onForceReloadFinished();
        notifyResultArrive();
    }

    private void notifyResultArrive() {
        this.mCountDownLatch.countDown();
    }
}
