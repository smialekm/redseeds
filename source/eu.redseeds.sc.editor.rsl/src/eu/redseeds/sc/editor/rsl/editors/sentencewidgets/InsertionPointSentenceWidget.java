package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import eu.redseeds.common.Constants;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.UseCaseScenarioView;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class InsertionPointSentenceWidget extends Composite implements ScenarioSentenceWidget {
	
	private InvocationSentenceDTO sentence;
	
	private UseCaseScenarioView scenarioView;
	private Label numberLabel;
	private Combo insertedPattern;

	public InsertionPointSentenceWidget(Composite parent, int style, 
			final InvocationSentenceDTO sent, final UseCaseScenarioView scenView) {
		
		super(parent, style);
		
		this.sentence = sent;
		this.scenarioView = scenView;
		
		numberLabel = new Label(this, SWT.NONE);
		numberLabel.setText("<<insertion point>> (" + getInvocationPointNumber(sentence) + ")");
		
		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = false;

		numberLabel.setLayoutData(gridData);

		numberLabel.setBackground(new Color(null, 255, 255, 255));
		numberLabel.setForeground(new Color(null, 0, 0, 0));
		
		insertedPattern = new Combo(this, SWT.READ_ONLY);

		insertedPattern.setItems(scenarioView.getEditor().getFacade().getAllALPsNames().toArray(new String[0]));
		
		 if (sent.getInvokedUseCase() != null)
			 insertedPattern.select(insertedPattern.indexOf(sent.getInvokedUseCase().getName()));

		 insertedPattern.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				scenarioView.getEditor().backupScenariosIfNotDirty();
				UseCaseDTO uc = scenarioView.getEditor().getFacade().getUseCaseByName(insertedPattern.getText());
				if (uc != null) {
					sent.setInvokedUseCase(uc);
				}
				scenarioView.getEditor().setDirty(true);
			}});

		GridLayout gridLayout = new GridLayout();

		gridLayout.numColumns = 2;
		gridLayout.marginLeft = 5;
		gridLayout.marginRight = 5;
		gridLayout.marginBottom = 0;
		gridLayout.marginTop = 0;
		gridLayout.marginHeight = 0;
		gridLayout.verticalSpacing = 0;
		//gridLayout.makeColumnsEqualWidth=true;

		setLayout(gridLayout);
		setBackground(new Color(null, 255, 255, 255));

		GridData gridData1 = new GridData();

		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.verticalAlignment = GridData.FILL;

		gridData1.grabExcessHorizontalSpace = true;

		insertedPattern.setLayoutData(gridData1);
		
		this.layout();
	}

	private int getInvocationPointNumber(InvocationSentenceDTO sentence) {
		if(sentence.getOwningScenarios().size() == 0) {
			return 0;
		}
		//get sentence number
		List<InvocationSentenceDTO> sentences = new ArrayList<InvocationSentenceDTO>();
		for(ConstrainedLanguageScenarioDTO scenario : sentence.getOwningScenarios()) {
			for(ConstrainedLanguageSentenceDTO sent : scenario.getScenarioSentenceList()) {
				if(sent instanceof InvocationSentenceDTO) {
					if(((InvocationSentenceDTO)sent)
							.getStereotypes().contains(
								Constants.ALP_INSERTION_POINT_RELATIONSHIP_STEREOTYPE)) {
						sentences.add((InvocationSentenceDTO)sent);
					}
				}
			}
		}
		Collections.sort(sentences, new InsertionPointComparator());
		return (sentences.indexOf(sentence) + 1);
	}

	@Override
	public boolean disableFields() {
		BusinessLayerFacade facade = SCProjectContainer.instance()
			.getSCProject(sentence).getBusinessLayerFacade();
		boolean isClipboardMember 
			= facade.isAnyClipboardMember(getSentence());
		if (isClipboardMember) {
			numberLabel.setEnabled(false);
		} else {
			numberLabel.setEnabled(true);
		}
		return isClipboardMember;
	}

	@Override
	public ConstrainedLanguageSentenceDTO getSentence() {
		return sentence;
	}

	@Override
	public boolean hasFocusIncChildren() {
		for (Control c: this.getChildren())
			if(c.isFocusControl())
				return true;
		return false;
	}

	@Override
	public void refreshWidget() {
		refresh();
	}

	@Override
	public void removeSelection() {
//		refresh();
	}
	
	private void refresh() {
		if (sentence.getOwningScenarios().size()>1)
			setInnerBackground(grey);
		else
			setInnerBackground(white);
		disableFields();
	}

	private void setInnerBackground(Color c) {
		this.setBackground(c);
		numberLabel.setBackground(c);
		
	}

	public void focus() {
		insertedPattern.forceFocus();
	}
	
	class InsertionPointComparator implements Comparator<InvocationSentenceDTO> {

		@Override
		public int compare(InvocationSentenceDTO o1, InvocationSentenceDTO o2) {
			if(o1.getId() > o2.getId()) {
				return 1;
			}
			if(o1.getId() < o2.getId()) {
				return -1;
			}
			return 0;
		}
		
	}

}

