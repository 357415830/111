package com.art2app.shared.create;

import java.security.BasicPermission;

public class ReadCreatPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadCreatPermission() {
		super(ReadCreatPermission.class.getSimpleName());
	}
}
