package com.art2app.client.create;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.client.ui.basic.tree.ITreeNode;
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

import com.art2app.client.apppage.AppNodePage;
import com.art2app.client.create.GenerateForm;
import com.art2app.client.work.WorkOutline;
import com.art2app.shared.create.GenerateFormData;
import com.art2app.shared.create.IGenerateService;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class GenerateFormTest {

	private static final String CANCELBUTTON_LABEL = TEXTS.get("Cancel");
	private static final String CONTENTBOX_LABEL = "Create a new smart app...";
	
	@BeanMock
	private IGenerateService m_mockSvc;
	
	@Before
	public void setup() {
		GenerateFormData answer = new GenerateFormData();
		Mockito.when(m_mockSvc.prepareCreate(Matchers.any(GenerateFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.create(Matchers.any(GenerateFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.load(Matchers.any(GenerateFormData.class))).thenReturn(answer);
		Mockito.when(m_mockSvc.store(Matchers.any(GenerateFormData.class))).thenReturn(answer);
	}
	
	@Test
	public void testContextBoxMessageByEnglish(){
		GenerateForm generateForm = new GenerateForm();
		String configuredLabel = generateForm.getContentBox().getConfiguredLabel();
		//System.out.println(configuredLabel);
		assertEquals(CONTENTBOX_LABEL, configuredLabel);
	}
	
	@Test
	public void testHtmlFieldValue(){
		GenerateForm generateForm = new GenerateForm();
		generateForm.getHtmlField().execInitField();
		assertEquals(generateForm.getHtmlField().html, generateForm.getHtmlField().getValue());
	}
	
	@Test
	public void testCancelButtonMessageByEnglish(){
		GenerateForm generateForm = new GenerateForm();
		String configuredLabel = generateForm.getCancelButton().getConfiguredLabel();
		//System.out.println(configuredLabel);
		assertEquals(CANCELBUTTON_LABEL, configuredLabel);
	}
	
	@Test
	public void testCancelButtonFieldDisabled(){
		GenerateForm generateForm = new GenerateForm();
		assertTrue(generateForm.getCancelButton().isEnabled());
	}
	
	@Test
	public void testDynamicTreeAdd(){
		WorkOutline outline = new WorkOutline();
		List<ITreeNode> childNodes = outline.getRootNode().getChildNodes();
		AppNodePage appNodePage = new AppNodePage();
		appNodePage.setName("temp");
		outline.addChildNode(outline.getRootNode(), appNodePage);
		outline.initTree();
		List<ITreeNode> childNodes2 = outline.getRootNode().getChildNodes();
		Assert.assertNotEquals(childNodes.size(), childNodes2.size());
		AppNodePage iTreeNode = (AppNodePage)childNodes2.get(childNodes2.size()-1);
		Assert.assertTrue(childNodes2.contains(appNodePage));
		Assert.assertEquals("temp", iTreeNode.getConfiguredTitle());
	}
	
	@Test
	public void testDynamicTreeUpdate(){
		WorkOutline outline = new WorkOutline();
		List<ITreeNode> childNodes = outline.getRootNode().getChildNodes();
		AppNodePage appNodePage = new AppNodePage();
		appNodePage.setName("temp");
		outline.addChildNode(outline.getRootNode(), appNodePage);
		int childNodeIndex = 1;
		appNodePage.setName("temp1");
		outline.removeChildNode(outline.getRootNode(),outline.getRootNode().getChildNode(childNodeIndex));
		outline.addChildNode(childNodeIndex, outline.getRootNode(),appNodePage);
		List<ITreeNode> childNodes2 = outline.getRootNode().getChildNodes();
		Assert.assertNotEquals(childNodes.size(), childNodes2.size());
		AppNodePage iTreeNode = (AppNodePage)childNodes2.get(childNodes2.size()-1);
		Assert.assertTrue(childNodes2.contains(appNodePage));
		Assert.assertEquals("temp1", iTreeNode.getConfiguredTitle());
	}
}
