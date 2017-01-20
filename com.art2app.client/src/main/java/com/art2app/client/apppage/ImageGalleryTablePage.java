package com.art2app.client.apppage;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.client.apppage.ImageGalleryTablePage.Table;
import com.art2app.shared.apppage.IImageGalleryService;
import com.art2app.shared.apppage.ImageGalleryTablePageData;

@Data(ImageGalleryTablePageData.class)
public class ImageGalleryTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		// TODO [Administrator] verify translation
		return "Image Gallery";
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IImageGalleryService.class).getImageGalleryTableData(filter));
	}

	public class Table extends AbstractTable {
	}
	@Override
	protected boolean getConfiguredLeaf() {
		// TODO Auto-generated method stub
		return true;
	}
}
