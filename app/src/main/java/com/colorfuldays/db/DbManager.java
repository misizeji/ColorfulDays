package com.colorfuldays.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.colorfuldays.commu.GetAttlogs;
import com.colorfuldays.commu.OptionsUtils;
import com.colorfuldays.commu.UsersUtils;

import java.util.ArrayList;
import java.util.List;

public class DbManager {

	public static DbHelper dbZK;
	private static SQLiteDatabase dbRead, dbWrite;

	public DbManager() {

	}

	public static DbHelper getDbZK() {
		return dbZK;
	}

	public static SQLiteDatabase getDbRead() {
		return dbRead;
	}

	public static SQLiteDatabase getDbWrite() {
		return dbWrite;
	}

	public static void initDbHandle(Context context) {
		dbZK = new DbHelper(context);
		dbRead = dbZK.getReadableDatabase();
		dbWrite = dbZK.getWritableDatabase();
	}

	public static void closeDbHandle() {
		dbRead.close();
		dbWrite.close();
	}

	public static void addUsersToDb(List<User> users) {
		if (null == users) {
			return;
		}

		dbWrite.beginTransaction();
		try {
			for (User user : users) {
				dbWrite.execSQL(
						"INSERT INTO "
								+ DbHelper.TABLE_NAME_USER
								+ " VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, null, null,?, null, null, 0, ?, ?, ?)",
						new Object[]{user.getUserPIN(), user.getPrivilege(),
								user.getName(), user.getPassword(),
								user.getFaceGroupID(), user.getAccGroupID(),
								user.getDeptID(), user.getIsGroupTZ(),
								user.getVerifyType(), user.getMainCard(),
								user.getViceCard(), user.getExpires(),
								user.getTimeZones()[0], user.getTimeZones()[1],
								user.getTimeZones()[2]});
			}
			dbWrite.setTransactionSuccessful();
		} finally {
			dbWrite.endTransaction();
		}
	}

	public static void addAttlogsToDb(List<Attlog> attlogs) {
		if (null == attlogs) {
			return;
		}

		dbWrite.beginTransaction();
		try {
			for (Attlog attlog : attlogs) {
				dbWrite.execSQL(
						"INSERT INTO "
								+ DbHelper.TABLE_NAME_ATTLOG
								+ " VALUES(null, ?, ?, ?, ?, ?, 0, 0, null, null, 0)",
						new Object[]{attlog.getUserPIN(),
								attlog.getVerifyType(), attlog.getDateTime(),
								attlog.getStatus(), attlog.getWorkcode()});
			}
			dbWrite.setTransactionSuccessful();
		} finally {
			dbWrite.endTransaction();
		}
	}

