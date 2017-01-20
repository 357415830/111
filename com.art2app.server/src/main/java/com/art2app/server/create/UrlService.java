package com.art2app.server.create;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import com.art2app.shared.create.CreateUrlPermission;
import com.art2app.shared.create.IUrlService;
import com.art2app.shared.create.ReadUrlPermission;
import com.art2app.shared.create.UpdateUrlPermission;
import com.art2app.shared.create.UrlFormData;

public class UrlService implements IUrlService {

	@Override
	public UrlFormData prepareCreate(UrlFormData formData) {
		if (!ACCESS.check(new CreateUrlPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public UrlFormData create(UrlFormData formData) {
		if (!ACCESS.check(new CreateUrlPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public UrlFormData load(UrlFormData formData) {
		if (!ACCESS.check(new ReadUrlPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public UrlFormData store(UrlFormData formData) {
		if (!ACCESS.check(new UpdateUrlPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}
}
