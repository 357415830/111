package com.art2app.server.create;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import com.art2app.shared.create.CreateChooseFormData;
import com.art2app.shared.create.CreateCreatChoosePermission;
import com.art2app.shared.create.ICreateChooseService;
import com.art2app.shared.create.ReadCreatChoosePermission;
import com.art2app.shared.create.UpdateCreatChoosePermission;

public class CreateChooseService implements ICreateChooseService {

	@Override
	public CreateChooseFormData prepareCreate(CreateChooseFormData formData) {
		if (!ACCESS.check(new CreateCreatChoosePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public CreateChooseFormData create(CreateChooseFormData formData) {
		if (!ACCESS.check(new CreateCreatChoosePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public CreateChooseFormData load(CreateChooseFormData formData) {
		if (!ACCESS.check(new ReadCreatChoosePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public CreateChooseFormData store(CreateChooseFormData formData) {
		if (!ACCESS.check(new UpdateCreatChoosePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}
}
