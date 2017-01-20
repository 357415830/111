package com.art2app.shared.apptable;

import java.security.BasicPermission;

public class CreateAppPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateAppPermission() {
		super(CreateAppPermission.class.getSimpleName());
	}
}
