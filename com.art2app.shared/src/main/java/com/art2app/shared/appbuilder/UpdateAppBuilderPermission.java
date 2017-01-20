package com.art2app.shared.appbuilder;

import java.security.BasicPermission;

public class UpdateAppBuilderPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public UpdateAppBuilderPermission() {
		super(UpdateAppBuilderPermission.class.getSimpleName());
	}
}
