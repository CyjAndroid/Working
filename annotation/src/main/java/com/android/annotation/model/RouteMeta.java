package com.android.annotation.model;


/**
 * Created by chengyijun on 18/7/10.
 */

public class RouteMeta {
    public static final String TYPE_ACTIVITY = "android.app.Activity";
    public static final String TYPE_FRAGMENT = "android.app.Fragment";
    public static final String TYPE_FRAGMENT_V4 = "android.support.v4.app.Fragment";
    public static final String TYPE_DEGRADE_SEIVICE = "com.android.cyj.router.DegradeService";

    private String classType;
    private Class<?> destination;   // Destination
    private String path;

    private RouteMeta(String url, String type, Class clazz) {
        path = url;
        classType = type;
        destination = clazz;
    }

    public static RouteMeta build(String url, String type, Class clazz) {
        return new RouteMeta(url, type, clazz);
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Class<?> getDestination() {
        return destination;
    }

    public void setDestination(Class<?> destination) {
        this.destination = destination;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
