package eu.redseeds.common.preferences;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Widget;

import eu.redseeds.common.Constants;

public class ListFieldEditor extends FieldEditor {
	
	private List list;
	private SelectionListener selectionListener;
	private String stringToParse;
	private String[] selection = null;
	
	public ListFieldEditor(String name, String labelText, Composite parent) {
        super.init(name, labelText);
        super.createControl(parent);
    }
	
	@Override
	protected void adjustForNumColumns(int numColumns) {
		Control control = getLabelControl();
        ((GridData) control.getLayoutData()).horizontalSpan = numColumns;
        ((GridData) list.getLayoutData()).horizontalSpan = numColumns - 1;
	}

	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		Control control = getLabelControl(parent);
        GridData gd = new GridData();
        gd.horizontalSpan = numColumns;
        control.setLayoutData(gd);

        list = getListControl(parent);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.verticalAlignment = GridData.FILL;
        gd.horizontalSpan = numColumns - 1;
        gd.grabExcessHorizontalSpace = true;
        list.setLayoutData(gd);
	}
	
	public List getListControl(Composite parent) {
        if (list == null) {
            list = new List(parent, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL
                    | SWT.H_SCROLL);
            list.setFont(parent.getFont());
            list.addSelectionListener(getSelectionListener());
            list.addDisposeListener(new DisposeListener() {
                public void widgetDisposed(DisposeEvent event) {
                    list = null;
                }
            });
        } else {
            checkParent(list, parent);
        }
        return list;
    }
	
	private SelectionListener getSelectionListener() {
		if (selectionListener == null) {
			createSelectionListener();
		}
	    return selectionListener;
	}
	
	public void createSelectionListener() {
        selectionListener = new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
            	Widget widget = event.widget;
            	if (widget == list) {
            		selectionChanged();
            	}
            }
        };
    }
	
	protected void selectionChanged() {
		if(list.getSelection() != null && list.getSelectionCount() != 0){
			getPage().setErrorMessage(null);
			selection = list.getSelection();
		}
		else{
			getPage().setErrorMessage("Project must be selected!");
			selection = null;
		}
    }
	
	public String[] getSelection() {
		return selection;
	}
	
	public void resetSelection() {
		selection = null;
	}

	@Override
	protected void doLoad() {
		if (list != null) {
			String val = getPreferenceStore().getString(Constants.EXISTING_MODELIO_PROJECT);
			list.removeAll();
            String[] array = parseString();
            for (int i = 0; i < array.length; i++) {
            	list.add(array[i]);
            	if(i == array.length-1 && selection == null){
            		list.select(0);
            		selection = list.getSelection();
            	}
            	/*if(selection == null && val != null && val.equals(array[i])){
            		list.select(i);
            		selection = list.getSelection();
            	}*/
            }
        }
	}
	
	public void setStringToParse(String stringToParse) {
		this.stringToParse = stringToParse;
	}
	
	public String[] parseString() {
		java.util.List<String> strings = new ArrayList<String>();
		if(stringToParse != null){
			File workspace = new File(stringToParse);
	        File[] projects = workspace.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File f) {
					if(f.isDirectory() && !f.getName().equals(".config")){
						File[] ofpxProjs = f.listFiles(new FileFilter() {
							
							@Override
							public boolean accept(File pathname) {
								return pathname.getName().endsWith(".ofpx");
							}
						});
						if(ofpxProjs != null && ofpxProjs.length != 0){
							return true;
						}
					}
					return false;
				}
			});
	        
	        if(projects != null)
	        	for(int i = 0; i < projects.length; i++){
	        		strings.add(projects[i].getName());
	        }
		}
		else{
			list.removeAll();
		}
        
		return strings.toArray(new String[strings.size()]);
	}

	@Override
	protected void doLoadDefault() {
		doLoad();
	}

	@Override
	protected void doStore() {
		String s = createList(selection);
        if (s != null) {
			getPreferenceStore().setValue(getPreferenceName(), s);
		}
	}
	
	private String createList(String[] items) {
		return items != null && items.length != 0 ? items[0] : null;
	}

	@Override
	public int getNumberOfControls() {
		return 2;
	}
	
	public List getList() {
    	return list;
    }
	
	public void setEnabled(boolean enabled, Composite parent) {
        super.setEnabled(enabled, parent);
        getListControl(parent).setEnabled(enabled);
    }
}
