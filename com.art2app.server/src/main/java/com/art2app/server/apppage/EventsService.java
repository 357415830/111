package com.art2app.server.apppage;

import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.shared.apppage.EventsTablePageData;
import com.art2app.shared.apppage.IEventsService;

public class EventsService implements IEventsService {

	@Override
	public EventsTablePageData getEventsTableData(SearchFilter filter) {
		EventsTablePageData pageData = new EventsTablePageData();
		// TODO [admin] fill pageData.
		return pageData;
	}
}
