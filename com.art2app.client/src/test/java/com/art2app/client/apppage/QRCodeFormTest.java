package com.art2app.client.apppage;

import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.art2app.shared.apppage.IQRCodeService;
import com.art2app.shared.apppage.QRCodeFormData;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class QRCodeFormTest {

	@BeanMock
	private IQRCodeService m_mockSvc;

	@Before
	public void setup() {
		QRCodeFormData answer = new QRCodeFormData();
		Mockito.when(m_mockSvc.prepareCreate(Matchers.any(QRCodeFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.create(Matchers.any(QRCodeFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.load(Matchers.any(QRCodeFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.store(Matchers.any(QRCodeFormData.class))).thenReturn(answer);
	}

	// TODO [admin] add test cases
}
