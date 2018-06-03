package com.miui.analytics.internal.collection;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.util.c;
import com.miui.analytics.internal.util.o;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class MusicStateListener extends Service {
    private static final String a = "MusicStateListener";
    private static final String b = "enabled_notification_listeners";

    public static void a(Context context) {
        try {
            if (!c(context)) {
                HashSet hashSet = new HashSet();
                hashSet.add(new ComponentName(context, MusicStateListener.class));
                a(context, hashSet);
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "setEnable e", e);
        }
    }

    private static void a(Context context, HashSet<ComponentName> hashSet) {
        try {
            if (c.c(context, "android.permission.WRITE_SECURE_SETTINGS")) {
                String string = Secure.getString(context.getContentResolver(), b);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(string);
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    ComponentName componentName = (ComponentName) it.next();
                    if (!TextUtils.isEmpty(stringBuilder.toString())) {
                        stringBuilder.append(':');
                    }
                    stringBuilder.append(componentName.flattenToString());
                }
                Secure.putString(context.getContentResolver(), b, stringBuilder.toString());
                return;
            }
            Log.e(o.a(a), "not have WRITE_SECURE_SETTINGS permission");
        } catch (Throwable e) {
            Log.e(o.a(a), "saveEnabledServices exception:", e);
        }
    }

    private static boolean c(Context context) {
        try {
            CharSequence packageName = context.getPackageName();
            Object string = Secure.getString(context.getContentResolver(), b);
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            String[] split = string.split(":");
            for (String unflattenFromString : split) {
                ComponentName unflattenFromString2 = ComponentName.unflattenFromString(unflattenFromString);
                if (unflattenFromString2 != null && TextUtils.equals(packageName, unflattenFromString2.getPackageName())) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            Log.e(o.a(a), "isEnabled exception:", e);
            return false;
        }
    }

    public static void a(Context context, ComponentName componentName) {
        try {
            if (c.c(context, "android.permission.WRITE_SECURE_SETTINGS")) {
                Object string = Secure.getString(context.getContentResolver(), b);
                if (!TextUtils.isEmpty(string)) {
                    String[] split = string.split(":");
                    StringBuffer stringBuffer = null;
                    for (int i = 0; i < split.length; i++) {
                        ComponentName unflattenFromString = ComponentName.unflattenFromString(split[i]);
                        if (unflattenFromString == null || !TextUtils.equals(componentName.getPackageName(), unflattenFromString.getPackageName()) || !TextUtils.equals(componentName.getClassName(), unflattenFromString.getClassName())) {
                            if (stringBuffer == null) {
                                stringBuffer = new StringBuffer();
                            } else {
                                stringBuffer.append(":");
                            }
                            stringBuffer.append(split[i]);
                        }
                    }
                    Secure.putString(context.getContentResolver(), b, stringBuffer != null ? stringBuffer.toString() : "");
                    return;
                }
                return;
            }
            Log.e(o.a(a), "not have WRITE_SECURE_SETTINGS permission");
        } catch (Throwable e) {
            Log.e(o.a(a), "removeEnabledServices exception:", e);
        }
    }

    public static boolean b(Context context) {
        try {
            if (VERSION.SDK_INT >= 21) {
                Class cls = Class.forName("android.media.session.MediaSessionManager");
                if (cls == null) {
                    return false;
                }
                Object obj = null;
                Constructor[] constructors = cls.getConstructors();
                if (constructors == null) {
                    return false;
                }
                for (Constructor constructor : constructors) {
                    Class[] parameterTypes = constructor.getParameterTypes();
                    if (parameterTypes.length == 1 && parameterTypes[0] == Context.class) {
                        obj = constructor.newInstance(new Object[]{context});
                    }
                }
                Method method = cls.getMethod("getActiveSessions", new Class[]{ComponentName.class});
                if (method == null || obj == null) {
                    return false;
                }
                List list = (List) method.invoke(obj, new Object[]{new ComponentName(context, MusicStateListener.class)});
                if (list == null) {
                    return false;
                }
                for (Object obj2 : list) {
                    if (obj2.getClass() == Class.forName("android.media.session.MediaController")) {
                        Method method2 = obj2.getClass().getMethod("getPlaybackState", new Class[0]);
                        if (method2 != null) {
                            Object invoke = method2.invoke(obj2, new Object[0]);
                            Class cls2 = Class.forName("android.media.session.PlaybackState");
                            if (invoke != null && invoke.getClass() == cls2) {
                                Method method3 = invoke.getClass().getMethod("getState", new Class[0]);
                                if (method3 != null) {
                                    int intValue = ((Integer) method3.invoke(invoke, new Object[0])).intValue();
                                    o.a(a, "playbackState state:" + intValue);
                                    Field field = invoke.getClass().getField("STATE_PLAYING");
                                    Field field2 = invoke.getClass().getField("STATE_BUFFERING");
                                    int i = field.getInt(invoke);
                                    int i2 = field2.getInt(invoke);
                                    if (intValue == i || intValue == i2) {
                                        return true;
                                    }
                                } else {
                                    continue;
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "isMusicPlaying e", e);
        }
        return false;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