	public static void addDeviceToDb(Device device) {
		if (null == device) {
			return;
		}

		dbWrite.beginTransaction();
		try {
			dbWrite.execSQL(
					"INSERT INTO "
							+ DbHelper.TABLE_NAME_DEVICES
							+ " VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					new Object[]{device.getIpAddress(), device.getComKey(),
							device.getDeviceName(), device.getSerialNumber(),
							device.getMacAddress(), device.getMaxUserCounts(),
							device.getMaxFpCounts(), device.getMaxAttCounts(),
							device.getZKFPVersion(), device.getPlatformInfo(),
							device.getManufacturer(),
							device.getManufactureDate()});
			dbWrite.setTransactionSuccessful();
		} finally {
			dbWrite.endTransaction();
		}
	}

	public static void delUserFromDb(String userPin) {

		if (userPin.equals("ALL")) {
			dbWrite.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_USER);
		} else {
			dbWrite.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_USER
					+ " WHERE " + DbHelper.COLUMN_NAME_USER_PIN + " = "
					+ userPin);
		}
	}

	public static void delAttlogFromDb(String userPin) {

		if (userPin.equals("ALL")) {
			dbWrite.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_ATTLOG);
		} else {
			dbWrite.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_ATTLOG
					+ " WHERE " + DbHelper.COLUMN_NAME_ATTLOG_USER_PIN + " = "
					+ userPin);
		}
	}

	public static void delDeviceInfoFromDb(String serialNumber) {

		if (serialNumber.equals("ALL")) {
			dbWrite.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_DEVICES);
		} else {
			dbWrite.execSQL("DELETE FROM " + DbHelper.TABLE_NAME_DEVICES
					+ " WHERE " + DbHelper.COLUMN_NAME_DEVICES_SERIALNUM
					+ " = " + serialNumber);
		}
	}

	public static List<Attlog> queryAttlogs(String userPin) {
		ArrayList<Attlog> attlogs = new ArrayList<>();
		Cursor c;
		if (userPin.equals("ALL")) {
			c = dbWrite.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME_ATTLOG,
					null);
		} else {
			c = dbWrite.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME_ATTLOG
							+ " WHERE " + DbHelper.COLUMN_NAME_ATTLOG_USER_PIN + "=?",
					new String[]{userPin});
		}

		while (c.moveToNext()) {
			Attlog attlog = new Attlog();
			attlog.setUserPIN(c.getString(c.getColumnIndex(DbHelper.COLUMN_NAME_ATTLOG_USER_PIN)));
			attlog.setVerifyType(c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_ATTLOG_VF_TYPE)));
			attlog.setDateTime(c.getString(c.getColumnIndex(DbHelper.COLUMN_NAME_ATTLOG_VF_TIME)));
			attlog.setStatus(c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_ATTLOG_STATUS)));
			attlog.setWorkcode(c.getString(c.getColumnIndex(DbHelper.COLUMN_NAME_ATTLOG_WORKCODE_ID)));
			attlogs.add(attlog);
		}
		c.close();
		return attlogs;
	}

	public static List<User> queryUsers(String userPin) {
		ArrayList<User> users = new ArrayList<>();
		Cursor c;
		if (userPin.equals("ALL")) {
			c = dbWrite.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME_USER,
					null);
		} else {
			c = dbWrite.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME_USER
							+ " WHERE " + DbHelper.COLUMN_NAME_USER_PIN + "=?",
					new String[]{userPin});
		}

		while (c.moveToNext()) {
			User user = new User();
			user.setUserID(c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_ID)));
			user.setUserPIN(c.getString(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_PIN)));
			user.setPrivilege(c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_PRIVILEGE)));
			user.setName(c.getString(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_NAME)));
			user.setPassword(c.getString(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_PASSWORD)));
			user.setFaceGroupID(c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_FACE_GP_ID)));
			user.setAccGroupID(c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_ACC_GP_ID)));
			user.setDeptID(c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_DEPT_ID)));
			user.setIsGroupTZ(c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_IS_GP_TZ)));
			user.setVerifyType(c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_VF_TYPE)));
			user.setMainCard(c.getString(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_MAIN_CARD)));
			user.setViceCard(c.getString(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_VICE_CARD)));
			user.setExpires(c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_EXPIRES)));
			user.setTimeZones(new int[]{
					c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_TZ1)),
					c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_TZ2)),
					c.getInt(c.getColumnIndex(DbHelper.COLUMN_NAME_USER_TZ3))});
			users.add(user);
		}
		c.close();
		return users;
	}

	public static List<Device> queryDeviceInfo(String serialNumber) {
		ArrayList<Device> devices = new ArrayList<>();
		Cursor c;
		if (serialNumber.equals("ALL")) {
			c = dbWrite.rawQuery(
					"SELECT * FROM " + DbHelper.TABLE_NAME_DEVICES, null);
		} else {
			c = dbWrite.rawQuery(
					"SELECT * FROM " + DbHelper.TABLE_NAME_DEVICES + " WHERE "
							+ DbHelper.COLUMN_NAME_DEVICES_SERIALNUM + "=?",
					new String[]{serialNumber});
		}

		while (c.moveToNext()) {
			Device tempDevice = new Device();
			tempDevice.setIpAddress(c.getString(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_IP)));
			tempDevice.setComKey(c.getString(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_COMKEY)));
			tempDevice.setDeviceName(c.getString(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_NAME)));
			tempDevice.setSerialNumber(c.getString(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_SERIALNUM)));
			tempDevice.setMacAddress(c.getString(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_MAC)));
			tempDevice.setMaxUserCounts(c.getInt(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_USER_CAPACITY)));
			tempDevice.setMaxFpCounts(c.getInt(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_FP_CAPACITY)));
			tempDevice.setMaxAttCounts(c.getInt(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_ATTLOG_CAPACITY)));
			tempDevice.setZKFPVersion(c.getString(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_FP_VERSION)));
			tempDevice.setPlatformInfo(c.getString(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_PLATFORM_INFO)));
			tempDevice.setManufacturer(c.getString(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_MANUFACTURER)));
			tempDevice.setManufacturerDate(c.getString(c
					.getColumnIndex(DbHelper.COLUMN_NAME_DEVICES_MANUFACTURER_DATE)));
			devices.add(tempDevice);
		}
		c.close();
		return devices;
	}

	public static Device getDeviceInfo(String ipAddress, String comPwdKey) {
		String M_GETOPT = "GetOption";
		String M_SUBOPT_NAME = "Name";
		Device tempDevice = new Device();

		tempDevice.setIpAddress(ipAddress);
		tempDevice.setComKey(comPwdKey);
		tempDevice.setDeviceName(OptionsUtils.getOptions(M_GETOPT, M_SUBOPT_NAME, "~DeviceName"));
		tempDevice.setSerialNumber(OptionsUtils.getOptions(M_GETOPT, M_SUBOPT_NAME, "~SerialNumber"));
		tempDevice.setMacAddress(OptionsUtils.getOptions(M_GETOPT, M_SUBOPT_NAME, "MAC"));
		tempDevice.setMaxUserCounts(Integer.valueOf(OptionsUtils.getOptions(M_GETOPT,
				M_SUBOPT_NAME, "~MaxUserCount")));
		tempDevice.setMaxFpCounts(Integer.valueOf(OptionsUtils.getOptions(M_GETOPT,
				M_SUBOPT_NAME, "~MaxFingerCount")));
		tempDevice.setMaxAttCounts(Integer.valueOf(OptionsUtils.getOptions(M_GETOPT,
				M_SUBOPT_NAME, "~MaxAttLogCount")));
		tempDevice.setZKFPVersion(OptionsUtils.getOptions(M_GETOPT, M_SUBOPT_NAME, "~ZKFPVersion"));
		tempDevice.setPlatformInfo(OptionsUtils.getOptions(M_GETOPT, M_SUBOPT_NAME, "~Platform"));
		tempDevice.setManufacturer(OptionsUtils.getOptions(M_GETOPT, M_SUBOPT_NAME, "~OEMVendor"));
		tempDevice.setManufacturerDate(OptionsUtils.getOptions(M_GETOPT,
				M_SUBOPT_NAME, "~ProductTime"));
		return tempDevice;
	}

	public static void syncDeviceInfoToDb(String ipAddress, String comPwdKey) {
		DbManager.delDeviceInfoFromDb("ALL");
		addDeviceToDb(getDeviceInfo(ipAddress, comPwdKey));
	}

	public static void syncDataToDb() {
		// delete the data of the DB
		DbManager.delUserFromDb("ALL");
		DbManager.delAttlogFromDb("ALL");
		// sync the data to DB
		DbManager.addUsersToDb(UsersUtils.getAllUserInfo());
		DbManager.addAttlogsToDb(GetAttlogs.getAllAttlogs());
	}
}
