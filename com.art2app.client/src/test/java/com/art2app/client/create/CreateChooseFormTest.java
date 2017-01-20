package com.art2app.client.create;

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

import com.art2app.client.create.CreateChooseForm;
import com.art2app.shared.create.CreateChooseFormData;
import com.art2app.shared.create.ICreateChooseService;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class CreateChooseFormTest {
	private static final String CONTENTBOX_LABEL = "Create a new smart app...";
	private static final String CHOOSETOP_LABEL = "Select the features to include in the app.";
	private static final String SPLASHSCREEN_LABEL = "Splash screen";
	private static final String WEBTOMOBILE_LABEL = "Web to mobile";
	private static final String SETTINGS_LABEL = "Settings";
	private static final String NEXTBUTTON_LABEL = "Next";
	private static final String WARNINGHTML_VALUE = "<img src='res/warning.png'>&nbsp;At least one feature should be selected to create an app.";
	@BeanMock
	private ICreateChooseService m_mockSvc;
	private CreateChooseFormData answer = new CreateChooseFormData();
	@Before
	public void setup() {
		Mockito.when(m_mockSvc.prepareCreate(Matchers.any(CreateChooseFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.create(Matchers.any(CreateChooseFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.load(Matchers.any(CreateChooseFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.store(Matchers.any(CreateChooseFormData.class))).thenReturn(answer);
	}

	// TODO [admin] add test cases
	@Test
	public void testContentBoxVisible(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertTrue(creatChooseForm.getContentBox().isVisible());
	}
	@Test
	public void testContentBoxMessageByEnglish(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertEquals(CONTENTBOX_LABEL, creatChooseForm.getContentBox().getLabel());
	}
	@Test
	public void testChooseTopFieldVisible(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertTrue(creatChooseForm.getChooseTopField().isVisible());
	}
	@Test
	public void testChooseTopFieldMessageByEnglish(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertEquals(CHOOSETOP_LABEL, creatChooseForm.getChooseTopField().getLabel());
	}
	@Test
	public void testSplashScreenFieldVisible(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertTrue(creatChooseForm.getSplashScreenField().isVisible());
	}
	@Test
	public void testSplashScreenFieldMessageByEnglish(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertEquals(SPLASHSCREEN_LABEL, creatChooseForm.getSplashScreenField().getLabel());
	}
	@Test
	public void testSplashScreenFieldChecked(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertFalse(creatChooseForm.getSplashScreenField().getValue());
	}
	@Test
	public void testWebToMobileFieldVisible(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertTrue(creatChooseForm.getWebToMobileField().isVisible());
	}
	@Test
	public void testWebToMobileFieldMessageByEnglish(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertEquals(WEBTOMOBILE_LABEL, creatChooseForm.getWebToMobileField().getLabel());
	}
	@Test
	public void testWebToMobileFieldChecked(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertFalse(creatChooseForm.getWebToMobileField().getValue());
	}
	@Test
	public void testSettingsFieldVisible(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertTrue(creatChooseForm.getSettingsField().isVisible());
	}
	@Test
	public void testSettingsFieldMessageByEnglish(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertEquals(SETTINGS_LABEL, creatChooseForm.getSettingsField().getLabel());
	}
	@Test
	public void testSettingsFieldChecked(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertFalse(creatChooseForm.getSettingsField().getValue());
	}
	@Test
	public void testNextButtonDisable(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertTrue(creatChooseForm.getNextButton().isEnabled());
	}
	@Test
	public void testNextButtonMessageByEnglish(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		Assert.assertEquals(NEXTBUTTON_LABEL, creatChooseForm.getNextButton().getLabel());
	}
	@Test
	public void testChooseAllFalse(){
		CreateChooseForm creatChooseForm = new CreateChooseForm();
		answer.getSplashScreen().setValue(false);
		answer.getWebToMobile().setValue(false);
		answer.getSettings().setValue(false);
		//creatChooseForm.startNew();
		creatChooseForm.getNextButton().execClickAction();
		Assert.assertEquals(WARNINGHTML_VALUE, creatChooseForm.getWarningField().getValue());
	}
	
}
