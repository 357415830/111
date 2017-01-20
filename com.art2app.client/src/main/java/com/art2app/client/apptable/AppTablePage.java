package com.art2app.client.apptable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.job.ModelJobs;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.client.ui.MouseButton;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateTimeColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIconColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.OpenUriAction;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBoxes;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.job.FixedDelayScheduleBuilder;
import org.eclipse.scout.rt.platform.job.Jobs;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;
import org.eclipse.scout.rt.shared.ISession;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.job.filter.future.SessionFutureFilter;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.client.ClientSession;
import com.art2app.client.apptable.AppTablePage.Table;
import com.art2app.client.create.CreateForm;
import com.art2app.client.create.UrlForm;
import com.art2app.shared.Icons;
import com.art2app.shared.apptable.AppTablePageData;
import com.art2app.shared.apptable.AppTablePageData.AppTableRowData;
import com.art2app.shared.apptable.IAppService;
import com.art2app.shared.create.CreateFormData;
import com.art2app.shared.create.ICreateService;
import com.art2app.shared.create.IGenerateService;
import com.art2app.shared.exception.URLException;

/**
 * @author admin
 *
 */
@Data(AppTablePageData.class)
public class AppTablePage extends AbstractPageWithTable<Table> {

	private AppTableRowData appTableData;
	private String name;
	private String IPA;
	private String APK;
	@Override
	protected String getConfiguredTitle() {
		// TODO [Administrator] verify translation
		return TEXTS.get("Notifications");
	}

	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected void execPageActivated() {
		reloadPage();
	}
	@Override
	protected void execInitPage() {
		
		if("Edit".equals(ClientSession.get().getData("Edit"))||
				"Create".equals(ClientSession.get().getData("Create"))){
	
			 ModelJobs.schedule(new IRunnable() {
	          @Override
	          public void run() throws Exception {
	        	  ClientSession.get().setData("Create",null);
	        	  AppTableRowData appTableData2 = BEANS.get(IAppService.class).getAppTableData(new SearchFilter());
	        	  if(appTableData2.getDownload()!=null&&!appTableData2.getDownload().equals("generating")){
	        	  reloadPage();
	        	  Jobs.getJobManager().cancel(Jobs.newFutureFilterBuilder()
	        			    .andMatch(new SessionFutureFilter(ISession.CURRENT.get()))
	        			    .toFilter(), true);
	        	  }
	        	 }
	        }, ModelJobs.newInput(ClientRunContexts.copyCurrent())
	            .withExecutionTrigger(Jobs.newExecutionTrigger().withSchedule(FixedDelayScheduleBuilder.repeatForever(1, TimeUnit.SECONDS))));		
		}
	}
	@Override
	protected void execLoadData(SearchFilter filter) {
		getTable().deleteAllRows();
		if("Edit".equals((String)ClientSession.get().getData("Edit"))){
			BEANS.get(IGenerateService.class).updateVersionIdByAppId(BEANS.get(IGenerateService.class).getAppIdByAppName((String) ClientSession.get().getData("appname")));
		}
		BEANS.get(IAppService.class).setApp((String) ClientSession.get().getData("appname"),
				ClientSession.get().getUserId());
		appTableData = BEANS.get(IAppService.class).getAppTableData(filter);
		ClientSession.get().setData("version", appTableData.getVersion());
		Table table = getTable();
		ITableRow r;
		r = table.addRow(getTable().createRow());
		table.getStoreColumn().setValue(r, "Apple Store");
		table.getSystemColumn().setValue(r, "iOS");
		/*table.getVersionColumn().setValue(r, appTableData.getVersion());
		table.getDateColumn().setValue(r, appTableData.getDate());*/
		if (appTableData.getDownloada() == null ) {
			table.getDownloadColumn().setValue(r, "icon/" + Icons.Generate);
		} else if("no generating".equals(appTableData.getDownloada())){
			table.getDownloadColumn().setValue(r, "icon/" + Icons.Upgrade);
		}

		r = table.addRow(getTable().createRow());
		table.getStoreColumn().setValue(r, "Google Play Store");
		table.getSystemColumn().setValue(r, "Android");
		table.getVersionColumn().setValue(r, appTableData.getVersion());
		table.getDateColumn().setValue(r, appTableData.getDate());
		if (appTableData.getDownload() == null || appTableData.getDownload() == "generating") {
			table.getDownloadColumn().setValue(r, "icon/" + Icons.Generate);
		} else  if(IGenerateService.FAILED.equals(appTableData.getDownload())){
			//getOpenMenu().setVisible(false);
			table.getVersionColumn().setValue(r, null);
			table.getDateColumn().setValue(r, null);
			table.getDownloadColumn().setValue(r, "icon/" + Icons.Upgrade);
			
		}else {
			table.getDownloadColumn().setValue(r, "icon/" + Icons.DownLoad);
			table.getUpgradeColumn().setValue(r, "icon/" + Icons.Upgrade);
		}
		IPA = appTableData.getDownloada();
		APK = appTableData.getDownload();
		
		if((String)ClientSession.get().getData("ifReload")!=null){
			ClientSession.get().setData("ifReload",null);
			try {
				generateApp();
			} catch (IOException e ) {
				MessageBoxes.create().withHeader("Error").withBody("Generate failure, please contact the administrator")
				.withCancelButtonText("Close").show();
				new UrlForm().cleanSession();
				e.printStackTrace();
			} catch (URLException e) {
				MessageBoxes.create().withHeader("Error").withBody("Generate failure, please contact the administrator")
				.withCancelButtonText("Close").show();
				new UrlForm().cleanSession();
				e.printStackTrace();
			}
		}
		/*System.out.println("IPA=" + IPA);
		System.out.println("APK=" + APK);*/
	}

