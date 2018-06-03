package com.miui.analytics.internal.b;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.o;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class e extends SQLiteOpenHelper {
    public static final String a = "hybrid.db";
    public static final String b = "hybrid";
    public static final String c = "p";
    public static final String d = "c";
    public static final String e = "ts";
    public static final String f = "_id";
    private static final String g = "HybridUsageDB";
    private static final int h = 1;
    private static final int i = 1;
    private static final String j = "create table %s(_id INTEGER PRIMARY KEY,p TEXT,c TEXT,ts INT8)";
    private static final String k = "insert into %s values(null, ?, ?, ?)";
    private static final String l = "drop table if exists %s";
    private ReentrantReadWriteLock m;
    private Lock n;
    private Lock o;
    private Context p;

    public e(Context context) {
        super(context, a, null, 1);
        this.p = c.a(context);
        if (this.m == null) {
            this.m = new ReentrantReadWriteLock();
            this.n = this.m.readLock();
            this.o = this.m.writeLock();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        onUpgrade(sQLiteDatabase, 1, 1);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(j, new Object[]{b}));
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(l, new Object[]{b}));
    }

    private Cursor a(String str) {
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().rawQuery(str, null);
        } catch (Throwable e) {
            Log.e(o.a(g), "rawQuery e", e);
        }
        return cursor;
    }

    public boolean a(String str, String str2, long j) {
        try {
            this.o.lock();
            getWritableDatabase().execSQL(String.format(k, new Object[]{b}), new Object[]{str, str2, Long.valueOf(j)});
            if (this.o != null) {
                this.o.unlock();
            }
        } catch (Throwable e) {
            Log.e(o.a(g), "insert e", e);
            if (this.o != null) {
                this.o.unlock();
            }
        } catch (Throwable th) {
            if (this.o != null) {
                this.o.unlock();
            }
        }
        return true;
    }

    public boolean a() {
        try {
            this.o.lock();
            o.a(g, "deleteOneweekAgo deleteCount" + getWritableDatabase().delete(b, "ts < ? ", new String[]{String.valueOf(System.currentTimeMillis() - ac.a)}));
            if (this.o == null) {
                return true;
            }
            this.o.unlock();
            return true;
        } catch (Throwable e) {
            Log.e(o.a(g), "deleteOneweekAgo e", e);
            if (this.o != null) {
                this.o.unlock();
            }
            return false;
        } catch (Throwable e2) {
            if (this.o != null) {
                this.o.unlock();
            }
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.miui.analytics.internal.collection.UploadUsageHelper.HybridInfo> a(long r10, long r12) {
        /*
        r9 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "select * from hybrid where ts >= ";
        r0 = r0.append(r1);
        r0 = r0.append(r10);
        r1 = " and ";
        r0 = r0.append(r1);
        r1 = "ts";
        r0 = r0.append(r1);
        r1 = " <= ";
        r0 = r0.append(r1);
        r0 = r0.append(r12);
        r1 = r0.toString();
        r2 = new java.util.ArrayList;
        r2.<init>();
        r0 = 0;
        r3 = r9.n;	 Catch:{ Exception -> 0x0094, all -> 0x00a4 }
        r3.lock();	 Catch:{ Exception -> 0x0094, all -> 0x00a4 }
        r0 = r9.a(r1);	 Catch:{ Exception -> 0x0094, all -> 0x00a4 }
        if (r0 == 0) goto L_0x006c;
    L_0x003a:
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x0094, all -> 0x00b7 }
        if (r1 == 0) goto L_0x006c;
    L_0x0040:
        r1 = "p";
        r1 = r0.getColumnIndex(r1);	 Catch:{ Exception -> 0x0094, all -> 0x00b7 }
        r1 = r0.getString(r1);	 Catch:{ Exception -> 0x0094, all -> 0x00b7 }
        r3 = "c";
        r3 = r0.getColumnIndex(r3);	 Catch:{ Exception -> 0x0094, all -> 0x00b7 }
        r3 = r0.getString(r3);	 Catch:{ Exception -> 0x0094, all -> 0x00b7 }
        r4 = "ts";
        r4 = r0.getColumnIndex(r4);	 Catch:{ Exception -> 0x0094, all -> 0x00b7 }
        r4 = r0.getLong(r4);	 Catch:{ Exception -> 0x0094, all -> 0x00b7 }
        r6 = new com.miui.analytics.internal.collection.UploadUsageHelper$HybridInfo;	 Catch:{ Exception -> 0x0094, all -> 0x00b7 }
        r6.<init>(r1, r4, r3);	 Catch:{ Exception -> 0x0094, all -> 0x00b7 }
        r2.add(r6);	 Catch:{ Exception -> 0x0094, all -> 0x00b7 }
        r1 = r0.moveToNext();	 Catch:{ Exception -> 0x0094, all -> 0x00b7 }
        if (r1 != 0) goto L_0x0040;
    L_0x006c:
        if (r0 == 0) goto L_0x0071;
    L_0x006e:
        r0.close();
    L_0x0071:
        r0 = r9.n;
        if (r0 == 0) goto L_0x007a;
    L_0x0075:
        r0 = r9.n;
        r0.unlock();
    L_0x007a:
        r0 = "HybridUsageDB";
        r1 = "querySendCountExpired %d events from db. ";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = r2.size();
        r5 = java.lang.Integer.valueOf(r5);
        r3[r4] = r5;
        r1 = java.lang.String.format(r1, r3);
        com.miui.analytics.internal.util.o.a(r0, r1);
        return r2;
    L_0x0094:
        r1 = move-exception;
        if (r0 == 0) goto L_0x009a;
    L_0x0097:
        r0.close();
    L_0x009a:
        r0 = r9.n;
        if (r0 == 0) goto L_0x007a;
    L_0x009e:
        r0 = r9.n;
        r0.unlock();
        goto L_0x007a;
    L_0x00a4:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x00a8:
        if (r1 == 0) goto L_0x00ad;
    L_0x00aa:
        r1.close();
    L_0x00ad:
        r1 = r9.n;
        if (r1 == 0) goto L_0x00b6;
    L_0x00b1:
        r1 = r9.n;
        r1.unlock();
    L_0x00b6:
        throw r0;
    L_0x00b7:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x00a8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.e.a(long, long):java.util.List<com.miui.analytics.internal.collection.UploadUsageHelper$HybridInfo>");
    }
}
