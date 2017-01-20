package com.art2app.shared.appbuilder;

import java.security.BasicPermission;

public class ReadAppBuilderPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadAppBuilderPermission() {
		super(ReadAppBuilderPermission.class.getSimpleName());
	}
}
