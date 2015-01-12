package com.pavophilip.android.vk_files;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

/**
 * Created by Philip on 07.01.2015.
 */
public class User {
    public MainActivity main;
    public String name;
    public Bitmap photo_200;
    public Bitmap photo_100;
    public Bitmap photo_50;
    public int id;

    public User(MainActivity main) {
        this.main = main;
    }

    public static void auth(){
        VKSdk.authorize(Config.SCOPE_LIST);
    }
    public void init(){
        VKRequest request = VKApi.users().get(VKParameters.from( VKApiConst.FIELDS, "photo_50, photo_100, 200"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                VKApiUser user = ((VKList<VKApiUser>)response.parsedModel).get(0);
                name = user.first_name + " " + user.last_name;
                id = user.id;
                photo_200 = Utils.getBitmapFromURL(user.photo_200);
                photo_100 = Utils.getBitmapFromURL(user.photo_100);
                photo_50 = Utils.getBitmapFromURL(user.photo_50);
                CircularImageView user_avatar = (CircularImageView) main.findViewById(R.id.user_avatar);
                TextView user_name = (TextView)main.findViewById(R.id.user_name);
                user_avatar.setImageBitmap(photo_100);
                user_name.setText(name);
                main.onUserInited();
            }

            @Override
            public void onError(VKError error) {
                main.selectSection(-1);
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {

            }
        });
    }

    public boolean authed(){
        return VKSdk.isLoggedIn();
    }
}
