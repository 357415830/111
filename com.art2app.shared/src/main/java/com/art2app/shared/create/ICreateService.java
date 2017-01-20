package com.art2app.shared.create;

import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.shared.create.CreateFormData;
import com.art2app.shared.create.CreateTablePageData;
import com.art2app.shared.exception.NameDuplicateException;
import com.art2app.shared.exception.URLException;

@TunnelToServer
public interface ICreateService extends IService {

	CreateTablePageData getCreatTableData(SearchFilter filter);

	CreateFormData prepareCreate(CreateFormData formData);

	CreateFormData create(CreateFormData formData);

	CreateFormData load(CreateFormData formData);

	CreateFormData store(CreateFormData formData);

	CreateFormData selectAppname(String username, String appName, CreateFormData formData);

	void insertApp(String appName, String username) throws NameDuplicateException;

	CreateFormData updateApp(String appName, String appId);

	CreateFormData insertAppVersion(String appId, String appName, BinaryResource icon, String icon_filename,
			BinaryResource splashscreen, String splash_filename, String webtomobile_url, int setting) throws URLException;

	CreateFormData getAppVersionByAppId(String version, String appId);
	
	void deleteApp(String appId);
}
