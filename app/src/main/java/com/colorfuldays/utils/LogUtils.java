package com.colorfuldays.utils;

import android.util.Log;

/**
 * Log统一管理类
 */
public class LogUtils {

	/**
	 * this parameter "isDebug" is the global switch of the whole application, if you want your personal debug info
	 * you should turn on the switch first then define your own "TAG",to get your debug info by the filter of your TAG.
	 */
	public static boolean isDebug = false;// 是否需要打印bug，可以在application的onCreate函数里面初始化
	public static String TAG = "ColorfulDays";// 可以在需要打印的程序段之前更改自己需要的TAG内容
	private LogUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	// 下面四个是默认tag的函数
	public static void i(String msg) {
		if (isDebug)
			Log.i(TAG, msg);
	}

	public static void d(String msg) {
		if (isDebug)
			Log.d(TAG, msg);
	}

	public static void e(String msg) {
		if (isDebug)
			Log.e(TAG, msg);
	}

	public static void v(String msg) {
		if (isDebug)
			Log.v(TAG, msg);
	}

	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}
}