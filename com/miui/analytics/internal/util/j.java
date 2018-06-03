package com.miui.analytics.internal.util;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class j {
    private static final String a = "FileStoreHelper";
    private String b;
    private Context c;
    private File d;

    public j(Context context, String str, String str2) {
        a(context, str, str2);
    }

    public j(Context context, String str) {
        a(context, "", str);
    }

    private void a(Context context, String str, String str2) {
        try {
            this.c = context;
            this.b = str2;
            if (TextUtils.isEmpty(str)) {
                str = this.c.getFilesDir().getAbsolutePath();
            }
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            this.d = new File(file.getAbsolutePath(), this.b);
            if (!this.d.exists()) {
                this.d.createNewFile();
            }
        } catch (Throwable e) {
            Log.e(o.a(a), "constructor e", e);
        }
    }

    public void a(String str) {
        a(false, str);
    }

    public void b(String str) {
        a(true, str);
    }

    private void a(final boolean z, final String str) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ab.a(new Runnable(this) {
                final /* synthetic */ j c;

                public void run() {
                    this.c.b(z, str);
                }
            });
        } else {
            b(z, str);
        }
    }

    private void b(boolean z, String str) {
        Throwable e;
        FileLock fileLock = null;
        RandomAccessFile randomAccessFile;
        FileChannel channel;
        try {
            if (!this.d.exists()) {
                this.d.createNewFile();
            }
            randomAccessFile = new RandomAccessFile(this.d, "rw");
            try {
                channel = randomAccessFile.getChannel();
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    while (!ac.a(currentTimeMillis, (long) g.c)) {
                        try {
                            fileLock = channel.lock();
                            break;
                        } catch (Throwable e2) {
                            Log.e(o.a(a), "save e: 有其他线程正在操作该文件，当前线程休眠1000毫秒", e2);
                            Thread.sleep(1000);
                        }
                    }
                    if (fileLock != null) {
                        if (fileLock.isValid()) {
                            if (z) {
                                randomAccessFile.seek(randomAccessFile.length());
                            }
                            randomAccessFile.writeUTF(str);
                        }
                    }
                    if (fileLock != null) {
                        try {
                            fileLock.release();
                        } catch (Throwable e22) {
                            Log.e(o.a(a), "save e", e22);
                            return;
                        }
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (Exception e3) {
                    e22 = e3;
                }
            } catch (Exception e4) {
                e22 = e4;
                channel = null;
                try {
                    Log.e(o.a(a), "save e", e22);
                    if (fileLock != null) {
                        try {
                            fileLock.release();
                        } catch (Throwable e222) {
                            Log.e(o.a(a), "save e", e222);
                            return;
                        }
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (Throwable th) {
                    e222 = th;
                    if (fileLock != null) {
                        try {
                            fileLock.release();
                        } catch (Throwable e5) {
                            Log.e(o.a(a), "save e", e5);
                            throw e222;
                        }
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw e222;
                }
            } catch (Throwable th2) {
                e222 = th2;
                channel = null;
                if (fileLock != null) {
                    fileLock.release();
                }
                if (channel != null) {
                    channel.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw e222;
            }
        } catch (Exception e6) {
            e222 = e6;
            channel = null;
            randomAccessFile = null;
            Log.e(o.a(a), "save e", e222);
            if (fileLock != null) {
                fileLock.release();
            }
            if (channel != null) {
                channel.close();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (Throwable th3) {
            e222 = th3;
            channel = null;
            randomAccessFile = null;
            if (fileLock != null) {
                fileLock.release();
            }
            if (channel != null) {
                channel.close();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw e222;
        }
    }

    public String a() {
        Throwable e;
        FileLock fileLock = null;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Log.e(o.a(a), "您现在在主线程进行了文件的读取操作，请异步执行该操作！！！");
        }
        RandomAccessFile randomAccessFile;
        FileChannel channel;
        try {
            randomAccessFile = new RandomAccessFile(this.d, "rw");
            try {
                channel = randomAccessFile.getChannel();
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    while (!ac.a(currentTimeMillis, (long) g.c)) {
                        try {
                            fileLock = channel.tryLock();
                            break;
                        } catch (Throwable e2) {
                            Log.e(o.a(a), "get e: 有其他线程正在操作该文件，当前线程休眠1000毫秒", e2);
                            Thread.sleep(1000);
                        }
                    }
                    if (fileLock != null) {
                        if (fileLock.isValid()) {
                            StringBuilder stringBuilder = new StringBuilder();
                            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                                stringBuilder.append(randomAccessFile.readUTF());
                            }
                            String stringBuilder2 = stringBuilder.toString();
                            if (fileLock != null) {
                                try {
                                    fileLock.release();
                                } catch (Throwable e3) {
                                    Log.e(o.a(a), "get e", e3);
                                    return stringBuilder2;
                                }
                            }
                            if (channel != null) {
                                channel.close();
                            }
                            if (randomAccessFile == null) {
                                return stringBuilder2;
                            }
                            randomAccessFile.close();
                            return stringBuilder2;
                        }
                    }
                    if (fileLock != null) {
                        try {
                            fileLock.release();
                        } catch (Throwable e22) {
                            Log.e(o.a(a), "get e", e22);
                        }
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (Exception e4) {
                    e22 = e4;
                }
            } catch (Exception e5) {
                e22 = e5;
                channel = null;
                try {
                    Log.e(o.a(a), "get e", e22);
                    if (fileLock != null) {
                        try {
                            fileLock.release();
                        } catch (Throwable e222) {
                            Log.e(o.a(a), "get e", e222);
                        }
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    return "";
                } catch (Throwable th) {
                    e222 = th;
                    if (fileLock != null) {
                        try {
                            fileLock.release();
                        } catch (Throwable e32) {
                            Log.e(o.a(a), "get e", e32);
                            throw e222;
                        }
                    }
                    if (channel != null) {
                        channel.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw e222;
                }
            } catch (Throwable th2) {
                e222 = th2;
                channel = null;
                if (fileLock != null) {
                    fileLock.release();
                }
                if (channel != null) {
                    channel.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw e222;
            }
        } catch (Exception e6) {
            e222 = e6;
            channel = null;
            randomAccessFile = null;
            Log.e(o.a(a), "get e", e222);
            if (fileLock != null) {
                fileLock.release();
            }
            if (channel != null) {
                channel.close();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return "";
        } catch (Throwable th3) {
            e222 = th3;
            channel = null;
            randomAccessFile = null;
            if (fileLock != null) {
                fileLock.release();
            }
            if (channel != null) {
                channel.close();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw e222;
        }
        return "";
    }

    public boolean b() {
        if (this.d == null || !this.d.exists()) {
            return false;
        }
        return this.d.delete();
    }

    public long c() {
        if (this.d == null || !this.d.exists()) {
            return 0;
        }
        return this.d.lastModified();
    }

    public String d() {
        if (this.d == null || !this.d.exists()) {
            return "";
        }
        return this.d.getAbsolutePath();
    }

    public String e() {
        return this.b;
    }
}
