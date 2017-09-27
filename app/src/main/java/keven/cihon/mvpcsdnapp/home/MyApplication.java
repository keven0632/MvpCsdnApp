package keven.cihon.mvpcsdnapp.home;

import com.mob.MobApplication;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by zhengjian on 2017/9/15.
 */

public class MyApplication extends MobApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
