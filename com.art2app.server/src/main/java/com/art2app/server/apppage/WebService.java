package com.art2app.server.apppage;

import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.shared.apppage.IWebService;
import com.art2app.shared.apppage.WebTablePageData;

public class WebService implements IWebService {

	@Override
	public WebTablePageData getWebTableData(SearchFilter filter) {
		WebTablePageData pageData = new WebTablePageData();
		// TODO [admin] fill pageData.
		return pageData;
	}
}
