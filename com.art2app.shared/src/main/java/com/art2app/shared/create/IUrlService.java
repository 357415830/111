package com.art2app.shared.create;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import com.art2app.shared.create.UrlFormData;

@TunnelToServer
public interface IUrlService extends IService {

	UrlFormData prepareCreate(UrlFormData formData);

	UrlFormData create(UrlFormData formData);

	UrlFormData load(UrlFormData formData);

	UrlFormData store(UrlFormData formData);
	
}
