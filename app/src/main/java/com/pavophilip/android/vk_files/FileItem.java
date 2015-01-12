package com.pavophilip.android.vk_files;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Philip on 10.01.2015.
 */
public class FileItem {
    public String title;
    public int size;
    public String ext;
    public String url;
    public String photo_100;
    public String photo_130;
    public int icon;

    public ImageView view_icon;
    public TextView view_title;
    public TextView view_size;
    public int position;
}
