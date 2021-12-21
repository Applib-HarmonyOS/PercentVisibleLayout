package com.example.percentvisiblelayout;

import ohos.agp.components.AttrSet;
import ohos.agp.components.ScrollView;
import ohos.agp.utils.Rect;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

/**
 * PercentVisibleLayout.
 */
public class PercentVisibleLayout extends ScrollView {

    private static final String TAG = PercentVisibleLayout.class.getSimpleName();
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD000F00, TAG);
    private OnVisibilityPercentChanged mPercentageListener;
    private OnVisibilityPixelChanged mPixelVisibilityListener;
    private int lastPercentageWidht;
    private int lastPercentageHeight;
    private int minHorizontalPercentage = 0;
    private int maxHorizontalPercentage = 101;
    private int minVerticalPercentage = 0;
    private int maxVerticalPercentage = 101;
    private boolean duplicateEnabled = false;
    private int fromWhereHeigth;
    private int fromWhereWidht;
    private int mTop;
    private int mBottom;
    private int mRight;
    private int mLeft;
    private int width;
    private int height;
    public static final int TOP = 1;
    public static final int BOTTOM = 3;
    public static final int RIGHT = 2;
    public static final int LEFT = 4;
    public static final int LEFT_AND_RIGHT = 5;
    public static final int TOP_AND_BOTTOM = 6;
    public static final int NOWHERE = 7;
    private static final String PUBLIC = "%{public}s";
    private static final String NOT_PERCENTAGE = "Sorry not a percentage";

    public PercentVisibleLayout(Context context) {
        super(context);
    }

    /**
     * Constructor.
     *
     * @param context context
     * @param attrSet attrSet
     */
    public PercentVisibleLayout(Context context, AttrSet attrSet) {
        super(context, attrSet);
        this.mContext = context;
        this.mPercentageListener = null;
    }


    public PercentVisibleLayout(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
    }




    @Override
    public void setRotation(float rotation) {
        // to set rotation
    }

    /**
     * Calculate the visibility.
     */
    public void calculateVisibility() {


        Rect rectf = new Rect();
        this.getSelfVisibleRect(rectf);


        mTop = rectf.top;
        mBottom = rectf.bottom;
        mRight = rectf.right;
        mLeft = rectf.left;
        width = this.getWidth();
        height = this.getHeight();



        verticalCalculation();
        horizontalCalculation();



        int heightPixels = height + mTop - mBottom;
        int widthPixels = width + mLeft - mRight;

        int heightPercentage = (int) (100 - ((double) (heightPixels) / height) * 100);
        int widthPercentage = (int) (100 - ((double) (widthPixels) / width) * 100);

        if (mTop > height || mBottom > height) {
            heightPercentage = 0;
            heightPixels = 0;
        }

        if (mRight > width || mLeft > width) {
            widthPercentage = 0;
            widthPixels = 0;
        }


        if ((mPercentageListener != null
                && isBetweenHorizontalPercentageLimits(widthPercentage)
                && isBetweenVerticalPercentageLimits(heightPercentage))
                && (duplicateEnabled || (!duplicateEnabled && (lastPercentageHeight != heightPercentage
                || lastPercentageWidht != widthPercentage)))) {


            lastPercentageHeight = heightPercentage;
            lastPercentageWidht = widthPercentage;
            mPercentageListener.onVisibilityChange(fromWhereHeigth, fromWhereWidht,
                        heightPercentage, widthPercentage);

        }



        if (mPixelVisibilityListener != null) {
            mPixelVisibilityListener.onVisibilityChange(fromWhereHeigth, fromWhereWidht, heightPixels, widthPixels);
        }

    }



    private void verticalCalculation() {
        if (mTop != 0 && mBottom != height) {
            fromWhereHeigth = TOP_AND_BOTTOM;
        } else if (mTop != 0) {
            fromWhereHeigth = TOP;
        } else if (mBottom != height) {
            fromWhereHeigth = BOTTOM;
        } else {
            fromWhereHeigth = NOWHERE;
        }
    }

    private void horizontalCalculation() {
        if (mLeft != 0 && mRight != width) {
            fromWhereWidht = LEFT_AND_RIGHT;
        } else if (mLeft != 0) {
            fromWhereWidht = LEFT;
        } else if (mRight != width) {
            fromWhereWidht = RIGHT;
        } else {
            fromWhereWidht = NOWHERE;
        }
    }


    /**
     * To set minimum horizontal percentage.
     *
     * @param perc perc
     */
    public void setMinHortizontalPercentage(int perc) {
        if (perc >= 0 && perc <= 100) {
            this.minHorizontalPercentage = perc;
        } else {
            HiLog.debug(LABEL_LOG, PUBLIC, NOT_PERCENTAGE);
        }

    }


    /**
     * To set maximum horizontal percentage.
     *
     * @param perc perc
     */
    public void setMaxHorizontalPercentage(int perc) {
        if (perc >= 0 && perc <= 100) {
            this.maxHorizontalPercentage = perc;
        } else {
            HiLog.debug(LABEL_LOG, PUBLIC, NOT_PERCENTAGE);
        }

    }

    /**
     * To set minimum vertical percentage.
     *
     * @param perc perc
     */
    public void setMinVerticalPercentage(int perc) {
        if (perc >= 0 && perc <= 100) {
            this.minVerticalPercentage = perc;
        } else {
            HiLog.debug(LABEL_LOG, PUBLIC, NOT_PERCENTAGE);
        }

    }

    /**
     * To set maximum vertical percentage.
     *
     * @param perc perc
     */
    public void setMaxVerticalPercentage(int perc) {
        if (perc >= 0 && perc <= 100) {
            this.maxVerticalPercentage = perc;
        } else {
            HiLog.debug(LABEL_LOG, PUBLIC, NOT_PERCENTAGE);
        }

    }


    /**
     * To reset percentage limits.
     */
    public void resetPercentageLimits() {
        this.minHorizontalPercentage = 0;
        this.maxHorizontalPercentage = 101;
        this.minVerticalPercentage = 0;
        this.maxVerticalPercentage = 101;
    }

    public void allowDuplicates(boolean bool) {
        this.duplicateEnabled = bool;

    }


    private boolean isBetweenHorizontalPercentageLimits(int a) {
        return  (a <= maxHorizontalPercentage && a >= minHorizontalPercentage);
    }


    private boolean isBetweenVerticalPercentageLimits(int a) {
        return  (a <= maxVerticalPercentage && a >= minVerticalPercentage);
    }




    /**
     * Callback method on visibility percent change.
     */
    public interface OnVisibilityPercentChanged {
        void onVisibilityChange(int verticalClip, int horizontalClip, int percentageHeight, int percentageWidth);
    }

    public void setOnVisibilityPercentChangedListener(OnVisibilityPercentChanged eventListener) {
        mPercentageListener = eventListener;
    }

    public void removeVisibilityPercentageListener() {
        mPercentageListener = null;
    }





    /**
     * Callback method on visibility pixel change.
     */
    public interface OnVisibilityPixelChanged {
        void onVisibilityChange(int verticalClip, int horizontalClip, int pixelHeight, int pixelWidth);
    }

    public void setOnVisibilityPixelChangedListener(OnVisibilityPixelChanged eventListener) {
        mPixelVisibilityListener = eventListener;
    }

    public void removePixelPercentageListener() {
        mPixelVisibilityListener = null;
    }



}

