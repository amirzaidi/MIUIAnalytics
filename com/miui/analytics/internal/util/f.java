package com.miui.analytics.internal.util;

import android.util.Log;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

public final class f {
    private static final String a = "ChannelApkPacker";
    private static final String b = "UTF-8";
    private static final int c = 65535;
    private static final int d = 2;
    private static final byte[] e = new byte[]{(byte) 33, (byte) 77, (byte) 73, (byte) 65, (byte) 83, (byte) 33};

    private static boolean a(byte[] bArr) {
        if (bArr.length != e.length) {
            return false;
        }
        for (int i = 0; i < e.length; i++) {
            if (bArr[i] != e[i]) {
                return false;
            }
        }
        return true;
    }

    private static void a(byte[] bArr, DataOutput dataOutput) throws IOException {
        dataOutput.write(bArr);
    }

    private static void a(int i, DataOutput dataOutput) throws IOException {
        ByteBuffer order = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
        order.putShort((short) i);
        dataOutput.write(order.array());
    }

    private static short a(DataInput dataInput) throws IOException {
        byte[] bArr = new byte[2];
        dataInput.readFully(bArr);
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getShort(0);
    }

    public static void a(File file, String str) throws IOException {
        if (c(file)) {
            throw new IllegalStateException("zip comment already exists, ignore.");
        }
        byte[] bytes = str.getBytes(b);
        int length = (bytes.length + 2) + e.length;
        if (c > length) {
            throw new IllegalStateException("comment too long, ignore.");
        }
        DataOutput randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.seek(file.length() - 2);
        a(length, randomAccessFile);
        a(bytes, randomAccessFile);
        a(bytes.length, randomAccessFile);
        a(e, randomAccessFile);
        randomAccessFile.close();
    }

