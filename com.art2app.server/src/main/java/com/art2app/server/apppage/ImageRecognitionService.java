package com.art2app.server.apppage;

import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.shared.apppage.IImageRecognitionService;
import com.art2app.shared.apppage.ImageRecognitionTablePageData;

public class ImageRecognitionService implements IImageRecognitionService {

	@Override
	public ImageRecognitionTablePageData getImageRecognitionTableData(SearchFilter filter) {
		ImageRecognitionTablePageData pageData = new ImageRecognitionTablePageData();
		// TODO [admin] fill pageData.
		return pageData;
	}
}
