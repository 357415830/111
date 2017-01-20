package com.art2app.shared.apppage;

import java.security.BasicPermission;

public class ReadQRCodePermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadQRCodePermission() {
		super(ReadQRCodePermission.class.getSimpleName());
	}
}
