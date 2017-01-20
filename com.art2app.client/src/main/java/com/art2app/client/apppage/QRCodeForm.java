package com.art2app.client.apppage;


import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;

import com.art2app.client.apppage.QRCodeForm.MainBox.EcsoyaBox;
import com.art2app.client.apppage.QRCodeForm.MainBox.EcsoyaBox.CommitButton;
import com.art2app.client.apppage.QRCodeForm.MainBox.EcsoyaBox.QRCodeField;
import com.art2app.client.apppage.QRCodeForm.MainBox.EcsoyaBox.QRCodeStringField;

import com.art2app.shared.apppage.CreateQRCodePermission;
import com.art2app.shared.apppage.IQRCodeService;
import com.art2app.shared.apppage.QRCodeFormData;
import com.art2app.shared.apppage.UpdateQRCodePermission;


@FormData(value = QRCodeFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class QRCodeForm extends AbstractForm {

	@Override
	protected String getConfiguredTitle() {
		// TODO [Ecsoya] verify translation
		return TEXTS.get("Person");
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new ViewHandler());
	}



	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public EcsoyaBox getEcsoyaBox() {
		return getFieldByClass(EcsoyaBox.class);
	}

	public QRCodeField getQRCodeField() {
		return getFieldByClass(QRCodeField.class);
	}

	public QRCodeStringField getQRCodeStringField() {
		return getFieldByClass(QRCodeStringField.class);
	}

	public CommitButton getCommitButton() {
		return getFieldByClass(CommitButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Order(1000)
		public class EcsoyaBox extends AbstractGroupBox {

			@Override
			protected int getConfiguredGridColumnCount() {
				return 1;
			}

			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("PersonMainBox");
			}

			@Order(1500)
			public class QRCodeStringField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("QRCodeString");
				}

				protected boolean getConfiguredMultilineText() {
					return true;
				}

			}

			@Order(1750)
			public class CommitButton extends AbstractButton {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Commit");
				}

				@Override
				protected void execClickAction() {
					getQRCodeField().setOriginalText(getQRCodeStringField().getValue());
					// try (InputStream in =
					// ResourceBase.class.getResource("html/demo.html").openStream())
					// {
					// getQRCodeField().setValue(IOUtility.readString(in,
					// null));
					// } catch (IOException e) {
					// throw new ProcessingException("Html-Field can't load
					// file", e);
					// }
				}
			}

			@Order(2000)
			public class QRCodeField extends AbstractHtmlField {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("QRCode");
				}

				public void setOriginalText(String text) {
					String value = "<div id=\"scout_center\">Generate QRCode for: <h3>" + text
							+ "</h3><div id=\"qrcode\"></div><script>$(function() { $('#qrcode').qrcode(\"" + text
							+ "\");})</script></div>";
					setValue(value);

				}

				@Override
				protected boolean getConfiguredFillVertical() {
					return true;
				}

				@Override
				protected boolean getConfiguredGridUseUiHeight() {
					return true;
				}
			}

		}
	}

	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IQRCodeService service = BEANS.get(IQRCodeService.class);
			QRCodeFormData formData = new QRCodeFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateQRCodePermission());
		}

		@Override
		protected void execStore() {
			IQRCodeService service = BEANS.get(IQRCodeService.class);
			QRCodeFormData formData = new QRCodeFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IQRCodeService service = BEANS.get(IQRCodeService.class);
			QRCodeFormData formData = new QRCodeFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);

			setEnabledPermission(new CreateQRCodePermission());
		}

		@Override
		protected void execStore() {
			IQRCodeService service = BEANS.get(IQRCodeService.class);
			QRCodeFormData formData = new QRCodeFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}

	public class ViewHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {

		}
	}
}
