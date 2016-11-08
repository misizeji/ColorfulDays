package com.colorfuldays.commu;

import com.colorfuldays.db.Attlog;
import com.colorfuldays.utils.LogUtils;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class GetAttlogs extends KsoapInit {

	public GetAttlogs() {
		// LogUtils.isDebug = true;
		// LogUtils.TAG = "GetAttlogs";
	}

	public static List<Attlog> getAttlogs(String UserPIN) {
		initSubKsoap("GetAttLog", "PIN", UserPIN, 3000);
		FutureTask<List<Attlog>> future = new FutureTask<>(
				new Callable<List<Attlog>>() {
					@Override
					public List<Attlog> call() throws Exception {
						List<Attlog> ValueList = new ArrayList<>();
						httpSE.call(null, soapserial);
						if (soapserial.getResponse() != null) {
							SoapObject result = (SoapObject) soapserial.bodyIn;
							LogUtils.i("<--- GetAttlogs ---> the result is " + result);
							LogUtils.i("<--- GetAttlogs ---> getAttlogs counts:"
									+ result.getPropertyCount());
							for (int i = 0; i < result.getPropertyCount(); i++) {
								SoapObject detial = (SoapObject) result.getProperty(i);
								Attlog tmpAttlog = new Attlog();
								tmpAttlog.setUserPIN(detial.getProperty("PIN").toString());
								tmpAttlog.setDateTime(detial.getProperty("DateTime").toString());
								tmpAttlog.setVerifyType(Integer.valueOf(detial
										.getProperty("Verified").toString()));
								tmpAttlog.setStatus(Integer.valueOf(detial
										.getProperty("Status").toString()));
								tmpAttlog.setWorkcode(detial.getProperty(
										"WorkCode").toString());
								ValueList.add(tmpAttlog);
							}
							return ValueList;
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

	public static List<Attlog> getAllAttlogs() {
		initSubKsoap("GetAttLog", "PIN", "ALL", 5000);
		FutureTask<List<Attlog>> future = new FutureTask<>(
				new Callable<List<Attlog>>() {
					@Override
					public List<Attlog> call() throws Exception {
						List<Attlog> ValueList = new ArrayList<>();
						httpSE.call(null, soapserial);
						if (soapserial.getResponse() != null) {
							SoapObject result = (SoapObject) soapserial.bodyIn;
							LogUtils.i("<--- GetAttlogs ---> the result is " + result);
							LogUtils.i("<--- GetAttlogs ---> getAllAttlogs counts:"
									+ result.getPropertyCount());
							for (int i = 0; i < result.getPropertyCount(); i++) {
								SoapObject detial = (SoapObject) result.getProperty(i);
								Attlog tmpAttlog = new Attlog();
								tmpAttlog.setUserPIN(detial.getProperty("PIN").toString());
								tmpAttlog.setDateTime(detial.getProperty("DateTime").toString());
								tmpAttlog.setVerifyType(Integer.valueOf(detial
										.getProperty("Verified").toString()));
								tmpAttlog.setStatus(Integer.valueOf(detial
										.getProperty("Status").toString()));
								tmpAttlog.setWorkcode(detial.getProperty(
										"WorkCode").toString());
								ValueList.add(tmpAttlog);
							}
							return ValueList;
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
