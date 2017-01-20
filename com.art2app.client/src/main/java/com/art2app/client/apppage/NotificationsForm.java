package com.art2app.client.apppage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.job.ModelJobs;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.OpenUriAction;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBoxes;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.job.FixedDelayScheduleBuilder;
import org.eclipse.scout.rt.platform.job.Jobs;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;
import org.eclipse.scout.rt.shared.ISession;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.job.filter.future.SessionFutureFilter;
import org.eclipse.scout.rt.shared.services.common.file.IRemoteFileService;
import org.eclipse.scout.rt.shared.services.common.file.RemoteFile;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import com.art2app.client.ClientSession;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.AndroidBox;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.AndroidBox.AndroidField;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.AndroidBox.AndroidIconField;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.AndroidBox.DownloadAndroidButton;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.AndroidBox.ErrorField;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.ContentBox;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.ContentBox.DateField;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.ContentBox.NameField;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.IosBox;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.IosBox.DownloadIosButton;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.IosBox.IosField;
import com.art2app.client.apppage.NotificationsForm.MainBox.LeftBox.IosBox.IosIconField;
import com.art2app.client.create.CreateForm;
import com.art2app.client.create.UrlForm;
import com.art2app.client.apppage.NotificationsForm.MainBox.RightBox;
import com.art2app.shared.Icons;
import com.art2app.shared.apppage.CreateNotificationsPermission;
import com.art2app.shared.apppage.INotificationsService;
import com.art2app.shared.apppage.NotificationsFormData;
import com.art2app.shared.apppage.UpdateNotificationsPermission;
import com.art2app.shared.apptable.AppTablePageData.AppTableRowData;
import com.art2app.shared.apptable.IAppService;
import com.art2app.shared.create.CreateFormData;
import com.art2app.shared.create.ICreateService;
import com.art2app.shared.create.IGenerateService;
import com.art2app.shared.exception.URLException;

