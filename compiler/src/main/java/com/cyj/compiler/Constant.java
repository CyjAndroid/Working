package com.cyj.compiler;

/**
 * Created by chengyijun on 18/6/29.
 */

public class Constant {
    public static final String KEY_MODULE_NAME = "moduleName";
    public static final String AutoCreateActivityMapPrefix = "AutoCreateModuleActivityMap_";
    public static final String AutoCreateInterceptorPrefix = "AutoCreateModuleInterceptor_";
    public static final String AutoCreateActivityMapMethod = ".initRouterTable()";
    public static final String DISPATCHER_PACKAGE = "com.android.cyj.router";
    public static final String AutoCreateInterceptorPackage = "com.android.easyrouter.interceptor";

    private static final String LANG = "java.lang";
    public static final String BYTE = LANG + ".Byte";
    public static final String SHORT = LANG + ".Short";
    public static final String INTEGER = LANG + ".Integer";
    public static final String LONG = LANG + ".Long";
    public static final String FLOAT = LANG + ".Float";
    public static final String DOUBEL = LANG + ".Double";
    public static final String BOOLEAN = LANG + ".Boolean";
    public static final String STRING = LANG + ".String";
}
