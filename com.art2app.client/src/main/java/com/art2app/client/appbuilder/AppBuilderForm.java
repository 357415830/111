package com.art2app.client.appbuilder;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;

import com.art2app.client.appbuilder.AppBuilderForm.MainBox.NameAndIconBox;
import com.art2app.client.appbuilder.AppBuilderForm.MainBox.NameAndIconBox.GenerateButton;
import com.art2app.client.appbuilder.AppBuilderForm.MainBox.NameAndIconBox.IconField;
import com.art2app.client.appbuilder.AppBuilderForm.MainBox.NameAndIconBox.NameField;
import com.art2app.client.appbuilder.AppBuilderForm.MainBox.PreviewBox;
import com.art2app.client.appbuilder.AppBuilderForm.MainBox.PreviewBox.PreviewIconField;
import com.art2app.shared.Icons;
import com.art2app.shared.appbuilder.AppBuilderFormData;
import com.art2app.shared.appbuilder.CreateAppBuilderPermission;
import com.art2app.shared.appbuilder.IAppBuilderService;
import com.art2app.shared.appbuilder.UpdateAppBuilderPermission;

@FormData(value = AppBuilderFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class AppBuilderForm extends AbstractForm {

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("AppBuilder");
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public NameField getNameField() {
		return getFieldByClass(NameField.class);
	}

	public IconField getIconField() {
		return getFieldByClass(IconField.class);
	}

	public PreviewIconField getPreviewIconField() {
		return getFieldByClass(PreviewIconField.class);
	}

	public PreviewBox getPreviewBox() {
		return getFieldByClass(PreviewBox.class);
	}

	public GenerateButton getCommitButton() {
		return getFieldByClass(GenerateButton.class);
	}

	public NameAndIconBox getNameAndIconBox() {
		return getFieldByClass(NameAndIconBox.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Override
		protected int getConfiguredGridColumnCount() {
			return 2;
		}

		@Order(1000)
		public class NameAndIconBox extends AbstractGroupBox {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("DefineNameAndIcons");
			}

			@Override
			protected int getConfiguredGridColumnCount() {
				return 1;
			}

			@Override
			protected int getConfiguredGridW() {
				return 1;
			}

			@Order(1000)
			public class NameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Name");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}

				@Override
				protected int getConfiguredGridW() {
					return 1;
				}
			}

			@Order(2000)
			public class IconField extends AbstractFileChooserField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Icon");
				}

				@Override
				protected int getConfiguredGridW() {
					return 1;
				}
			}

			@Order(3000)
			public class GenerateButton extends AbstractButton {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Generate");
				}

				@Override
				protected boolean getConfiguredProcessButton() {
					return false;
				}

				@Override
				protected void execClickAction() {
					
				}

				@Override
				protected int getConfiguredHorizontalAlignment() {
					return 0;
				}
			}

		}

		@Order(2000)
		public class PreviewBox extends AbstractGroupBox {
			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("Preview");
			}

			@Override
			protected int getConfiguredGridW() {
				return 1;
			}

			@Order(1000)
			public class PreviewIconField extends AbstractImageField {
				@Override
				protected int getConfiguredHorizontalAlignment() {
					return 0;
				}

				@Override
				protected int getConfiguredVerticalAlignment() {
					return -1;
				}

				@Override
				protected String getConfiguredImageId() {
					return Icons.Scout;
				}

				@Override
				protected boolean getConfiguredAutoFit() {
					return true;
				}

				@Override
				protected int getConfiguredGridH() {
					return 2;
				}
			}
		}

	}

	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IAppBuilderService service = BEANS.get(IAppBuilderService.class);
			AppBuilderFormData formData = new AppBuilderFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateAppBuilderPermission());
		}

		@Override
		protected void execStore() {
			IAppBuilderService service = BEANS.get(IAppBuilderService.class);
			AppBuilderFormData formData = new AppBuilderFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IAppBuilderService service = BEANS.get(IAppBuilderService.class);
			AppBuilderFormData formData = new AppBuilderFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);

			setEnabledPermission(new CreateAppBuilderPermission());
		}

		@Override
		protected void execStore() {
			IAppBuilderService service = BEANS.get(IAppBuilderService.class);
			AppBuilderFormData formData = new AppBuilderFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}
}
