package com.pavophilip.android.vk_files;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.api.VKError;

/**
 * Created by Philip on 07.01.2015.
 */
public class VkListener extends VKSdkListener{
    MainActivity main;
    public VkListener(MainActivity main){
        this.main = main;
    }

    @Override
    public void onCaptchaError(VKError vkError) {

    }

    @Override
    public void onReceiveNewToken(VKAccessToken newToken) {
        this.main.user.init();
    }

    @Override
    public void onTokenExpired(VKAccessToken vkAccessToken) {
        this.main.selectSection(-1);
    }

    @Override
    public void onAccessDenied(VKError vkError) {

    }
}
