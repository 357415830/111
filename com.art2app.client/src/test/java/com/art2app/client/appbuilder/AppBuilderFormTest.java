package com.art2app.client.appbuilder;

import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.art2app.shared.appbuilder.AppBuilderFormData;
import com.art2app.shared.appbuilder.IAppBuilderService;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class AppBuilderFormTest {

	@BeanMock
	private IAppBuilderService m_mockSvc;

	@Before
	public void setup() {
		AppBuilderFormData answer = new AppBuilderFormData();
		Mockito.when(m_mockSvc.prepareCreate(Matchers.any(AppBuilderFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.create(Matchers.any(AppBuilderFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.load(Matchers.any(AppBuilderFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.store(Matchers.any(AppBuilderFormData.class))).thenReturn(answer);
	}

	// TODO [Ecsoya] add test cases
}
