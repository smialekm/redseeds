package eu.redset.engine.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PerspectiveFactory implements IPerspectiveFactory {

	public static String ID_PROJECT_EXPLORER = "eu.redset.navigator.view";
	
    public PerspectiveFactory() {
        super();
    }

    /**
     * Defines the initial layout for a perspective.  
     *
     * Implementors of this method may add additional views to a
     * perspective.  The perspective already contains an editor folder
     * with <code>ID = ILayoutFactory.ID_EDITORS</code>.  Add additional views
     * to the perspective in reference to the editor folder.
     *
     * This method is only called when a new perspective is created.  If
     * an old perspective is restored from a persistence file then
     * this method is not called.
     *
     * @param layout the factory used to add views to the perspective
     */
    public void createInitialLayout(IPageLayout layout) {
        defineActions(layout);
        defineLayout(layout);
    }

    /**
     * Defines the initial actions for a page.  
     * @param layout The layout we are filling
     */
    public void defineActions(IPageLayout layout) {
        // Add "new wizards".
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");//$NON-NLS-1$
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");//$NON-NLS-1$

        // Add "show views".
        layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
        layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
        layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        layout.addShowViewShortcut(IPageLayout.ID_PROGRESS_VIEW);
        layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);

        //layout.addPerspectiveShortcut("org.eclipse.ui.resourcePerspective");
        layout.addPerspectiveShortcut("eu.redset.engine.perspective");
        
        layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
    }

    /**
     * Defines the initial layout for a page.  
     * @param layout The layout we are filling
     */
    public void defineLayout(IPageLayout layout) {
        // Editors are placed for free.
        String editorArea = layout.getEditorArea();

        // Top left.
        IFolderLayout topLeft = layout.createFolder(
                "topLeft", IPageLayout.LEFT, (float) 0.26, editorArea);

        topLeft.addView(ID_PROJECT_EXPLORER);
        //topLeft.addView(IPageLayout.ID_RES_NAV);
        topLeft.addPlaceholder(IPageLayout.ID_BOOKMARKS);

        // Add a placeholder for the old navigator to maintain compatibility
        topLeft.addPlaceholder("org.eclipse.ui.views.ResourceNavigator");

        // Bottom left.
        IFolderLayout bottomLeft = layout.createFolder(
                "bottomLeft", IPageLayout.BOTTOM, (float) 0.50,
                "topLeft");
        bottomLeft.addView(IPageLayout.ID_OUTLINE);

        // Bottom right.
		IFolderLayout bottomRight = layout.createFolder(
                "bottomRight", IPageLayout.BOTTOM, (float) 0.66,
                editorArea);

		bottomRight.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottomRight.addView(IPageLayout.ID_TASK_LIST);	
		bottomRight.addView("org.eclipse.ui.console.ConsoleView");
    }

}
