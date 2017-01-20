package com.art2app.shared.create;

import java.security.BasicPermission;

public class UpdateCreatPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public UpdateCreatPermission() {
		super(UpdateCreatPermission.class.getSimpleName());
	}
}
