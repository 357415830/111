/*******************************************************************************
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package com.art2app.server;

import org.eclipse.scout.rt.platform.config.AbstractStringConfigProperty;

// tag::databaseProperties[]
public final class ConfigProperties {

  private ConfigProperties() {
  }

  public static class JdbcMappingNameProperty extends AbstractStringConfigProperty {

	  @Override
	    public String getKey() {
	      return "jdbc.url";
	    }
	  }
	  
	  public static class JdbcUserNameProperty extends AbstractStringConfigProperty {
		    @Override
		    public String getKey() {
		      return "jdbc.username";
		    }
		  }
	  
	  public static class JdbcPasswordProperty extends AbstractStringConfigProperty {

		  @Override
		    public String getKey() {
		      return "jdbc.password";
		    }
		  }
	  public static class ServerRootPathProperty extends AbstractStringConfigProperty {

		  @Override
		    public String getKey() {
		      return "org.eclipse.scout.rt.server.services.common.file.RemoteFileService#rootPath";
		    }
		  }
	  public static class UrlPrefixProperty extends AbstractStringConfigProperty {

		  @Override
		    public String getKey() {
		      return "url_prefix";
		    }
		  }
	  public static class ResourceKeystoreUrlProperty extends AbstractStringConfigProperty {

		  @Override
		    public String getKey() {
		      return "resource_keystore_url";
		    }
		  }
	  public static class AppGitUrlProperty extends AbstractStringConfigProperty {

		  @Override
		    public String getKey() {
		      return "app_git_url";
		    }
		  }
	  public static class AppGitBranchProperty extends AbstractStringConfigProperty {

		  @Override
		    public String getKey() {
		      return "app_git_branch";
		    }
		  }
	  public static class StorePassProperty extends AbstractStringConfigProperty {

		  @Override
		    public String getKey() {
		      return "store_pass";
		    }
		  }
	  public static class ZipAlignPathProperty extends AbstractStringConfigProperty {

		  @Override
		    public String getKey() {
		      return "zip_align_path";
		    }
		  }
	  public static class AndroidPlatformToolsPathProperty extends AbstractStringConfigProperty {

		  @Override
		    public String getKey() {
		      return "android_Platform_Tools_Path";
		    }
		  }
	  
	  public static class AndroidHomeProperty extends AbstractStringConfigProperty {

		  @Override
		    public String getKey() {
		      return "android_home";
		    }
		  }
}
