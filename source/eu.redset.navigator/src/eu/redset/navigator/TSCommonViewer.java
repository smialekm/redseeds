package eu.redset.navigator;


import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.navigator.CommonViewer;


public class TSCommonViewer extends CommonViewer {
	


	public TSCommonViewer(String viewerId, Composite parent, int style) {
		super(viewerId, parent, style);
			
	}
	
	@Override
	protected void initDragAndDrop() {
		
	}
}
