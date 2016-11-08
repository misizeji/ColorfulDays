package com.colorfuldays.db;

//#define MAX_USERPIN_LEN 24
//typedef struct _AttLog_
//{
//	WORD PIN;							//user_ID索引
//	BYTE userPIN[MAX_USERPIN_LEN];		//用户工号
//	BYTE verified;						//验证方式，如卡加指纹
//	time_t time_second;					//验证时间，单位为秒   这里使用字符串进行存储
//	BYTE status;						//验证状态，如上班签到
//	DWORD workcode;						//工作号码
//	BYTE reserved[4];
//}GCC_PACKED TAttLog, *PAttLog;

public class Attlog {

	private int userID;
	private String userPIN;
	private int verifyType;
	private String dateTime;
	private int status;
	private String workcode;

	// private int reserved[];

	public Attlog() {

	}

	public Attlog(int userID, String userPIN, int verifyType, String dateTime,
				  int status, String workcode) {
		this.userID = userID;
		this.userPIN = userPIN;
		this.verifyType = verifyType;
		this.dateTime = dateTime;
		this.status = status;
		this.workcode = workcode;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserPIN() {
		return userPIN;
	}

	public void setUserPIN(String userPIN) {
		this.userPIN = userPIN;
	}

	public int getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(int verifyType) {
		this.verifyType = verifyType;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getWorkcode() {
		return workcode;
	}

	public void setWorkcode(String workcode) {
		this.workcode = workcode;
	}
}
