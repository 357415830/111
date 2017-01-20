package com.art2app.client.apppage;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.client.ClientSession;
import com.art2app.client.apppage.NotificationsTablePage.Table;
import com.art2app.shared.apppage.INotificationsService;
import com.art2app.shared.apppage.NotificationsTablePageData;

@Data(NotificationsTablePageData.class)
public class NotificationsTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		// TODO [Administrator] verify translation
		return TEXTS.get("Notifications");
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(INotificationsService.class).getNotificationsTableData(filter));
	}
	
	/*@Override
	protected boolean getConfiguredTableVisible() {
		return false;
	}*/
	
	@Override
	protected void execPageActivated() {
		if(((AppNodePage)getOutline().getSelectedNode().getParentNode()).getConfiguredTitle()!=ClientSession.get().getData("appname")){
			ClientSession.get().setData("appname", ((AppNodePage)getOutline().getSelectedNode().getParentNode()).getConfiguredTitle());
		}
		setTableVisible(false);
		NotificationsForm data = (NotificationsForm) ClientSession.get().getData("downform");
		if(data!=null){
			data.doClose();
		}
		NotificationsForm form = new NotificationsForm();
		form.setDisplayHint(20);
		form.startNew();
		ClientSession.get().setData("downform", form);
	}
	public class Table extends AbstractTable {
	}
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}
}
