package eu.redset.tsl.editor.editors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.NFTest;
import eu.redset.emf.model.tsl.Test;

public class TestDetailsViewControl extends Composite{

	private Test test;
	TestDetailsEditor editor;
	
	public TestDetailsViewControl(Composite parent, Test test, TestDetailsEditor editor) {
		super(parent, SWT.None);
		this.test = test;
		this.editor = editor;
		createContent();
	}
	

	private void createContent(){
		if (test instanceof NFTest){
			NFTestDetailesViewControl nfComp = new NFTestDetailesViewControl(this, editor, test); 
		}
	
	}
		
}
