package com.art2app.shared.apppage;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;



@TunnelToServer
public interface IQRCodeService extends IService {

	QRCodeTablePageData getQRCodeTableData(SearchFilter filter);

	QRCodeFormData create(QRCodeFormData formData);

	QRCodeFormData load(QRCodeFormData formData);

	QRCodeFormData store(QRCodeFormData formData);

	QRCodeFormData prepareCreate(QRCodeFormData formData);
}
