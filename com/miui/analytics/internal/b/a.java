package com.miui.analytics.internal.b;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.util.ad;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class a extends SQLiteOpenHelper {
    public static final String a = "analytics.db";
    public static final String b = "analytics";
    public static final String c = "config_key";
    public static final String d = "event_time";
    public static final String e = "session_id";
    public static final String f = "app_id";
    public static final String g = "body";
    public static final String h = "sn";
    public static final String i = "header";
    public static final String j = "_id";
    public static final String k = "send_count";
    public static final String l = "event_type";
    public static final String m = "id_type";
    private static final String n = "AnalyticsDB";
    private static final int o = 1;
    private static final int p = 4;
    private static final String q = "create table %s(_id INTEGER PRIMARY KEY,config_key TEXT,event_time INT8, session_id TEXT,app_id TEXT,sn TEXT,body TEXT, header TEXT)";
    private static final String r = "drop table if exists %s";
    private static final String s = "delete from analytics where _id = %s";
    private static final String t = "delete from analytics";
    private static final String u = "insert into %s values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String v = "delete from analytics where event_time < ";
    private ReentrantReadWriteLock w;
    private Lock x;
    private Lock y;
    private Context z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0020 in list [B:5:0x001b]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:60)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r3 = this;
        r0 = "AnalyticsDB";
        r0 = com.miui.analytics.internal.util.o.a(r0);
        r1 = "[ANALYTICS-DB-TRUNCATE] drop table analytics";
        android.util.Log.w(r0, r1);
        r0 = r3.y;	 Catch:{ Exception -> 0x0021, all -> 0x0037 }
        r0.lock();	 Catch:{ Exception -> 0x0021, all -> 0x0037 }
        r0 = r3.getWritableDatabase();	 Catch:{ Exception -> 0x0021, all -> 0x0037 }
        r3.c(r0);	 Catch:{ Exception -> 0x0021, all -> 0x0037 }
        r0 = r3.y;
        if (r0 == 0) goto L_0x0020;
    L_0x001b:
        r0 = r3.y;
        r0.unlock();
    L_0x0020:
        return;
    L_0x0021:
        r0 = move-exception;
        r1 = "AnalyticsDB";	 Catch:{ Exception -> 0x0021, all -> 0x0037 }
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x0021, all -> 0x0037 }
        r2 = "deleteAllItemsInAnalyticsTable e";	 Catch:{ Exception -> 0x0021, all -> 0x0037 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x0021, all -> 0x0037 }
        r0 = r3.y;
        if (r0 == 0) goto L_0x0020;
    L_0x0031:
        r0 = r3.y;
        r0.unlock();
        goto L_0x0020;
    L_0x0037:
        r0 = move-exception;
        r1 = r3.y;
        if (r1 == 0) goto L_0x0041;
    L_0x003c:
        r1 = r3.y;
        r1.unlock();
    L_0x0041:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.a.a():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.util.List<com.miui.analytics.internal.LogEvent> r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x000a in list [B:15:0x0078]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:60)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r8 = this;
        r0 = "update %s set %s = %s + 1 where %s = %s";
        if (r9 == 0) goto L_0x000a;
    L_0x0004:
        r0 = r9.size();
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r0 = r8.y;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r0.lock();	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r1 = r8.getWritableDatabase();	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r2 = r9.iterator();	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
    L_0x0018:
        r0 = r2.hasNext();	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        if (r0 == 0) goto L_0x007e;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
    L_0x001e:
        r0 = r2.next();	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r0 = (com.miui.analytics.internal.LogEvent) r0;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r3 = "update %s set %s = %s + 1 where %s = %s";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4 = 5;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r5 = 0;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r6 = "analytics";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r5 = 1;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r6 = "send_count";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r5 = 2;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r6 = "send_count";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r5 = 3;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r6 = "_id";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r5 = 4;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r6 = r0.i();	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r0 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4[r5] = r0;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r0 = java.lang.String.format(r3, r4);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r1.execSQL(r0);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r3 = "AnalyticsDB";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4.<init>();	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r5 = "increaseRetryCountFromDB : ";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r0 = r4.append(r0);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        com.miui.analytics.internal.util.o.a(r3, r0);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        goto L_0x0018;
    L_0x0068:
        r0 = move-exception;
        r1 = "AnalyticsDB";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r2 = "increaseRetryCount e";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r0 = r8.y;
        if (r0 == 0) goto L_0x000a;
    L_0x0078:
        r0 = r8.y;
        r0.unlock();
        goto L_0x000a;
    L_0x007e:
        r0 = r8.y;
        if (r0 == 0) goto L_0x000a;
    L_0x0082:
        r0 = r8.y;
        r0.unlock();
        goto L_0x000a;
    L_0x0088:
        r0 = move-exception;
        r1 = r8.y;
        if (r1 == 0) goto L_0x0092;
    L_0x008d:
        r1 = r8.y;
        r1.unlock();
    L_0x0092:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.a.a(java.util.List):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(long r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0029 in list [B:11:0x003b]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:60)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r3 = this;
        r0 = r3.y;	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r0.lock();	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r0 = r3.getWritableDatabase();	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r1.<init>();	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r2 = "delete from analytics where event_time < ";	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r0.execSQL(r1);	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r0 = r3.y;
        if (r0 == 0) goto L_0x0028;
    L_0x0023:
        r0 = r3.y;
        r0.unlock();
    L_0x0028:
        r0 = 1;
    L_0x0029:
        return r0;
    L_0x002a:
        r0 = move-exception;
        r1 = "AnalyticsDB";	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r2 = "deleteByTime e";	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x002a, all -> 0x0041 }
        r0 = 0;
        r1 = r3.y;
        if (r1 == 0) goto L_0x0029;
    L_0x003b:
        r1 = r3.y;
        r1.unlock();
        goto L_0x0029;
    L_0x0041:
        r0 = move-exception;
        r1 = r3.y;
        if (r1 == 0) goto L_0x004b;
    L_0x0046:
        r1 = r3.y;
        r1.unlock();
    L_0x004b:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.a.a(long):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0046 in list [B:4:0x0041]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:60)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r6 = this;
        r0 = r6.y;	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r0.lock();	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r0 = r6.getWritableDatabase();	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r1 = "analytics";	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r2 = "send_count >= ? ";	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r3 = 1;	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r4 = 0;	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r5 = r6.z;	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r5 = com.miui.analytics.internal.util.g.a(r5);	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r5 = r5.a();	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r3[r4] = r5;	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r0 = r0.delete(r1, r2, r3);	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r1 = "AnalyticsDB";	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r2.<init>();	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r3 = "deleteEventRetryExpired deleteCount:";	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        com.miui.analytics.internal.util.o.a(r1, r0);	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r0 = r6.y;
        if (r0 == 0) goto L_0x0046;
    L_0x0041:
        r0 = r6.y;
        r0.unlock();
    L_0x0046:
        return;
    L_0x0047:
        r0 = move-exception;
        r1 = "AnalyticsDB";	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r2 = "deleteEventRetryExpired e";	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x0047, all -> 0x005d }
        r0 = r6.y;
        if (r0 == 0) goto L_0x0046;
    L_0x0057:
        r0 = r6.y;
        r0.unlock();
        goto L_0x0046;
    L_0x005d:
        r0 = move-exception;
        r1 = r6.y;
        if (r1 == 0) goto L_0x0067;
    L_0x0062:
        r1 = r6.y;
        r1.unlock();
    L_0x0067:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.a.b():void");
    }

    public a(Context context) {
        super(context, a, null, 4);
        this.z = c.a(context);
        if (this.w == null) {
            this.w = new ReentrantReadWriteLock();
            this.x = this.w.readLock();
            this.y = this.w.writeLock();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        onUpgrade(sQLiteDatabase, 1, 4);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d(o.a(n), String.format("upgrade db:%s, oldVersion:%d, newVersion:%d", new Object[]{a, Integer.valueOf(i), Integer.valueOf(i2)}));
        for (int i3 = i + 1; i3 <= i2; i3++) {
            switch (i3) {
                case 2:
                    String format = String.format("alter table %s add column %s %s", new Object[]{b, "send_count", " INT DEFAULT(0)"});
                    o.a(n, "onUpgrade version " + i3 + ": " + format);
                    a(sQLiteDatabase, format);
                    break;
                case 3:
                    a(sQLiteDatabase, String.format("alter table %s add column %s %s", new Object[]{b, "event_type", " INT DEFAULT(0)"}));
                    break;
                case 4:
                    a(sQLiteDatabase, String.format("alter table %s add column %s %s", new Object[]{b, "id_type", " INT DEFAULT(0)"}));
                    break;
                default:
                    break;
            }
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d(o.a(n), String.format("drop & create table when downgrade, db:%s, oldVersion:%d, newVersion:%d", new Object[]{a, Integer.valueOf(i), Integer.valueOf(i2)}));
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(q, new Object[]{b}));
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(r, new Object[]{b}));
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL(str);
        } catch (Throwable e) {
            Log.e(o.a(n), "exeSQL e:", e);
        }
    }

    private Cursor b(String str) {
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().rawQuery(str, null);
        } catch (Throwable e) {
            Log.e(o.a(n), "rawQuery e:", e);
        }
        return cursor;
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(t);
        } catch (Exception e) {
        }
    }

    public boolean b(List<LogEvent> list) {
        ad.a("InsertEvents");
        o.a(n, "insert events " + list.size());
        SQLiteDatabase sQLiteDatabase = null;
        try {
            this.y.lock();
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            for (LogEvent logEvent : list) {
                SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(String.format(u, new Object[]{b}));
                compileStatement.bindString(1, logEvent.b());
                compileStatement.bindLong(2, logEvent.e());
                compileStatement.bindString(3, logEvent.d());
                compileStatement.bindString(4, logEvent.c());
                compileStatement.bindString(5, logEvent.h());
                compileStatement.bindString(6, logEvent.g());
                compileStatement.bindString(7, logEvent.j());
                compileStatement.bindLong(8, (long) logEvent.k());
                compileStatement.bindLong(9, (long) logEvent.o());
                compileStatement.bindLong(10, (long) logEvent.p());
                compileStatement.executeInsert();
                compileStatement.clearBindings();
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e) {
                }
            }
            ad.a();
            if (this.y != null) {
                this.y.unlock();
            }
        } catch (Throwable e2) {
            Log.e(o.a(n), "insert event list e", e2);
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e3) {
                }
            }
            ad.a();
            if (this.y != null) {
                this.y.unlock();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e4) {
                }
            }
            ad.a();
            if (this.y != null) {
                this.y.unlock();
            }
        }
        return true;
    }

    public boolean c(List<LogEvent> list) {
        ad.a("DeleteEvents");
        o.a(n, "delete ");
        SQLiteDatabase sQLiteDatabase = null;
        try {
            this.y.lock();
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            for (LogEvent logEvent : list) {
                sQLiteDatabase.execSQL(String.format(s, new Object[]{logEvent.i() + ""}));
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e) {
                }
            }
            ad.a();
            if (this.y != null) {
                this.y.unlock();
            }
        } catch (Throwable e2) {
            Log.e(o.a(n), "delete event list e", e2);
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e3) {
                }
            }
            ad.a();
            if (this.y != null) {
                this.y.unlock();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e4) {
                }
            }
            ad.a();
            if (this.y != null) {
                this.y.unlock();
            }
        }
        return true;
    }

    public void a(String str) {
        try {
            List c = c();
            Iterator it = c.iterator();
            while (it.hasNext()) {
                if (!TextUtils.equals(((LogEvent) it.next()).c(), str)) {
                    it.remove();
                }
            }
            c(c);
        } catch (Throwable e) {
            Log.e(o.a(n), "deleteEventsByAppId e", e);
        }
    }

    public List<LogEvent> c() {
        String str = "select * from analytics";
        List<LogEvent> arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            this.x.lock();
            cursor = b("select * from analytics");
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                if (this.x != null) {
                    this.x.unlock();
                }
                o.a(n, String.format("queryEvents %d events from db. ", new Object[]{Integer.valueOf(arrayList.size())}));
                return arrayList;
            }
            do {
                arrayList.add(new LogEvent(cursor));
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            if (this.x != null) {
                this.x.unlock();
            }
            o.a(n, String.format("queryEvents %d events from db. ", new Object[]{Integer.valueOf(arrayList.size())}));
            return arrayList;
        } catch (Throwable e) {
            Log.e(o.a(n), "queryEvents e", e);
            if (cursor != null) {
                cursor.close();
            }
            if (this.x != null) {
                this.x.unlock();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            if (this.x != null) {
                this.x.unlock();
            }
        }
    }

    public long d() {
        Throwable e;
        String str = "select count(*) from analytics";
        Cursor cursor = null;
        Cursor b;
        try {
            this.x.lock();
            b = b("select count(*) from analytics");
            if (b != null) {
                try {
                    if (b.moveToFirst()) {
                        o.a(n, "db count is " + b.getLong(0));
                        long j = b.getLong(0);
                        if (b != null) {
                            b.close();
                        }
                        if (this.x == null) {
                            return j;
                        }
                        this.x.unlock();
                        return j;
                    }
                } catch (Exception e2) {
                    e = e2;
                    cursor = b;
                    try {
                        Log.e(o.a(n), "getCount e", e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (this.x != null) {
                            this.x.unlock();
                        }
                        return 0;
                    } catch (Throwable th) {
                        e = th;
                        b = cursor;
                        if (b != null) {
                            b.close();
                        }
                        if (this.x != null) {
                            this.x.unlock();
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    if (b != null) {
                        b.close();
                    }
                    if (this.x != null) {
                        this.x.unlock();
                    }
                    throw e;
                }
            }
            if (b != null) {
                b.close();
            }
            if (this.x != null) {
                this.x.unlock();
            }
        } catch (Exception e3) {
            e = e3;
            Log.e(o.a(n), "getCount e", e);
            if (cursor != null) {
                cursor.close();
            }
            if (this.x != null) {
                this.x.unlock();
            }
            return 0;
        } catch (Throwable th3) {
            e = th3;
            b = null;
            if (b != null) {
                b.close();
            }
            if (this.x != null) {
                this.x.unlock();
            }
            throw e;
        }
        return 0;
    }

    public String e() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase != null) {
            return readableDatabase.getPath();
        }
        return null;
    }
}
