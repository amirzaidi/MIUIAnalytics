package com.miui.analytics.internal.collection;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.k;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ac;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public class i {
    private static final String a = "UploadNetworkFlow";
    private static final String b = "a";
    private static final String c = "s";
    private static final String d = "h";
    private static final String e = "t";
    private static final String f = "k";
    private static final String g = "n";
    private static volatile i h = null;
    private static final String j = "flow_";
    private Context i;
    private String k;

    private class a {
        public String a;
        public String b;
        public String c;
        final /* synthetic */ i d;

        private a(i iVar) {
            this.d = iVar;
            this.a = "";
            this.b = "";
            this.c = "";
        }
    }

    public static i a(Context context) {
        if (h == null) {
            synchronized (i.class) {
                h = new i(context);
            }
        }
        return h;
    }

    private i(Context context) {
        this.i = context;
    }

    public void a(String str, String str2, String str3, String str4, String str5, long j) {
        try {
            if ((n.b() || n.c()) && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str5)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(b, str);
                jSONObject.put(f, str2);
                jSONObject.put("n", str3);
                jSONObject.put("s", str4.getBytes().length);
                jSONObject.put(d, str5.startsWith("https"));
                jSONObject.put("t", j);
                a(jSONObject.toString());
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "monitorNetFlow e", e);
        }
    }

    public void a() {
        if (n.b() || n.c()) {
            ab.a(new Runnable(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        o.a(i.a, "start upload net flow.");
                        List<a> a = this.a.c();
                        if (a != null && a.size() > 0) {
                            for (a aVar : a) {
                                if (!(aVar == null || TextUtils.isEmpty(aVar.c))) {
                                    k.a(this.a.i).a(new LogEvent(this.a.i, "com.miui.analytics", f.p, aVar.c));
                                    o.a(i.a, "upload net flow data:" + aVar.c);
                                    this.a.b(aVar.b);
                                }
                            }
                        }
                    } catch (Throwable e) {
                        Log.e(o.a(i.a), "upload exception:", e);
                    }
                }
            });
        }
    }

    private String b() {
        if (TextUtils.isEmpty(this.k) && this.i != null) {
            this.k = this.i.getFilesDir() + "/netFlow";
        }
        return this.k;
    }

    private boolean a(String str) {
        Throwable e;
        BufferedWriter bufferedWriter = null;
        try {
            if (TextUtils.isEmpty(str) || this.i == null) {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (Throwable e2) {
                        Log.e(o.a(a), "save bufferedWriter close exception", e2);
                    }
                }
                return false;
            }
            File file = new File(b());
            if (!file.exists()) {
                file.mkdir();
            }
            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(new File(file.getAbsolutePath(), j + new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()))), true));
            try {
                bufferedWriter2.write(str);
                bufferedWriter2.flush();
                if (bufferedWriter2 == null) {
                    return true;
                }
                try {
                    bufferedWriter2.close();
                    return true;
                } catch (Throwable e3) {
                    Log.e(o.a(a), "save bufferedWriter close exception", e3);
                    return true;
                }
            } catch (Exception e4) {
                e2 = e4;
                bufferedWriter = bufferedWriter2;
                try {
                    Log.e(o.a(a), "save exception", e2);
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Throwable e22) {
                            Log.e(o.a(a), "save bufferedWriter close exception", e22);
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    e22 = th;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Throwable e32) {
                            Log.e(o.a(a), "save bufferedWriter close exception", e32);
                        }
                    }
                    throw e22;
                }
            } catch (Throwable th2) {
                e22 = th2;
                bufferedWriter = bufferedWriter2;
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                throw e22;
            }
        } catch (Exception e5) {
            e22 = e5;
            Log.e(o.a(a), "save exception", e22);
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            return false;
        }
    }

    private ArrayList<a> c() {
        BufferedReader bufferedReader;
        Throwable e;
        Throwable e2;
        File file = new File(b());
        if (file != null) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                ArrayList<a> arrayList = new ArrayList();
                for (File file2 : listFiles) {
                    if (file2.isFile()) {
                        long lastModified = file2.lastModified();
                        if (ac.a(lastModified)) {
                            continue;
                        } else if (System.currentTimeMillis() - lastModified > 259200000) {
                            a(file2);
                            o.a(a, "flow数据已经超过3天，直接删除不再上报:" + file2.getAbsolutePath());
                        } else {
                            try {
                                bufferedReader = new BufferedReader(new FileReader(file2));
                                try {
                                    a aVar = new a();
                                    while (true) {
                                        String readLine = bufferedReader.readLine();
                                        if (readLine == null) {
                                            break;
                                        }
                                        aVar.c += readLine;
                                    }
                                    aVar.a = file2.getName();
                                    aVar.b = file2.getAbsolutePath();
                                    arrayList.add(aVar);
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (IOException e3) {
                                            try {
                                                e3.printStackTrace();
                                            } catch (Throwable e4) {
                                                Log.e(o.a(a), "getNotUploadFlowExceptToday exception", e4);
                                            }
                                        }
                                    } else {
                                        continue;
                                    }
                                } catch (Exception e5) {
                                    e2 = e5;
                                    try {
                                        Log.e(o.a(a), "getNotUploadFlowExceptToday exception", e2);
                                        if (bufferedReader == null) {
                                            try {
                                                bufferedReader.close();
                                            } catch (IOException e32) {
                                                e32.printStackTrace();
                                            }
                                        } else {
                                            continue;
                                        }
                                    } catch (Throwable th) {
                                        e4 = th;
                                    }
                                }
                            } catch (Exception e6) {
                                e2 = e6;
                                bufferedReader = null;
                                Log.e(o.a(a), "getNotUploadFlowExceptToday exception", e2);
                                if (bufferedReader == null) {
                                    continue;
                                } else {
                                    bufferedReader.close();
                                }
                            } catch (Throwable th2) {
                                e4 = th2;
                                bufferedReader = null;
                            }
                        }
                    }
                }
                return arrayList;
            }
        }
        return null;
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e322) {
                e322.printStackTrace();
            }
        }
        throw e4;
        throw e4;
    }

    private boolean a(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        o.a(a, "delete file:" + file.getAbsolutePath());
        return file.delete();
    }

    private boolean b(String str) {
        return a(new File(str));
    }
}
