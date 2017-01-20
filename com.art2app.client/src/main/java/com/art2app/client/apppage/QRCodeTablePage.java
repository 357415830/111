package com.art2app.client.apppage;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;


import com.art2app.client.apppage.QRCodeTablePage.Table;
import com.art2app.shared.apppage.IQRCodeService;
import com.art2app.shared.apppage.QRCodeTablePageData;

@Data(QRCodeTablePageData.class)
public class QRCodeTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		// TODO [Administrator] verify translation
		return "QR Code";
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IQRCodeService.class).getQRCodeTableData(filter));
	}

	@Override
	protected Class<? extends IForm> getConfiguredDetailForm() {
		return QRCodeForm.class;
	}
	public class Table extends AbstractTable {
	}
	@Override
	protected boolean getConfiguredLeaf() {
		// TODO Auto-generated method stub
		return true;
	}
}
