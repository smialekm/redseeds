package eu.redseeds.sc.editor.rsl.editors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.Constants;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.actions.AddDomainElementAction;
import eu.redseeds.sc.editor.rsl.actions.AddStatementToNotionAction;
import eu.redseeds.sc.editor.rsl.editors.domain.PhrasePropertyEditor;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.ConditionSentenceWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.INewDomainObjectAddedListener;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.InsertionPointSentenceWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.InvocationSentenceWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.PostconditionSentenceWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.PreconditionSentenceWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.RejoinSentenceWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.SVOSentenceWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.SVOSentenceWidgetFactory;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.ScenarioSentenceWidget;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.impl.rsl.rsldomainelements.terms.NounImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.ActorOrSystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InclusionType;
import eu.remics.common.Constans;
import eu.remics.recovery.model.NotionKindHelper;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;

public class UseCaseScenarioView extends ScrolledComposite implements INewDomainObjectAddedListener {

	private UseCaseEditor editor;

	private UseCaseScenarioView scenarioView;

	private ConstrainedLanguageScenarioDTO scenario;

//	private Color grey = new Color(null, 235, 235, 235);

	private Composite comp;

	private Text nameBox;

	private Composite namePanel;
	
	private Label recipientLabel;

	private List<ScenarioSentenceWidget> scenarioSentenceWidgets=new ArrayList<ScenarioSentenceWidget>();

