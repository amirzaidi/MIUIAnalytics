package com.miui.analytics.internal.util;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class s {
    private static final String a = "ObjectStore";

    public static Object a(String str) {
        Object readObject;
        Throwable e;
        Throwable th;
        Closeable readObject2 = null;
        Closeable objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(str));
            try {
                readObject2 = objectInputStream.readObject();
                m.a(objectInputStream);
            } catch (Exception e2) {
                e = e2;
                try {
                    o.b(a, "failed to read object from " + str, e);
                    m.a(objectInputStream);
                    return readObject2;
                } catch (Throwable th2) {
                    th = th2;
                    m.a(objectInputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            objectInputStream = readObject2;
            o.b(a, "failed to read object from " + str, e);
            m.a(objectInputStream);
            return readObject2;
        } catch (Throwable e4) {
            objectInputStream = readObject2;
            th = e4;
            m.a(objectInputStream);
            throw th;
        }
        return readObject2;
    }

    public static boolean a(String str, Serializable serializable) {
        Throwable e;
        Closeable objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(str));
            try {
                objectOutputStream.writeObject(serializable);
                m.a(objectOutputStream);
                return true;
            } catch (Exception e2) {
                e = e2;
                try {
                    o.b(a, "failed to write object to " + str, e);
                    m.a(objectOutputStream);
                    return false;
                } catch (Throwable th) {
                    e = th;
                    m.a(objectOutputStream);
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            objectOutputStream = null;
            o.b(a, "failed to write object to " + str, e);
            m.a(objectOutputStream);
            return false;
        } catch (Throwable th2) {
            e = th2;
            objectOutputStream = null;
            m.a(objectOutputStream);
            throw e;
        }
    }
}
