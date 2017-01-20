package com.art2app.shared.create;

import java.security.BasicPermission;

public class ReadGeneratePermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadGeneratePermission() {
		super(ReadGeneratePermission.class.getSimpleName());
	}
}
