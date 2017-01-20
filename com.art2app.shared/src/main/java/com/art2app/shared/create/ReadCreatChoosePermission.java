package com.art2app.shared.create;

import java.security.BasicPermission;

public class ReadCreatChoosePermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadCreatChoosePermission() {
		super(ReadCreatChoosePermission.class.getSimpleName());
	}
}
