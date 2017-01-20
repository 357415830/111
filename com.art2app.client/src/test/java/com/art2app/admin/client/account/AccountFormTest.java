package com.art2app.admin.client.account;


import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.art2app.shared.account.AccountFormData;
import com.art2app.shared.accout.IAccountService;


@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class AccountFormTest {

	@BeanMock
	private IAccountService m_mockSvc;

	@Before
	public void setup() {
		AccountFormData answer = new AccountFormData();
		Mockito.when(m_mockSvc.prepareCreate(Matchers.any(AccountFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.create(Matchers.any(AccountFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.load(Matchers.any(AccountFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.store(Matchers.any(AccountFormData.class))).thenReturn(answer);
	}

	// TODO [moi] add test cases
}