	public UseCaseScenarioView(UseCaseEditor editor,
			ConstrainedLanguageScenarioDTO scenario, Composite parent, int style) {
		super(parent, style | SWT.V_SCROLL);
		comp = new Composite(this, style);
		this.editor = editor;
		this.scenario = scenario;
		scenarioView = this;
		initSentenes();

		//force SCNavigator refreshing on ScenView dispose
		//prevents navigator from showing reference to non-existent
		//scenario after deletion (WP6T61-67)
		this.addDisposeListener(new DisposeListener(){

			@Override
			public void widgetDisposed(DisposeEvent e) {
				if(PlatformUI.getWorkbench()!=null)
					if(PlatformUI.getWorkbench().getActiveWorkbenchWindow()!=null)
						if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()!=null)
							if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("eu.redseeds.engine.navigator.view")!=null)
				SCProjectHelper.refreshSCNavigator();
			}});
		// this.setBounds(this.getParent().getBounds());
		// this.setSize(this.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	public void refreshSentenes() {
		for (Control c : comp.getChildren())
			if (c instanceof ScenarioSentenceWidget)
				c.dispose();
		initSentenceControls();
		// this.setSize(this.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		// this.setBounds(this.getParent().getBounds());
		// this.layout();
		comp.layout();
		this.getVerticalBar().setVisible(true);

	}

	private void initSentenes() {

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginLeft = 5;
		gridLayout.marginRight = 5;
		gridLayout.marginBottom = 0;
		gridLayout.marginTop = 0;
		gridLayout.marginHeight = 0;
		gridLayout.verticalSpacing = 0;

		comp.setLayout(gridLayout);
		comp.setBackground(new Color(null, 255, 255, 255));

		this.setBackground(new Color(null, 255, 255, 255));

		this.setContent(comp);
		this.setExpandVertical(true);
		this.setExpandHorizontal(true);
		this.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle r = scenarioView.getClientArea();
				scenarioView.setMinSize(comp.computeSize(r.width, SWT.DEFAULT));
			}
		});

		namePanel = new Composite(comp, SWT.NONE);
		namePanel.setBackground(new Color(null, 255, 255, 255));
		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = true;

		namePanel.setLayoutData(gridData);

		GridLayout gl = new GridLayout();
		gl.numColumns=5;
		
		namePanel.setLayout(gl);

		Label nameLabel = new Label(namePanel, SWT.NO_BACKGROUND);
		nameLabel.setText("Name: ");
		nameLabel.setBackground(new Color(null, 255, 255, 255));

		nameBox = new Text(namePanel, SWT.BORDER);
		nameBox.setText(scenario.getName());
		
		GridData gd = new GridData();
		gd.widthHint=200;
		gd.heightHint=12;
		
		nameBox.setLayoutData(gd);

		nameBox.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				editor.backupScenariosIfNotDirty();
				editor.setDirty(true);
			}

		});

		scenarioSentenceWidgets.add(new NameBoxMockSentenceWidget(nameBox,this));
		
		Label label1 = new Label(namePanel, SWT.NO_BACKGROUND);
		label1.setText("Sentence Type");
		label1.setBackground(new Color(null, 255, 255, 255));
		
		GridData gd1 = new GridData();
		gd1.horizontalAlignment=GridData.END;
		gd1.widthHint=175;
		gd1.grabExcessHorizontalSpace = true;
		label1.setLayoutData(gd1);
		
		Label label2 = new Label(namePanel, SWT.NO_BACKGROUND);
		label2.setText("Action Type");
		label2.setBackground(new Color(null, 255, 255, 255));
		
		GridData gd2 = new GridData();
		
		gd2.horizontalAlignment=GridData.END;
		gd2.widthHint=68;
		label2.setLayoutData(gd2);
		
		Combo recipient = new Combo(namePanel, SWT.READ_ONLY);
		for (ActorOrSystemElementDTO ase: scenarioView.getEditor().getFacade().getAllRecipients())
			if (ase instanceof ActorDTO && null!=((ActorDTO) ase).getName() || ase instanceof SystemElementDTO && null!=((SystemElementDTO) ase).getName())
				recipient.add(ase.toString());
		
		int width = recipient.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
		
		recipient.dispose();
		
		recipientLabel = new Label(namePanel, SWT.NO_BACKGROUND);
		recipientLabel.setText("Recipient");
		recipientLabel.setBackground(new Color(null, 255, 255, 255));
		
		GridData gd3 = new GridData();
		gd3.horizontalAlignment=GridData.END;
		gd3.widthHint=width+5;
		recipientLabel.setLayoutData(gd3);
		
		// initSentenceControls();
	}

	public void initSentenceControls() {
		for (ConstrainedLanguageSentenceDTO sent : this.scenario
				.getScenarioSentenceList()) {
			if (sent instanceof PreconditionSentenceDTO) {
				this
						.addPreconditionSentenceWidget((PreconditionSentenceDTO) sent);
			}
			if (sent instanceof SVOSentenceDTO) {
				this.addSVOSentenceWidget((SVOSentenceDTO) sent);
			} else if (sent instanceof ConditionSentenceDTO) {
				this.addConditionSentenceWidget((ConditionSentenceDTO) sent);
			} else if (sent instanceof InvocationSentenceDTO) {
				InvocationSentenceDTO invSent = (InvocationSentenceDTO)sent;
				if(invSent.getStereotypes().contains(
						Constants.ALP_INSERTION_POINT_RELATIONSHIP_STEREOTYPE)) {//is insertion point
					this.addInsertionPointSentenceWidget(invSent, false);
				}
				else {
					this.addInvocationSentenceWidget(invSent);
				}
			} else if (sent instanceof RejoinSentenceDTO) {
				this.addRejoinSentenceWidget((RejoinSentenceDTO) sent);
			}
			if (sent instanceof PostconditionSentenceDTO) {
				this.addPostconditionSentenceWidget((PostconditionSentenceDTO) sent);
			}
		}
	}

	private RejoinSentenceWidget addRejoinSentenceWidget(RejoinSentenceDTO sent) {

		RejoinSentenceWidget rejoin = new RejoinSentenceWidget(comp, SWT.NONE,
				sent, this);

		/*
		 * if (insert) condition.moveBelow((Control)
		 * this.getCurrentSentenceWidget());
		 */

		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = true;

		rejoin.setLayoutData(gridData);
		rejoin.refreshWidget();

		comp.layout();
		this.layout();

		return rejoin;
	}

	private ConditionSentenceWidget addConditionSentenceWidget(
			ConditionSentenceDTO sent) {
		return addConditionSentenceWidget(sent, false);
	}

	private ConditionSentenceWidget addConditionSentenceWidget(
			ConditionSentenceDTO sent, boolean insert) {
		ConditionSentenceWidget condition = new ConditionSentenceWidget(comp,
				SWT.NONE, sent, this);

		if (insert)
			condition.moveBelow((Control) this.getCurrentSentenceWidget());

		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = true;

		condition.setLayoutData(gridData);
		condition.refreshWidget();

		comp.layout();
		this.layout();

//		scenarioSentenceWidgets.add(condition);

		return condition;
	}

	private PreconditionSentenceWidget addPreconditionSentenceWidget(
			PreconditionSentenceDTO sent) {
		PreconditionSentenceWidget precondition = new PreconditionSentenceWidget(
				comp, SWT.NONE, sent, this);

		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = true;

		precondition.setLayoutData(gridData);
		precondition.refreshWidget();

		comp.layout();
		this.layout();

		scenarioSentenceWidgets.add(precondition);

		return precondition;
	}

	private PostconditionSentenceWidget addPostconditionSentenceWidget(
			PostconditionSentenceDTO sent) {
		PostconditionSentenceWidget postcondition = new PostconditionSentenceWidget(
				comp, SWT.NONE, sent, this);

		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = true;

		postcondition.setLayoutData(gridData);
		postcondition.refreshWidget();


		comp.layout();
		this.layout();

		scenarioSentenceWidgets.add(postcondition);

		return postcondition;
	}

	private InvocationSentenceWidget addInvocationSentenceWidget(
			InvocationSentenceDTO sent) {
		return addInvocationSentenceWidget(sent, false);
	}

	private InvocationSentenceWidget addInvocationSentenceWidget(
			InvocationSentenceDTO sent, boolean insert) {
		InvocationSentenceWidget invoke = new InvocationSentenceWidget(comp,
				SWT.NONE, sent, this);
		if (insert)
			invoke.moveBelow((Control) this.getCurrentSentenceWidget());
		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = true;

		invoke.setLayoutData(gridData);
		invoke.refreshWidget();
		comp.layout();
		this.layout();
		return invoke;
	}
	
	private InsertionPointSentenceWidget addInsertionPointSentenceWidget(
				InvocationSentenceDTO sent, boolean insert) {
		InsertionPointSentenceWidget insrtPntWgt 
			= new InsertionPointSentenceWidget(comp, SWT.NONE, sent, this);
		
		if(insert) {
			insrtPntWgt.moveBelow((Control)this.getCurrentSentenceWidget());
		}
		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;

		insrtPntWgt.setLayoutData(gridData);
		insrtPntWgt.refreshWidget();
		comp.layout();
		this.layout();

		return insrtPntWgt;
	}

	private SVOSentenceWidget addSVOSentenceWidget(SVOSentenceDTO sent) {
		return addSVOSentenceWidget(sent, false);
	}

	private SVOSentenceWidget addSVOSentenceWidget(SVOSentenceDTO sent,
			boolean insert) {
		SVOSentenceWidget text = SVOSentenceWidgetFactory.getDefaultInstance(comp, SWT.NONE, sent,
				this);

		if (insert)
			text.moveBelow((Control) this.getCurrentSentenceWidget());

		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = true;

		text.setLayoutData(gridData);

		text.addKeyPressedListenener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
			};

			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					scenarioView.createSVOSentence();
				} else if (e.keyCode == SWT.ARROW_DOWN) {
					boolean thisSentence = false;
					for (Control c : comp.getChildren()) {
						if (thisSentence == true
								&& c instanceof SVOSentenceWidget) {
							((SVOSentenceWidget) c).focus();
							break;
						}
						if (c.equals(((StyledText) (e.getSource())).getParent()
								.getParent())) {
							thisSentence = true;
						}
					}
				} else if (e.keyCode == SWT.ARROW_UP) {
					boolean thisSentence = false;

					List<Control> tmp = Arrays.asList(comp.getChildren());
					Collections.reverse(tmp);

					for (Control c : tmp) {
						if (thisSentence == true
								&& c instanceof SVOSentenceWidget) {
							((SVOSentenceWidget) c).focus();
							break;
						}
						if (c
								.equals(((StyledText) (e.getSource()))
										.getParent())) {
							thisSentence = true;
						}
					}
				}
			};
		});

		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				editor.backupScenariosIfNotDirty();
				editor.setDirty(true);
			}
		});

		text.refreshWidget();
		comp.layout();
		this.layout();

		scenarioSentenceWidgets.add(text);

		return text;

	}

	/**
	 * Removes selection from all SvoSentenceWidgets without SVOSentenceWidget which is sender
	 * @param sender won't remove selection in this widget
	 */
	//this flag is used to prevent other svosentence from firing rmvSelection after their selection was removed
	private final AtomicBoolean isRmvSelectionActive=new AtomicBoolean(false);
	public void rmvSelectionFormAllOtherSentenceWidgets(ScenarioSentenceWidget sender){
		if(isRmvSelectionActive.compareAndSet(false, true)){
			for (ScenarioSentenceWidget sentenceWidget : scenarioSentenceWidgets) {
				if(sentenceWidget!=sender){
					sentenceWidget.removeSelection();
				}
			}
			isRmvSelectionActive.set(false);
		}
	}


	public void createSVOSentence() {
		//prevent from adding new sentences to scenario in clipboard
		if (isScenarioClipboardMember()) return;
		ConstrainedLanguageSentenceDTO currSent;
		if ((currSent = getCurrentSentence()) != null) {
			if (currSent instanceof PostconditionSentenceDTO)
				return;
		}
		
		editor.backupScenariosIfNotDirty();

		SVOSentenceDTO sent = editor.getFacade().createSVOSentenceDTO();
		sent.setSentenceText("");
		if (this.getCurrentSentenceWidget() != null) {
			ConstrainedLanguageSentenceDTO after = this
					.getCurrentSentenceWidget().getSentence();
			for (ConstrainedLanguageScenarioDTO scen : this
					.getCurrentSentenceWidget().getSentence()
					.getOwningScenarios()) {
				scen.insertScenarioStepAfter(after, sent);
			}
			addSVOSentenceWidget(sent, true).focus();
		} else {
			scenario.addScenarioStep(sent);
			addSVOSentenceWidget(sent).focus();
		}
		editor.setDirty(true);
		refreshScenariosTab();
		//TODO this.refreshSentenes();
	}

	private ScenarioSentenceWidget getCurrentSentenceWidget() {
		for (Control c : comp.getChildren()) {
			if (c instanceof ScenarioSentenceWidget)
				if (((ScenarioSentenceWidget) c).hasFocusIncChildren()) {
					return (ScenarioSentenceWidget) c;
				}
		}
		return null;
	}

	private ConstrainedLanguageSentenceDTO getCurrentSentence() {
		ConstrainedLanguageSentenceDTO sent = null;
		ScenarioSentenceWidget widget = getCurrentSentenceWidget();
		if (widget != null)
			sent = widget.getSentence();
		else if (scenario.getScenarioSentenceList().size() > 0)
			sent = scenario.getScenarioSentenceList().get(
					scenario.getScenarioSentenceList().size() - 1);
		return sent;
	}

	private void createInvocationSentence(InclusionType type) {
		editor.backupScenariosIfNotDirty();
		
		InvocationSentenceDTO sent = editor.getFacade()
				.createInvocationSentenceDTO();
		sent.setInclusionType(type);

		if (this.getCurrentSentenceWidget() != null) {
			ConstrainedLanguageSentenceDTO after = this
					.getCurrentSentenceWidget().getSentence();
			for (ConstrainedLanguageScenarioDTO scen : this
					.getCurrentSentenceWidget().getSentence()
					.getOwningScenarios()) {
				scen.insertScenarioStepAfter(after, sent);
			}
			addInvocationSentenceWidget(sent, true).focus();
		} else {
			scenario.addScenarioStep(sent);
			addInvocationSentenceWidget(sent).focus();
		}
		editor.setDirty(true);
	}
	
	private void createInsertionPointSentence() {
		editor.backupScenariosIfNotDirty();
		
		InvocationSentenceDTO sent = editor.getFacade()
				.createInvocationSentenceDTO();
		sent.setInclusionType(InclusionType.INSERT);//does it matter? 
		
		sent.addStereotype(Constants.ALP_INSERTION_POINT_RELATIONSHIP_STEREOTYPE);
		
		if (this.getCurrentSentenceWidget() != null) {
			ConstrainedLanguageSentenceDTO after = this
					.getCurrentSentenceWidget().getSentence();
			for (ConstrainedLanguageScenarioDTO scen : this
					.getCurrentSentenceWidget().getSentence()
					.getOwningScenarios()) {
				scen.insertScenarioStepAfter(after, sent);
			}
			addInsertionPointSentenceWidget(sent, true).focus();
		} else {
			scenario.addScenarioStep(sent);
			addInsertionPointSentenceWidget(sent, false).focus();
		}
		editor.setDirty(true);
	}

	public void createFinalSuccess() {
		//prevent from adding new sentences to scenario in clipboard
		if (isScenarioClipboardMember()) return;

		ConstrainedLanguageSentenceDTO currSent;
		if ((currSent = getCurrentSentence()) != null) {
			if (currSent instanceof PostconditionSentenceDTO
					|| currSent instanceof PreconditionSentenceDTO
					|| currSent instanceof RejoinSentenceDTO)
				return;
		}

		if ((currSent = scenario.getLastSentence()) != null) {
			if (currSent instanceof PostconditionSentenceDTO
					|| currSent instanceof PreconditionSentenceDTO
					|| currSent instanceof RejoinSentenceDTO)
				return;
		} else return;

		editor.backupScenariosIfNotDirty();

		createPostconditionSentence(true);
		refreshScenariosTab();
		editor.setDirty(true);
	}

	public void createFinalFailure() {
		//prevent from adding new sentences to scenario in clipboard
		if (isScenarioClipboardMember()) return;

		ConstrainedLanguageSentenceDTO currSent;
		if ((currSent = getCurrentSentence()) != null) {
			if (currSent instanceof PostconditionSentenceDTO
					|| currSent instanceof PreconditionSentenceDTO
					|| currSent instanceof RejoinSentenceDTO)
				return;
		}

		if ((currSent = scenario.getLastSentence()) != null) {
			if (currSent instanceof PostconditionSentenceDTO
					|| currSent instanceof PreconditionSentenceDTO
					|| currSent instanceof RejoinSentenceDTO)
				return;
		} else return;

		editor.backupScenariosIfNotDirty();
		
		createPostconditionSentence(false);
		refreshScenariosTab();
		editor.setDirty(true);
	}

	private void createPostconditionSentence(boolean isSuccess) {
		
		editor.backupScenariosIfNotDirty();
		
		PostconditionSentenceDTO sent = editor.getFacade()
				.createPostconditionSentenceDTO();
		sent.setSuccess(isSuccess);

		scenario.addScenarioStep(sent);
		addPostconditionSentenceWidget(sent).focus();

		editor.setDirty(true);
	}

	public void createRejoinSentnece() {
		//prevent from adding new sentences to scenario in clipboard
		if (isScenarioClipboardMember()) return;

		ConstrainedLanguageSentenceDTO currSent;
		if ((currSent = getCurrentSentence()) != null) {
			if (currSent instanceof PostconditionSentenceDTO
					|| currSent instanceof PreconditionSentenceDTO
					|| currSent instanceof RejoinSentenceDTO)
				return;
		}

		if ((currSent = scenario.getLastSentence()) != null) {
			if (currSent instanceof PostconditionSentenceDTO
					|| currSent instanceof PreconditionSentenceDTO
					|| currSent instanceof RejoinSentenceDTO)
				return;
		} else return;

		editor.backupScenariosIfNotDirty();
		
		RejoinSentenceDTO sent = editor.getFacade().createRejoinSentenceDTO();
		scenario.addScenarioStep(sent);
		addRejoinSentenceWidget(sent).focus();

		editor.setDirty(true);
		refreshScenariosTab();
		editor.setDirty(true);
	}

	public void createInvokeInsertSentence() {
		//prevent from adding new sentences to scenario in clipboard
		if (isScenarioClipboardMember()) return;

		ConstrainedLanguageSentenceDTO currSent;
		if ((currSent = getCurrentSentence()) != null) {
			if (currSent instanceof PostconditionSentenceDTO
					|| currSent instanceof PreconditionSentenceDTO)
				return;
		}
		
		editor.backupScenariosIfNotDirty();
		
		createInvocationSentence(InclusionType.INSERT);
		refreshScenariosTab();
		editor.setDirty(true);
	}

	public void createInvokeRequestSentence() {
		//prevent from adding new sentences to scenario in clipboard
		if (isScenarioClipboardMember()) return;

		ConstrainedLanguageSentenceDTO currSent;
		if ((currSent = getCurrentSentence()) != null) {
			if (!(currSent instanceof SVOSentenceDTO)
					&& !(currSent instanceof InvocationSentenceDTO))
				return;
			else if (currSent instanceof InvocationSentenceDTO)
				if (((InvocationSentenceDTO) currSent).getInclusionType()
						.equals(InclusionType.INSERT))
					return;
		}
		
		editor.backupScenariosIfNotDirty();
		
		createInvocationSentence(InclusionType.REQUEST);
		refreshScenariosTab();
		editor.setDirty(true);
	}
	
	/**
	 * creates invoke sentence used as an insertion point
	 */
	public void createInvokeSentenceAsInsertionPoint() {
		//prevent from adding new sentences to scenario in clipboard
		if (isScenarioClipboardMember()) return;
		
		ConstrainedLanguageSentenceDTO currSent;
		if ((currSent = getCurrentSentence()) != null) {
			if (currSent instanceof PostconditionSentenceDTO)
				return;
		}
		
		editor.backupScenariosIfNotDirty();
		
		createInsertionPointSentence();
		refreshScenariosTab();
		editor.setDirty(true);
	}

	public void fork() {
		//prevent from adding new sentences to scenario in clipboard
		if (isScenarioClipboardMember()) return;

		ConstrainedLanguageSentenceDTO currSent;
		if ((currSent = getCurrentSentence()) != null) {
			if (!(currSent instanceof SVOSentenceDTO))
				return;
		}

		editor.backupScenariosIfNotDirty();
		
		UseCaseDTO uc = editor.getUseCase();
		ConstrainedLanguageScenarioDTO newScen = editor.getFacade()
				.createConstrainedLanguageScenarioDTO();
		newScen.setName("alternate ");
		uc.addConstrainedLanguageScenario(newScen);

		ConstrainedLanguageSentenceDTO after = null;

		if (this.getCurrentSentenceWidget() != null)
			after = this.getCurrentSentenceWidget().getSentence();

		for (ConstrainedLanguageSentenceDTO sent : scenario
				.getScenarioSentenceList()) {
			newScen.addScenarioStep(sent);
			if (after.equals(sent))
				break;
		}
		ConditionSentenceDTO cond = editor.getFacade()
				.createConditionSentenceDTO();
		cond.setSentenceText("set your condition");
		newScen.addScenarioStep(cond);

		boolean crCond = true;
		if (after != null) {
			List<ConstrainedLanguageSentenceDTO> sentences = scenario
					.getScenarioSentenceList();
			int index = sentences.indexOf(after);
			if (++index < sentences.size())
				if (sentences.get(index) instanceof ConditionSentenceDTO)
					crCond = false;
		}
		if (crCond &&after!=null) {
			ConditionSentenceDTO sent = editor.getFacade()
					.createConditionSentenceDTO();
			sent.setSentenceText("set your condition");
			for(ConstrainedLanguageScenarioDTO owningScen:after.getOwningScenarios()){
				if (!owningScen.equals(newScen))
					owningScen.insertScenarioStepAfter(after, sent);
			}
				addConditionSentenceWidget(sent, true).focus();


		}
			//createConditionSentence();
		newScen.setName("alternate " + editor.addScenarioTab(newScen));

		refreshScenariosTab();

		editor.setDirty(true);

		// TODO: set new scenario name
		// editor.setPageText(index, newScen.getName());
	}

	public void join() {
		//prevent from adding new sentences to scenario in clipboard
		if (isScenarioClipboardMember()) return;

		editor.refreshScenariosTabs(this);
	}

	public UseCaseEditor getEditor() {
		return editor;
	}

	public void save() {
		scenario.setName(nameBox.getText());
	}

	public ConstrainedLanguageScenarioDTO getScenario() {
		return scenario;
	}

	void refreshScenariosTab() {
		for (Control c : comp.getChildren())
			if (c instanceof ScenarioSentenceWidget)
				((ScenarioSentenceWidget) c).refreshWidget();
		refreshLayout();
	}
	
	public void refreshSentenceCombos(){
		for (Control c : comp.getChildren())
			if (c instanceof SVOSentenceWidget)
				((SVOSentenceWidget) c).refreshCombos();
		refreshLayout();
	}

	public void refreshLayout(){
		for (Control c : comp.getChildren())
			if (c instanceof SVOSentenceWidget)
				((SVOSentenceWidget) c).layout(true);
		
		Combo recipient = new Combo(namePanel, SWT.READ_ONLY);
		for (ActorOrSystemElementDTO ase: scenarioView.getEditor().getFacade().getAllRecipients()){
			recipient.add(ase.toString());
		}
		
		int width = recipient.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
		
		recipient.dispose();
		
		((GridData) recipientLabel.getLayoutData()).widthHint=width+5;
		
		namePanel.layout(true);
	}
	
	public void refreshSentenceTypes(){
		for (Control c : comp.getChildren())
			if (c instanceof SVOSentenceWidget)
				((SVOSentenceWidget) c).setSentenceTypeData();
		refreshActionTypes();
	}
	
	public void refreshActionTypes(){
		for (Control c : comp.getChildren())
			if (c instanceof SVOSentenceWidget)
				((SVOSentenceWidget) c).setActionTypeData();
	}
	
	public void deleteSentence() {
		//prevent from deleting sentences to scenario in clipboard
		if (isScenarioClipboardMember()) return;

		ConstrainedLanguageSentenceDTO sent = this.getCurrentSentence();
		List<ConstrainedLanguageSentenceDTO> sentenceToBeDeleted = new ArrayList<ConstrainedLanguageSentenceDTO>();
		if (sent != null) {
			if (!(sent instanceof PreconditionSentenceDTO)) {
				if (sent instanceof ConditionSentenceDTO){
					int sentIndex = scenario.getScenarioSentenceList().indexOf(
							sent);
					if(sentIndex>0)
						if(scenario.getScenarioSentenceList().get(sentIndex-1).getOwningScenarios().size()
							==sent.getOwningScenarios().size())
							sentenceToBeDeleted.add(sent);

				} else {
				sentenceToBeDeleted.add(sent);
				}
				if (sent instanceof SVOSentenceDTO) {
					ConstrainedLanguageSentenceDTO lastSent = sent;
					for (int i = scenario.getScenarioSentenceList().indexOf(
							sent) + 1; i < scenario.getScenarioSentenceList()
							.size(); i++) {
						lastSent = scenario.getScenarioSentenceList().get(i);
						if (!(lastSent instanceof InvocationSentenceDTO))
							break;
						else if (!((InvocationSentenceDTO) lastSent)
								.getInclusionType().equals(
										InclusionType.REQUEST))
							break;
						sentenceToBeDeleted.add(lastSent);
					}
					if (scenario.getScenarioSentenceList().indexOf(sent) == 1
							&& !(lastSent instanceof SVOSentenceDTO))
						sentenceToBeDeleted.clear();
				}

				for (ConstrainedLanguageSentenceDTO delSent : sentenceToBeDeleted) {
					
					scenarioView.getEditor().backupScenariosIfNotDirty();
					
					for (ConstrainedLanguageScenarioDTO scen : delSent
							.getOwningScenarios()) {
						scen.removeScenarioStep(delSent);
					}
					if(delSent instanceof SVOSentenceDTO ){
						SVOSentenceDTO svoSent = (SVOSentenceDTO)delSent;
						if(svoSent.getPredicate()!=null)
							svoSent.getPredicate().deleteRecursively();
						if(svoSent.getSubject()!=null)
							svoSent.getSubject().deleteRecursively();
					}
					((ConstrainedLanguageSentence) delSent).delete();
					editor.setDirty(true);
				}

			}
		}
		if (editor.isDirty())
			this.refreshSentenes();
	}

	public void deleteScenario() {
		//prevent from deleting scenario from clipboard
		if (isScenarioClipboardMember()) return;
		UseCaseDTO uc = editor.getUseCase();
		
		if (uc.getConstrainedLanguageScenarioDTOList().size() > 1) {
			if(MScenario.isDerivedScenario(scenario)){
				MessageDialog.openInformation(getShell(), "Delete scenario", "Cannot delete recovered scenario.");
				return; 
			}
			
			editor.backupScenariosIfNotDirty();
			
			scenario.delete();
/*			for (ConstrainedLanguageSentenceDTO sent : scenario
					.getScenarioSentenceList()) {
				scenario.removeScenarioStep(sent);
				if (sent.getOwningScenarios().isEmpty())
					((ConstrainedLanguageSentence) sent).delete();
			}
			((RSLUseCase) uc)
					.removeRepresentation(((ConstrainedLanguageScenario) scenario));*/
			editor.removePage(editor.getActivePageId());
			editor.setDirty(true);
		}

	}

	private boolean isScenarioClipboardMember(){
		BusinessLayerFacade facade = SCProjectContainer.instance()
		.getSCProject(getScenario()).getBusinessLayerFacade();
		return facade.isAnyClipboardMember(getScenario());
	}

	private static class NameBoxMockSentenceWidget implements ScenarioSentenceWidget{

		private final Text nameBox;
		private final UseCaseScenarioView scenarioView;

		NameBoxMockSentenceWidget(Text nameBox,UseCaseScenarioView scenarioView) {
			super();
			this.nameBox = nameBox;
			this.scenarioView=scenarioView;
			addSelectionAwareMouseListener(nameBox);
		}

		@Override
		public boolean disableFields() {
			return false;
		}

		@Override
		public ConstrainedLanguageSentenceDTO getSentence() {
			return null;
		}

		@Override
		public boolean hasFocusIncChildren() {
			return false;
		}

		@Override
		public void refreshWidget() {

		}

		@Override
		public void removeSelection() {
			if(nameBox!=null && !nameBox.isDisposed()){
				nameBox.setSelection(0, 0);
			}
		}

		private void addSelectionAwareMouseListener(Control composite){
			composite.addMouseListener(new MouseListener(){
				@Override
				public void mouseDoubleClick(MouseEvent e) {
				}
				@Override
				public void mouseDown(MouseEvent e) {
					if(scenarioView!=null){
						scenarioView.rmvSelectionFormAllOtherSentenceWidgets(NameBoxMockSentenceWidget.this);
					}
				}
				@Override
				public void mouseUp(MouseEvent e) {
				}});
		}

	}

	@Override
	public void handleNewDomainObjectEvent(EventObject e) {
		if (!comp.isDisposed()) refreshSentenceCombos();
	}

	public void addAllDomainElements() {
		//TODO uniemozliwic wlaczenie bez zapsiu (QuickFix)
		if (isScenarioClipboardMember()) return;
		for (ConstrainedLanguageSentenceDTO sent:scenario.getScenarioSentenceList())
			if (sent instanceof SVOSentenceDTO){
				if (null!=((SVOSentenceDTO) sent).getSubject()){
					if (((SVOSentenceDTO) sent).getSubject().getNoun().getSynonymUid()==0){
						if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) ((SVOSentenceDTO) sent).getSubject().getNoun().autoAddAndAssignSense();
						else if (SCProjectHelper.getSenseAutoAssigmentFlag()) ((SVOSentenceDTO) sent).getSubject().getNoun().autoAssignSense();
					}
					BusinessLayerFacade facade = (BusinessLayerFacade) ((ConstrainedLanguageScenario) scenario).getGraph();
					if (null==facade.getActorDTO(((SVOSentenceDTO) sent).getSubject()) && null==facade.getSystemElementDTO(((SVOSentenceDTO) sent).getSubject()) && RemoteJGWNL.getInstance().isConnected()){
						if ("system".equalsIgnoreCase(((SVOSentenceDTO) sent).getSubject().getNoun().getName())){
							SystemElementDTO sysElem = facade.createSystemElementDTO();
							NounPhraseDTO phraseWN = (NounPhraseDTO) ((SVOSentenceDTO) sent).getSubject().copy(true);
							sysElem.setNamePhrase(phraseWN);
							AddDomainElementAction action = new AddDomainElementAction(SCProjectContainer.instance().getSCProject(scenario));
							action.setDomainElement(sysElem);
							action.run();
						} else {
							ActorDTO actor = facade.createActorDTO();
							NounPhraseDTO phraseWN = (NounPhraseDTO) ((SVOSentenceDTO) sent).getSubject().copy(true);
							actor.setNamePhrase(phraseWN);
							AddDomainElementAction action = new AddDomainElementAction(SCProjectContainer.instance().getSCProject(scenario));
							action.setDomainElement(actor);
							action.run();
						}
					}
				}
				if (null!=((SVOSentenceDTO) sent).getPredicate()){
					SimpleVerbPhraseDTO simpleVerbPhrase = (((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO)?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate()):((ComplexVerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate()).getSimpleVerbPhrase();
					ComplexVerbPhraseDTO complexVerbPhrase = (((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO)?null:((ComplexVerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate());
					if (null!=simpleVerbPhrase && null!=simpleVerbPhrase.getObject() && null!=simpleVerbPhrase.getObject().getNoun()){
						if (simpleVerbPhrase.getObject().getNoun().getSynonymUid()==0){
							if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) simpleVerbPhrase.getObject().getNoun().autoAddAndAssignSense();
							else if (SCProjectHelper.getSenseAutoAssigmentFlag()) simpleVerbPhrase.getObject().getNoun().autoAssignSense();
						}
						if (null!=simpleVerbPhrase.getObject().getDeterminer() && simpleVerbPhrase.getObject().getDeterminer().getSynonymUid()==0){
							if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) simpleVerbPhrase.getObject().getDeterminer().autoAddAndAssignSense();
							else if (SCProjectHelper.getSenseAutoAssigmentFlag()) simpleVerbPhrase.getObject().getDeterminer().autoAssignSense();
						}
						if (null!=simpleVerbPhrase.getObject().getModifier() && simpleVerbPhrase.getObject().getModifier().getSynonymUid()==0){
							if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) simpleVerbPhrase.getObject().getModifier().autoAddAndAssignSense();
							else if (SCProjectHelper.getSenseAutoAssigmentFlag()) simpleVerbPhrase.getObject().getModifier().autoAssignSense();
						}
						if (simpleVerbPhrase.getVerb().getSynonymUid()==0){
							if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) simpleVerbPhrase.getVerb().autoAddAndAssignSense();
							else if (SCProjectHelper.getSenseAutoAssigmentFlag()) simpleVerbPhrase.getVerb().autoAssignSense();
						}
						if (simpleVerbPhrase.getObject().getNoun().getSynonymUid()!=0 && simpleVerbPhrase.getVerb().getSynonymUid()!=0 && (null==simpleVerbPhrase.getObject().getDeterminer() || simpleVerbPhrase.getObject().getDeterminer().getSynonymUid()!=0) && (null==simpleVerbPhrase.getObject().getModifier() || simpleVerbPhrase.getObject().getModifier().getSynonymUid()!=0)){
							NotionDTO notion = addNotion(simpleVerbPhrase.getObject());
							if (null!=notion)
								addStatement(notion, simpleVerbPhrase);
							if (null!=complexVerbPhrase && null!=complexVerbPhrase.getObject() && null!=complexVerbPhrase.getObject().getNoun()){
								if (complexVerbPhrase.getObject().getNoun().getSynonymUid()==0){
									if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) complexVerbPhrase.getObject().getNoun().autoAddAndAssignSense();
									else if (SCProjectHelper.getSenseAutoAssigmentFlag()) complexVerbPhrase.getObject().getNoun().autoAssignSense();
								}
								if (null!=complexVerbPhrase.getObject().getDeterminer() && complexVerbPhrase.getObject().getDeterminer().getSynonymUid()==0){
									if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) complexVerbPhrase.getObject().getDeterminer().autoAddAndAssignSense();
									else if (SCProjectHelper.getSenseAutoAssigmentFlag()) complexVerbPhrase.getObject().getDeterminer().autoAssignSense();
								}
								if (null!=complexVerbPhrase.getObject().getModifier() && complexVerbPhrase.getObject().getModifier().getSynonymUid()==0){
									if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) complexVerbPhrase.getObject().getModifier().autoAddAndAssignSense();
									else if (SCProjectHelper.getSenseAutoAssigmentFlag()) complexVerbPhrase.getObject().getModifier().autoAssignSense();
								}
								if (complexVerbPhrase.getPreposition().getSynonymUid()==0){
									if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) complexVerbPhrase.getPreposition().autoAddAndAssignSense();
									else if (SCProjectHelper.getSenseAutoAssigmentFlag()) complexVerbPhrase.getPreposition().autoAssignSense();
								}
								if (complexVerbPhrase.getObject().getNoun().getSynonymUid()!=0 && complexVerbPhrase.getPreposition().getSynonymUid()!=0 && (null==complexVerbPhrase.getObject().getDeterminer() || complexVerbPhrase.getObject().getDeterminer().getSynonymUid()!=0) && (null==complexVerbPhrase.getObject().getModifier() || complexVerbPhrase.getObject().getModifier().getSynonymUid()!=0)){
									notion = addNotion(complexVerbPhrase.getObject());
									if (null!=notion)
										addStatement(notion, complexVerbPhrase);
								}
							}
						}
					}
				}
			}
		refreshSentenceCombos();
		IWorkbenchWindow activeWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWindow != null ? activeWindow.getActivePage() : null;
		IPerspectiveDescriptor descr = activePage != null ? activePage.getPerspective() : null;
		if(descr != null && (descr.getId().equals(Constans.REDSEEDSperspectiveID) || descr.getId().equals(Constans.TALEperspectiveID))){
			IViewPart vp=null;
			if (null!=(vp=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("eu.redseeds.sc.editor.rsl.editors.domain.PhrasePropertyEditor")))
				((PhrasePropertyEditor) vp).refresh();
		}
	}
	
	private void addStatement(NotionDTO notion, PhraseDTO phrase){
		if (null!=notion.getDomainStatementDTO(phrase)) return;
		if (!RemoteJGWNL.getInstance().isConnected()) return;
		AddStatementToNotionAction act = new AddStatementToNotionAction(SCProjectContainer.instance().getSCProject(scenario));
		act.setNotion(notion);
		act.setPhrase(phrase.copy(true));
		act.run();
	}

	private NotionDTO addNotion(NounPhraseDTO phraseForNotion){
		BusinessLayerFacade facade = (BusinessLayerFacade) ((ConstrainedLanguageScenario) scenario).getGraph();
		NotionDTO notion=null;
		if (null!=(notion=facade.getNotionDTO(phraseForNotion))) return notion;
		if (!RemoteJGWNL.getInstance().isConnected()) return null;
		AddDomainElementAction action = new AddDomainElementAction(SCProjectContainer.instance().getSCProject(scenario));
		notion = facade.createNotionDTO();
		NounPhraseDTO notionPhrase = facade.createNounPhraseDTO();
		TermSenseDTO ts = RemoteJGWNL.getInstance().getTermSenseDTO(phraseForNotion.getNoun().getSynonymUid());
		NounDTO notionNoun = facade.createNounDTO(ts.getLemma());
		notionNoun.setSynonymUid(ts.getUid());
		notionPhrase.setNoun(notionNoun);
		((BusinessLayerFacade)((NounImpl) notionNoun).getGraph()).cleanNouns(notionNoun);
		notion.setNamePhrase(notionPhrase);
		NotionKindHelper.setDefaultNotionKindInScenario(notion,false);
		action.setDomainElement(notion);
		action.run();
		return notion;
	}
	
}
