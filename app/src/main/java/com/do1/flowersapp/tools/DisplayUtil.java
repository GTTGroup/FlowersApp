package com.do1.flowersapp.tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DisplayUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getDeviceWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getDeviceHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 判断用户设备是否为平板
     *
     * @return
     */
    public static final boolean isPad(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();

        float screenWidth = metrics.widthPixels;
        // 屏幕高度
        float screenHeight = metrics.heightPixels;
        double x = Math.pow(screenWidth / metrics.xdpi, 2);
        double y = Math.pow(screenHeight / metrics.ydpi, 2);
        // 屏幕尺寸
        double screenInches = Math.sqrt(x + y);
        // 大于6尺寸则为Pad
        if (screenInches >= 6.0) {
            return true;
        }
        return false;
    }

//    private static final String TAG = "DisplayUtil";
//    // 判断是否为oppo的 ColorOS ROM
//    public static final String OPPO_COLOR_OS = "persist.oppo.opporom";
//    /**
//     * 全局手机尺寸、密度等手机配置信息
//     */
//    protected static DisplayMetrics sMetrics;
//
//    public static DisplayMetrics getMetrics(Context context) {
//        return sMetrics != null ? sMetrics : getResource(context).getDisplayMetrics();
//    }
//
//    /**
//     * 获取屏幕密度
//     *
//     * @param context
//     * @return
//     */
//    public static float getDensity() {
//        return LTApplication.getMetrics(LTApplication.getContext()).density;
//    }
//
//    /**
//     * DIP转PX
//     *
//     * @param context
//     * @param dip
//     * @return
//     */
//    public static int convertDipOrPx(double dip) {
//        float scale = LTApplication.getMetrics(LTApplication.getContext()).density;
//        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
//    }
//
//    /**
//     * PX转DIP
//     *
//     * @param context
//     * @param px
//     * @return
//     */
//    public static int convertPxOrDip(int px) {
//        float scale = LTApplication.getMetrics(LTApplication.getContext()).density;
//        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
//    }
//
//    /**
//     * sp转px
//     *
//     * @param context
//     * @param px
//     * @return
//     */
//    public static int convertSp2Px(int sp) {
//        float fontScale = LTApplication.getMetrics(LTApplication.getContext()).scaledDensity;
//        return (int) (sp * fontScale + 0.5f);
//    }
//
//    /**
//     * 判断用户设备是否为平板
//     *
//     * @return
//     */
//    public static final boolean isPad() {
//        DisplayMetrics metrics = LTApplication.getMetrics(LTApplication.getContext());
//
//        float screenWidth = metrics.widthPixels;
//        // 屏幕高度
//        float screenHeight = metrics.heightPixels;
//        double x = Math.pow(screenWidth / metrics.xdpi, 2);
//        double y = Math.pow(screenHeight / metrics.ydpi, 2);
//        // 屏幕尺寸
//        double screenInches = Math.sqrt(x + y);
//        // 大于6尺寸则为Pad
//        if (screenInches >= 6.0) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 过滤HTML文本标签
//     */
//    public static String htmlFormatText(String content) {
//        if (TextUtils.isEmpty(content))
//            return content;
//        content = Html.fromHtml(content).toString();
//        if (PPBuildConfig.DEBUG)
//            Log.v(TAG, "htmlFormatText content:" + content);
//        int start = 0;
//        int end = content.length();
//        while (start < end) {
//            char ch = content.charAt(start);
//            if (ch != '\n' && ch != ' ' && ch != '\t' && ch != 160 && ch != 12288)
//                break;
//            start++;
//        }
//
//        while (end > start) {
//            char ch = content.charAt(end - 1);
//            if (ch != '\n' && ch != ' ' && ch != '\t' && ch != 160 && ch != 12288) // 换行,
//                // 空格,tab,HTML空格
//                break;
//            end--;
//        }
//
//        boolean append = true;
//        StringBuilder sb = new StringBuilder(end - start + 1);
//        for (int i = start; i < end; i++) {
//            char ch = content.charAt(i);
//            if (append) {
//                sb.append(ch);
//                if (ch == '\n')
//                    append = false;
//            } else if (ch != ' ' && ch != 160 && ch != '\n' && ch != '\t' && ch != 12288) {
//                sb.append(ch);
//                append = true;
//            }
//        }
//        return sb.toString();
//    }
//
//    /**
//     * 判断手机ROM是否为Color OS
//     *
//     * @return
//     */
//    public static boolean isColorOS() {
//        // 判断是否为Color OS，适配Color OS的通知栏样式
//        // String isColorOS = PPPhoneTools.getSystemProperty(OPPO_COLOR_OS);
//        return Boolean.parseBoolean("");
//    }
//
//    /**
//     * 获取字体的宽度
//     *
//     * @param text
//     * @param Size 字体的sp单位
//     * @return 计算后字体宽度
//     */
//    public static float getTextWidth(String text, float Size) { // 第一个参数是要计算的字符串，第二个参数是字提大小
//        TextPaint FontPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
//        float fontScale = LTApplication.getMetrics(LTApplication.getContext()).scaledDensity;
//        FontPaint.setTextSize(fontScale * Size);
//        return FontPaint.measureText(text);
//
//    }
//
//    /**
//     * 获取字体的宽度
//     *
//     * @param text
//     * @param Size 字体的px单位
//     * @return 计算后字体宽度
//     */
//    public static float getPxTextWidth(String text, float Size) { // 第一个参数是要计算的字符串，第二个参数是字提大小
//        TextPaint FontPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
//        FontPaint.setTextSize(Size);
//        return FontPaint.measureText(text);
//
//    }
//
//    /**
//     * 广告高度比例
//     *
//     * @return
//     */
//    public static float getADHeightRate() {
//        // return PPApplication.isPad() ? 0.36458f : 0.396875f; // TODO:
//        // 0.36458f;
//        return 0.61111112f;
//        // return LTApplication.isPad() ? 0.36458f : 0.61111112f; // TODO:
//        // 0.36458f;
//    }
//
//    /**
//     * 广告高度比例
//     *
//     * @return
//     */
//    public static float getHomeADHeightRate() {
//        // return PPApplication.isPad() ? 0.36458f : 0.396875f; // TODO:
//        // 0.36458f;
//        return 1.142857143f;
//        // return LTApplication.isPad() ? 0.36458f : 0.61111112f; // TODO:
//        // 0.36458f;
//    }
}
