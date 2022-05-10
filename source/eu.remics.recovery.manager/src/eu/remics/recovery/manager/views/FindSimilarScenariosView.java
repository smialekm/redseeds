package eu.remics.recovery.manager.views;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import eu.remics.common.TableView;
import eu.remics.common.bindings.ICommandIds;
import eu.remics.recovery.manager.applogic.AMain;
import eu.remics.recovery.manager.applogic.ActionsFactory;
import eu.remics.recovery.model.dto.XScenariosCommonPart;

public class FindSimilarScenariosView extends ViewPart {

	private TableView tabview = new TableView();
	private String[] columnTitles = {"Scenario name", "Similar scenario name", "Longest common part"};
	
	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 0;
		layout.marginHeight = 2;
		parent.setLayout(layout);
		tabview.createViewer(new Composite(parent,SWT.BORDER), getSite(), columnTitles );
		
		tabview.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ActionsFactory.enableAction("Show in detail");
				IStructuredSelection select = (IStructuredSelection) event.getSelection();
				if(select.getFirstElement() instanceof XScenariosCommonPart){
					XScenariosCommonPart similar = (XScenariosCommonPart) select.getFirstElement();
					AMain.cFindsimilarScenarios.setSimilarScenarios(similar);
				}
			}
		});
		
		try {
			ActionsFactory.createAction(this, AMain.cFindsimilarScenarios, AMain.cFindsimilarScenarios.getClass().getMethod("ShowInDetail",  (Class<?>[])null), "Show in detail", ICommandIds.CMD_SIMILAR_SCENARIO_DETAIL);
		} 
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setFocus() {
	}
	
	public TableViewer getViewer(){
		return tabview.getViewer();
	}

}
