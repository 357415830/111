package com.art2app.shared.create;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import com.art2app.shared.create.SplashFormData;

@TunnelToServer
public interface ISplashService extends IService {

	SplashFormData prepareCreate(SplashFormData formData);

	SplashFormData create(SplashFormData formData);

	SplashFormData load(SplashFormData formData);

	SplashFormData store(SplashFormData formData);
	
}
