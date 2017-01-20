package com.art2app.client.account;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractNumberColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.shared.account.AccountTablePageData;
import com.art2app.shared.accout.IAccountService;
import com.art2app.client.account.AccountTablePage.Table;

@Data(AccountTablePageData.class)
public class AccountTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Account");
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IAccountService.class).getAccountTableData(filter));
	}

	public class Table extends AbstractTable {

		private class AccountFormListener implements FormListener {

			@Override
			public void formChanged(FormEvent e) {
				// reload page to reflect new/changed data after saving any changes
				if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
					reloadPage();
				}
			}
		}

		
		@Order(1000)
		public class EditMenuMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("Edit");
			}

			@Override
			protected void execAction() {
				AccountForm form = new AccountForm();
				form.setAccountId(getAccountIdColumn().getSelectedValue()); 
				form.addFormListener(new AccountFormListener());
				// start the form using its modify handler
				form.startModify();
			}

		}
		public CompanyColumn getCompanyColumn() {
			return getColumnSet().getColumnByClass(CompanyColumn.class);
		}

		public EmailColumn getEmailColumn() {
			return getColumnSet().getColumnByClass(EmailColumn.class);
		}

		public StatusColumn getStatusColumn() {
			return getColumnSet().getColumnByClass(StatusColumn.class);
		}

		public ValidateCodeColumn getValidateCodeColumn() {
			return getColumnSet().getColumnByClass(ValidateCodeColumn.class);
		}

		public RegisterTimeColumn getRegisterTimeColumn() {
			return getColumnSet().getColumnByClass(RegisterTimeColumn.class);
		}

		public PasswordColumn getPasswordColumn() {
			return getColumnSet().getColumnByClass(PasswordColumn.class);
		}

		public LastNameColumn getLastNameColumn() {
			return getColumnSet().getColumnByClass(LastNameColumn.class);
		}

		public FirstNameColumn getFirstNameColumn() {
			return getColumnSet().getColumnByClass(FirstNameColumn.class);
		}

		public AccountIdColumn getAccountIdColumn() {
			return getColumnSet().getColumnByClass(AccountIdColumn.class);
		}

		@Order(1000)
		public class AccountIdColumn extends AbstractStringColumn {

			@Override 
			protected boolean getConfiguredDisplayable() {
				return false;
			}

			@Override 
			protected boolean getConfiguredPrimaryKey() {
				return true;
			}			
		}

		@Order(2000)
		public class FirstNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("FirstName");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(3000)
		public class LastNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("LastName");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(4000)
		public class CompanyColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Company");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(5000)
		public class PasswordColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Password");
			}
			
			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(6000)
		public class EmailColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Email");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(7000)
		public class StatusColumn extends AbstractNumberColumn<Short> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Status");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}

			@Override
			protected Short getConfiguredMinValue() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected Short getConfiguredMaxValue() {
				// TODO Auto-generated method stub
				return null;
			}
		}

		@Order(8000)
		public class ValidateCodeColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ValidateCode");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(9000)
		public class RegisterTimeColumn extends AbstractDateColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("RegisterTime");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}
		
	}
}