@FormData(value = NotificationsFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class NotificationsForm extends AbstractForm {

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public LeftBox getLeftBox() {
		return getFieldByClass(LeftBox.class);
	}

	public ContentBox getContentBox() {
		return getFieldByClass(ContentBox.class);
	}

	public IosBox getIosBox() {
		return getFieldByClass(IosBox.class);
	}

	public AndroidBox getAndroidBox() {
		return getFieldByClass(AndroidBox.class);
	}

	public RightBox getRightBox() {
		return getFieldByClass(RightBox.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public ErrorField getErrorField() {
		return getFieldByClass(ErrorField.class);
	}

	public NameField getNameField() {
		return getFieldByClass(NameField.class);
	}

	public DateField getDateField() {
		return getFieldByClass(DateField.class);
	}

	public IosField getIosField() {
		return getFieldByClass(IosField.class);
	}

	public IosIconField getIosIconField() {
		return getFieldByClass(IosIconField.class);
	}

	public DownloadIosButton getDownloadIosButton() {
		return getFieldByClass(DownloadIosButton.class);
	}

	public AndroidField getAndroidField() {
		return getFieldByClass(AndroidField.class);
	}

	public AndroidIconField getAndroidIconField() {
		return getFieldByClass(AndroidIconField.class);
	}

	public DownloadAndroidButton getDownloadAndroidButton() {
		return getFieldByClass(DownloadAndroidButton.class);
	}

	@Override
	protected void execInitForm() {
		if ("Edit".equals((String) ClientSession.get().getData("Edit"))) {
			BEANS.get(IGenerateService.class).updateVersionIdByAppId(getAppId());
		}
		BEANS.get(IAppService.class).setApp(getAppName(), ClientSession.get().getUserId());
		if ("Edit".equals(ClientSession.get().getData("Edit"))
				|| "Create".equals(ClientSession.get().getData("Create"))) {

			ModelJobs.schedule(new IRunnable() {
				@Override
				public void run() throws Exception {
					ClientSession.get().setData("Create", null);
					AppTableRowData appTableData = BEANS.get(IAppService.class).getAppTableData(new SearchFilter());
					if (appTableData.getDownload() != null && "failed".equals(appTableData.getDownload())) {
						loadForm(appTableData);
						setErrorReson("System generated app script exception");
						Jobs.getJobManager().cancel(Jobs.newFutureFilterBuilder()
								.andMatch(new SessionFutureFilter(ISession.CURRENT.get())).toFilter(), true);
					} else if (appTableData.getDownload() != null && !"generating".equals(appTableData.getDownload())) {
						loadForm(appTableData);

						Jobs.getJobManager().cancel(Jobs.newFutureFilterBuilder()
								.andMatch(new SessionFutureFilter(ISession.CURRENT.get())).toFilter(), true);
					}

				}
			}, ModelJobs.newInput(ClientRunContexts.copyCurrent()).withExecutionTrigger(Jobs.newExecutionTrigger()
					.withSchedule(FixedDelayScheduleBuilder.repeatForever(1, TimeUnit.SECONDS))));
		} else {
			AppTableRowData appTableData = BEANS.get(IAppService.class).getAppTableData(new SearchFilter());				
			if (appTableData.getDownload()!=null&&"failed".equals(appTableData.getDownload())) {
				loadForm(appTableData);
				setErrorReson("System generated app script exception");
			}else if (appTableData.getDownload() != null && !"generating".equals(appTableData.getDownload())) {
				loadForm(appTableData);
			}
		}
		if ((String) ClientSession.get().getData("ifReload") != null) {
			ClientSession.get().setData("ifReload", null);
			try {
				generateApp();
			} catch (IOException e) {
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
	}
	
	public void setErrorReson(String reason){
		getDownloadAndroidButton().setVisible(false);
		getErrorField().setVisible(true);
		getErrorField().setValue(getErrorMessage("#00001", reason));
	}

	public String getErrorMessage(String href, String errorCode, String exception) {

		String html = "<div style='margin:0 auto;padding:10px 0;width:160px;'>" + "<div style='float:left;'>"
				+ "<img src='icon/download.png' alt=''></div>" + "<div style='margin-left:30px;color:red'>"
				+ "Generation error:<br>Error code: <a href='" + href + "' style='color:red'>" + errorCode
				+ "</a><br>Reason: " + exception + "<br>" + "</div>" + "</div>";
		return html;
	}

	public String getErrorMessage(String errorCode, String exception) {

		String html = "<div style='margin:0 auto;padding:10px 0;width:160px;'>" + "<div style='float:left;'>"
				+ "<img src='icon/download.png' alt='' style='cursor: pointer;'></div>" + "<div style='margin-left:30px;color:red'>"
				+ "Generation error:<br>Error code: " + errorCode + "<br>Reason: "+ "<br>" + exception  + "</div>"
				+ "</div>";
		return html;
	}

	public void generateApp() throws FileNotFoundException, IOException, URLException {
		IGenerateService service = BEANS.get(IGenerateService.class);
		String appname = getAppName();
		String version = service.getVersionByAppId(service.getAppIdByAppName(appname));
		if (version == null) {
			version = "0.0.1";
		} else {
			String replace = version.replace(".", "");
			int parseInt = Integer.parseInt(replace);
			String format = String.format("%0" + replace.length() + "d", (parseInt + 1));
			StringBuffer s = new StringBuffer(format);
			for (int i = 1; i <= format.length(); i += 2) {
				s.insert(i, ".");
			}
			version = s.toString();
		}
		service.preGenerateApp(getAppId(), service.getVersionIdByAppId(getAppId()), version, appname,
				(BinaryResource) ClientSession.get().getData("icon"),
				(BinaryResource) ClientSession.get().getData("splash"), (String) ClientSession.get().getData("URL"));
		new UrlForm().cleanSession();
	}

	public String getApkName() {
		IGenerateService service = BEANS.get(IGenerateService.class);
		String appName = getAppName();
		String appId = getAppId();
		String versionId = service.getVersionIdByAppId(appId);
		return appName + appId + versionId + ".apk";
	}

	public void loadForm(AppTableRowData appTableData) {
		getMainBox().getEditMenu().setVisible(true);
		getMainBox().getDeleteMenu().setVisible(true);
		getNameField().setValue(getAppName());
		getDateField().setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(appTableData.getDate()));

		getIosIconField().setVisible(false);
		getIosField().setValue(appTableData.getVersion());
		getIosField().setVisible(true);
		getDownloadIosButton().setVisible(true);

		getAndroidIconField().setVisible(false);
		getAndroidField().setValue(appTableData.getVersion());
		getAndroidField().setVisible(true);
		getDownloadAndroidButton().setVisible(true);
	}

	public String getAppName() {
		return (String) ClientSession.get().getData("appname");
	}

	public String getAppId() {
		return BEANS.get(IGenerateService.class).getAppIdByAppName(getAppName());
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Override
		protected int getConfiguredGridColumnCount() {
			return 3;
		}

		public EditMenu getEditMenu() {
			return getMenuByClass(EditMenu.class);
		}

		public DeleteMenu getDeleteMenu() {
			return getMenuByClass(DeleteMenu.class);
		}

		@Order(1000)
		public class LeftBox extends AbstractGroupBox {

			@Override
			protected int getConfiguredGridColumnCount() {
				return 1;
			}

			@Override
			protected boolean getConfiguredLabelVisible() {
				return false;
			}

			@Override
			protected int getConfiguredGridW() {
				return 1;
			}

			@Override
			protected int getConfiguredHeightInPixel() {
				return 130;
			}

			@Order(1000)
			public class ContentBox extends AbstractGroupBox {

				@Override
				protected int getConfiguredHeightInPixel() {
					return 70;
				}

				@Override
				protected int getConfiguredGridColumnCount() {
					return 1;
				}

				@Override
				protected int getConfiguredGridW() {
					return 1;
				}

				@Order(1000)
				@FormData(sdkCommand = FormData.SdkCommand.IGNORE)
				public class NameField extends AbstractLabelField {

					@Override
					protected int getConfiguredLabelWidthInPixel() {
						return 100;
					}

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Name") + ":";
					}

				}

				@Order(2000)
				@FormData(sdkCommand = FormData.SdkCommand.IGNORE)
				public class DateField extends AbstractLabelField {

					@Override
					protected int getConfiguredLabelWidthInPixel() {
						return 100;
					}

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Date") + ":";
					}

				}
			}

			@Order(2000)
			public class IosBox extends AbstractGroupBox {

				@Override
				protected int getConfiguredHeightInPixel() {
					return 30;
				}

				@Override
				protected int getConfiguredGridColumnCount() {
					return 2;
				}

				@Override
				protected int getConfiguredGridW() {
					return 1;
				}

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Order(1000)
				@FormData(sdkCommand = FormData.SdkCommand.IGNORE)
				public class IosField extends AbstractLabelField {

					@Override
					protected int getConfiguredLabelWidthInPixel() {
						return 100;
					}

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("iOS") + ":";
					}

					@Override
					protected boolean getConfiguredVisible() {
						return false;
					}

					@Override
					protected boolean getConfiguredStatusVisible() {
						return false;
					}

				}

				@Order(2000)
				public class IosIconField extends AbstractImageField {

					@Override
					protected int getConfiguredLabelWidthInPixel() {
						return 100;
					}

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("iOS") + ":";
					}

					@Override
					protected int getConfiguredHorizontalAlignment() {
						return -1;
					}

					@Override
					protected String getConfiguredImageId() {
						return Icons.Generate;
					}

				}

				@Order(3000)
				public class DownloadIosButton extends AbstractLinkButton {

					@Override
					protected boolean getConfiguredLabelVisible() {
						return false;
					}

					@Override
					protected String getConfiguredIconId() {
						return Icons.DownLoad;
					}

					@Override
					protected void execClickAction() {
					}

					@Override
					protected boolean getConfiguredVisible() {
						return false;
					}

					@Override
					protected boolean getConfiguredProcessButton() {
						return false;
					}

					@Override
					protected String getConfiguredBackgroundColor() {
						return "5C5C5C";
					}

					@Override
					protected String getConfiguredTooltipText() {
						return TEXTS.get("DownloadForIOS");
					}

				}
			}

			@Order(3000)
			public class AndroidBox extends AbstractGroupBox {

				@Override
				protected int getConfiguredHeightInPixel() {
					return 30;
				}

				@Override
				protected int getConfiguredGridColumnCount() {
					return 2;
				}

				@Override
				protected int getConfiguredGridW() {
					return 1;
				}

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Order(1000)
				@FormData(sdkCommand = FormData.SdkCommand.IGNORE)
				public class AndroidField extends AbstractLabelField {

					@Override
					protected int getConfiguredLabelWidthInPixel() {
						return 100;
					}

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Android") + ":";
					}

					@Override
					protected boolean getConfiguredVisible() {
						return false;
					}

					@Override
					protected boolean getConfiguredStatusVisible() {
						return false;
					}

				}

				@Order(2000)
				public class AndroidIconField extends AbstractImageField {

					@Override
					protected int getConfiguredLabelWidthInPixel() {
						return 100;
					}

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Android") + ":";
					}

					@Override
					protected int getConfiguredHorizontalAlignment() {
						return -1;
					}

					@Override
					protected String getConfiguredImageId() {
						return Icons.Generate;
					}

					@Override
					protected boolean getConfiguredGridUseUiWidth() {
						return true;
					}

				}

				@Order(3000)
				public class DownloadAndroidButton extends AbstractLinkButton {

					@Override
					protected boolean getConfiguredLabelVisible() {
						return false;
					}

					@Override
					protected String getConfiguredIconId() {
						return Icons.DownLoad;
					}

					@Override
					protected void execClickAction() {

						RemoteFile file = BEANS.get(IRemoteFileService.class).getRemoteFile(
								new RemoteFile(ClientSession.get().getUserId() + "/apk", getApkName(), 0L));
						try {
							ClientSessionProvider.currentSession().getDesktop().openUri(
									new BinaryResource(file.getName(), file.extractData()), OpenUriAction.DOWNLOAD);
						} catch (Exception e) {
							MessageBoxes.create().withHeader(TEXTS.get("Error"))
									.withBody(TEXTS.get("DownloadFailedPleaseContactTheAdministrator")).withCancelButtonText("CloseButton")
									.show();
						}

					}

					@Override
					protected boolean getConfiguredVisible() {
						return false;
					}

					@Override
					protected boolean getConfiguredProcessButton() {
						return false;
					}

					@Override
					protected String getConfiguredBackgroundColor() {
						return "5C5C5C";
					}

					@Override
					protected String getConfiguredTooltipText() {
						return TEXTS.get("DownloadForAndroid");
					}

				}

				@Order(4000)
				public class ErrorField extends AbstractHtmlField {

					@Override
					protected boolean getConfiguredLabelVisible() {
						return false;
					}

					@Override
					protected boolean getConfiguredVisible() {
						return false;
					}

					@Override
					protected int getConfiguredHeightInPixel() {
						return 60;
					}

				}

			}

		}

		@Order(2000)
		public class RightBox extends AbstractGroupBox {

			@Override
			protected int getConfiguredGridColumnCount() {
				return 1;
			}

			@Override
			protected int getConfiguredGridW() {
				return 2;
			}

			@Order(10)
			public class ImageField extends AbstractImageField {

				@Override
				protected int getConfiguredLabelWidthInPixel() {
					return 100;
				}

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Override
				protected int getConfiguredGridW() {
					return 2;
				}

			}
		}

		@Order(3000)
		public class EditMenu extends AbstractMenu {

			@Override
			protected String getConfiguredIconId() {
				return Icons.Edit;
			}

			@Override
			protected void execAction() {
				AppNodePage page = (AppNodePage) getDesktop().getOutline().getSelectedNode();
				page.setTableVisible(true);
				doClose();
				String version = getAndroidField().getValue();
				String appname = getAppName();
				String appId = getAppId();
				CreateFormData selectAppVersion = BEANS.get(ICreateService.class).getAppVersionByAppId(version, appId);
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
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}

			@Override
			protected String getConfiguredTooltipText() {
				return TEXTS.get("Edit");
			}
		}

		@Order(4000)
		public class DeleteMenu extends AbstractMenu {

			@Override
			protected String getConfiguredIconId() {
				return Icons.DELETE;
			}

			@Override
			protected void execAction() {
				int show = MessageBoxes.createYesNo().withBody(TEXTS.get("AreYouSureToDeleteThisApp")).show();
				if (show == 0) {
					doClose();
					BEANS.get(ICreateService.class).deleteApp(getAppId());
					getDesktop().getOutline().removeNode(getDesktop().getOutline().getSelectedNode());
				}
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}

			@Override
			protected String getConfiguredTooltipText() {
				return TEXTS.get("DeleteMenu");
			}
		}

	}

	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			INotificationsService service = BEANS.get(INotificationsService.class);
			NotificationsFormData formData = new NotificationsFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			setEnabledPermission(new UpdateNotificationsPermission());
		}

		@Override
		protected void execStore() {
			INotificationsService service = BEANS.get(INotificationsService.class);
			NotificationsFormData formData = new NotificationsFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			INotificationsService service = BEANS.get(INotificationsService.class);
			NotificationsFormData formData = new NotificationsFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);

			setEnabledPermission(new CreateNotificationsPermission());
		}

		@Override
		protected void execStore() {
			INotificationsService service = BEANS.get(INotificationsService.class);
			NotificationsFormData formData = new NotificationsFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}
}
