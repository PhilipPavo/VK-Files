package com.pavophilip.android.vk_files;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Philip on 11.01.2015.
 */
public class CustomDialog  extends Dialog{
    public static int SIZE_BIG = 300;
    public static int SIZE_MEDIUM = 200;
    public static int SIZE_SMALL = 100;
    public CustomDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_custom);
    }
    public CustomDialog setText(String s){
        TextView title = (TextView) findViewById(R.id.dialog_text);
        title.setVisibility(View.VISIBLE);
        title.setText(s);
        return this;
    }
    public CustomDialog setCustomContent(View v, int size){
        ViewGroup container = (ViewGroup) findViewById(R.id.dialog_content);
        container.addView(v);
        container.getLayoutParams().width = SIZE_MEDIUM;
        return this;
    }
    public CustomDialog setCustomTitle(String s) {
        TextView title = (TextView) findViewById(R.id.dialog_title);
        title.setVisibility(View.VISIBLE);
        title.setText(s);
        return this;
    }
    public CustomDialog setCancelButton(String s){
        Button btn = (Button) findViewById(R.id.dialog_cancel);
        btn.setVisibility(View.VISIBLE);
        btn.setText(s);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return this;
    }
    public CustomDialog setOkayButton(String s){
        Button btn = (Button) findViewById(R.id.dialog_okay);
        btn.setVisibility(View.VISIBLE);
        btn.setText(s);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return this;
    }
}
