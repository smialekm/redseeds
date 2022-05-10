package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import java.util.Arrays;
import java.util.EventObject;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.actions.PropertiesOpenAction;
import eu.redseeds.sc.editor.rsl.editors.UseCaseScenarioView;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars.SVOSentenceGrammar;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.SVOSentenceProvider;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.ActorOrSystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.ActionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.SentenceTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;
import eu.remics.recovery.model.preferences.MConfiguration;

public abstract class SVOSentenceWidget extends Composite implements ScenarioSentenceWidget, ITextMarkedListener {

	private static final Color grey = new Color(null, 235, 235, 235);
	private Text sentenceNumber;
	private UseCaseScenarioView scenarioView;
	private Combo recipient;
	private Combo sentenceType;
	private Combo actionType;

	public SVOSentenceWidget(Composite parent, int style,
			SVOSentenceDTO sentence, UseCaseScenarioView scenView) {
		super(parent, style);
		initialized(sentence,scenView);
	}

	/**
	 * this method will be call after {@link #initSentenceWidget(SVOSentenceProvider)}
	 * @param scenView
	 */
	protected abstract void addAdditionalFunctionalityToSentenceWidget(UseCaseScenarioView scenView);

	public void addKeyPressedListenener(KeyListener l) {
		getSentenceWidget().addKeyPressedListenener(l);
	}

	public void addModifyListener(ModifyListener l) {
		getSentenceWidget().addModifyListener(l);
	}

	private GridData createGridData1(){
		GridData gridData1 = new GridData();

		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.verticalAlignment = GridData.FILL;

		gridData1.grabExcessHorizontalSpace = true;
		return gridData1;
	}

	private GridData createGridData2(){
		GridData gridData2 = new GridData();

		gridData2.horizontalAlignment = GridData.GRAB_HORIZONTAL;
		gridData2.verticalAlignment = GridData.FILL;

		gridData2.grabExcessHorizontalSpace = false;
		
		return gridData2;
	}
	
	private GridData createGridData3(){
		GridData gridData3 = new GridData();

		gridData3.horizontalAlignment = GridData.GRAB_HORIZONTAL;
		gridData3.verticalAlignment = GridData.FILL;

		gridData3.grabExcessHorizontalSpace = false;
		
		gridData3.widthHint=147;
		
		return gridData3;
	}
	
	private GridData createGridData4(){
		GridData gridData4 = new GridData();

		gridData4.horizontalAlignment = GridData.GRAB_HORIZONTAL;
		gridData4.verticalAlignment = GridData.FILL;

		gridData4.grabExcessHorizontalSpace = false;
		
		gridData4.widthHint=40;

		return gridData4;
	}

	public void focus() {
		getSentenceWidget().focus();
	}



	@Override
	public ConstrainedLanguageSentenceDTO getSentence() {
		Object sent = getSentenceWidget().getMarker().getContentProvider()
				.getSentence();
		if (sent instanceof ConstrainedLanguageSentenceDTO)
			return (ConstrainedLanguageSentenceDTO) sent;
		return null;
	}
	public abstract GenericSentenceWidgetAbstract getSentenceWidget();
	public boolean hasFocusIncChildren() {
		for (Control c : this.getChildren())
			if (c.isFocusControl())
				return true;
		return getSentenceWidget().hasFocusIncChildren();

	}

	private void initialized(SVOSentenceDTO sentence, UseCaseScenarioView scenView){
		this.scenarioView = scenView;
		GridLayout gridLayout = new GridLayout();

		gridLayout.numColumns = 5;
		gridLayout.marginLeft = 5;
		gridLayout.marginRight = 5;
		gridLayout.marginBottom = 0;
		gridLayout.marginTop = 0;
		gridLayout.marginHeight = 0;
		gridLayout.verticalSpacing = 0;
		// gridLayout.makeColumnsEqualWidth=true;

		setLayout(gridLayout);
		setBackground(new Color(null, 255, 255, 255));

		GridData gridData = new GridData();

		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.FILL;

		gridData.grabExcessHorizontalSpace = false;

		sentenceNumber = new Text(this, SWT.NONE);
		sentenceNumber.setText("n");

		sentenceNumber.setLayoutData(gridData);

		SVOSentenceProvider prov = new SVOSentenceProvider();
		prov.setBLFacade(scenarioView.getEditor().getFacade());
		prov.setSentence(sentence);

		//initialized SentenceWidget
		initSentenceWidget(prov);
		addAdditionalFunctionalityToSentenceWidget(scenView);
		GenericSentenceWidgetAbstract sentenceWidget=getSentenceWidget();
		sentenceWidget.getMarker().setSentenceProvider(prov);
		prov.addEventListener(this);
		sentenceWidget.setLayoutData(createGridData1());

		initAditionalCombos();
		
		initRecipientCombo();
		initSentenceWidgetMouseListener(sentenceWidget);
		addSelectionAwareMouseListener(sentenceWidget);

		if (sentence.getOwningScenarios().size() > 1){
			sentenceWidget.setInnerBackground(grey);
		}


	}
	
