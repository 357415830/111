package com.art2app.client.create;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.util.IOUtility;
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

import com.art2app.client.create.SplashForm;
import com.art2app.shared.create.ISplashService;
import com.art2app.shared.create.SplashFormData;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class SplashFormTest {

	private static final String FINSHBUTTON_LABEL = "Next";
	private static final String CONTENTBOX_LABEL = "Create a new smart app...";
	private static final String ICON_LABEL = "Splash screen:";
	private static final String HTMLFIELD_VALUE = "<img src='res/warning.png'>&nbsp;Missing image";
	private static final String FILEPATH = "src/test/resources/";
	private static final String FILENAME = "2.png";
	@BeanMock
	private ISplashService m_mockSvc;
	
	@Before
	public void setup() {
		SplashFormData answer = new SplashFormData();
		Mockito.when(m_mockSvc.prepareCreate(Matchers.any(SplashFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.create(Matchers.any(SplashFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.load(Matchers.any(SplashFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.store(Matchers.any(SplashFormData.class))).thenReturn(answer);
	}

	@Test
	public void testContextBoxMessageByEnglish(){
		SplashForm splashForm = new SplashForm();
		assertEquals(CONTENTBOX_LABEL, splashForm.getContentBox().getConfiguredLabel());
	}
	@Test
	public void testIconFieldDisabled(){
		SplashForm splashForm = new SplashForm();
		assertTrue(splashForm.getIconField().isEnabled());
	}
	@Test
	public void testIconFieldMessageByEnglish(){
		SplashForm splashForm = new SplashForm();
		String configuredLabel = splashForm.getIconField().getConfiguredLabel();
		assertEquals(ICON_LABEL, configuredLabel);
	}
	@Test
	public void testIconCheckType(){
		SplashForm splashForm = new SplashForm();
		try {
			FileInputStream inputStream = new FileInputStream(new File(FILEPATH,FILENAME));
			byte[] readBytes = IOUtility.readBytes(inputStream);
			BinaryResource binaryResource = new BinaryResource(FILENAME, readBytes);
			String contentType = binaryResource.getContentType();
			List<String> fileExtensions = splashForm.getIconField().getFileExtensions();
			assertTrue(fileExtensions.contains(contentType.substring(contentType.lastIndexOf("/")+1, contentType.length())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testHtmlFieldLabelVisible(){
		SplashForm splashForm = new SplashForm();
		Assert.assertFalse(splashForm.getHtmlField().getConfiguredLabelVisible());
	}
	@Test
	public void testNullImageMessageByEnglish(){
		SplashForm splashForm = new SplashForm();
		splashForm.getFinishButton().execClickAction();
		assertEquals(HTMLFIELD_VALUE, splashForm.getHtmlField().getValue());
	}
	@Test
	public void testFinshButtonMessageByEnglish(){
		SplashForm splashForm = new SplashForm();
		String configuredLabel = splashForm.getFinishButton().getConfiguredLabel();
		assertEquals(FINSHBUTTON_LABEL, configuredLabel);
	}
	@Test
	public void testFinshButtonFieldDisabled(){
		SplashForm splashForm = new SplashForm();
		assertTrue(splashForm.getFinishButton().isEnabled());
	}
}
