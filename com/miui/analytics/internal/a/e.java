package com.miui.analytics.internal.a;

import android.database.Cursor;

public class e {
    public int a = 0;
    private int b;
    private String c;
    private long d;
    private int e = 0;
    private String f = "";

    public String toString() {
        return String.format("AdEventRecord{mId:%d, mCreateTime:%d, mStatus:%d, mSentCount:%d, mUrl:%s, mAppId:%s}", new Object[]{Integer.valueOf(this.b), Long.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.a), this.c, this.f});
    }

    public e(Cursor cursor) {
        this.d = a(cursor, "event_time");
        this.b = b(cursor, "_id");
        this.e = b(cursor, "status");
        this.c = c(cursor, b.b);
        this.a = b(cursor, "send_count");
        this.f = c(cursor, "app_id");
    }

    private long a(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0 || columnIndex >= cursor.getColumnCount()) {
            return 0;
        }
        return cursor.getLong(columnIndex);
    }

    private int b(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0 || columnIndex >= cursor.getColumnCount()) {
            return 0;
        }
        return cursor.getInt(columnIndex);
    }

    private String c(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0 || columnIndex >= cursor.getColumnCount()) {
            return "";
        }
        return cursor.getString(columnIndex);
    }

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public String b() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public long c() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public String d() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public int e() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }
}
