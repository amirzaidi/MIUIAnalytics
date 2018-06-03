package com.miui.analytics.internal;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.util.Log;
import com.miui.analytics.internal.c.c;
import com.miui.analytics.internal.collection.h;
import com.miui.analytics.internal.policy.a.e;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.o;

@SuppressLint({"NewApi"})
public class JobSchedulerService extends JobService {
    private static final String a = "JobSchedulerService";

    public boolean onStartJob(final JobParameters jobParameters) {
        try {
            Log.i(o.a(a), "onStartJob");
            ab.a(new Runnable(this) {
                final /* synthetic */ JobSchedulerService b;

                public void run() {
                    try {
                        Context applicationContext = this.b.getApplicationContext();
                        new e(applicationContext).a();
                        com.miui.analytics.internal.collection.e.a(applicationContext).c();
                        c.a(applicationContext).a("00", c.n, true);
                        h.a(applicationContext).b();
                        this.b.jobFinished(jobParameters, true);
                    } catch (Throwable e) {
                        Log.e(o.a(JobSchedulerService.a), "onStartJob thread run exception:", e);
                    }
                }
            });
        } catch (Throwable e) {
            Log.e(o.a(a), "onStartJob exception:", e);
        }
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(o.a(a), "onStopJob");
        return false;
    }
}
