package com.art2app.client.apppage;

import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.shared.TEXTS;
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

import com.art2app.shared.apppage.INotificationsService;
import com.art2app.shared.apppage.NotificationsFormData;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class NotificationsFormTest {
	
	private static final String NAMEFIELD_LABEL = TEXTS.get("Name") + ":";
	private static final String DATEFIELD_LABEL = TEXTS.get("Date") + ":";
	private static final String IOSFIELD_LABEL = TEXTS.get("iOS") + ":";
	private static final String DOENLOADIOSBUTTON_TOOLTIPTEXT = TEXTS.get("DownloadForIOS");
	private static final String ANDROIDFIELD_LABEL = TEXTS.get("Android") + ":";
	private static final String DOWNLOADANDROIDBUTTON_TOOLTIPTEXT = TEXTS.get("DownloadForAndroid");
	@BeanMock
	private INotificationsService m_mockSvc;

	@Before
	public void setup() {
		NotificationsFormData answer = new NotificationsFormData();
		Mockito.when(m_mockSvc.prepareCreate(Matchers.any(NotificationsFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.create(Matchers.any(NotificationsFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.load(Matchers.any(NotificationsFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.store(Matchers.any(NotificationsFormData.class))).thenReturn(answer);
	}

	@Test
	public void testNameFieldLabel(){
		NotificationsForm form = new NotificationsForm();
		String configuredLabel = form.getNameField().getConfiguredLabel();
		Assert.assertEquals(NAMEFIELD_LABEL, configuredLabel);
	}
	
	@Test
	public void testNameFieldEnabled(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getNameField().isEnabled());
	}
	
	@Test
	public void testNameFieldVisible(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getNameField().isVisible());
	}
	
	@Test
	public void testDateFieldLabel(){
		NotificationsForm form = new NotificationsForm();
		String configuredLabel = form.getDateField().getConfiguredLabel();
		Assert.assertEquals(DATEFIELD_LABEL, configuredLabel);
	}
	
	@Test
	public void testDateFieldEnabled(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getDateField().isEnabled());
	}
	
	@Test
	public void testDateFieldVisible(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getDateField().isVisible());
	}

	@Test
	public void testIosFieldLabel(){
		NotificationsForm form = new NotificationsForm();
		String configuredLabel = form.getIosField().getConfiguredLabel();
		Assert.assertEquals(IOSFIELD_LABEL, configuredLabel);
	}
	
	@Test
	public void testIosFieldEnabled(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getIosField().isEnabled());
	}
	
	@Test
	public void testIosFieldVisible(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertFalse(form.getIosField().isVisible());
	}
	
	@Test
	public void testIosIconFieldLabel(){
		NotificationsForm form = new NotificationsForm();
		String configuredLabel = form.getIosIconField().getConfiguredLabel();
		Assert.assertEquals(IOSFIELD_LABEL, configuredLabel);
	}
	
	@Test
	public void testIosIconFieldEnabled(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getIosIconField().isEnabled());
	}
	
	@Test
	public void testIosIconFieldVisible(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getIosIconField().isVisible());
	}
	
	@Test
	public void testDownloadIosButtonLabelVisible(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertFalse(form.getDownloadIosButton().getConfiguredLabelVisible());
	}
	
	@Test
	public void testDownloadIosButtonEnabled(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getDownloadIosButton().isEnabled());
	}
	
	@Test
	public void testDownloadIosButtonVisible(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertFalse(form.getDownloadIosButton().isVisible());
	}
	
	@Test
	public void testDownloadIosButtonTooltipText(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertEquals(DOENLOADIOSBUTTON_TOOLTIPTEXT,form.getDownloadIosButton().getConfiguredTooltipText());
	}
	
	@Test
	public void testAndroidFieldLabel(){
		NotificationsForm form = new NotificationsForm();
		String configuredLabel = form.getAndroidField().getConfiguredLabel();
		Assert.assertEquals(ANDROIDFIELD_LABEL, configuredLabel);
	}
	
	@Test
	public void testAndroidFieldEnabled(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getAndroidField().isEnabled());
	}
	
	@Test
	public void testAndroidFieldVisible(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertFalse(form.getAndroidField().isVisible());
	}
	
	@Test
	public void testAndroidIconFieldLabel(){
		NotificationsForm form = new NotificationsForm();
		String configuredLabel = form.getAndroidIconField().getConfiguredLabel();
		Assert.assertEquals(ANDROIDFIELD_LABEL, configuredLabel);
	}
	
	@Test
	public void testAndroidIconFieldEnabled(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getAndroidIconField().isEnabled());
	}
	
	@Test
	public void testAndroidIconFieldVisible(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getAndroidIconField().isVisible());
	}
	
	@Test
	public void testDownloadAndroidButtonLabelVisible(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertFalse(form.getDownloadAndroidButton().getConfiguredLabelVisible());
	}
	
	@Test
	public void testDownloadAndroidButtonEnabled(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertTrue(form.getDownloadAndroidButton().isEnabled());
	}
	
	@Test
	public void testDownloadAndroidButtonVisible(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertFalse(form.getDownloadAndroidButton().isVisible());
	}
	
	@Test
	public void testDownloadAndroidButtonTooltipText(){
		NotificationsForm form = new NotificationsForm();
		Assert.assertEquals(DOWNLOADANDROIDBUTTON_TOOLTIPTEXT,form.getDownloadAndroidButton().getConfiguredTooltipText());
	}
}
