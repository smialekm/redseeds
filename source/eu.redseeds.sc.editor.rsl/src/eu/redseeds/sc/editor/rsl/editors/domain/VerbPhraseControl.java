package eu.redseeds.sc.editor.rsl.editors.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.editor.rsl.actions.AbstractProcessSensesAssignmentAction;
import eu.redseeds.sc.editor.rsl.actions.AddDomainElementAction;
import eu.redseeds.sc.editor.rsl.actions.AddStatementToNotionAction;
import eu.redseeds.sc.editor.rsl.actions.ProcessSensesAssignmentActionFactory;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.OpenPhraseListener;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.TerminologyWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.GenericSentenceWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.INewDomainObjectAddedListener;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.NewDomainObjectAddedEvent;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.PhraseProvider;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.impl.rsl.rsldomainelements.terms.NounImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.remics.recovery.model.NotionKindHelper;

public class VerbPhraseControl extends Composite{

	private SCProject scProject;
	private BusinessLayerFacade facade;
	private PhraseDTO phrase;
	private NounPhraseDTO simpleNounPhrase, complexNounPhrase;
	private SimpleVerbPhraseDTO simpleVerbPhrase;
	private ComplexVerbPhraseDTO complexVerbPhrase;
	private PhrasePropertyEditor editor;
	//private String phraseText = "";
	private NotionDTO simpleNotion, complexNotion;
	
	
	private Button addAll;
	private Text directObjectText;
	private Text directNounPhraseText;
	private Text simpleVerbPhraseText;
	private Button addSimpleVerbPhraseButton;
	private Button addDirectObjectButton;
	private Button addNounPhraseButton;
	private Text inDirectObjectText;
	private Text indirectNounPhraseText;
	private Text complexVerbPhraseText;
	private Button addComplexVerbPhraseButton;
	private Button addInDirectObjectButton;
	private Button addIndirectNounPhraseButton;
	

	private static int SIMPLE_NOTION = 1;
	private static int SIMPLE_NOUN_PHRASE = 2;
	private static int SIMPLE_PHRASE = 3;
	private static int COMPLEX_NOTION = 1;
	private static int COMPLEX_NOUN_PHRASE = 2;
	private static int COMPLEX_PHRASE = 3;

	private int simplePhraseExists = 0;
	private int complexPhraseExists = 0;

	private List<INewDomainObjectAddedListener> _listeners = new ArrayList<INewDomainObjectAddedListener>();

	public synchronized void addEventListener(INewDomainObjectAddedListener listener)  {
		_listeners.add(listener);
	}

	public synchronized void removeEventListener(INewDomainObjectAddedListener listener)   {
		_listeners.remove(listener);
	}

	private synchronized void fireEvent() {
		NewDomainObjectAddedEvent event = new NewDomainObjectAddedEvent(this);
	    Iterator<INewDomainObjectAddedListener> i = _listeners.iterator();
	    while(i.hasNext())
	    	((INewDomainObjectAddedListener) i.next()).handleNewDomainObjectEvent(event);

	}

