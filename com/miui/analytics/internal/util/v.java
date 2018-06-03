package com.miui.analytics.internal.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class v {
    private static final String a = "PrefsHelper";
    private SharedPreferences b;
    private String c;
    private Context d;

    public v(Context context, String str) {
        this.d = context;
        this.b = this.d.getSharedPreferences(str, 0);
    }

    public v(Context context, String str, String str2) {
        this.d = context;
        this.c = str2;
        this.b = this.d.getSharedPreferences(str, 0);
    }

    public Editor a() {
        return this.b.edit();
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && str2.getBytes().length > 200) {
            Log.e(o.a(a), "please don not put large string(> 200) into sharedpreferences, use file instead");
        }
        Editor edit = this.b.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public String b(String str, String str2) {
        if (this.b.contains(str)) {
            return this.b.getString(str, str2);
        }
        if (TextUtils.isEmpty(this.c)) {
            return str2;
        }
        o.a(a, "try to getString from old shared prefs, key:" + str);
        return (String) a(String.class, str, str2);
    }

    public void a(String str, int i) {
        Editor edit = this.b.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public int b(String str, int i) {
        if (this.b.contains(str)) {
            return this.b.getInt(str, i);
        }
        if (TextUtils.isEmpty(this.c)) {
            return i;
        }
        o.a(a, "try to getInt from old shared prefs, key:" + str);
        return ((Integer) a(Integer.class, str, Integer.valueOf(i))).intValue();
    }

    public void a(String str, long j) {
        Editor edit = this.b.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public long b(String str, long j) {
        if (this.b.contains(str)) {
            return this.b.getLong(str, j);
        }
        if (TextUtils.isEmpty(this.c)) {
            return j;
        }
        o.a(a, "try to getLong from old shared prefs, key:" + str);
        return ((Long) a(Long.class, str, Long.valueOf(j))).longValue();
    }

    public void a(String str, float f) {
        Editor edit = this.b.edit();
        edit.putFloat(str, f);
        edit.apply();
    }

    public float b(String str, float f) {
        if (this.b.contains(str)) {
            return this.b.getFloat(str, f);
        }
        if (TextUtils.isEmpty(this.c)) {
            return f;
        }
        o.a(a, "try to getFloat from old shared prefs, key:" + str);
        return ((Float) a(Float.class, str, Float.valueOf(f))).floatValue();
    }

    public void a(String str, boolean z) {
        Editor edit = this.b.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public boolean b(String str, boolean z) {
        if (this.b.contains(str)) {
            return this.b.getBoolean(str, z);
        }
        if (TextUtils.isEmpty(this.c)) {
            return z;
        }
        o.a(a, "try to getBoolean from old shared prefs, key:" + str);
        return ((Boolean) a(Boolean.class, str, Boolean.valueOf(z))).booleanValue();
    }

    public Map<String, ?> b() {
        return this.b.getAll();
    }

    public void a(String str) {
        Editor edit = this.b.edit();
        edit.remove(str);
        edit.apply();
    }

    public void c() {
        Editor edit = this.b.edit();
        edit.clear();
        edit.apply();
    }

    public boolean b(String str) {
        return this.b.contains(str);
    }

    private Object a(Class cls, String str, Object obj) {
        SharedPreferences sharedPreferences = this.d.getSharedPreferences(this.c, 0);
        a(sharedPreferences);
        if (cls == String.class) {
            return sharedPreferences.getString(str, (String) obj);
        }
        if (cls == Long.class) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        if (cls == Integer.class) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if (cls == Float.class) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if (cls == Boolean.class) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (cls == Set.class) {
            return sharedPreferences.getStringSet(str, (Set) obj);
        }
        return obj;
    }

    private void a(final SharedPreferences sharedPreferences) {
        if (!sharedPreferences.getAll().isEmpty()) {
            ab.a(new Runnable(this) {
                final /* synthetic */ v b;

                public void run() {
                    o.a(v.a, "start compatibleWithOldVersion");
                    try {
                        Map all = sharedPreferences.getAll();
                        Iterator it = all.keySet().iterator();
                        Editor edit = this.b.b.edit();
                        while (it.hasNext()) {
                            try {
                                String str = (String) it.next();
                                Object obj = all.get(it);
                                if (obj instanceof String) {
                                    edit.putString(str, (String) obj);
                                } else if (obj instanceof Long) {
                                    edit.putLong(str, ((Long) obj).longValue());
                                } else if (obj instanceof Integer) {
                                    edit.putInt(str, ((Integer) obj).intValue());
                                } else if (obj instanceof Float) {
                                    edit.putFloat(str, ((Float) obj).floatValue());
                                } else if (obj instanceof Boolean) {
                                    edit.putBoolean(str, ((Boolean) obj).booleanValue());
                                } else if (obj instanceof Set) {
                                    edit.putStringSet(str, (Set) obj);
                                }
                            } catch (Throwable e) {
                                Log.e(o.a(v.a), "compatibleWithOldVersion put to new e", e);
                            }
                        }
                        edit.commit();
                        sharedPreferences.edit().clear().commit();
                        v.a(this.b.d, this.b.c);
                    } catch (Throwable e2) {
                        Log.e(o.a(v.a), "compatibleWithOldVersion e", e2);
                    }
                }
            });
        }
    }

    public static void a(Context context, String str) {
        try {
            Field declaredField = ContextWrapper.class.getDeclaredField("mBase");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(context);
            Method declaredMethod = obj.getClass().getDeclaredMethod("getPreferencesDir", new Class[0]);
            declaredMethod.setAccessible(true);
            File file = new File((File) declaredMethod.invoke(obj, new Object[0]), str + ".xml");
            if (file.exists()) {
                o.a(a, "oldSharedPrefsFile exists,delete " + file);
                file.delete();
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "deleteOldSharedPrefsFile e", e);
        }
    }
}
