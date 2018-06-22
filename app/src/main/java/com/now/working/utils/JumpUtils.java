package com.now.working.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by chengyijun on 18/6/22.
 */

public class JumpUtils {
    public static final String SCHEME_ANTIVITY_TEST = "lib://com.cyj.http/activity/test";

    public static void intentActivity(Context context, String scheme) {
        Uri uri = Uri.parse(scheme);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
