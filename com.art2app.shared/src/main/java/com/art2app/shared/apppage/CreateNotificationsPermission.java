package com.art2app.shared.apppage;

import java.security.BasicPermission;

public class CreateNotificationsPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateNotificationsPermission() {
		super(CreateNotificationsPermission.class.getSimpleName());
	}
}
