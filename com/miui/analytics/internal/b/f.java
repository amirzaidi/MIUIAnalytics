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

public class f extends SQLiteOpenHelper {
    public static final String a = "processinfo.db";
    public static final String b = "processinfo";
    public static final String c = "_id";
    public static final String d = "key_type";
    public static final String e = "action_type";
    public static final String f = "date";
    public static final String g = "success_count";
    public static final String h = "failed_count";
    private static final String i = "ProcessInfoDB";
    private static final int j = 1;
    private static final int k = 1;
    private static final String l = "create table %s(_id INTEGER PRIMARY KEY,key_type TEXT,action_type TEXT,date INT8, success_count INT, failed_count INT)";
    private static final String m = "drop table if exists %s";
    private static final String n = "delete from processinfo";
    private static final String o = "insert into %s values(null, ?, ?, ?, ?, ?)";
    private ReentrantReadWriteLock p;
    private Lock q;
    private Lock r;
    private Context s;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r9, java.lang.String r10, boolean r11) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x009f in list [B:8:0x009a]
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
        r8 = this;
        r0 = com.miui.analytics.internal.util.ac.d();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "update processinfo set success_count = success_count + 1 where key_type = ";
        r2 = r2.append(r3);
        r2 = r2.append(r9);
        r3 = " and ";
        r2 = r2.append(r3);
        r3 = "action_type";
        r2 = r2.append(r3);
        r3 = " = ";
        r2 = r2.append(r3);
        r2 = r2.append(r10);
        r3 = " and ";
        r2 = r2.append(r3);
        r3 = "date";
        r2 = r2.append(r3);
        r3 = " = ";
        r2 = r2.append(r3);
        r2 = r2.append(r0);
        r2 = r2.toString();
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "update processinfo set failed_count = failed_count + 1 where key_type = ";
        r3 = r3.append(r4);
        r3 = r3.append(r9);
        r4 = " and ";
        r3 = r3.append(r4);
        r4 = "action_type";
        r3 = r3.append(r4);
        r4 = " = ";
        r3 = r3.append(r4);
        r3 = r3.append(r10);
        r4 = " and ";
        r3 = r3.append(r4);
        r4 = "date";
        r3 = r3.append(r4);
        r4 = " = ";
        r3 = r3.append(r4);
        r3 = r3.append(r0);
        r3 = r3.toString();
        r4 = r8.r;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r4.lock();	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r4 = r8.getWritableDatabase();	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r0 = r8.b(r9, r10, r0);	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        if (r0 == 0) goto L_0x00ba;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
    L_0x0091:
        if (r11 == 0) goto L_0x00a0;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
    L_0x0093:
        r4.execSQL(r2);	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
    L_0x0096:
        r0 = r8.r;
        if (r0 == 0) goto L_0x009f;
    L_0x009a:
        r0 = r8.r;
        r0.unlock();
    L_0x009f:
        return;
    L_0x00a0:
        r4.execSQL(r3);	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        goto L_0x0096;
    L_0x00a4:
        r0 = move-exception;
        r1 = "ProcessInfoDB";	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r2 = "increaseOrInsertCount e";	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r0 = r8.r;
        if (r0 == 0) goto L_0x009f;
    L_0x00b4:
        r0 = r8.r;
        r0.unlock();
        goto L_0x009f;
    L_0x00ba:
        if (r11 == 0) goto L_0x00d4;
    L_0x00bc:
        r4 = com.miui.analytics.internal.util.ac.d();	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r6 = 1;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r7 = 0;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r1 = r8;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r2 = r9;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r3 = r10;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r1.a(r2, r3, r4, r6, r7);	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        goto L_0x0096;
    L_0x00c9:
        r0 = move-exception;
        r1 = r8.r;
        if (r1 == 0) goto L_0x00d3;
    L_0x00ce:
        r1 = r8.r;
        r1.unlock();
    L_0x00d3:
        throw r0;
    L_0x00d4:
        r4 = com.miui.analytics.internal.util.ac.d();	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r6 = 0;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r7 = 1;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r1 = r8;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r2 = r9;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r3 = r10;	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        r1.a(r2, r3, r4, r6, r7);	 Catch:{ Exception -> 0x00a4, all -> 0x00c9 }
        goto L_0x0096;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.f.a(java.lang.String, java.lang.String, boolean):void");
    }

    public f(Context context) {
        super(context, a, null, 1);
        this.s = c.a(context);
        if (this.p == null) {
            this.p = new ReentrantReadWriteLock();
            this.q = this.p.readLock();
            this.r = this.p.writeLock();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        onUpgrade(sQLiteDatabase, 1, 1);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d(o.a(i), String.format("drop & create table when upgrade, db:%s, oldVersion:%d, newVersion:%d", new Object[]{a, Integer.valueOf(i), Integer.valueOf(i2)}));
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.d(o.a(i), String.format("drop & create table when downgrade, db:%s, oldVersion:%d, newVersion:%d", new Object[]{a, Integer.valueOf(i), Integer.valueOf(i2)}));
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(l, new Object[]{b}));
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(m, new Object[]{b}));
    }

    private Cursor a(String str) {
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().rawQuery(str, null);
        } catch (Throwable e) {
            Log.e(o.a(i), "rawQuery e", e);
        }
        return cursor;
    }

    private boolean a(String str, String str2, long j, int i, int i2) {
        try {
            this.r.lock();
            getWritableDatabase().execSQL(String.format(o, new Object[]{b}), new Object[]{str, str2, Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(i2)});
            if (this.r != null) {
                this.r.unlock();
            }
        } catch (Throwable e) {
            Log.e(o.a(i), "insert e", e);
            if (this.r != null) {
                this.r.unlock();
            }
        } catch (Throwable th) {
            if (this.r != null) {
                this.r.unlock();
            }
        }
        return true;
    }

    public boolean a(String str, String str2, long j) {
        SQLiteDatabase sQLiteDatabase = null;
        String str3 = "delete from processinfo where key_type = " + str + " and " + e + " = " + str2 + " and " + f + " = " + j;
        try {
            this.r.lock();
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str3);
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e) {
                }
            }
            if (this.r != null) {
                this.r.unlock();
            }
        } catch (Throwable e2) {
            Log.e(o.a(i), "delete e", e2);
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e3) {
                }
            }
            if (this.r != null) {
                this.r.unlock();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e4) {
                }
            }
            if (this.r != null) {
                this.r.unlock();
            }
        }
        return true;
    }

    public void a() {
        String str = "delete from processinfo where date < " + ac.d();
        SQLiteDatabase sQLiteDatabase = null;
        try {
            this.r.lock();
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e) {
                }
            }
            if (this.r != null) {
                this.r.unlock();
            }
        } catch (Throwable e2) {
            Log.e(o.a(i), "deleteItemBeforeToday e", e2);
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e3) {
                }
            }
            if (this.r != null) {
                this.r.unlock();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e4) {
                }
            }
            if (this.r != null) {
                this.r.unlock();
            }
        }
    }

    private boolean b(String str, String str2, long j) {
        String str3 = "select * from processinfo where key_type = " + str + " and " + e + " = " + str2 + " and " + f + " = " + j;
        Cursor cursor = null;
        try {
            this.q.lock();
            cursor = a(str3);
            if (cursor == null || cursor.getCount() <= 0) {
                if (cursor != null) {
                    cursor.close();
                }
                if (this.q != null) {
                    this.q.unlock();
                }
                return false;
            }
            if (cursor != null) {
                cursor.close();
            }
            if (this.q == null) {
                return true;
            }
            this.q.unlock();
            return true;
        } catch (Throwable e) {
            Log.e(o.a(i), "has e", e);
            if (cursor != null) {
                cursor.close();
            }
            if (this.q != null) {
                this.q.unlock();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            if (this.q != null) {
                this.q.unlock();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONArray b() {
        /*
        r7 = this;
        r0 = com.miui.analytics.internal.util.ac.d();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "select * from processinfo where date < ";
        r2 = r2.append(r3);
        r0 = r2.append(r0);
        r1 = r0.toString();
        r0 = 0;
        r2 = new org.json.JSONArray;
        r2.<init>();
        r3 = r7.q;	 Catch:{ Exception -> 0x0096, all -> 0x00a6 }
        r3.lock();	 Catch:{ Exception -> 0x0096, all -> 0x00a6 }
        r0 = r7.a(r1);	 Catch:{ Exception -> 0x0096, all -> 0x00a6 }
        if (r0 == 0) goto L_0x0087;
    L_0x0028:
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        if (r1 == 0) goto L_0x0087;
    L_0x002e:
        r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r1.<init>();	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r3 = "t";
        r4 = "date";
        r4 = r0.getColumnIndexOrThrow(r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r4 = r0.getLong(r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r1.put(r3, r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r3 = "k";
        r4 = "key_type";
        r4 = r0.getColumnIndexOrThrow(r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r4 = r0.getString(r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r1.put(r3, r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r3 = "a";
        r4 = "action_type";
        r4 = r0.getColumnIndexOrThrow(r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r4 = r0.getString(r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r1.put(r3, r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r3 = "s";
        r4 = "success_count";
        r4 = r0.getColumnIndexOrThrow(r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r4 = r0.getInt(r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r1.put(r3, r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r3 = "f";
        r4 = "failed_count";
        r4 = r0.getColumnIndexOrThrow(r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r4 = r0.getInt(r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r1.put(r3, r4);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r2.put(r1);	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        r1 = r0.moveToNext();	 Catch:{ Exception -> 0x0096, all -> 0x00b9 }
        if (r1 != 0) goto L_0x002e;
    L_0x0087:
        if (r0 == 0) goto L_0x008c;
    L_0x0089:
        r0.close();
    L_0x008c:
        r0 = r7.q;
        if (r0 == 0) goto L_0x0095;
    L_0x0090:
        r0 = r7.q;
        r0.unlock();
    L_0x0095:
        return r2;
    L_0x0096:
        r1 = move-exception;
        if (r0 == 0) goto L_0x009c;
    L_0x0099:
        r0.close();
    L_0x009c:
        r0 = r7.q;
        if (r0 == 0) goto L_0x0095;
    L_0x00a0:
        r0 = r7.q;
        r0.unlock();
        goto L_0x0095;
    L_0x00a6:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
    L_0x00aa:
        if (r1 == 0) goto L_0x00af;
    L_0x00ac:
        r1.close();
    L_0x00af:
        r1 = r7.q;
        if (r1 == 0) goto L_0x00b8;
    L_0x00b3:
        r1 = r7.q;
        r1.unlock();
    L_0x00b8:
        throw r0;
    L_0x00b9:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x00aa;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.f.b():org.json.JSONArray");
    }

    public long c() {
        Throwable e;
        String str = "select count(*) from processinfo";
        Cursor cursor = null;
        Cursor a;
        try {
            this.q.lock();
            a = a("select count(*) from processinfo");
            if (a != null) {
                try {
                    if (a.moveToFirst()) {
                        o.a(i, "db count is " + a.getLong(0));
                        long j = a.getLong(0);
                        if (a != null) {
                            a.close();
                        }
                        if (this.q == null) {
                            return j;
                        }
                        this.q.unlock();
                        return j;
                    }
                } catch (Exception e2) {
                    e = e2;
                    cursor = a;
                    try {
                        Log.e(o.a(i), "getCount e", e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (this.q != null) {
                            this.q.unlock();
                        }
                        return 0;
                    } catch (Throwable th) {
                        e = th;
                        a = cursor;
                        if (a != null) {
                            a.close();
                        }
                        if (this.q != null) {
                            this.q.unlock();
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    if (a != null) {
                        a.close();
                    }
                    if (this.q != null) {
                        this.q.unlock();
                    }
                    throw e;
                }
            }
            if (a != null) {
                a.close();
            }
            if (this.q != null) {
                this.q.unlock();
            }
        } catch (Exception e3) {
            e = e3;
            Log.e(o.a(i), "getCount e", e);
            if (cursor != null) {
                cursor.close();
            }
            if (this.q != null) {
                this.q.unlock();
            }
            return 0;
        } catch (Throwable th3) {
            e = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            if (this.q != null) {
                this.q.unlock();
            }
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
