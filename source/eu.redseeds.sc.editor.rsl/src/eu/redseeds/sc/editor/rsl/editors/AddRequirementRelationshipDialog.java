package eu.redseeds.sc.editor.rsl.editors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.NonInvocationRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.model.dto.XScenariosCommonPart;

public class AddRequirementRelationshipDialog extends Dialog {

	private Shell shell;
	private BusinessLayerFacade facade;
	private RequirementDTO requirement;
	private RequirementDTO requirement2;
	
	private boolean dragAndDrop;
	private Label lbl1;
	private Label lbl1a;
	private Label lbl2;
	private Label lbl3;
	private ImageCombo reqCombo;
	private ImageCombo reqCombo2;
	private Combo relCombo;
	private Combo dirCombo;
	private Button btnAdd;
	private Button btnCancel;
	private Button btnMerge;
	private int returnValue = SWT.NONE;
	
	private Map<Integer, String> reqIndices2Types = new HashMap<Integer, String>();
	private Map<Integer, String> reqIndices2Types2 = new HashMap<Integer, String>();

	
	public AddRequirementRelationshipDialog(RequirementDTO req) {
		super(SCProjectHelper.getShell());
		shell = new Shell(SCProjectHelper.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(400, 200);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		shell.setLayout(layout);
		
		facade = SCProjectContainer.instance().getSCProject(req).getBusinessLayerFacade();
		this.requirement = req;
		
		dragAndDrop = false;
	}

	private ModifyListener modListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			
			if (!dragAndDrop) {
				if (reqCombo.getText().length() != 0){
					btnAdd.setEnabled(true);
				}
				else{
					btnAdd.setEnabled(false);
				}
			}
		}
	};
	
	public AddRequirementRelationshipDialog(RequirementDTO req1, RequirementDTO req2) {
		super(SCProjectHelper.getShell());
		shell = new Shell(SCProjectHelper.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(400, 200);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		shell.setLayout(layout);
		facade = SCProjectContainer.instance().getSCProject(req1).getBusinessLayerFacade();
		this.requirement = req1;
		this.requirement2 = req2;
		
		dragAndDrop = true;
	}
	
	public int open() {
		
		shell.setText("Add related requirement");
		createContent();
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()){
			if (!display.readAndDispatch())
				display.sleep();
		}
		return returnValue;
		
	}
	
	private void createContent() {
		if (!dragAndDrop) {

			lbl1 = new Label(shell, SWT.NONE);
			lbl1.setText("Choose requirement:");

			reqCombo = new ImageCombo(shell, SWT.READ_ONLY | SWT.BORDER);
			reqCombo.setEditable(false);
			reqCombo.addModifyListener(modListener);
			int index = 0;
			List<String> reqNames = facade.getAllRequirementsNames();
			for (String r : reqNames) {
				reqCombo.add(r, Activator.getDefault().
						createImageDescriptorFor(IImageKeys.REQUIREMENT).createImage());
				reqIndices2Types.put(index, "req");
				index++;
			}
			List<String> ucNames = facade.getAllUseCasesNames();
			for (String uc : ucNames) {
				UseCaseDTO usecase = facade.getUseCaseByName(uc);
				if(usecase.getConstrainedLanguageScenarioDTOList().isEmpty() && usecase.getParent() == null){
					continue;
				}
				if(uc.startsWith("~")){
					continue;
				}
				reqCombo.add(uc, Activator.getDefault().
						createImageDescriptorFor(IImageKeys.USE_CASE).createImage());
				reqIndices2Types.put(index, "uc");
				index++;
			}

		}
		else { // dragAndDrop = true
			
			// requirement1
			lbl1 = new Label(shell, SWT.NONE);
			lbl1.setText("Source requirement:");

			reqCombo = new ImageCombo(shell, SWT.READ_ONLY | SWT.BORDER);
			reqCombo.setEditable(false);
			
			int index = 0;
			
			if (requirement instanceof UseCaseDTO) {
				reqCombo.add(requirement.getName(), Activator.getDefault().
						createImageDescriptorFor(IImageKeys.USE_CASE).createImage());
				reqIndices2Types.put(index, "uc");
				
			}
			else { // !(requirement instanceof UseCaseDTO)
				reqCombo.add(requirement.getName(), Activator.getDefault().
						createImageDescriptorFor(IImageKeys.REQUIREMENT).createImage());
				reqIndices2Types.put(index, "req");
			}
			reqCombo.select(index);
			
			// requirement2
			
			lbl1a = new Label(shell, SWT.NONE);
			lbl1a.setText("Target requirement:");

			reqCombo2 = new ImageCombo(shell, SWT.READ_ONLY | SWT.BORDER);
			reqCombo2.setEditable(false);
			
			int index2 = 0;
			
			if (requirement2 instanceof UseCaseDTO) {
				reqCombo2.add(requirement2.getName(), Activator.getDefault().
						createImageDescriptorFor(IImageKeys.USE_CASE).createImage());
				
				reqIndices2Types2.put(index2, "uc");
			}
			else { // !(requirement instanceof UseCaseDTO)
				reqCombo2.add(requirement2.getName(), Activator.getDefault().
						createImageDescriptorFor(IImageKeys.REQUIREMENT).createImage());
				reqIndices2Types2.put(index2, "req");
			}
			reqCombo2.select(index2);
		}
		
		lbl2 = new Label(shell, SWT.NONE);
		lbl2.setText("Choose relationship type:");
		
		relCombo = new Combo(shell, SWT.READ_ONLY | SWT.BORDER);
		//relCombo.addModifyListener(modListener);
		relCombo.add("<<Conflicts with>>");
		relCombo.add("<<Constrains>>");
		relCombo.add("<<Depends on>>");
		relCombo.add("<<Derives from>>");
		relCombo.add("<<Elaborates>>");
		relCombo.add("<<Fulfils>>");
		relCombo.add("<<Is similar to>>");
		relCombo.add("<<Makes possible>>");
		relCombo.add("<<Operationalizes>>");
		relCombo.select(0);
		
		if (!dragAndDrop) {
			lbl3 = new Label(shell, SWT.NONE);
			lbl3.setText("Choose requirement role:");
		
			dirCombo = new Combo(shell, SWT.READ_ONLY | SWT.BORDER);
			dirCombo.add("source");
			dirCombo.add("target");
			dirCombo.select(0);
		}
		
		btnAdd = new Button(shell, SWT.PUSH);
		btnAdd.setText("Add");
		btnMerge = new Button(shell, SWT.PUSH);
		btnMerge.setText("Merge");
		
		if (!dragAndDrop){
			btnAdd.setEnabled(false);
			btnMerge.setVisible(false);
		}
		else if(!(requirement instanceof UseCaseDTO) || (requirement2 == null) || !(requirement2 instanceof UseCaseDTO)){
			btnMerge.setVisible(false);
		}
		
		btnCancel = new Button(shell, SWT.PUSH);
		btnCancel.setText("Cancel");
		
		Listener btnListener = new Listener() {
			public void handleEvent(Event e){
				if(e.widget == btnAdd) {
					
					// Add requirement relationship
					
					if (!dragAndDrop) {
						boolean isTarget = dirCombo.getText().equals("target");
						if (reqIndices2Types.get(reqCombo.getSelectionIndex()) == "req") {
							RequirementDTO r = facade.getRequirementByName(reqCombo.getText());
							if (r != null) {
								requirement.addRelatedRequirement(r, mapStr2RelType(relCombo.getText()), isTarget);
							}
						}
						else if (reqIndices2Types.get(reqCombo.getSelectionIndex()) == "uc") {
							UseCaseDTO r = facade.getUseCaseByName(reqCombo.getText());
							if (r != null) {
								requirement.addRelatedRequirement(r, mapStr2RelType(relCombo.getText()), isTarget);
							}
						}
					}
					else {
						requirement.addRelatedRequirement(requirement2, mapStr2RelType(relCombo.getText()), true);
					}
					
					returnValue = SWT.OK;
				}
				if(e.widget == btnCancel) {
					returnValue = SWT.CANCEL;
				}
				if(e.widget == btnMerge){
					if(requirement instanceof UseCaseDTO && requirement2 != null && requirement2 instanceof UseCaseDTO){
						/*
						 * Needed use of reflection
						 */
						Method method;
						try {
							method = RecoveryManagerHelper.getInstanceOfCMergeUseCases().getClass().getMethod("ShowUseCaseMergeView", (Class<?>[])null);
							Object classInvokedOn = RecoveryManagerHelper.getInstanceOfCMergeUseCases();
							XScenariosCommonPart similar = new XScenariosCommonPart(((UseCaseDTO)requirement2).getConstrainedLanguageScenarioDTOList().get(0),
									((UseCaseDTO)requirement).getConstrainedLanguageScenarioDTOList().get(0), 0);
							RecoveryManagerHelper.setSimilarScenarios(similar);
							method.invoke(classInvokedOn, (Object[])null);

						} catch (SecurityException e1) {
							e1.printStackTrace();
						} catch (NoSuchMethodException e1) {
							e1.printStackTrace();
						} catch (IllegalArgumentException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						} catch (InvocationTargetException e1) {
							e1.printStackTrace();
						}

						returnValue = SWT.OK;
					}
				}
				shell.close();
			}
		};
		
		btnAdd.addListener(SWT.Selection, btnListener);
		btnCancel.addListener(SWT.Selection, btnListener);
		btnMerge.addListener(SWT.Selection, btnListener);
		
		GridData data = new GridData();
		data.widthHint = 150;
		lbl1.setLayoutData(data);
		if (dragAndDrop)
			lbl1a.setLayoutData(data);
		
		data = new GridData();
		data.widthHint = 150;
		lbl2.setLayoutData(data);
		
		data = new GridData();
		data.widthHint = 150;
		if (!dragAndDrop)
			lbl3.setLayoutData(data);
		
		GridData data2 = new GridData(GridData.FILL_HORIZONTAL);
		reqCombo.setLayoutData(data2);
		if (dragAndDrop)
			reqCombo2.setLayoutData(data2);
		data2 = new GridData(GridData.FILL_HORIZONTAL);
		relCombo.setLayoutData(data2);
		if (!dragAndDrop) {
			data2 = new GridData(GridData.FILL_HORIZONTAL);
			dirCombo.setLayoutData(data2);
		}
		
		GridData data3 = new GridData();
		data3.horizontalSpan = 2;
		data3.widthHint = 50;
		btnAdd.setLayoutData(data3);
		data3.horizontalSpan = 2;
		btnCancel.setLayoutData(data3);
		data3.horizontalSpan = 2;
		btnMerge.setLayoutData(data3);
		
	}
	
	private int mapStr2RelType(String type) {
		
		if (type.equals("<<Conflicts with>>"))
			return NonInvocationRelationshipDTO.CONFLICTS_WITH;
		if (type.equals("<<Constrains>>"))
			return NonInvocationRelationshipDTO.CONSTRAINS;
		if (type.equals("<<Depends on>>"))
			return NonInvocationRelationshipDTO.DEPENDS_ON;
		if (type.equals("<<Derives from>>"))
			return NonInvocationRelationshipDTO.DERIVES_FROM;
		if (type.equals("<<Elaborates>>"))
			return NonInvocationRelationshipDTO.ELABORATES;
		if (type.equals("<<Fulfils>>"))
			return NonInvocationRelationshipDTO.FULFILS;
		if (type.equals("<<Is similar to>>"))
			return NonInvocationRelationshipDTO.IS_SIMILAR_TO;
		if (type.equals("<<Makes possible>>"))
			return NonInvocationRelationshipDTO.MAKES_POSSIBLE;
		if (type.equals("<<Operationalizes>>"))
			return NonInvocationRelationshipDTO.OPERATIONALIZES;
				
		return 0;
	}

}
