package com.colorfuldays.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.colorfuldays.utils.LogUtils;

/* Table structure of the USER_INFO */

// CREATE TABLE [USER_INFO] (
// [ID] INTEGER PRIMARY KEY AUTOINCREMENT,
// [User_PIN] VARCHAR(24) UNIQUE,
// [Privilege] INT,
// [Name] VARCHAR(48),
// [Password] VARCHAR(16),
// [Face_Group_ID] INT,
// [Acc_Group_ID] INT DEFAULT 1,
// [Dept_ID] INT DEFAULT 1,
// [Is_Group_TZ] INT DEFAULT 1,
// [Verify_Type] INT DEFAULT 0,
// [Main_Card] VARCHAR(24),
// [Vice_Card] VARCHAR(24),
// [CREATE_ID] VARCHAR(24),
// [MODIFY_TIME] VARCHAR(24),
// [SEND_FLAG] INTEGER DEFAULT 0,
// [Expires] INTEGER DEFAULT 0,
// [StartDatetime] VARCHAR(24) DEFAULT 0,
// [EndDatetime] VARCHAR(24) DEFAULT 0,
// [VaildCount] INTEGER DEFAULT 0,
// [Timezone1] INT DEFAULT 1,
// [Timezone2] INT DEFAULT 0,
// [Timezone3] INT DEFAULT 0);

/* * 
 * Table structure of the ATT_LOG 
 * 
 * in here, changed the WorkCode to String type.
 */

// CREATE TABLE [ATT_LOG] (
// [ID] INTEGER PRIMARY KEY AUTOINCREMENT,
// [User_PIN] VARCHAR(24),
// [Verify_Type] INT,
// [Verify_Time] VARCHAR(24),
// [Status] INT,
// [Work_Code_ID] INT,
// [Sensor_NO] INT,
// [Att_Flag] INT,
// [CREATE_ID] VARCHAR(24),
// [MODIFY_TIME] VARCHAR(24),
// [SEND_FLAG] INTEGER DEFAULT 0);
//
// CREATE INDEX [Verify_Time_Index] ON [ATT_LOG] ([Verify_Time]);

public class DbHelper extends SQLiteOpenHelper {

	/* for the table of user */
	public static final String TABLE_NAME_USER = "USER_INFO";
	public static final String COLUMN_NAME_USER_ID = "ID";
	public static final String COLUMN_NAME_USER_PIN = "User_PIN";
	public static final String COLUMN_NAME_USER_PRIVILEGE = "Privilege";
	public static final String COLUMN_NAME_USER_NAME = "Name";
	public static final String COLUMN_NAME_USER_PASSWORD = "Password";
	public static final String COLUMN_NAME_USER_FACE_GP_ID = "Face_Group_ID";
	public static final String COLUMN_NAME_USER_ACC_GP_ID = "Acc_Group_ID";
	public static final String COLUMN_NAME_USER_DEPT_ID = "Dept_ID";
	public static final String COLUMN_NAME_USER_IS_GP_TZ = "Is_Group_TZ";
	public static final String COLUMN_NAME_USER_VF_TYPE = "Verify_Type";
	public static final String COLUMN_NAME_USER_MAIN_CARD = "Main_Card";
	public static final String COLUMN_NAME_USER_VICE_CARD = "Vice_Card";
	/* not use for now */
	public static final String COLUMN_NAME_USER_CAEATE_ID = "CREATE_ID";
	public static final String COLUMN_NAME_USER_MODIFY_TIME = "MODIFY_TIME";
	public static final String COLUMN_NAME_USER_SEND_FLAG = "SEND_FLAG";
	/* end */
	public static final String COLUMN_NAME_USER_EXPIRES = "Expires";
	/* not use for now */
	public static final String COLUMN_NAME_USER_SDATE_TIME = "StartDatetime";
	public static final String COLUMN_NAME_USER_EDATE_TIME = "EndDatetime";
	public static final String COLUMN_NAME_USER_VAILD_COUNT = "VaildCount";
	/* end */
	public static final String COLUMN_NAME_USER_TZ1 = "Timezone1";
	public static final String COLUMN_NAME_USER_TZ2 = "Timezone2";
	public static final String COLUMN_NAME_USER_TZ3 = "Timezone3";

