package com.art2app.client.appbuilder;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.shared.TEXTS;

import com.art2app.client.account.AccountTablePage;

public class AppBuilderNodePage extends AbstractPageWithNodes {
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected boolean getConfiguredTableVisible() {
		return false;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("AppBuilderNodePage_Title");
	}

	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		super.execCreateChildPages(pageList);
		pageList.add(new AccountTablePage());
	}

	@Override
	protected Class<? extends IForm> getConfiguredDetailForm() {
		return AppBuilderForm.class;
	}
}
