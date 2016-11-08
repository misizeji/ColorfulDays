package com.colorfuldays.commu;

import com.colorfuldays.db.User;
import com.colorfuldays.utils.LogUtils;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class UsersUtils extends KsoapInit {

	public UsersUtils() {
		// LogUtils.isDebug = true;
		// LogUtils.TAG = "UsersUtils";
	}

	/**
	 * @param userPIN the pin of which user you want to get
	 * @return User this User's object
	 */
	public static User getUserInfo(String userPIN) {
		initSubKsoap("GetUserInfo", "PIN", userPIN, 2000);
		FutureTask<User> future = new FutureTask<>(
				new Callable<User>() {
					@Override
					public User call() throws Exception {
						httpSE.call(null, soapserial);
						if (soapserial.getResponse() != null) {
							SoapObject result = (SoapObject) soapserial.getResponse();
							LogUtils.i("<--- UsersUtils ---> the result is " + result);
							if (Integer.valueOf(result.getProperty("PIN").toString()) == 1) {
								User tmpUser = new User();
								int tmpTimeZones[] = {0, 0, 0};
								tmpUser.setUserID(Integer.valueOf(result
										.getProperty("PIN").toString()));
								tmpUser.setUserPIN(result.getProperty("PIN2").toString());
								tmpUser.setPrivilege(Integer.valueOf(result
										.getProperty("Privilege").toString()));
								tmpUser.setName(result.getProperty("Name").toString());
								tmpUser.setPassword(result.getProperty("Password").toString());
								/* if the device is iface series products */
								// tmpUser.setFaceGroupID(Integer.valueOf(result
								// .getProperty("Face_Group_ID")
								// .toString()));
								tmpUser.setAccGroupID(Integer.valueOf(result
										.getProperty("Group").toString()));
								// tmpUser.setDeptID(Integer.valueOf(result
								// .getProperty("Dept_ID").toString()));
								// tmpUser.setIsGroupTZ(Integer.valueOf(result
								// .getProperty("Is_Group_TZ").toString()));
								// tmpUser.setVerifyType(Integer.valueOf(result
								// .getProperty("Verify_Type").toString()));
								tmpUser.setMainCard(result.getProperty("Card").toString());
								// tmpUser.setViceCard(result.getProperty(
								// "ViceCard").toString());
								// tmpUser.setExpires(Integer.valueOf(result
								// .getProperty("Expires").toString()));
								tmpTimeZones[0] = Integer.valueOf(result
										.getProperty("TZ1").toString());
								tmpTimeZones[1] = Integer.valueOf(result
										.getProperty("TZ2").toString());
								tmpTimeZones[2] = Integer.valueOf(result
										.getProperty("TZ3").toString());
								tmpUser.setTimeZones(tmpTimeZones);
								return tmpUser;
							} else {
								return null;
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

	/**
	 * @return User UserList
	 */

	public static List<User> getAllUserInfo() {
		initSubKsoap("GetAllUserInfo", "PIN", "ALL", 3000);
		FutureTask<List<User>> future = new FutureTask<>(
				new Callable<List<User>>() {
					@Override
					public List<User> call() throws Exception {
						List<User> UserList = new ArrayList<>();
						httpSE.call(null, soapserial);
						if (soapserial.getResponse() != null) {
							SoapObject result = (SoapObject) soapserial.bodyIn;
							LogUtils.i("<--- UsersUtils ---> the result is " + result);
							LogUtils.i("<--- UsersUtils ---> getAllUserInfo counts: "
									+ result.getPropertyCount());
							for (int i = 0; i < result.getPropertyCount(); i++) {
								SoapObject detial = (SoapObject) result.getProperty(i);
								if (Integer.valueOf(detial.getProperty("PIN").toString()) > 0) {
									User tmpUser = new User();
									int tmpTimeZones[] = {0, 0, 0};
									tmpUser.setUserID(Integer.valueOf(detial
											.getProperty("PIN").toString()));
									tmpUser.setUserPIN(detial.getProperty("PIN2").toString());
									tmpUser.setPrivilege(Integer.valueOf(detial
											.getProperty("Privilege").toString()));
									tmpUser.setName(detial.getProperty("Name").toString());
									tmpUser.setPassword(detial.getProperty("Password").toString());
									// tmpUser.setFaceGroupID(Integer.valueOf(detial
									// .getProperty("Face_Group_ID")
									// .toString()));
									tmpUser.setAccGroupID(Integer.valueOf(detial
											.getProperty("Group").toString()));
									// tmpUser.setDeptID(Integer.valueOf(detial
									// .getProperty("Dept_ID").toString()));
									// tmpUser.setIsGroupTZ(Integer.valueOf(detial
									// .getProperty("Is_Group_TZ").toString()));
									// tmpUser.setVerifyType(Integer.valueOf(detial
									// .getProperty("Verify_Type").toString()));
									tmpUser.setMainCard(detial.getProperty("Card").toString());
									// tmpUser.setViceCard(detial.getProperty(
									// "ViceCard").toString());
									// tmpUser.setExpires(Integer.valueOf(detial
									// .getProperty("Expires").toString()));
									tmpTimeZones[0] = Integer.valueOf(detial
											.getProperty("TZ1").toString());
									tmpTimeZones[1] = Integer.valueOf(detial
											.getProperty("TZ2").toString());
									tmpTimeZones[2] = Integer.valueOf(detial
											.getProperty("TZ3").toString());
									tmpUser.setTimeZones(tmpTimeZones);
									UserList.add(tmpUser);
								}
							}
							return UserList;
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
