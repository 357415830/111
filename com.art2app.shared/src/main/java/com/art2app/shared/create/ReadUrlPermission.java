package com.art2app.shared.create;

import java.security.BasicPermission;

public class ReadUrlPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadUrlPermission() {
		super(ReadUrlPermission.class.getSimpleName());
	}
}
