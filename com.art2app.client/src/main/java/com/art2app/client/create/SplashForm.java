package com.art2app.client.create;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.TEXTS;

import com.art2app.client.ClientSession;
import com.art2app.client.create.SplashForm.MainBox.ContentBox;
import com.art2app.client.create.SplashForm.MainBox.UnderBox;
import com.art2app.client.create.SplashForm.MainBox.ContentBox.HtmlField;
import com.art2app.client.create.SplashForm.MainBox.ContentBox.IconField;
import com.art2app.client.create.SplashForm.MainBox.ContentBox.ImageField;
import com.art2app.client.create.SplashForm.MainBox.UnderBox.FinishButton;
import com.art2app.shared.create.CreateSplashPermission;
import com.art2app.shared.create.ISplashService;
import com.art2app.shared.create.SplashFormData;
import com.art2app.shared.create.UpdateSplashPermission;

@FormData(value = SplashFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class SplashForm extends AbstractForm {

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public IconField getIconField() {
		return getFieldByClass(IconField.class);
	}

	public ContentBox getContentBox() {
		return getFieldByClass(ContentBox.class);
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
		// TODO Auto-generated method stub
		return VIEW_ID_CENTER;
	}

	public ImageField getImageField() {
		return getFieldByClass(ImageField.class);
	}

	public UnderBox getUnderBox() {
		return getFieldByClass(UnderBox.class);
	}

	public HtmlField getHtmlField() {
		return getFieldByClass(HtmlField.class);
	}

	@Override
	protected void execInitForm() {
		String data = (String) ClientSession.get().getData("Edit");
		if ("Edit".equals(data)) {
			BinaryResource binaryResource = (BinaryResource) ClientSession.get().getData("splash");
			getIconField().setValue(binaryResource);
			getImageField().setImage(getIconField().getValue().getContent());
			getImageField().setImageId(getIconField().getValue().getFilename());
		}
		LinkedHashMap<AbstractForm, Boolean> map = (LinkedHashMap<AbstractForm, Boolean>) ClientSession.get()
				.getData("map");
		Iterator<Entry<AbstractForm, Boolean>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<AbstractForm, Boolean> entry = entries.next();
			if (entry.getKey().getClass().isInstance(new SplashForm())) {
				entry = entries.next();
				while (!entry.getValue()) {
					entry = entries.next();
				}
				if (entry.getKey().getClass().isInstance(new GenerateForm())) {
					getFinishButton().setLabel(TEXTS.get("Finish"));
					break;
				} else {
					break;
				}
			}
		}
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
			public class IconField extends AbstractFileChooserField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("SplashScreen") + ":";
				}

				@Override
				protected List<String> getConfiguredFileExtensions() {
					return CollectionUtility.arrayList("png", "bmp", "jpg", "jpeg", "gif");
				}

				@Override
				protected double getConfiguredGridWeightX() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				protected int getConfiguredWidthInPixel() {
					// TODO Auto-generated method stub
					return 300;
				}

				@Override
				protected void execChangedValue() {
					int fileSize = getIconField().getFileSize();
					if (fileSize < 500000) {
						getImageField().setImage(getValue().getContent());
						getImageField().setImageId(getValue().getFilename());
						getHtmlField().setValue("");
					} else {
						getImageField().setImage(null);
						String html = "<img src='res/warning.png'>&nbsp;" + TEXTS.get("PictureSizeMustBeLessThan500KB");
						getHtmlField().setValue(html);
					}
				}
			}

			@Order(2000)
			public class ImageField extends AbstractImageField {

				@Override
				protected boolean getConfiguredAutoFit() {
					// TODO Auto-generated method stub
					return true;
				}

				@Override
				protected int getConfiguredGridH() {
					// TODO Auto-generated method stub
					return 3;
				}

			}

			@Order(3000)
			public class HtmlField extends AbstractHtmlField {

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
			public class FinishButton extends AbstractButton {
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
					if (getIconField().getValue() == null) {
						String html = "<img src='res/warning.png'>&nbsp;" + TEXTS.get("MissingImage");
						getHtmlField().setValue(html);
					}
					else if(getIconField().getFileSize()>500000){
						getImageField().setImage(null);
						String html = "<img src='res/warning.png'>&nbsp;" + TEXTS.get("PictureSizeMustBeLessThan500KB");
						getHtmlField().setValue(html);
					}
					else {
						doClose();
						String data = (String) ClientSession.get().getData("Edit");
						ClientSession.get().setData("splash", getIconField().getValue());
						ClientSession.get().setData("splash_filename", getIconField().getValue().getFilename());
						LinkedHashMap<AbstractForm, Boolean> map = (LinkedHashMap<AbstractForm, Boolean>) ClientSession
								.get().getData("map");
						Iterator<Entry<AbstractForm, Boolean>> entries = map.entrySet().iterator();
						while (entries.hasNext()) {
							Entry<AbstractForm, Boolean> entry = entries.next();
							if (entry.getKey().getClass().isInstance(new SplashForm())) {
								ClientSession.get().setData("splashcheck", "splashcheck");
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
									appVersion.setWebToMobileURL(null);
									appVersion.setSetting((int) ClientSession.get().getData("setting"));
									new UrlForm().prepareToGenerateApp(appVersion);
									new UrlForm().AddDynamincTree();
									break;
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			ISplashService service = BEANS.get(ISplashService.class);
			SplashFormData formData = new SplashFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateSplashPermission());
		}

		@Override
		protected void execStore() {
			ISplashService service = BEANS.get(ISplashService.class);
			SplashFormData formData = new SplashFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			ISplashService service = BEANS.get(ISplashService.class);
			SplashFormData formData = new SplashFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);

			setEnabledPermission(new CreateSplashPermission());
		}

		@Override
		protected void execStore() {
			ISplashService service = BEANS.get(ISplashService.class);
			SplashFormData formData = new SplashFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}
}
