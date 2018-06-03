package com.miui.analytics.internal.service;

import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.service.HttpRequest.Method;
import com.miui.analytics.internal.util.m;
import com.miui.analytics.internal.util.o;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import org.apache.http.NameValuePair;

public class k extends c {
    private static final String a = "URLClient";

    public d a(HttpRequest httpRequest) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(httpRequest.e()).openConnection();
            List<NameValuePair> d = httpRequest.d();
            if (d != null) {
                for (NameValuePair nameValuePair : d) {
                    httpURLConnection.setRequestProperty(nameValuePair.getName(), nameValuePair.getValue());
                }
            }
            httpURLConnection.setRequestMethod(httpRequest.b() == Method.GET ? "GET" : "POST");
            if (httpRequest.b() == Method.POST) {
                a(httpURLConnection, httpRequest);
            }
            httpURLConnection.setConnectTimeout(f.O);
            httpURLConnection.setReadTimeout(60000);
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            switch (responseCode) {
                case 200:
                    InputStream gZIPInputStream;
                    InputStream inputStream = httpURLConnection.getInputStream();
                    if (!TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
                        Object toLowerCase = httpURLConnection.getContentEncoding().toLowerCase(Locale.getDefault());
                        if (!TextUtils.isEmpty(toLowerCase) && toLowerCase.indexOf("gzip") >= 0) {
                            gZIPInputStream = new GZIPInputStream(httpURLConnection.getInputStream());
                            return new d(responseCode, httpURLConnection.getContentLength(), gZIPInputStream);
                        }
                    }
                    gZIPInputStream = inputStream;
                    return new d(responseCode, httpURLConnection.getContentLength(), gZIPInputStream);
                default:
                    break;
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "performRequest e", e);
        }
        return null;
    }

    private void a(HttpURLConnection httpURLConnection, HttpRequest httpRequest) {
        Throwable th;
        Throwable e;
        Closeable closeable = null;
        try {
            Closeable closeable2;
            httpURLConnection.setDoOutput(true);
            List c = httpRequest.c();
            if (c == null || c.size() <= 0) {
                closeable2 = null;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < c.size(); i++) {
                    NameValuePair nameValuePair = (NameValuePair) c.get(i);
                    if (!(nameValuePair == null || TextUtils.isEmpty(nameValuePair.getName()) || TextUtils.isEmpty(nameValuePair.getValue()))) {
                        stringBuilder.append(URLEncoder.encode(nameValuePair.getName(), "UTF8"));
                        stringBuilder.append("=");
                        stringBuilder.append(URLEncoder.encode(nameValuePair.getValue(), "UTF8"));
                        stringBuilder.append("&");
                    }
                }
                if (stringBuilder.length() > 0) {
                    stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
                closeable2 = new OutputStreamWriter(httpURLConnection.getOutputStream());
                try {
                    closeable2.write(stringBuilder.toString());
                    closeable2.flush();
                } catch (Throwable e2) {
                    th = e2;
                    closeable = closeable2;
                    e = th;
                    try {
                        Log.e(o.a(a), "post e", e);
                        m.a(closeable);
                    } catch (Throwable th2) {
                        e = th2;
                        m.a(closeable);
                        throw e;
                    }
                } catch (Throwable e22) {
                    th = e22;
                    closeable = closeable2;
                    e = th;
                    m.a(closeable);
                    throw e;
                }
            }
            m.a(closeable2);
        } catch (Exception e3) {
            e = e3;
            Log.e(o.a(a), "post e", e);
            m.a(closeable);
        }
    }
}
