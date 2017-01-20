package com.art2app.client.create;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBoxes;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.shared.TEXTS;

import com.art2app.client.ClientSession;
import com.art2app.client.apppage.AppNodePage;
import com.art2app.client.create.UrlForm.MainBox.ContentBox;
import com.art2app.client.create.UrlForm.MainBox.UnderBox;
import com.art2app.client.create.UrlForm.MainBox.ContentBox.URLField;
import com.art2app.client.create.UrlForm.MainBox.ContentBox.checkField;
import com.art2app.client.create.UrlForm.MainBox.ContentBox.htmlField;
import com.art2app.client.create.UrlForm.MainBox.ContentBox.lableField;
import com.art2app.client.create.UrlForm.MainBox.UnderBox.FinishButton;
import com.art2app.shared.create.CreateUrlPermission;
import com.art2app.shared.create.ICreateService;
import com.art2app.shared.create.IGenerateService;
import com.art2app.shared.create.IUrlService;
import com.art2app.shared.create.UpdateUrlPermission;
import com.art2app.shared.create.UrlFormData;
import com.art2app.shared.exception.NameDuplicateException;
import com.art2app.shared.exception.URLException;

@FormData(value = UrlFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class UrlForm extends AbstractForm {

	LinkedHashMap<AbstractForm, Boolean> map;
	
	@Override
	protected void execInitForm() {
		String data = (String) ClientSession.get().getData("Edit");
		if ("Edit".equals(data)) {
			getURLField().setValue((String) ClientSession.get().getData("URL"));
		}
	}

	public String URL_PATTERN = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
			+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
			+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
			+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
			+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
			+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
			+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
			+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
	public String html = "<img src='res/warning.png'>&nbsp;";
	public String htmlok = "<img src='res/ok.png'>&nbsp;";
	private boolean GenerateForm;

	// private static final String EMAIL_PATTERN
	// ="(((http|ftp|https|)://)|www)(([a-zA-Z0-9\._-]+\.[a-zA-Z]{2,6})|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\&%_\./-~-]*)?";
	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public checkField getcheckField() {
		return getFieldByClass(checkField.class);
	}

	public lableField getlableField() {
		return getFieldByClass(lableField.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public ContentBox getContentBox() {
		return getFieldByClass(ContentBox.class);
	}

	public UnderBox getUnderBox() {
		return getFieldByClass(UnderBox.class);
	}

	public URLField getURLField() {
		return getFieldByClass(URLField.class);
	}

	public htmlField gethtmlField() {
		return getFieldByClass(htmlField.class);
	}

	public FinishButton getFinishButton() {
		return getFieldByClass(FinishButton.class);
	}

	@Override
	protected int getConfiguredDisplayHint() {
		return super.getConfiguredDisplayHint();
	}

	@Override
	protected String getConfiguredDisplayViewId() {
		return VIEW_ID_CENTER;
	}

	public boolean isGenerateForm() {
		return GenerateForm;
	}

	public void setGenerateForm(boolean generateForm) {
		GenerateForm = generateForm;
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Order(1000)
		public class ContentBox extends AbstractGroupBox {
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
			@FormData(sdkCommand = FormData.SdkCommand.IGNORE)
			public class lableField extends AbstractLabelField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("EnterTheRootURLForTheWebToMobileFeature");
				}

				@Override
				protected int getConfiguredLabelWidthInPixel() {
					return 300;
				}
			}

			@Order(2000)
			public class URLField extends AbstractStringField {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("URL");
				}

				@Override
				protected int getConfiguredLabelWidthInPixel() {
					return 100;
				}

				@Override
				public String getLabelBackgroundColor() {
					return super.getLabelBackgroundColor();
				}

				@Override
				protected double getConfiguredGridWeightX() {
					return 0;
				}

				@Override
				protected int getConfiguredWidthInPixel() {
					return 450;
				}

				@Override
				protected String execValidateValue(String rawValue) {
					if (rawValue != null && !Pattern.matches(URL_PATTERN, rawValue)) {
						gethtmlField().setValue(html + TEXTS.get("PleaseInputCorrectURL"));
					} else if (rawValue == null) {
						getcheckField().setLabel("");
					} 
					return rawValue; 
				}
			}

			@Order(3000)
			@FormData(sdkCommand = FormData.SdkCommand.IGNORE)
			public class checkField extends AbstractLabelField {

				@Override
				protected int getConfiguredGridH() {
					return 2;
				}

				@Override
				protected String getConfiguredLabel() {
					return " ";
				}

				@Override
				protected int getConfiguredLabelWidthInPixel() {
					return 400;
				}
			}

			@Order(4000)
			public class htmlField extends AbstractHtmlField {
				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Override
				protected int getConfiguredHorizontalAlignment() {
					return -1;
				}
			}

		}

		@Order(2000)
		public class UnderBox extends AbstractGroupBox {
			@Override
			protected int getConfiguredWidthInPixel() {
				return 700;
			}

			@Order(1000)
			public class CancelButton extends AbstractButton {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Cancel");
				}

				@Override
				protected boolean getConfiguredProcessButton() {
					return false;
				}

				@Override
				protected int getConfiguredHorizontalAlignment() {
					return -1;
				}

				@Override
				protected int getConfiguredWidthInPixel() {
					return 130;
				}

				@Override
				protected int getConfiguredHeightInPixel() {
					return 30;
				}

				@Override
				protected void execClickAction() {
					doClose();
					cleanSession();
					getDesktop().getOutline().selectNode(null);
				}
			}

			@Order(2000)
			public class FinishButton extends AbstractButton {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Finish");
				}

				@Override
				protected boolean getConfiguredProcessButton() {
					return false;
				}

				@Override
				protected int getConfiguredHorizontalAlignment() {
					return 1;
				}

				@Override
				protected int getConfiguredWidthInPixel() {
					return 130;
				}

				@Override
				protected int getConfiguredHeightInPixel() {
					return 30;
				}

				@Override
				protected void execClickAction() {
					if (getURLField().getValue() == null) {
						gethtmlField().setValue(html + TEXTS.get("PleaseInputURL"));
					} else if (!Pattern.matches(URL_PATTERN, getURLField().getValue())) {
						gethtmlField().setValue(html + TEXTS.get("PleaseInputCorrectURL"));
					} else {
						doClose();
						ClientSession.get().setData("URL", getURLField().getValue());
						map = (LinkedHashMap<AbstractForm, Boolean>) ClientSession
								.get().getData("map");
						Iterator<Entry<AbstractForm, Boolean>> entries = map.entrySet().iterator();
						while (entries.hasNext()) {
							Entry<AbstractForm, Boolean> entry = entries.next();
							if (entry.getKey().getClass().isInstance(new UrlForm())) {
								entry = entries.next();
								while (!entry.getValue()) {
									entry = entries.next();
								}
								if(!entry.getKey().getClass().isInstance(new GenerateForm())){
									entry.getKey().start();
									break;
								}
								else{
									ClientSession.get().setData("ifReload","ifReload");
									AppVersion appVersion = new AppVersion();
									appVersion.setAppName((String) ClientSession.get().getData("appname"));
									appVersion.setIcon((BinaryResource) ClientSession.get().getData("icon"));
									appVersion.setIcon_Filename((String) ClientSession.get().getData("icon_filename"));
									if("splashcheck".equals(ClientSession.get().getData("splashcheck"))){
										appVersion.setSplash_filename((String) ClientSession.get().getData("splash_filename"));
										appVersion.setSplashScreen((BinaryResource) ClientSession.get().getData("splash"));
									}
									else{
										appVersion.setSplash_filename(null);
										appVersion.setSplashScreen(null);
									}
									appVersion.setWebToMobileURL((String) ClientSession.get().getData("URL"));
									appVersion.setSetting((int) ClientSession.get().getData("setting"));
									prepareToGenerateApp(appVersion);
									AddDynamincTree();
									break;
								}
							}
						}
					}
				}
			}
		}

	}

	public void prepareToGenerateApp(AppVersion appVersion){
		String appId = null;
		String data = (String) ClientSession.get().getData("Edit");
		String username = ClientSession.get().getUserId();
		ICreateService iCreatService = BEANS.get(ICreateService.class);
		if("Edit".equals(data)){
			appId = (String)ClientSession.get().getData("appId");
			iCreatService.updateApp(appVersion.getAppName(),appId);
		}else{
			try {
				iCreatService.insertApp(appVersion.getAppName(),username);
			} catch (NameDuplicateException e) {
				MessageBoxes.create().withHeader("Error").withBody("The appName already exists")
				.withCancelButtonText("Close").show();
				cleanSession();
				e.printStackTrace();
			}
			appId = BEANS.get(IGenerateService.class).getAppIdByAppName(appVersion.getAppName());
			ClientSession.get().setData("appId", appId);
		}
		try {
			iCreatService.insertAppVersion(appId,appVersion.getAppName(),appVersion.getIcon()
					,appVersion.getIcon_Filename(),appVersion.getSplashScreen()
					,appVersion.getSplash_Filename(),appVersion.getWebToMobileURL(),appVersion.getSetting());
		} catch (URLException e) {
			MessageBoxes.create().withHeader("Error").withBody(TEXTS.get("PleaseInputCorrectURL"))
			.withCancelButtonText("Close").show();
			cleanSession();
			e.printStackTrace();
		}
	}
	
	public void cleanSession(){
		ClientSession.get().setData("icon",null);
		ClientSession.get().setData("splash",null);
		ClientSession.get().setData("URL",null);
		ClientSession.get().setData("setting",null);
		ClientSession.get().setData("icon_filename", null);
		ClientSession.get().setData("splash_filename", null);
		ClientSession.get().setData("map", null);
		ClientSession.get().setData("splashcheck",null);
		ClientSession.get().setData("Edit", null);
		ClientSession.get().setData("appId", null);
	}
	public void AddDynamincTree() {
		AppNodePage appNodePage = new AppNodePage();
		appNodePage.setName((String) ClientSession.get().getData("appname"));
		String data = (String) ClientSession.get().getData("Edit");
		if ("Edit".equals(data)) {
			int childNodeIndex = getDesktop().getOutline().getSelectedNode().getChildNodeIndex();
			getDesktop().getOutline().removeChildNode(getDesktop().getOutline().getRootNode(),
					getDesktop().getOutline().getSelectedNode());
			getDesktop().getOutline().addChildNode(childNodeIndex, getDesktop().getOutline().getRootNode(),
					appNodePage);
		} else {
			getDesktop().getOutline().addChildNode(getDesktop().getOutline().getRootNode(), appNodePage);
		}
		getDesktop().getOutline().initTree();
		getDesktop().getOutline().selectNode(appNodePage);
	}

	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IUrlService service = BEANS.get(IUrlService.class);
			UrlFormData formData = new UrlFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateUrlPermission());
		}

		@Override
		protected void execStore() {
			IUrlService service = BEANS.get(IUrlService.class);
			UrlFormData formData = new UrlFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IUrlService service = BEANS.get(IUrlService.class);
			UrlFormData formData = new UrlFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);

			setEnabledPermission(new CreateUrlPermission());
		}

		@Override
		protected void execStore() {
			IUrlService service = BEANS.get(IUrlService.class);
			UrlFormData formData = new UrlFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}

}