	public VerbPhraseControl(Composite parent, PhrasePropertyEditor editor, PhraseDTO phrase, SCProject scProject) {
		super(parent, SWT.None);
		this.editor = editor;
		this.phrase = phrase;
		this.scProject = scProject;
		this.facade = scProject.getBusinessLayerFacade();
		decomposePhrase();
		createContent();
		
	}

	
	private SelectionListener selListener = new SelectionListener(){
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {

		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (e.widget.getData("name") != null) {
				if (((String) e.widget.getData("name")).equals("AddAll")) {
					if (phrase instanceof NounPhraseDTO
							|| phrase instanceof SimpleVerbPhraseDTO
							|| phrase instanceof ComplexVerbPhraseDTO) {
						if (simplePhraseExists < SIMPLE_NOTION) {

							AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(simpleNounPhrase.getNoun());
							if (!processSensesAction.validateTerm()){
								return;
							}
							addNotion((NounPhraseDTO)simpleNounPhrase);
							decomposePhrase();
						}
						if (simplePhraseExists < SIMPLE_NOUN_PHRASE) {
							if (null!=simpleNotion && simpleNotion
									.getDomainStatementDTO(simpleNounPhrase) == null) {
								AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(simpleNounPhrase);
								if (!processSensesAction.validatePhrase()){
									return;
								}
								addStatement(simpleNotion, (NounPhraseDTO) simpleNounPhrase.copy(true));
								decomposePhrase();
							}
						}
					}
					if (phrase instanceof SimpleVerbPhraseDTO
							|| phrase instanceof ComplexVerbPhraseDTO) {
						if (simplePhraseExists < SIMPLE_PHRASE) {
							if (null!=simpleNotion && simpleNotion
									.getDomainStatementDTO(simpleVerbPhrase) == null) {
								AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(simpleVerbPhrase);
								if (!processSensesAction.validatePhrase()){
									return;
								}

								addStatement(simpleNotion,
										(SimpleVerbPhraseDTO) simpleVerbPhrase.copy(true));
								decomposePhrase();
							}
						}
					}
					if (phrase instanceof ComplexVerbPhraseDTO) {
						if (complexPhraseExists < COMPLEX_NOTION) {
							AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(complexNounPhrase.getNoun());
							if (!processSensesAction.validateTerm()){
								return;
							}
							addNotion((NounPhraseDTO)complexNounPhrase);
							decomposePhrase();
						}
						if (complexPhraseExists < COMPLEX_NOUN_PHRASE) {
							if (null!=complexNotion && complexNotion
									.getDomainStatementDTO(complexNounPhrase) == null) {
								AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(complexNounPhrase);
								if (!processSensesAction.validatePhrase()){
									return;
								}
								addStatement(complexNotion,
										(NounPhraseDTO) complexNounPhrase.copy(true));
								decomposePhrase();
							}
						}
						if (complexPhraseExists < COMPLEX_PHRASE) {
							if (null!=complexNotion && complexNotion
									.getDomainStatementDTO(complexVerbPhrase) == null) {
								AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(complexVerbPhrase);
								if (!processSensesAction.validatePhrase()){
									return;
								}
								addStatement(
										complexNotion,
										(ComplexVerbPhraseDTO) complexVerbPhrase.copy(true));
								decomposePhrase();
							}
						}
					}
					fireEvent();
					return;
				}
			}

			if (e.widget.getData("item") instanceof NounDTO){
				if (((String)e.widget.getData("notion")).equals("simple")){
					AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider((NounDTO)e.widget.getData("item"));
					if (!processSensesAction.validateTerm()){
						return;
					}
					addNotion((NounPhraseDTO)simpleNounPhrase);
					fireEvent();
					return;
				}
				if (((String)e.widget.getData("notion")).equals("complex")){
					AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider((NounDTO)e.widget.getData("item"));
					if (!processSensesAction.validateTerm()){
						return;
					}
					addNotion((NounPhraseDTO)complexNounPhrase);
					fireEvent();
					return;
				}
			}
			if (((String)e.widget.getData("item")).equals("simpleNounPhrase")){
				AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(simpleNounPhrase);
				if (!processSensesAction.validatePhrase()){
					return;
				}
				addStatement((NotionDTO)e.widget.getData("target"), simpleNounPhrase.copy(true));
				fireEvent();
				return;
			}

			if (((String)e.widget.getData("item")).equals("complexNounPhrase")){
				AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(complexNounPhrase);
				if (!processSensesAction.validatePhrase()){
					return;
				}
				addStatement((NotionDTO)e.widget.getData("target"), complexNounPhrase.copy(true));
				fireEvent();
				return;
			}

			if (((String)e.widget.getData("item")).equals("simpleVerbPhrase")){
				AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(simpleVerbPhrase);
				if (!processSensesAction.validatePhrase()){
					return;
				}
				addStatement((NotionDTO)e.widget.getData("target"), simpleVerbPhrase.copy(true));
				fireEvent();
				return;
			}
			if (((String)e.widget.getData("item")).equals("complexVerbPhrase")){
				AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(complexVerbPhrase);
				if (!processSensesAction.validatePhrase()){
					return;
				}
				addStatement((NotionDTO)e.widget.getData("target"), complexVerbPhrase.copy(true));
				fireEvent();
				return;
			}
		}

		private void addStatement(NotionDTO notion, PhraseDTO phrase){
			AddStatementToNotionAction act = new AddStatementToNotionAction(scProject);
			act.setNotion(notion);
			act.setPhrase(phrase);
			act.run();
			editor.clean();
			editor.setContent();
		}

		private void addNotion(NounPhraseDTO phraseForNotion){
			if (null==facade.getNotionDTO(phraseForNotion)){
				AddDomainElementAction action = new AddDomainElementAction(scProject);
				NotionDTO notion = facade.createNotionDTO();
				NounPhraseDTO notionPhrase = facade.createNounPhraseDTO();
				//NounDTO notionNoun = facade.createNounDTO();
				TermSenseDTO ts = RemoteJGWNL.getInstance().getTermSenseDTO(phraseForNotion.getNoun().getSynonymUid());
				NounDTO notionNoun = facade.createNounDTO(ts.getLemma());
				//notionNoun.setName(ts.getLemma());
				notionNoun.setSynonymUid(ts.getUid());
				notionPhrase.setNoun(notionNoun);
				((BusinessLayerFacade)((NounImpl) notionNoun).getGraph()).cleanNouns(notionNoun);
				notion.setNamePhrase(notionPhrase);
				NotionKindHelper.setDefaultNotionKindInScenario(notion,false);
				action.setDomainElement(notion);
				action.run();
			}
			editor.clean();
			editor.setContent();
		}
	};


