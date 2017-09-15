package keven.cihon.mvpcsdnapp.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by zhengjian on 2017/9/15.
 */

public class LogUtils {
    private static boolean isDebug=true;
    public static void e(String message){
        if (isDebug){
            Logger.e(message);
        }
    }

}
