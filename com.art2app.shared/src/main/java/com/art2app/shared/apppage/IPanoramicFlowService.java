package com.art2app.shared.apppage;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IPanoramicFlowService extends IService {

	PanoramicFlowTablePageData getPanoramicFlowTableData(SearchFilter filter);
}
