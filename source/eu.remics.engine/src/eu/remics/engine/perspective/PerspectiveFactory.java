package eu.remics.engine.perspective;


import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import eu.remics.common.Constans;


public class PerspectiveFactory implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, 0.25f, editorArea);
		//topLeft.addView(Constans.BrowserViewID);
		//topLeft.addPlaceholder(Constans.BrowserViewID + ":*");
		topLeft.addView(Constans.SCNavigatorID);
		topLeft.addPlaceholder(Constans.SCNavigatorID + ":*");
		
		/*IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.5f, "topLeft");
		bottomLeft.addView(Constans.NotionViewID);
		bottomLeft.addView(Constans.UseCaseViewID);*/
		
		
		/*IFolderLayout centerTop = layout.createFolder("centerTop", IPageLayout.TOP, 0.65f, editorArea);
		centerTop.addPlaceholder(UCDiagramView.ID + ":*");
		centerTop.addView(UCDiagramView.ID);*/
		
		IFolderLayout centerBottom = layout.createFolder("centerBottom", IPageLayout.BOTTOM, 0.4f, editorArea);
		centerBottom.addPlaceholder(Constans.UnassignedScenarioListViewID + ":*");
		centerBottom.addView(Constans.UnassignedScenarioListViewID);
		
		/*IFolderLayout topRight = layout.createFolder("topRight", IPageLayout.RIGHT, 0.85f, "centerTop");
		topRight.addPlaceholder(Constans.NotionsMergingViewID + ":*");
		topRight.addView(Constans.NotionsMergingViewID);*/
		
		//layout.getViewLayout(Constans.BrowserViewID).setCloseable(false);
	}
}

