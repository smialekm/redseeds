package eu.remics.recovery.manager.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.remics.recovery.manager.applogic.ActionsFactory;
import eu.remics.recovery.model.preferences.MConfiguration;

public class MergeShifter {
	
	int color = SWT.COLOR_DARK_GREEN;
	private DetailedSimilarScenariosView mergeView;
	private TableViewer scenarioTable;
	private TableViewer alterScenarioTable;
	
	public MergeShifter(DetailedSimilarScenariosView mergeView) {
		this.mergeView = mergeView;
		this.scenarioTable = mergeView.getScenarioViewer();
		this.alterScenarioTable = mergeView.getSimilarScenarioViewer();
	}
	
	public void setShift(int[] shift){
		if(shift[0] == 1 && shift[1] == 0 && shift[2] == 1){
			mergeView.btnDown.setEnabled(false);
		}
		
		int max1=shift[0]+shift[2],max2=shift[0]+shift[2];
		if(shift[1] >= 0){
			doShift(alterScenarioTable, shift[1]);
			for(int i=shift[2]; i<max1 || i<max2; i++){
				if (i<max1){
					if(!MConfiguration.isCheckPrefixInvcationAndConditionSentences() && (scenarioTable.getElementAt(i) instanceof ConditionSentenceDTO || scenarioTable.getElementAt(i) instanceof InvocationSentenceDTO)){
						max1++;
						max2++;
						insertEmptyRow(alterScenarioTable, i);
					}
					else if(scenarioTable.getElementAt(i) == null){
		
					}
					else if(i < scenarioTable.getTable().getItemCount()){
						scenarioTable.getTable().getItem(i).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
					}
				}
				if (i<max2){
					if(!MConfiguration.isCheckPrefixInvcationAndConditionSentences() && (alterScenarioTable.getElementAt(i) instanceof ConditionSentenceDTO || alterScenarioTable.getElementAt(i) instanceof InvocationSentenceDTO)){
						max2++;
						max1++;
						insertEmptyRow(scenarioTable, i);
					}
					else if(alterScenarioTable.getElementAt(i) == null){
		
					}
					else if(i < alterScenarioTable.getTable().getItemCount()){
						alterScenarioTable.getTable().getItem(i).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
					}
				}
			}
		}
	}
	
	private void doShift(TableViewer viewer, int shift){
		Object[] items = new Object[viewer.getTable().getItemCount()];
		for(int i=0; i<viewer.getTable().getItemCount(); i++){
			if(viewer.getElementAt(i) != null)
				items[i] = viewer.getElementAt(i);
		}
		for(int i=0; i<items.length; i++){
			if(items[i] != null)
				viewer.remove(items[i]);
		}
		
		viewer.getTable().setItemCount(viewer.getTable().getItemCount()+shift);
		
		for(int i=0; i<items.length; i++){
			if(items[i] != null)
				viewer.add(items[i]);
		}
	}
	
	private void insertEmptyRow(TableViewer viewer, int pos) {
		viewer.insert(null, pos);
	}
	
