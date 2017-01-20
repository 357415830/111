package com.art2app.shared.appbuilder;

import java.security.BasicPermission;

public class CreateAppBuilderPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateAppBuilderPermission() {
		super(CreateAppBuilderPermission.class.getSimpleName());
	}
}
