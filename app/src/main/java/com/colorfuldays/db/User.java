package com.colorfuldays.db;

//typedef struct _User_
//{
//	int User_ID;
//	char User_PIN[MAX_USERPIN_LEN];
//	int Privilege;
//	char Name[MAX_USERNAME_LEN];
//	char Password[MAX_PWD_LEN];
//	int Face_Group_ID;
//	int Acc_Group_ID;
//	int Dept_ID;
//	int Is_Group_TZ;
//	int Verify_Type;
//	char Main_Card[MAX_CARDNO_LEN];
//	char Vice_Card[MAX_CARDNO_LEN];
//	int Expires;
//	int TimeZones[USER_MAX_TZCOUNT];
//}GCC_PACKED TUser, *PUser;

public class User {

	private int userID;
	private String userPIN;
	private int privilege;
	private String name;
	private String password;
	private int faceGroupID;
	private int accGroupID;
	private int deptID;
	private int isGroupTZ;
	private int verifyType;
	private String mainCard;
	private String viceCard;
	private int expires;
	private int timeZones[];

	public User() {

	}

	public User(int user_ID, String user_PIN, int privilege, String name,
				String password, int face_Group_ID, int acc_Group_ID, int dept_ID,
				int is_Group_TZ, int verify_Type, String main_Card,
				String vice_Card, int expires, int[] timeZones) {
		this.userID = user_ID;
		this.userPIN = user_PIN;
		this.privilege = privilege;
		this.name = name;
		this.password = password;
		this.faceGroupID = face_Group_ID;
		this.accGroupID = acc_Group_ID;
		this.deptID = dept_ID;
		this.isGroupTZ = is_Group_TZ;
		this.verifyType = verify_Type;
		this.mainCard = main_Card;
		this.viceCard = vice_Card;
		this.expires = expires;
		this.timeZones = timeZones;
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

	public int getPrivilege() {
		return privilege;
	}

	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFaceGroupID() {
		return faceGroupID;
	}

	public void setFaceGroupID(int faceGroupID) {
		this.faceGroupID = faceGroupID;
	}

	public int getAccGroupID() {
		return accGroupID;
	}

	public void setAccGroupID(int accGroupID) {
		this.accGroupID = accGroupID;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public int getIsGroupTZ() {
		return isGroupTZ;
	}

	public void setIsGroupTZ(int isGroupTZ) {
		this.isGroupTZ = isGroupTZ;
	}

	public int getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(int verifyType) {
		this.verifyType = verifyType;
	}

	public String getMainCard() {
		return mainCard;
	}

	public void setMainCard(String mainCard) {
		this.mainCard = mainCard;
	}

	public String getViceCard() {
		return viceCard;
	}

	public void setViceCard(String viceCard) {
		this.viceCard = viceCard;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

	public int[] getTimeZones() {
		return timeZones;
	}

	public void setTimeZones(int[] timeZones) {
		this.timeZones = timeZones;
	}

}
