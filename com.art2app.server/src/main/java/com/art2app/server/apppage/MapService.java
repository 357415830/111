package com.art2app.server.apppage;

import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.shared.apppage.IMapService;
import com.art2app.shared.apppage.MapTablePageData;

public class MapService implements IMapService {

	@Override
	public MapTablePageData getMapTableData(SearchFilter filter) {
		MapTablePageData pageData = new MapTablePageData();
		// TODO [admin] fill pageData.
		return pageData;
	}
}
