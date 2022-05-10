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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.editor.rsl.actions.AbstractProcessSensesAssignmentAction;
import eu.redseeds.sc.editor.rsl.actions.AddDomainElementAction;
import eu.redseeds.sc.editor.rsl.actions.ProcessSensesAssignmentActionFactory;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.TerminologyWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.GenericSentenceWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.INewDomainObjectAddedListener;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.NewDomainObjectAddedEvent;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.PhraseProvider;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

public class NounPhraseControl extends Composite{

	//private DomainStatement domStat;
	private NounPhraseDTO phrase;
	private SCProject scProject;
	private BusinessLayerFacade facade;
	private ActorDTO actor;
	private SystemElementDTO sysElem;
	private int isActorOrSystemElement = 0;
	private int whatToAdd = 0;
	private NounPhraseControl control;
	private String phraseText = "";
	private PhrasePropertyEditor editor;

	private  Button addSysElemButton;
	private  Button addActorButton;
	private  Button systemElemeButton;
	private  Button actorButton;
	private  Button linkButton;
	private  Button openButton;

	private static int IS_ACTOR = 1;
	private static int IS_SYSTEM_ELEMENT = 2;
	private static int IS_ACTOR_AND_SYSTEM_ELEMENT = 3;
	private static int IS_NONE = 0;

	private static int ADD_NONE = 0;
	private static int ADD_ACTOR = 1;
	private static int ADD_SYS_ELEM = 2;
	
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

	public NounPhraseControl(Composite parent, PhrasePropertyEditor editor, NounPhraseDTO phrase, SCProject scProject) {
		super(parent, SWT.None);
		this.phrase = phrase;
		this.scProject = scProject;
		this.facade = scProject.getBusinessLayerFacade();
		this.editor = editor;
		decomposePhrase();
		createContent();
		this.control = this;
	}


	private SelectionListener selListener = new SelectionListener(){
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {

		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (e.widget instanceof Button){
				if (((Button)e.widget).getText().equals("Add Actor")){
					if (!facade.isActorNameValid(phraseText)) {
						MessageBox informMB = new MessageBox(SCProjectHelper.getShell(),
								SWT.ICON_INFORMATION  | SWT.OK);
						informMB.setMessage("A unique name must be specified");
						informMB.open();
						return;
					}
					AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(phrase.getNoun());
					if (!processSensesAction.validateTerm()){
						return;
					}
					if (null==facade.getActorDTO(phrase)){
						actor = facade.createActorDTO();
						NounPhraseDTO phraseWN = (NounPhraseDTO)phrase.copy(true);
						actor.setNamePhrase(phraseWN);
						AddDomainElementAction action = new AddDomainElementAction(scProject);
						action.setDomainElement(actor);
						action.run();
						fireEvent();
					}
					isActorOrSystemElement += IS_ACTOR;
					actorButton.setEnabled(false);
					systemElemeButton.setEnabled(false);
				}
				if (((Button)e.widget).getText().equals("Add System element")){
					if (!facade.isSystemElementNameValid(phraseText)) {
						MessageBox informMB = new MessageBox(SCProjectHelper.getShell(),
								SWT.ICON_INFORMATION  | SWT.OK);
						informMB.setMessage("A unique name must be specified");
						informMB.open();
						return;
					}
					AbstractProcessSensesAssignmentAction processSensesAction = ProcessSensesAssignmentActionFactory.getTermsContentProvider(phrase.getNoun());
					if (!processSensesAction.validateTerm()){
						return;
					}
					if (null==facade.getSystemElementDTO(phrase)){
						sysElem = facade.createSystemElementDTO();
						NounPhraseDTO phraseWN = (NounPhraseDTO)phrase.copy(true);
						sysElem.setNamePhrase(phraseWN);
						AddDomainElementAction action = new AddDomainElementAction(scProject);
						action.setDomainElement(sysElem);
						action.run();
						fireEvent();
					}
					isActorOrSystemElement += IS_SYSTEM_ELEMENT;
					actorButton.setEnabled(false);
					systemElemeButton.setEnabled(false);
				}
			}
			if (actorButton.getSelection()){
				if (isActorOrSystemElement == IS_ACTOR || isActorOrSystemElement == IS_ACTOR_AND_SYSTEM_ELEMENT){
					addActorButton.setVisible(false);
					addSysElemButton.setVisible(false);
					linkButton.setEnabled(true);
				} else {
					addActorButton.setVisible(true);
					addSysElemButton.setVisible(false);
					linkButton.setEnabled(false);
				}
				whatToAdd = ADD_ACTOR;
			} else if (systemElemeButton.getSelection()){
				if (isActorOrSystemElement == IS_SYSTEM_ELEMENT || isActorOrSystemElement == IS_ACTOR_AND_SYSTEM_ELEMENT){
					addActorButton.setVisible(false);
					addSysElemButton.setVisible(false);
					linkButton.setEnabled(true);
				} else {
					addActorButton.setVisible(false);
					addSysElemButton.setVisible(true);
					linkButton.setEnabled(false);
				}
				whatToAdd = ADD_SYS_ELEM;
			} else {
				addActorButton.setVisible(false);
				addSysElemButton.setVisible(false);
				linkButton.setEnabled(false);
				whatToAdd = ADD_NONE;
			}

			if (whatToAdd == ADD_NONE){

			}
			control.layout(false);
		}
	};


