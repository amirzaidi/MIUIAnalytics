package com.miui.analytics.internal.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.Location;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.policy.f;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class p {
    private static final String a = "MC";
    private static final int b = -1;
    private static final String c = ",";
    private static volatile p e;
    private boolean d = false;
    private com.miui.analytics.internal.util.t.a f;
    private Location g;
    private TelephonyManager h;
    private b i;
    private Context j;

    private class a implements com.miui.analytics.internal.util.t.a {
        final /* synthetic */ p a;

        private a(p pVar) {
            this.a = pVar;
        }

        public void a(Location location) {
            this.a.g.set(location);
            this.a.i.m();
        }
    }

    private class b {
        private static final String b = "mi";
        private static final String c = "mir";
        private static final String d = "lssl";
        private static final String e = "ami";
        private static final String f = "amlssl";
        private static final String g = "amfmri";
        private static final String h = "b";
        private static final String i = "s";
        private static final String j = "w";
        private static final String k = "g";
        private static final String l = "t";
        private static final String m = "m";
        final /* synthetic */ p a;
        private v n;
        private j o;
        private final Object p = new Object();

        b(p pVar, Context context) {
            this.a = pVar;
            this.n = new v(context, u.d);
            this.o = new j(context, d);
        }

        private void l() {
            if (!n.e()) {
                o.a(p.a, "record mds");
                ab.a(new Runnable(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        synchronized (this.a.p) {
                            try {
                                if (this.a.a(this.a.h(), 3600000)) {
                                    this.a.a(this.a.a(this.a.n()));
                                    this.a.d(System.currentTimeMillis());
                                    return;
                                }
                                o.a(p.a, String.format("recordCoarseMds skip, throttle interval:%d, lastTimeRecord = %d", new Object[]{Integer.valueOf(ac.d), Long.valueOf(r2)}));
                            } catch (Throwable th) {
                                Log.e(o.a(p.a), "recordCoarseMds task exception: ", th);
                            }
                        }
                    }
                });
            }
        }

        private void m() {
            if (n.e() || !g.g()) {
                o.a(p.a, "recordFineMds enable is" + g.g());
            }
        }

        private boolean a(long j, long j2) {
            if (ac.a(j, j2)) {
                return true;
            }
            return false;
        }

        private String a(JSONObject jSONObject) {
            JSONArray jSONArray;
            String str = "";
            Object a = a();
            JSONArray jSONArray2 = new JSONArray();
            if (TextUtils.isEmpty(a)) {
                jSONArray = jSONArray2;
            } else {
                try {
                    jSONArray = new JSONArray(a);
                } catch (Exception e) {
                    jSONArray = jSONArray2;
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("t", System.currentTimeMillis());
                jSONObject2.put("m", jSONObject);
                jSONArray.put(jSONObject2);
            } catch (Throwable th) {
                Log.e(o.a(p.a), "getNewMdsInfo exception: ", th);
            }
            return jSONArray.toString();
        }

        private JSONObject n() {
            JSONObject jSONObject = new JSONObject();
            long currentTimeMillis = System.currentTimeMillis();
            try {
                jSONObject.put(h, r());
                jSONObject.put("w", q());
                jSONObject.put("s", s());
            } catch (Throwable e) {
                Log.e(o.a(p.a), "get getCoarseMdsInfo exception: ", e);
            }
            o.a(p.a, String.format("coarse %dms, %s", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis), jSONObject.toString()}));
            return jSONObject;
        }

        private JSONObject o() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(h, r());
                jSONObject.put("w", q());
                jSONObject.put("s", s());
                jSONObject.put("g", p());
            } catch (Throwable e) {
                Log.e(o.a(p.a), "get getFineMdsInfo exception: ", e);
            }
            return jSONObject;
        }

        private String p() {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                if (this.a.g == null || (af.a(this.a.g.getLatitude()) && af.a(this.a.g.getLongitude()))) {
                    return "";
                }
                stringBuilder.append(this.a.g.getLatitude() + p.c).append(this.a.g.getLongitude() + p.c).append(this.a.g.getProvider());
                return stringBuilder.toString();
            } catch (Throwable e) {
                Log.e(o.a(p.a), "getFineMds exception: ", e);
            }
        }

        private String q() {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                WifiInfo connectionInfo = ((WifiManager) this.a.j.getSystemService(f.i)).getConnectionInfo();
                if (!(connectionInfo == null || connectionInfo.getBSSID() == null || connectionInfo.getSSID() == null)) {
                    String ssid = connectionInfo.getSSID();
                    int rssi = connectionInfo.getRssi();
                    stringBuilder.append(connectionInfo.getBSSID() + p.c).append(ssid.substring(ssid.indexOf("\"") + 1, ssid.lastIndexOf("\"")));
                    stringBuilder.append(p.c + rssi);
                }
            } catch (Throwable e) {
                Log.e(o.a(p.a), "get wifi exception: ", e);
            }
            return stringBuilder.toString();
        }

        private String r() {
            String str = "";
            if (c.c(this.a.j, "android.permission.ACCESS_COARSE_LOCATION")) {
                try {
                    CellLocation cellLocation = this.a.h.getCellLocation();
                    if (cellLocation != null) {
                        String networkOperator = this.a.h.getNetworkOperator();
                        o.a(p.a, "operator " + networkOperator);
                        if (!TextUtils.isEmpty(networkOperator) && networkOperator.length() > 3) {
                            str = a(cellLocation, networkOperator);
                        }
                    }
                } catch (Throwable e) {
                    Log.e(o.a(p.a), "get bs exception: ", e);
                }
            } else {
                Log.d(o.a(p.a), "no permission ACCESS_COARSE_LOCATION");
            }
            return str;
        }

        @TargetApi(17)
        private JSONArray s() {
            int i = 0;
            JSONArray jSONArray = new JSONArray();
            if (!c.c(this.a.j, "android.permission.ACCESS_COARSE_LOCATION") || VERSION.SDK_INT < 17) {
                return jSONArray;
            }
            try {
                List<CellInfo> allCellInfo = this.a.h.getAllCellInfo();
                if (allCellInfo == null || allCellInfo.isEmpty()) {
                    o.a(p.a, "cells null");
                    return jSONArray;
                }
                String str;
                String str2 = "";
                String networkOperator = this.a.h.getNetworkOperator();
                if (TextUtils.isEmpty(networkOperator) || networkOperator.length() <= 3) {
                    str = str2;
                } else {
                    str = networkOperator.substring(0, 3);
                }
                o.a(p.a, "cells " + allCellInfo.size());
                int i2 = 0;
                for (CellInfo cellInfo : allCellInfo) {
                    int i3 = i2 + 1;
                    if (i2 >= 10) {
                        break;
                    }
                    int i4;
                    CharSequence a = a(str, cellInfo);
                    if (!TextUtils.isEmpty(a)) {
                        jSONArray.put(a);
                        i4 = i + 1;
                        if (i >= 3) {
                            break;
                        }
                    } else {
                        i4 = i;
                    }
                    i = i4;
                    i2 = i3;
                }
                return jSONArray;
            } catch (Throwable e) {
                Log.e(o.a(p.a), "get cell info exception: ", e);
            }
        }

        @TargetApi(17)
        private String a(String str, CellInfo cellInfo) {
            StringBuilder stringBuilder = new StringBuilder();
            if (cellInfo instanceof CellInfoGsm) {
                CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
                if (!(cellIdentity.getLac() == Integer.MAX_VALUE || cellIdentity.getCid() == Integer.MAX_VALUE)) {
                    stringBuilder.append(cellIdentity.getMcc()).append(p.c).append(cellIdentity.getMnc()).append(p.c).append(cellIdentity.getLac()).append(p.c).append(cellIdentity.getCid()).append(p.c).append(cellInfoGsm.getCellSignalStrength().getDbm());
                }
            } else if (cellInfo instanceof CellInfoLte) {
                CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                CellIdentityLte cellIdentity2 = cellInfoLte.getCellIdentity();
                if (!(cellIdentity2.getCi() == Integer.MAX_VALUE || cellIdentity2.getTac() == Integer.MAX_VALUE)) {
                    stringBuilder.append(cellIdentity2.getMcc()).append(p.c).append(cellIdentity2.getMnc()).append(p.c).append(cellIdentity2.getTac()).append(p.c).append(cellIdentity2.getCi()).append(p.c).append(cellInfoLte.getCellSignalStrength().getDbm());
                }
            } else if (cellInfo instanceof CellInfoCdma) {
                CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                CellIdentityCdma cellIdentity3 = cellInfoCdma.getCellIdentity();
                if (!(cellIdentity3.getNetworkId() == Integer.MAX_VALUE || cellIdentity3.getBasestationId() == Integer.MAX_VALUE)) {
                    stringBuilder.append(str).append(p.c).append(cellIdentity3.getSystemId()).append(p.c).append(cellIdentity3.getNetworkId()).append(p.c).append(cellIdentity3.getBasestationId()).append(p.c).append(cellInfoCdma.getCellSignalStrength().getDbm());
                }
            } else if ((cellInfo instanceof CellInfoWcdma) && VERSION.SDK_INT >= 18) {
                CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                if (!(cellIdentity4.getLac() == Integer.MAX_VALUE || cellIdentity4.getCid() == Integer.MAX_VALUE)) {
                    stringBuilder.append(cellIdentity4.getMcc()).append(p.c).append(cellIdentity4.getMnc()).append(p.c).append(cellIdentity4.getLac()).append(p.c).append(cellIdentity4.getCid()).append(p.c).append(cellInfoWcdma.getCellSignalStrength().getDbm());
                }
            }
            return stringBuilder.toString();
        }

        private String a(CellLocation cellLocation, String str) {
            StringBuilder stringBuilder = new StringBuilder();
            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                if (!(gsmCellLocation.getLac() == -1 || gsmCellLocation.getCid() == -1)) {
                    stringBuilder.append(str.substring(0, 3)).append(p.c).append(str.substring(3)).append(p.c).append(gsmCellLocation.getLac()).append(p.c).append(gsmCellLocation.getCid());
                }
            } else if (cellLocation instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                if (!(cdmaCellLocation.getSystemId() == -1 || cdmaCellLocation.getBaseStationId() == -1 || cdmaCellLocation.getNetworkId() == -1)) {
                    stringBuilder.append(str.substring(0, 3)).append(p.c).append(cdmaCellLocation.getSystemId()).append(p.c).append(cdmaCellLocation.getNetworkId()).append(p.c).append(cdmaCellLocation.getBaseStationId());
                }
            }
            return stringBuilder.toString();
        }

        synchronized String a() {
            String str;
            str = "";
            try {
                if (!TextUtils.isEmpty(c("mi"))) {
                    str = b.c(c("mi"), af.b(e));
                    o.a(p.a, "getPersistedMds " + str);
                }
            } catch (Throwable e) {
                Log.e(o.a(p.a), "getPersistedMds  exception: ", e);
            }
            return str;
        }

        synchronized void a(String str) {
            try {
                a("mi", b.a(str, af.b(e)));
            } catch (Throwable e) {
                Log.e(o.a(p.a), "updatePersistedMds  exception: ", e);
            }
        }

        void a(long j) {
            this.n.a(u.y, j);
        }

        long b() {
            return this.n.b(u.y, 0);
        }

        synchronized void c() {
            a("");
        }

        void b(long j) {
            this.n.a(u.w, j);
        }

        long d() {
            return this.n.b(u.w, 0);
        }

        void c(long j) {
            this.n.a(u.x, j);
        }

        long e() {
            return this.n.b(u.x, 0);
        }

        void b(String str) {
            try {
                a(c, b.a(str, af.b(g)));
            } catch (Throwable e) {
                Log.e(o.a(p.a), "setFineMdsRegisterInfo  exception: ", e);
            }
        }

        String f() {
            String str = "";
            try {
                if (!TextUtils.isEmpty(c(c))) {
                    str = b.c(c(c), af.b(g));
                }
            } catch (Throwable e) {
                Log.e(o.a(p.a), "getFineMdsRegisterInfo  exception: ", e);
            }
            return str;
        }

        void g() {
            try {
                a(c, b.a("", af.b(g)));
            } catch (Throwable e) {
                Log.e(o.a(p.a), "cleanFineMdsRegisterInfo  exception: ", e);
            }
        }

        void d(long j) {
            this.n.a(u.v, j);
        }

        long h() {
            return this.n.b(u.v, 0);
        }

        void a(String str, String str2) throws IOException {
            Throwable e;
            Closeable closeable = null;
            try {
                File file = new File(e(str));
                if (!file.exists()) {
                    file.createNewFile();
                    k.a(file);
                }
                Closeable fileOutputStream = new FileOutputStream(file, false);
                try {
                    fileOutputStream.write(str2.getBytes());
                    if (fileOutputStream != null) {
                        m.a(fileOutputStream);
                    }
                } catch (Exception e2) {
                    e = e2;
                    closeable = fileOutputStream;
                    try {
                        Log.e(o.a(p.a), "writeMdsFile exception: ", e);
                        if (closeable != null) {
                            m.a(closeable);
                        }
                    } catch (Throwable th) {
                        e = th;
                        if (closeable != null) {
                            m.a(closeable);
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    closeable = fileOutputStream;
                    if (closeable != null) {
                        m.a(closeable);
                    }
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
                Log.e(o.a(p.a), "writeMdsFile exception: ", e);
                if (closeable != null) {
                    m.a(closeable);
                }
            }
        }

        String c(String str) throws IOException {
            Throwable e;
            Throwable th;
            String str2 = "";
            FileInputStream fileInputStream = null;
            try {
                File file = new File(e(str));
                if (!file.exists()) {
                    file.createNewFile();
                    k.a(file);
                }
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[fileInputStream2.available()];
                    fileInputStream2.read(bArr);
                    str2 = EncodingUtils.getString(bArr, "UTF-8");
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileInputStream = fileInputStream2;
                    try {
                        Log.e(o.a(p.a), "readMdsFile exception: ", e);
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return str2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                Log.e(o.a(p.a), "readMdsFile exception: ", e);
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str2;
            }
            return str2;
        }

        private String e(String str) {
            if (TextUtils.isEmpty(str) || str.startsWith("/")) {
                return null;
            }
            return this.a.j.getFilesDir().getAbsolutePath() + "/" + str;
        }

        void d(String str) {
            this.o.a(b.a(str, af.b(f)));
        }

        String i() {
            return this.o.a();
        }

        void a(int i) {
            this.n.a(u.A, i);
        }

        int j() {
            return this.n.b(u.A, 0);
        }

        void e(long j) {
            this.n.a(u.z, j);
        }

        long k() {
            return this.n.b(u.z, 0);
        }

        private String b(String str, String str2) {
            String str3 = "";
            try {
                if (!TextUtils.isEmpty(this.n.b(str2, ""))) {
                    str3 = b.c(this.n.b(str2, ""), af.b(str));
                }
            } catch (Throwable e) {
                Log.e(o.a(p.a), "getAesDecodeInfo exception: ", e);
                this.n.a(str2, "");
            }
            return str3;
        }
    }

    private p(Context context) {
        try {
            this.j = c.a(context);
            this.i = new b(this, this.j);
            this.h = (TelephonyManager) this.j.getSystemService("phone");
        } catch (Throwable e) {
            Log.e(o.a(a), "MdsCollector exception:", e);
        }
    }

    public static synchronized p a(Context context) {
        p pVar;
        synchronized (p.class) {
            if (e == null) {
                e = new p(context);
            }
            pVar = e;
        }
        return pVar;
    }

    private void k() {
        this.g = new Location("passive");
        t.a(this.j).a();
        this.f = new a();
        l();
    }

    public String a(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String jSONObject = this.i.n().toString();
            o.a(a, String.format("getCurrentCoarseMds %dms", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
            return b.a(jSONObject, str);
        } catch (Throwable e) {
            Log.e(o.a(a), "getCurrentCoarseMds exception:", e);
            return "";
        }
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            String B = z.B();
            jSONObject.put("triggerId", w.a(B));
            jSONObject.put("ex", d(B));
        } catch (Throwable e) {
            Log.e(o.a(a), "getMdsContent exception:", e);
        }
        return jSONObject.toString();
    }

    public long b() {
        return this.i.e();
    }

    public void a(long j) {
        this.i.c(j);
    }

    public long c() {
        return this.i.b();
    }

    public void d() {
        this.i.g();
    }

    public void b(String str) {
        this.i.b(str);
    }

    public String e() {
        return this.i.f();
    }

    public void c(String str) {
        this.i.d(str);
    }

    public String f() {
        return this.i.i();
    }

    public void g() {
        this.i.a(System.currentTimeMillis());
        this.i.c();
    }

    public WifiInfo h() {
        try {
            return ((WifiManager) this.j.getSystemService(f.i)).getConnectionInfo();
        } catch (Throwable e) {
            Log.e(o.a(a), "getWifiInfoForMatch exception: ", e);
            return null;
        }
    }

    public String i() {
        String str = "";
        if (c.c(this.j, "android.permission.ACCESS_COARSE_LOCATION")) {
            try {
                CellLocation cellLocation = this.h.getCellLocation();
                if (cellLocation != null) {
                    str = a(cellLocation);
                }
            } catch (Throwable e) {
                Log.e(o.a(a), "getBaseStationForMatch exception: ", e);
            }
        }
        return str;
    }

    public String a(CellLocation cellLocation) {
        String str = "";
        String networkOperator = this.h.getNetworkOperator();
        if (TextUtils.isEmpty(networkOperator) || networkOperator.length() <= 3) {
            return str;
        }
        int lac;
        int cid;
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            lac = gsmCellLocation.getLac();
            cid = gsmCellLocation.getCid();
            if (lac == -1 || cid == -1) {
                return str;
            }
            str = networkOperator.substring(0, 3);
            networkOperator = networkOperator.substring(3);
            int intValue = Integer.valueOf(str).intValue();
            int intValue2 = Integer.valueOf(networkOperator).intValue();
            return String.format("%03x%04x%08x%08x", new Object[]{Integer.valueOf(intValue), Integer.valueOf(intValue2), Integer.valueOf(lac), Integer.valueOf(cid)});
        } else if (!(cellLocation instanceof CdmaCellLocation)) {
            return str;
        } else {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            lac = cdmaCellLocation.getBaseStationId();
            cid = cdmaCellLocation.getSystemId();
            int networkId = cdmaCellLocation.getNetworkId();
            if (cid == -1 || lac == -1 || networkId == -1) {
                return str;
            }
            return String.format("%03x%04x%08x%08x", new Object[]{Integer.valueOf(Integer.valueOf(networkOperator.substring(0, 3)).intValue()), Integer.valueOf(cid), Integer.valueOf(networkId), Integer.valueOf(lac)});
        }
    }

    public boolean b(long j) {
        try {
            if (this.i.j() < g.h() || Math.abs(j - this.i.k()) > ac.b) {
                return true;
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "enableRegister exception: ", e);
        }
        return false;
    }

    public void c(long j) {
        try {
            int j2 = this.i.j();
            if (j2 == 0) {
                this.i.a(j2 + 1);
                this.i.e(j);
            } else if (Math.abs(j - this.i.k()) > ac.b) {
                this.i.e(j);
                this.i.a(1);
            } else {
                this.i.a(j2 + 1);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "updateAdRegisterCountDays exception: ", e);
        }
    }

    private String d(String str) {
        try {
            return b.a(this.i.a(), str);
        } catch (Throwable e) {
            Log.e(o.a(a), "get encrypt exception: ", e);
            return "";
        }
    }

    private void l() {
        if (!this.d) {
            this.d = true;
            if (this.f != null) {
                t.a(this.j).a(this.f);
            }
        }
    }

    public void j() {
        if (!n.a(this.j, a) && af.a()) {
            this.i.l();
        }
    }
}
