package com.miui.analytics.internal.b;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.miui.analytics.internal.util.o;

public class d extends SQLiteOpenHelper {
    private static final String a = "GuidDB";
    private static final String b = "guid.db";
    private static final String c = "guid";
    private static final String d = "_app_id";
    private static final String e = "guid";
    private static final String f = "create_time";
    private static final String g = "create table %s(_app_id TEXT PRIMARY KEY,guid TEXT,create_time INT8)";
    private static final String h = "drop table if exists %s";
    private static final String i = "delete from guid";
    private static final int j = 1;
    private static final int k = 1;
    private static volatile d l;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0015 in list [B:4:0x0012]
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
        r4 = this;
        r1 = 0;
        r1 = r4.getWritableDatabase();	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        r1.beginTransaction();	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        r0 = "delete from guid";	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        r1.execSQL(r0);	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        if (r1 == 0) goto L_0x0015;
    L_0x0012:
        r1.endTransaction();
    L_0x0015:
        return;
    L_0x0016:
        r0 = move-exception;
        r2 = "GuidDB";	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        r2 = com.miui.analytics.internal.util.o.a(r2);	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        r3 = "deleteAll exception";	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        if (r1 == 0) goto L_0x0015;
    L_0x0024:
        r1.endTransaction();
        goto L_0x0015;
    L_0x0028:
        r0 = move-exception;
        if (r1 == 0) goto L_0x002e;
    L_0x002b:
        r1.endTransaction();
    L_0x002e:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.d.a():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r6, java.lang.String r7, long r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x002e in list [B:4:0x002b]
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
        r5 = this;
        r1 = 0;
        r1 = r5.getWritableDatabase();	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r1.beginTransaction();	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r0 = new android.content.ContentValues;	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r0.<init>();	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r2 = "_app_id";	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r0.put(r2, r6);	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r2 = "guid";	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r0.put(r2, r7);	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r2 = "create_time";	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r3 = java.lang.Long.valueOf(r8);	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r0.put(r2, r3);	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r2 = "guid";	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r3 = 0;	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r1.replace(r2, r3, r0);	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        if (r1 == 0) goto L_0x002e;
    L_0x002b:
        r1.endTransaction();
    L_0x002e:
        return;
    L_0x002f:
        r0 = move-exception;
        r2 = "GuidDB";	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r2 = com.miui.analytics.internal.util.o.a(r2);	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        r3 = "insertOrReplace e";	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ Exception -> 0x002f, all -> 0x0041 }
        if (r1 == 0) goto L_0x002e;
    L_0x003d:
        r1.endTransaction();
        goto L_0x002e;
    L_0x0041:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0047;
    L_0x0044:
        r1.endTransaction();
    L_0x0047:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.d.a(java.lang.String, java.lang.String, long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x001d in list [B:4:0x001a]
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
        r5 = this;
        r1 = 0;
        r1 = r5.getWritableDatabase();	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r1.beginTransaction();	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r0 = "guid";	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r2 = "_app_id = ? ";	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r3 = 1;	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r4 = 0;	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r3[r4] = r6;	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r1.delete(r0, r2, r3);	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        if (r1 == 0) goto L_0x001d;
    L_0x001a:
        r1.endTransaction();
    L_0x001d:
        return;
    L_0x001e:
        r0 = move-exception;
        r2 = "GuidDB";	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r2 = com.miui.analytics.internal.util.o.a(r2);	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r3 = "delete e";	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        if (r1 == 0) goto L_0x001d;
    L_0x002c:
        r1.endTransaction();
        goto L_0x001d;
    L_0x0030:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0036;
    L_0x0033:
        r1.endTransaction();
    L_0x0036:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.d.d(java.lang.String):void");
    }

    public static d a(Context context) {
        if (l == null) {
            synchronized (d.class) {
                if (l == null) {
                    l = new d(context);
                }
            }
        }
        return l;
    }

    private d(Context context) {
        super(context, b, null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        onUpgrade(sQLiteDatabase, 1, 1);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        for (int i3 = i + 1; i3 <= i2; i3++) {
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b(sQLiteDatabase);
        a(sQLiteDatabase);
        onUpgrade(sQLiteDatabase, 1, i2);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(g, new Object[]{"guid"}));
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(h, new Object[]{"guid"}));
    }

    public String a(String str) {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        Throwable e;
        Cursor cursor = null;
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                readableDatabase.beginTransaction();
                cursor = readableDatabase.query("guid", new String[]{d, "guid"}, "_app_id = ? ", new String[]{str}, null, null, null);
                if (cursor == null || !cursor.moveToFirst()) {
                    readableDatabase.setTransactionSuccessful();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (readableDatabase != null) {
                        readableDatabase.endTransaction();
                    }
                    return "";
                }
                String string = cursor.getString(cursor.getColumnIndexOrThrow("guid"));
                if (string == null) {
                    string = "";
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (readableDatabase == null) {
                    return string;
                }
                readableDatabase.endTransaction();
                return string;
            } catch (Throwable e2) {
                th = e2;
                sQLiteDatabase = readableDatabase;
                e = th;
                try {
                    Log.e(o.a(a), "queryGuid e", e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    return "";
                } catch (Throwable th2) {
                    e = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    throw e;
                }
            } catch (Throwable e22) {
                th = e22;
                sQLiteDatabase = readableDatabase;
                e = th;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            sQLiteDatabase = null;
            Log.e(o.a(a), "queryGuid e", e);
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            return "";
        } catch (Throwable th3) {
            e = th3;
            sQLiteDatabase = null;
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            throw e;
        }
    }

    public long b(String str) {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        Throwable e;
        Cursor cursor = null;
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                readableDatabase.beginTransaction();
                cursor = readableDatabase.query("guid", new String[]{d, f}, "_app_id = ? ", new String[]{str}, null, null, null);
                if (cursor == null || !cursor.moveToFirst()) {
                    readableDatabase.setTransactionSuccessful();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (readableDatabase != null) {
                        readableDatabase.endTransaction();
                    }
                    return 0;
                }
                long j = cursor.getLong(cursor.getColumnIndexOrThrow(f));
                if (cursor != null) {
                    cursor.close();
                }
                if (readableDatabase != null) {
                    readableDatabase.endTransaction();
                }
                return j;
            } catch (Throwable e2) {
                th = e2;
                sQLiteDatabase = readableDatabase;
                e = th;
                try {
                    Log.e(o.a(a), "queryCreateTime e", e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    return 0;
                } catch (Throwable th2) {
                    e = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    throw e;
                }
            } catch (Throwable e22) {
                th = e22;
                sQLiteDatabase = readableDatabase;
                e = th;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            sQLiteDatabase = null;
            Log.e(o.a(a), "queryCreateTime e", e);
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            return 0;
        } catch (Throwable th3) {
            e = th3;
            sQLiteDatabase = null;
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            throw e;
        }
    }

    public Object[] c(String str) {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        Throwable e;
        SQLiteDatabase sQLiteDatabase2;
        Cursor query;
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                readableDatabase.beginTransaction();
                query = readableDatabase.query("guid", new String[]{d, "guid", f}, "_app_id = ? ", new String[]{str}, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            Object[] objArr = new Object[]{query.getString(query.getColumnIndexOrThrow("guid")), Long.valueOf(query.getLong(query.getColumnIndexOrThrow(f)))};
                            if (query != null) {
                                query.close();
                            }
                            if (readableDatabase != null) {
                                readableDatabase.endTransaction();
                            }
                            return objArr;
                        }
                    } catch (Throwable e2) {
                        th = e2;
                        sQLiteDatabase = readableDatabase;
                        e = th;
                        try {
                            Log.e(o.a(a), "query e", e);
                            if (query != null) {
                                query.close();
                            }
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.endTransaction();
                            }
                            return null;
                        } catch (Throwable th2) {
                            e = th2;
                            if (query != null) {
                                query.close();
                            }
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.endTransaction();
                            }
                            throw e;
                        }
                    } catch (Throwable e22) {
                        th = e22;
                        sQLiteDatabase = readableDatabase;
                        e = th;
                        if (query != null) {
                            query.close();
                        }
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.endTransaction();
                        }
                        throw e;
                    }
                }
                readableDatabase.setTransactionSuccessful();
                if (query != null) {
                    query.close();
                }
                if (readableDatabase != null) {
                    readableDatabase.endTransaction();
                }
            } catch (Throwable e222) {
                query = null;
                sQLiteDatabase2 = readableDatabase;
                e = e222;
                sQLiteDatabase = sQLiteDatabase2;
                Log.e(o.a(a), "query e", e);
                if (query != null) {
                    query.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                return null;
            } catch (Throwable e2222) {
                query = null;
                sQLiteDatabase2 = readableDatabase;
                e = e2222;
                sQLiteDatabase = sQLiteDatabase2;
                if (query != null) {
                    query.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                }
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            sQLiteDatabase = null;
            query = null;
            Log.e(o.a(a), "query e", e);
            if (query != null) {
                query.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            return null;
        } catch (Throwable th3) {
            e = th3;
            sQLiteDatabase = null;
            query = null;
            if (query != null) {
                query.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            throw e;
        }
        return null;
    }
}
