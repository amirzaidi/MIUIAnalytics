package com.miui.analytics.internal.b;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.miui.analytics.internal.util.o;
import java.util.ArrayList;
import java.util.List;

public class i extends SQLiteOpenHelper {
    private static final String a = "StoreLocationDB";
    private static final String b = "storeLocation.db";
    private static final String c = "storeLocation";
    private static final String d = "store_id";
    private static final String e = "longitude";
    private static final String f = "latitude";
    private static final int g = 1;
    private static final int h = 1;
    private static final String i = "create table %s(store_id INTEGER PRIMARY KEY,longitude REAL, latitude REAL)";
    private static final String j = "drop table if exists %s";
    private static final String k = "delete from storeLocation where %s = %s";
    private static volatile i l;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.miui.analytics.internal.b.j r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x003d in list [B:4:0x003a]
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
        r6 = this;
        r1 = 0;
        r1 = r6.getWritableDatabase();	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r1.beginTransaction();	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r0 = new android.content.ContentValues;	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r0.<init>();	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r2 = "store_id";	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r3 = r7.a;	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r0.put(r2, r3);	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r2 = "latitude";	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r4 = r7.c;	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r3 = java.lang.Double.valueOf(r4);	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r0.put(r2, r3);	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r2 = "longitude";	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r4 = r7.b;	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r3 = java.lang.Double.valueOf(r4);	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r0.put(r2, r3);	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r2 = "storeLocation";	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r3 = "store_id";	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r1.replace(r2, r3, r0);	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        if (r1 == 0) goto L_0x003d;
    L_0x003a:
        r1.endTransaction();
    L_0x003d:
        return;
    L_0x003e:
        r0 = move-exception;
        r2 = "StoreLocationDB";	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r2 = com.miui.analytics.internal.util.o.a(r2);	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        r3 = "insertOrReplace e";	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ Exception -> 0x003e, all -> 0x0050 }
        if (r1 == 0) goto L_0x003d;
    L_0x004c:
        r1.endTransaction();
        goto L_0x003d;
    L_0x0050:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0056;
    L_0x0053:
        r1.endTransaction();
    L_0x0056:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.b.i.a(com.miui.analytics.internal.b.j):void");
    }

    private i(Context context) {
        super(context, b, null, 1);
    }

    public static i a(Context context) {
        if (l == null) {
            synchronized (i.class) {
                if (l == null) {
                    l = new i(context);
                }
            }
        }
        return l;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        onUpgrade(sQLiteDatabase, 1, 1);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        for (int i3 = i + 1; i3 <= i2; i3++) {
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b(sQLiteDatabase);
        a(sQLiteDatabase);
        onUpgrade(sQLiteDatabase, 1, i2);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(i, new Object[]{c}));
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(j, new Object[]{c}));
    }

    private Cursor a(String str) {
        Throwable e;
        SQLiteDatabase readableDatabase;
        try {
            readableDatabase = getReadableDatabase();
            try {
                readableDatabase.beginTransaction();
                Cursor rawQuery = readableDatabase.rawQuery(str, null);
                readableDatabase.setTransactionSuccessful();
                if (readableDatabase == null) {
                    return rawQuery;
                }
                readableDatabase.endTransaction();
                return rawQuery;
            } catch (Exception e2) {
                e = e2;
                try {
                    Log.e(o.a(a), "rawQuery e", e);
                    if (readableDatabase != null) {
                        readableDatabase.endTransaction();
                    }
                    return null;
                } catch (Throwable th) {
                    e = th;
                    if (readableDatabase != null) {
                        readableDatabase.endTransaction();
                    }
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            readableDatabase = null;
            Log.e(o.a(a), "rawQuery e", e);
            if (readableDatabase != null) {
                readableDatabase.endTransaction();
            }
            return null;
        } catch (Throwable th2) {
            e = th2;
            readableDatabase = null;
            if (readableDatabase != null) {
                readableDatabase.endTransaction();
            }
            throw e;
        }
    }

    public boolean a(List<j> list) {
        o.a(a, "insert storeLocationInfos " + list.size());
        try {
            for (j a : list) {
                a(a);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "insertOrReplace e", e);
        }
        return true;
    }

    public boolean b(List<j> list) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = getWritableDatabase();
            sQLiteDatabase.beginTransaction();
            for (j jVar : list) {
                sQLiteDatabase.execSQL(String.format(k, new Object[]{d, jVar.a + ""}));
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "delete e", e);
            return true;
        } finally {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        }
        return true;
    }

    public List<j> a() {
        String str = "select * from storeLocation";
        List<j> arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = a("select * from storeLocation");
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                o.a(a, String.format("queryEvents %d events from db. ", new Object[]{Integer.valueOf(arrayList.size())}));
                return arrayList;
            }
            do {
                arrayList.add(a(cursor));
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            o.a(a, String.format("queryEvents %d events from db. ", new Object[]{Integer.valueOf(arrayList.size())}));
            return arrayList;
        } catch (Throwable e) {
            Log.e(o.a(a), "queryStoreLocations e", e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private j a(Cursor cursor) {
        j jVar = new j();
        jVar.a = cursor.getInt(cursor.getColumnIndexOrThrow(d));
        jVar.b = cursor.getDouble(cursor.getColumnIndexOrThrow(e));
        jVar.c = cursor.getDouble(cursor.getColumnIndexOrThrow(f));
        return jVar;
    }

    public long b() {
        Throwable e;
        String str = "select count(*) from storeLocation";
        Cursor cursor = null;
        Cursor a;
        try {
            a = a("select count(*) from storeLocation");
            if (a != null) {
                try {
                    if (a.moveToFirst()) {
                        o.a(a, "db count is " + a.getLong(0));
                        long j = a.getLong(0);
                        if (a == null) {
                            return j;
                        }
                        a.close();
                        return j;
                    }
                } catch (Exception e2) {
                    e = e2;
                    cursor = a;
                    try {
                        Log.e(o.a(a), "getCount e", e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return 0;
                    } catch (Throwable th) {
                        e = th;
                        a = cursor;
                        if (a != null) {
                            a.close();
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    if (a != null) {
                        a.close();
                    }
                    throw e;
                }
            }
            if (a != null) {
                a.close();
            }
        } catch (Exception e3) {
            e = e3;
            Log.e(o.a(a), "getCount e", e);
            if (cursor != null) {
                cursor.close();
            }
            return 0;
        } catch (Throwable th3) {
            e = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            throw e;
        }
        return 0;
    }

    public String c() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase != null) {
            return readableDatabase.getPath();
        }
        return null;
    }
}
