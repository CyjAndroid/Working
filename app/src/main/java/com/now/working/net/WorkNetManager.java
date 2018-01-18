package com.now.working.net;

import com.now.working.net.convert.BaseConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Cyj on 17/12/28.
 */

public class WorkNetManager {
    private static WorkNetManager mInstance;

    private Retrofit.Builder mBuilder;

    private WorkNetManager() {
        mBuilder = new Retrofit.Builder()
                .baseUrl(ServiceInfo.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    public static WorkNetManager getInstance() {
        if (mInstance == null) {
            synchronized (WorkNetManager.class) {
                if (mInstance == null) {
                    mInstance = new WorkNetManager();
                }
            }
        }
        return mInstance;
    }

    public NetRequestServer getNetRequestService(){
        return mBuilder.addConverterFactory(GsonConverterFactory.create()).build().create(NetRequestServer.class);
    }

    public NetRequestServer getNetRequestService(BaseConverterFactory converterFactory){
        return mBuilder.addConverterFactory(converterFactory).build().create(NetRequestServer.class);
    }
}
