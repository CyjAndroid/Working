package com.now.plugin;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.now.plugin.hook.PMSHookHelper;
import com.now.plugin.utils.Utils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cyj on 18/1/26.
 */

public class PluginHelper {
    private static Context mContext;
    private static Map<String, Object> mLoadedApk = new HashMap<String, Object>();

    public static Object getCurrentThread() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method method = activityThreadClass.getDeclaredMethod("currentActivityThread");
            method.setAccessible(true);
            return method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载插件
     * public final LoadedApk getPackageInfoNoCheck(ApplicationInfo ai,
     * CompatibilityInfo compatInfo) {
     * }
     */
    public static void loadPlugin(Context context, File apkFile) {
        if (!PluginApplication.isHookPMS()) {
            PMSHookHelper.hook();
        }
        try {
            mContext = context;

            // 先获取到当前的ActivityThread对象
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);

            // 获取到 mPackages 这个静态成员变量, 这里缓存了dex包的信息
            Field mPackagesField = activityThreadClass.getDeclaredField("mPackages");
            mPackagesField.setAccessible(true);
            //这个map中key是包名，value是LoadedAPK对象
            Map mPackages = (Map) mPackagesField.get(currentActivityThread);
            //先获取ApplicationInfo对象
            ApplicationInfo applicationInfo = getApplicationInfo(apkFile);

            Class<?> compatibilityInfoClass = Class.forName("android.content.res.CompatibilityInfo");
            Method getPackageInfoNoCheckMethod = activityThreadClass
                    .getDeclaredMethod("getPackageInfoNoCheck", ApplicationInfo.class, compatibilityInfoClass);
            Field defaultCompatibilityInfoField = compatibilityInfoClass.getDeclaredField("DEFAULT_COMPATIBILITY_INFO");
            defaultCompatibilityInfoField.setAccessible(true);
            Object compatInfo = defaultCompatibilityInfoField.get(null);

            Object loadedApk = getPackageInfoNoCheckMethod.invoke(activityThreadClass, applicationInfo, compatInfo);

            String odexPath = Utils.getPluginOptDexDir(mContext, applicationInfo.packageName).getPath();
            String libDir = Utils.getPluginLibDir(mContext, applicationInfo.packageName).getPath();

            ClassLoader classLoader = new WorkClassLoader(apkFile.getPath(), odexPath, libDir, ClassLoader.getSystemClassLoader());
            Field mClassLoaderField = loadedApk.getClass().getDeclaredField("mClassLoader");
            mClassLoaderField.setAccessible(true);
            mClassLoaderField.set(loadedApk, classLoader);

            mLoadedApk.put(applicationInfo.packageName, loadedApk);
            WeakReference weakReference = new WeakReference(loadedApk);
            mPackages.put(applicationInfo.packageName, weakReference);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * public static ApplicationInfo generateApplicationInfo(Package p, int flags,
     * PackageUserState state) {
     */
    public static ApplicationInfo getApplicationInfo(File apkFile) {
        try {
            Class<?> packageParserClass = Class.forName("android.content.pm.PackageParser");
            Class<?> packageClass = Class.forName("android.content.pm.PackageParser$Package");
            Class<?> packageUserStateClass = Class.forName("android.content.pm.PackageUserState");
            Method generateApplicationInfoMethod = packageParserClass.getDeclaredMethod("generateApplicationInfo",
                    packageClass,
                    int.class,
                    packageUserStateClass);

            //Package parsePackage(File packageFile, int flags)
            Object packageParser = packageParserClass.newInstance();
            Method parsePackageMethod = packageParserClass.getDeclaredMethod("parsePackage", Field.class, int.class);
            Object packageObj = parsePackageMethod.invoke(packageParser, apkFile, 0);
            Object defaultPackageUserState = packageUserStateClass.newInstance();
            ApplicationInfo info = (ApplicationInfo) generateApplicationInfoMethod
                    .invoke(packageParser, packageObj, 0, defaultPackageUserState);
            String path = apkFile.getAbsolutePath();
            info.sourceDir = path;
            info.publicSourceDir = path;

            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
