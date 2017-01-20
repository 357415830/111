package com.art2app.shared.apptable;

import java.security.BasicPermission;

public class UpdateAppPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public UpdateAppPermission() {
		super(UpdateAppPermission.class.getSimpleName());
	}
}
