package com.miui.analytics.internal.a;

import android.content.Context;
import android.text.TextUtils;
import com.miui.analytics.internal.util.o;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class g {
    private static final String a = "AdEventServer";
    private static final int b = 5;
    private Context c;
    private String d;

    public g(Context context, String str) {
        this.c = context;
        this.d = str;
    }

    public boolean a() {
        Throwable e;
        String str = this.d;
        HttpURLConnection httpURLConnection = null;
        int i = 0;
        int i2 = 0;
        while (i < b) {
            if (i2 / 100 == 3) {
                i++;
                str = httpURLConnection.getHeaderField("Location");
                o.a(a, "redirect url is:" + str);
            }
            String str2 = str;
            int i3 = i;
            o.a(a, String.format("redirect count:%d, request url is :%s", new Object[]{Integer.valueOf(i3), str2}));
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str2).openConnection();
            try {
                httpURLConnection2.setInstanceFollowRedirects(false);
                httpURLConnection2.setRequestMethod("GET");
                httpURLConnection2.setConnectTimeout(10000);
                httpURLConnection2.setReadTimeout(10000);
                int responseCode = httpURLConnection2.getResponseCode();
                o.a(a, "Ad url response code is " + responseCode);
                if (responseCode / 100 != b && responseCode / 100 != 3) {
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception e2) {
                        }
                    }
                    return true;
                } else if (responseCode / 100 != 3) {
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception e3) {
                        }
                    }
                    return false;
                } else {
                    int i4 = i3;
                    str = str2;
                    i2 = responseCode;
                    httpURLConnection = httpURLConnection2;
                    i = i4;
                }
            } catch (ProtocolException e4) {
                ProtocolException protocolException = e4;
                httpURLConnection = httpURLConnection2;
                ProtocolException e5 = protocolException;
            } catch (Throwable e6) {
                httpURLConnection = httpURLConnection2;
                e = e6;
            } catch (Throwable e62) {
                httpURLConnection = httpURLConnection2;
                e = e62;
            }
        }
        try {
            o.a(a, "redirectCount >= 5, return true");
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Exception e7) {
                }
            }
            return true;
        } catch (ProtocolException e8) {
            e5 = e8;
        } catch (Exception e9) {
            e = e9;
        }
        if (e5 != null) {
            try {
                if (TextUtils.isEmpty(e5.getMessage()) && e5.getMessage().contains("200 OK")) {
                    o.a(a, "response code is 200, bug status line is invalid.");
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception e10) {
                        }
                    }
                    return true;
                }
            } catch (Throwable th) {
                e = th;
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception e11) {
                    }
                }
                throw e;
            }
        }
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e12) {
            }
        }
        return false;
        o.a(a, "http get failed. ", e);
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e13) {
            }
        }
        return false;
        return true;
    }
}
