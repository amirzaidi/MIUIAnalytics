package com.miui.analytics.internal;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.analytics.internal.b.a;
import com.miui.analytics.internal.b.b;
import com.miui.analytics.internal.service.i;
import com.miui.analytics.internal.util.g;

public class LogEvent {
    private long a = 0;
    protected String b = "";
    protected long c;
    protected String d = "";
    protected String e = "";
    protected String f = null;
    protected int g = LogType.TYPE_EVENT.a();
    protected int h = 0;
    protected int i = IdType.TYPE_DEFAULT.a();
    protected long j = 0;
    protected String k = null;
    private String l = "";
    private String m = "";

    public enum IdType {
        TYPE_DEFAULT(0),
        TYPE_IMEI(1),
        TYPE_MAC(2),
        TYPE_ANDROID_ID(3),
        TYPE_AAID(4),
        TYPE_GAID(5),
        TYPE_GUID(6);
        
        private int h;

        private IdType(int i) {
            this.h = 0;
            this.h = i;
        }

        public static IdType a(int i) {
            switch (i) {
                case 0:
                    return TYPE_DEFAULT;
                case 1:
                    return TYPE_IMEI;
                case 2:
                    return TYPE_MAC;
                case 3:
                    return TYPE_ANDROID_ID;
                case 4:
                    return TYPE_AAID;
                case 5:
                    return TYPE_GAID;
                case 6:
                    return TYPE_GUID;
                default:
                    return TYPE_DEFAULT;
            }
        }

        public int a() {
            return this.h;
        }
    }

    public enum LogType {
        TYPE_EVENT(0),
        TYPE_AD(1);
        
        private int c;

        private LogType(int i) {
            this.c = 0;
            this.c = i;
        }

        public static LogType a(int i) {
            switch (i) {
                case 1:
                    return TYPE_AD;
                default:
                    return TYPE_EVENT;
            }
        }

        public int a() {
            return this.c;
        }
    }

    public LogEvent(Cursor cursor) {
        a(cursor, a.a);
    }

    public LogEvent(Cursor cursor, String str) {
        a(cursor, str);
    }

    private void a(Cursor cursor, String str) {
        if (TextUtils.isEmpty(str) || str.equals(a.a)) {
            this.b = d(cursor, "config_key");
            this.a = b(cursor, "_id");
            this.c = b(cursor, "event_time");
            this.m = d(cursor, "session_id");
            this.l = d(cursor, "app_id");
            this.d = d(cursor, "body");
            this.e = d(cursor, "sn");
            this.f = d(cursor, "header");
            this.h = c(cursor, "send_count");
            this.g = c(cursor, "event_type");
            this.i = c(cursor, "id_type");
            if (!TextUtils.isEmpty(this.f)) {
                this.j = (long) this.f.getBytes().length;
            }
            if (!TextUtils.isEmpty(this.d)) {
                this.j += (long) this.d.getBytes().length;
            }
        } else if (str.equals(b.a)) {
            this.b = d(cursor, "config_key");
            this.a = b(cursor, "_id");
            this.c = b(cursor, "event_time");
            this.m = d(cursor, "session_id");
            this.l = d(cursor, "app_id");
            this.d = d(cursor, "body");
            this.e = d(cursor, "sn");
            this.f = d(cursor, "header");
            this.h = c(cursor, "send_count");
            this.g = c(cursor, "event_type");
            this.i = c(cursor, "id_type");
            if (!TextUtils.isEmpty(this.f)) {
                this.j = (long) this.f.getBytes().length;
            }
            if (!TextUtils.isEmpty(this.d)) {
                this.j += (long) this.d.getBytes().length;
            }
        }
    }

    public LogEvent(Context context, String str, String str2, String str3) {
        a(context, str, str2, str3, LogType.TYPE_EVENT.a(), IdType.TYPE_DEFAULT.a());
    }

    public LogEvent(Context context, String str, String str2, String str3, int i) {
        a(context, str, str2, str3, i, IdType.TYPE_DEFAULT.a());
    }

    public LogEvent(Context context, String str, String str2, String str3, int i, int i2) {
        a(context, str, str2, str3, i, i2);
    }

    private void a(Context context, String str, String str2, String str3, int i, int i2) {
        this.b = str2;
        this.c = System.currentTimeMillis();
        this.l = str;
        this.d = str3;
        this.g = i;
        this.e = j.a(a());
        this.i = i2;
        this.f = i.a(context, Boolean.valueOf(g.a(context).a(str, str2)), this.g, this.i, this.l).toString();
        if (!TextUtils.isEmpty(this.f)) {
            this.j = (long) this.f.getBytes().length;
        }
        if (!TextUtils.isEmpty(this.d)) {
            this.j += (long) this.d.getBytes().length;
        }
    }

    private long b(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0 || columnIndex >= cursor.getColumnCount()) {
            return 0;
        }
        return cursor.getLong(columnIndex);
    }

    private int c(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0 || columnIndex >= cursor.getColumnCount()) {
            return 0;
        }
        return cursor.getInt(columnIndex);
    }

    private String d(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0 || columnIndex >= cursor.getColumnCount()) {
            return "";
        }
        return cursor.getString(columnIndex);
    }

    private String a() {
        return this.l + ":" + this.b;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.l;
    }

    public String d() {
        return this.m;
    }

    public void a(String str) {
        this.m = str;
    }

    public long e() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public String f() {
        return this.k;
    }

    public void b(String str) {
        this.k = str;
    }

    public String g() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public String h() {
        return this.e;
    }

    public long i() {
        return this.a;
    }

    public void b(long j) {
        this.a = j;
    }

    public String j() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public int k() {
        return this.h;
    }

    public void l() {
        this.h++;
    }

    public boolean m() {
        return (TextUtils.isEmpty(this.l) || TextUtils.isEmpty(this.b)) ? false : true;
    }

    public String toString() {
        return "configKey = " + this.b + ", appId = " + this.l + ", sessionId = " + this.m + ", logSN = " + this.e + ", eventTime = " + this.c + ", mSendCount = " + this.h;
    }

    public long n() {
        return this.j;
    }

    public void a(int i) {
        this.g = i;
    }

    public int o() {
        return this.g;
    }

    public int p() {
        return this.i;
    }
}
