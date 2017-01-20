package com.art2app.server.create;

import java.io.File;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.util.IOUtility;
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
import com.art2app.server.create.CreateService;
import com.art2app.shared.create.CreateFormData;
import com.art2app.shared.exception.NameDuplicateException;
import com.art2app.shared.exception.URLException;


@RunWithSubject("anonymous")
@RunWith(ServerTestRunner.class)
@RunWithServerSession(ServerSession.class)
public class CreatServiceTest {

	private CreateFormData formData = new CreateFormData();
	private CreateService creatService = new CreateService();
	private String username;
	private String appname;
	private String version;
	private String appid;
	private String icon_filename;
	private String path;
	private String splash_filename;
	private String webtomobile_url;
	private int setting;
	@BeforeClass
	public static void conn(){			
		BEANS.get(AbstractMySqlSqlService.class).getConnection();
	}
	@AfterClass
	public static void stop(){
		BEANS.get(AbstractMySqlSqlService.class).destroySqlConnectionPool();
	}
	@Before
	public void before() {
		username = "admin";
		appname = "test1";
		version = "1.0.1";
		appid = "250";
		path = "src/test/resources/";
		icon_filename = "2.png";
		splash_filename = "test.png";
	  }
	
	//check if app name is unique, or it is already exists in database
	@Test
	public void SelectAppInfo(){
		CreateFormData select = creatService.selectAppname(username, appname, formData);
		Assert.assertNull(select.getName().getValue());
		//Assert.assertEquals("test1", select.getName().getValue());
	}
	
	@Test
	public void InsertAppInfoCorrectData(){		
			creatService.insertApp(appname, username);		
		CreateFormData select = creatService.selectAppname(username, appname, formData);
		Assert.assertEquals(appname, select.getName().getValue());
	}
	
	
	
	@Test
	public void InsertAppInfoRepeatData(){
		try {
			creatService.insertApp("app10", username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertEquals(new NameDuplicateException("The appName already exists"), e);
		}
	}
	
	@Test
	public void UpdateAppInfo(){
		String prename = creatService.selectAppname(username, appname, formData).getName().getValue();
		String name = "test2";
		creatService.updateApp(name, appid);
		String nextname = creatService.selectAppname(username, name, formData).getName().getValue();
		Assert.assertNotEquals(prename, nextname);
	}
	
	@Test
	public void SelectAppVersion(){
		CreateFormData selectAppVersion = creatService.getAppVersionByAppId(version, appid);
		Assert.assertNotNull(selectAppVersion.getAppid());
	}
	
	@Test
	public void InsertAppVersion(){
		byte[] iconContent = IOUtility.getContent(new File(path+icon_filename));
		byte[] splashscreenContent = IOUtility.getContent(new File(path+splash_filename));
		BinaryResource icon = new BinaryResource(icon_filename, iconContent);
		BinaryResource splashscreen = new BinaryResource(splash_filename, splashscreenContent);
		try {
			creatService.insertAppVersion(appid, appname, icon, icon_filename, splashscreen, splash_filename, webtomobile_url, setting);
		} catch (URLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void InsertAppVersionNOSplash(){
		byte[] iconContent = IOUtility.getContent(new File(path+icon_filename));
		//byte[] splashscreenContent = IOUtility.getContent(new File(path+splash_filename));
		BinaryResource icon = new BinaryResource(icon_filename, iconContent);
		BinaryResource splashscreen = null;
		try {
			creatService.insertAppVersion(appid, appname, icon, icon_filename, splashscreen, splash_filename, webtomobile_url, setting);
		} catch (URLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