    private static boolean c(File file) throws IOException {
        Throwable th;
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, com.miui.analytics.internal.f.S);
            try {
                byte[] bArr = new byte[e.length];
                randomAccessFile.seek(randomAccessFile.length() - ((long) e.length));
                randomAccessFile.readFully(bArr);
                boolean a = a(bArr);
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                return a;
            } catch (Throwable th2) {
                th = th2;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    private static boolean a(RandomAccessFile randomAccessFile) {
        try {
            byte[] bArr = new byte[e.length];
            randomAccessFile.seek(randomAccessFile.length() - ((long) e.length));
            randomAccessFile.readFully(bArr);
            return a(bArr);
        } catch (Exception e) {
            return false;
        }
    }

    public static String a(File file) {
        RandomAccessFile randomAccessFile;
        Throwable e;
        RandomAccessFile randomAccessFile2;
        try {
            randomAccessFile2 = new RandomAccessFile(file, com.miui.analytics.internal.f.S);
            try {
                String str;
                if (a(randomAccessFile2)) {
                    byte[] bArr = new byte[e.length];
                    long length = randomAccessFile2.length() - ((long) e.length);
                    randomAccessFile2.seek(length);
                    randomAccessFile2.readFully(bArr);
                    if (a(bArr)) {
                        length -= 2;
                        randomAccessFile2.seek(length);
                        short a = a((DataInput) randomAccessFile2);
                        if (a > (short) 0) {
                            randomAccessFile2.seek(length - ((long) a));
                            byte[] bArr2 = new byte[a];
                            randomAccessFile2.readFully(bArr2);
                            str = new String(bArr2, b);
                            if (randomAccessFile2 == null) {
                                return str;
                            }
                            try {
                                randomAccessFile2.close();
                                return str;
                            } catch (Throwable e2) {
                                Log.e(o.a(a), "readZipComment e", e2);
                                return str;
                            }
                        }
                        throw new IOException("zip comment content not found");
                    }
                    throw new IOException("zip comment magic bytes not found");
                }
                str = "";
                if (randomAccessFile2 == null) {
                    return str;
                }
                try {
                    randomAccessFile2.close();
                    return str;
                } catch (Throwable e22) {
                    Log.e(o.a(a), "readZipComment e", e22);
                    return str;
                }
            } catch (Exception e3) {
                randomAccessFile = randomAccessFile2;
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (Throwable e4) {
                        Log.e(o.a(a), "readZipComment e", e4);
                    }
                }
                return null;
            } catch (Throwable th) {
                e4 = th;
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (Throwable e222) {
                        Log.e(o.a(a), "readZipComment e", e222);
                    }
                }
                throw e4;
            }
        } catch (Exception e5) {
            randomAccessFile = null;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return null;
        } catch (Throwable th2) {
            e4 = th2;
            randomAccessFile2 = null;
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            throw e4;
        }
    }

    public static String b(File file) {
        Exception e;
        RandomAccessFile randomAccessFile;
        Throwable th;
        MappedByteBuffer mappedByteBuffer = null;
        long length = file.length();
        RandomAccessFile randomAccessFile2;
        MappedByteBuffer map;
        try {
            randomAccessFile2 = new RandomAccessFile(file, com.miui.analytics.internal.f.S);
            try {
                if (a(randomAccessFile2)) {
                    map = randomAccessFile2.getChannel().map(MapMode.READ_ONLY, length - 10240, 10240);
                    try {
                        map.order(ByteOrder.LITTLE_ENDIAN);
                        byte[] bArr = new byte[e.length];
                        int length2 = 10240 - e.length;
                        map.position(length2);
                        map.get(bArr);
                        if (a(bArr)) {
                            length2 -= 2;
                            map.position(length2);
                            short s = map.getShort();
                            if (s > (short) 0) {
                                map.position(length2 - s);
                                bArr = new byte[s];
                                map.get(bArr);
                                String str = new String(bArr, b);
                                if (map != null) {
                                    map.clear();
                                }
                                if (randomAccessFile2 == null) {
                                    return str;
                                }
                                try {
                                    randomAccessFile2.close();
                                    return str;
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    return str;
                                }
                            }
                        }
                        if (map != null) {
                            map.clear();
                        }
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    } catch (Exception e4) {
                        e3 = e4;
                        randomAccessFile = randomAccessFile2;
                        try {
                            e3.printStackTrace();
                            if (map != null) {
                                map.clear();
                            }
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (Exception e32) {
                                    e32.printStackTrace();
                                }
                            }
                            return mappedByteBuffer;
                        } catch (Throwable th2) {
                            th = th2;
                            randomAccessFile2 = randomAccessFile;
                            if (map != null) {
                                map.clear();
                            }
                            if (randomAccessFile2 != null) {
                                try {
                                    randomAccessFile2.close();
                                } catch (Exception e22) {
                                    e22.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (map != null) {
                            map.clear();
                        }
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                        throw th;
                    }
                    return mappedByteBuffer;
                }
                if (mappedByteBuffer != null) {
                    mappedByteBuffer.clear();
                }
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (Exception e322) {
                        e322.printStackTrace();
                    }
                }
                return mappedByteBuffer;
            } catch (Exception e5) {
                e322 = e5;
                map = mappedByteBuffer;
                randomAccessFile = randomAccessFile2;
                e322.printStackTrace();
                if (map != null) {
                    map.clear();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                return mappedByteBuffer;
            } catch (Throwable th4) {
                th = th4;
                map = mappedByteBuffer;
                if (map != null) {
                    map.clear();
                }
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                throw th;
            }
        } catch (Exception e6) {
            e322 = e6;
            map = mappedByteBuffer;
            randomAccessFile = mappedByteBuffer;
            e322.printStackTrace();
            if (map != null) {
                map.clear();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return mappedByteBuffer;
        } catch (Throwable th5) {
            th = th5;
            map = mappedByteBuffer;
            randomAccessFile2 = mappedByteBuffer;
            if (map != null) {
                map.clear();
            }
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            throw th;
        }
    }
}
