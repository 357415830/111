package com.art2app.ui.html;

import java.io.IOException;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.security.ICredentialVerifier;

import com.art2app.shared.account.AccountFormData;
import com.art2app.shared.accout.IAccountService;



@Bean
public class DBCredentialVerifier implements ICredentialVerifier {

	@Override
	public int verify(String username, char[] password) throws IOException {
		AccountFormData myAccountData = BEANS.get(IAccountService.class).login(username, password);
		if (myAccountData.getAccountId()!=null) {

			return AUTH_OK;
		}
//		AccountTableRowData[] rows = myAccountData.getRows();
//		for (AccountTableRowData accountTableRowData : rows) {
//			if (accountTableRowData.getLastName().equals(username)) {
//				return AUTH_OK;
//			}
//		}
		return AUTH_FAILED;
	}

}
