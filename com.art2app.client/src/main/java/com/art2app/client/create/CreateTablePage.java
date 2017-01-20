package com.art2app.client.create;

import java.util.List;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.tree.ITreeNode;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.client.ClientSession;
import com.art2app.client.apppage.NotificationsForm;
import com.art2app.client.create.CreateTablePage.Table;
import com.art2app.shared.create.CreateTablePageData;
import com.art2app.shared.create.ICreateService;

@Data(CreateTablePageData.class)
public class CreateTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		// TODO [Administrator] verify translation
		return TEXTS.get("CreatANewSmartApp");
	}
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}
	@Override
	protected void execPageActivated() {
		List<ITreeNode> childNodes = getOutline().getRootNode().getChildNodes();
		for (ITreeNode iTreeNode : childNodes) {
			iTreeNode.setExpanded(false);
		}
		ClientSession.get().setData("Edit",null);
		ClientSession.get().setData("Create","Create");
		new CreateForm().startNew();
		NotificationsForm data = (NotificationsForm) ClientSession.get().getData("downform");
		if(data!=null){
			data.doClose();
		}
	}
	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(ICreateService.class).getCreatTableData(filter));
	}

	public class Table extends AbstractTable {
	}
	
}
