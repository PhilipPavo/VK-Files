package com.pavophilip.android.vk_files;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Philip on 09.01.2015.
 */
public class RoundedImageView extends ImageView {

    public RoundedImageView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Bitmap b =  ((BitmapDrawable)drawable).getBitmap() ;
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth(), h = getHeight();


        Bitmap roundBitmap =  getCroppedBitmap(bitmap, w, 0);
        canvas.drawBitmap(roundBitmap, 0,0, null);

    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius, int border_w) {
        Bitmap sbmp;
        if(bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#FFFFFF"));
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        canvas.drawCircle(sbmp.getWidth() / 2 + border_w / 2, sbmp.getHeight() / 2 + border_w/2,
                sbmp.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        Paint border;

        border = new Paint(Paint.ANTI_ALIAS_FLAG);
        border.setColor(android.graphics.Color.WHITE);
        border.setStyle(Paint.Style.STROKE);
        border.setStrokeWidth(border_w);
        border.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);
        canvas.drawCircle(radius/2,radius/2,radius /2 - 2.0f, border);

        return output;
    }

}