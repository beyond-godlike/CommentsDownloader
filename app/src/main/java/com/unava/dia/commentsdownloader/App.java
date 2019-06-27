package com.unava.dia.commentsdownloader;

import android.app.Application;

import com.unava.dia.commentsdownloader.network.DaggerNetComponent;
import com.unava.dia.commentsdownloader.network.NetComponent;
import com.unava.dia.commentsdownloader.network.NetModule;

public class App extends Application {
    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // after building
         netComponent = DaggerNetComponent
                .builder()
                .netModule(new NetModule())
                .build();


    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}