package com.art2app.shared.create;

import java.security.BasicPermission;

public class ReadSplashPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadSplashPermission() {
		super(ReadSplashPermission.class.getSimpleName());
	}
}
