package com.art2app.client.create;

import java.util.concurrent.TimeUnit;

import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.job.ModelJobs;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.job.Jobs;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;
import org.eclipse.scout.rt.shared.TEXTS;

import com.art2app.client.ClientSession;
import com.art2app.client.apppage.AppNodePage;
import com.art2app.client.apptable.AppTablePage;
import com.art2app.client.create.GenerateForm.MainBox.ContentBox;
import com.art2app.client.create.GenerateForm.MainBox.ContentBox.CancelButton;
import com.art2app.client.create.GenerateForm.MainBox.ContentBox.HtmlField;
import com.art2app.shared.create.CreateGeneratePermission;
import com.art2app.shared.create.GenerateFormData;
import com.art2app.shared.create.IGenerateService;
import com.art2app.shared.create.UpdateGeneratePermission;

@FormData(value = GenerateFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class GenerateForm extends AbstractForm {

	private String appname;
	private String iconpath;
	private String User;
	private String Url;
	private String Splashfilename;
	public String getIconpath() {
		return iconpath;
	}
	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}
	@Override
	  protected int getConfiguredDisplayHint() { 
	    return DISPLAY_HINT_DIALOG;
	  }
	@Override
	protected void execInitForm() {
		IGenerateService service = BEANS.get(IGenerateService.class);
		GenerateFormData formData = new GenerateFormData();
		exportFormData(formData);
		//service.update_app((String)ClientSession.get().getData("appname"), ClientSession.get().getUserId(), (String)ClientSession.get().getData("URL"), (String)ClientSession.get().getData("splash"), formData);
		 ClientSession.get().setData("map", null); 
         ClientSession.get().setData("splash", null); 
         ClientSession.get().setData("URL", null); 
         String data = (String) ClientSession.get().getData("Edit");
		ModelJobs.schedule(new IRunnable() {

	          @Override
	          public void run() throws Exception {
	        	  doClose();
	        	  if("Edit".equals(data)){
	        		  AppTablePage selectedNode = (AppTablePage) getDesktop().getOutline().getSelectedNode();
						AppNodePage appNodePage = new AppNodePage();
						appNodePage.setName((String) ClientSession.get().getData("appname"));
						int childNodeIndex = getDesktop().getOutline().getSelectedNode().getParentNode().getChildNodeIndex();
						System.out.println(childNodeIndex);
						getDesktop().getOutline().removeChildNode(getDesktop().getOutline().getRootNode(), selectedNode.getParentNode());
						getDesktop().getOutline().addChildNode(childNodeIndex,getDesktop().getOutline().getRootNode(), appNodePage);
						ClientSession.get().setData("Edit",null);
	        	  }
	        	  else{
	        		  
	        		  AppNodePage appnodepage = new AppNodePage();
	        		  appnodepage.setName((String)ClientSession.get().getData("appname"));
	        		  getDesktop().getOutline().addChildNode(getDesktop().getOutline().getRootNode(), appnodepage);
	        		  getDesktop().getOutline().initTree();
	        		  getDesktop().getOutline().selectNode(appnodepage);
	        	  }
	        	 }
	        }, ModelJobs.newInput(ClientRunContexts.copyCurrent())
	            .withExecutionTrigger(Jobs.newExecutionTrigger()
	                .withStartIn(3, TimeUnit.SECONDS)));
	}
		@Override
		protected String getConfiguredDisplayViewId() {
			
			return VIEW_ID_CENTER;
		}
		
	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public HtmlField getHtmlField(){
		return getFieldByClass(HtmlField.class);
	}
	public ContentBox getContentBox(){
		return getFieldByClass(ContentBox.class);
	}
	public void startNew() {
		startInternal(new NewHandler());
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}
	
	public CancelButton getCancelButton(){
		return getFieldByClass(CancelButton.class);
	}
	@FormData
	public String getAppname() {
		return appname;
	}
	@FormData
	public void setAppname(String appname) {
		this.appname = appname;
	}
	@FormData
	public String getUser() {
		return User;
	}
	@FormData
	public void setUser(String user) {
		User = user;
	}
	@FormData
	public String getUrl() {
		return Url;
	}
	@FormData
	public void setUrl(String url) {
		Url = url;
	}
	@FormData
	public String getSplashfilename() {
		return Splashfilename;
	}
	@FormData
	public void setSplashfilename(String splashfilename) {
		Splashfilename = splashfilename;
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Override
		protected boolean getConfiguredGridUseUiHeight() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		protected boolean getConfiguredGridUseUiWidth() {
			// TODO Auto-generated method stub
			return true;
		}
		
		@Order(1000)
		public class ContentBox extends AbstractGroupBox{
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("CreatANewSmartApp");
			}
			@Override
			protected int getConfiguredGridColumnCount() {
				return 1;
			}
			@Override
			protected int getConfiguredWidthInPixel() {
				return 700;
			}
			@Override
			protected int getConfiguredHeightInPixel() {
				return 400;
			}
			
			@Order(1000)
			public class HtmlField extends AbstractHtmlField{
				
				String html ="<div id=\"Loadbox\" style=\"width:100px; height:100px;margin-left: 155px;margin-top: 80px;\"></div>"
						+ "<script>"
						+ "$(function(){"
						+ "$('#Loadbox').shCircleLoader();var i = 0;setInterval(function() {$('#Loadbox').shCircleLoader('progress', '"+TEXTS.get("Generate")+"...');}, 100);"
						+ "})"
						+ "</script>";
				@Override
				protected void execInitField() {
					setValue(html);
				}
			
				@Override
				protected int getConfiguredGridH() {
					// TODO Auto-generated method stub
					return 3;
				}
			}
			
			@Order(2000)
			public class CancelButton extends AbstractOkButton{
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Cancel");
				}
				@Override
				protected boolean getConfiguredProcessButton() {
					return false;
				}
				@Override
				protected int getConfiguredWidthInPixel() {
					// TODO Auto-generated method stub
					return 130;
				}
				@Override
				protected int getConfiguredHeightInPixel() {
					return 30;
				}
				
				@Override
				protected int getConfiguredHorizontalAlignment() {
					return 0;
				}
				@Override
				protected void execClickAction() {
					//new AppTablePage();
					//new EmptyForm().startNew();
				}
			}
		}
	}

	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IGenerateService service = BEANS.get(IGenerateService.class);
			GenerateFormData formData = new GenerateFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateGeneratePermission());
		}

		@Override
		protected void execStore() {
			IGenerateService service = BEANS.get(IGenerateService.class);
			GenerateFormData formData = new GenerateFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IGenerateService service = BEANS.get(IGenerateService.class);
			GenerateFormData formData = new GenerateFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);

			setEnabledPermission(new CreateGeneratePermission());
		}

		@Override
		protected void execStore() {
			IGenerateService service = BEANS.get(IGenerateService.class);
			GenerateFormData formData = new GenerateFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}
}
