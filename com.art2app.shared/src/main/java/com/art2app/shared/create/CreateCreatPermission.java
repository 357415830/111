package com.art2app.shared.create;

import java.security.BasicPermission;

public class CreateCreatPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateCreatPermission() {
		super(CreateCreatPermission.class.getSimpleName());
	}
}
