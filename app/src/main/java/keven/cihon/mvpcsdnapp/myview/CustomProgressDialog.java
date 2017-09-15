package keven.cihon.mvpcsdnapp.myview;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import keven.cihon.mvpcsdnapp.R;

public class CustomProgressDialog extends Dialog {
    /**
     * 弹出自定义ProgressDialog
     *
     * @param context
     * 上下�?
     * @param message
     * 提示
     * @param cancelable
     * 是否按返回键取消
     * @param cancelListener
     * 按下返回键监�?
     * @return
     */
    CustomProgressDialog dialog;
    private Context context;
    private List<Dialog> list = new ArrayList<Dialog>();

    public CustomProgressDialog(Context context) {
        super(context);
        this.context = context;
    }


    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = (TextView) findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();
        }
    }

    public CustomProgressDialog show(CharSequence message, boolean cancelable, OnCancelListener cancelListener) {
        dialog = new CustomProgressDialog(context, R.style.QuanDialog);
        dialog.setTitle("");
        dialog.setContentView(R.layout.my_progress_dialog);

        if (message == null || message.length() == 0) {
            dialog.findViewById(R.id.message).setVisibility(View.GONE);
        } else {
            TextView txt = (TextView) dialog.findViewById(R.id.message);
            txt.setText(message);
        }
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.5f;
        lp.alpha = 0.5f;
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        if (context != null) {
            if (!((Activity) context).isFinishing()) {
                // dialog.show();
            }
        }

//		    dialog.show();
        return dialog;
    }

    public void disMiss() {
        if (dialog != null && dialog.isShowing()) {
//
            if (context != null) {
                if (!((Activity) context).isFinishing()) {
                    dialog.dismiss();
                    dialog.cancel();
                }
            }

        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (!hasFocus) {
            dismiss();
        }
    }
}
