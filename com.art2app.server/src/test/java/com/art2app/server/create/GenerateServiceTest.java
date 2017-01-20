package com.art2app.server.create;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.server.jdbc.mysql.AbstractMySqlSqlService;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.art2app.server.ServerSession;
import com.art2app.server.create.GenerateService;
import com.art2app.server.sql.SQLs;
import com.art2app.server.utils.IOUtils;
import com.art2app.shared.create.IGenerateService;
import com.art2app.shared.exception.URLException;

@RunWithSubject("anonymous")
@RunWith(ServerTestRunner.class)
@RunWithServerSession(ServerSession.class)
public class GenerateServiceTest {

	@BeforeClass
	public static void conn() {
		BEANS.get(AbstractMySqlSqlService.class).getConnection();
	}

	@AfterClass
	public static void stop() {
		BEANS.get(AbstractMySqlSqlService.class).destroySqlConnectionPool();
	}

	@Before
	public void before() {
	}

	@Test
	public void getAppId() {
		GenerateService generateService = new GenerateService();
		generateService.getAppIdByAppName("");
	}

	 @Test
	 public void testPreGenerateApp() throws FileNotFoundException, IOException {
	 GenerateService generateService = new GenerateService();
	 String appname = "test1";
	 String url = "http://www.baidu.com";
	 String appid = "theAppId";
	 String versionId = "123";
	 String version = "1.0.2";
	 String imagepath = "src/test/resources/icon.png";
	 String splashScreenPath = "src/test/resources/splash.png";
	 File image = new File(imagepath);
	 BinaryResource imageResource;	
		imageResource = new BinaryResource(image.getName(),
		 IOUtils.readFileToByteArray(imagepath));
		 File splashScreen = new File(splashScreenPath);
		 BinaryResource splashResource = new
		 BinaryResource(splashScreen.getName(),
		 IOUtils.readFileToByteArray(splashScreenPath));
		 generateService.preGenerateApp(appid,versionId, version, appname, imageResource,
		 splashResource, url);
		
	 }

	@Test
	public void testPreGenerateAppByWrongURL() {
		GenerateService generateService = new GenerateService();
		String appname = "test1";
		String url = "jjknjkcxvjbksdf";
		String appid = "theAppId";
		String versionId = "123";
		String version = "1.0.2";
		try {
			generateService.preGenerateApp(appid, versionId, version, appname, null, null, url);
		} catch (Exception e) {
			Assert.assertEquals(new URLException(), e);
			e.printStackTrace();
		} 
		
	}
	
	private static final String SELECT_VERSION = ""
		  		+ "SELECT version from app_version where ID = :versionId "
		  		+ "into :version ";

	private static final String SELECT_APK_URL = ""
	  		+ "SELECT APK_URL from app_version where ID = :versionId "
	  		+ "into :apkUrl ";

	private static final String SELECT_LATEST_VERSION_ID_BY_APP_ID = ""
			+ "SELECT LATEST_VERSION_ID FROM app where ID = :appId "
			+ "into :versionId";

	@Test
	public void testGenerateSuccess() throws ClassNotFoundException, SQLException {
		GenerateService generateService = new GenerateService();
		// TODO sb, create an apkId first, this one comes from the database
		String appId = "14";
		long currentTimeMillis = System.currentTimeMillis();
		String expectedVersion = "theVersion_" + currentTimeMillis;
		String expectedVersionId = "12";
		String expectedApkUrl = "theApkPath_" +  + currentTimeMillis;
		generateService.generateSuccess(expectedVersion, expectedApkUrl, expectedVersionId, appId);

		checkVersionIdAndApkUrl(generateService, appId, expectedVersion, expectedVersionId, expectedApkUrl);
	}

	private void checkVersionIdAndApkUrl(GenerateService generateService, String appId, String expectedVersion,
			String expectedVersionId, String expectedApkUrl) {
		String actualVersion = generateService.getValueByParams(SELECT_VERSION, "version", new NVPair("versionId", expectedVersionId));
		Assert.assertEquals(expectedVersion, actualVersion);
		
		String apkUrl = generateService.getValueByParams(SELECT_APK_URL, "apkUrl", new NVPair("versionId", expectedVersionId));
		Assert.assertEquals(expectedApkUrl, apkUrl);
		
		String actualVersionId = generateService.getValueByParams(SELECT_LATEST_VERSION_ID_BY_APP_ID, "versionId", new NVPair("appId", appId));
		Assert.assertEquals(expectedVersionId, actualVersionId);
	}
	
	@Test
	public void testGenerateFailed() throws ClassNotFoundException, SQLException {
		GenerateService generateService = new GenerateService();
		// TODO sb, create an apkId first, this one comes from the database
		String appId = "14";
		long currentTimeMillis = System.currentTimeMillis();
		String expectedVersion = "theVersion" + currentTimeMillis;
		String expectedVersionId = "12";
		generateService.generateFailed(expectedVersion, expectedVersionId, appId);

		checkVersionIdAndApkUrl(generateService, appId, expectedVersion, expectedVersionId, IGenerateService.FAILED);
	}
	

	@Test
	public void testGetValueByParams() {
		GenerateService generateService = new GenerateService();
		String valueByParams = generateService.getValueByParams(SQLs.SELECT_APPID, "appId",new NVPair("appname", "app1"),new NVPair("username", "admin"));
		Assert.assertEquals("169", valueByParams);
	}
	
	
}
