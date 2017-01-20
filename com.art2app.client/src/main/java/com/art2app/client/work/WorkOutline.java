package com.art2app.client.work;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;

import com.art2app.client.create.CreateTablePage;
import com.art2app.shared.Icons;

/**
 * <h3>{@link WorkOutline}</h3>
 *
 * @author Ecsoya
 */
@Order(10)
public class WorkOutline extends AbstractOutline {
	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		super.execCreateChildPages(pageList);
/*		pageList.add(new AppBuilderNodePage());
		pageList.add(new AccountTablePage());
		pageList.add(new MyAccountNodePage());
		pageList.add(new AppTablePage());*/
		pageList.add(new CreateTablePage());
		/*pageList.add(new MyappTablePage());*/
	}
	@Override
	@Order(10)
	protected String getConfiguredIconId() {
		return Icons.Phone;
	}

	@Override
	protected boolean getConfiguredNavigateButtonsVisible() {
		// TODO Auto-generated method stub
		return true;
	}
}
