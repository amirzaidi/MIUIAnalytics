package com.miui.analytics.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import com.miui.analytics.internal.collection.h;
import com.miui.analytics.internal.collection.j;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.ae;
import com.miui.analytics.internal.util.af;
import com.miui.analytics.internal.util.e;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class b {
    private static final String a = "AlarmClock";
    private static final String b = "GMT+0";
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;
    private static final int f = 3;
    private static final int g = 4;
    private static final int h = 5;
    private static final int i = 7;
    private static final int j = 8;
    private static final int k = 9;
    private static final int l = 900;
    private static final int m = 10;
    private static final int n = 1000;
    private static final int o = 23;
    private static final int p = 11;
    private static final int q = 12;
    private static final int r = 13;
    private static final int s = 14;
    private static final int t = 15;
    private static final int u = 16;
    private static final int v = 60000;

    private void j(Context context) {
        Calendar instance = Calendar.getInstance();
        instance.set(p, q);
        instance.set(q, 0);
        long timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % ac.c) + ac.c);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent(context, WakeupService.class);
        intent.putExtra(WakeupService.a, WakeupService.b);
        PendingIntent service = PendingIntent.getService(context, 0, intent, 134217728);
        alarmManager.cancel(service);
        alarmManager.setInexactRepeating(1, b(timeInMillis), ac.c, service);
        o.a(a, "set alarm clock for wakeup, " + b(timeInMillis));
    }

    private void k(Context context) {
        Calendar instance = Calendar.getInstance();
        instance.set(p, 0);
        instance.set(q, 0);
        long timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % ac.c) + ac.c);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent(context, WakeupService.class);
        intent.putExtra(WakeupService.a, WakeupService.c);
        PendingIntent service = PendingIntent.getService(context, 1, intent, 134217728);
        alarmManager.cancel(service);
        alarmManager.setInexactRepeating(1, b(timeInMillis), 28800000, service);
        o.a(a, "set alarm clock for active, " + b(timeInMillis));
    }

    public static void a(Context context) {
        int w = g.w();
        int v = g.v();
        long nextLong = (((long) w) * 3600000) + ((new Random().nextLong() % ((((long) ((v + 1) - w)) * 3600000) / 2)) + ((((long) ((v + 1) - w)) * 3600000) / 2));
        Calendar instance = Calendar.getInstance();
        instance.set(p, 0);
        instance.set(q, 0);
        instance.set(r, 0);
        instance.set(s, 0);
        long timeInMillis = instance.getTimeInMillis() + nextLong;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent = new Intent(context, WakeupService.class);
        intent.putExtra(WakeupService.a, WakeupService.c);
        PendingIntent service = PendingIntent.getService(context, s, intent, 134217728);
        alarmManager.cancel(service);
        alarmManager.set(1, timeInMillis, service);
        o.a(a, "set LATER alarm clock for active, " + b(timeInMillis));
    }

    private void l(Context context) {
        if (e.d()) {
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone(b));
            instance.set(p, 0);
            instance.set(q, 0);
            long timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % ac.c) + ac.c);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, WakeupService.o);
            PendingIntent service = PendingIntent.getService(context, u, intent, 134217728);
            alarmManager.cancel(service);
            alarmManager.setInexactRepeating(1, b(timeInMillis), 28800000, service);
            o.a(a, "set alarm clock for uploadBattery, " + b(timeInMillis));
        }
    }

    private void m(Context context) {
        if (!n.e() && af.a()) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, WakeupService.d);
            if (a(context, intent, 2)) {
                o.a(a, "Exist alarm clock for recordLocation, skip");
                return;
            }
            PendingIntent service = PendingIntent.getService(context, 2, intent, 134217728);
            alarmManager.cancel(service);
            long currentTimeMillis = System.currentTimeMillis() + 60000;
            alarmManager.setInexactRepeating(1, currentTimeMillis, g.c(), service);
            o.a(a, "set alarm clock for recordLocation, " + currentTimeMillis);
        }
    }

    private void n(Context context) {
        if (!n.e() && af.a()) {
            Calendar instance = Calendar.getInstance();
            instance.set(p, 0);
            instance.set(q, 0);
            instance.set(r, 0);
            long timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % ac.c) + ac.c);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, WakeupService.e);
            PendingIntent service = PendingIntent.getService(context, 4, intent, 134217728);
            alarmManager.cancel(service);
            alarmManager.setInexactRepeating(1, b(timeInMillis), 28800000, service);
            o.a(a, "set alarm clock for uploadLocation, " + b(timeInMillis));
        }
    }

    @TargetApi(19)
    private void o(Context context) {
        if (!g.D()) {
            o.a(a, "Upload usage V1 is disabled by remote.");
        } else if (g.c(context)) {
            PendingIntent service;
            long j;
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, "usage");
            if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT <= 22) {
                service = PendingIntent.getService(context, 3, intent, 268435456);
            } else if (a(context, intent, 3)) {
                o.a(a, "setUploadUsageAlarm, Exist alarm clock for usage, skip");
                return;
            } else {
                service = PendingIntent.getService(context, 3, intent, 134217728);
            }
            alarmManager.cancel(service);
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone(b));
            long timeInMillis;
            if (VERSION.SDK_INT <= 22) {
                instance.set(p, 23);
                instance.set(q, 0);
                timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % 1200000) + g.d);
                if (VERSION.SDK_INT >= 19) {
                    alarmManager.setExact(0, b(timeInMillis), service);
                } else {
                    alarmManager.setRepeating(0, b(timeInMillis), ac.b, service);
                }
                j = timeInMillis;
            } else {
                instance.set(p, 0);
                instance.set(q, 0);
                timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % 21600000) + 21600000);
                alarmManager.setInexactRepeating(1, b(timeInMillis), ac.b, service);
                j = timeInMillis;
            }
            o.a(a, "set alarm clock for usage " + b(j));
        }
    }

    @TargetApi(19)
    public static void b(Context context) {
        if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT <= 22) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, "usage");
            PendingIntent service = PendingIntent.getService(context, 3, intent, 268435456);
            alarmManager.cancel(service);
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone(b));
            instance.add(h, 1);
            instance.set(p, 23);
            instance.set(q, 0);
            long timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % 1200000) + g.d);
            alarmManager.setExact(0, b(timeInMillis), service);
            o.a(a, "set next day alarm" + b(timeInMillis));
        }
    }

    private int[] a(int[] iArr) {
        Object obj = null;
        if (VERSION.SDK_INT < 19 || VERSION.SDK_INT > 22) {
            return iArr;
        }
        for (int i : iArr) {
            if (i == 23) {
                obj = 1;
            }
        }
        if (obj != null) {
            return iArr;
        }
        iArr = Arrays.copyOf(iArr, iArr.length + 1);
        iArr[iArr.length - 1] = 23;
        return iArr;
    }

    @TargetApi(19)
    private void p(Context context) {
        try {
            if (g.c(context)) {
                AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
                Intent intent = new Intent(context, WakeupService.class);
                intent.putExtra(WakeupService.a, WakeupService.g);
                Calendar instance = Calendar.getInstance(TimeZone.getTimeZone(b));
                int[] l = g.l();
                PendingIntent service;
                long nextLong;
                if (l == null || l.length <= 0) {
                    o.a(a, "usagev2 upload times is empty");
                    if (a(context, intent, m)) {
                        o.a(a, "setUploadUsageAlarmV2, Exist alarm clock for usagev2, skip");
                        return;
                    }
                    service = PendingIntent.getService(context, m, intent, 134217728);
                    alarmManager.cancel(service);
                    if (VERSION.SDK_INT <= 22) {
                        instance.set(p, 23);
                        instance.set(q, 0);
                        nextLong = ((new Random().nextLong() % 1200000) + g.d) + instance.getTimeInMillis();
                        if (VERSION.SDK_INT >= 19) {
                            alarmManager.setExact(0, b(nextLong), service);
                            return;
                        } else {
                            alarmManager.setRepeating(0, b(nextLong), ac.b, service);
                            return;
                        }
                    }
                    instance.set(p, 0);
                    instance.set(q, 0);
                    alarmManager.setInexactRepeating(1, b(((new Random().nextLong() % 21600000) + 21600000) + instance.getTimeInMillis()), ac.b, service);
                    return;
                }
                for (int i : a(l)) {
                    int i2 = i + 1000;
                    if (a(context, intent, i2)) {
                        o.a(a, "setUploadUsageAlarmV2, Exist alarm clock for usagev2, skip time" + i);
                    } else {
                        service = PendingIntent.getService(context, i2, intent, 134217728);
                        alarmManager.cancel(service);
                        instance.set(p, i);
                        instance.set(q, 0);
                        nextLong = ((new Random().nextLong() % 3600000) + 3600000) + instance.getTimeInMillis();
                        if (i != 23 || VERSION.SDK_INT < 19 || VERSION.SDK_INT > 22) {
                            alarmManager.setRepeating(1, nextLong, ac.b, service);
                        } else {
                            alarmManager.setExact(0, nextLong, service);
                            o.a(a, "4.4 <= 安卓版本 <= 5.1,需要添加一个23点的必须采集的闹钟");
                        }
                        o.a(a, "set alarm clock for usagev2 " + new Date(nextLong).toGMTString() + "，time:" + i);
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "setUploadUsageAlarmV2 e", e);
        }
    }

    @TargetApi(19)
    public static void c(Context context) {
        try {
            if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT <= 22) {
                AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
                Intent intent = new Intent(context, WakeupService.class);
                intent.putExtra(WakeupService.a, WakeupService.g);
                if (a(context, intent, 1023)) {
                    o.a(a, "setNextDayExactAlarmToUploadUsageV2, Exist alarm clock for usagev2, skip");
                    return;
                }
                PendingIntent service = PendingIntent.getService(context, 1023, intent, 268435456);
                alarmManager.cancel(service);
                Calendar instance = Calendar.getInstance(TimeZone.getTimeZone(b));
                instance.add(h, 1);
                instance.set(p, 23);
                instance.set(q, 0);
                long timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % 1200000) + g.d);
                alarmManager.setExact(0, b(timeInMillis), service);
                o.a(a, "setNextDayExactAlarmToUploadUsageV2 next day alarm" + b(timeInMillis));
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "setNextDayExactAlarmToUploadUsageV2 e", e);
        }
    }

    private void q(Context context) {
        if (g.c(context) && VERSION.SDK_INT >= 23) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, WakeupService.i);
            int[] l = g.l();
            PendingIntent service;
            Calendar instance;
            if (l == null || l.length == 0) {
                o.a(a, "installationv2 upload times is empty");
                if (a(context, intent, k)) {
                    o.a(a, "Exist alarm clock for installationv2, skip");
                    return;
                }
                service = PendingIntent.getService(context, k, intent, 134217728);
                alarmManager.cancel(service);
                instance = Calendar.getInstance(TimeZone.getTimeZone(b));
                instance.set(p, 23);
                instance.set(q, 0);
                long timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % 1200000) + g.d);
                alarmManager.setRepeating(0, b(timeInMillis), ac.b, service);
                o.a(a, "set alarm clock for installationv2, " + b(timeInMillis));
                return;
            }
            for (int i : l) {
                int i2 = i + l;
                if (a(context, intent, i2)) {
                    o.a(a, "Exist alarm clock for installationv2, skip " + i);
                } else {
                    service = PendingIntent.getService(context, i2, intent, 134217728);
                    alarmManager.cancel(service);
                    instance = Calendar.getInstance(TimeZone.getTimeZone(b));
                    instance.set(p, i);
                    instance.set(q, 0);
                    long nextLong = ((new Random().nextLong() % 1200000) + g.d) + instance.getTimeInMillis();
                    alarmManager.setRepeating(1, nextLong, ac.b, service);
                    o.a(a, "set alarm clock for installationv2, " + nextLong + ",time:" + i);
                }
            }
        }
    }

    private void r(Context context) {
        if (!g.D()) {
            o.a(a, "Upload usage V1 is disabled by remote.");
        } else if (g.c(context) && VERSION.SDK_INT >= 23) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, WakeupService.h);
            if (a(context, intent, h)) {
                o.a(a, "Exist alarm clock for installation, skip");
                return;
            }
            PendingIntent service = PendingIntent.getService(context, h, intent, 134217728);
            alarmManager.cancel(service);
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone(b));
            instance.set(p, 23);
            instance.set(q, 0);
            long timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % 1200000) + g.d);
            alarmManager.setRepeating(0, b(timeInMillis), ac.b, service);
            o.a(a, "set alarm clock for installation, " + b(timeInMillis));
        }
    }

    private void s(final Context context) {
        new AsyncTask<String, String, String>(this) {
            final /* synthetic */ b b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((String) obj);
            }

            protected String a(String... strArr) {
                j.a(context).a(g.b(context));
                j.a(context).b(g.j());
                return null;
            }

            protected void a(String str) {
                super.onPostExecute(str);
                if (j.a(context).e()) {
                    Calendar instance = Calendar.getInstance();
                    long timeInMillis = instance.getTimeInMillis() + j.a(context).d();
                    AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
                    Intent intent = new Intent(context, WakeupService.class);
                    intent.putExtra(WakeupService.a, WakeupService.j);
                    PendingIntent service = PendingIntent.getService(context, b.i, intent, 134217728);
                    alarmManager.cancel(service);
                    alarmManager.setRepeating(1, b.b(timeInMillis), j.a(context).d(), service);
                    o.a(b.a, String.format("set alarm clock for %s ,time: %d, intervalTime: %d ", new Object[]{WakeupService.j, Long.valueOf(b.b(timeInMillis)), Long.valueOf(j.a(context).d())}));
                }
            }
        }.execute(new String[0]);
    }

    public void d(Context context) {
        if (n.b() || n.c()) {
            Calendar instance = Calendar.getInstance();
            instance.set(p, 0);
            instance.set(q, 0);
            long timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % ac.c) + ac.c);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, WakeupService.k);
            PendingIntent service = PendingIntent.getService(context, j, intent, 134217728);
            alarmManager.cancel(service);
            alarmManager.setRepeating(0, b(timeInMillis), ac.b, service);
            o.a(a, "set alarm clock for net flow at " + b(timeInMillis));
        }
    }

    public void e(Context context) {
        if (h.a(context).a(false)) {
            Calendar instance = Calendar.getInstance();
            instance.set(p, 0);
            instance.set(q, 0);
            long nextLong = ((new Random().nextLong() % ac.c) + ac.c) + instance.getTimeInMillis();
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, WakeupService.l);
            PendingIntent service = PendingIntent.getService(context, p, intent, 134217728);
            alarmManager.cancel(service);
            alarmManager.setRepeating(0, nextLong, ac.b, service);
            o.a(a, "set alarm clock for update store info at " + nextLong);
        }
    }

    public void f(Context context) {
        if (h.a(context).a(false)) {
            Calendar instance = Calendar.getInstance();
            instance.set(p, 0);
            instance.set(q, 0);
            long timeInMillis = instance.getTimeInMillis() + ((new Random().nextLong() % ac.c) + ac.c);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, WakeupService.m);
            PendingIntent service = PendingIntent.getService(context, q, intent, 134217728);
            alarmManager.cancel(service);
            alarmManager.setInexactRepeating(1, b(timeInMillis), ac.c, service);
            o.a(a, "set alarm clock for uploadNearestStores, " + b(timeInMillis));
        }
    }

    public static void g(Context context) {
        long currentTimeMillis = 10800000 + System.currentTimeMillis();
        if (ac.b(currentTimeMillis)) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, WakeupService.m);
            PendingIntent service = PendingIntent.getService(context, r, intent, 134217728);
            alarmManager.cancel(service);
            alarmManager.set(1, currentTimeMillis, service);
            o.a(a, "set alarm clock when get loc failed for uploadNearestStores, " + b(currentTimeMillis));
            return;
        }
        o.a(a, "next trigger time in tomorrow when upload stores failed ,give up set next alarm, return");
    }

    public static void h(Context context) {
        if (e.d()) {
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone(b));
            instance.set(p, 0);
            instance.set(q, 0);
            long timeInMillis = instance.getTimeInMillis();
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            Intent intent = new Intent(context, WakeupService.class);
            intent.putExtra(WakeupService.a, WakeupService.n);
            PendingIntent service = PendingIntent.getService(context, t, intent, 134217728);
            alarmManager.cancel(service);
            alarmManager.setInexactRepeating(1, b(timeInMillis), 3600000, service);
            o.a(a, "set alarm clock for gatherBatteryInfo, " + b(timeInMillis));
        }
    }

    public void i(Context context) {
        try {
            j(context);
            k(context);
            n(context);
            m(context);
            o(context);
            p(context);
            r(context);
            q(context);
            s(context);
            d(context);
            e(context);
            f(context);
            l(context);
            h(context);
        } catch (Throwable e) {
            ae.a(context, a, "setupAlarms exception: ", e);
        }
    }

    private static boolean a(Context context, Intent intent, int i) {
        return PendingIntent.getService(context, i, intent, 536870912) != null;
    }

    private static long b(long j) {
        return j > System.currentTimeMillis() ? j : System.currentTimeMillis() + 60000;
    }
}
