package com.miui.analytics.internal.a;

import android.content.Context;
import com.miui.analytics.internal.b.k;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class f {
    public static boolean a = false;
    private static final String b = "AdEventSender";
    private static AtomicBoolean c = new AtomicBoolean(false);
    private final long d = 259200000;
    private List<e> e = new ArrayList();
    private Context f;
    private Runnable g = new Runnable(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.e != null && this.a.e.size() > 0) {
                k.a(this.a.f).a(this.a.e);
            }
            o.a(f.b, String.format("mRestoreTask mAdEvents: %d, sIsStarted:%s", new Object[]{Integer.valueOf(this.a.e.size()), f.c.toString()}));
            if (f.c.compareAndSet(false, true)) {
                ab.a(this.a.h);
            } else {
                o.a(f.b, "another mUploadTask is running");
            }
        }
    };
    private Runnable h = new Runnable(this) {
        final /* synthetic */ f a;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0093 in list [B:11:0x0080]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:60)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
            /*
            r10 = this;
            r9 = 1;
            r8 = 0;
            r0 = r10.a;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r0 = r0.f;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r0 = com.miui.analytics.internal.policy.h.a(r0);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r1 = r10.a;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r1 = r1.f;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r1 = com.miui.analytics.internal.b.k.a(r1);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r0 = r0.e();	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = com.miui.analytics.internal.a.f.a;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            if (r2 == 0) goto L_0x0094;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
        L_0x001e:
            r0 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
        L_0x0020:
            r2 = "AdEventSender";	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3.<init>();	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r4 = "trigger adEvent(monitor url) polling task, polling interval is  ";	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3 = r3.append(r0);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3 = r3.toString();	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            com.miui.analytics.internal.util.o.a(r2, r3);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
        L_0x0038:
            r1.f();	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = r1.e();	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3 = "AdEventSender";	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r4 = "there are %d ad events in db. ";	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r5 = 1;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r6 = 0;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r7 = r2.size();	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r5[r6] = r7;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r4 = java.lang.String.format(r4, r5);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            com.miui.analytics.internal.util.o.a(r3, r4);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3 = r2.size();	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            if (r3 != 0) goto L_0x009c;
        L_0x005e:
            r0 = com.miui.analytics.internal.a.f.c;
            r0.set(r8);
            r0 = "AdEventSender";
            r1 = "ad polling thread is done.";
            com.miui.analytics.internal.util.o.a(r0, r1);
            r0 = r10.a;
            r0 = r0.f;
            r0 = com.miui.analytics.internal.b.k.a(r0);
            r0 = r0.e();
            r0 = r0.size();
            if (r0 <= 0) goto L_0x0093;
        L_0x0080:
            r0 = com.miui.analytics.internal.a.f.c;
            r0 = r0.compareAndSet(r8, r9);
            if (r0 == 0) goto L_0x0093;
        L_0x008a:
            r0 = r10.a;
            r0 = r0.h;
            com.miui.analytics.internal.util.ab.a(r0);
        L_0x0093:
            return;
        L_0x0094:
            r2 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
            r0 = java.lang.Math.max(r2, r0);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            goto L_0x0020;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
        L_0x009c:
            r3 = new com.miui.analytics.internal.a.c;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r4 = r10.a;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r4 = r4.f;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3.<init>(r4, r2);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = r3.a();	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r1.b(r2);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = "AdEventSender";	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3 = "try to delete stalled ad event ";	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            com.miui.analytics.internal.util.o.a(r2, r3);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r4 = 259200000; // 0xf731400 float:1.1984677E-29 double:1.280618154E-315;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = r2 - r4;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r1.a(r2);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = r10.a;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = r2.f;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = com.miui.analytics.internal.b.k.a(r2);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = r2.e();	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3 = r10.a;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3.b(r2);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = (long) r0;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            com.miui.analytics.internal.util.z.a(r2);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = "AdEventSender";	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3 = "after sleep %d ms";	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r4 = 1;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r5 = 0;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r6 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r4[r5] = r6;	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r3 = java.lang.String.format(r3, r4);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            com.miui.analytics.internal.util.o.a(r2, r3);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            goto L_0x0038;
        L_0x00ee:
            r0 = move-exception;
            r1 = "AdEventSender";	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r2 = "mUploadTask run exception:";	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            com.miui.analytics.internal.util.o.b(r1, r2, r0);	 Catch:{ Exception -> 0x00ee, all -> 0x012d }
            r0 = com.miui.analytics.internal.a.f.c;
            r0.set(r8);
            r0 = "AdEventSender";
            r1 = "ad polling thread is done.";
            com.miui.analytics.internal.util.o.a(r0, r1);
            r0 = r10.a;
            r0 = r0.f;
            r0 = com.miui.analytics.internal.b.k.a(r0);
            r0 = r0.e();
            r0 = r0.size();
            if (r0 <= 0) goto L_0x0093;
        L_0x0118:
            r0 = com.miui.analytics.internal.a.f.c;
            r0 = r0.compareAndSet(r8, r9);
            if (r0 == 0) goto L_0x0093;
        L_0x0122:
            r0 = r10.a;
            r0 = r0.h;
            com.miui.analytics.internal.util.ab.a(r0);
            goto L_0x0093;
        L_0x012d:
            r0 = move-exception;
            r1 = com.miui.analytics.internal.a.f.c;
            r1.set(r8);
            r1 = "AdEventSender";
            r2 = "ad polling thread is done.";
            com.miui.analytics.internal.util.o.a(r1, r2);
            r1 = r10.a;
            r1 = r1.f;
            r1 = com.miui.analytics.internal.b.k.a(r1);
            r1 = r1.e();
            r1 = r1.size();
            if (r1 <= 0) goto L_0x0163;
        L_0x0150:
            r1 = com.miui.analytics.internal.a.f.c;
            r1 = r1.compareAndSet(r8, r9);
            if (r1 == 0) goto L_0x0163;
        L_0x015a:
            r1 = r10.a;
            r1 = r1.h;
            com.miui.analytics.internal.util.ab.a(r1);
        L_0x0163:
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.a.f.2.run():void");
        }

        {
            this.a = r1;
        }
    };

    public f(Context context, List<e> list) {
        this.e = list;
        this.f = context;
        o.a(b, "AdEventSender constructor, sIsStarted: " + c.toString());
    }

    private static String a(e eVar) {
        if (eVar != null) {
            return "" + eVar.b() + eVar.c();
        }
        return "";
    }

    private void a(List<e> list) {
        try {
            o.a(b, "enter accumulateRetryCount");
            k.a(this.f).c((List) list);
        } catch (Throwable e) {
            o.b(b, "accumulateRetryCount exception", e);
        }
    }

    public f(Context context) {
        this.f = context;
    }

    public void a() {
        ab.a(this.g);
    }

    private void b(List<e> list) {
        if (r.b(this.f)) {
            a((List) list);
        } else {
            o.d(b, "network not reachable, don't accumulate retry count");
        }
    }
}
