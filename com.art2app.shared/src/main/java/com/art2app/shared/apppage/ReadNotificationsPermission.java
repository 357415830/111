package com.art2app.shared.apppage;

import java.security.BasicPermission;

public class ReadNotificationsPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadNotificationsPermission() {
		super(ReadNotificationsPermission.class.getSimpleName());
	}
}
