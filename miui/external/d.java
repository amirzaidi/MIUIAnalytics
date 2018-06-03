package miui.external;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import miui.external.SdkConstants.SdkError;

public class d extends Activity implements SdkConstants {
    private String b;
    private OnClickListener c = new OnClickListener(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            this.a.finish();
            System.exit(0);
        }
    };
    private OnClickListener d = new OnClickListener(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            final Dialog a = this.a.d();
            new a(this.a, a).show(this.a.getFragmentManager(), "SdkUpdatePromptDialog");
            new AsyncTask<Void, Void, Boolean>(this) {
                final /* synthetic */ AnonymousClass2 b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((Boolean) obj);
                }

                protected Boolean a(Void... voidArr) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return Boolean.valueOf(this.b.a.h());
                }

                protected void a(Boolean bool) {
                    a.dismiss();
                    new a(this.b.a, bool.booleanValue() ? this.b.a.e() : this.b.a.f()).show(this.b.a.getFragmentManager(), "SdkUpdateFinishDialog");
                }
            }.execute(new Void[0]);
        }
    };

    class a extends DialogFragment {
        final /* synthetic */ d a;
        private Dialog b;

        public a(d dVar, Dialog dialog) {
            this.a = dVar;
            this.b = dialog;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            return this.b;
        }
    }

    protected void onCreate(Bundle bundle) {
        Dialog b;
        setTheme(16973909);
        super.onCreate(bundle);
        this.b = Locale.getDefault().getLanguage();
        SdkError sdkError = null;
        Intent intent = getIntent();
        if (intent != null) {
            sdkError = (SdkError) intent.getSerializableExtra(SdkError.d);
        }
        if (sdkError == null) {
            sdkError = SdkError.GENERIC;
        }
        switch (sdkError) {
            case NO_SDK:
                b = b();
                break;
            case LOW_SDK_VERSION:
                b = c();
                break;
            default:
                b = a();
                break;
        }
        new a(this, b).show(getFragmentManager(), "SdkErrorPromptDialog");
    }

    private Dialog a(String str, String str2, OnClickListener onClickListener) {
        return new Builder(this).setTitle(str).setMessage(str2).setPositiveButton(17039370, onClickListener).setIcon(17301543).setCancelable(false).create();
    }

    private Dialog a(String str, String str2, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return new Builder(this).setTitle(str).setMessage(str2).setPositiveButton(17039370, onClickListener).setNegativeButton(17039360, onClickListener2).setIcon(17301543).setCancelable(false).create();
    }

    private Dialog a() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.b)) {
            str = "MIUI SDK发生错误";
            str2 = "请重新安装MIUI SDK再运行本程序。";
        } else {
            str = "MIUI SDK encounter errors";
            str2 = "Please re-install MIUI SDK and then re-run this application.";
        }
        return a(str, str2, this.c);
    }

    private Dialog b() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.b)) {
            str = "没有找到MIUI SDK";
            str2 = "请先安装MIUI SDK再运行本程序。";
        } else {
            str = "MIUI SDK not found";
            str2 = "Please install MIUI SDK and then re-run this application.";
        }
        return a(str, str2, this.c);
    }

    private Dialog c() {
        String str;
        String str2;
        if (g()) {
            if (Locale.CHINESE.getLanguage().equals(this.b)) {
                str = "MIUI SDK版本过低";
                str2 = "请先升级MIUI SDK再运行本程序。是否现在升级？";
            } else {
                str = "MIUI SDK too old";
                str2 = "Please upgrade MIUI SDK and then re-run this application. Upgrade now?";
            }
            return a(str, str2, this.d, this.c);
        }
        if (Locale.CHINESE.getLanguage().equals(this.b)) {
            str = "MIUI SDK版本过低";
            str2 = "请先升级MIUI SDK再运行本程序。";
        } else {
            str = "MIUI SDK too old";
            str2 = "Please upgrade MIUI SDK and then re-run this application.";
        }
        return a(str, str2, this.c);
    }

    private Dialog d() {
        CharSequence charSequence;
        CharSequence charSequence2;
        if (Locale.CHINESE.getLanguage().equals(this.b)) {
            charSequence = "MIUI SDK正在更新";
            charSequence2 = "请稍候...";
        } else {
            charSequence = "MIUI SDK updating";
            charSequence2 = "Please wait...";
        }
        return ProgressDialog.show(this, charSequence, charSequence2, true, false);
    }

    private Dialog e() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.b)) {
            str = "MIUI SDK更新完成";
            str2 = "请重新运行本程序。";
        } else {
            str = "MIUI SDK updated";
            str2 = "Please re-run this application.";
        }
        return a(str, str2, this.c);
    }

    private Dialog f() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.b)) {
            str = "MIUI SDK更新失败";
            str2 = "请稍后重试。";
        } else {
            str = "MIUI SDK update failed";
            str2 = "Please try it later.";
        }
        return a(str, str2, this.c);
    }

    private boolean g() {
        try {
            return ((Boolean) c.a().getMethod("supportUpdate", new Class[]{Map.class}).invoke(null, new Object[]{null})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean h() {
        try {
            HashMap hashMap = new HashMap();
            return ((Boolean) c.a().getMethod("update", new Class[]{Map.class}).invoke(null, new Object[]{hashMap})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
