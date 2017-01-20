package com.art2app.shared.apppage;

import java.security.BasicPermission;

public class UpdateNotificationsPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public UpdateNotificationsPermission() {
		super(UpdateNotificationsPermission.class.getSimpleName());
	}
}
