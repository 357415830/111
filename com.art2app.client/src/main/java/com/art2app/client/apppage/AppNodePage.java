package com.art2app.client.apppage;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;

import com.art2app.client.ClientSession;

public class AppNodePage extends AbstractPageWithNodes {
	
	private String name;

	public String getConfiguredTitle() {
		return getName();
	}

	@Override
		protected void execPageActivated() {
		ClientSession.get().setData("appname", getConfiguredTitle());
		NotificationsForm data = (NotificationsForm) ClientSession.get().getData("downform");
		if (data != null) {
			data.doClose();
		}
		setTableVisible(false);
		NotificationsForm form = new NotificationsForm();
		form.setDisplayHint(20);
		form.startNew();
		ClientSession.get().setData("downform", form);

	}

	@Override
		protected boolean getConfiguredLeaf() {
			return true;
		}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
