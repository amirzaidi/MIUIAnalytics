package com.xiaomi.security.devicecredential;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class OnRemoteCallFinishedListenerProxy implements IOnRemoteCallFinishedListener {
    private final IBinder mRemote;

    public OnRemoteCallFinishedListenerProxy(IBinder iBinder) {
        this.mRemote = iBinder;
    }

    public void onGetSecurityDeviceIdFinished(int i, String str) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IOnRemoteCallFinishedListener.DESCRIPTOR);
            obtain.writeInt(i);
            obtain.writeString(str);
            this.mRemote.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    public void onSignFinished(int i, byte[] bArr) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IOnRemoteCallFinishedListener.DESCRIPTOR);
            obtain.writeInt(i);
            obtain.writeByteArray(bArr);
            this.mRemote.transact(2, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    public void onForceReloadFinished(int i) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IOnRemoteCallFinishedListener.DESCRIPTOR);
            obtain.writeInt(i);
            this.mRemote.transact(3, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    public IBinder asBinder() {
        return this.mRemote;
    }
}
