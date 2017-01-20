package com.art2app.shared.apptable;

import java.security.BasicPermission;

public class ReadAppPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadAppPermission() {
		super(ReadAppPermission.class.getSimpleName());
	}
}
