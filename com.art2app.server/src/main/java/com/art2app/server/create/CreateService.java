package com.art2app.server.create;

import java.io.File;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.ISession;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import com.art2app.server.ConfigProperties;
import com.art2app.server.sql.SQLs;
import com.art2app.shared.create.CreateCreatPermission;
import com.art2app.shared.create.CreateFormData;
import com.art2app.shared.create.CreateTablePageData;
import com.art2app.shared.create.ICreateService;
import com.art2app.shared.create.ReadCreatPermission;
import com.art2app.shared.create.UpdateCreatPermission;
import com.art2app.shared.exception.NameDuplicateException;
import com.art2app.shared.exception.URLException;

public class CreateService implements ICreateService {

	private static Logger logger = LogManager.getLogger(CreateService.class);
	public String URL_PATTERN = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
			+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
			+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
			+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
			+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
			+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
			+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
			+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
	
	@Override
	public CreateTablePageData getCreatTableData(SearchFilter filter) {
		CreateTablePageData pageData = new CreateTablePageData();
		return pageData;
	}

	@Override
	public CreateFormData prepareCreate(CreateFormData formData) {
		if (!ACCESS.check(new CreateCreatPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		return formData;
	}

	@Override
	public CreateFormData create(CreateFormData formData) {
		if (!ACCESS.check(new CreateCreatPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		return formData;
	}

	@Override
	public CreateFormData load(CreateFormData formData) {
		if (!ACCESS.check(new ReadCreatPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}

		return formData;
	}

	@Override
	public CreateFormData store(CreateFormData formData) {
		if (!ACCESS.check(new UpdateCreatPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}

		return formData;
	}

	@Override
	public CreateFormData selectAppname(String username, String appname, CreateFormData formData) {
		SQL.selectInto(SQLs.SELECT_APPNAME, new NVPair("username", username), new NVPair("appname", appname), formData);
		return formData;
	}

	@Override
	public CreateFormData insertAppVersion(String appId, String appname, BinaryResource icon, String icon_filename,
			BinaryResource splashscreen, String splash_filename, String webtomobile_url, int setting) throws URLException {
		
		if (webtomobile_url !=null && !Pattern.matches(URL_PATTERN, webtomobile_url)) {
			
				logger.error(TEXTS.get("PleaseInputCorrectURL"));
				throw new URLException(TEXTS.get("PleaseInputCorrectURL"));
			
		}
		
		if (splashscreen == null) {
			SQL.insert(SQLs.INSERT_APP_VERSION_ICON, new NVPair("appId", appId), new NVPair("appname", appname),
					new NVPair("icon", icon.getContent()), new NVPair("icon_filename", icon_filename),
					new NVPair("webtomobile_url", webtomobile_url), new NVPair("setting", setting));
		} else {
			SQL.insert(SQLs.INSERT_APP_VERSION, new NVPair("appId", appId), new NVPair("appname", appname),
					new NVPair("icon", icon.getContent()), new NVPair("icon_filename", icon_filename),
					new NVPair("splashscreen", splashscreen.getContent()),
					new NVPair("splash_filename", splash_filename), new NVPair("webtomobile_url", webtomobile_url),
					new NVPair("setting", setting));
		}
		return null;
	}

	@Override
	public void insertApp(String appname, String username)  {
		CreateFormData formData = new CreateFormData();
		SQL.selectInto(SQLs.SELECT_APPNAME, new NVPair("username", username), new NVPair("appname", appname), formData);
		if (appname.equals(formData.getName().getValue())) {
			logger.error("The appName already exists");
			try {
				throw new NameDuplicateException("The appName already exists");
			} catch (NameDuplicateException e) {
				e.printStackTrace();
			}
		} else {
			SQL.insert(SQLs.INSERT_APP, new NVPair("appname", appname), new NVPair("username", username));
		}
	}

	@Override
	public CreateFormData getAppVersionByAppId(String version, String appId) {
		CreateFormData formData = new CreateFormData();
		SQL.select(SQLs.SELECT_APP_VERSION_EDIT, new NVPair("version", version), new NVPair("appId", appId), formData);
		return formData;
	}

	@Override
	public CreateFormData updateApp(String appname, String appid) {
		SQL.update(SQLs.UPDATE_APP, new NVPair("appname", appname), new NVPair("appid", appid));
		return null;
	}

	@Override
	public void deleteApp(String appId) {
		Object[][] select = SQL.select(SQLs.SELECT_APPVERSION, new NVPair("appId", appId));
		String path = CONFIG.getPropertyValue(ConfigProperties.ServerRootPathProperty.class);
		String user = ISession.CURRENT.get().getUserId();
		for (int i = 0; i < select.length; i++) {
			for (int j = 0; j < select[i].length; j++) {
				deleteFile(path + user + "/icon/", select[i][2].toString());
				if (select[i][3] != null) {
					deleteFile(path + user + "/splash/", select[i][3].toString());
				}
				if (!"failed".equals(select[i][4]) && !"generating".equals(select[i][4])) {
					deleteFile(path + user + "/apk/", select[i][1] + appId + select[i][0] + ".apk");
				}

			}
		}
		SQL.delete(SQLs.DELETE_APPVERSION, new NVPair("appId", appId));
		SQL.delete(SQLs.DELETE_APP, new NVPair("appId", appId));
	}

	public void deleteFile(String filePath, String fileName) {
		File folder = new File(filePath);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().equals(fileName)) {
				file.delete();
			}
		}
	}

}
