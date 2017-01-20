package com.art2app.shared.create;

import java.security.BasicPermission;

public class CreateGeneratePermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateGeneratePermission() {
		super(CreateGeneratePermission.class.getSimpleName());
	}
}
