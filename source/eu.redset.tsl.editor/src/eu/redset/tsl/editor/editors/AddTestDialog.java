package eu.redset.tsl.editor.editors;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.ScenarioSentence;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.NFTest;




public class AddTestDialog extends Dialog {

	private Combo testTypesCombo;
	private Combo nfrTestsCombo;
	private GridData data;
	private Composite comp;
	private Text testNameText;
	private TSLBusinessLayerFacade tslFacade;
	private String[] nfItems;
	private Test parentTest;
	private NFTest selNFT = null;
	private RelatedTestsPanel parentComposite;
	private Shell shell;
	private Label parentTestLabel;
	private Text parentTestText;
	private String type = "";
	private Label domainStatementDescLabel;
	
	public AddTestDialog(Shell parentShell, Test parentTest, RelatedTestsPanel parentComposite) {
		super(parentShell);
		this.shell = parentShell;
		this.parentComposite = parentComposite;
		this.parentTest = parentTest;
		this.tslFacade = SCProjectContainer.instance().getSCProject(parentTest).getTestSpecificationContainer().getTSLFacade(parentTest);
	}

	protected Control createDialogArea(Composite parent) {
		getShell().setText("Add Test");
	    comp = (Composite) super.createDialogArea(parent);
	    GridLayout layout = (GridLayout) comp.getLayout();
	    layout.numColumns = 2;

	    parentTestLabel = new Label(comp, SWT.RIGHT);
	    parentTestLabel.setText("Parent test: ");
	    data = new GridData(65, 20);
	    parentTestLabel.setLayoutData(data);
	    
	    parentTestText = new Text(comp, SWT.LEFT | SWT.BORDER);
	    if (parentTest instanceof Notion)
	    	parentTestText.setText(((Notion)parentTest).getNotionName());
	    else
	    	parentTestText.setText(parentTest.getName());
	    data = new GridData(305, 20);
	    parentTestText.setLayoutData(data);
	    parentTestText.setEditable(false);
	    
	    Label domainStatementNameLabel = new Label(comp, SWT.RIGHT);
	    domainStatementNameLabel.setText("Type: ");
	    data = new GridData(65, 20);
	    domainStatementNameLabel.setLayoutData(data);

	    testTypesCombo = new Combo(comp, SWT.READ_ONLY);
	    data = new GridData(290, 20);
	    testTypesCombo.setLayoutData(data);
	    //testTypesCombo.setBounds(50, 50, 150, 65);
	    testTypesCombo.setEnabled(true);
	    String items[] = {"GUI Test", "Non-functional Test", "Business Logic Test"};
	    testTypesCombo.setItems(items);
	    testTypesCombo.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if (testTypesCombo.getText().equals("GUI Test")) {
	        	if (type.equals("Non-functional Test")){
	        		nfrTestsCombo.dispose();
	        		domainStatementDescLabel.dispose();
	        	}
	        	type = "GUI Test";
	        	testNameText.setEnabled(true);
	        	comp.layout();
	        } else if (testTypesCombo.getText().equals("Non-functional Test") && !type.equals("Non-functional Test")) {
	        	type = "Non-functional Test";
	        	testNameText.setEnabled(true);
	        	comp.layout();
	        	data = new GridData(65, 20);
	    	    domainStatementDescLabel = new Label(comp, SWT.RIGHT | SWT.TOP);
	    	    domainStatementDescLabel.setText("NFR Test: ");
	    	    domainStatementDescLabel.setLayoutData(data);
	    	    
	    	    nfrTestsCombo = new Combo(comp, SWT.READ_ONLY);
	    	    data = new GridData(290, 20);
	    	    nfrTestsCombo.setLayoutData(data);
	    	    List nfTests = tslFacade.getAllNFTests();
	    	    nfItems = new String[nfTests.size()]; 
	    	    
	    	    for (int i=0; i<nfTests.size(); i++){
	    	    	nfItems[i]=((NFTest)nfTests.get(i)).getName();
	    	    }
	    	    
	    	    nfrTestsCombo.setItems(nfItems);
	    	    nfrTestsCombo.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						 selNFT = tslFacade.getNFTestByName(nfItems[nfrTestsCombo.getSelectionIndex()]);
						testNameText.setText(selNFT.getName());
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
	    	    
	    	    comp.layout();
	        } else if (testTypesCombo.getText().equals("Business Logic Test") && !type.equals("Business Logic Test")) {
	        	if (type.equals("Non-functional Test")){
	        		nfrTestsCombo.dispose();
	        		domainStatementDescLabel.dispose();
	        	}
	        	type = "Business Logic Test";
	        	testNameText.setEnabled(true);
	        	comp.layout();
	        }

	      }
	      
	    });

	    data = new GridData(65, 20);
	    Label testNameLabel = new Label(comp, SWT.RIGHT);
	    testNameLabel.setText("Name: ");
	    testNameLabel.setLayoutData(data);

	    data = new GridData(305, 20);
	    testNameText = new Text(comp, SWT.LEFT | SWT.BORDER);
	    //testNameText.setBounds(0,0,250, 10);
	    testNameText.setText("");
	    testNameText.setLayoutData(data);
	    testNameText.setEnabled(false);
	    testNameText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if (testNameText.getText() == null)
					getButton(OK).setEnabled(false);
				else{
					if (testNameText.getText().equals(""))
						getButton(OK).setEnabled(false);
					else
						getButton(OK).setEnabled(true);
				}
			}
		});
	    
	    
	    return comp;
	  }


	  @Override
	  protected void okPressed() {

		if (testTypesCombo.getText().equals("Non-functional Test")) {
			if (selNFT != null) {
				if (parentTest instanceof SVOSentence) {
					SVOSentence svo = (SVOSentence) parentTest;
					if (svo.getPredicate() != null)
						tslFacade.createTestRelationship(svo.getPredicate(), selNFT);
				}
			} else 
				tslFacade.createTestRelationship(parentTest, selNFT);
		} else if (testTypesCombo.getText().equals("Business Logic Test")){
			BLTest blTest = tslFacade.createBLTest(tslFacade.getBLTestsPackage(), testNameText.getText());
			if (blTest == null){
				MessageBox mb = new MessageBox(shell);
				mb.setMessage("Business logic test can not be createad.");
				mb.open();
			} else{
			if (parentTest instanceof SVOSentence) {
					SVOSentence svo = (SVOSentence) parentTest;
					if (svo.getPredicate() != null) {
						tslFacade.createTestRelationship(svo.getPredicate(), blTest);
					}
				} else
					tslFacade.createTestRelationship(parentTest, blTest);
			}
		  } else if (testTypesCombo.getText().equals("GUI Test")){
			  GUITest guiTest = tslFacade.createGUITest(tslFacade.getGUITestsPackage(), testNameText.getText());
			  if (parentTest instanceof SVOSentence){
				  SVOSentence svo = (SVOSentence)parentTest;
				  if (svo.getPredicate() != null){
					  tslFacade.createTestRelationship(svo.getPredicate(), guiTest);
				  }
			  } else
				  tslFacade.createTestRelationship(parentTest, guiTest);
		  }
		  SCProjectContainer.instance().getSCProject(parentTest).saveTS(parentTest);
		  SCProjectHelper.refreshTSNavigator();
		  //parentComposite.layout();
		  parentComposite.refresh();
		  //parentComposite.dispose();
		  
		  this.close();
	  }

	  //public void setOkButtonDisabled(){
		//  getButton(OK).setEnabled(false);
	  //}

	@Override
	protected Point getInitialSize() {
	        return new Point(415, 250);
	    }
	
	}
