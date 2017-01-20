package com.art2app.client.create;

import java.util.List;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;

import com.art2app.client.ClientSession;
import com.art2app.client.create.CreateForm.MainBox.ContentBox;
import com.art2app.client.create.CreateForm.MainBox.UnderBox;
import com.art2app.client.create.CreateForm.MainBox.ContentBox.ImageField;
import com.art2app.client.create.CreateForm.MainBox.ContentBox.IconBox.IconCheckedField;
import com.art2app.client.create.CreateForm.MainBox.ContentBox.IconBox.IconField;
import com.art2app.client.create.CreateForm.MainBox.ContentBox.NameBox.NameCheckedField;
import com.art2app.client.create.CreateForm.MainBox.ContentBox.NameBox.NameField;
import com.art2app.client.create.CreateForm.MainBox.UnderBox.CancelButton;
import com.art2app.client.create.CreateForm.MainBox.UnderBox.FinshButton;
import com.art2app.shared.create.CreateFormData;
import com.art2app.shared.create.ICreateService;
import com.art2app.shared.create.UpdateCreatPermission;

@FormData(value = CreateFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class CreateForm extends AbstractForm {

	private String Name;
	private String User;
	private int appid;
	private byte[] icons;
	private String icon_filename;
	private byte[] splashscreen;
	private String splash_filename;
	private String webtomobile_url;
	private int setting;

	@FormData
	public int getAppid() {
		return appid;
	}

	@FormData
	public void setAppid(int appid) {
		this.appid = appid;
	}

	@FormData
	public byte[] getIcons() {
		return icons;
	}

	@FormData
	public void setIcons(byte[] icons) {
		this.icons = icons;
	}

	@FormData
	public byte[] getSplashscreen() {
		return splashscreen;
	}

	@FormData
	public void setSplashscreen(byte[] splashscreen) {
		this.splashscreen = splashscreen;
	}

	@FormData
	public String getWebtomobile_url() {
		return webtomobile_url;
	}

	@FormData
	public void setWebtomobile_url(String webtomobile_url) {
		this.webtomobile_url = webtomobile_url;
	}

	@FormData
	public int getSetting() {
		return setting;
	}

	@FormData
	public void setSetting(int setting) {
		this.setting = setting;
	}

	public CreateForm() {
		setHandler(new ModifyHandler());
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
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

	public ImageField getImageField() {
		return getFieldByClass(ImageField.class);
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	public UnderBox getFloorBox() {
		return getFieldByClass(UnderBox.class);
	}

	public NameField getNameField() {
		return getFieldByClass(NameField.class);
	}

	public IconField getIconField() {
		return getFieldByClass(IconField.class);
	}

	public FinshButton getFinshButton() {
		return getFieldByClass(FinshButton.class);
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

	@FormData
	public String getName() {
		return Name;
	}

	@FormData
	public void setName(String name) {
		Name = name;
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
	public String getIcon_filename() {
		return icon_filename;
	}

	@FormData
	public void setIcon_filename(String icon_filename) {
		this.icon_filename = icon_filename;
	}

	@FormData
	public String getSplash_filename() {
		return splash_filename;
	}

	@FormData
	public void setSplash_filename(String splash_filename) {
		this.splash_filename = splash_filename;
	}

	public NameCheckedField getNameCheckedField() {
		return getFieldByClass(NameCheckedField.class);
	}

	public IconCheckedField getIconCheckedField() {
		return getFieldByClass(IconCheckedField.class);
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
			protected int getConfiguredWidthInPixel() {
				return 700;
			}

			@Override
			protected int getConfiguredHeightInPixel() {
				return 400;
			}

			@Order(1000)
			public class NameBox extends AbstractGroupBox {

				@Override
				protected boolean getConfiguredLabelVisible() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				protected int getConfiguredWidthInPixel() {
					// TODO Auto-generated method stub
					return 700;
				}

				@Override
				protected int getConfiguredHeightInPixel() {
					// TODO Auto-generated method stub
					return 40;
				}

				@Order(1000)
				public class NameField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Name") + ":";
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
					protected int getConfiguredHorizontalAlignment() {
						// TODO Auto-generated method stub
						return -1;
					}

					@Override
					protected int getConfiguredWidthInPixel() {
						return 400;
					}

					@Override
					protected void execChangedValue() {
						getNameCheckedField().setValue(null);
					}
				}

				@Order(2000)
				public class NameCheckedField extends AbstractHtmlField {
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
			public class IconBox extends AbstractGroupBox {
				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Override
				protected int getConfiguredWidthInPixel() {
					return 700;
				}

				@Override
				protected int getConfiguredHeightInPixel() {
					return 40;
				}

				@Order(1000)
				public class IconField extends AbstractFileChooserField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Icon") + ":";
					}

					@Override
					protected List<String> getConfiguredFileExtensions() {
						return CollectionUtility.arrayList("png", "bmp", "jpg", "jpeg", "gif");
					}

					@Override
					protected int getConfiguredWidthInPixel() {
						return 550;
					}

					@Override
					protected void execChangedValue() {
						int fileSize = getIconField().getFileSize();
						if (fileSize < 100000) {
							getImageField().setImage(getValue().getContent());
							getImageField().setImageId(getValue().getFilename());
							getIconCheckedField().setValue(null);
						} else {
							getImageField().setImage(null);
							getIconCheckedField()
									.setValue("<span style='color:red'>" + TEXTS.get("PictureSizeMustBeLessThan100KB") + "</span>");
						}
					}
				}

				@Order(2000)
				public class IconCheckedField extends AbstractHtmlField {
					@Override
					protected boolean getConfiguredLabelVisible() {
						return false;
					}

					@Override
					protected int getConfiguredHorizontalAlignment() {
						// TODO Auto-generated method stub
						return -1;
					}
				}
			}

			@Order(3000)
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
					getDesktop().getOutline().selectNode(null);
					new UrlForm().cleanSession();
				}
			}

			@Order(2000)
			public class FinshButton extends AbstractButton {
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
					return 130;
				}

				@Override
				protected int getConfiguredHeightInPixel() {
					return 30;
				}

				@Override
				protected void execClickAction() {
					new NewHandler().execStore();
				}
			}
		}

	}

	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			ICreateService service = BEANS.get(ICreateService.class);
			CreateFormData formData = new CreateFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateCreatPermission());
		}

		@Override
		protected void execStore() {
			ICreateService service = BEANS.get(ICreateService.class);
			CreateFormData formData = new CreateFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			ICreateService service = BEANS.get(ICreateService.class);
			CreateFormData formData = new CreateFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			ICreateService service = BEANS.get(ICreateService.class);
			CreateFormData formData = new CreateFormData();
			exportFormData(formData);
			boolean noName = StringUtility.isNullOrEmpty(getNameField().getValue());
			if (noName) {
				/*
				 * MessageBoxes.create().withHeader("Warning").withBody(
				 * "MissingName").withCancelButtonText("CloseButton") .show();
				 */
				getNameCheckedField()
						.setValue("<span style='color:red'>" + TEXTS.get("TheNameCanNotBeEmpty") + "</span>");
				getNameField().requestFocus();
			} else {
				CreateFormData form = service.selectAppname(ClientSession.get().getUserId(), getNameField().getValue(),
						formData);
				String data = (String) ClientSession.get().getData("Edit");
				getNameCheckedField().setValue(null);
				if ("Edit".equals(data)) {
					if (!(getNameField().getValue()).equals(form.getName().getValue())
							|| (getNameField().getValue().equals(ClientSession.get().getData("appname")))) {
						getNameCheckedField().setValue(null);
					} else {
						/*
						 * MessageBoxes.create().withHeader("Warning").
						 * withBody("Name have been Created")
						 * .withCancelButtonText("CloseButton").show();
						 */
						getNameCheckedField()
								.setValue("<span style='color:red'>" + TEXTS.get("TheNameAlreadyExists") + "</span>");
						getNameField().requestFocus();
					}
				} else if ((getNameField().getValue()).equals(form.getName().getValue())) {
					/*
					 * MessageBoxes.create().withHeader("Warning").
					 * withBody("Name have been Created")
					 * .withCancelButtonText("CloseButton").show();
					 */
					getNameCheckedField()
							.setValue("<span style='color:red'>" + TEXTS.get("TheNameAlreadyExists") + "</span>");
					getNameField().requestFocus();
				}

				if (getIconField().getValue() == null) {
					/*
					 * MessageBoxes.create().withHeader("Warning").withBody(
					 * "MissIcon") .withCancelButtonText("CloseButton").show();
					 */
					getNameCheckedField().setValue(null);
					getIconCheckedField()
							.setValue("<span style='color:red'>" + TEXTS.get("TheIconCanNotBeEmpty") + "</span>");
					getIconField().requestFocus();
				} else if (getIconField().getFileSize() > 100000) {
					getNameCheckedField().setValue(null);
					getIconCheckedField()
							.setValue("<span style='color:red'>" + TEXTS.get("PictureSizeMustBeLessThan100KB") + "</span>");
					getIconField().requestFocus();

				} else {
					getNameCheckedField().setValue(null);
					getIconCheckedField().setValue(null);
					doClose();
					ClientSession.get().setData("appname", getNameField().getValue());
					ClientSession.get().setData("icon", getIconField().getValue());
					ClientSession.get().setData("icon_filename", getIconField().getValue().getFilename());
					CreateChooseForm creatChooseForm = new CreateChooseForm();
					creatChooseForm.startNew();
				}
			}

		}
	}
}
