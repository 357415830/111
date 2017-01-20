package com.art2app.shared.create;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import com.art2app.shared.create.CreateChooseFormData;

@TunnelToServer
public interface ICreateChooseService extends IService {

	CreateChooseFormData prepareCreate(CreateChooseFormData formData);

	CreateChooseFormData create(CreateChooseFormData formData);

	CreateChooseFormData load(CreateChooseFormData formData);

	CreateChooseFormData store(CreateChooseFormData formData);
}
