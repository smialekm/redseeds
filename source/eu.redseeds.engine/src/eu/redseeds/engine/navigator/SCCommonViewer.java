package eu.redseeds.engine.navigator;

import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.navigator.CommonViewer;

import eu.redseeds.engine.navigator.dnd.SCLElementDragListener;
import eu.redseeds.engine.navigator.dnd.SCLElementTransfer;
import eu.redseeds.engine.navigator.dnd.SCLElementTreeDropAdapter;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;

public class SCCommonViewer extends CommonViewer {
	
	public SCLElementDragListener dragListener;
	public DropTarget dropTarget;
	public SCLElementTreeDropAdapter dropAdapter;
	public boolean beenDragged = false;

	public SCCommonViewer(String viewerId, Composite parent, int style) {
		super(viewerId, parent, style);
		getTree().addMenuDetectListener(new MenuDetectListener() {
			
			@Override
			public void menuDetected(MenuDetectEvent e) {
				ITreeSelection selection = (ITreeSelection) getSelection();
				ConstrainedLanguageScenarioDTO domain = null;
				Object obj = selection.getFirstElement();
				if(obj instanceof ConstrainedLanguageScenarioDTO){
					domain = (ConstrainedLanguageScenarioDTO) obj;
				}
				Menu menu = getControl().getMenu();
				MenuItem[] mItems = menu.getItems();
				for(int i=0; i<mItems.length; i++){
					MenuItem subitem = mItems[i];
					if(subitem.getText().equals("Deassign scenario")){
						if(null != domain && MScenario.isDerivedScenario(domain) && domain.getParent().getConstrainedLanguageScenarioDTOList().indexOf(domain) != 0){
							subitem.setEnabled(true);
						}else{
							subitem.setEnabled(false);
						}
					}
				}
			}
		});
	}
	
	@Override
	protected void initDragAndDrop() {
		Transfer[] transfers = new Transfer[] { SCLElementTransfer.getInstance() };
		dropTarget = new DropTarget(getTree(), DND.DROP_MOVE);
		dropTarget.setTransfer(transfers);
		
		dropAdapter = new SCLElementTreeDropAdapter(this);
		dropTarget.addDropListener(dropAdapter);
		
		dragListener = new SCLElementDragListener(this);
		addDragSupport(DND.DROP_MOVE, transfers, dragListener);
		
		/*addDragSupport(DND.DROP_MOVE, transfers, new SCLElementDragListener(this));
		addDropSupport(DND.DROP_MOVE, transfers, new SCLElementTreeDropAdapter(this));*/
		
	}
}
