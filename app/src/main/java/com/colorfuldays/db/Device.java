package com.colorfuldays.db;

public class Device {

	private String ipAddress = ""; // "IPAddress"
	private String comKey = ""; // "COMKey"
	private String deviceName = ""; // "~DeviceName"
	private String serialNumber = ""; // "~SerialNumber"
	private String macAddress = ""; // "MAC"
	private int maxUserCounts = 0; // "~MaxUserCount"
	private int maxFpCounts = 0; // "~MaxFingerCount"
	private int maxAttCounts = 0; // "~MaxAttLogCount"
	private String ZKFPVersion = ""; // "~ZKFPVersion"
	private String platformInfo = ""; // "~Platform"
	private String manufacturer = ""; // "~OEMVendor"
	private String manufacturerDate = ""; // "~ProductTime"

	public Device() {

	}

	public Device(String ipAddress, String comKey, String deviceName,
				  String serialNumber, String macAddress, int maxUserCounts,
				  int maxFpCounts, int maxAttCounts, String ZKFPVersion,
				  String platformInfo, String manufacturer, String manufacturerDate) {
		this.ipAddress = ipAddress;
		this.comKey = comKey;
		this.deviceName = deviceName;
		this.serialNumber = serialNumber;
		this.macAddress = macAddress;
		this.maxUserCounts = maxUserCounts;
		this.maxFpCounts = maxFpCounts;
		this.maxAttCounts = maxAttCounts;
		this.ZKFPVersion = ZKFPVersion;
		this.platformInfo = platformInfo;
		this.manufacturer = manufacturer;
		this.manufacturerDate = manufacturerDate;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getComKey() {
		return this.comKey;
	}

	public void setComKey(String comKey) {
		this.comKey = comKey;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public int getMaxUserCounts() {
		return this.maxUserCounts;
	}

	public void setMaxUserCounts(int maxUserCounts) {
		this.maxUserCounts = maxUserCounts;
	}

	public int getMaxFpCounts() {
		return this.maxFpCounts;
	}

	public void setMaxFpCounts(int maxFpCounts) {
		this.maxFpCounts = maxFpCounts;
	}

	public int getMaxAttCounts() {
		return this.maxAttCounts;
	}

	public void setMaxAttCounts(int maxAttCounts) {
		this.maxAttCounts = maxAttCounts;
	}

	public String getZKFPVersion() {
		return this.ZKFPVersion;
	}

	public void setZKFPVersion(String zKFPVersion) {
		this.ZKFPVersion = zKFPVersion;
	}

	public String getPlatformInfo() {
		return this.platformInfo;
	}

	public void setPlatformInfo(String platformInfo) {
		this.platformInfo = platformInfo;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getManufactureDate() {
		return this.manufacturerDate;
	}

	public void setManufacturerDate(String manufacturerDate) {
		this.manufacturerDate = manufacturerDate;
	}
}
