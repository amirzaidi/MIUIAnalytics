package com.miui.analytics.internal.b;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.util.ad;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class b extends SQLiteOpenHelper {
    public static final String a = "analyticsv2.db";
    public static final String b = "analyticsv2";
    public static final String c = "config_key";
    public static final String d = "event_time";
    public static final String e = "session_id";
    public static final String f = "app_id";
    public static final String g = "body";
    public static final String h = "sn";
    public static final String i = "header";
    public static final String j = "_id";
    public static final String k = "real_time";
    public static final String l = "polling_wifi_only";
    public static final String m = "send_count";
    public static final String n = "level";
    public static final String o = "event_type";
    public static final String p = "id_type";
    private static final String q = "AnalyticsDBV2";
    private static final int r = 1;
    private static final int s = 3;
    private static final String t = "create table %s(_id INTEGER PRIMARY KEY,config_key TEXT,event_time INT8, session_id TEXT,app_id TEXT,sn TEXT,body TEXT, header TEXT,real_time INT,polling_wifi_only INT,send_count INT,level INT)";
    private static final String u = "drop table if exists %s";
    private static final String v = "delete from analyticsv2 where _id = %s";
    private static final String w = "delete from analyticsv2";
    private static final String x = "insert into %s values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private Lock A;
    private Context B;
    private ReentrantReadWriteLock y;
    private Lock z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(long r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0045 in list [B:10:0x0042]
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
        r5 = this;
        r1 = 0;
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0.<init>();	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2 = "select * from analyticsv2 where event_time < ";	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0 = r0.append(r6);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r1 = r5.a(r0);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0.<init>();	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        if (r1 == 0) goto L_0x0035;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
    L_0x001f:
        r2 = r1.moveToFirst();	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        if (r2 == 0) goto L_0x0035;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
    L_0x0025:
        r2 = new com.miui.analytics.internal.LogEvent;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r3 = "analyticsv2.db";	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2.<init>(r1, r3);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0.add(r2);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        if (r2 != 0) goto L_0x0025;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
    L_0x0035:
        r2 = r5.B;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2 = com.miui.analytics.internal.c.c.a(r2);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r3 = r5.B;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2.a(r3, r0);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        if (r1 == 0) goto L_0x0045;
    L_0x0042:
        r1.close();
    L_0x0045:
        return;
    L_0x0046:
        r0 = move-exception;
        r2 = "AnalyticsDBV2";	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2 = com.miui.analytics.internal.util.o.a(r2);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r3 = "monitorDeleteByTime e";	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        if (r1 == 0) goto L_0x0045;
    L_0x0054:
        r1.close();
        goto L_0x0045;
    L_0x0058:
        r0 = move-exception;
        if (r1 == 0) goto L_0x005e;
    L_0x005b:
        r1.close();
    L_0x005e:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.b.c(long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d(long r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0045 in list [B:10:0x0042]
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
        r5 = this;
        r1 = 0;
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0.<init>();	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2 = "select * from analyticsv2 where event_time < ";	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0 = r0.append(r6);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r1 = r5.a(r0);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0.<init>();	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        if (r1 == 0) goto L_0x0035;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
    L_0x001f:
        r2 = r1.moveToFirst();	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        if (r2 == 0) goto L_0x0035;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
    L_0x0025:
        r2 = new com.miui.analytics.internal.LogEvent;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r3 = "analyticsv2.db";	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2.<init>(r1, r3);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r0.add(r2);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        if (r2 != 0) goto L_0x0025;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
    L_0x0035:
        r2 = r5.B;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2 = com.miui.analytics.internal.c.c.a(r2);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r3 = r5.B;	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2.a(r3, r0);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        if (r1 == 0) goto L_0x0045;
    L_0x0042:
        r1.close();
    L_0x0045:
        return;
    L_0x0046:
        r0 = move-exception;
        r2 = "AnalyticsDBV2";	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r2 = com.miui.analytics.internal.util.o.a(r2);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        r3 = "monitorDeleteEventTimeExpired e";	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ Exception -> 0x0046, all -> 0x0058 }
        if (r1 == 0) goto L_0x0045;
    L_0x0054:
        r1.close();
        goto L_0x0045;
    L_0x0058:
        r0 = move-exception;
        if (r1 == 0) goto L_0x005e;
    L_0x005b:
        r1.close();
    L_0x005e:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.b.d(long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void g() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x004f in list [B:10:0x004c]
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
        r4 = this;
        r1 = 0;
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r0.<init>();	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r2 = "select * from analyticsv2 where send_count > ";	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r2 = r4.B;	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r2 = com.miui.analytics.internal.util.g.a(r2);	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r2 = r2.a();	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r1 = r4.a(r0);	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r0.<init>();	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        if (r1 == 0) goto L_0x003f;	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
    L_0x0029:
        r2 = r1.moveToFirst();	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        if (r2 == 0) goto L_0x003f;	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
    L_0x002f:
        r2 = new com.miui.analytics.internal.LogEvent;	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r3 = "analyticsv2.db";	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r2.<init>(r1, r3);	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r0.add(r2);	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        if (r2 != 0) goto L_0x002f;	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
    L_0x003f:
        r2 = r4.B;	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r2 = com.miui.analytics.internal.c.c.a(r2);	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r3 = r4.B;	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r2.b(r3, r0);	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        if (r1 == 0) goto L_0x004f;
    L_0x004c:
        r1.close();
    L_0x004f:
        return;
    L_0x0050:
        r0 = move-exception;
        r2 = "AnalyticsDBV2";	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r2 = com.miui.analytics.internal.util.o.a(r2);	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        r3 = "monitorDeleteEventSendCountExpired e";	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ Exception -> 0x0050, all -> 0x0062 }
        if (r1 == 0) goto L_0x004f;
    L_0x005e:
        r1.close();
        goto L_0x004f;
    L_0x0062:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0068;
    L_0x0065:
        r1.close();
    L_0x0068:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.b.g():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0048 in list [B:8:0x0043]
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
        r4 = this;
        r0 = "AnalyticsDBV2";
        r0 = com.miui.analytics.internal.util.o.a(r0);
        r1 = "[ANALYTICS-DBV2-TRUNCATE] drop table analytics";
        android.util.Log.w(r0, r1);
        r0 = r4.A;	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r0.lock();	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r0 = r4.getWritableDatabase();	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r1 = "analyticsv2";	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r2 = 0;	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r3 = 0;	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r0 = r0.delete(r1, r2, r3);	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        if (r0 <= 0) goto L_0x0027;	 Catch:{ Exception -> 0x0049, all -> 0x005f }
    L_0x001e:
        r1 = r4.B;	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r1 = com.miui.analytics.internal.c.c.a(r1);	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r1.a();	 Catch:{ Exception -> 0x0049, all -> 0x005f }
    L_0x0027:
        r1 = "AnalyticsDBV2";	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r2.<init>();	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r3 = "deleteAllItemsInAnalyticsTable deleteCount";	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        com.miui.analytics.internal.util.o.a(r1, r0);	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r0 = r4.A;
        if (r0 == 0) goto L_0x0048;
    L_0x0043:
        r0 = r4.A;
        r0.unlock();
    L_0x0048:
        return;
    L_0x0049:
        r0 = move-exception;
        r1 = "AnalyticsDBV2";	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r2 = "deleteAllItemsInAnalyticsTable e";	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x0049, all -> 0x005f }
        r0 = r4.A;
        if (r0 == 0) goto L_0x0048;
    L_0x0059:
        r0 = r4.A;
        r0.unlock();
        goto L_0x0048;
    L_0x005f:
        r0 = move-exception;
        r1 = r4.A;
        if (r1 == 0) goto L_0x0069;
    L_0x0064:
        r1 = r4.A;
        r1.unlock();
    L_0x0069:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.b.a():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0055 in list [B:9:0x0050]
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
        r0 = r6.A;	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r0.lock();	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r0 = com.miui.analytics.internal.util.n.b();	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        if (r0 != 0) goto L_0x0011;	 Catch:{ Exception -> 0x0056, all -> 0x006c }
    L_0x000b:
        r0 = com.miui.analytics.internal.util.n.c();	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        if (r0 == 0) goto L_0x0014;	 Catch:{ Exception -> 0x0056, all -> 0x006c }
    L_0x0011:
        r6.g();	 Catch:{ Exception -> 0x0056, all -> 0x006c }
    L_0x0014:
        r0 = r6.getWritableDatabase();	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r1 = "analyticsv2";	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r2 = "send_count >= ? ";	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r3 = 1;	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r4 = 0;	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r5 = r6.B;	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r5 = com.miui.analytics.internal.util.g.a(r5);	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r5 = r5.a();	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r3[r4] = r5;	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r0 = r0.delete(r1, r2, r3);	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r1 = "AnalyticsDBV2";	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r2.<init>();	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r3 = "deleteEventSendCountExpired deleteCount:";	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        com.miui.analytics.internal.util.o.a(r1, r0);	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r0 = r6.A;
        if (r0 == 0) goto L_0x0055;
    L_0x0050:
        r0 = r6.A;
        r0.unlock();
    L_0x0055:
        return;
    L_0x0056:
        r0 = move-exception;
        r1 = "AnalyticsDBV2";	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r2 = "deleteEventSendCountExpired e";	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x0056, all -> 0x006c }
        r0 = r6.A;
        if (r0 == 0) goto L_0x0055;
    L_0x0066:
        r0 = r6.A;
        r0.unlock();
        goto L_0x0055;
    L_0x006c:
        r0 = move-exception;
        r1 = r6.A;
        if (r1 == 0) goto L_0x0076;
    L_0x0071:
        r1 = r6.A;
        r1.unlock();
    L_0x0076:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.b.b():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(long r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0050 in list [B:10:0x004b]
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
        r7 = this;
        r0 = java.lang.System.currentTimeMillis();
        r0 = r0 - r8;
        r2 = r7.A;	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r2.lock();	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r2 = com.miui.analytics.internal.util.n.b();	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        if (r2 != 0) goto L_0x0016;	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
    L_0x0010:
        r2 = com.miui.analytics.internal.util.n.c();	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        if (r2 == 0) goto L_0x0019;	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
    L_0x0016:
        r7.d(r0);	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
    L_0x0019:
        r2 = r7.getWritableDatabase();	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r3 = "analyticsv2";	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r4 = "event_time < ? ";	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r5 = 1;	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r6 = 0;	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r5[r6] = r0;	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r0 = r2.delete(r3, r4, r5);	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r1 = "AnalyticsDBV2";	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r2.<init>();	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r3 = "deleteEventTimeExpired deleteCount";	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        com.miui.analytics.internal.util.o.a(r1, r0);	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r0 = r7.A;
        if (r0 == 0) goto L_0x0050;
    L_0x004b:
        r0 = r7.A;
        r0.unlock();
    L_0x0050:
        return;
    L_0x0051:
        r0 = move-exception;
        r1 = "AnalyticsDBV2";	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r2 = "deleteEventTimeExpired e";	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x0051, all -> 0x0067 }
        r0 = r7.A;
        if (r0 == 0) goto L_0x0050;
    L_0x0061:
        r0 = r7.A;
        r0.unlock();
        goto L_0x0050;
    L_0x0067:
        r0 = move-exception;
        r1 = r7.A;
        if (r1 == 0) goto L_0x0071;
    L_0x006c:
        r1 = r7.A;
        r1.unlock();
    L_0x0071:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.b.b(long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(java.util.List<com.miui.analytics.internal.LogEvent> r9) {
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
        r0 = r8.A;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
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
        r6 = "analyticsv2";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
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
        r3 = "AnalyticsDBV2";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4.<init>();	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r5 = "increaseRetryCountFromDBV2 sql: ";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r0 = r4.append(r0);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        com.miui.analytics.internal.util.o.a(r3, r0);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        goto L_0x0018;
    L_0x0068:
        r0 = move-exception;
        r1 = "AnalyticsDBV2";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r2 = "increaseRetryCount e";	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x0068, all -> 0x0088 }
        r0 = r8.A;
        if (r0 == 0) goto L_0x000a;
    L_0x0078:
        r0 = r8.A;
        r0.unlock();
        goto L_0x000a;
    L_0x007e:
        r0 = r8.A;
        if (r0 == 0) goto L_0x000a;
    L_0x0082:
        r0 = r8.A;
        r0.unlock();
        goto L_0x000a;
    L_0x0088:
        r0 = move-exception;
        r1 = r8.A;
        if (r1 == 0) goto L_0x0092;
    L_0x008d:
        r1 = r8.A;
        r1.unlock();
    L_0x0092:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.b.c(java.util.List):void");
    }

    public b(Context context) {
        super(context, a, null, 3);
        this.B = c.a(context);
        if (this.y == null) {
            this.y = new ReentrantReadWriteLock();
            this.z = this.y.readLock();
            this.A = this.y.writeLock();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        onUpgrade(sQLiteDatabase, 1, 3);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d(o.a(q), String.format("upgrade db:%s, oldVersion:%d, newVersion:%d", new Object[]{a, Integer.valueOf(i), Integer.valueOf(i2)}));
        for (int i3 = i + 1; i3 <= i2; i3++) {
            switch (i3) {
                case 2:
                    a(sQLiteDatabase, String.format("alter table %s add column %s %s", new Object[]{b, "event_type", " INT DEFAULT(0)"}));
                    break;
                case 3:
                    a(sQLiteDatabase, String.format("alter table %s add column %s %s", new Object[]{b, "id_type", " INT DEFAULT(0)"}));
                    break;
                default:
                    break;
            }
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d(o.a(q), String.format("drop & create table when downgrade, db:%s, oldVersion:%d, newVersion:%d", new Object[]{a, Integer.valueOf(i), Integer.valueOf(i2)}));
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(t, new Object[]{b}));
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(u, new Object[]{b}));
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(w);
        } catch (Exception e) {
        }
    }

    private Cursor a(String str) {
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().rawQuery(str, null);
        } catch (Throwable e) {
            Log.e(o.a(q), "rawQuery e", e);
        }
        return cursor;
    }

    private void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL(str);
        } catch (Throwable e) {
            Log.e(o.a(q), "exeSQL e:", e);
        }
    }

    public boolean a(List<LogEvent> list) {
        if (list == null) {
            return false;
        }
        ad.a("InsertEvents");
        o.a(q, "insert events " + list.size());
        SQLiteDatabase sQLiteDatabase = null;
        try {
            this.A.lock();
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            h a = h.a(this.B);
            for (LogEvent logEvent : list) {
                long j;
                SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(String.format(x, new Object[]{b}));
                compileStatement.bindString(1, logEvent.b());
                compileStatement.bindLong(2, logEvent.e());
                compileStatement.bindString(3, logEvent.d());
                compileStatement.bindString(4, logEvent.c());
                compileStatement.bindString(5, logEvent.h());
                compileStatement.bindString(6, logEvent.g());
                compileStatement.bindString(7, logEvent.j());
                compileStatement.bindLong(8, a.h(logEvent) ? 1 : 0);
                if (a.g(logEvent)) {
                    j = 1;
                } else {
                    j = 0;
                }
                compileStatement.bindLong(9, j);
                compileStatement.bindLong(10, 0);
                compileStatement.bindLong(11, (long) a.i(logEvent));
                compileStatement.bindLong(12, (long) logEvent.o());
                compileStatement.bindLong(13, (long) logEvent.p());
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
            if (this.A != null) {
                this.A.unlock();
            }
        } catch (Throwable e2) {
            Log.e(o.a(q), "insert e", e2);
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e3) {
                }
            }
            ad.a();
            if (this.A != null) {
                this.A.unlock();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e4) {
                }
            }
            ad.a();
            if (this.A != null) {
                this.A.unlock();
            }
        }
        return true;
    }

    public boolean a(long j) {
        try {
            this.A.lock();
            if (n.b() || n.c()) {
                c(j);
            }
            o.a(q, "deleteByTime deleteCount" + getWritableDatabase().delete(b, "event_time < ? ", new String[]{String.valueOf(j)}));
            if (this.A == null) {
                return true;
            }
            this.A.unlock();
            return true;
        } catch (Throwable e) {
            Log.e(o.a(q), "deleteByTime e", e);
            if (this.A != null) {
                this.A.unlock();
            }
            return false;
        } catch (Throwable e2) {
            if (this.A != null) {
                this.A.unlock();
            }
            throw e2;
        }
    }

    public boolean b(List<LogEvent> list) {
        ad.a("DeleteEvents");
        o.a(q, "delete ");
        SQLiteDatabase sQLiteDatabase = null;
        try {
            this.A.lock();
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            for (LogEvent logEvent : list) {
                sQLiteDatabase.execSQL(String.format(v, new Object[]{logEvent.i() + ""}));
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e) {
                }
            }
            ad.a();
            if (this.A != null) {
                this.A.unlock();
            }
        } catch (Throwable e2) {
            Log.e(o.a(q), "delete e", e2);
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e3) {
                }
            }
            ad.a();
            if (this.A != null) {
                this.A.unlock();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e4) {
                }
            }
            ad.a();
            if (this.A != null) {
                this.A.unlock();
            }
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.miui.analytics.internal.LogEvent> h() {
        /*
        r7 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "select * from analyticsv2 where send_count > ";
        r0 = r0.append(r1);
        r1 = r7.B;
        r1 = com.miui.analytics.internal.util.g.a(r1);
        r1 = r1.a();
        r0 = r0.append(r1);
        r1 = r0.toString();
        r2 = new java.util.ArrayList;
        r2.<init>();
        r0 = 0;
        r3 = r7.z;	 Catch:{ Exception -> 0x006c, all -> 0x007c }
        r3.lock();	 Catch:{ Exception -> 0x006c, all -> 0x007c }
        r0 = r7.a(r1);	 Catch:{ Exception -> 0x006c, all -> 0x007c }
        if (r0 == 0) goto L_0x0044;
    L_0x002e:
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x006c, all -> 0x008f }
        if (r1 == 0) goto L_0x0044;
    L_0x0034:
        r1 = new com.miui.analytics.internal.LogEvent;	 Catch:{ Exception -> 0x006c, all -> 0x008f }
        r3 = "analyticsv2.db";
        r1.<init>(r0, r3);	 Catch:{ Exception -> 0x006c, all -> 0x008f }
        r2.add(r1);	 Catch:{ Exception -> 0x006c, all -> 0x008f }
        r1 = r0.moveToNext();	 Catch:{ Exception -> 0x006c, all -> 0x008f }
        if (r1 != 0) goto L_0x0034;
    L_0x0044:
        if (r0 == 0) goto L_0x0049;
    L_0x0046:
        r0.close();
    L_0x0049:
        r0 = r7.z;
        if (r0 == 0) goto L_0x0052;
    L_0x004d:
        r0 = r7.z;
        r0.unlock();
    L_0x0052:
        r0 = "AnalyticsDBV2";
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
    L_0x006c:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0072;
    L_0x006f:
        r0.close();
    L_0x0072:
        r0 = r7.z;
        if (r0 == 0) goto L_0x0052;
    L_0x0076:
        r0 = r7.z;
        r0.unlock();
        goto L_0x0052;
    L_0x007c:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x0080:
        if (r1 == 0) goto L_0x0085;
    L_0x0082:
        r1.close();
    L_0x0085:
        r1 = r7.z;
        if (r1 == 0) goto L_0x008e;
    L_0x0089:
        r1 = r7.z;
        r1.unlock();
    L_0x008e:
        throw r0;
    L_0x008f:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0080;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.b.h():java.util.List<com.miui.analytics.internal.LogEvent>");
    }

    public long c() {
        long j = 0;
        String str = "select min(event_time) from analyticsv2";
        Cursor cursor = null;
        try {
            this.z.lock();
            getReadableDatabase();
            cursor = a("select min(event_time) from analyticsv2");
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                if (this.z != null) {
                    this.z.unlock();
                }
                return j;
            }
            j = cursor.getLong(0);
            if (cursor != null) {
                cursor.close();
            }
            if (this.z != null) {
                this.z.unlock();
            }
            return j;
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            if (this.z != null) {
                this.z.unlock();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            if (this.z != null) {
                this.z.unlock();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.miui.analytics.internal.LogEvent> d() {
        /*
        r7 = this;
        r0 = "select * from analyticsv2 where real_time = 1";
        r0 = "AnalyticsDBV2";
        r1 = "queryRealTimeEvents :select * from analyticsv2 where real_time = 1";
        com.miui.analytics.internal.util.o.a(r0, r1);
        r1 = new java.util.ArrayList;
        r1.<init>();
        r0 = 0;
        r2 = r7.z;	 Catch:{ Exception -> 0x005a, all -> 0x006a }
        r2.lock();	 Catch:{ Exception -> 0x005a, all -> 0x006a }
        r2 = "select * from analyticsv2 where real_time = 1";
        r0 = r7.a(r2);	 Catch:{ Exception -> 0x005a, all -> 0x006a }
        if (r0 == 0) goto L_0x0032;
    L_0x001c:
        r2 = r0.moveToFirst();	 Catch:{ Exception -> 0x005a, all -> 0x007d }
        if (r2 == 0) goto L_0x0032;
    L_0x0022:
        r2 = new com.miui.analytics.internal.LogEvent;	 Catch:{ Exception -> 0x005a, all -> 0x007d }
        r3 = "analyticsv2.db";
        r2.<init>(r0, r3);	 Catch:{ Exception -> 0x005a, all -> 0x007d }
        r1.add(r2);	 Catch:{ Exception -> 0x005a, all -> 0x007d }
        r2 = r0.moveToNext();	 Catch:{ Exception -> 0x005a, all -> 0x007d }
        if (r2 != 0) goto L_0x0022;
    L_0x0032:
        if (r0 == 0) goto L_0x0037;
    L_0x0034:
        r0.close();
    L_0x0037:
        r0 = r7.z;
        if (r0 == 0) goto L_0x0040;
    L_0x003b:
        r0 = r7.z;
        r0.unlock();
    L_0x0040:
        r0 = "AnalyticsDBV2";
        r2 = "queryRealTimeEventsFromEventDbV2 %d events from db. ";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = r1.size();
        r5 = java.lang.Integer.valueOf(r5);
        r3[r4] = r5;
        r2 = java.lang.String.format(r2, r3);
        com.miui.analytics.internal.util.o.a(r0, r2);
        return r1;
    L_0x005a:
        r2 = move-exception;
        if (r0 == 0) goto L_0x0060;
    L_0x005d:
        r0.close();
    L_0x0060:
        r0 = r7.z;
        if (r0 == 0) goto L_0x0040;
    L_0x0064:
        r0 = r7.z;
        r0.unlock();
        goto L_0x0040;
    L_0x006a:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x006e:
        if (r1 == 0) goto L_0x0073;
    L_0x0070:
        r1.close();
    L_0x0073:
        r1 = r7.z;
        if (r1 == 0) goto L_0x007c;
    L_0x0077:
        r1 = r7.z;
        r1.unlock();
    L_0x007c:
        throw r0;
    L_0x007d:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x006e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.b.d():java.util.List<com.miui.analytics.internal.LogEvent>");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.miui.analytics.internal.LogEvent> a(int r8) {
        /*
        r7 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "select * from analyticsv2 where real_time = 0  order by event_time asc limit ";
        r0 = r0.append(r1);
        r0 = r0.append(r8);
        r1 = r0.toString();
        r0 = "AnalyticsDBV2";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "queryPollingEventsFromEventDBV2 : ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.miui.analytics.internal.util.o.a(r0, r2);
        r2 = new java.util.ArrayList;
        r2.<init>();
        r0 = 0;
        r3 = r7.z;	 Catch:{ Exception -> 0x007a, all -> 0x008a }
        r3.lock();	 Catch:{ Exception -> 0x007a, all -> 0x008a }
        r0 = r7.a(r1);	 Catch:{ Exception -> 0x007a, all -> 0x008a }
        if (r0 == 0) goto L_0x0052;
    L_0x003c:
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x007a, all -> 0x009d }
        if (r1 == 0) goto L_0x0052;
    L_0x0042:
        r1 = new com.miui.analytics.internal.LogEvent;	 Catch:{ Exception -> 0x007a, all -> 0x009d }
        r3 = "analyticsv2.db";
        r1.<init>(r0, r3);	 Catch:{ Exception -> 0x007a, all -> 0x009d }
        r2.add(r1);	 Catch:{ Exception -> 0x007a, all -> 0x009d }
        r1 = r0.moveToNext();	 Catch:{ Exception -> 0x007a, all -> 0x009d }
        if (r1 != 0) goto L_0x0042;
    L_0x0052:
        if (r0 == 0) goto L_0x0057;
    L_0x0054:
        r0.close();
    L_0x0057:
        r0 = r7.z;
        if (r0 == 0) goto L_0x0060;
    L_0x005b:
        r0 = r7.z;
        r0.unlock();
    L_0x0060:
        r0 = "AnalyticsDBV2";
        r1 = "queryPollingEventsFromEventDBV2 %d events from db. ";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = r2.size();
        r5 = java.lang.Integer.valueOf(r5);
        r3[r4] = r5;
        r1 = java.lang.String.format(r1, r3);
        com.miui.analytics.internal.util.o.a(r0, r1);
        return r2;
    L_0x007a:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0080;
    L_0x007d:
        r0.close();
    L_0x0080:
        r0 = r7.z;
        if (r0 == 0) goto L_0x0060;
    L_0x0084:
        r0 = r7.z;
        r0.unlock();
        goto L_0x0060;
    L_0x008a:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x008e:
        if (r1 == 0) goto L_0x0093;
    L_0x0090:
        r1.close();
    L_0x0093:
        r1 = r7.z;
        if (r1 == 0) goto L_0x009c;
    L_0x0097:
        r1 = r7.z;
        r1.unlock();
    L_0x009c:
        throw r0;
    L_0x009d:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x008e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.b.a(int):java.util.List<com.miui.analytics.internal.LogEvent>");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.miui.analytics.internal.LogEvent> b(int r8) {
        /*
        r7 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "select * from analyticsv2 where real_time = 0 and polling_wifi_only = 0  order by event_time asc limit ";
        r0 = r0.append(r1);
        r0 = r0.append(r8);
        r1 = r0.toString();
        r0 = "AnalyticsDBV2";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "queryPollingEventNetAllFromEventDBV2 : ";
        r2 = r2.append(r3);
        r2 = r2.append(r1);
        r2 = r2.toString();
        com.miui.analytics.internal.util.o.a(r0, r2);
        r2 = new java.util.ArrayList;
        r2.<init>();
        r0 = 0;
        r3 = r7.z;	 Catch:{ Exception -> 0x007a, all -> 0x008a }
        r3.lock();	 Catch:{ Exception -> 0x007a, all -> 0x008a }
        r0 = r7.a(r1);	 Catch:{ Exception -> 0x007a, all -> 0x008a }
        if (r0 == 0) goto L_0x0052;
    L_0x003c:
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x007a, all -> 0x009d }
        if (r1 == 0) goto L_0x0052;
    L_0x0042:
        r1 = new com.miui.analytics.internal.LogEvent;	 Catch:{ Exception -> 0x007a, all -> 0x009d }
        r3 = "analyticsv2.db";
        r1.<init>(r0, r3);	 Catch:{ Exception -> 0x007a, all -> 0x009d }
        r2.add(r1);	 Catch:{ Exception -> 0x007a, all -> 0x009d }
        r1 = r0.moveToNext();	 Catch:{ Exception -> 0x007a, all -> 0x009d }
        if (r1 != 0) goto L_0x0042;
    L_0x0052:
        if (r0 == 0) goto L_0x0057;
    L_0x0054:
        r0.close();
    L_0x0057:
        r0 = r7.z;
        if (r0 == 0) goto L_0x0060;
    L_0x005b:
        r0 = r7.z;
        r0.unlock();
    L_0x0060:
        r0 = "AnalyticsDBV2";
        r1 = "queryPollingEventNetAllFromEventDBV2 %d events from db. ";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = r2.size();
        r5 = java.lang.Integer.valueOf(r5);
        r3[r4] = r5;
        r1 = java.lang.String.format(r1, r3);
        com.miui.analytics.internal.util.o.a(r0, r1);
        return r2;
    L_0x007a:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0080;
    L_0x007d:
        r0.close();
    L_0x0080:
        r0 = r7.z;
        if (r0 == 0) goto L_0x0060;
    L_0x0084:
        r0 = r7.z;
        r0.unlock();
        goto L_0x0060;
    L_0x008a:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x008e:
        if (r1 == 0) goto L_0x0093;
    L_0x0090:
        r1.close();
    L_0x0093:
        r1 = r7.z;
        if (r1 == 0) goto L_0x009c;
    L_0x0097:
        r1 = r7.z;
        r1.unlock();
    L_0x009c:
        throw r0;
    L_0x009d:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x008e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.b.b(int):java.util.List<com.miui.analytics.internal.LogEvent>");
    }

    public long e() {
        Throwable e;
        String str = "select count(*) from analyticsv2";
        Cursor cursor = null;
        Cursor a;
        try {
            this.z.lock();
            a = a("select count(*) from analyticsv2");
            if (a != null) {
                try {
                    if (a.moveToFirst()) {
                        o.a(q, "db count is " + a.getLong(0));
                        long j = a.getLong(0);
                        if (a != null) {
                            a.close();
                        }
                        if (this.z == null) {
                            return j;
                        }
                        this.z.unlock();
                        return j;
                    }
                } catch (Exception e2) {
                    e = e2;
                    cursor = a;
                    try {
                        Log.e(o.a(q), "getCount e", e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (this.z != null) {
                            this.z.unlock();
                        }
                        return 0;
                    } catch (Throwable th) {
                        e = th;
                        a = cursor;
                        if (a != null) {
                            a.close();
                        }
                        if (this.z != null) {
                            this.z.unlock();
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    if (a != null) {
                        a.close();
                    }
                    if (this.z != null) {
                        this.z.unlock();
                    }
                    throw e;
                }
            }
            if (a != null) {
                a.close();
            }
            if (this.z != null) {
                this.z.unlock();
            }
        } catch (Exception e3) {
            e = e3;
            Log.e(o.a(q), "getCount e", e);
            if (cursor != null) {
                cursor.close();
            }
            if (this.z != null) {
                this.z.unlock();
            }
            return 0;
        } catch (Throwable th3) {
            e = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            if (this.z != null) {
                this.z.unlock();
            }
            throw e;
        }
        return 0;
    }

    public String f() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase != null) {
            return readableDatabase.getPath();
        }
        return null;
    }
}
