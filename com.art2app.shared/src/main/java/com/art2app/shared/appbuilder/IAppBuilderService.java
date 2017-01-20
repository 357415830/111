package com.art2app.shared.appbuilder;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IAppBuilderService extends IService {

	AppBuilderFormData prepareCreate(AppBuilderFormData formData);

	AppBuilderFormData create(AppBuilderFormData formData);

	AppBuilderFormData load(AppBuilderFormData formData);

	AppBuilderFormData store(AppBuilderFormData formData);
}
