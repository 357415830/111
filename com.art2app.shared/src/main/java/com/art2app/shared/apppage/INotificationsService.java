package com.art2app.shared.apppage;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface INotificationsService extends IService {

	NotificationsTablePageData getNotificationsTableData(SearchFilter filter);

	NotificationsFormData prepareCreate(NotificationsFormData formData);

	NotificationsFormData create(NotificationsFormData formData);

	NotificationsFormData load(NotificationsFormData formData);

	NotificationsFormData store(NotificationsFormData formData);
}