	private void initAditionalCombos(){
		if (MConfiguration.isHideSentenceTypeCombo()) return;
		sentenceType = new Combo(this, SWT.READ_ONLY);
		setSentenceTypeData();
		sentenceType.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}

			@Override
			public void widgetSelected(SelectionEvent e) {
				BusinessLayerFacade facade = (BusinessLayerFacade) ((SVOSentence) getSentence()).getGraph();
				NotionDTO not = facade.getNotionDTO(((SVOSentenceDTO) getSentence()).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) getSentence()).getPredicate()).getObject():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) getSentence()).getPredicate()).getSimpleVerbPhrase().getObject());
				NotionDTO not2 = null;
				if (((SVOSentenceDTO) getSentence()).getPredicate() instanceof ComplexVerbPhraseDTO) not2 = facade.getNotionDTO(((ComplexVerbPhraseDTO) ((SVOSentenceDTO) getSentence()).getPredicate()).getObject());
				SentenceTypesEnum sentType = ((SentenceTypesEnum[]) sentenceType.getData())[sentenceType.getSelectionIndex()];
				String type = (null==not2 || !sentType.complex() || !sentType.reverseInComplex())?(null!=sentType.notionType()?sentType.notionType().tag():""):(null!=sentType.secondaryNotionType()?sentType.secondaryNotionType().tag():"");
				boolean refresh = false;
				if (!type.equals(not.getType())){
					for (Stereotype s:((Notion) not).getStereotypeList())
						if (Arrays.asList(NotionTypesEnum.tags()).contains(s.getName()))
							s.delete();
					if (!type.isEmpty()) {
						Stereotype stereotype = facade.createSclkernel$Stereotype();
						stereotype.setName(type);
						((Notion)not).addStereotype(stereotype);
					}
					if (MConfiguration.isAssignCruds())
							MNotion.clearActionTypes(not,type);
					refresh = true;
				}
				if (null!=not2 && sentType.complex()){
					type=(!sentType.reverseInComplex())?(null!=sentType.secondaryNotionType()?sentType.secondaryNotionType().tag():""):(null!=sentType.notionType()?sentType.notionType().tag():"");
					if(!type.equals(not2.getType())){
						for (Stereotype s:((Notion) not2).getStereotypeList())
							if (Arrays.asList(NotionTypesEnum.tags()).contains(s.getName()))
								s.delete();
						if(!type.isEmpty()) {
							Stereotype stereotype = facade.createSclkernel$Stereotype();
							stereotype.setName(type);
							((Notion)not2).addStereotype(stereotype);
						}
						if (MConfiguration.isAssignCruds())
							MNotion.clearActionTypes(not2,type);
						refresh = true;
					}
				}
				if (refresh) scenarioView.refreshSentenceTypes();
			}
		});
		sentenceType.setLayoutData(createGridData3());
		
		actionType = new Combo(this, SWT.READ_ONLY);
		setActionTypeData();
		actionType.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}

			@Override
			public void widgetSelected(SelectionEvent e) {
				BusinessLayerFacade facade = (BusinessLayerFacade) ((SVOSentence) getSentence()).getGraph();
				DomainStatementDTO ds=facade.getDomainStatementByPhrase(((SVOSentenceDTO) getSentence()).getPredicate());
				String type = "";
				if (actionType.getSelectionIndex()>0) type = ((ActionTypesEnum[]) actionType.getData())[actionType.getSelectionIndex()-1].tag();
				if (!type.equals(ds.getActionTypeTag())){
					MNotion.setActionType(ds, type);
					scenarioView.refreshActionTypes();
				}
			}
		});
		actionType.setLayoutData(createGridData4());
	}

	public void setSentenceTypeData() {
		BusinessLayerFacade facade = (BusinessLayerFacade) ((SVOSentence) getSentence()).getGraph();
		NotionDTO not = null, not2 = null;
		NounPhraseDTO np = null, np2 = null;
		if (null!=((SVOSentenceDTO) getSentence()).getPredicate()) np = ((SVOSentenceDTO) getSentence()).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) getSentence()).getPredicate()).getObject():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) getSentence()).getPredicate()).getSimpleVerbPhrase().getObject();
		if (null!=np){
			not=facade.getNotionDTO(np);
			if (((SVOSentenceDTO) getSentence()).getPredicate() instanceof ComplexVerbPhraseDTO) np2=((ComplexVerbPhraseDTO) ((SVOSentenceDTO) getSentence()).getPredicate()).getObject();
			if (null!=np2) not2=facade.getNotionDTO(np2);
		}
		if (null!=facade.getActorDTO(((SVOSentenceDTO) getSentence()).getSubject()) && null!=not){
			sentenceType.removeAll();
			sentenceType.setData(SentenceTypesEnum.values(true));
			for (SentenceTypesEnum val:SentenceTypesEnum.values(true)) sentenceType.add(val.toString());
			int num = SentenceTypesEnum.getSentenceTypeNumber(not, not2, true);
			if (-1!=num) sentenceType.select(num);
			else sentenceType.deselectAll();
		} else if (null!=facade.getSystemElementDTO(((SVOSentenceDTO) getSentence()).getSubject()) && null!=not){
			sentenceType.removeAll();
			sentenceType.setData(SentenceTypesEnum.values(false));
			for (SentenceTypesEnum val:SentenceTypesEnum.values(false)) sentenceType.add(val.toString());
			int num = SentenceTypesEnum.getSentenceTypeNumber(not, not2, false);
			if (-1!=num) sentenceType.select(num);
			else sentenceType.deselectAll();
		} else {
			sentenceType.removeAll();
			sentenceType.setData(new Object[]{});
		}
	}
	
	public void setActionTypeData() {
		BusinessLayerFacade facade = (BusinessLayerFacade) ((SVOSentence) getSentence()).getGraph();
		DomainStatementDTO ds = null;
		if (-1!=sentenceType.getSelectionIndex() && null!=(ds=facade.getDomainStatementByPhrase(((SVOSentenceDTO) getSentence()).getPredicate()))){
			ActionTypesEnum[] data = ActionTypesEnum.values((null!=((SentenceTypesEnum[]) sentenceType.getData())[sentenceType.getSelectionIndex()].notionType()?((SentenceTypesEnum[]) sentenceType.getData())[sentenceType.getSelectionIndex()].notionType().tag():""),null!=facade.getActorDTO(((SVOSentenceDTO) getSentence()).getSubject()));
			actionType.removeAll();
			actionType.setData(data);
			actionType.add(ActionTypesEnum.EMPTY);
			for (ActionTypesEnum val:data) actionType.add(val.toString());
			if (0!=data.length) actionType.select(ActionTypesEnum.getActionTypeNumber(ds));
			else actionType.select(0);
		} else {
			actionType.removeAll();
			actionType.setData(new Object[]{});
		}
	}

	private void initRecipientCombo(){
		recipient = new Combo(this, SWT.READ_ONLY);
		setRecipientData();
		recipient.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				scenarioView.getEditor().backupScenariosIfNotDirty();
				((SVOSentenceDTO) getSentence())
						.setRecipient(((List<ActorOrSystemElementDTO>) recipient
								.getData()).get(recipient.getSelectionIndex()));
				scenarioView.getEditor().setDirty(true);
			}
		});
		recipient.setLayoutData(createGridData2());
	}

	private void setRecipientData(){
		recipient.removeAll();
		// System.out.println(scenarioView.getEditor().getFacade().getAllRecipientsNames());
		recipient.setData(scenarioView.getEditor().getFacade()
				.getAllRecipients());
/*		recipient
				.setItems(((List<ActorOrSystemElementDTO>) recipient.getData())
						.toArray(new String[0]));*/
		for (ActorOrSystemElementDTO ase: scenarioView.getEditor().getFacade()
				.getAllRecipients()){
			recipient.add(ase.toString());
				}

		if (((SVOSentenceDTO) getSentence()).getRecipient() != null)
			recipient.select(((List<ActorOrSystemElementDTO>) recipient
					.getData()).indexOf(((SVOSentenceDTO) getSentence())
					.getRecipient()));
	}
	
	protected abstract void initSentenceWidget(SVOSentenceProvider prov);

	private void initSentenceWidgetMouseListener(final GenericSentenceWidgetAbstract sentenceWidget){
		sentenceWidget.addMouseListener(new MouseListener() {
			public void mouseDoubleClick(MouseEvent e) {
				int lastMarkPosition = sentenceWidget.getMarker()
						.getLastMarkPosition();
				int offset = sentenceWidget.getOffsetAtLocation(new Point(e.x,
						e.y));
				if (offset == -1)
					return;
				if (offset > lastMarkPosition)
					return;
				SortedMap<Object, Integer> offsets = sentenceWidget.getMarker()
						.getStatesOffsets();

				if (offsets.containsKey(SVOSentenceGrammar.States.Noun1)) {

					int nounOffset = 0;
					for (Map.Entry<Object, Integer> entry : offsets.entrySet()) {
						if (entry.getKey().equals(
								SVOSentenceGrammar.States.Noun1))
							break;
						nounOffset = entry.getValue();
					}

					int sentLenght = //prevent from null subject
						((SVOSentenceDTO) sentenceWidget.getMarker().getContentProvider().getSentence()).getSubject()!=null?
					((SVOSentenceDTO) sentenceWidget.getMarker().getContentProvider().getSentence()).getSubject().getNounText().length():
						0;
					if (offset < nounOffset + sentLenght) {
						SVOSentenceDTO sent = (SVOSentenceDTO) sentenceWidget
								.getMarker().getContentProvider().getSentence();
						PhraseDTO sub = sent.getSubject(); //null subject is tested inside openPhraseProperty method
						openPhraseProperty(sub);
						return;
					}

				}


				if (offsets.containsKey(SVOSentenceGrammar.States.Verb)) {
					if (((SVOSentenceDTO) sentenceWidget.getMarker()
							.getContentProvider().getSentence())
							.getSentenceText().trim().length() > 0)
						return;
					int verbOffset = 0;
					for (Map.Entry<Object, Integer> entry : offsets.entrySet()) {
						if (entry.getKey().equals(
								SVOSentenceGrammar.States.Verb))
							break;
						verbOffset = entry.getValue();
					}
					if (offset > verbOffset) {
						SVOSentenceDTO sent = (SVOSentenceDTO) sentenceWidget
								.getMarker().getContentProvider().getSentence();
						PhraseDTO pre = sent.getPredicate();//null predicate is checked in openPhraseProperty method
						openPhraseProperty(pre);
						return;
					}
				}

			}

			public void mouseDown(MouseEvent e) {
			}

			public void mouseUp(MouseEvent e) {
			}
		});
	}

	private void addSelectionAwareMouseListener(Control composite){
		composite.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
			@Override
			public void mouseDown(MouseEvent e) {
				if(scenarioView!=null){
					scenarioView.rmvSelectionFormAllOtherSentenceWidgets(SVOSentenceWidget.this);
				}
			}
			@Override
			public void mouseUp(MouseEvent e) {
			}});
	}

	private void openPhraseProperty(PhraseDTO ph) {
		boolean valid = false;
		if (ph != null) {
			if (ph instanceof NounPhraseDTO)
				valid = validateNounPhrase((NounPhraseDTO) ph);
			else if (ph instanceof SimpleVerbPhraseDTO)
				valid = validateSimpleVerbPhrase((SimpleVerbPhraseDTO) ph);
			else if (ph instanceof ComplexVerbPhraseDTO)
				valid = validateComplexVerbPhrase((ComplexVerbPhraseDTO) ph);

			if (valid) {
				ph.connect();
				refreshCombos();
				// tstraszak: opening property view for the given phrase
				PropertiesOpenAction openPropertyAction = new PropertiesOpenAction();
				openPropertyAction.setProject(scenarioView.getEditor()
						.getScProject());
				openPropertyAction.setParent(scenarioView.getEditor());
				openPropertyAction.setPhraseOrTerm(ph);
				openPropertyAction.setListener(scenarioView);
				openPropertyAction.run();
			}

		}
	}

	@Override
	public void refreshWidget() {
		if (((SVOSentenceDTO) getSentenceWidget().getMarker().getContentProvider()
				.getSentence()).getOwningScenarios().size() > 1)
			setInnerBackground(grey);
		else
			setInnerBackground(white);
		disableFields();
		setSentenceNumber();
		getSentenceWidget().resetStyleRanges();
	}
	
	public void refreshCombos(){
		if (null!=sentenceType) setSentenceTypeData();
		if (null!=actionType) setActionTypeData();
		setRecipientData();
	}

	public void removeKeyPressedListenener(KeyListener l) {
		getSentenceWidget().removeKeyPressedListenener(l);
	}

	public void removeModifyListener(ModifyListener l) {
		getSentenceWidget().removeModifyListener(l);
	}

	public void setInnerBackground(Color c) {
		getSentenceWidget().setInnerBackground(c);
		this.setBackground(c);
		this.sentenceNumber.setBackground(c);
		this.recipient.setBackground(c);
	}

	public void setSentenceNumber() {

		int firstNumber = 0;
		int secondNumber = 0;
		int letter = 0;

		List<ConstrainedLanguageScenarioDTO> allScenarios = scenarioView
				.getEditor().getUseCase()
				.getConstrainedLanguageScenarioDTOList();
		ConstrainedLanguageScenarioDTO firstScen = allScenarios.get(0);

		SVOSentenceDTO sent = (SVOSentenceDTO) this.getSentenceWidget().getMarker()
				.getContentProvider().getSentence();
		List<ConstrainedLanguageScenarioDTO> owningScen = sent
				.getOwningScenarios();
		ConstrainedLanguageScenarioDTO firstSentScen = owningScen.get(0);

		letter = allScenarios.indexOf(firstSentScen);

		for (ConstrainedLanguageSentenceDTO tmpSent : firstSentScen
				.getScenarioSentenceList()) {
			if (tmpSent instanceof SVOSentenceDTO) {
				ConstrainedLanguageScenarioDTO tmpScen = tmpSent
						.getOwningScenarios().get(0);
				if (tmpScen.equals(firstScen))
					firstNumber++;
				if (tmpScen.equals(firstSentScen))
					secondNumber++;
				if (tmpSent.equals(sent))
					break;
			}
		}

		if (firstSentScen.equals(firstScen))
			this.sentenceNumber.setText(firstNumber + ".");
		else
			this.sentenceNumber.setText(firstNumber + "." + letter + "."
					+ secondNumber);
	}

	public void setSentenceNumber(String number) {
		this.sentenceNumber.setText(number);
	}

	boolean validateComplexVerbPhrase(ComplexVerbPhraseDTO cvf) {
		if (cvf.getPreposition() == null)
			return false;
		if (cvf.getPreposition().getName() == null)
			return false;
		if (cvf.getPreposition().getName().equals(""))
			return false;
		if (!validateNounPhrase(cvf.getObject()))
			return false;
		return validateSimpleVerbPhrase(cvf.getSimpleVerbPhrase());
	}

	boolean validateNounPhrase(NounPhraseDTO nf) {
		if (nf == null)
			return false;
		if (nf.getNounText() == null)
			return false;
		if (nf.getNounText().replace('_', ' ').trim().equals(""))
			return false;
		return true;
	}

	boolean validateSimpleVerbPhrase(SimpleVerbPhraseDTO svf) {
		if (svf.getVerb() == null)
			return false;
		if (svf.getVerb().getName() == null)
			return false;
		if (svf.getVerb().getName().equals(""))
			return false;
		return validateNounPhrase(svf.getObject());
	}

	public boolean disableFields() {
		BusinessLayerFacade facade = SCProjectContainer.instance()
				.getSCProject(getSentence()).getBusinessLayerFacade();
		boolean isClipboradMember = facade.isAnyClipboardMember(getSentence());
		if (isClipboradMember) {
			sentenceNumber.setEnabled(false);
			recipient.setEnabled(false);
			if (null!=sentenceType) sentenceType.setEnabled(false);
			if (null!=actionType) actionType.setEnabled(false);
		} else {
			sentenceNumber.setEnabled(true);
			recipient.setEnabled(true);
			if (null!=sentenceType) sentenceType.setEnabled(true);
			if (null!=actionType) actionType.setEnabled(true);
		}
		sentenceNumber.setEditable(false);
		return isClipboradMember;
	}

	public abstract void removeSelection();

	public UseCaseScenarioView getScenarioView() {
		return scenarioView;
	}
	
	@Override
	public void handleTextMarkedEvent(EventObject e) {
		refreshCombos();
	}

}
