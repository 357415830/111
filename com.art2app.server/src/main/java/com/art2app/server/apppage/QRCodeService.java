package com.art2app.server.apppage;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import com.art2app.shared.apppage.CreateQRCodePermission;
import com.art2app.shared.apppage.IQRCodeService;
import com.art2app.shared.apppage.QRCodeFormData;
import com.art2app.shared.apppage.QRCodeTablePageData;
import com.art2app.shared.apppage.ReadQRCodePermission;
import com.art2app.shared.apppage.UpdateQRCodePermission;



public class QRCodeService implements IQRCodeService {

	@Override
	public QRCodeTablePageData getQRCodeTableData(SearchFilter filter) {
		QRCodeTablePageData pageData = new QRCodeTablePageData();
		// TODO [admin] fill pageData.
		return pageData;
	}

	@Override
	public QRCodeFormData prepareCreate(QRCodeFormData formData) {
		if (!ACCESS.check(new CreateQRCodePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public QRCodeFormData create(QRCodeFormData formData) {
		if (!ACCESS.check(new CreateQRCodePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public QRCodeFormData load(QRCodeFormData formData) {
		if (!ACCESS.check(new ReadQRCodePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}

	@Override
	public QRCodeFormData store(QRCodeFormData formData) {
		if (!ACCESS.check(new UpdateQRCodePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [admin] add business logic here.
		return formData;
	}
}
