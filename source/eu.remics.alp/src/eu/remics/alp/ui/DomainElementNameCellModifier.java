package eu.remics.alp.ui;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

/**
 * Used as a table cell modifier for entering concrete domain elements' names
 * @author aambroziewicz
 *
 */
public class DomainElementNameCellModifier implements ICellModifier {
	
	protected TableViewer viewer;
	private boolean enabled;
	private ImportALPWizardDomainPage page;
	DomainInstantiationControl control;
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public DomainElementNameCellModifier(TableViewer viewer, DomainInstantiationControl control, ImportALPWizardDomainPage page) {
		this.viewer = viewer;
		this.enabled = true;
		this.page = page;
		this.control = control;
	}

	@Override
	public boolean canModify(Object element, String property) {
//		Activator.log("DomainElementNameCellModifier.canModify: " + element + " " + property, Status.INFO);
		if(enabled) {
			int column = getIndex(property);
			if(column == 0) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public Object getValue(Object element, String property) {
//		Activator.log("DomainElementNameCellModifier.getValue: " + element + " " + property, Status.INFO);
		if(getIndex(property) == 1){
			Object[] obj = (Object[])element;
			return (String)obj[1];
		}
		return null;
	}

	@Override
	public void modify(Object element, String property, Object value) {
//		Activator.log("DomainElementNameCellModifier.modify: " + element + " " + property + " " + value, Status.INFO);
		TableItem item = (TableItem)element;
		Object[] obj = (Object[])item.getData();
		obj[1] = value;
		item.setData(obj);
		viewer.update(item.getData(), null);
		page.setPageComplete(control.validateItems());
	}

	/**
	 * Finds the index of the column by given column name in the domain elements table
	 * @param columnName
	 * @return
	 */
	public static int getIndex(String columnName) {
		for (int i = 0; i < DomainInstantiationControl.columnProperties.length; i++) {
			if(columnName.equalsIgnoreCase(DomainInstantiationControl.columnProperties[i])) {
				return i;
			}
		}
		return -1; //unknown column
		

	}
}
