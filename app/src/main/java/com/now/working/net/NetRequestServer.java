package com.now.working.net;

import com.now.working.data.bean.AndroidNews;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Cyj on 17/12/28.
 */

public interface NetRequestServer {
    @GET(ServiceInfo.DATA_ANDROID)
    Observable<AndroidNews> loadAndroidNews();
}
