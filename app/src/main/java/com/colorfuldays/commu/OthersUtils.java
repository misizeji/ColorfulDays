package com.colorfuldays.commu;

import com.colorfuldays.utils.LogUtils;

import org.ksoap2.serialization.SoapObject;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by JITAO on 2016/9/18.
 * <p>
 * For some other operate for the TA devices. Like Restart, GetDate etc...
 */
public class OthersUtils extends KsoapInit {

	public OthersUtils() {
		// LogUtils.isDebug = true;
		// LogUtils.TAG = "OthersUtils";
	}

	/**
	 * @return return restart's result but not effective yet.
	 */
	public static Boolean restart() {
		initKsoap("Restart", 1000);
		FutureTask<Boolean> future = new FutureTask<>(
				new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						try {
							httpSE.call(null, soapserial);
							if (soapserial.getResponse() != null) {
								SoapObject result = (SoapObject) soapserial.getResponse();
								LogUtils.i("<--- OthersUtils ---> the result is " + result);
								String Value = result.getProperty("value").toString();
								return Value.equals("Succeed!");
							}
						} catch (Exception e) {
							e.printStackTrace();
							if (e instanceof SocketTimeoutException) {
								LogUtils.i("<--- OthersUtils ---> the Exception is TimeOut ");
							} else if (e instanceof UnknownHostException) {
								LogUtils.i("<--- OthersUtils ---> the Exception is UnknownHost ");
							} else if (e instanceof  NullPointerException){
								LogUtils.i("<--- OthersUtils ---> the Exception is No Response ");
							}
						}
						return false;
					}
				});
		new Thread(future).start();
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return false;
	}

}