	public void generateApp() throws FileNotFoundException, IOException, URLException {
		IGenerateService service = BEANS.get(IGenerateService.class);
		String appname =(String)ClientSession.get().getData("appname");
		String version = service.getVersionByAppId(service.getAppIdByAppName(appname));
				if(version==null){
					version ="0.0.1";
				}else{
					String replace = version.replace(".", "");
					int parseInt = Integer.parseInt(replace);
					String format = String.format("%0"+replace.length()+"d", (parseInt+1));
					StringBuffer s = new StringBuffer(format);
					for(int i=1;i<=format.length();i+=2){
						s.insert(i, ".");
					}
					version = s.toString();
				}
				
		service.preGenerateApp(
				service.getAppIdByAppName(appname),
				service.getVersionIdByAppId(service.getAppIdByAppName(appname)),
				version,
				appname,
				(BinaryResource)ClientSession.get().getData("icon"),
				(BinaryResource)ClientSession.get().getData("splash"),
				(String)ClientSession.get().getData("URL"));
		new UrlForm().cleanSession();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public class Table extends AbstractTable {

		public OpenMenu getOpenMenu(){
			return getMenuByClass(OpenMenu.class);
			
		}
		
		public UpgradeMenu getUpgradeMenu(){
			return getMenuByClass(UpgradeMenu.class);
		}
		
		public UpgradeColumn getUpgradeColumn() {
			return getColumnSet().getColumnByClass(UpgradeColumn.class);
		}

		public StoreColumn getStoreColumn() {
			return getColumnSet().getColumnByClass(StoreColumn.class);
		}

		public SystemColumn getSystemColumn() {
			return getColumnSet().getColumnByClass(SystemColumn.class);
		}

		public VersionColumn getVersionColumn() {
			return getColumnSet().getColumnByClass(VersionColumn.class);
		}

		public DateColumn getDateColumn() {
			return getColumnSet().getColumnByClass(DateColumn.class);
		}

		public DownloadColumn getDownloadColumn() {
			return getColumnSet().getColumnByClass(DownloadColumn.class);
		}

		public DownloadaColumn getDownloadaColumn() {
			return getColumnSet().getColumnByClass(DownloadaColumn.class);
		}
		
		@Override
		protected void execRowClick(ITableRow row, MouseButton mouseButton) {
			// TODO Auto-generated method stub
			String version = getTable().getVersionColumn().getValue(row);
			if(version==null){
				getOpenMenu().setVisible(false);
				getUpgradeMenu().setVisible(false);
			}
			else{
				getOpenMenu().setVisible(true);
				getUpgradeMenu().setVisible(true);
			}
		}

		@Order(1000)
		public class StoreColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return "Store";
			}

			@Override
			protected int getConfiguredWidth() {
				return 140;
			}
		}

