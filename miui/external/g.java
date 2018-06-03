package miui.external;

import android.content.Context;
import android.os.Build.VERSION;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

class g {
    private static final String a = "dalvik.system.DexPathList";
    private static final String b = "dalvik.system.DexPathList$Element";
    private static final String c = (VERSION.SDK_INT < 26 ? b : "dalvik.system.DexPathList$NativeLibraryElement");
    private static final String d = "dexElements";
    private static final String e = "nativeLibraryPathElements";

    g() {
    }

    public static boolean a(String str, String str2, String str3, ClassLoader classLoader) {
        return a(str, str2, str3, classLoader, null);
    }

    static boolean a(String str, String str2, String str3, ClassLoader classLoader, Context context) {
        if (str == null && (str3 == null || context == null)) {
            return false;
        }
        try {
            String str4;
            ClassLoader pathClassLoader;
            Object a = a(classLoader);
            if (str != null) {
                str4 = str;
            } else if (VERSION.SDK_INT < 23) {
                a(a, str3);
                return true;
            } else {
                str2 = null;
                str4 = context.getApplicationInfo().sourceDir;
            }
            if (str2 == null) {
                pathClassLoader = new PathClassLoader(str4, str3, classLoader.getParent());
            } else {
                pathClassLoader = new DexClassLoader(str4, str2, str3, classLoader.getParent());
            }
            Object a2 = a(pathClassLoader);
            if (str != null) {
                a(a, a2);
            }
            if (str3 != null) {
                a(a, a2, str3);
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalAccessException e2) {
            return false;
        } catch (ClassNotFoundException e3) {
            return false;
        } catch (NoSuchFieldException e4) {
            return false;
        }
    }

    private static Object a(ClassLoader classLoader) throws NoSuchFieldException {
        if (classLoader instanceof BaseDexClassLoader) {
            Field[] declaredFields = BaseDexClassLoader.class.getDeclaredFields();
            int length = declaredFields.length;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                if (a.equals(field.getType().getName())) {
                    field.setAccessible(true);
                    try {
                        return field.get(classLoader);
                    } catch (IllegalArgumentException e) {
                    } catch (IllegalAccessException e2) {
                    }
                } else {
                    i++;
                }
            }
        }
        throw new NoSuchFieldException("dexPathList field not found.");
    }

    private static void a(Object obj, Object obj2) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        a(obj, obj2, d, b);
    }

    private static void a(Object obj, Object obj2, String str) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        if (VERSION.SDK_INT >= 23) {
            a(obj, obj2, e, c);
        } else {
            a(obj, str);
        }
    }

    private static void a(Object obj, Object obj2, String str, String str2) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Object[] objArr = (Object[]) a(obj2, str, str2).get(obj2);
        Field a = a(obj, str, str2);
        Object[] objArr2 = (Object[]) a.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(Class.forName(str2), objArr2.length + 1);
        objArr3[0] = objArr[0];
        System.arraycopy(objArr2, 0, objArr3, 1, objArr2.length);
        a.set(obj, objArr3);
    }

    private static Field a(Object obj, String str, String str2) throws NoSuchFieldException {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.getName().equals(str)) {
                Class type = field.getType();
                if (type.isArray() && str2.equals(type.getComponentType().getName())) {
                    field.setAccessible(true);
                    return field;
                }
            }
        }
        throw new NoSuchFieldException(str + " field not found.");
    }

    private static void a(Object obj, String str) throws NoSuchFieldException, IllegalAccessException {
        Field a = a(obj);
        File[] fileArr = (File[]) a.get(obj);
        Object obj2 = new File[(fileArr.length + 1)];
        obj2[0] = new File(str);
        System.arraycopy(fileArr, 0, obj2, 1, fileArr.length);
        a.set(obj, obj2);
    }

    private static Field a(Object obj) throws NoSuchFieldException {
        for (Field field : obj.getClass().getDeclaredFields()) {
            Class type = field.getType();
            if (type.isArray() && type.getComponentType() == File.class) {
                field.setAccessible(true);
                return field;
            }
        }
        throw new NoSuchFieldException("nativeLibraryDirectories field not found.");
    }
}
