package com.art2app.server.apppage;

import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.shared.apppage.ISocialNetworkService;
import com.art2app.shared.apppage.SocialNetworkTablePageData;

public class SocialNetworkService implements ISocialNetworkService {

	@Override
	public SocialNetworkTablePageData getSocialNetworkTableData(SearchFilter filter) {
		SocialNetworkTablePageData pageData = new SocialNetworkTablePageData();
		// TODO [admin] fill pageData.
		return pageData;
	}
}
