package com.miui.analytics.internal.policy.a;

import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.b.k;
import com.miui.analytics.internal.policy.g;
import com.miui.analytics.internal.policy.h;
import com.miui.analytics.internal.service.b;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.r;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class e extends g {
    private static final String c = "PollingSender";
    private static AtomicBoolean d = new AtomicBoolean(false);
    private static long e = 0;
    private Runnable f = new Runnable(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void run() {
            List arrayList = new ArrayList();
            for (LogEvent logEvent : this.a.b) {
                if (h.a(this.a.a).b(logEvent).a()) {
                    o.a(e.c, "sample rate match.");
                    arrayList.add(logEvent);
                }
            }
            k.a(this.a.a).d(arrayList);
            if (e.d.compareAndSet(false, true)) {
                ab.a(this.a.g);
            } else {
                o.a(e.c, "mUploadTask is already running...");
            }
        }
    };
    private Runnable g = new Runnable(this) {
        final /* synthetic */ e a;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0124 in list [B:19:0x0111]
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
            r13 = this;
            r12 = 1;
            r11 = 0;
            r0 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r1 = com.miui.analytics.internal.policy.h.a(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.getPackageName();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r1.a(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r2 = com.miui.analytics.internal.b.k.a(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.getPackageName();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r1.c(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4 = com.miui.analytics.internal.policy.a.e.e;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = 0;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            if (r3 != 0) goto L_0x0048;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x003b:
            if (r0 == 0) goto L_0x0048;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x003d:
            r0 = r0.h();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            if (r0 == 0) goto L_0x0048;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x0043:
            r0 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0.d();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x0048:
            r3 = r1.e();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = "PollingSender";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4.<init>();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r5 = "trigger polling task, poling interval is ";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4 = r4.append(r5);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4 = r4.append(r3);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4 = r4.toString();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            com.miui.analytics.internal.util.o.a(r0, r4);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = com.miui.analytics.internal.policy.a.e.e;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r4 - r6;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r8 = (long) r3;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            if (r0 >= 0) goto L_0x009c;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x0073:
            r6 = (long) r3;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4 = r6 - r4;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = com.miui.analytics.internal.policy.a.e.e;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4 = r4 + r6;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = "PollingSender";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6.<init>();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r7 = "sleep ";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r6.append(r7);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r6.append(r4);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r7 = "ms till next upload opportunity";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r6.append(r7);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r6.toString();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            com.miui.analytics.internal.util.o.a(r0, r6);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            com.miui.analytics.internal.util.z.a(r4);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x009c:
            r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            com.miui.analytics.internal.policy.a.e.e = r4;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4.<init>();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r5 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r5.<init>();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x00ad:
            r0 = "PollingSender";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = "check discard policy ";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            com.miui.analytics.internal.util.o.a(r0, r6);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = com.miui.analytics.internal.policy.h.a(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.c();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r6.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r7 = 1;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0.a(r6, r7);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = com.miui.analytics.internal.b.k.a(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0.j();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = com.miui.analytics.internal.b.k.a(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.g();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            if (r0 == 0) goto L_0x00ef;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x00e9:
            r6 = r0.size();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            if (r6 != 0) goto L_0x0125;
        L_0x00ef:
            r0 = com.miui.analytics.internal.policy.a.e.d;
            r0.set(r11);
            r0 = "PollingSender";
            r1 = "polling thread is done.";
            com.miui.analytics.internal.util.o.a(r0, r1);
            r0 = r13.a;
            r0 = r0.a;
            r0 = com.miui.analytics.internal.b.k.a(r0);
            r0 = r0.g();
            r0 = r0.size();
            if (r0 <= 0) goto L_0x0124;
        L_0x0111:
            r0 = com.miui.analytics.internal.policy.a.e.d;
            r0 = r0.compareAndSet(r11, r12);
            if (r0 == 0) goto L_0x0124;
        L_0x011b:
            r0 = r13.a;
            r0 = r0.g;
            com.miui.analytics.internal.util.ab.a(r0);
        L_0x0124:
            return;
        L_0x0125:
            r4.clear();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r5.clear();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r0.iterator();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x012f:
            r0 = r6.hasNext();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            if (r0 == 0) goto L_0x01c2;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x0135:
            r0 = r6.next();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = (com.miui.analytics.internal.LogEvent) r0;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r7 = r1.h(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            if (r7 == 0) goto L_0x0187;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x0141:
            r4.add(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            goto L_0x012f;
        L_0x0145:
            r0 = move-exception;
            r1 = "PollingSender";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r1 = com.miui.analytics.internal.util.o.a(r1);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r2 = "mUploadTask run exception:";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            android.util.Log.e(r1, r2, r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = com.miui.analytics.internal.policy.a.e.d;
            r0.set(r11);
            r0 = "PollingSender";
            r1 = "polling thread is done.";
            com.miui.analytics.internal.util.o.a(r0, r1);
            r0 = r13.a;
            r0 = r0.a;
            r0 = com.miui.analytics.internal.b.k.a(r0);
            r0 = r0.g();
            r0 = r0.size();
            if (r0 <= 0) goto L_0x0124;
        L_0x0173:
            r0 = com.miui.analytics.internal.policy.a.e.d;
            r0 = r0.compareAndSet(r11, r12);
            if (r0 == 0) goto L_0x0124;
        L_0x017d:
            r0 = r13.a;
            r0 = r0.g;
            com.miui.analytics.internal.util.ab.a(r0);
            goto L_0x0124;
        L_0x0187:
            r5.add(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            goto L_0x012f;
        L_0x018b:
            r0 = move-exception;
            r1 = com.miui.analytics.internal.policy.a.e.d;
            r1.set(r11);
            r1 = "PollingSender";
            r2 = "polling thread is done.";
            com.miui.analytics.internal.util.o.a(r1, r2);
            r1 = r13.a;
            r1 = r1.a;
            r1 = com.miui.analytics.internal.b.k.a(r1);
            r1 = r1.g();
            r1 = r1.size();
            if (r1 <= 0) goto L_0x01c1;
        L_0x01ae:
            r1 = com.miui.analytics.internal.policy.a.e.d;
            r1 = r1.compareAndSet(r11, r12);
            if (r1 == 0) goto L_0x01c1;
        L_0x01b8:
            r1 = r13.a;
            r1 = r1.g;
            com.miui.analytics.internal.util.ab.a(r1);
        L_0x01c1:
            throw r0;
        L_0x01c2:
            r0 = new com.miui.analytics.internal.service.b;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r6.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0.<init>(r6, r4);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.a();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r6.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = com.miui.analytics.internal.b.k.a(r6);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6.e(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6.a(r4, r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = "PollingSender";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r7 = "realtime events(%d) success sent %d events";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r8 = 2;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r8 = new java.lang.Object[r8];	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r9 = 0;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r10 = r4.size();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r8[r9] = r10;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r9 = 1;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.size();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r8[r9] = r0;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = java.lang.String.format(r7, r8);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            com.miui.analytics.internal.util.o.a(r6, r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r4.clear();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = com.miui.analytics.internal.policy.a.e.e;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r1.a(r6);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = "PollingSender";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r7 = "check polling send trigger.";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            com.miui.analytics.internal.util.o.a(r6, r7);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = 1;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.a(r2, r6);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            if (r0 == 0) goto L_0x027b;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x0220:
            r0 = "PollingSender";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = "send policy is triggered.";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            com.miui.analytics.internal.util.o.a(r0, r6);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = com.miui.analytics.internal.util.r.a(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            if (r0 == 0) goto L_0x0288;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x0233:
            r4.addAll(r5);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x0236:
            r0 = new com.miui.analytics.internal.service.b;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r6.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0.<init>(r6, r4);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = r0.a();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = "PollingSender";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r7 = "triggered send polling events(%d), success sent %d events";	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r8 = 2;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r8 = new java.lang.Object[r8];	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r9 = 0;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r10 = r4.size();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r8[r9] = r10;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r9 = 1;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r10 = r0.size();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r8[r9] = r10;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r7 = java.lang.String.format(r7, r8);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            com.miui.analytics.internal.util.o.a(r6, r7);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r6.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = com.miui.analytics.internal.b.k.a(r6);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6.e(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = r13.a;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6.a(r4, r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x027b:
            r0 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = java.lang.Math.max(r0, r3);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r6 = (long) r0;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            com.miui.analytics.internal.util.z.a(r6);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            goto L_0x00ad;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x0288:
            r6 = r5.iterator();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x028c:
            r0 = r6.hasNext();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            if (r0 == 0) goto L_0x0236;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x0292:
            r0 = r6.next();	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r0 = (com.miui.analytics.internal.LogEvent) r0;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            r7 = r1.g(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            if (r7 != 0) goto L_0x028c;	 Catch:{ Exception -> 0x0145, all -> 0x018b }
        L_0x029e:
            r4.add(r0);	 Catch:{ Exception -> 0x0145, all -> 0x018b }
            goto L_0x028c;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miui.analytics.internal.policy.a.e.2.run():void");
        }

        {
            this.a = r1;
        }
    };

    public e(Context context) {
        super(context, (LogEvent) null);
    }

    public e(Context context, List<LogEvent> list) {
        super(context, (List) list);
    }

    public e(Context context, LogEvent logEvent) {
        super(context, logEvent);
    }

    public void a() {
        ab.a(this.f);
    }

    private void d() {
        try {
            o.a(c, "drain all the events");
            if (r.b(this.a)) {
                Object<LogEvent> g = k.a(this.a).g();
                h a = h.a(this.a);
                List arrayList = new ArrayList();
                if (r.a(this.a)) {
                    arrayList.addAll(g);
                } else {
                    for (LogEvent logEvent : g) {
                        if (!a.g(logEvent)) {
                            arrayList.add(logEvent);
                        }
                    }
                }
                List a2 = new b(this.a, arrayList).a();
                k.a(this.a).e(a2);
                a(arrayList, a2);
            }
        } catch (Throwable e) {
            Log.e(o.a(c), "drainAllEvents exception", e);
        }
    }

    private void a(List<LogEvent> list, List<LogEvent> list2) {
        List arrayList = new ArrayList();
        if (r.b(this.a)) {
            for (LogEvent logEvent : list) {
                if (!list2.contains(logEvent)) {
                    arrayList.add(logEvent);
                }
            }
        }
        k.a(this.a).f(arrayList);
    }
}
