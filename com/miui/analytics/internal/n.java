package com.miui.analytics.internal;

public class n implements Comparable<n> {
    public int a = 1;
    public int b = 0;
    public int c = 0;

    public /* synthetic */ int compareTo(Object obj) {
        return b((n) obj);
    }

    public n(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public n(String str) {
        try {
            String[] split = str.split("\\.");
            this.a = Integer.parseInt(split[0]);
            this.b = Integer.parseInt(split[1]);
            this.c = Integer.parseInt(split[2]);
        } catch (Exception e) {
        }
    }

    public boolean a(n nVar) {
        if (nVar != null && this.a == nVar.a && this.b == nVar.b) {
            return true;
        }
        return false;
    }

    public int b(n nVar) {
        if (nVar == null) {
            return 1;
        }
        if (this.a != nVar.a) {
            return this.a - nVar.a;
        }
        if (this.b != nVar.b) {
            return this.b - nVar.b;
        }
        return this.c - nVar.c;
    }

    public String toString() {
        return this.a + "." + this.b + "." + this.c;
    }
}
