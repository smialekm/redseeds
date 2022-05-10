package eu.redseeds.sc.editor.sdsl.editors;

import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;

public class SDSLEditor extends EditorPart {
	
	public static String EDITOR_ID = "eu.redseeds.sc.editor.sdsl.editors.SDSLEditor";

	public SDSLEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(4, false));

		final Button editModelsInEAButton = new Button(parent, SWT.NONE);
		editModelsInEAButton.setText("Open models in EA");
//		editModelsInEAButton.setBounds(376, 255, 44, 23);
		editModelsInEAButton.addSelectionListener(
				new EditModelsInEA_SelectionListener());

		final Button getAnalysisFromEAButton = new Button(parent, SWT.NONE);
		getAnalysisFromEAButton.setText("Get Analysis from EA");
		getAnalysisFromEAButton.addSelectionListener(
				new GetAnalysisFromEA_SelectionListener());

		final Button getArchitectureFromEAButton = new Button(parent, SWT.NONE);
		getArchitectureFromEAButton.setText("Get Architecture from EA");
		getArchitectureFromEAButton.addSelectionListener(
				new GetArchitectureFromEA_SelectionListener());

		final Button getDetailedDesignFromEAButton = new Button(parent, SWT.NONE);
		getDetailedDesignFromEAButton.setText("Get Detailed Design from EA");
		getDetailedDesignFromEAButton.addSelectionListener(
				new GetDDesignFromEA_SelectionListener());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
