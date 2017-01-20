package com.art2app.client.create;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.art2app.client.create.CreateForm;
import com.art2app.shared.create.CreateFormData;
import com.art2app.shared.create.ICreateService;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class CreateFormTest {

    private static final String NAME_VALUE = "testData";
	private static final String FINSHBUTTON_LABEL = "Next";
	private static final String ICON_LABEL = "Icon:";
	private static final String NAME_LABEL = TEXTS.get("Name") + ":";
	private static final String CONTENTBOX_LABEL = "Create a new smart app...";
	private static final String FILEPATH = "src/test/resources/";
	private static final String FILENAME = "test.png";
	@BeanMock
    private ICreateService m_mockSvc;
    
	@Before
	public void setup(){
		CreateFormData formData = new CreateFormData();
		formData.getName().setValue(NAME_VALUE);
		
		Mockito.when(m_mockSvc.load(Matchers.any(CreateFormData.class))).thenReturn(formData);
	}
	
	@Test
	public void testContextBoxFieldLabel(){
		CreateForm creatForm = new CreateForm();
		String configuredLabel = creatForm.getContentBox().getConfiguredLabel();
		//System.out.println(configuredLabel);
		assertEquals(CONTENTBOX_LABEL, configuredLabel);
	}
	@Test
	public void testNameFieldDisabled(){
		CreateForm creatForm = new CreateForm();
		assertTrue(creatForm.getNameField().isEnabled());
	}
	@Test
	public void testNameValue(){
		CreateForm creatForm = new CreateForm();
		creatForm.start();
		//System.out.println(creatForm.getNameField().getValue());
		assertEquals(NAME_VALUE, creatForm.getNameField().getValue());
	}
	@Test
	public void testNameFieldMessageByEnglish(){
		CreateForm creatForm = new CreateForm();
		String configuredLabel = creatForm.getNameField().getConfiguredLabel();
		//System.out.println(configuredLabel);
		assertEquals(NAME_LABEL, configuredLabel);
	}
	@Test
	public void testIconFieldDisabled(){
		CreateForm creatForm = new CreateForm();
		assertTrue(creatForm.getIconField().isEnabled());
	}
	@Test
	public void testIconFieldMessageByEnglish(){
		CreateForm creatForm = new CreateForm();
		String configuredLabel = creatForm.getIconField().getConfiguredLabel();
		//System.out.println(configuredLabel);
		assertEquals(ICON_LABEL, configuredLabel);
	}
	@Test
	public void testiconcheckType(){
		CreateForm creatForm = new CreateForm();
		try {
			FileInputStream inputStream = new FileInputStream(new File(FILEPATH,FILENAME));
			byte[] readBytes = IOUtility.readBytes(inputStream);
			BinaryResource binaryResource = new BinaryResource(FILENAME, readBytes);
			String contentType = binaryResource.getContentType();
			//System.out.println(contentType.substring(contentType.lastIndexOf("/")+1, contentType.length()));
			List<String> fileExtensions = creatForm.getIconField().getFileExtensions();
			assertTrue(fileExtensions.contains(contentType.substring(contentType.lastIndexOf("/")+1, contentType.length())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testFinshButtonMessageByEnglish(){
		CreateForm creatForm = new CreateForm();
		String configuredLabel = creatForm.getFinshButton().getConfiguredLabel();
		//System.out.println(configuredLabel);
		assertEquals(FINSHBUTTON_LABEL, configuredLabel);
	}
	@Test
	public void testFinshButtonFieldDisabled(){
		CreateForm creatForm = new CreateForm();
		assertTrue(creatForm.getFinshButton().isEnabled());
	}
}
