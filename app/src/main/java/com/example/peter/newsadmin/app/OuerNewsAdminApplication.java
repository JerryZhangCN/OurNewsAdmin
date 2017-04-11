
package com.example.peter.newsadmin.app;

import android.app.Application;

/**
 * created by byron on 2016/12/18
 *
 * 全局application
 */
public class OuerNewsAdminApplication extends Application {

    private static final String TAG = "BizConfApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationLogic();
    }

    private void initApplicationLogic() {
        AppContext.init(this);
    }

}
