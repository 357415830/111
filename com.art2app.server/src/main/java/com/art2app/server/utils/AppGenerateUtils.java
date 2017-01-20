package com.art2app.server.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.art2app.generator.api.AppDefinition;
import com.art2app.generator.api.AppGeneratorService;
import com.art2app.generator.api.AppIcon;
import com.art2app.generator.api.AppPackage;
import com.art2app.generator.api.AppSplashScreen;
import com.art2app.generator.impl.AppDefinitionImpl;
import com.art2app.generator.impl.AppGeneratorServiceImpl;
import com.art2app.generator.impl.AppIconFile;
import com.art2app.generator.impl.AppSplashScreenFile;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class AppGenerateUtils {
	private String appName;
	private File fileIcon;
	private File fileSplashScreen;
	private String url;
	private static Logger logger = LogManager.getLogger(AppGenerateUtils.class);

	public AppGenerateUtils(String appName, File fileIcon, File splashScreen, String url) {
		this.appName = appName;
		this.fileIcon = fileIcon;
		this.fileSplashScreen = splashScreen;
		this.url = url;
	}

	
	public Observable<AppPackage> preGenerateApp(Scheduler scheduler,String appGitUrl, String appGitBranch, String appFolder, File keyStore, String storePass,
			String androidHome, File zipAlignPath) {
	
		
		AppIcon appIcon = new AppIconFile("icon.png", fileIcon);
		AppSplashScreen appSplashScreen = null;
		if (fileSplashScreen != null && !fileSplashScreen.equals("")) {
			appSplashScreen = new AppSplashScreenFile("splash.png", fileSplashScreen);
		}
		AppDefinition appDescription = new AppDefinitionImpl(appName, appIcon, appSplashScreen, url);
		AppGeneratorService appGeneratorService = new AppGeneratorServiceImpl(scheduler, appGitUrl, appGitBranch, appFolder,
				keyStore, storePass, androidHome, zipAlignPath);
		return appGeneratorService.generateApp(appDescription);
	}

	public void installOnDevice(AppPackage appPackage, String android_Platform_Tools_Path, String TARGET_RESULT_APK) throws IOException, Exception {
		File localApkFile = new File(TARGET_RESULT_APK);
		localApkFile.delete();
		try (InputStream packageStream = appPackage.getPackage();
				FileOutputStream out = new FileOutputStream(localApkFile)) {
			IOUtils.copy(packageStream, out);			
			out.close();
			packageStream.close();
		} catch (IOException e) {
			logger.error(e);
			throw e;
		} catch (Exception e1) {
			logger.error(e1);
			throw e1;
		} 
	}

}
