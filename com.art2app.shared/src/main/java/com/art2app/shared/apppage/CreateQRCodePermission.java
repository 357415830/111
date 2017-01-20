package com.art2app.shared.apppage;

import java.security.BasicPermission;

public class CreateQRCodePermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateQRCodePermission() {
		super(CreateQRCodePermission.class.getSimpleName());
	}
}
