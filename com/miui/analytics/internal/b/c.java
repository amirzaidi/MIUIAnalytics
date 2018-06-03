package com.miui.analytics.internal.b;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.List;

public class c extends SQLiteOpenHelper {
    private static final String a = "AppenderManagerDB";
    private static final String b = "appenderManager.db";
    private static final String c = "appenderManager";
    private static final String d = "_package_name";
    private static final String e = "value";
    private static final String f = "create table %s(_package_name TEXT PRIMARY KEY,value TEXT)";
    private static final String g = "drop table if exists %s";
    private static final String h = "delete from appenderManager";
    private static final int i = 1;
    private static final int j = 1;
    private static volatile c k;

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
        r0 = "delete from appenderManager";	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        r1.execSQL(r0);	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        if (r1 == 0) goto L_0x0015;
    L_0x0012:
        r1.endTransaction();
    L_0x0015:
        return;
    L_0x0016:
        r0 = move-exception;
        r2 = "AppenderManagerDB";	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        r2 = com.miui.analytics.internal.util.o.a(r2);	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
        r3 = "delete exception";	 Catch:{ Exception -> 0x0016, all -> 0x0028 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.c.a():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r5, java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0025 in list [B:4:0x0022]
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
        r1 = r4.getWritableDatabase();	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r1.beginTransaction();	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r0 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r0.<init>();	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r2 = "_package_name";	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r0.put(r2, r5);	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r2 = "value";	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r0.put(r2, r6);	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r2 = "appenderManager";	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r3 = 0;	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r1.replace(r2, r3, r0);	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        if (r1 == 0) goto L_0x0025;
    L_0x0022:
        r1.endTransaction();
    L_0x0025:
        return;
    L_0x0026:
        r0 = move-exception;
        r2 = "AppenderManagerDB";	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r2 = com.miui.analytics.internal.util.o.a(r2);	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        r3 = "insert e";	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ Exception -> 0x0026, all -> 0x0038 }
        if (r1 == 0) goto L_0x0025;
    L_0x0034:
        r1.endTransaction();
        goto L_0x0025;
    L_0x0038:
        r0 = move-exception;
        if (r1 == 0) goto L_0x003e;
    L_0x003b:
        r1.endTransaction();
    L_0x003e:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.c.a(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(java.lang.String r6) {
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
        r0 = "appenderManager";	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
        r2 = "_package_name = ? ";	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
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
        r2 = "AppenderManagerDB";	 Catch:{ Exception -> 0x001e, all -> 0x0030 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.c.b(java.lang.String):void");
    }

    public static c a(Context context) {
        if (k == null) {
            synchronized (c.class) {
                if (k == null) {
                    k = new c(context);
                }
            }
        }
        return k;
    }

    private c(Context context) {
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
        sQLiteDatabase.execSQL(String.format(f, new Object[]{c}));
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(g, new Object[]{c}));
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
                cursor = readableDatabase.query(c, new String[]{d, e}, "_package_name = ? ", new String[]{str}, null, null, null);
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
                String string = cursor.getString(cursor.getColumnIndexOrThrow(e));
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
                    Log.e(o.a(a), "query e", e);
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
            Log.e(o.a(a), "query e", e);
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

    public List<Pair<String, String>> b() {
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        Throwable e;
        Cursor cursor = null;
        List<Pair<String, String>> arrayList = new ArrayList();
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                readableDatabase.beginTransaction();
                cursor = readableDatabase.query(c, new String[]{d, e}, null, null, null, null, null);
                if (cursor == null || !cursor.moveToFirst()) {
                    readableDatabase.setTransactionSuccessful();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (readableDatabase != null) {
                        readableDatabase.endTransaction();
                    }
                    return arrayList;
                }
                do {
                    arrayList.add(new Pair(cursor.getString(cursor.getColumnIndexOrThrow(d)), cursor.getString(cursor.getColumnIndexOrThrow(e))));
                } while (cursor.moveToNext());
                readableDatabase.setTransactionSuccessful();
                if (cursor != null) {
                    cursor.close();
                }
                if (readableDatabase != null) {
                    readableDatabase.endTransaction();
                }
                return arrayList;
            } catch (Throwable e2) {
                th = e2;
                sQLiteDatabase = readableDatabase;
                e = th;
                try {
                    Log.e(o.a(a), "getAll e", e);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    return arrayList;
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
            sQLiteDatabase = cursor;
            Log.e(o.a(a), "getAll e", e);
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            return arrayList;
        } catch (Throwable th3) {
            e = th3;
            sQLiteDatabase = cursor;
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            throw e;
        }
    }
}
