package eu.remics.recovery.manager.views;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import eu.redseeds.engine.navigator.dnd.SCLElementDragListener;
import eu.redseeds.engine.navigator.dnd.SCLElementTransfer;
import eu.redseeds.engine.navigator.dnd.SCLElementTreeDropAdapter;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.common.TableView;
import eu.remics.common.bindings.ICommandIds;
import eu.remics.recovery.manager.applogic.AMain;
import eu.remics.recovery.manager.applogic.ActionsFactory;
import eu.remics.recovery.model.domainlogic.usecases.MUnassignedScenariosList;
import eu.remics.util.SCNavigatorHelper;

public class UnassignedScenariosView extends ViewPart {
	TableView tabview = new TableView();
	String[] columnTitles = {"Name", "Invoked by", "Invokes", "Test script name", "Test script file"};
	public SCLElementDragListener dragListener;
	Transfer[] transfers;
	private SCLElementTreeDropAdapter dropListener;

	@Override
	public void createPartControl(Composite parent) {
		tabview.createViewer(new Composite(parent,SWT.BORDER), getSite(), columnTitles);
		transfers = new Transfer[] { SCLElementTransfer.getInstance() };
		
		dragListener = new SCLElementDragListener(getUnassignedScenarioListViewer());
		getUnassignedScenarioListViewer().addDragSupport(DND.DROP_MOVE, transfers, dragListener);
		
		dropListener = new SCLElementTreeDropAdapter(getUnassignedScenarioListViewer()); 
		getUnassignedScenarioListViewer().addDropSupport(DND.DROP_MOVE, transfers, dropListener);
		
		/*
		 * Getting selected scenarios from unassigned scenarios list
		 */
		this.getUnassignedScenarioListViewer().addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event) {
				if(event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					ArrayList<UseCaseDTO> scenarioList = new ArrayList<UseCaseDTO>();
					for (Iterator<?> iterator = selection.iterator(); iterator.hasNext();) {
						UseCaseDTO dto = (UseCaseDTO)iterator.next();
						
						scenarioList.add(dto);
						AMain.cAssignScenarioToUseCase.setScenario(dto);
						AMain.cSplitScenario.setScenario(dto);
						
						ArrayList<String> actionsName = new ArrayList<String>();
						actionsName.add(ActionsFactory.ATTACH_UC);
						actionsName.add(ActionsFactory.CREATE);
						actionsName.add(ActionsFactory.DELETE);
						actionsName.add(ActionsFactory.SPLIT);
						actionsName.add(ActionsFactory.PREVIEW);
						for(String actionName : actionsName){
							if(MUnassignedScenariosList.getUnnasignedScenarios().isEmpty()){
								ActionsFactory.disableAction(actionName);
							}
							ActionsFactory.enableAction(actionName);
						}
						
					}
					AMain.cDeleteScenario.setScenarioList(scenarioList);
					AMain.cCreateUseCaseFromUnassignedScenario.setScenarioList(scenarioList);
					AMain.cPreviewScenario.setScenarioList(scenarioList);
				}
				if(SCNavigatorHelper.getViewer().beenDragged){
					disableButtons();
					SCNavigatorHelper.getViewer().beenDragged = false;
				}
				
				/*
				 * handling dynamic change of dragging from unassigned list to navigator
				 */
				if(SCNavigatorHelper.getViewer() != null && SCNavigatorHelper.getViewer().dropTarget != null){
					for(DropTargetListener a : SCNavigatorHelper.getViewer().dropTarget.getDropListeners())
						SCNavigatorHelper.getViewer().dropTarget.removeDropListener((SCLElementTreeDropAdapter)a);
					
					SCNavigatorHelper.getViewer().dropTarget.addDropListener(dropListener);
				}
			}
		});
		
		//Create toolbar
		try {
			ActionsFactory.createAction(this, AMain.cPreviewScenario, AMain.cPreviewScenario.getClass().getMethod("Preview",  (Class<?>[])null), ActionsFactory.PREVIEW, ICommandIds.CMD_PREVIEW_SCENARIO);
		} 
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
		try {
			ActionsFactory.createAction(this, AMain.cAssignScenarioToUseCase, AMain.cAssignScenarioToUseCase.getClass().getMethod("ClicksAssignScenarioButton", (Class<?>[])null), ActionsFactory.ATTACH_UC, ICommandIds.CMD_ASSIGN_TO_USE_CASE);
			RecoveryManagerHelper.setCAssignScenarioToUseCaseObject(AMain.cAssignScenarioToUseCase);
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
		try {
			ActionsFactory.createAction(this, AMain.cCreateUseCaseFromUnassignedScenario, AMain.cCreateUseCaseFromUnassignedScenario.getClass().getMethod("ClicksCreateNewUseCaseOption", (Class<?>[])null), ActionsFactory.CREATE, ICommandIds.CMD_CREATE_NEW_USE_CASE);
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
		try {
			ActionsFactory.createAction(this, AMain.cSplitScenario, AMain.cSplitScenario.getClass().getMethod("ClicksSplitScenario", (Class<?>[])null), ActionsFactory.SPLIT, ICommandIds.CMD_SPLIT_SCENARIO);
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
		try {
			ActionsFactory.createAction(this, AMain.cDeleteScenario, AMain.cDeleteScenario.getClass().getMethod("_ClicksDeleteScenarioOption", (Class<?>[])null), ActionsFactory.DELETE, ICommandIds.CMD_DELETE_SCENARIO);
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		tabview.getViewer().getControl().setFocus();
	}

	public TableViewer getUnassignedScenarioListViewer() {
		return tabview.getViewer();
	}
	
	public void disableButtons(){
		if(this != null){
			refresh();
		}
		if(MUnassignedScenariosList.getUnnasignedScenarios().isEmpty()){
			ActionsFactory.disableAction(ActionsFactory.ATTACH_UC);
			ActionsFactory.disableAction(ActionsFactory.CREATE);
			ActionsFactory.disableAction(ActionsFactory.DELETE);
			ActionsFactory.disableAction(ActionsFactory.SPLIT);
		}
		else{
			for(int i=0; i<getUnassignedScenarioListViewer().getTable().getItemCount(); i++){
				if(!getUnassignedScenarioListViewer().getTable().isSelected(i)){
					ActionsFactory.disableAction(ActionsFactory.ATTACH_UC);
					ActionsFactory.disableAction(ActionsFactory.CREATE);
					ActionsFactory.disableAction(ActionsFactory.DELETE);
					ActionsFactory.disableAction(ActionsFactory.SPLIT);
				}
				else{
					ActionsFactory.enableAction(ActionsFactory.ATTACH_UC);
					ActionsFactory.enableAction(ActionsFactory.CREATE);
					ActionsFactory.enableAction(ActionsFactory.DELETE);
					ActionsFactory.enableAction(ActionsFactory.SPLIT);
				}
			}
		}
	}
	
	public void refresh(){
		if(MUnassignedScenariosList.getUnnasignedScenarios() != null)
			getUnassignedScenarioListViewer().setInput(MUnassignedScenariosList.getUnnasignedScenarios());
	}

}