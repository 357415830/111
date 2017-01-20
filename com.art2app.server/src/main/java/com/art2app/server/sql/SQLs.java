package com.art2app.server.sql;

// tag::createDB[]
public interface SQLs {

  String APP_PAGE_SELECT = ""
      + "SELECT   NAME, "
      + "         USER_ID, "
      + "         DESCRIPTION "
      + "FROM     app";

  String APP_PAGE_DATA_SELECT_INTO = ""
      + "INTO     :{page.name}, "
      + "         :{page.userId}, "
      + "         :{page.description}";

  String NOTIFICATION_CATEGORY_PAGE_SELECT = ""
	      + "SELECT   ID, "
		  + "         CATEGORY_NAME, "
		  + "         LAST_UPDATETIME "
	      + "FROM     app_notification_category ";

	  String NOTIFICATION_CATEGORY_PAGE_DATA_SELECT_INTO = ""
	      + "INTO     :{page.categoryId}, "
	      + "         :{page.categoryName}, "
	      + "         :{page.lastUpdatetime} ";

  
  String USER_PAGE_SELECT = ""
	      + "SELECT   ID, "
		  + "         FIRST_NAME, "
		  + "         LAST_NAME, "
		  + "         COMPANY, "
		  + "         PASSWORD, "
		  + "         EMAIL, "
		  + "         STATUS, "
		  + "         VALIDATECODE, "
		  + "         REGISTERTIME "
	      + "FROM     user ";

	  String USER_PAGE_DATA_SELECT_INTO = ""
	      + "INTO     :{page.accountId}, "
	      + "         :{page.firstName}, "
	      + "         :{page.lastName}, "
	      + "         :{page.company}, "
	      + "         :{page.password}, "
	      + "         :{page.email}, "
	      + "         :{page.status}, "
	      + "         :{page.validateCode}, "
	      + "         :{page.registerTime} ";
	  String APP_SELECT="SELECT APPID,APPNAME FROM apptest INTO :{page.appId},:{page.appName}";
	  String PERSON_INSERT = ""
      + "INSERT   INTO "
      + "PERSON  (person_id) "
      + "VALUES   (:personId)";

  String USER_SELECT = ""
	      + "SELECT   "
		  + "         FIRST_NAME, "
		  + "         LAST_NAME, "
		  + "         COMPANY, "
		  + "         EMAIL "
	      + "FROM     user "
      + "WHERE    ID = :accountId "
      + "INTO     :firstName, "
      + "         :lastName, "
      + "         :company, "
      + "         :email ";

  String USER_ID_SELECT = ""
	      + "SELECT   "
	      + "ID, "
		  + "LAST_NAME "
	      + "FROM     user "
      + "WHERE    LAST_NAME = :lastName "
      + "INTO     :accountId ";
  
  String LOGIN_SELECT="SELECT ID,USER_NAME,PASSWORD FROM user WHERE USER_NAME= :username AND PASSWORD= :password INTO :accountId ";
  
  String USER_UPDATE = ""
      + "UPDATE   user "
      + "SET      FIRST_NAME = :firstName, "
      + "         LAST_NAME = :lastName, "
      + "         COMPANY = :company, "
      + "         EMAIL = :email "
      + "WHERE    ID = :accountId";
/*--------------------------------------------------*/
	  
      String SELECT_APP = ""
      		+ "SELECT NAME FROM app WHERE USER_ID = ( SELECT ID FROM `user` WHERE USER_NAME= :username ) ";
  
  	  String SELECT_APPNAME = ""
	  		+ "SELECT NAME FROM app WHERE USER_ID = ( SELECT ID FROM `user` WHERE USER_NAME= :username ) AND NAME = :appname "
	  		+ "into :Name";
	  
  	  String INSERT_APP = ""
	  		+ "INSERT INTO app (NAME,USER_ID,LAST_UPDATETIME) VALUES (:appname,(SELECT ID from user where USER_NAME = :username),NOW())";
	  
  	  String UPDATE_APP = ""
	  		+ "UPDATE app SET NAME = :appname WHERE ID = :appid";
	 
  	  String INSERT_APP_VERSION = ""
	  		+ "INSERT INTO app_version (APP_ID,NAME,ICON,ICON_FILENAME,SPLASHSCREEN,SPLASHSCREEN_FILENAME,WEBTOMOBILE_URL,HAS_SETTINGS,DATETIME,APK_URL,IOS_URL) VALUES (:appId"
	  		+ ",:appname,:icon,:icon_filename,:splashscreen,:splash_filename,:webtomobile_url,:setting,NOW(),'generating','no generating') ";
	  
  	  String INSERT_APP_VERSION_ICON = ""
	  		+ "INSERT INTO app_version (APP_ID,NAME,ICON,ICON_FILENAME,WEBTOMOBILE_URL,HAS_SETTINGS,DATETIME,APK_URL,IOS_URL) values (:appId,:appname,:icon"
	  		+ ",:icon_filename,:webtomobile_url,:setting,NOW(),'generating','no generating')";
	  
  	  String SELECT_APP_VERSION = ""
		  		+ "SELECT VERSION,DATETIME,APK_URL,IOS_URL FROM app_version WHERE ID = ( SELECT LATEST_VERSION_ID FROM app WHERE NAME = :appname AND USER_ID = (SELECT ID FROM `user` WHERE USER_NAME = :username)) "
		  		+ "INTO :version,"
			  		+ "		:date,"
			  		+ "		:download,"
			  		+ "		:downloada";
	  
	  String SELECT_APP_VERSION_EDIT = ""
		  		+ "SELECT ICON,ICON_FILENAME,SPLASHSCREEN,SPLASHSCREEN_FILENAME,WEBTOMOBILE_URL,HAS_SETTINGS FROM app_version WHERE version = :version AND APP_ID = :appId "
		  		+ "INTO :icons,"
		  		+ "		:icon_filename,"
		  		+ "		:splashscreen,"
		  		+ "		:splash_filename,"
		  		+ "		:webtomobile_url,"
		  		+ "		:setting";
	  String SELECT_APPID = ""
		  		+ "SELECT id from app where NAME = :appname and USER_ID = (select ID from user where USER_NAME = :username) "
		  		+ "into :appId";
	  String SELECT_VERSIONID = ""
	  		+ "SELECT max(ID) FROM app_version where APP_ID = :appId "
	  		+ "into :versionid";
	  String SELECT_VERSION = ""
	  		+ "SELECT max(version) from app_version where APP_ID = :appid"
	  		+ "into :version ";
	  String SELECT_APPVERSION = "SELECT ID,NAME,ICON_FILENAME,SPLASHSCREEN_FILENAME,APK_URL FROM app_version WHERE APP_ID = :appId";
	  String DELETE_APPVERSION = "DELETE FROM app_version WHERE APP_ID = :appId";
	  String DELETE_APP = "DELETE FROM app WHERE ID = :appId";
	  String UPDATE_APP_VERSION_APKURL = ""
	  		+ "update app_version set VERSION = ?, APK_URL = ? where ID = ?";
	  String UPDATE_APP_VERSIONID = ""
    		+ "UPDATE app set LATEST_VERSION_ID = ? where ID = ? ";
	  String UPDATE_APP_NULLVERSIONID = ""
    		+ "UPDATE app set LATEST_VERSION_ID = null where ID = :appId ";
	  String update_app_webtomobile = ""
	  		+ "update app set SPLASHSCREEN_FILENAME = :splashfilename,WEBTOMOBILE_URL = :url where NAME = :appname and USER_ID = (select ID from user where USER_NAME = :username) ";
		  // tag::createDB[]
}
// end::createDB[]