	/* for the table of attlog */
	public static final String TABLE_NAME_ATTLOG = "ATT_LOG";
	public static final String COLUMN_NAME_ATTLOG_ID = "ID";
	public static final String COLUMN_NAME_ATTLOG_USER_PIN = "User_PIN";
	public static final String COLUMN_NAME_ATTLOG_VF_TYPE = "Verify_Type";
	public static final String COLUMN_NAME_ATTLOG_VF_TIME = "Verify_Time";
	public static final String COLUMN_NAME_ATTLOG_STATUS = "Status";
	public static final String COLUMN_NAME_ATTLOG_WORKCODE_ID = "Work_Code_ID";
	public static final String COLUMN_NAME_ATTLOG_SENSOR_NO = "Sensor_NO";
	public static final String COLUMN_NAME_ATTLOG_ATT_FLAG = "Att_Flag";
	public static final String COLUMN_NAME_ATTLOG_CREATE_ID = "CREATE_ID";
	public static final String COLUMN_NAME_ATTLOG_MODIFY_TIME = "MODIFY_TIME";
	public static final String COLUMN_NAME_ATTLOG_SEND_FLAG = "SEND_FLAG";

	/* for the table of devices */
	public static final String TABLE_NAME_DEVICES = "DEV_LIST";
	public static final String COLUMN_NAME_DEVICES_ID = "Device_ID";
	public static final String COLUMN_NAME_DEVICES_IP = "IPAddress";
	public static final String COLUMN_NAME_DEVICES_COMKEY = "ComKey";
	public static final String COLUMN_NAME_DEVICES_NAME = "DeviceName";
	public static final String COLUMN_NAME_DEVICES_SERIALNUM = "serialNumber";
	public static final String COLUMN_NAME_DEVICES_MAC = "MAC";
	public static final String COLUMN_NAME_DEVICES_USER_CAPACITY = "maxUserCount";
	public static final String COLUMN_NAME_DEVICES_FP_CAPACITY = "maxFPCount";
	public static final String COLUMN_NAME_DEVICES_ATTLOG_CAPACITY = "maxAttCount";
	public static final String COLUMN_NAME_DEVICES_FP_VERSION = "ZKFPVersion";
	public static final String COLUMN_NAME_DEVICES_PLATFORM_INFO = "Platform";
	public static final String COLUMN_NAME_DEVICES_MANUFACTURER = "OEMVendor";
	public static final String COLUMN_NAME_DEVICES_MANUFACTURER_DATE = "ProductTime";

	// public DbSqlite(Context context, String name, CursorFactory factory,
	// int version)

