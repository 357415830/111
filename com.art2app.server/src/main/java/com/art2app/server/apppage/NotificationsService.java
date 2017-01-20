package com.art2app.server.apppage;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import com.art2app.shared.apppage.CreateNotificationsPermission;
import com.art2app.shared.apppage.INotificationsService;
import com.art2app.shared.apppage.NotificationsFormData;
import com.art2app.shared.apppage.NotificationsTablePageData;
import com.art2app.shared.apppage.ReadNotificationsPermission;
import com.art2app.shared.apppage.UpdateNotificationsPermission;

public class NotificationsService implements INotificationsService {

	@Override
	public NotificationsTablePageData getNotificationsTableData(SearchFilter filter) {
		NotificationsTablePageData pageData = new NotificationsTablePageData();
		// TODO [admin] fill pageData.
		return pageData;
	}

	@Override
	public NotificationsFormData prepareCreate(NotificationsFormData formData) {
		if (!ACCESS.check(new CreateNotificationsPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public NotificationsFormData create(NotificationsFormData formData) {
		if (!ACCESS.check(new CreateNotificationsPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public NotificationsFormData load(NotificationsFormData formData) {
		if (!ACCESS.check(new ReadNotificationsPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public NotificationsFormData store(NotificationsFormData formData) {
		if (!ACCESS.check(new UpdateNotificationsPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}
}