	private void createContent(){

		if (!phrasesExist()){
			final Label errorLabel = new Label(this, SWT.NONE);
			errorLabel.setBounds(10, 10, 100, 13);
			errorLabel.setText("Phrase is broken");
			errorLabel.setAlignment(SWT.RIGHT);
			return;
		}
		
		// input phrase
		final Label phraseLabel = new Label(this, SWT.NONE);
		phraseLabel.setBounds(10, 10, 100, 13);
		phraseLabel.setText("Predicate:");
		phraseLabel.setAlignment(SWT.RIGHT);

/*		final Label phraseName = new Label(this, SWT.NONE);
		phraseName.setBounds(115, 10, 340, 13);
		phraseName.setText(phraseText);
		phraseName.setBackground(new Color(null, 200, 200, 200));*/

		final TerminologyWidget tw = new TerminologyWidget(this, null);
		tw.setBounds(10, 100, 650, 165);
		tw.setEnabled(false);

		PhraseProvider prov = new PhraseProvider();
		prov.setBLFacade(facade);
		prov.setSentence(phrase);
		final GenericSentenceWidget sentenceWidget = new GenericSentenceWidget(this, SWT.None, prov);
		sentenceWidget.setBounds(115, 10, 340, 18);
		sentenceWidget.setBackground(new Color(null, 200, 200, 200));
		sentenceWidget.getMarker().setSentenceProvider(prov);
		sentenceWidget.setEditable(false);

		sentenceWidget.addMouseListener(new MouseListener() {
			public void mouseDown(MouseEvent e) {
				int lastMarkPosition = sentenceWidget.getMarker()
						.getLastMarkPosition();
				int offset = sentenceWidget.getOffsetAtLocation(new Point(e.x,
						e.y));
				if (offset > lastMarkPosition)
					return;
				if (offset == -1)
					return;
				TermDTO term = ((PhraseProvider)sentenceWidget.getMarker().getContentProvider()).getStateTerm(
				sentenceWidget.getMarker().getStateAtOffset(offset));

				tw.setTerm(term);
				tw.setPhrase(phrase);
				tw.setEnabled(true);
				tw.refresh();

			}

			public void mouseUp(MouseEvent e) {
			}

			public void mouseDoubleClick(MouseEvent e) {
			}
		});

		addAll = new Button(this, SWT.None);
		addAll.setBounds(520, 5, 50, 20);
		addAll.setText("Add all");
		addAll.setData("name", "AddAll");
		addAll.addSelectionListener(selListener);
		addAll.setVisible(false);
		if (phrase instanceof NounPhraseDTO && simplePhraseExists != SIMPLE_NOUN_PHRASE){
			addAll.setVisible(true);
		}
		if (phrase instanceof SimpleVerbPhraseDTO && simplePhraseExists != SIMPLE_PHRASE){
			addAll.setVisible(true);
		}
		if (phrase instanceof ComplexVerbPhraseDTO &&
				(simplePhraseExists != SIMPLE_PHRASE || complexPhraseExists != COMPLEX_PHRASE)){
			addAll.setVisible(true);
		}

		//simple verb phrase
		final Label directObjectLabel = new Label(this, SWT.NONE);
		directObjectLabel.setBounds(10, 40, 100, 13);
		directObjectLabel.setText("Direct object:");
		directObjectLabel.setAlignment(SWT.RIGHT);

		final Label directNounPhraseLabel = new Label(this, SWT.NONE);
		directNounPhraseLabel.setBounds(10, 60, 100, 13);
		directNounPhraseLabel.setText("Noun phrase:");
		directNounPhraseLabel.setAlignment(SWT.RIGHT);

		final Label simpleVerbPhraseLabel = new Label(this, SWT.NONE);
		simpleVerbPhraseLabel.setBounds(10, 80, 100, 13);
		simpleVerbPhraseLabel.setText("Simple verb phrase:");
		simpleVerbPhraseLabel.setAlignment(SWT.RIGHT);
		if (phrase instanceof NounPhraseDTO){
			simpleVerbPhraseLabel.setVisible(false);
		}

		directObjectText = new Text(this, SWT.NONE);
		directObjectText.setBounds(115, 40, 140, 13);
		directObjectText.setEnabled(true);
		if (simpleNotion == null){
			if (simpleNounPhrase != null)
				directObjectText.setText(simpleNounPhrase.getNounText());
		} else {
			directObjectText.setText(simpleNotion.getName());
			OpenPhraseListener openDirectObjectListener = new OpenPhraseListener();
			openDirectObjectListener.setNotionToOpen(simpleNotion);
			openDirectObjectListener.setPhraseToOpen(simpleNotion.getNamePhrase());
			openDirectObjectListener.setEditor(editor);
			directObjectText.addMouseListener(openDirectObjectListener);
		}
		directObjectText.setBackground(new Color(null, 200, 200, 200));
		directObjectText.setEditable(false);
		directObjectText.setEnabled(true);
		
		//directObjectText.addSelectionListener(listener)
		//directObjectText.addModifyListener(modListener);

		directNounPhraseText = new Text(this, SWT.NONE);
		directNounPhraseText.setBounds(115, 60, 140, 13);
		directNounPhraseText.setEnabled(true);
		if (simpleNounPhrase != null){
			directNounPhraseText.setText(simpleNounPhrase.toString());
		}
		directNounPhraseText.setBackground(new Color(null, 200, 200, 200));
		directNounPhraseText.setEditable(false);
		directNounPhraseText.setEnabled(true);
		
		//directNounPhraseText.addModifyListener(modListener);

		if (!(phrase instanceof NounPhraseDTO)){
		simpleVerbPhraseText = new Text(this, SWT.NONE);
		simpleVerbPhraseText.setBounds(115, 80, 140, 13);
		simpleVerbPhraseText.setEnabled(true);
		simpleVerbPhraseText.setText(simpleVerbPhrase.toString());
		simpleVerbPhraseText.setBackground(new Color(null, 200, 200, 200));
		simpleVerbPhraseText.setEditable(false);
		simpleVerbPhraseText.setEnabled(true);

		if (simplePhraseExists < SIMPLE_PHRASE){
			addSimpleVerbPhraseButton = new Button(this, SWT.None);
			addSimpleVerbPhraseButton.setBounds(260, 75, 30, 20);
			addSimpleVerbPhraseButton.setText("Add");
			simpleVerbPhraseText.setBackground(new Color(null, 255, 100, 100));
			if (simplePhraseExists <= SIMPLE_NOUN_PHRASE-1){
				addSimpleVerbPhraseButton.setEnabled(false);
			} else {
				addSimpleVerbPhraseButton.setData("target", simpleNotion);
				addSimpleVerbPhraseButton.setData("item", "simpleVerbPhrase");
				addSimpleVerbPhraseButton.addSelectionListener(selListener);
			}

		} else {
			OpenPhraseListener openSimpleVerbPhraseListener = new OpenPhraseListener();
			openSimpleVerbPhraseListener.setNotionToOpen(simpleNotion);
			openSimpleVerbPhraseListener.setPhraseToOpen(simpleVerbPhrase);
			openSimpleVerbPhraseListener.setEditor(editor);
			simpleVerbPhraseText.addMouseListener(openSimpleVerbPhraseListener);
		}
		}
		//simpleVerbPhraseText.addModifyListener(modListener);

		if (simplePhraseExists  < SIMPLE_NOTION){
			addDirectObjectButton = new Button(this, SWT.None);
			addDirectObjectButton.setBounds(260, 35, 30, 20);
			addDirectObjectButton.setText("Add");
			directObjectText.setBackground(new Color(null, 255, 100, 100));
			addDirectObjectButton.setData("target", null);
			addDirectObjectButton.setData("item", simpleNounPhrase.getNoun());
			addDirectObjectButton.setData("notion", "simple");
			addDirectObjectButton.addSelectionListener(selListener);
		}

		if (simplePhraseExists < SIMPLE_NOUN_PHRASE){
			addNounPhraseButton = new Button(this, SWT.None);
			addNounPhraseButton.setBounds(260, 55, 30, 20);
			addNounPhraseButton.setText("Add");
			directNounPhraseText.setBackground(new Color(null, 255, 100, 100));
			if (simplePhraseExists <= SIMPLE_NOTION-1){
				addNounPhraseButton.setEnabled(false);
			} else {
				addNounPhraseButton.setData("target", simpleNotion);
				addNounPhraseButton.setData("item", "simpleNounPhrase");
				addNounPhraseButton.addSelectionListener(selListener);
			}
		} else{
			OpenPhraseListener openDirectNounPhraseListener = new OpenPhraseListener();
			openDirectNounPhraseListener.setNotionToOpen(simpleNotion);
			openDirectNounPhraseListener.setPhraseToOpen(simpleNounPhrase);
			openDirectNounPhraseListener.setEditor(editor);
			directNounPhraseText.addMouseListener(openDirectNounPhraseListener);
		}




		//complex verb phrase
		if (phrase instanceof ComplexVerbPhraseDTO) {
			final Label inDirectObjectLabel = new Label(this, SWT.NONE);
			inDirectObjectLabel.setBounds(320, 40, 100, 13);
			inDirectObjectLabel.setText("Indirect object:");
			inDirectObjectLabel.setAlignment(SWT.RIGHT);

			final Label indirectNounPhraseLabel = new Label(this, SWT.NONE);
			indirectNounPhraseLabel.setBounds(320, 60, 100, 13);
			indirectNounPhraseLabel.setText("Noun phrase:");
			indirectNounPhraseLabel.setAlignment(SWT.RIGHT);

			final Label complexVerbPhraseLabel = new Label(this, SWT.NONE);
			complexVerbPhraseLabel.setBounds(320, 80, 100, 13);
			complexVerbPhraseLabel.setText("Complex verb phrase:");
			complexVerbPhraseLabel.setAlignment(SWT.RIGHT);

			inDirectObjectText = new Text(this, SWT.NONE);
			inDirectObjectText.setBounds(425, 40, 140, 13);
			inDirectObjectText.setEnabled(true);
			if (complexNotion == null){
				if (complexNounPhrase != null)
					inDirectObjectText.setText(complexNounPhrase.getNounText());
			} else {
				inDirectObjectText.setText(complexNotion.getName());
				OpenPhraseListener openIndirectObjectListener = new OpenPhraseListener();
				openIndirectObjectListener.setNotionToOpen(complexNotion);
				openIndirectObjectListener.setPhraseToOpen(complexNotion.getNamePhrase());
				openIndirectObjectListener.setEditor(editor);
				inDirectObjectText.addMouseListener(openIndirectObjectListener);
			}
			inDirectObjectText.setBackground(new Color(null, 200, 200, 200));
			inDirectObjectText.setEditable(false);
			inDirectObjectText.setEnabled(true);
			// inDirectObjectText.addModifyListener(modListener);

			indirectNounPhraseText = new Text(this, SWT.NONE);
			indirectNounPhraseText.setBounds(425, 60, 140, 13);
			indirectNounPhraseText.setEnabled(true);
			if (complexNounPhrase !=null)
				indirectNounPhraseText
					.setText(complexNounPhrase.toString());
			indirectNounPhraseText.setBackground(new Color(null, 200, 200, 200));
			indirectNounPhraseText.setEditable(false);
			indirectNounPhraseText.setEnabled(true);
			// indirectNounPhraseText.addModifyListener(modListener);

			complexVerbPhraseText = new Text(this, SWT.NONE);
			complexVerbPhraseText.setBounds(425, 80, 140, 13);
			complexVerbPhraseText.setEnabled(true);
			complexVerbPhraseText.setText(complexVerbPhrase.toString());
			complexVerbPhraseText.setBackground(new Color(null, 200, 200, 200));
			complexVerbPhraseText.setEditable(false);
			complexVerbPhraseText.setEnabled(true);
			// complexVerbPhraseText.addModifyListener(modListener);

			if (complexPhraseExists  < COMPLEX_NOTION){
				addInDirectObjectButton = new Button(this, SWT.None);
				addInDirectObjectButton.setBounds(570, 35, 30, 20);
				addInDirectObjectButton.setText("Add");
				inDirectObjectText.setBackground(new Color(null, 255, 100, 100));
				addInDirectObjectButton.setData("target", null);
				addInDirectObjectButton.setData("item", complexNounPhrase.getNoun());
				addInDirectObjectButton.setData("notion", "complex");
				addInDirectObjectButton.addSelectionListener(selListener);
			}

			if (complexPhraseExists < COMPLEX_NOUN_PHRASE) {
				addIndirectNounPhraseButton = new Button(this,SWT.None);
				addIndirectNounPhraseButton.setBounds(570, 55, 30, 20);
				addIndirectNounPhraseButton.setText("Add");
				indirectNounPhraseText.setBackground(new Color(null, 255, 100, 100));
				if (complexPhraseExists <= COMPLEX_NOTION-1) {
					addIndirectNounPhraseButton.setEnabled(false);
				} else {
					addIndirectNounPhraseButton.setData("target", complexNotion);
					addIndirectNounPhraseButton.setData("item", "complexNounPhrase");
					addIndirectNounPhraseButton.addSelectionListener(selListener);
				}
			} else {
				OpenPhraseListener openIndirectNounPhraseListener = new OpenPhraseListener();
				openIndirectNounPhraseListener.setNotionToOpen(complexNotion);
				openIndirectNounPhraseListener.setPhraseToOpen(complexNounPhrase);
				openIndirectNounPhraseListener.setEditor(editor);
				indirectNounPhraseText.addMouseListener(openIndirectNounPhraseListener);
			}

			if (complexPhraseExists < COMPLEX_PHRASE) {
				addComplexVerbPhraseButton = new Button(this,SWT.None);
				addComplexVerbPhraseButton.setBounds(570, 75, 30, 20);
				addComplexVerbPhraseButton.setText("Add");
				complexVerbPhraseText.setBackground(new Color(null, 255, 100, 100));
				if (complexPhraseExists <= COMPLEX_NOUN_PHRASE-1 || simplePhraseExists != SIMPLE_PHRASE) {
					addComplexVerbPhraseButton.setEnabled(false);
				} else {
					addComplexVerbPhraseButton.setData("target", complexNotion);
					addComplexVerbPhraseButton.setData("item", "complexVerbPhrase");
					addComplexVerbPhraseButton.addSelectionListener(selListener);
				}
			} else {
				OpenPhraseListener openComplexVerbPhraseListener = new OpenPhraseListener();
				openComplexVerbPhraseListener.setNotionToOpen(complexNotion);
				openComplexVerbPhraseListener.setPhraseToOpen(complexVerbPhrase);
				openComplexVerbPhraseListener.setEditor(editor);
				complexVerbPhraseText.addMouseListener(openComplexVerbPhraseListener);
			}
		}

		enableElements();

	}