	public DbHelper(Context context) {
		// create the database
		super(context, "ZKColorfulDays", null, 1);
		// LogUtils.isDebug = true;
		// LogUtils.TAG = "DbSqlite";
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		LogUtils.i("<---Dbsqlite---> onCreate");
		db.execSQL("CREATE TABLE " + TABLE_NAME_USER + "("
				+ COLUMN_NAME_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_NAME_USER_PIN + " VACHAR(24) UNIQUE,"
				+ COLUMN_NAME_USER_PRIVILEGE + " INT," + COLUMN_NAME_USER_NAME
				+ " VARCHAR(48)," + COLUMN_NAME_USER_PASSWORD + " VARCHAR(16),"
				+ COLUMN_NAME_USER_FACE_GP_ID + " INT,"
				+ COLUMN_NAME_USER_ACC_GP_ID + " INT DEFAULT 1,"
				+ COLUMN_NAME_USER_DEPT_ID + " INT DEFAULT 1,"
				+ COLUMN_NAME_USER_IS_GP_TZ + " INT DEFAULT 1,"
				+ COLUMN_NAME_USER_VF_TYPE + " INT DEFAULT 0,"
				+ COLUMN_NAME_USER_MAIN_CARD + " VARCHAR(24),"
				+ COLUMN_NAME_USER_VICE_CARD + " VARCHAR(24),"
				+ COLUMN_NAME_USER_CAEATE_ID + " VARCHAR(24),"
				+ COLUMN_NAME_USER_MODIFY_TIME + " VARCHAR(24),"
				+ COLUMN_NAME_USER_SEND_FLAG + " INTEGER DEFAULT 0,"
				+ COLUMN_NAME_USER_EXPIRES + " INTEGER DEFAULT 0,"
				+ COLUMN_NAME_USER_SDATE_TIME + " VARCHAR(24) DEFAULT 0,"
				+ COLUMN_NAME_USER_EDATE_TIME + " VARCHAR(24) DEFAULT 0,"
				+ COLUMN_NAME_USER_VAILD_COUNT + " INTEGER DEFAULT 0,"
				+ COLUMN_NAME_USER_TZ1 + " INTEGER DEFAULT 1,"
				+ COLUMN_NAME_USER_TZ2 + " INTEGER DEFAULT 0,"
				+ COLUMN_NAME_USER_TZ3 + " INTEGER DEFAULT 0" + ")");

		db.execSQL("CREATE TABLE " + TABLE_NAME_ATTLOG + "("
				+ COLUMN_NAME_ATTLOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_NAME_ATTLOG_USER_PIN + " VARCHAR(24),"
				+ COLUMN_NAME_ATTLOG_VF_TYPE
				+ " INT,"
				+ COLUMN_NAME_ATTLOG_VF_TIME
				+ " VARCHAR(24),"
				+ COLUMN_NAME_ATTLOG_STATUS
				+ " INT,"
				+ COLUMN_NAME_ATTLOG_WORKCODE_ID
				+ " VARCHAR(24),"// changed this type to String
				+ COLUMN_NAME_ATTLOG_SENSOR_NO + " INT,"
				+ COLUMN_NAME_ATTLOG_ATT_FLAG + " INT,"
				+ COLUMN_NAME_ATTLOG_CREATE_ID + " VARCHAR(24),"
				+ COLUMN_NAME_ATTLOG_MODIFY_TIME + " VARCHAR(24),"
				+ COLUMN_NAME_ATTLOG_SEND_FLAG + " INTEGER DEFAULT 0" + ")");

		db.execSQL("CREATE TABLE " + TABLE_NAME_DEVICES + "("
				+ COLUMN_NAME_DEVICES_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_NAME_DEVICES_IP + " VARCHAR(16),"
				+ COLUMN_NAME_DEVICES_COMKEY + " VARCHAR(16),"
				+ COLUMN_NAME_DEVICES_NAME + " VARCHAR(25),"
				+ COLUMN_NAME_DEVICES_SERIALNUM + " VARCHAR(25) UNIQUE,"
				+ COLUMN_NAME_DEVICES_MAC + " VARCHAR(25),"
				+ COLUMN_NAME_DEVICES_USER_CAPACITY + " VARCHAR(25),"
				+ COLUMN_NAME_DEVICES_FP_CAPACITY + " VARCHAR(25),"
				+ COLUMN_NAME_DEVICES_ATTLOG_CAPACITY + " VARCHAR(25),"
				+ COLUMN_NAME_DEVICES_FP_VERSION + " VARCHAR(25),"
				+ COLUMN_NAME_DEVICES_PLATFORM_INFO + " VARCHAR(25),"
				+ COLUMN_NAME_DEVICES_MANUFACTURER + " VARCHAR(25),"
				+ COLUMN_NAME_DEVICES_MANUFACTURER_DATE + " VARCHAR(25)" + ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		LogUtils.i("<---Dbsqlite---> onUpgrade" + oldVersion + newVersion);
	}

}