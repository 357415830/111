package com.art2app.shared.create;

import java.security.BasicPermission;

public class UpdateUrlPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public UpdateUrlPermission() {
		super(UpdateUrlPermission.class.getSimpleName());
	}
}
