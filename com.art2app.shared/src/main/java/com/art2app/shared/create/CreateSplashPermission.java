package com.art2app.shared.create;

import java.security.BasicPermission;

public class CreateSplashPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateSplashPermission() {
		super(CreateSplashPermission.class.getSimpleName());
	}
}
