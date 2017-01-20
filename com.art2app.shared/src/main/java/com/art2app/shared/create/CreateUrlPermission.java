package com.art2app.shared.create;

import java.security.BasicPermission;

public class CreateUrlPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateUrlPermission() {
		super(CreateUrlPermission.class.getSimpleName());
	}
}
