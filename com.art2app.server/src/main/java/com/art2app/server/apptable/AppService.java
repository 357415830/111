package com.art2app.server.apptable;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

import com.art2app.server.sql.SQLs;
import com.art2app.shared.apptable.AppFormData;
import com.art2app.shared.apptable.AppTablePageData.AppTableRowData;
import com.art2app.shared.apptable.CreateAppPermission;
import com.art2app.shared.apptable.IAppService;
import com.art2app.shared.apptable.ReadAppPermission;
import com.art2app.shared.apptable.UpdateAppPermission;

public class AppService implements IAppService {
	
	private String name;
	private String user;
	
	@Override
	public AppTableRowData getAppTableData(SearchFilter filter) {
		AppTableRowData appTableRowData = new AppTableRowData();
		SQL.selectInto(SQLs.SELECT_APP_VERSION,new NVPair("appname", name),new NVPair("username", user), appTableRowData);
		return appTableRowData;
	}

	@Override
	public AppFormData prepareCreate(AppFormData formData) {
		if (!ACCESS.check(new CreateAppPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Mr.zhang] add business logic here.
		return formData;
	}

	@Override
	public AppFormData create(AppFormData formData) {
		if (!ACCESS.check(new CreateAppPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Mr.zhang] add business logic here.
		return formData;
	}

	@Override
	public AppFormData load(AppFormData formData) {
		if (!ACCESS.check(new ReadAppPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Mr.zhang] add business logic here.
		return formData;
	}

	@Override
	public AppFormData store(AppFormData formData) {
		if (!ACCESS.check(new UpdateAppPermission())) {
			throw new VetoException(TEXTS.get("AuthorizationFailed"));
		}
		// TODO [Mr.zhang] add business logic here.
		return formData;
}

	@Override
	public AppFormData setApp(String name, String user) {
		// TODO Auto-generated method stub
		this.name = name;
		this.user = user;
		return new AppFormData();
	}
}
