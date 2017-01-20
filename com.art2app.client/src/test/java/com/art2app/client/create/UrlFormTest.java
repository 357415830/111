package com.art2app.client.create;

import java.util.regex.Pattern;

import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.art2app.client.create.UrlForm;
import com.art2app.shared.create.IUrlService;
import com.art2app.shared.create.UrlFormData;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class UrlFormTest {

	private static final String URL = "http://www.baidu.com";
	private static final String CONTENTBOX_LABEL = "Create a new smart app...";
	private static final String LABELFIELD_LABEL = "Enter the root URL for the Web to Mobile feature";
	private static final String URLFIELD_LABEL = "URL";
	private static final String CHECKFIELD_LABEL = " ";
	private static final String FINSHBUTTON_LABEL = "Finish";
	private static final String URL_NULL = "<img src='res/warning.png'>&nbsp;Please input URL";
	private static final String URL_ERROR = "<img src='res/warning.png'>&nbsp;Please input correct URL";
	@BeanMock
	private IUrlService m_mockSvc;
	private UrlFormData answer = new UrlFormData();
	@Before
	public void setup() {
		Mockito.when(m_mockSvc.prepareCreate(Matchers.any(UrlFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.create(Matchers.any(UrlFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.load(Matchers.any(UrlFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.store(Matchers.any(UrlFormData.class))).thenReturn(answer);
	}

	@Test
	public void testContextBoxMessageByEnglish(){
		UrlForm urlForm = new UrlForm();
		String configuredLabel = urlForm.getContentBox().getConfiguredLabel();
		//System.out.println(configuredLabel);
		Assert.assertEquals(CONTENTBOX_LABEL, configuredLabel);
	}
	@Test
	public void testlableFieldMessageByEnglish(){
		UrlForm urlForm = new UrlForm();
		String configuredLabel = urlForm.getlableField().getConfiguredLabel();
		//System.out.println(configuredLabel);
		Assert.assertEquals(LABELFIELD_LABEL, configuredLabel);
	}
	
	@Test
	public void testUrlFieldDisabled(){
		UrlForm urlForm = new UrlForm();
		Assert.assertTrue(urlForm.getURLField().isEnabled());
	}
	
	@Test
	public void testUrlFieldCheck(){
		UrlForm urlForm = new UrlForm();
		Assert.assertTrue(Pattern.matches(urlForm.URL_PATTERN, URL));
	}
	
	@Test
	public void testUrlFieldMessageByEnglish(){
		UrlForm urlForm = new UrlForm();
		String configuredLabel = urlForm.getURLField().getConfiguredLabel();
		//System.out.println(configuredLabel);
		Assert.assertEquals(URLFIELD_LABEL, configuredLabel);
	}
	
	@Test
	public void testcheckFieldMessageByEnglish(){
		UrlForm urlForm = new UrlForm();
		String configuredLabel = urlForm.getcheckField().getConfiguredLabel();
		//System.out.println(configuredLabel);
		Assert.assertEquals(CHECKFIELD_LABEL, configuredLabel);
	}
	
	@Test
	public void testHtmlFieldLabelVisible(){
		UrlForm urlForm = new UrlForm();
		Assert.assertFalse(urlForm.gethtmlField().getConfiguredLabelVisible());
	}
	
	@Test
	public void testFinshButtonMessageByEnglish(){
		UrlForm urlForm = new UrlForm();
		String configuredLabel = urlForm.getFinishButton().getConfiguredLabel();
		//System.out.println(configuredLabel);
		Assert.assertEquals(FINSHBUTTON_LABEL, configuredLabel);
	}
	@Test
	public void testFinshButtonFieldDisabled(){
		UrlForm urlForm = new UrlForm();
		Assert.assertTrue(urlForm.getFinishButton().isEnabled());
	}
	@Test
	public void testNullUrlMessageByEnglish(){
		answer.getURL().setValue("");
		UrlForm urlForm = new UrlForm();
		//urlForm.startNew();
		urlForm.getFinishButton().execClickAction();
		Assert.assertEquals(URL_NULL,urlForm.gethtmlField().getValue());		
	}
	@Test
	public void testErrorUrlMessageByEnglish(){
		UrlForm urlForm = new UrlForm();
		urlForm.getURLField().setValue("google.com");
		urlForm.getFinishButton().execClickAction();
		Assert.assertEquals(URL_ERROR,urlForm.gethtmlField().getValue());		
	}
}
