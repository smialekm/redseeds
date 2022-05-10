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
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

public class RejoinSentenceWidget extends Composite implements
		ScenarioSentenceWidget {

	private Label arrow;
	private Combo scenarios;
	private Combo sentences;
	// private Combo invokedUseCase;
	private UseCaseScenarioView scenarioView;

	private RejoinSentenceDTO sent;

	@Override
	public void refreshWidget() {
		if (sent.getOwningScenarios().size() > 1)
			setInnerBackground(grey);
		else
			setInnerBackground(white);
		disableFields();
	}

	public RejoinSentenceWidget(Composite parent, int arg1,
			final RejoinSentenceDTO sent, UseCaseScenarioView scenView) {
		super(parent, arg1);

		this.sent = sent;
		this.scenarioView = scenView;

		arrow = new Label(this, SWT.NONE);
		arrow.setText("=>rejoin: ");

		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = false;

		arrow.setLayoutData(gridData);

		arrow.setBackground(new Color(null, 255, 255, 255));

		scenarios = new Combo(this, SWT.READ_ONLY);
		sentences = new Combo(this, SWT.READ_ONLY);


		List<ConstrainedLanguageScenarioDTO> indexesScenarios = new ArrayList<ConstrainedLanguageScenarioDTO>();
		for (ConstrainedLanguageScenarioDTO scen : sent.getOwningScenarios()
				.get(0).getParent().getConstrainedLanguageScenarioDTOList()) {
			scenarios.add(scen.getName());
			indexesScenarios.add(scen);
		}
		scenarios.setData(indexesScenarios);

		scenarios.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				scenarioView.getEditor().backupScenariosIfNotDirty();
				List<ConstrainedLanguageSentenceDTO> indexesSentences = new ArrayList<ConstrainedLanguageSentenceDTO>();
				//clear rejoin sentence and sentence combobox
				sentences.removeAll();
				indexesSentences.clear();
				sent.setRejoinedSentence(null);
				for (ConstrainedLanguageSentenceDTO rjSent : ((List<ConstrainedLanguageScenarioDTO>) scenarios
						.getData()).get(scenarios.getSelectionIndex())
						.getScenarioSentenceList()) {
					if (rjSent instanceof SVOSentenceDTO) {
						sentences.add(((SVOSentenceDTO) rjSent)
								.toString());
						indexesSentences.add(rjSent);
					}

				}
				sentences.setData(indexesSentences);
				scenarioView.getEditor().setDirty(true);
			}
		});

		if (sent.getRejoinedSentence() != null)
			if (!sent.getRejoinedSentence().getOwningScenarios().isEmpty()) {
				List<ConstrainedLanguageSentenceDTO> indexesSentences = new ArrayList<ConstrainedLanguageSentenceDTO>();
				for (ConstrainedLanguageSentenceDTO rjSent : sent
						.getRejoinedSentence().getOwningScenarios().get(0)
						.getScenarioSentenceList()) {
					if (rjSent instanceof SVOSentenceDTO) {
						sentences.add(((SVOSentenceDTO) rjSent).toString()
								);
						indexesSentences.add(rjSent);
					}
				}
				sentences.setData(indexesSentences);
				scenarios
						.select(((List<ConstrainedLanguageScenarioDTO>) scenarios
								.getData()).indexOf(sent.getRejoinedSentence()
								.getOwningScenarios().get(0)));
			}

		if (sent.getRejoinedSentence() != null) {
			sentences.select(((List<ConstrainedLanguageSentenceDTO>) sentences
					.getData()).indexOf(sent.getRejoinedSentence()));
		}

		sentences.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				scenarioView.getEditor().backupScenariosIfNotDirty();
				sent
						.setRejoinedSentence(((List<ConstrainedLanguageSentenceDTO>) sentences
								.getData()).get(sentences.getSelectionIndex()));
				scenarioView.getEditor().setDirty(true);
			}
		});

		GridLayout gridLayout = new GridLayout();

		gridLayout.numColumns = 3;
		gridLayout.marginLeft = 5;
		gridLayout.marginRight = 5;
		gridLayout.marginBottom = 0;
		gridLayout.marginTop = 0;
		gridLayout.marginHeight = 0;
		gridLayout.verticalSpacing = 0;
		// gridLayout.makeColumnsEqualWidth=true;

		setLayout(gridLayout);
		setBackground(new Color(null, 255, 255, 255));

		GridData gridData1 = new GridData();

		gridData1.horizontalAlignment = GridData.BEGINNING;
		gridData1.verticalAlignment = GridData.FILL;

		gridData1.grabExcessHorizontalSpace = false;

		scenarios.setLayoutData(gridData1);

		GridData gridData2 = new GridData();

		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.verticalAlignment = GridData.FILL;

		gridData2.grabExcessHorizontalSpace = true;

		sentences.setLayoutData(gridData2);

		this.layout();

	}

	/*
	 * public void addKeyPressedListenener(KeyListener l){
	 * invokedUseCase.addKeyListener(l); }
	 *
	 *
	 * public void removeKeyPressedListenener(KeyListener l){
	 * invokedUseCase.removeKeyListener(l); }
	 */

	public void focus() {
		sentences.forceFocus();
	}

	public void setInnerBackground(Color c) {
		this.setBackground(c);
		sentences.setBackground(c);
		scenarios.setBackground(c);
		arrow.setBackground(c);
	}

	@Override
	public ConstrainedLanguageSentenceDTO getSentence() {
		return sent;
	}

	@Override
	public boolean hasFocusIncChildren() {
		for (Control c : this.getChildren())
			if (c.isFocusControl())
				return true;
		return false;
	}

	public boolean disableFields(){
		BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(sent).getBusinessLayerFacade();
		boolean isClipboradMember = facade.isAnyClipboardMember(getSentence());
		if (isClipboradMember) {
			arrow.setEnabled(false);
			scenarios.setEnabled(false);
			sentences.setEnabled(false);
		} else
		{
			arrow.setEnabled(true);
			scenarios.setEnabled(true);
			sentences.setEnabled(true);
		}
		return isClipboradMember;
	}

	@Override
	public void removeSelection() {
	}

}
