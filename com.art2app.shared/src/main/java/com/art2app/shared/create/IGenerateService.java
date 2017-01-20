package com.art2app.shared.create;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import com.art2app.shared.create.GenerateFormData;

@TunnelToServer
public interface IGenerateService extends IService {

	public static final String FAILED = "failed";

	GenerateFormData prepareCreate(GenerateFormData formData);

	GenerateFormData create(GenerateFormData formData);

	GenerateFormData load(GenerateFormData formData);

	GenerateFormData store(GenerateFormData formData);
	
	Object[][] getAppNameByUserName(String userName);

	String getAppIdByAppName(String appname);
	
	String getVersionIdByAppId(String appId);
	
	String getVersionByAppId(String appId);
	
	void updateVersionIdByAppId(String appId);
	
	void preGenerateApp(String appId, String versionId, String version, String appName, BinaryResource imageData,
			BinaryResource splashScreenData, String url) throws FileNotFoundException, IOException;
	
}
