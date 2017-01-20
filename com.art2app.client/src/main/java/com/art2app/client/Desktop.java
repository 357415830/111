package com.art2app.client;

import java.util.List;

import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.AbstractFormMenu;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.platform.config.PlatformConfigProperties.ApplicationNameProperty;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.ISession;
import org.eclipse.scout.rt.shared.TEXTS;

import com.art2app.client.apppage.AppNodePage;
import com.art2app.client.apppage.NotificationsForm;
import com.art2app.client.search.SearchOutline;
import com.art2app.client.work.WorkOutline;
import com.art2app.shared.Icons;
import com.art2app.shared.create.IGenerateService;

public class Desktop extends AbstractDesktop {

	@Override
	protected String getConfiguredTitle() {
		return CONFIG.getPropertyValue(ApplicationNameProperty.class);
	}

	@Override
	protected String getConfiguredLogoId() {
		return "application_logo";
	}

	@Override
	public String getTheme() {
		return "dark";
	}

	@Override
	protected List<Class<? extends IOutline>> getConfiguredOutlines() {
		return CollectionUtility.<Class<? extends IOutline>>arrayList(WorkOutline.class, SearchOutline.class);
	}

	@Override
	protected void execDefaultView() {
		setOutline(WorkOutline.class);
	}

	public class RefreshOutlineKeyStroke extends AbstractKeyStroke {

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F5;
		}

		@Override
		protected void execAction() {
			if (getOutline() != null) {
				IPage<?> page = getOutline().getActivePage();
				if (page != null) {
					page.reloadPage();
				}
			}
		}
	}

	@Order(1)
	public class WorkOutlineViewButton extends AbstractOutlineViewButton {
		public WorkOutlineViewButton() {
			this(WorkOutline.class);
		}
		protected WorkOutlineViewButton(Class<? extends WorkOutline> outlineClass) {
			super(Desktop.this, outlineClass);
		}
		@Override
		protected DisplayStyle getConfiguredDisplayStyle() {
			return DisplayStyle.TAB;
		}
		@Override
		protected String getConfiguredIconId() {
			return Icons.Phone;
		}
		@Override
		protected String getConfiguredKeyStroke() {
			return "ctrl-shift-c";
		}
		@Override
		protected void execInitAction() {
		Object[][] selectAppname = BEANS.get(IGenerateService.class).getAppNameByUserName(ClientSession.get().getUserId());
			for (int i = 0; i < selectAppname.length; i++) {
				for (int j = 0; j < selectAppname[i].length; j++) {
					// System.out.println(selectAppname[i][0]);
					AppNodePage appNodePage = new AppNodePage();
					appNodePage.setName((String) selectAppname[i][0]);
					getOutline().addChildNode(getOutline().getRootNode(), appNodePage);
					getOutline().initTree();
				}
			}
		}
		@Override
	public boolean isSelected() {
			return true;
		}

	}

	@Order(2)
	public class SearchOutlineViewButton extends AbstractOutlineViewButton {

		public SearchOutlineViewButton() {
			this(SearchOutline.class);
		}

		protected SearchOutlineViewButton(Class<? extends SearchOutline> outlineClass) {
			super(Desktop.this, outlineClass);
		}

		@Override
		protected DisplayStyle getConfiguredDisplayStyle() {
			return DisplayStyle.TAB;
		}

		@Override
		protected String getConfiguredIconId() {
			return Icons.User;
		}

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F3;
		}
	}

	@Order(20)
	public class OptionsMenu extends AbstractFormMenu<OptionsForm> { // <1>

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("Options");
		}

		@Override
		protected String getConfiguredIconId() {
			return Icons.DownLoad;
		}

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F11;
		}

		@Override
		protected boolean getConfiguredVisible() {
			return false;
		}

		@Override
		protected Class<OptionsForm> getConfiguredForm() {
			return OptionsForm.class;
		}

	}

	@Order(30)
	public class UserMenu extends AbstractMenu { // <2>
		@Override
		protected String getConfiguredIconId() {

			return AbstractIcons.Person;

		}

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F12;
		}

		@Override
		protected void execInitAction() {
			setText(ISession.CURRENT.get().getUserId());
		}

	}

	@Order(40)
	public class ExitMenu extends AbstractMenu {
		@Override
		protected String getConfiguredIconId() {
			return Icons.Logout;
		}

		@Override
		protected void execAction() {
			NotificationsForm data = (NotificationsForm) ClientSession.get().getData("downform");
			if(data!=null){
				data.doClose();
			}
			ClientSessionProvider.currentSession(ClientSession.class).stop();
		}

	}

	@Override
	public void refreshPages(Class<?>... pageTypes) {
		// TODO Auto-generated method stub
		super.refreshPages(pageTypes);
	}
}
