package com.art2app.server.create;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import com.art2app.shared.create.CreateSplashPermission;
import com.art2app.shared.create.ISplashService;
import com.art2app.shared.create.ReadSplashPermission;
import com.art2app.shared.create.SplashFormData;
import com.art2app.shared.create.UpdateSplashPermission;

public class SplashService implements ISplashService {

	@Override
	public SplashFormData prepareCreate(SplashFormData formData) {
		if (!ACCESS.check(new CreateSplashPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public SplashFormData create(SplashFormData formData) {
		if (!ACCESS.check(new CreateSplashPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public SplashFormData load(SplashFormData formData) {
		if (!ACCESS.check(new ReadSplashPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public SplashFormData store(SplashFormData formData) {
		if (!ACCESS.check(new UpdateSplashPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

}
