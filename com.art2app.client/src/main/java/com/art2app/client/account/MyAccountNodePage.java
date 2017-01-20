package com.art2app.client.account;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.TEXTS;

import com.art2app.shared.account.AccountFormData;
import com.art2app.shared.accout.IAccountService;


public class MyAccountNodePage extends AbstractPageWithNodes {
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("MyAccount");
	}
	@Override
	protected boolean getConfiguredTableVisible() {
		return false;
	}

	@Override
	protected Class<? extends IForm> getConfiguredDetailForm() {
		return MyAccountForm.class;
	}

	@Override
	public MyAccountForm getDetailForm() {
		return (MyAccountForm) super.getDetailForm();
	}

	@Override
	protected void execInitDetailForm() {
		if (getDetailForm() != null) {
			IAccountService service = BEANS.get(IAccountService.class);
			AccountFormData myAccount = service.getMyAccountData();
			if (myAccount != null) {
				getDetailForm().setAccountId(myAccount.getAccountId());
				getDetailForm().startModify();
			} else {
				// TODO sb
			}
		}
	}
}
