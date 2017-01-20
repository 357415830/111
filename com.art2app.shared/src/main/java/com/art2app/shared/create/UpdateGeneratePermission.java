package com.art2app.shared.create;

import java.security.BasicPermission;

public class UpdateGeneratePermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public UpdateGeneratePermission() {
		super(UpdateGeneratePermission.class.getSimpleName());
	}
}
