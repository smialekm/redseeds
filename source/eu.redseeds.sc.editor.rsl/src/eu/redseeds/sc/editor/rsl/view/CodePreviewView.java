package eu.redseeds.sc.editor.rsl.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Parameter;
import eu.redseeds.scl.uml.classes.kernel.ParameterDirectionKind;
import eu.redseeds.scl.uml.classes.kernel.Property;
import eu.redseeds.scl.uml.classes.kernel.VisibilityKind;

public class CodePreviewView extends ViewPart {

	ScrolledComposite scrolledPanel;
	Composite codePanel;
	StyledText code;
	
	public CodePreviewView() {}

	@Override
	public void createPartControl(Composite parent) {
		parent.setBackground(new Color(null, 255, 255, 255));
		parent.setLayout(new FillLayout());
		
		scrolledPanel = new ScrolledComposite(parent,SWT.V_SCROLL | SWT.H_SCROLL);
		scrolledPanel.setBackground(new Color(null, 255, 255, 255));
		
		codePanel = new Composite(scrolledPanel, SWT.NONE);
		codePanel.setBackground(new Color(null, 255, 255, 255));
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace=true;
		gridData.grabExcessVerticalSpace=true;
		codePanel.setLayoutData(gridData);
		codePanel.setLayout(new GridLayout());
		
		code = new StyledText(codePanel, SWT.MULTI | SWT.READ_ONLY);
		code.setBackground(new Color(null, 255, 255, 255));
		
		scrolledPanel.setContent(codePanel);
		scrolledPanel.setExpandVertical(true);
		scrolledPanel.setExpandHorizontal(true);
	}
	
	public void setInput(Operation operation) {
		code.setText(getOperationText(operation));
		codePanel.layout();
		scrolledPanel.setMinHeight(code.getBounds().height+2*((GridLayout) codePanel.getLayout()).marginHeight);
		scrolledPanel.setMinWidth(code.getBounds().width+2*((GridLayout) codePanel.getLayout()).marginWidth);
	}
	
	public void setInput(Class umlClass) {
		String text = "public class "+umlClass.getName()+" {\n\t\n";
		for(Property attr:umlClass.getOwnedAttributeList()){
			VisibilityKind avis = attr.getVisibility();
			String savis ="";
			if (VisibilityKind.PUBLIC.equals(avis)) savis = "public ";
			else if (VisibilityKind.PRIVATE.equals(avis)) savis = "private ";
			else if (VisibilityKind.PROTECTED.equals(avis)) savis = "protected ";
			text+="\t"+savis+attr.getTypeList().get(0).getName()+" "+attr.getName()+";\n";
		}
		text+="\t\n";
		for (Operation op:umlClass.getOwnedOperationList())
			text+="\t"+getOperationText(op).replace("\n", "\n\t")+"\n\t\n";
		text+="}";
		code.setText(text);
		codePanel.layout();
		scrolledPanel.setMinHeight(code.getBounds().height+2*((GridLayout) codePanel.getLayout()).marginHeight);
		scrolledPanel.setMinWidth(code.getBounds().width+2*((GridLayout) codePanel.getLayout()).marginWidth);
	}
	
	private String getOperationText(Operation operation){
		String vis = ((OperationDTO) operation).getVisibilityString();
		if ("+".equals(vis)) vis = "public ";
		else if ("-".equals(vis)) vis = "private ";
		else if ("#".equals(vis)) vis = "protected ";
		else vis = "";
		String ret="void ";
		for (Parameter par:operation.getOwnedParameterList()) if (ParameterDirectionKind.RETURN.equals(par.getDirection()))
			ret=par.getTypeList().get(0).getName()+" ";
		String text=vis+(operation.isIsStatic()?"static ":"")+ret+operation.getName().substring(1,operation.getName().indexOf("("))+"(";
		for (Parameter par:operation.getOwnedParameterList()) if (!ParameterDirectionKind.RETURN.equals(par.getDirection())){
			if ('('!=text.charAt(text.length()-1))
				text+=", ";
			text+=par.getTypeList().get(0).getName()+" "+par.getName();
		}
		text+="){\n\t"+operation.getOwnedCommentList().get(0).getBody().substring(7,'\n'==operation.getOwnedCommentList().get(0).getBody().charAt(operation.getOwnedCommentList().get(0).getBody().length()-1)?operation.getOwnedCommentList().get(0).getBody().length()-1:operation.getOwnedCommentList().get(0).getBody().length()).replace("\n", "\n\t")+"\n}";
		return text;
	}

	@Override
	public void setFocus() {}

}
