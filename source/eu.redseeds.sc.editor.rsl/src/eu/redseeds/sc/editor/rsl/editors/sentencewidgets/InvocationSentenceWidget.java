package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.UseCaseScenarioView;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class InvocationSentenceWidget extends Composite implements ScenarioSentenceWidget {

	private Label arrow;
	private Combo invokedUseCase;
	private UseCaseScenarioView scenarioView;

	private InvocationSentenceDTO sent;

	@Override
	public void refreshWidget() {
		if (sent.getOwningScenarios().size()>1)
			setInnerBackground(grey);
		else
			setInnerBackground(white);
		disableFields();
	}

	public InvocationSentenceWidget(Composite parent, int arg1,
			final InvocationSentenceDTO sent,final UseCaseScenarioView scenView) {
		super(parent, arg1);

		this.scenarioView = scenView;

		this.sent = sent;

		arrow = new Label(this,SWT.NONE);
		arrow.setText("=>invoke/"+sent.getInclusionType());

		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = false;

		arrow.setLayoutData(gridData);

		arrow.setBackground(new Color(null, 255, 255, 255));

		invokedUseCase = new Combo(this,SWT.READ_ONLY);
		// invokedUseCase.setText(sent.getInvokedUseCase().getName());

		List<String> usecasesNames = new ArrayList<String>();
		for(String ucname : scenarioView.getEditor().getFacade().getAllUseCasesNames()){
			UseCaseDTO usecase = scenarioView.getEditor().getFacade().getUseCaseByName(ucname);
			if(usecase.getConstrainedLanguageScenarioDTOList().isEmpty() && usecase.getParent() == null && !usecase.equals(sent.getInvokedUseCase())){
				continue;
			}
			if(ucname.startsWith("~")){
				continue;
			}
			else{
				usecasesNames.add(ucname);
			}
		}
		
		 //invokedUseCase.setItems(scenarioView.getEditor().getFacade().getAllUseCasesNames().toArray(new String[0]));
		invokedUseCase.setItems(usecasesNames.toArray(new String[0]));
		 if (sent.getInvokedUseCase()!=null)
			 invokedUseCase.select(invokedUseCase.indexOf(sent.getInvokedUseCase().getName()));

		// scenarioView.getEditor().getFacade().getUseCaseByName(name)



		 invokedUseCase.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				scenarioView.getEditor().backupScenariosIfNotDirty();
				UseCaseDTO uc = scenarioView.getEditor().getFacade().getUseCaseByName(invokedUseCase.getText());
				if (uc!=null)
					sent.setInvokedUseCase(uc);

				scenarioView.getEditor().setDirty(true);

			}});

		GridLayout gridLayout = new GridLayout();

		gridLayout.numColumns = 2;
		gridLayout.marginLeft = 5;
		gridLayout.marginRight = 5;
		gridLayout.marginBottom=0;
		gridLayout.marginTop=0;
		gridLayout.marginHeight=0;
		gridLayout.verticalSpacing=0;
		//gridLayout.makeColumnsEqualWidth=true;

		setLayout(gridLayout);
		setBackground(new Color(null, 255, 255, 255));

		GridData gridData1 = new GridData();

		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.verticalAlignment = GridData.FILL;

		gridData1.grabExcessHorizontalSpace = true;

		invokedUseCase.setLayoutData(gridData1);

		this.layout();

	}

/*	public void addKeyPressedListenener(KeyListener l){
		invokedUseCase.addKeyListener(l);
	}


	public void removeKeyPressedListenener(KeyListener l){
		invokedUseCase.removeKeyListener(l);
	}*/

	public void focus(){
		invokedUseCase.forceFocus();
	}

	public void setInnerBackground(Color c){
		this.setBackground(c);
		invokedUseCase.setBackground(c);
		arrow.setBackground(c);
	}

	@Override
	public ConstrainedLanguageSentenceDTO getSentence() {
		return sent;
	}

	@Override
	public boolean hasFocusIncChildren(){
		for (Control c: this.getChildren())
			if(c.isFocusControl())
				return true;
		return false;
	}

	public boolean disableFields(){
		BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(sent).getBusinessLayerFacade();
		boolean isClipboradMember = facade.isAnyClipboardMember(getSentence());
		if (isClipboradMember) {
			arrow.setEnabled(false);
			invokedUseCase.setEnabled(false);
		} else
		{
			arrow.setEnabled(true);

			invokedUseCase.setEnabled(true);
		}
		return isClipboradMember;
	}

	@Override
	public void removeSelection() {
		// no implementation
	}

}
