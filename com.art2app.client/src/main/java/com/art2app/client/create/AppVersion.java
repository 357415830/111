package com.art2app.client.create;

import org.eclipse.scout.rt.platform.resource.BinaryResource;

public class AppVersion {

	private int appId;
	private String appName;
	private BinaryResource icon;
	private String icon_Filename;
	private BinaryResource splashScreen;
	private String splash_Filename;
	private String webToMobileURL;
	private int setting;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public BinaryResource getIcon() {
		return icon;
	}
	public void setIcon(BinaryResource icon) {
		this.icon = icon;
	}
	public String getIcon_Filename() {
		return icon_Filename;
	}
	public void setIcon_Filename(String icon_Filename) {
		this.icon_Filename = icon_Filename;
	}
	public BinaryResource getSplashScreen() {
		return splashScreen;
	}
	public void setSplashScreen(BinaryResource splashScreen) {
		this.splashScreen = splashScreen;
	}
	public String getSplash_Filename() {
		return splash_Filename;
	}
	public void setSplash_filename(String splash_Filename) {
		this.splash_Filename = splash_Filename;
	}
	public String getWebToMobileURL() {
		return webToMobileURL;
	}
	public void setWebToMobileURL(String webToMobileURL) {
		this.webToMobileURL = webToMobileURL;
	}
	public int getSetting() {
		return setting;
	}
	public void setSetting(int setting) {
		this.setting = setting;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
}
