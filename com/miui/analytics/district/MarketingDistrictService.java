package com.miui.analytics.district;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.miui.analytics.district.a.a;
import com.miui.analytics.internal.util.o;

public class MarketingDistrictService extends Service {
    private static final String a = "MarketingDistrictService";
    private IBinder b = new a(this) {
        final /* synthetic */ MarketingDistrictService c;

        {
            this.c = r1;
        }

        public void a(long j, String str, String str2, String str3, b bVar) {
            try {
                bVar.a(false);
            } catch (Throwable e) {
                Log.e(o.a(MarketingDistrictService.a), "registerMdsCondition e", e);
            }
        }

        public String a(long j, String str) {
            return "";
        }
    };

    public IBinder onBind(Intent intent) {
        return this.b;
    }
}
