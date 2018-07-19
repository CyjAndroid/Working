package com.now.working.ui.viewmodel;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by chengyijun on 18/7/16.
 */

public class MainViewModel extends ViewModel {
    private MediatorLiveData<String> data;

    public MediatorLiveData<String> getData() {
        if (data == null) {
            data = new MediatorLiveData<>();
        }
        return data;
    }


}
