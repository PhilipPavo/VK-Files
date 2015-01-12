package com.pavophilip.android.vk_files;

import com.vk.sdk.VKScope;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Philip on 07.01.2015.
 */
public class Config {
    public static String APP_ID = "4719092";
    public static String[] SCOPE_LIST = {
            VKScope.DOCS,
            VKScope.FRIENDS,
            VKScope.GROUPS,
            VKScope.NOTES,
            VKScope.PHOTOS,
            VKScope.WALL
    };
}
