package com.art2app.shared.create;

import java.security.BasicPermission;

public class UpdateSplashPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public UpdateSplashPermission() {
		super(UpdateSplashPermission.class.getSimpleName());
	}
}
