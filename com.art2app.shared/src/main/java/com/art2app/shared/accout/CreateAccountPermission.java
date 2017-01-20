package com.art2app.shared.accout;

import java.security.BasicPermission;

public class CreateAccountPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateAccountPermission() {
		super(CreateAccountPermission.class.getSimpleName());
	}
}
