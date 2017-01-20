package com.art2app.client.account;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.TEXTS;

import com.art2app.shared.account.AccountFormData;
import com.art2app.shared.accout.IAccountService;
import com.art2app.client.account.MyAccountForm.MainBox.CancelButton;
import com.art2app.client.account.MyAccountForm.MainBox.GeneralBox;
import com.art2app.client.account.MyAccountForm.MainBox.OkButton;
import com.art2app.client.account.MyAccountForm.MainBox.GeneralBox.CompanyField;
import com.art2app.client.account.MyAccountForm.MainBox.GeneralBox.EmailField;
import com.art2app.client.account.MyAccountForm.MainBox.GeneralBox.FirstNameField;
import com.art2app.client.account.MyAccountForm.MainBox.GeneralBox.LastNameField;

@FormData(value = AccountFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class MyAccountForm extends AbstractForm {

	private String accountId;
	@FormData
	public String getAccountId() {
		return accountId;
	}
	
	@FormData
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Override
	public Object computeExclusiveKey() { 
		return getAccountId();
	}

	@Override
	protected int getConfiguredDisplayHint() { 
		return IForm.DISPLAY_HINT_VIEW;
	}
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Account");
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GeneralBox getGeneralBox() {
		return getFieldByClass(GeneralBox.class);
	}

	public FirstNameField getFirstNameField() {
		return getFieldByClass(FirstNameField.class);
	}

	public LastNameField getLastNameField() {
		return getFieldByClass(LastNameField.class);
	}

	public CompanyField getCompanyField() {
		return getFieldByClass(CompanyField.class);
	}

	public EmailField getEmailField() {
		return getFieldByClass(EmailField.class);
	}
	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		
		@Order(1000)
		public class GeneralBox extends AbstractGroupBox {

			@Order(1000)
			public class FirstNameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("FirstName");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
			}

			@Order(2000)
			public class LastNameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("LastName");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
			}

			@Order(3000)
			public class CompanyField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Company");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
			}

			@Order(4000)
			public class EmailField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Email");
				}

				@Override
				protected int getConfiguredMaxLength() {
					return 128;
				}
			}	
		}

		@Order(100000)
		public class OkButton extends AbstractOkButton {
			  @Override
			  protected int getConfiguredSystemType() {
			    return SYSTEM_TYPE_SAVE;
			  }
		}

		@Order(101000)
		public class CancelButton extends AbstractCancelButton {
			  @Override
			  protected int getConfiguredSystemType() {
			    return SYSTEM_TYPE_RESET;
			  }
		}
	}

	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IAccountService service = BEANS.get(IAccountService.class);
			AccountFormData formData = new AccountFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);
			
			setSubTitle(calculateSubTitle());
		}

		@Override
		protected void execStore() {
			IAccountService service = BEANS.get(IAccountService.class);
			AccountFormData formData = new AccountFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			IAccountService service = BEANS.get(IAccountService.class);
			AccountFormData formData = new AccountFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);

//			setEnabledPermission(new CreateAccountPermission());
		}

		@Override
		protected void execStore() {
			IAccountService service = BEANS.get(IAccountService.class);
			AccountFormData formData = new AccountFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}

	private String calculateSubTitle() {
		return StringUtility.join(" ", getFirstNameField().getValue(),
				getLastNameField().getValue());
	}


}
