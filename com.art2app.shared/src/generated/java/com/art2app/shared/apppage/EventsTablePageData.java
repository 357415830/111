package com.art2app.shared.apppage;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "com.art2app.client.apppage.EventsTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class EventsTablePageData extends AbstractTablePageData {

	private static final long serialVersionUID = 1L;

	@Override
	public EventsTableRowData addRow() {
		return (EventsTableRowData) super.addRow();
	}

	@Override
	public EventsTableRowData addRow(int rowState) {
		return (EventsTableRowData) super.addRow(rowState);
	}

	@Override
	public EventsTableRowData createRow() {
		return new EventsTableRowData();
	}

	@Override
	public Class<? extends AbstractTableRowData> getRowType() {
		return EventsTableRowData.class;
	}

	@Override
	public EventsTableRowData[] getRows() {
		return (EventsTableRowData[]) super.getRows();
	}

	@Override
	public EventsTableRowData rowAt(int index) {
		return (EventsTableRowData) super.rowAt(index);
	}

	public void setRows(EventsTableRowData[] rows) {
		super.setRows(rows);
	}

	public static class EventsTableRowData extends AbstractTableRowData {

		private static final long serialVersionUID = 1L;
	}
}