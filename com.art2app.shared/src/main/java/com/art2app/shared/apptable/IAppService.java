package com.art2app.shared.apptable;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.shared.apptable.AppTablePageData.AppTableRowData;

@TunnelToServer
public interface IAppService extends IService {

	AppTableRowData getAppTableData(SearchFilter filter);

	AppFormData prepareCreate(AppFormData formData);

	AppFormData create(AppFormData formData);

	AppFormData load(AppFormData formData);

	AppFormData store(AppFormData formData);

	AppFormData setApp(String name,String user);
}
