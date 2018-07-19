package com.now.working.loader;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by chengyijun on 18/7/19.
 */

public class WorkLoader {
    private static final String CONFIG_FILE_NAME = "service_config.txt";

    public static <S> S load(Context context, Class<S> service) throws Exception {
        Object serviceInstance = null;
        try {
            InputStreamReader inputReader =
                    new InputStreamReader(context.getResources().getAssets().open(CONFIG_FILE_NAME));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                if (line.contains(":")) {
                    String[] services = line.split(":");
                    if (services.length == 2) {
                        Class superService = WorkLoader.class.getClassLoader().loadClass(services[0]);
                        if (service == superService) {
                            Class serviceClass = WorkLoader.class.getClassLoader().loadClass(services[1]);
                            serviceInstance = serviceClass.newInstance();
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (S) serviceInstance;

    }

}
