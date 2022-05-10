package eu.redset.tsl.editor.editors;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.part.ViewPart;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.BLTestInstance;
import eu.redset.emf.model.tsl.DomainObject;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.GUITestInstance;
import eu.redset.emf.model.tsl.NFTest;
import eu.redset.emf.model.tsl.NFTestInstance;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.Test;
import eu.redset.tsl.editor.IImageKeys;



public class TestDetailsEditor extends ViewPart implements ISaveablePart {

	private SCProject scProject;
	private TestDetailsViewControl testCaseDetailedControl;
	private NFTestDetailesViewControl nftControl;
	private GUITestDetailesViewControl guiControl;
	private BLTestDetailesViewControl bltControl;
	private NotionTestDetailesViewControl ntControl;
	
	private Test incomingObject;
	private boolean isDirty = false;
	private Composite parent;
	//private Object editor = null; 
	private ScrolledComposite sc;

	
	@Override
	public void createPartControl(Composite parent) {		
		this.parent = parent;
		parent.setLayout(new FillLayout());
		sc = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
	    
	}

	public void setContent(){
		if (incomingObject instanceof GUITest || incomingObject instanceof GUITestInstance){
			guiControl = new GUITestDetailesViewControl(sc, this, (Test)incomingObject);
			guiControl.setSize(630, 210);
			sc.setContent(guiControl);
			this.setTitleImage(ImageCache.getImage(IImageKeys.TEST));
		} else if (incomingObject instanceof NFTest || incomingObject instanceof NFTestInstance){
			nftControl = new NFTestDetailesViewControl(sc, this, (Test)incomingObject);
			nftControl.setSize(630, 210);
			sc.setContent(nftControl);
			this.setTitleImage(ImageCache.getImage(IImageKeys.TEST));
		} else if (incomingObject instanceof BLTest || incomingObject instanceof BLTestInstance){
			bltControl = new BLTestDetailesViewControl(sc, this, (Test)incomingObject);
			bltControl.setSize(630, 210);
			sc.setContent(bltControl);
			this.setTitleImage(ImageCache.getImage(IImageKeys.TEST));
		} else if (incomingObject instanceof Notion || incomingObject instanceof DomainObject){
			ntControl = new NotionTestDetailesViewControl(sc, this, (Test)incomingObject);
			ntControl.setSize(630, 210);
			sc.setContent(ntControl);
			this.setTitleImage(ImageCache.getImage(IImageKeys.NOTION));
		}
		
		
		parent.layout();
	}
	
	public void clean(){
		if (testCaseDetailedControl != null){
			testCaseDetailedControl.dispose();
		}
		/*
		if (verbPhraseControl != null){
			verbPhraseControl.dispose();
		}
		if (nounControl != null){
			nounControl.dispose();
		}
		*/
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	public void setIncomingObject(Test test){
			this.incomingObject = test;		
	}
	
	public Test getIncomingObject(){
		return incomingObject;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {		
		if (bltControl != null)
			bltControl.saveTest();
		if (guiControl != null)
			guiControl.saveTest();
		if (ntControl != null)
			ntControl.saveTest();
		if (nftControl != null)
			nftControl.saveTest();
		this.setDirty(false);
		// TODO Auto-generated method stub
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	public void setDirty(boolean isDirty){
		this.isDirty = isDirty;
		firePropertyChange(PROP_DIRTY);
	}
	
	@Override
	public boolean isDirty() {
		return isDirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public SCProject getScProject() {
		return scProject;
	}

	public void setScProject(SCProject scProject) {
		this.scProject = scProject;
	}
/*
	public Object getEditor() {
		return editor;
	}

	public void setEditor(Object editor) {
		this.editor = editor;
	}
	*/
}
