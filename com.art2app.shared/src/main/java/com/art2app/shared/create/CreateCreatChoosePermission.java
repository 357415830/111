package com.art2app.shared.create;

import java.security.BasicPermission;

public class CreateCreatChoosePermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreateCreatChoosePermission() {
		super(CreateCreatChoosePermission.class.getSimpleName());
	}
}
