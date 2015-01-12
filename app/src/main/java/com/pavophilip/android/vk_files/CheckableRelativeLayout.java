package com.pavophilip.android.vk_files;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.RelativeLayout;

/**
 * Created by Philip on 10.01.2015.
 */
public class CheckableRelativeLayout extends RelativeLayout implements Checkable {

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked,
    };

    private boolean mChecked;

    public CheckableRelativeLayout(Context context) {
        super(context);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setChecked(boolean checked) {
        if(mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

}