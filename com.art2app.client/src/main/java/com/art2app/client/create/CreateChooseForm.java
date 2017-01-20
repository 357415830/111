package com.art2app.client.create;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.shared.TEXTS;

import com.art2app.client.ClientSession;
import com.art2app.client.create.CreateChooseForm.MainBox.ContentBox;
import com.art2app.client.create.CreateChooseForm.MainBox.ContentBox.ChooseTopField;
import com.art2app.client.create.CreateChooseForm.MainBox.ContentBox.SettingsField;
import com.art2app.client.create.CreateChooseForm.MainBox.ContentBox.SplashScreenField;
import com.art2app.client.create.CreateChooseForm.MainBox.ContentBox.WarningBox;
import com.art2app.client.create.CreateChooseForm.MainBox.ContentBox.WebToMobileField;
import com.art2app.client.create.CreateChooseForm.MainBox.ContentBox.WarningBox.WarningField;
import com.art2app.client.create.CreateChooseForm.MainBox.UnderBox.NextButton;
import com.art2app.shared.create.CreateChooseFormData;
import com.art2app.shared.create.CreateCreatChoosePermission;
import com.art2app.shared.create.ICreateChooseService;
import com.art2app.shared.create.UpdateCreatChoosePermission;

@FormData(value = CreateChooseFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class CreateChooseForm extends AbstractForm {

	LinkedHashMap<AbstractForm, Boolean> map = new LinkedHashMap<AbstractForm, Boolean>();

	public LinkedHashMap<AbstractForm, Boolean> getMap() {
		return map;
	}

	public void setMap(LinkedHashMap<AbstractForm, Boolean> map) {
		this.map = map;
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	@Override
	protected void execInitForm() {
		// TODO Auto-generated method stub
		String data = (String) ClientSession.get().getData("Edit");
		if ("Edit".equals(data)) {
			 if (ClientSession.get().getData("splash_filename") != null) {
				getSplashScreenField().setChecked(true);
			}
			if (ClientSession.get().getData("URL") != null) {
				getWebToMobileField().setChecked(true);
			}
			if ((int) ClientSession.get().getData("setting") != 0) {
				getSettingsField().setChecked(true);
			}
		}
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public ContentBox getContentBox() {
		return getFieldByClass(ContentBox.class);
	}

	public SplashScreenField getSplashScreenField() {
		return getFieldByClass(SplashScreenField.class);
	}

	public WebToMobileField getWebToMobileField() {
		return getFieldByClass(WebToMobileField.class);
	}

	public SettingsField getSettingsField() {
		return getFieldByClass(SettingsField.class);
	}

	public ChooseTopField getChooseTopField() {
		return getFieldByClass(ChooseTopField.class);
	}

	public WarningBox getWarningBox() {
		return getFieldByClass(WarningBox.class);
	}

	public WarningField getWarningField() {
		return getFieldByClass(WarningField.class);
	}

	public NextButton getNextButton() {
		return getFieldByClass(NextButton.class);
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

			@Order(0)
			public class ChooseTopField extends AbstractLabelField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("SelectTheFeaturesToIncludeInTheApp");
				}

				@Override
				public String getLabelBackgroundColor() {
					return super.getLabelBackgroundColor();
				}

				@Override
				protected double getConfiguredGridWeightX() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public int getLabelWidthInPixel() {
					return 250;
				}
			}

			@Order(1000)
			public class SplashScreenField extends AbstractBooleanField {
				@Override
				public int getLabelPosition() {
					// TODO Auto-generated method stub
					return 20;
				}

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("SplashScreen");
				}
			}

			@Order(2000)
			public class WebToMobileField extends AbstractBooleanField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("WebToMobile");
				}

				@Override
				public int getLabelPosition() {
					// TODO Auto-generated method stub
					return 20;
				}
			}

			@Order(3000)
			public class SettingsField extends AbstractBooleanField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Settings");
				}

				@Override
				public int getLabelPosition() {
					// TODO Auto-generated method stub
					return 20;
				}
			}

			@Order(3125)
			public class WarningBox extends AbstractGroupBox {
				@Override
				protected int getConfiguredHeightInPixel() {
					// TODO Auto-generated method stub
					return 100;
				}

				@Order(1000)
				public class WarningField extends AbstractHtmlField {
					@Override
					public int getLabelPosition() {
						// TODO Auto-generated method stub
						return 20;
					}
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
					// TODO Auto-generated method stub
					return 130;
				}

				@Override
				protected int getConfiguredHeightInPixel() {
					return 30;
				}

				@Override
				protected void execClickAction() {
					doClose();
					new UrlForm().cleanSession();
					getDesktop().getOutline().selectNode(null);
				}
			}

			@Order(2000)
			public class NextButton extends AbstractButton {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Next");
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
					// TODO Auto-generated method stub
					return 130;
				}

				@Override
				protected int getConfiguredHeightInPixel() {
					return 30;
				}

				@Override
				protected void execClickAction() {
					if (getSplashScreenField().isChecked() || getWebToMobileField().isChecked()
							|| getSettingsField().isChecked()) {
						doClose();
						ClientSession.get().setData("setting", getSettingsField().isChecked() == true ? 1 : 0);
						map.put(new SplashForm(), getSplashScreenField().isChecked());
						map.put(new UrlForm(), getWebToMobileField().isChecked());
						map.put(null, false);
						map.put(new GenerateForm(), true);
						Iterator<Entry<AbstractForm, Boolean>> entries = map.entrySet().iterator();
						while (entries.hasNext()) {
							Entry<AbstractForm, Boolean> entry = entries.next();
							while (!entry.getValue()) {
								entry = entries.next();
							}
							if (entry.getValue() && !entry.getKey().getClass().isInstance(new GenerateForm())) {
								ClientSession.get().setData("map", map);
								entry.getKey().start();
								break;
							} else {
								ClientSession.get().setData("ifReload", "ifReload");
								AppVersion appVersion = new AppVersion();
								appVersion.setAppName((String) ClientSession.get().getData("appname"));
								appVersion.setIcon((BinaryResource) ClientSession.get().getData("icon"));
								appVersion.setIcon_Filename((String) ClientSession.get().getData("icon_filename"));
								appVersion.setSplash_filename(null);
								appVersion.setSplashScreen(null);
								appVersion.setWebToMobileURL(null);
								appVersion.setSetting((int) ClientSession.get().getData("setting"));
								new UrlForm().prepareToGenerateApp(appVersion);
								new UrlForm().AddDynamincTree();
								break;
							}
						}
					} else {
						String WarningHtml = "<img src='res/warning.png'>&nbsp;"
								+ TEXTS.get("AtLeastOneFeatureShouldBeSelectedToCreateAnApp");
						getWarningField().setValue(WarningHtml);
					}
				}
			}
		}
	}

	/*public void service() {
		String data = (String) ClientSession.get().getData("Edit");
		String username = ClientSession.get().getUserId();
		String appName = (String) ClientSession.get().getData("appname");
		BinaryResource icon = (BinaryResource) ClientSession.get().getData("icon");
		String icon_filename = (String) ClientSession.get().getData("icon_filename");
		BinaryResource splashScreen = (BinaryResource) ClientSession.get().getData("splash");
		String splash_filename = (String) ClientSession.get().getData("splash_filename");
		String webToMobileURL = (String) ClientSession.get().getData("URL");
		int setting = (int) ClientSession.get().getData("setting");

		if ("Edit".equals(data)) {
			splash_filename = null;
			splashScreen = null;
			webToMobileURL = null;
			ICreatService iCreatService = BEANS.get(ICreatService.class);
			iCreatService.updateAppInfo(appName, (int) ClientSession.get().getData("appid"));
			iCreatService.insertAppVersion(username, appName, icon, icon_filename, splashScreen, splash_filename,
					webToMobileURL, setting);
			// new UrlForm().DeleteSession();

		} else {
			ICreatService iCreatService = BEANS.get(ICreatService.class);
			iCreatService.insertAppInfo(appName, username);
			iCreatService.insertAppVersion(username, appName, icon, icon_filename, splashScreen, splash_filename,
					webToMobileURL, setting);
			// new UrlForm().DeleteSession();
		}
	}*/

	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			ICreateChooseService service = BEANS.get(ICreateChooseService.class);
			CreateChooseFormData formData = new CreateChooseFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateCreatChoosePermission());
		}

		@Override
		protected void execStore() {
			ICreateChooseService service = BEANS.get(ICreateChooseService.class);
			CreateChooseFormData formData = new CreateChooseFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			ICreateChooseService service = BEANS.get(ICreateChooseService.class);
			CreateChooseFormData formData = new CreateChooseFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);

			setEnabledPermission(new CreateCreatChoosePermission());
		}

		@Override
		protected void execStore() {
			ICreateChooseService service = BEANS.get(ICreateChooseService.class);
			CreateChooseFormData formData = new CreateChooseFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}
}
