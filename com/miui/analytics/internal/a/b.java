package com.miui.analytics.internal.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import com.miui.analytics.internal.util.ad;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.o;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class b extends SQLiteOpenHelper {
    public static final String a = "status";
    public static final String b = "url";
    public static final String c = "_id";
    public static final String d = "event_time";
    public static final String e = "send_count";
    public static final String f = "app_id";
    private static final String g = "AdEventDB";
    private static final String h = "requests.db";
    private static final String i = "request";
    private static final int j = 1;
    private static final int k = 3;
    private static final String l = "create table %s(_id INTEGER PRIMARY KEY,url TEXT,event_time INT8,status INT)";
    private static final String m = "drop table if exists %s";
    private static final String n = "delete from request where _id = %s";
    private static final String o = "insert into %s values(null, ?, ?, ?, ?, ?)";
    private static final String p = "delete from request where event_time < ";
    private ReentrantReadWriteLock q;
    private Lock r;
    private Lock s;
    private Context t;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0032 in list [B:5:0x002d]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r3 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "delete from request where send_count > ";
        r0 = r0.append(r1);
        r1 = r3.t;
        r1 = com.miui.analytics.internal.util.g.a(r1);
        r1 = r1.a();
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = r3.s;	 Catch:{ Exception -> 0x0033, all -> 0x0049 }
        r1.lock();	 Catch:{ Exception -> 0x0033, all -> 0x0049 }
        r1 = r3.getWritableDatabase();	 Catch:{ Exception -> 0x0033, all -> 0x0049 }
        r1.execSQL(r0);	 Catch:{ Exception -> 0x0033, all -> 0x0049 }
        r0 = r3.s;
        if (r0 == 0) goto L_0x0032;
    L_0x002d:
        r0 = r3.s;
        r0.unlock();
    L_0x0032:
        return;
    L_0x0033:
        r0 = move-exception;
        r1 = "AdEventDB";	 Catch:{ Exception -> 0x0033, all -> 0x0049 }
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x0033, all -> 0x0049 }
        r2 = "deleteEventRetryExpired e";	 Catch:{ Exception -> 0x0033, all -> 0x0049 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x0033, all -> 0x0049 }
        r0 = r3.s;
        if (r0 == 0) goto L_0x0032;
    L_0x0043:
        r0 = r3.s;
        r0.unlock();
        goto L_0x0032;
    L_0x0049:
        r0 = move-exception;
        r1 = r3.s;
        if (r1 == 0) goto L_0x0053;
    L_0x004e:
        r1 = r3.s;
        r1.unlock();
    L_0x0053:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.a.b.c():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(java.util.List<com.miui.analytics.internal.a.e> r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x000a in list [B:15:0x0060]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r7 = this;
        r0 = "update %s set %s = %s + 1 where %s = %s";
        if (r8 == 0) goto L_0x000a;
    L_0x0004:
        r0 = r8.size();
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r0 = r7.s;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r0.lock();	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r1 = r7.getWritableDatabase();	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r2 = r8.iterator();	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
    L_0x0018:
        r0 = r2.hasNext();	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        if (r0 == 0) goto L_0x0066;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
    L_0x001e:
        r0 = r2.next();	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r0 = (com.miui.analytics.internal.a.e) r0;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r3 = "update %s set %s = %s + 1 where %s = %s";	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r4 = 5;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r5 = 0;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r6 = "request";	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r5 = 1;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r6 = "send_count";	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r5 = 2;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r6 = "send_count";	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r5 = 3;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r6 = "_id";	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r5 = 4;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r0 = r0.a();	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r4[r5] = r0;	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r0 = java.lang.String.format(r3, r4);	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r1.execSQL(r0);	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        goto L_0x0018;
    L_0x0050:
        r0 = move-exception;
        r1 = "AdEventDB";	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r2 = "increaseRetryCount e";	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x0050, all -> 0x0070 }
        r0 = r7.s;
        if (r0 == 0) goto L_0x000a;
    L_0x0060:
        r0 = r7.s;
        r0.unlock();
        goto L_0x000a;
    L_0x0066:
        r0 = r7.s;
        if (r0 == 0) goto L_0x000a;
    L_0x006a:
        r0 = r7.s;
        r0.unlock();
        goto L_0x000a;
    L_0x0070:
        r0 = move-exception;
        r1 = r7.s;
        if (r1 == 0) goto L_0x007a;
    L_0x0075:
        r1 = r7.s;
        r1.unlock();
    L_0x007a:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.a.b.c(java.util.List):void");
    }

    public b(Context context) {
        super(context, h, null, 3);
        this.t = c.a(context);
        e();
    }

    private void e() {
        if (this.q == null) {
            this.q = new ReentrantReadWriteLock();
            this.r = this.q.readLock();
            this.s = this.q.writeLock();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Log.i(o.a(g), "onCreate, version:3");
        e();
        a(sQLiteDatabase);
        onUpgrade(sQLiteDatabase, 1, 3);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d(o.a(g), String.format("drop & create table when upgrade, db:%s, oldVersion:%d, newVersion:%d", new Object[]{h, Integer.valueOf(i), Integer.valueOf(i2)}));
        for (int i3 = i + 1; i3 <= i2; i3++) {
            String format;
            switch (i3) {
                case 2:
                    format = String.format("alter table %s add column %s %s", new Object[]{i, "send_count", " INT DEFAULT(0)"});
                    o.a(g, "onUpgrade version " + i3 + ": " + format);
                    a(sQLiteDatabase, format);
                    break;
                case 3:
                    format = String.format("alter table %s add column %s %s", new Object[]{i, "app_id", " TEXT"});
                    o.a(g, "onUpgrade version " + i3 + ": " + format);
                    a(sQLiteDatabase, format);
                    break;
                default:
                    break;
            }
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d(o.a(g), String.format("drop & create table when downgrade, db:%s, oldVersion:%d, newVersion:%d", new Object[]{h, Integer.valueOf(i), Integer.valueOf(i2)}));
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(l, new Object[]{i}));
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(m, new Object[]{i}));
    }

    public void a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            this.s.lock();
            sQLiteDatabase.execSQL(str);
        } catch (Throwable e) {
            Log.e(o.a(g), "exeSQL e", e);
        } finally {
            this.s.unlock();
        }
    }

    public void a(String str, Object[] objArr) {
        try {
            this.s.lock();
            getWritableDatabase().execSQL(str, objArr);
        } catch (Throwable e) {
            Log.e(o.a(g), "exeSQL e", e);
        } finally {
            this.s.unlock();
        }
    }

    private Cursor a(String str) {
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().rawQuery(str, null);
        } catch (Throwable e) {
            Log.e(o.a(g), "rawQueryLocked e", e);
        }
        return cursor;
    }

    private Cursor a(String str, String[] strArr) {
        try {
            return getReadableDatabase().rawQuery(str, strArr);
        } catch (Throwable e) {
            Log.e(o.a(g), "rawQueryLocked e", e);
            return null;
        }
    }

    public boolean a(e eVar) {
        try {
            this.s.lock();
            getWritableDatabase().execSQL(String.format(o, new Object[]{i}), new Object[]{eVar.b(), Long.valueOf(eVar.c()), Integer.valueOf(eVar.e()), Integer.valueOf(eVar.a), eVar.d()});
        } catch (Throwable e) {
            Log.e(o.a(g), "insert e", e);
        } finally {
            this.s.unlock();
        }
        return true;
    }

    public boolean a(List<e> list) {
        ad.a("InsertEvents");
        SQLiteDatabase sQLiteDatabase = null;
        try {
            this.s.lock();
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            for (e eVar : list) {
                SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(String.format(o, new Object[]{i}));
                compileStatement.bindString(1, eVar.b());
                compileStatement.bindLong(2, eVar.c());
                compileStatement.bindLong(3, (long) eVar.e());
                compileStatement.bindLong(4, (long) eVar.a);
                compileStatement.bindString(5, eVar.d());
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
            this.s.unlock();
            ad.a();
        } catch (Throwable e2) {
            Log.e(o.a(g), "insert e", e2);
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e3) {
                }
            }
            this.s.unlock();
            ad.a();
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e4) {
                }
            }
            this.s.unlock();
            ad.a();
        }
        return true;
    }

    public boolean b(List<e> list) {
        ad.a("DeleteEvents");
        o.a(g, "delete ");
        SQLiteDatabase sQLiteDatabase = null;
        try {
            this.s.lock();
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            for (e eVar : list) {
                sQLiteDatabase.execSQL(String.format(n, new Object[]{eVar.a() + ""}));
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e) {
                }
            }
            this.s.unlock();
            ad.a();
        } catch (Throwable e2) {
            Log.e(o.a(g), "delete e", e2);
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e3) {
                }
            }
            this.s.unlock();
            ad.a();
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e4) {
                }
            }
            this.s.unlock();
            ad.a();
        }
        return true;
    }

    public boolean a(long j) {
        try {
            this.s.lock();
            getWritableDatabase().execSQL(p + j);
            this.s.unlock();
            return true;
        } catch (Throwable e) {
            Log.e(o.a(g), "deleteByTime e", e);
            this.s.unlock();
            return false;
        } catch (Throwable e2) {
            this.s.unlock();
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.miui.analytics.internal.a.e> a() {
        /*
        r7 = this;
        r0 = "select * from request";
        r1 = new java.util.ArrayList;
        r1.<init>();
        r0 = 0;
        r2 = r7.r;	 Catch:{ Exception -> 0x004d, all -> 0x0059 }
        r2.lock();	 Catch:{ Exception -> 0x004d, all -> 0x0059 }
        r2 = "select * from request";
        r0 = r7.a(r2);	 Catch:{ Exception -> 0x004d, all -> 0x0059 }
        if (r0 == 0) goto L_0x0029;
    L_0x0015:
        r2 = r0.moveToFirst();	 Catch:{ Exception -> 0x004d, all -> 0x0068 }
        if (r2 == 0) goto L_0x0029;
    L_0x001b:
        r2 = new com.miui.analytics.internal.a.e;	 Catch:{ Exception -> 0x004d, all -> 0x0068 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x004d, all -> 0x0068 }
        r1.add(r2);	 Catch:{ Exception -> 0x004d, all -> 0x0068 }
        r2 = r0.moveToNext();	 Catch:{ Exception -> 0x004d, all -> 0x0068 }
        if (r2 != 0) goto L_0x001b;
    L_0x0029:
        if (r0 == 0) goto L_0x002e;
    L_0x002b:
        r0.close();
    L_0x002e:
        r0 = r7.r;
        r0.unlock();
    L_0x0033:
        r0 = "AdEventDB";
        r2 = "queryEvents %d events from db. ";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = r1.size();
        r5 = java.lang.Integer.valueOf(r5);
        r3[r4] = r5;
        r2 = java.lang.String.format(r2, r3);
        com.miui.analytics.internal.util.o.a(r0, r2);
        return r1;
    L_0x004d:
        r2 = move-exception;
        if (r0 == 0) goto L_0x0053;
    L_0x0050:
        r0.close();
    L_0x0053:
        r0 = r7.r;
        r0.unlock();
        goto L_0x0033;
    L_0x0059:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x005d:
        if (r1 == 0) goto L_0x0062;
    L_0x005f:
        r1.close();
    L_0x0062:
        r1 = r7.r;
        r1.unlock();
        throw r0;
    L_0x0068:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x005d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.a.b.a():java.util.List<com.miui.analytics.internal.a.e>");
    }

    public long b() {
        Throwable e;
        String str = "select count(*) from request";
        Cursor cursor = null;
        Cursor a;
        try {
            this.r.lock();
            a = a("select count(*) from request");
            if (a != null) {
                try {
                    if (a.moveToFirst()) {
                        o.a(g, "db count is " + a.getLong(0));
                        long j = a.getLong(0);
                        if (a != null) {
                            a.close();
                        }
                        this.r.unlock();
                        return j;
                    }
                } catch (Exception e2) {
                    e = e2;
                    cursor = a;
                    try {
                        Log.e(o.a(g), "getCount e", e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        this.r.unlock();
                        return 0;
                    } catch (Throwable th) {
                        e = th;
                        a = cursor;
                        if (a != null) {
                            a.close();
                        }
                        this.r.unlock();
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    if (a != null) {
                        a.close();
                    }
                    this.r.unlock();
                    throw e;
                }
            }
            if (a != null) {
                a.close();
            }
            this.r.unlock();
        } catch (Exception e3) {
            e = e3;
            Log.e(o.a(g), "getCount e", e);
            if (cursor != null) {
                cursor.close();
            }
            this.r.unlock();
            return 0;
        } catch (Throwable th3) {
            e = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            this.r.unlock();
            throw e;
        }
        return 0;
    }

    public String d() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase != null) {
            return readableDatabase.getPath();
        }
        return null;
    }
}
