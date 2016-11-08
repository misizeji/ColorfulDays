package com.colorfuldays;

public class DeviceCellData {

	private String itemName = "";
	private String itemValue = "";

	public DeviceCellData(String itemName, String itemValue) {
		this.itemName = itemName;
		this.itemValue = itemValue;
	}

	public String getItemName() {
		return this.itemName;
	}

	public String getItemValue() {
		return this.itemValue;
	}
}
