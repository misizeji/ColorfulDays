package com.colorfuldays.commu;


import com.colorfuldays.utils.LogUtils;

public class ServerUtils {

	// Webservice server address
	public static String SERVER_URL = "http://192.168.1.201/iWsService";
	public static String PACE = "http://192.168.1.201/";
	// set communicate password
	public static String ComKey = "0";
	private static String SERVER_URL_BK = "http://192.168.1.201/iWsService";
	private static String PACE_BK = "http://192.168.1.201/";
	private static String ComKey_BK = "0";

	public ServerUtils() {
		// LogUtils.isDebug = true;
		// LogUtils.TAG = "ServerUtils";
	}

	// Webservice namespace
	public static String getComKey() {
		return ComKey;
	}

	public static String getServerUrl() {
		return SERVER_URL;
	}

	public static String getPACE() {
		return PACE;
	}

	private static void backupServer() {
		// backup the server's configure
		SERVER_URL_BK = SERVER_URL;
		PACE_BK = PACE;
		ComKey_BK = ComKey;
	}

	/**
	 * @param ipAddress the TA device's IP
	 * @param comPwdKey the TA device's comKey
	 */
	private static void setServer(String ipAddress, String comPwdKey) {
		// set new server'configure
		SERVER_URL = "http://" + ipAddress + "/iWsService";
		PACE = "http://" + ipAddress + "/";
		ComKey = comPwdKey;
	}

	private static void recoverServer() {
		// recover the server former setting
		SERVER_URL = SERVER_URL_BK;
		PACE = PACE_BK;
		ComKey = ComKey_BK;
	}

	/**
	 * @param ipAddress the TA device's IP
	 * @param comPwdKey the TA device's comKey
	 * @return true, false true means the TA device is available otherwise means not
	 */
	public static boolean testServer(String ipAddress, String comPwdKey) {
		String M_GETOPT = "GetOption";
		String M_SUBOPT = "~SerialNumber";
		String M_SUBOPT_NAME = "Name";

		backupServer();
		setServer(ipAddress, comPwdKey);
		String tmpSerialNumber = OptionsUtils.getOptions(M_GETOPT, M_SUBOPT_NAME, M_SUBOPT);
		recoverServer();
		if (null == tmpSerialNumber || tmpSerialNumber.isEmpty()) {
			return false;
		} else {
			LogUtils.i("<--- ServerUtils ---> ~SerialNumber: " + tmpSerialNumber);
			return true;
		}
	}

	/**
	 * @param ipAddress the TA device's IP
	 * @param comPwdKey the TA device's comKey
	 * @return true, false true means the TA device is available otherwise means not
	 */
	public static boolean saveServer(String ipAddress, String comPwdKey) {
		if (!testServer(ipAddress, comPwdKey)) {
			return false;
		} else {
			setServer(ipAddress, comPwdKey);
			LogUtils.i("<--- ServerUtils ---> Set the server OK");
			return true;
		}
	}
}