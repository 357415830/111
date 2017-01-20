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
package com.art2app.server.sql;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.server.jdbc.mysql.AbstractMySqlSqlService;
import org.eclipse.scout.rt.server.jdbc.mysql.MySqlSqlStyle;
import org.eclipse.scout.rt.server.jdbc.style.ISqlStyle;

import com.art2app.server.ConfigProperties;

@Order(1950)
// tag::mySqlService[]
public class MySqlSqlService extends AbstractMySqlSqlService {
	@Override
	public String getUsername() {
		// TODO sb find out how to avoid overloading this method
		return CONFIG.getPropertyValue(ConfigProperties.JdbcUserNameProperty.class);
	}

	@Override
	public String getPassword() {
		// TODO sb find out how to avoid overloading this method
		return CONFIG.getPropertyValue(ConfigProperties.JdbcPasswordProperty.class);
	}


	@Override
	public ISqlStyle getSqlStyle() {
		// TODO sb find out how to avoid overloading this method
		return new MySqlSqlStyle();
	}
	
	@Override
	protected String getConfiguredJdbcMappingName() {
		String jdbcMappingName = CONFIG.getPropertyValue(ConfigProperties.JdbcMappingNameProperty.class);
		return jdbcMappingName;// + ";create=true";
	}
	// end::mySqlService[]

}
// end::mySqlService[]
