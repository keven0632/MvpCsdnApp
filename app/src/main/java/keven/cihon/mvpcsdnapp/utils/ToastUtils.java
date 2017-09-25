package keven.cihon.mvpcsdnapp.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by zhengjian on 2017/9/25.
 */

public class ToastUtils {

    private static long currentTime;
    private static long lastTime;

    /**
     * Toast提示
     *
     * @param message
     */
    public static void showToast(Context context, String message) {
        currentTime = System.currentTimeMillis() / 1000;
        if (currentTime - lastTime > 1) {
            lastTime = System.currentTimeMillis() / 1000;
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.getView().setAlpha(0.5f);
            toast.show() ;
        }
    }
}
