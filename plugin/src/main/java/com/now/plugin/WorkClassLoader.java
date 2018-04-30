package com.now.plugin;

import dalvik.system.DexClassLoader;

/**
 * Created by Cyj on 18/1/26.
 */

public class WorkClassLoader extends DexClassLoader {
    public WorkClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
    }
}
