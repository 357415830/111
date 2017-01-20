package com.art2app.server.account;
import java.security.AccessController;
import java.security.Principal;
import javax.security.auth.Subject;
import org.eclipse.scout.rt.platform.context.RunWithRunContext;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import com.art2app.server.sql.SQLs;
import com.art2app.shared.account.AccountFormData;
import com.art2app.shared.account.AccountTablePageData;
import com.art2app.shared.accout.CreateAccountPermission;
import com.art2app.shared.accout.IAccountService;
import com.art2app.shared.accout.ReadAccountPermission;
import com.art2app.shared.accout.UpdateAccountPermission;

@RunWithRunContext
public class AccountService implements IAccountService {

	@Override
	public AccountFormData login(String username, char[] password) {
		AccountFormData pageData = new AccountFormData();
		SQL.selectInto(SQLs.LOGIN_SELECT, new NVPair("username", username),new NVPair("password", new String(password)), pageData);
		System.out.println("pageDataId:"+pageData.getAccountId());
		System.out.println("gageData:"+pageData);
		return pageData;

	}

	@Override
	public AccountFormData getMyAccountData() {
		AccountFormData pageData = new AccountFormData();

		String principal = null;
		Subject subject = Subject.getSubject(AccessController.getContext());
		if (subject != null && subject.getPrincipals().size() != 0) {
			Principal next = subject.getPrincipals().iterator().next();
			principal = next.getName();
		}
		
		SQL.selectInto(SQLs.USER_ID_SELECT, new NVPair("lastName", principal), pageData);

		return pageData;
	}

	@Override
	public AccountTablePageData getAccountTableData(SearchFilter filter) {
		AccountTablePageData pageData = new AccountTablePageData();

		String sql = SQLs.USER_PAGE_SELECT + SQLs.USER_PAGE_DATA_SELECT_INTO;
		SQL.selectInto(sql, new NVPair("page", pageData));

		return pageData;
	}

	@Override
	public AccountFormData prepareCreate(AccountFormData formData) {
		if (!ACCESS.check(new CreateAccountPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [moi] add business logic here.
		return formData;
	}

	@Override
	public AccountFormData create(AccountFormData formData) {
		if (!ACCESS.check(new CreateAccountPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [moi] add business logic here.
		return formData;
	}

	@Override
	public AccountFormData load(AccountFormData formData) {
		if (!ACCESS.check(new ReadAccountPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		SQL.selectInto(SQLs.USER_SELECT, formData);
		return formData;
	}

	@Override
	public AccountFormData store(AccountFormData formData) {
		if (!ACCESS.check(new UpdateAccountPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		SQL.update(SQLs.USER_UPDATE, formData); 
		return formData;
	}

}
