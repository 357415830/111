package com.art2app.server.create;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringHolder;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.ISession;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import com.art2app.generator.api.AppPackage;
import com.art2app.server.ConfigProperties;
import com.art2app.server.sql.MySqlSqlService;
import com.art2app.server.sql.SQLs;
import com.art2app.server.utils.AppGenerateUtils;
import com.art2app.server.utils.DBHelper;
import com.art2app.server.utils.IOUtils;
import com.art2app.shared.create.CreateGeneratePermission;
import com.art2app.shared.create.GenerateFormData;
import com.art2app.shared.create.IGenerateService;
import com.art2app.shared.create.ReadGeneratePermission;
import com.art2app.shared.create.UpdateGeneratePermission;
import com.art2app.shared.exception.URLException;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class GenerateService implements IGenerateService {
	private static Logger logger = LogManager.getLogger(GenerateService.class);
	private static final String GENERATION_FOLDER = "art2app";
	private static final String android_Platform_Tools_Path = "/home/moi/Project/android/android-sdk-linux/platform-tools";
	public String URL_PATTERN = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
			+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
			+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
			+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
			+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
			+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
			+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
			+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";

	@Override
	public GenerateFormData prepareCreate(GenerateFormData formData) {
		if (!ACCESS.check(new CreateGeneratePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Administrator] add business logic here.
		return formData;
	}

	@Override
	public GenerateFormData create(GenerateFormData formData) {
		if (!ACCESS.check(new CreateGeneratePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Administrator] add business logic here.
		return formData;
	}

	@Override
	public GenerateFormData load(GenerateFormData formData) {
		if (!ACCESS.check(new ReadGeneratePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Administrator] add business logic here.
		return formData;
	}

	@Override
	public GenerateFormData store(GenerateFormData formData) {
		if (!ACCESS.check(new UpdateGeneratePermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Administrator] add business logic here.
		return formData;
	}

	@Override
	public Object[][] getAppNameByUserName(String userName) {
		Object[][] select = SQL.select(SQLs.SELECT_APP, new NVPair("username", userName));
		return select;
	}

	@Override
	public void preGenerateApp(String appId, String versionId, String version, String appName, BinaryResource imageData,
			BinaryResource splashScreenData, String url) throws FileNotFoundException, IOException {

		String appGitUrl = CONFIG.getPropertyValue(ConfigProperties.AppGitUrlProperty.class);

		String appGitBranch = CONFIG.getPropertyValue(ConfigProperties.AppGitBranchProperty.class);

		String zipAlignPath = CONFIG.getPropertyValue(ConfigProperties.ZipAlignPathProperty.class);

		String keyStore = CONFIG.getPropertyValue(ConfigProperties.ResourceKeystoreUrlProperty.class);

		String storePass = CONFIG.getPropertyValue(ConfigProperties.StorePassProperty.class);

		String urlValue = CONFIG.getPropertyValue(ConfigProperties.UrlPrefixProperty.class);

		String serverRootPath = CONFIG.getPropertyValue(ConfigProperties.ServerRootPathProperty.class);

		String androidHome = CONFIG.getPropertyValue(ConfigProperties.AndroidHomeProperty.class);

		String userName = ISession.CURRENT.get().getUserId();
		String Target_dir = serverRootPath + "/" + userName;
		String Target_dir_icon = Target_dir + "/icon";
		String Target_dir_splash = Target_dir + "/splash";
		String Target_dir_apk = Target_dir + "/apk";
		byte[] imageBytes = null;
		byte[] splashScreenBytes = null;
		File image = null;
		File splashScreen = null;
		// save input data
		if (url != null && !Pattern.matches(URL_PATTERN, url)) {
			try {
				logger.error(TEXTS.get("PleaseInputCorrectURL"));
				throw new URLException(TEXTS.get("PleaseInputCorrectURL"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (imageData != null && imageData.getContent() != null) {
			imageBytes = imageData.getContent();
			image = IOUtils.writeByteArrayToFile(imageBytes, Target_dir_icon, imageData.getFilename());
		}
		if (splashScreenData != null && splashScreenData.getContent() != null) {
			splashScreenBytes = splashScreenData.getContent();
			splashScreen = IOUtils.writeByteArrayToFile(splashScreenBytes, Target_dir_splash,
					splashScreenData.getFilename());
		}
		AppGenerateUtils appGenerateUtils = new AppGenerateUtils(appName, image, splashScreen, url);
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
		Scheduler scheduler = Schedulers.from(newFixedThreadPool);
		Observable<AppPackage> appPackageObservable = appGenerateUtils.preGenerateApp(scheduler,appGitUrl, appGitBranch, GENERATION_FOLDER,
				new File(keyStore), storePass, androidHome, new File(zipAlignPath));

		appPackageObservable.subscribe(appPackage -> {
			try (InputStream packageStream = appPackage.getPackage()) {
				File file = new File(Target_dir_apk);
				if (!file.exists()) {
					file.mkdirs();
				}
				String TARGET_RESULT_APK = Target_dir_apk + "/" + appName + appId + versionId + ".apk";

				appGenerateUtils.installOnDevice(appPackage, android_Platform_Tools_Path, TARGET_RESULT_APK);
				String apkpath = urlValue + "/" + userName + "/apk/" + appName + appId + versionId + ".apk";
				generateSuccess(version, apkpath, versionId, appId);
				newFixedThreadPool.shutdownNow();
				System.out.println("newFixedThreadPool------------------->"+newFixedThreadPool.isShutdown());
			} catch (Exception e) {
				logger.error(e);
				try {
					generateFailed(version, versionId, appId);
				} catch (ClassNotFoundException | SQLException e1) {
					logger.error(e);
				}
			}

		}, t -> {
			logger.error(t);
			try {
				generateFailed(version, versionId, appId);
			} catch (ClassNotFoundException | SQLException e) {
				logger.error(e);
			}
		}

		);

	}

	void generateSuccess(String version, String apkpath, String versionId, String appId) throws ClassNotFoundException, SQLException {
		generateApkUrlImpl(version, apkpath, versionId, appId);
	}

	private void generateApkUrlImpl(String version, String apkpath, String versionId, String appId)
			throws ClassNotFoundException, SQLException {
		try (DBHelper dbHelper = new DBHelper();) {
			dbHelper.init(BEANS.get(MySqlSqlService.class));
			String[] params = { version, apkpath, versionId };
			dbHelper.prepareUpdate(SQLs.UPDATE_APP_VERSION_APKURL, params);
			String[] param = { versionId, appId + "" };
			dbHelper.prepareUpdate(SQLs.UPDATE_APP_VERSIONID, param);
			
			dbHelper.execute();
		}
	}

	void generateFailed(String version, String versionId, String appId) throws ClassNotFoundException, SQLException {
		generateApkUrlImpl(version, FAILED, versionId, appId);
	}

	@Override
	public String getAppIdByAppName(String appname) {
		return getValueByParams(SQLs.SELECT_APPID, "appId", new NVPair("appname", appname),
				new NVPair("username", ISession.CURRENT.get().getUserId()));
	}

	@Override
	public String getVersionIdByAppId(String appId) {
		return getValueByParams(SQLs.SELECT_VERSIONID, "versionid", new NVPair("appId", appId));
	}

	@Override
	public void updateVersionIdByAppId(String appId) {
		SQL.update(SQLs.UPDATE_APP_NULLVERSIONID, new NVPair("appId", appId));
	}

	@Override
	public String getVersionByAppId(String appId) {
		return getValueByParams(SQLs.SELECT_VERSION, "version", new NVPair("appid", appId));
	}

	/**
	 * Through the SQL statements, the key value of parameters and return values
	 * name for the query results
	 * 
	 * @param sql
	 * @param result
	 * @param nvPairs
	 * @return value
	 */
	public String getValueByParams(String sql, String result, NVPair... nvPairs) {
		StringHolder resultHolder = new StringHolder();
		NVPair resultPair = new NVPair(result, resultHolder);
		List<NVPair> list = new ArrayList<NVPair>();
		for (int i = 0; i < nvPairs.length; i++) {
			list.add(nvPairs[i]);
		}
		list.add(resultPair);
		NVPair[] newStr = list.toArray(new NVPair[1]);
		SQL.selectInto(sql, newStr);
		return resultHolder.getValue();
	}

}
