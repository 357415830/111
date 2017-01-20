package com.art2app.shared.accout;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import com.art2app.shared.account.AccountFormData;
import com.art2app.shared.account.AccountTablePageData;
@TunnelToServer
public interface IAccountService extends IService {

	AccountTablePageData getAccountTableData(SearchFilter filter);

	AccountFormData prepareCreate(AccountFormData formData);

	AccountFormData create(AccountFormData formData);

	AccountFormData load(AccountFormData formData);

	AccountFormData store(AccountFormData formData);
	
	AccountFormData getMyAccountData();

	AccountFormData login(String username, char[] password);

}