	private void createContent(){

		final Label phraseLabel = new Label(this, SWT.NONE);
		phraseLabel.setBounds(10, 10, 100, 13);
		phraseLabel.setText("Subject:");
		phraseLabel.setAlignment(SWT.RIGHT);

/*		final Label phraseName = new Label(this, SWT.NONE);
		phraseName.setBounds(115, 10, 340, 13);
		phraseName.setText(phrase.toString());
		phraseName.setBackground(new Color(null, 200, 200, 200));*/

		TermDTO initialTerm = null;
		if (phrase instanceof NounPhraseDTO){
			initialTerm = ((NounPhraseDTO)phrase).getNoun();
		}
		final TerminologyWidget tw = new TerminologyWidget(this, initialTerm);
		tw.setBounds(10, 60, 650, 165);
		tw.setPhrase(phrase);
		tw.setEnabled(true);
		tw.refresh();

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
				if (offset == -1)
					return;
				if (offset > lastMarkPosition)
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

		linkButton = new Button(this, SWT.None);
		linkButton.setBounds(465, 5, 50, 20);
		linkButton.setText("Link");
		linkButton.setEnabled(false);
		linkButton.setVisible(false);

		actorButton = new Button(this, SWT.RADIO);
		actorButton.setBounds(115, 40, 50, 20);
		actorButton.setText("Actor");
		actorButton.addSelectionListener(selListener);

		systemElemeButton = new Button(this, SWT.RADIO);
		systemElemeButton.setBounds(185, 40, 100, 20);
		systemElemeButton.setText("System element");
		systemElemeButton.addSelectionListener(selListener);

		if (isActorOrSystemElement == IS_ACTOR){
			actorButton.setSelection(true);
			systemElemeButton.setSelection(false);
			linkButton.setEnabled(true);
			actorButton.setEnabled(false);
			systemElemeButton.setEnabled(false);
		}

		if (isActorOrSystemElement == IS_SYSTEM_ELEMENT){
			actorButton.setSelection(false);
			systemElemeButton.setSelection(true);
			linkButton.setEnabled(true);
			actorButton.setEnabled(false);
			systemElemeButton.setEnabled(false);
		}

		if (isActorOrSystemElement == IS_NONE || isActorOrSystemElement == IS_ACTOR_AND_SYSTEM_ELEMENT){
			actorButton.setSelection(false);
			systemElemeButton.setSelection(false);
			linkButton.setEnabled(false);
		}

		addActorButton = new Button(this, SWT.None);
		addActorButton.setBounds(520, 5, 130, 20);
		addActorButton.setText("Add Actor");
		addActorButton.addSelectionListener(selListener);
		addActorButton.setVisible(false);

		addSysElemButton = new Button(this, SWT.None);
		addSysElemButton.setBounds(520, 5, 130, 20);
		addSysElemButton.setText("Add System element");
		addSysElemButton.addSelectionListener(selListener);
		addSysElemButton.setVisible(false);

		if (isActorOrSystemElement > IS_NONE){
			openButton = new Button(this, SWT.None);
			openButton.setBounds(520, 5, 130, 20);
			if (isActorOrSystemElement == IS_ACTOR)
				openButton.setText("Open Actor");
			if (isActorOrSystemElement == IS_SYSTEM_ELEMENT)
				openButton.setText("Open System Element");
			openButton.addSelectionListener(new SelectionListener(){

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					IWorkbenchPage activePage = (IWorkbenchPage) editor.getSite().getPage();
					if (isActorOrSystemElement == IS_ACTOR) {
						ActorEditorInput actorInput = new ActorEditorInput();
						actorInput.setActorDTO(actor);
						ActorEditor actorEditor;
						try {
							actorEditor = (ActorEditor) activePage.openEditor(
									actorInput, ActorEditor.EDITOR_ID, false, 1);
							actorEditor.setActor(actor);
							actorEditor.setFacade(facade);
							actorEditor.setScProject(scProject);
						} catch (PartInitException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (isActorOrSystemElement == IS_SYSTEM_ELEMENT) {
						SystemElementEditorInput sysElemInput = new SystemElementEditorInput();
						sysElemInput.setSysElemDTO(sysElem);
						SystemElementEditor sysElemEditor;
						try {
							sysElemEditor = (SystemElementEditor) activePage.openEditor(sysElemInput, SystemElementEditor.EDITOR_ID, false, 1);
							sysElemEditor.setSysElemDTO(sysElem);
							sysElemEditor.setFacade(facade);
							sysElemEditor.setScProject(scProject);
						} catch (PartInitException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}});
		}

	}

	private void decomposePhrase(){
		if (phrase instanceof NounPhraseDTO){
			phraseText = ((NounPhraseDTO)phrase).toString();
			actor = facade.getActorDTO(phrase);
			sysElem = facade.getSystemElementDTO(phrase);
			if (actor != null)
				isActorOrSystemElement += IS_ACTOR;
			if (sysElem != null)
				isActorOrSystemElement += IS_SYSTEM_ELEMENT;
		}
	}

	public void setPhrase(NounPhraseDTO phrase){
		this.phrase = phrase;
	}

	public SCProject getScProject() {
		return scProject;
	}

	public void setScProject(SCProject scProject) {
		this.scProject = scProject;
	}

}
