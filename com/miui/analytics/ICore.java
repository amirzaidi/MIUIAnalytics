package com.miui.analytics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICore extends IInterface {

    public static abstract class Stub extends Binder implements ICore {
        private static final String DESCRIPTOR = "com.miui.analytics.ICore";
        static final int TRANSACTION_deleteAllEvents = 9;
        static final int TRANSACTION_getClientExtra = 3;
        static final int TRANSACTION_getVersion = 1;
        static final int TRANSACTION_getVersionName = 2;
        static final int TRANSACTION_isPolicyReady = 8;
        static final int TRANSACTION_setDebugOn = 4;
        static final int TRANSACTION_setDefaultPolicy = 7;
        static final int TRANSACTION_trackEvent = 5;
        static final int TRANSACTION_trackEvents = 6;

        private static class Proxy implements ICore {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public int getVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getVersionName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getClientExtra(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setDebugOn(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void trackEvent(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(Stub.TRANSACTION_trackEvent, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void trackEvents(String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    this.mRemote.transact(Stub.TRANSACTION_trackEvents, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setDefaultPolicy(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(Stub.TRANSACTION_setDefaultPolicy, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isPolicyReady(String str, String str2) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(Stub.TRANSACTION_isPolicyReady, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void deleteAllEvents(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(Stub.TRANSACTION_deleteAllEvents, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICore asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICore)) {
                return new Proxy(iBinder);
            }
            return (ICore) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            String versionName;
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = getVersion();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    versionName = getVersionName();
                    parcel2.writeNoException();
                    parcel2.writeString(versionName);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    versionName = getClientExtra(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(versionName);
                    return true;
                case 4:
                    boolean z;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    setDebugOn(z);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_trackEvent /*5*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    trackEvent(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_trackEvents /*6*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    trackEvents(parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setDefaultPolicy /*7*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDefaultPolicy(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_isPolicyReady /*8*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isPolicyReady = isPolicyReady(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (isPolicyReady) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case TRANSACTION_deleteAllEvents /*9*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteAllEvents(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void deleteAllEvents(String str) throws RemoteException;

    String getClientExtra(String str, String str2) throws RemoteException;

    int getVersion() throws RemoteException;

    String getVersionName() throws RemoteException;

    boolean isPolicyReady(String str, String str2) throws RemoteException;

    void setDebugOn(boolean z) throws RemoteException;

    void setDefaultPolicy(String str, String str2) throws RemoteException;

    void trackEvent(String str) throws RemoteException;

    void trackEvents(String[] strArr) throws RemoteException;
}
