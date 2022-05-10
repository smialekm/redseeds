package eu.remics.recovery.manager.views;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import eu.remics.common.TableView;
import eu.remics.common.bindings.ICommandIds;
import eu.remics.recovery.manager.applogic.AMain;
import eu.remics.recovery.manager.applogic.ActionsFactory;


public class SplitScenarioView extends ViewPart {

	private TableView tabview = new TableView();
	private String[] columnTitles = {"Splitted scenario"};
	Button btnUp;
	Button btnDown;
	Text name;
	
	@Override
	public void createPartControl(Composite parent) {
		createControls(parent);
		tabview.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ActionsFactory.enableAction("Split");
				IStructuredSelection select = (IStructuredSelection) event.getSelection();
				if(!tabview.getViewer().getElementAt(0).equals(select.getFirstElement())){
					if(select.size() == 1){
						tabview.getViewer().getTable().select(0, tabview.getViewer().getTable().getSelectionIndex());
					}
					tabview.getViewer().getTable().select(0, tabview.getViewer().getTable().getSelectionIndex());
					if(tabview.getViewer().getTable().getSelectionIndex() == tabview.getViewer().getTable().getItemCount()-1){
						tabview.getViewer().getTable().deselect(tabview.getViewer().getTable().getSelectionIndex());
					}
				}
				AMain.cSplitScenario.setSplitNumber(tabview.getViewer().getTable().getSelectionIndex()+1);
			}
		});
		tabview.getViewer().getTable().addListener(SWT.EraseItem, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				event.detail &= ~SWT.HOT;
				if((event.detail & SWT.SELECTED) == 0) return;
				int clientWidth = tabview.getViewer().getTable().getClientArea().width;
				GC gc = event.gc;
				Color oldForeground = gc.getForeground();
				Color oldBackground = gc.getBackground();
				gc.setForeground(PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_DARK_CYAN));
				gc.setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_DARK_CYAN));
				gc.fillGradientRectangle(0, event.y, clientWidth, event.height, false);
				gc.setForeground(oldForeground);
				gc.setBackground(oldBackground);
				event.detail &= ~SWT.SELECTED;
			}
		});
		try {
			ActionsFactory.createAction(this, AMain.cSplitScenario, AMain.cSplitScenario.getClass().getMethod("Split",  (Class<?>[])null), "Split", ICommandIds.CMD_SPLIT_SCENARIO);
		} 
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	private void createControls(Composite parent){
		Group controls = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		controls.setLayout(gridLayout);
		controls.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		
		Label lb = new Label(controls, SWT.NONE);
		lb.setText("Name for second scenario: ");
		name = new Text(controls, SWT.BORDER);
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 0;
		layout.marginHeight = 2;
		parent.setLayout(layout);
		
		tabview.createViewer(new Composite(parent,SWT.BORDER), getSite(), columnTitles );
	}

	@Override
	public void setFocus() {
	}
	
	public TableViewer getViewer(){
		return tabview.getViewer();
	}
	
	public String getReferenceScenarioName(){
		return name.getText();
	}

}