	public void selectFirstRows(int size){
		if(scenarioTable.getTable().getItemCount() == 2){
			mergeView.btnDown.setEnabled(false);
		}
		if(scenarioTable.getTable().getItemCount() != 0){
			for(int i=0; i < scenarioTable.getTable().getItemCount(); i++){
				if(scenarioTable.getElementAt(i) != null)
					scenarioTable.getTable().getItem(i).setBackground(null);
			}
			if(scenarioTable.getTable().getItem(0).getText().equalsIgnoreCase("precondition: ")){
				if(size == 1){
					//tabview.getViewer().add("");
					mergeView.btnUp.setEnabled(false);
					mergeView.btnDown.setEnabled(false);
					ActionsFactory.enableAction(ActionsFactory.MERGE);
					ActionsFactory.enableAction(ActionsFactory.MOVE);
					scenarioTable.insert(null, 1);
					scenarioTable.getTable().getItem(1).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(color));
				}
				else{
					scenarioTable.insert(null, 2);
					scenarioTable.getTable().getItem(2).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(color));
				}
			}
			else{
				scenarioTable.insert(null, 1);
				scenarioTable.getTable().getItem(1).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(color));
			}
		}
		
		if(alterScenarioTable.getTable().getItemCount() != 0){
			for(int i=0; i < alterScenarioTable.getTable().getItemCount(); i++){
				if(alterScenarioTable.getElementAt(i) != null)
					alterScenarioTable.getTable().getItem(i).setBackground(null);
			}
			moveOneRowDown(alterScenarioTable);
			if(scenarioTable.getTable().getItem(0).getText().equalsIgnoreCase("precondition: ")){
				if(size == 1){
					alterScenarioTable.getTable().getItem(1).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(color));
					mergeView.setReferencedStepNumber(1);
				}
				else{
					alterScenarioTable.insert(null, 1);
					alterScenarioTable.getTable().getItem(2).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(color));
					mergeView.setReferencedStepNumber(2);
				}
			}
			else{
				alterScenarioTable.getTable().getItem(1).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(color));
			}
		}
	}
	
	void moveOneRowUp(TableViewer viewer) {
		Object[] items = new Object[viewer.getTable().getItemCount()];
		for(int i=0; i<viewer.getTable().getItemCount(); i++){
			if(viewer.getElementAt(i) != null)
				items[i] = viewer.getElementAt(i);
		}
		for(int i=0; i<items.length; i++){
			if(items[i] != null)
				viewer.remove(items[i]);
		}
		
		viewer.getTable().setItemCount(viewer.getTable().getItemCount()-1);
		mergeView.setReferencedStepNumber(viewer.getTable().getItemCount());
		
		for(int i=0; i<items.length; i++){
			if(items[i] != null)
				viewer.add(items[i]);
		}
	}
	
	void moveOneRowDown(TableViewer viewer) {
		Object[] items = new Object[viewer.getTable().getItemCount()];
		for(int i=0; i<viewer.getTable().getItemCount(); i++){
			if(viewer.getElementAt(i) != null)
				items[i] = viewer.getElementAt(i);
		}
		for(int i=0; i<items.length; i++){
			if(items[i] != null)
				viewer.remove(items[i]);
		}
		
		viewer.getTable().setItemCount(viewer.getTable().getItemCount()+1);
		mergeView.setReferencedStepNumber(viewer.getTable().getItemCount());
		
		for(int i=0; i<items.length; i++){
			if(items[i] != null)
				viewer.add(items[i]);
		}
	}
	
	int countNullRows(TableViewer viewer) {
		int counter = 0;
		for(int i=0; i<viewer.getTable().getItemCount(); i++){
			if(viewer.getElementAt(i) == null){
				counter++;
			}
		}
		return counter;
	}
	
	void clearOnlySelection(TableViewer viewer) {
		for(int i=0; i<viewer.getTable().getItemCount(); i++){
			if(viewer.getTable().getItem(i).getBackground() != null){
				viewer.getTable().getItem(i).setBackground(null);
			}
		}
	}
	
	void clearSelectionAndNullRows(TableViewer viewer) {
		for(int i=0; i < viewer.getTable().getItemCount(); i++){
			if(viewer.getElementAt(i) == null){
				viewer.getTable().remove(i);
			}
		}
		for(int i=0; i < viewer.getTable().getItemCount(); i++){
			if(viewer.getTable().getItem(i).getBackground() != null){
				viewer.getTable().getItem(i).setBackground(null);
			}
		}
	}
	
	void syncTablesOnDown(TableViewer viewer, TableViewer viewer2, int counter) {
		if(viewer.getTable().getItemCount() > counter){
			for(int i=0; i<viewer.getTable().getItemCount(); i++){
				if(viewer.getElementAt(i) == null){
					viewer.getTable().remove(i);
				}
			}
			viewer.insert(null, counter);
			viewer.getTable().getItem(counter).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(color));
			viewer.getTable().getItem(counter-1).setBackground(null);
		}
		if(viewer2.getElementAt(counter) != null)
			viewer2.getTable().getItem(counter).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(color));
	}
	
	void syncTablesOnUp(TableViewer viewer, TableViewer viewer2, int counter) {
		if(counter < viewer.getTable().getItemCount()-1){
			for(int i=0; i<viewer.getTable().getItemCount(); i++){
				if(viewer.getElementAt(i) == null){
					viewer.getTable().remove(i);
				}
			}
			viewer.insert(null, counter);
			viewer.getTable().getItem(counter).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(color));
			viewer.getTable().getItem(counter+1).setBackground(null);
		}
		if(viewer2.getElementAt(counter) != null)
			viewer2.getTable().getItem(counter).setBackground(PlatformUI.getWorkbench().getDisplay().getSystemColor(color));
	}
}
