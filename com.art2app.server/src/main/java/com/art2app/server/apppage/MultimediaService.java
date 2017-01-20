package com.art2app.server.apppage;

import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.shared.apppage.IMultimediaService;
import com.art2app.shared.apppage.MultimediaTablePageData;

public class MultimediaService implements IMultimediaService {

	@Override
	public MultimediaTablePageData getMultimediaTableData(SearchFilter filter) {
		MultimediaTablePageData pageData = new MultimediaTablePageData();
		// TODO [admin] fill pageData.
		return pageData;
	}
}
