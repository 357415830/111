package com.art2app.server.appbuilder;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import com.art2app.shared.appbuilder.AppBuilderFormData;
import com.art2app.shared.appbuilder.CreateAppBuilderPermission;
import com.art2app.shared.appbuilder.IAppBuilderService;
import com.art2app.shared.appbuilder.ReadAppBuilderPermission;
import com.art2app.shared.appbuilder.UpdateAppBuilderPermission;

public class AppBuilderService implements IAppBuilderService {

	@Override
	public AppBuilderFormData prepareCreate(AppBuilderFormData formData) {
		if (!ACCESS.check(new CreateAppBuilderPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Ecsoya] add business logic here.
		return formData;
	}

	@Override
	public AppBuilderFormData create(AppBuilderFormData formData) {
		if (!ACCESS.check(new CreateAppBuilderPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Ecsoya] add business logic here.
		return formData;
	}

	@Override
	public AppBuilderFormData load(AppBuilderFormData formData) {
		if (!ACCESS.check(new ReadAppBuilderPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Ecsoya] add business logic here.
		return formData;
	}

	@Override
	public AppBuilderFormData store(AppBuilderFormData formData) {
		if (!ACCESS.check(new UpdateAppBuilderPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Ecsoya] add business logic here.
		return formData;
	}
}
