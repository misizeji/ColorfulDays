package com.colorfuldays.commu;

import com.colorfuldays.utils.LogUtils;

import org.ksoap2.serialization.SoapObject;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class OptionsUtils extends KsoapInit {

	public OptionsUtils() {
		// LogUtils.isDebug = true;
		// LogUtils.TAG = "OptionsUtils";
	}

	/**
	 * @param nodeName    first node's name
	 * @param subNodeName subnode's name
	 * @param optionName  option's name
	 * @return return the options Value String
	 */
	public static String getOptions(String nodeName, String subNodeName, String optionName) {
		initSubKsoap(nodeName, subNodeName, optionName, 1000);
		FutureTask<String> future = new FutureTask<>(
				new Callable<String>() {
					@Override
					public String call() throws Exception {
						try {
							httpSE.call(null, soapserial);
							if (soapserial.getResponse() != null) {
								SoapObject result = (SoapObject) soapserial.getResponse();
								LogUtils.i("<--- OptionsUtils ---> the result is " + result);
								//String Value = result.getProperty("Value").toString();
								return result.getProperty("Value").toString();
							}
						} catch (Exception e) {
							e.printStackTrace();
							if (e instanceof java.net.SocketTimeoutException) {
								LogUtils.i("<--- OptionsUtils ---> the Exception is TimeOut ");
							} else if (e instanceof java.net.UnknownHostException) {
								LogUtils.i("<--- OptionsUtils ---> the Exception is UnknownHost ");
							}
						}
						return null;
					}
				});
		new Thread(future).start();
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
