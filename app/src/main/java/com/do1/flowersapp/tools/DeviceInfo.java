/**  company:   www.163.com
 *                      在线游戏事业部 
 *                      回声工作室
 *                      程序组
 *    工作编号:    G7525
 *    creator:      谷峰
 *    create on:  2014年12月16日   下午6:36:58
 *          坚持不懈,终会成功
 */
package com.do1.flowersapp.tools;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取设备相关
 * @author G7525
 *
 */
public class DeviceInfo {
	
	/**
	 * 检测sdcard是否可用
	 * 
	 * @return true为可用，否则为不可用
	 */
	public static boolean sdCardIsAvailable() {
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED))
			return false;
		return true;
	}
	
	/**
	 *    获取屏幕宽度
	 *    creator:         谷峰
	 *    created on:   2014年3月3日下午5:50:58
	 */
	public static int getDeviceWidth(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(mDisplayMetrics);
		return mDisplayMetrics.widthPixels;
	}
	
	/**
	 *   获取屏幕高度
	 *    creator:         谷峰
	 *    created on:   2014年3月3日下午5:51:12
	 */
	public static int getDeviceHeight(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(mDisplayMetrics);
		return mDisplayMetrics.heightPixels;
	}
	
	/**
	 *    获取IMSI
	 *    creator:         谷峰
	 *    created on:   2014年2月10日上午11:33:31
	 */
	public static String getIMSI(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getSubscriberId();
	}
	
	/**
	 *    获取IMEI
	 *    creator:         谷峰
	 *    created on:   2014年2月10日上午11:55:58
	 */
	public static String getIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}
	
	/**
	 *    获取设备的Android版本
	 *    creator:         谷峰
	 *    created on:   2014年2月10日上午11:54:32
	 */
	public static int getDeviceVersion(){
		return Build.VERSION.SDK_INT;
	}
	
	/**
	 * 获取系统版本名称
	 * 
	 * @return
	 */
	public static String getSdkVersion() {
		return Build.VERSION.RELEASE;
	}

	/**
	 *    获取设备的型号
	 *    creator:         谷峰
	 *    created on:   2014年2月10日上午11:54:47
	 */
	public static String getModelName() {
		return Build.MODEL;
	}
	
	/**
	 * @return 获取设备完整名称：手机厂商  + 手机型号
	 */
	public static String getDeviceName() {
		return Build.MANUFACTURER + " " + Build.MODEL;
	}
	
	/**
	 * 获取应用当前版本名称
	 *    creator:         谷峰
	 *    created on:   2014年2月10日上午11:54:47
	 */
	public static String getAppVersionName(Context ctx) {
		String versionname = "";
		try {
			PackageInfo pi = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
			versionname = pi.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return versionname;
	}
	
	/**
	 * 获取应用当前版本号
	 *    creator:         谷峰
	 *    created on:   2014年2月10日上午11:54:47
	 */
	public static int getAppVersionCode(Context ctx) {
		int versioncode = 0;
		try {
			PackageInfo pi = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
			versioncode = pi.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return versioncode;
	}
	
	/**
	 * 获取已安装的应用
	 * @param ctx
	 */
	public static List<PackageInfo> getInstalledApps(Context ctx) {
		List<PackageInfo> installedAppList = new ArrayList<PackageInfo>();
	    List<PackageInfo> appList = ctx.getPackageManager().getInstalledPackages(0);
	    for(PackageInfo _package:appList) {
	    	if((_package.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==0) {
	    		installedAppList.add(_package);
	    	}
	    }
	    
	    return installedAppList;
	}
	
	/**
	 * 获取当前应用是否在后台运行
	 * @param ctx
	 * @return
	 */
	public static boolean isForegroundRunning(Context ctx) {
		ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
		boolean isForeground = false;
		if (tasksInfo.size() > 0) {
			// 应用程序位于堆栈的顶层
			final String packageName = ctx.getPackageName();
			String topPackageName = getTopPackageName(ctx);
			if (packageName.equals(topPackageName))
				isForeground = true;
			else 
				isForeground = false;
		}
		return isForeground;
	}
	
	/**
	 * 获取正在前端运行的应用的包名
	 * 
	 * @return
	 */
	public static String getTopPackageName(Context ctx) {
		ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cpnName = activityManager.getRunningTasks(1).get(0).topActivity;
		String pkgName = cpnName.getPackageName();
		return pkgName;
	}

	/**
	 * 获得属于桌面的应用的应用包名称
	 * 
	 * @return 返回包含所有包名的字符串列表
	 */
	public static List<String> getHomes(Context ctx) {
		List<String> names = new ArrayList<String>();
		PackageManager packageManager = ctx.getPackageManager();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		for (ResolveInfo ri : resolveInfo) {
			names.add(ri.activityInfo.packageName);
		}
		return names;
	}

	/**
	 * 屏幕是否为<strong>竖屏</strong>
	 * 
	 * @param orientation 屏幕方向
	 * @return 返回屏幕方向是否为竖屏
	 */
	public static boolean isPortraitOrientation(int orientation) {

		return (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT 
				|| orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT 
				||  orientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
	}
	
	/**
	 * 屏幕是否为<strong>横屏</strong>
	 * 
	 * @param orientation 屏幕方向
	 * @return 返回屏幕方向是否为横屏
	 */
	public static boolean isLandscapeOrientation(int orientation) {

		return (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE 
				|| orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE 
				|| orientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
	}
	
	/**
	 * 判断是否为平板（大于6尺寸则为Pad）
	 * 
	 * @param ctx
	 *            上下文
	 * @return 返回是否为平板
	 */
	public static boolean isTabletDevice(Context ctx) {

		boolean device_large = ((ctx.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE);

		if (device_large) {

			DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();

			if (metrics.densityDpi == DisplayMetrics.DENSITY_DEFAULT || metrics.densityDpi == DisplayMetrics.DENSITY_HIGH || metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM || metrics.densityDpi == DisplayMetrics.DENSITY_TV || metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH) {
				return true;
			}
		}
		return false;
	}
}