	public void setPhrase(VerbPhraseDTO phrase){
		this.phrase = phrase;
		decomposePhrase();
	}

	private void decomposePhrase(){
		if (phrase instanceof NounPhraseDTO){
			simpleNounPhrase = (NounPhraseDTO)phrase;
			//phraseText = simpleNounPhrase.toString();
		}
		if (phrase instanceof SimpleVerbPhraseDTO){
			simpleVerbPhrase = (SimpleVerbPhraseDTO)phrase;
			simpleNounPhrase = simpleVerbPhrase.getObject();
			//phraseText = simpleVerbPhrase.toString();
		}
		if (phrase instanceof ComplexVerbPhraseDTO){
			complexVerbPhrase = (ComplexVerbPhraseDTO)phrase;
			simpleVerbPhrase = ((ComplexVerbPhraseDTO)phrase).getSimpleVerbPhrase();
			simpleNounPhrase = simpleVerbPhrase.getObject();
			complexNounPhrase = complexVerbPhrase.getObject();
			//phraseText = complexVerbPhrase.toString();
		}



		if (phrase instanceof SimpleVerbPhraseDTO || phrase instanceof ComplexVerbPhraseDTO || phrase instanceof NounPhraseDTO){
			// tstraszak: small change 19.09.2008
			//if (simpleNounPhrase.getNoun().getSynonymUid() == 0){
			if (simpleNounPhrase == null){
				simpleNotion = null;
			}
			else {
				simpleNotion = facade.getNotionDTO(simpleNounPhrase);
			}

			//simpleNotion = facade.getNotionDTO(simpleNounPhrase.getNoun());
			if (simpleNotion != null){
				simplePhraseExists = SIMPLE_NOTION;
				List<DomainStatementDTO> statements = simpleNotion.getDomainStatementDTOList();
				for(DomainStatementDTO ds : statements){
//					if (ds.getName() != null)
//					if (ds.getName().equalsIgnoreCase(simpleNounPhrase.toString())){
//						simplePhraseExists = SIMPLE_NOUN_PHRASE;
//					}
					if (ds.getPhraseDTO().equals(simpleNounPhrase)){
						simplePhraseExists = SIMPLE_NOUN_PHRASE;
						simpleNounPhrase = (NounPhraseDTO)ds.getPhraseDTO();
					}

				}
				if (simplePhraseExists == SIMPLE_NOUN_PHRASE && !(phrase instanceof NounPhraseDTO)){
					for(DomainStatementDTO ds : statements){
//						if (ds.getName() != null)
//						if (ds.getName().equalsIgnoreCase(simpleVerbPhrase.toString())){
//							simplePhraseExists = SIMPLE_PHRASE;
//						}
						if (ds.getPhraseDTO().equals(simpleVerbPhrase)){
							simplePhraseExists = SIMPLE_PHRASE;
							simpleVerbPhrase = (SimpleVerbPhraseDTO)ds.getPhraseDTO();
						}

					}
				}
			}
		}


		if (phrase instanceof ComplexVerbPhraseDTO){
			// tstraszak: small change 19.09.2008
			//if (complexNounPhrase.getNoun().getSynonymUid() == 0)
			if (complexNounPhrase == null)
				complexNotion = null;
			else
				complexNotion = facade.getNotionDTO(complexNounPhrase);

			//complexNotion = facade.getNotionDTO(complexNounPhrase.getNoun());
			if (complexNotion != null){
				complexPhraseExists = COMPLEX_NOTION;
				List<DomainStatementDTO> statements = complexNotion.getDomainStatementDTOList();
				for(DomainStatementDTO ds : statements){
//					if (ds.getName() != null)
//					if (ds.getName().equalsIgnoreCase(complexNounPhrase.toString())){
//						complexPhraseExists = COMPLEX_NOUN_PHRASE;
//					}
					if (ds.getPhraseDTO().equals(complexNounPhrase)){
						complexPhraseExists = COMPLEX_NOUN_PHRASE;
						complexNounPhrase = (NounPhraseDTO)ds.getPhraseDTO();
					}
				}
				if (complexPhraseExists == COMPLEX_NOUN_PHRASE){
					for(DomainStatementDTO ds : statements){
//						if (ds.getName() != null)
//						if (ds.getName().equalsIgnoreCase(complexVerbPhrase.toString())){
//							complexPhraseExists = COMPLEX_PHRASE;
//						}
						if (ds.getPhraseDTO().equals(complexVerbPhrase)){
							complexPhraseExists = COMPLEX_PHRASE;
							complexVerbPhrase = (ComplexVerbPhraseDTO)ds.getPhraseDTO();
						}
					}
				}
			}
		}
	}


