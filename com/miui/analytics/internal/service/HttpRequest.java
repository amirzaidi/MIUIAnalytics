package com.miui.analytics.internal.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class HttpRequest {
    private Method a = Method.GET;
    private String b;
    private List<NameValuePair> c = new ArrayList();
    private List<NameValuePair> d = new ArrayList();

    public enum Method {
        POST,
        GET
    }

    public static class a {
        private HttpRequest a = new HttpRequest();

        public a a(String str) {
            this.a.b = str;
            return this;
        }

        public a a(String str, String str2) {
            this.a.a(str, str2);
            return this;
        }

        public a a(Method method) {
            this.a.a = method;
            return this;
        }

        public HttpRequest a() {
            return this.a;
        }
    }

    public HttpRequest(String str) {
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(String str, String str2) {
        this.c.add(new BasicNameValuePair(str, str2));
    }

    public void b(String str, String str2) {
        this.d.add(new BasicNameValuePair(str, str2));
    }

    public Method b() {
        return this.a;
    }

    public void a(Method method) {
        this.a = method;
    }

    public List<NameValuePair> c() {
        return this.c;
    }

    public List<NameValuePair> d() {
        return this.d;
    }

    public String e() {
        if (this.a != Method.GET) {
            return this.b;
        }
        String format = URLEncodedUtils.format(this.c, "UTF-8");
        String str = this.b;
        if (!str.contains("?")) {
            str = str + "?";
        }
        return str + format;
    }
}
