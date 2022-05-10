package eu.remics.navigator.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;

public class BrowserView extends CommonNavigator {
	protected CommonViewer viewer;
	
	/*@Override
	protected XPackage getInitialInput(){
		return DummyModel.createDummyModel();
	}*/
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
	}
	
	@Override
	protected CommonViewer createCommonViewer(Composite parent){
		viewer = new CommonViewer(getViewSite().getId(), parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		initListeners(viewer);
		getSite().setSelectionProvider(viewer);
		
		//final XPackage treeRoot = DummyModel.createDummyModel();
		/*
		 * Setting root node in case of creating/updating use cases package in the tree.  
		 */
		//SelectedTreeItem.setSelectedTreeObject(treeRoot);
		//addSelectionListener();

		return viewer;
	}
	
	
}