	public SCProject getScProject() {
		return scProject;
	}

	public void setScProject(SCProject scProject) {
		this.scProject = scProject;
	}

	private void enableElements() {
		if (facade.isAnyClipboardMember(phrase)) {

			if (addAll != null)
				addAll.setEnabled(false);
//			if (directObjectText != null)
//				directObjectText.setEnabled(false);
//			if (directNounPhraseText != null)
//				directNounPhraseText.setEnabled(false);
//			if (simpleVerbPhraseText != null)
//				simpleVerbPhraseText.setEnabled(false);
			if (addDirectObjectButton != null)
				addDirectObjectButton.setEnabled(false);
			if (addSimpleVerbPhraseButton != null)
				addSimpleVerbPhraseButton.setEnabled(false);
			if (addNounPhraseButton != null)
				addNounPhraseButton.setEnabled(false);
//			if (inDirectObjectText != null)
//				inDirectObjectText.setEnabled(false);
//			if (indirectNounPhraseText != null)
//				indirectNounPhraseText.setEnabled(false);
//			if (complexVerbPhraseText != null)
//				complexVerbPhraseText.setEnabled(false);
			if (addComplexVerbPhraseButton != null)
				addComplexVerbPhraseButton.setEnabled(false);
			if (addInDirectObjectButton != null)
				addInDirectObjectButton.setEnabled(false);
			if (addIndirectNounPhraseButton != null)
				addIndirectNounPhraseButton.setEnabled(false);
		} else {
			if (addAll != null)
				addAll.setEnabled(true);
//			if (directObjectText != null)
//				directObjectText.setEnabled(true);
//			if (directNounPhraseText != null)
//				directNounPhraseText.setEnabled(true);
//			if (simpleVerbPhraseText != null)
//				simpleVerbPhraseText.setEnabled(true);
			if (addDirectObjectButton != null)
				addDirectObjectButton.setEnabled(true);
			if (addSimpleVerbPhraseButton != null)
				addSimpleVerbPhraseButton.setEnabled(true);
			if (addNounPhraseButton != null)
				addNounPhraseButton.setEnabled(true);
//			if (inDirectObjectText != null)
//				inDirectObjectText.setEnabled(true);
//			if (indirectNounPhraseText != null)
//				indirectNounPhraseText.setEnabled(true);
//			if (complexVerbPhraseText != null)
//				complexVerbPhraseText.setEnabled(true);
			if (addComplexVerbPhraseButton != null)
				addComplexVerbPhraseButton.setEnabled(true);
			if (addInDirectObjectButton != null)
				addInDirectObjectButton.setEnabled(true);
			if (addIndirectNounPhraseButton != null)
				addIndirectNounPhraseButton.setEnabled(true);
		}
	}
	
	private boolean phrasesExist(){
		if (phrase instanceof NounPhraseDTO){
			if (simpleNounPhrase == null)
				return false;
		}
		if (phrase instanceof SimpleVerbPhraseDTO){
			if (simpleVerbPhrase == null)
				return false;
			if (simpleNounPhrase == null)
				return false;
		}
		if (phrase instanceof ComplexVerbPhraseDTO){
			if (complexVerbPhrase == null)
				return false;
			if (simpleVerbPhrase == null)
				return false;
			if (simpleNounPhrase == null)
				return false;
			if (complexNounPhrase == null)
				return false;
		}
		return true;
	}
}