		@Order(2000)
		public class SystemColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return "System";
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(3000)
		public class VersionColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return "Version";
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}

		}

		@Order(4000)
		public class DateColumn extends AbstractDateTimeColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return "Date";
			}

			@Override
			protected int getConfiguredWidth() {
				return 200;
			}
		}

		@Order(5000)
		public class DownloadColumn extends AbstractIconColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return "Download";
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}

		}

		@Order(6000)
		public class DownloadaColumn extends AbstractIconColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return "Downloada";
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}

		}

		@Order(7000)
		public class UpgradeColumn extends AbstractIconColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Upgrade");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(1000)
		public class EditMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("Edit");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.<IMenuType>hashSet(TableMenuType.EmptySpace);
			}
			
			@Override
			protected void execAction() {
				/*ITableRow selectedRow = getTable().getSelectedRow();
				String version = getTable().getVersionColumn().getValue(selectedRow);*/
				String version = (String) ClientSession.get().getData("version");
				String appname = (String) ClientSession.get().getData("appname");
				String appId = BEANS.get(IGenerateService.class).getAppIdByAppName(appname);
				CreateFormData selectAppVersion = BEANS.get(ICreateService.class).getAppVersionByAppId(version,appId);
				byte[] icons = selectAppVersion.getIcons();
				String icon_filename = selectAppVersion.getIcon_filename();
				BinaryResource binaryResource = new BinaryResource(icon_filename, icons);
				byte[] splashscreen = selectAppVersion.getSplashscreen();
				String splash_filename = selectAppVersion.getSplash_filename();
				BinaryResource binaryResource2 = new BinaryResource(splash_filename, splashscreen);
				String webtomobile_url = selectAppVersion.getWebtomobile_url();
				int setting = selectAppVersion.getSetting();				
				CreateForm creatForm = new CreateForm();
				creatForm.getNameField().setValue(appname);
				creatForm.getIconField().setValue(binaryResource);
				creatForm.getImageField().setImage(creatForm.getIconField().getValue().getContent());
				creatForm.getImageField().setImageId(creatForm.getIconField().getValue().getFilename());
				creatForm.startModify();
				ClientSession.get().setData("Edit", "Edit");
				ClientSession.get().setData("splash", binaryResource2);
				ClientSession.get().setData("splash_filename", splash_filename);
				ClientSession.get().setData("URL", webtomobile_url);
				ClientSession.get().setData("setting", setting);
				ClientSession.get().setData("appId", appId);
				
				/*CreatForm creatForm = new CreatForm();
				creatForm.getNameField().setValue((String) ClientSession.get().getData("appname"));
				creatForm.getIconField().setValue((BinaryResource) ClientSession.get().getData("icon"));
				creatForm.getImageField().setImage(creatForm.getIconField().getValue().getContent());
				creatForm.getImageField().setImageId(creatForm.getIconField().getValue().getFilename());
				creatForm.startModify();
				ClientSession.get().setData("Edit", "Edit");*/
			}
		}
		
		@Order(2000)
		public class OpenMenu extends AbstractMenu {

			@Override
			protected String getConfiguredText() {
				return "Download";
			}

			@Override
			public void setVisible(boolean b) {
				// TODO Auto-generated method stub
				super.setVisible(b);
			}
			
			@Override
			protected void execInitAction() {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.SingleSelection);
			}
			
			@Override
			protected void execAction() {

				boolean selected = getTable().getRow(0).isSelected();
				if (selected) {
					ClientSessionProvider.currentSession().getDesktop().openUri(IPA, OpenUriAction.DOWNLOAD);
				} else {
					ClientSessionProvider.currentSession().getDesktop().openUri(APK, OpenUriAction.DOWNLOAD);
				}
			}
			
		}

		@Order(3000)
		public class UpgradeMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("Upgrade");
			}

			@Override
			public void setVisible(boolean b) {
				// TODO Auto-generated method stub
				super.setVisible(b);
			}
			
			@Override
			protected void execInitAction() {
				// TODO Auto-generated method stub
				setVisible(false);
			}
			
			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.SingleSelection);
			}

			@Override
			protected void execAction() {
			}
		}
		
		

	}
}
