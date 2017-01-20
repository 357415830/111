package com.art2app.server.apppage;

import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.shared.apppage.IPanoramicFlowService;
import com.art2app.shared.apppage.PanoramicFlowTablePageData;

public class PanoramicFlowService implements IPanoramicFlowService {

	@Override
	public PanoramicFlowTablePageData getPanoramicFlowTableData(SearchFilter filter) {
		PanoramicFlowTablePageData pageData = new PanoramicFlowTablePageData();
		// TODO [admin] fill pageData.
		return pageData;
	}
}
