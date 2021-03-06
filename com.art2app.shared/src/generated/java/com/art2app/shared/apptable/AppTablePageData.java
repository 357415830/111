package com.art2app.shared.apptable;

import java.util.Date;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "com.art2app.client.apptable.AppTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class AppTablePageData extends AbstractTablePageData {

	private static final long serialVersionUID = 1L;

	@Override
	public AppTableRowData addRow() {
		return (AppTableRowData) super.addRow();
	}

	@Override
	public AppTableRowData addRow(int rowState) {
		return (AppTableRowData) super.addRow(rowState);
	}

	@Override
	public AppTableRowData createRow() {
		return new AppTableRowData();
	}

	@Override
	public Class<? extends AbstractTableRowData> getRowType() {
		return AppTableRowData.class;
	}

	@Override
	public AppTableRowData[] getRows() {
		return (AppTableRowData[]) super.getRows();
	}

	@Override
	public AppTableRowData rowAt(int index) {
		return (AppTableRowData) super.rowAt(index);
	}

	public void setRows(AppTableRowData[] rows) {
		super.setRows(rows);
	}

	public static class AppTableRowData extends AbstractTableRowData {

		private static final long serialVersionUID = 1L;
		public static final String store = "store";
		public static final String system = "system";
		public static final String version = "version";
		public static final String date = "date";
		public static final String download = "download";
		public static final String downloada = "downloada";
		public static final String upgrade = "upgrade";
		private String m_store;
		private String m_system;
		private String m_version;
		private Date m_date;
		private String m_download;
		private String m_downloada;
		private String m_upgrade;

		public String getStore() {
			return m_store;
		}

		public void setStore(String newStore) {
			m_store = newStore;
		}

		public String getSystem() {
			return m_system;
		}

		public void setSystem(String newSystem) {
			m_system = newSystem;
		}

		public String getVersion() {
			return m_version;
		}

		public void setVersion(String newVersion) {
			m_version = newVersion;
		}

		public Date getDate() {
			return m_date;
		}

		public void setDate(Date newDate) {
			m_date = newDate;
		}

		public String getDownload() {
			return m_download;
		}

		public void setDownload(String newDownload) {
			m_download = newDownload;
		}

		public String getDownloada() {
			return m_downloada;
		}

		public void setDownloada(String newDownloada) {
			m_downloada = newDownloada;
		}

		public String getUpgrade() {
			return m_upgrade;
		}

		public void setUpgrade(String newUpgrade) {
			m_upgrade = newUpgrade;
		}
	}
}
