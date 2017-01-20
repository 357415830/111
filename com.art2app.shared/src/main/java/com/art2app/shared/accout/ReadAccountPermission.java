package com.art2app.shared.accout;

import java.security.BasicPermission;

public class ReadAccountPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadAccountPermission() {
		super(ReadAccountPermission.class.getSimpleName());
	}
}
