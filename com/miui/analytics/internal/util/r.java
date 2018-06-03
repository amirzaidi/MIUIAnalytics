package com.miui.analytics.internal.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import org.apache.http.conn.util.InetAddressUtils;

public class r {
    private static String a = "NetworkUtils";
    private static final int b = 16;
    private static final int c = 17;
    private static final int d = 18;
    private static final int e = 19;

    public static String a() {
        try {
            for (NetworkInterface inetAddresses : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress inetAddress : Collections.list(inetAddresses.getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(inetAddress.getHostAddress())) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Throwable e) {
            o.b(a, "getLocalIpAddress", e);
        }
        return "";
    }

    public static boolean a(Context context) {
        if (NetState.WIFI.equals(c(context))) {
            return true;
        }
        return false;
    }

    public static boolean b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                return true;
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "isNetAccessible e", e);
        }
        return false;
    }

    public static NetState c(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                if (activeNetworkInfo == null) {
                    o.a(a, "networkInfo == null");
                } else {
                    o.a(a, "!networkInfo.isConnectedOrConnecting():" + activeNetworkInfo.getState().toString());
                }
                return NetState.NONE;
            }
            if (VERSION.SDK_INT >= b) {
                if (!connectivityManager.isActiveNetworkMetered()) {
                    return NetState.WIFI;
                }
            } else if (activeNetworkInfo.getType() == 1) {
                return NetState.WIFI;
            }
            int networkType = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
            o.a(a, "getNetState networkType:" + networkType);
            return a(networkType);
        } catch (Throwable e) {
            o.b(a, "getNetState", e);
            return NetState.NONE;
        }
    }

    public static int d(Context context) {
        switch (c(context)) {
            case MN2G:
                return 1;
            case MN3G:
                return 2;
            case MN4G:
                return 3;
            case WIFI:
                return 10;
            default:
                return 0;
        }
    }

    private static NetState a(int i) {
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case b /*16*/:
                return NetState.MN2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return NetState.MN3G;
            case 13:
            case d /*18*/:
            case 19:
                return NetState.MN4G;
            default:
                return NetState.NONE;
        }
    }
}
