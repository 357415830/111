package com.art2app.server.apppage;

import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.shared.apppage.IImageGalleryService;
import com.art2app.shared.apppage.ImageGalleryTablePageData;

public class ImageGalleryService implements IImageGalleryService {

	@Override
	public ImageGalleryTablePageData getImageGalleryTableData(SearchFilter filter) {
		ImageGalleryTablePageData pageData = new ImageGalleryTablePageData();
		// TODO [admin] fill pageData.
		return pageData;
	}
}
