package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.common.QueriesHelper;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.editor.rsl.actions.PropertiesOpenAction;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.GenericSentenceWidget;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.PhraseProvider;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.ActionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;
import eu.remics.recovery.model.preferences.MConfiguration;

public class DomainStatementDialog extends Dialog {

	//private Text domainStatementNameText;

	private Text domainStatementDescText;

	private GenericSentenceWidget sentenceWidget;

	private DomainStatementDTO domStat;

	private SCProject scProject;

	private PhraseDTO phrase;
	
	private PhraseDTO oldPhrase;

	private NotionDTO notion;

	private boolean statementExists = false;

	private TerminologyWidget tw;
	
	private QueryWidget qw;


	public DomainStatementDialog(Shell parentShell) {
		super(parentShell);

	}

	protected Control createDialogArea(Composite parent) {
		getShell().setText("Domain Statement Editor");
	    Composite comp = (Composite) super.createDialogArea(parent);
	    GridLayout layout = (GridLayout) comp.getLayout();
	    layout.numColumns = 2;

	    GridData data = new GridData(70, 20);

	    Label domainStatementNameLabel = new Label(comp, SWT.RIGHT);
	    domainStatementNameLabel.setText("Name: ");
	    domainStatementNameLabel.setLayoutData(data);

	    data = new GridData(515, 20);
	    if (domStat == null){
	    	statementExists = false;
			PhraseProvider prov = new PhraseProvider();
			prov.setBLFacade(scProject.getBusinessLayerFacade());

			sentenceWidget = new GenericSentenceWidget(comp, SWT.BORDER, prov);
			sentenceWidget.setLayoutData(data);
			sentenceWidget.getMarker().setSentenceProvider(prov);

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

					phrase = (PhraseDTO)sentenceWidget.getMarker().getContentProvider().getSentence();
					tw.setTerm(term);
					tw.setPhrase(phrase);
					tw.setEnabled(true);
					tw.refresh();
					
					if (notion.isDomainNotion()){
						qw.setPhrase(phrase instanceof VerbPhraseDTO?(VerbPhraseDTO) phrase:null);
						qw.refresh();
					}
					
				}

				public void mouseUp(MouseEvent e) {
				}

				public void mouseDoubleClick(MouseEvent e) {
				}
			});

	    } else {
	    	statementExists = true;
	    	PhraseProvider prov = new PhraseProvider();
			prov.setBLFacade(scProject.getBusinessLayerFacade());
			prov.setSentence(domStat.getPhraseDTO());

	    	sentenceWidget = new GenericSentenceWidget(comp, SWT.BORDER, prov);
			sentenceWidget.setLayoutData(data);
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

					phrase = (PhraseDTO)sentenceWidget.getMarker().getContentProvider().getSentence();
					tw.setTerm(term);
					tw.setPhrase(phrase);
					tw.setEnabled(true);
					tw.refresh();
					
					if (notion.isDomainNotion()){
						qw.setPhrase(phrase instanceof VerbPhraseDTO?(VerbPhraseDTO) phrase:null);
						qw.refresh();
					}
					
				}

				public void mouseUp(MouseEvent e) {
				}

				public void mouseDoubleClick(MouseEvent e) {
				}
			});

			//sentenceWidget.getMarker().getContentProvider().setSentence(domStat.getPhraseDTO());
	    }

	    data = new GridData(70, MConfiguration.isEnableRSLDL()?169:200);
	    Label domainStatementDescLabel = new Label(comp, SWT.RIGHT | SWT.TOP);
	    domainStatementDescLabel.setText("Description: ");
	    domainStatementDescLabel.setLayoutData(data);

	    domainStatementDescText = new Text(comp, SWT.WRAP | SWT.V_SCROLL | SWT.MULTI | SWT.BORDER);
	    //domainStatementDescText.computeSize(100, 100);
	    data = new GridData(495,MConfiguration.isEnableRSLDL()?169:200);

	    if (domStat != null){
	    	domainStatementDescText.setText(DiagramFileHelper.isEnableRSLDL()?QueriesHelper.getDescription(domStat.getDescription()):domStat.getDescription());
	    } else {
	    	domainStatementDescText.setText("");
	    }
	    domainStatementDescText.setLayoutData(data);

	    if (MConfiguration.isEnableRSLDL()){
	    	data = new GridData(70, 23);
	    	Label emptyLabel = new Label(comp, SWT.RIGHT | SWT.TOP);
		    emptyLabel.setText("Query:");
		    emptyLabel.setLayoutData(data);
	    	data = new GridData(495, 23);
	    	qw = new QueryWidget(comp);
	    	qw.setData(data);
	    	if ((null!=domStat && domStat.getPhraseDTO() instanceof NounPhraseDTO) || !notion.isDomainNotion())
	    		qw.setEnabled(false);
	    	else {
	    		qw.setEnabled(true);
	    		if (null!=domStat){
		    		qw.setPhrase((VerbPhraseDTO) domStat.getPhraseDTO());
		    		qw.setActionType(ActionTypesEnum.getActionTypeByTag(domStat.getActionTypeTag()));
		    		qw.initialize(QueriesHelper.getParameters(domStat.getDescription()));
		    		qw.setCodeLinks(QueriesHelper.getCodeLinks(domStat.getDescription()));
		    		qw.setCodeLink(QueriesHelper.getCodeLink(domStat.getDescription()));
		    	}
	    	}	
	    }
	    
	    data = new GridData(70, 10);
    	Label emptyLabel = new Label(comp, SWT.RIGHT | SWT.TOP);
	    emptyLabel.setText("");
	    emptyLabel.setLayoutData(data);
    	data = new GridData(650, 200);
    	tw = new TerminologyWidget(comp, null);
    	tw.setLayoutData(data);
		tw.setEnabled(false);
		
		disableFields();

	    return comp;
	  }

	public DomainStatementDTO getDomStat() {
		return domStat;
	}

	public void setDomStat(DomainStatementDTO domStat) {
		this.domStat = domStat;
		if (null!=domStat && null!=domStat.getPhraseDTO())
			oldPhrase=domStat.getPhraseDTO().copy(false);
	}

	protected void buttonPressed(int buttonId) {
		if (buttonId == OK) {
			if (sentenceWidget.getMarker().getContentProvider().isValid()) {
				phrase = (PhraseDTO)sentenceWidget.getMarker().getContentProvider().getSentence();
				if (isDomainStatementValid()){
					if (domStat == null) {
						domStat = scProject.getBusinessLayerFacade().createDomainStatementDTO(phrase);
						notion.addDomainStatementDTO(domStat);
						if (MConfiguration.isAssignCruds())
							MNotion.autoAssignActionType(domStat);
						if (DiagramFileHelper.isEnableRSLDL()&&qw.isEnabled()&&null!=qw.getQueryType())
							MNotion.setActionType(domStat, qw.getQueryType().getActionType().tag());	
					}
					addMissingStatements();
					domStat.setDescription(DiagramFileHelper.isEnableRSLDL()&&qw.isEnabled()?qw.getTextForDescription():""+domainStatementDescText.getText());
					if (null!=oldPhrase) oldPhrase.deleteRecursively();
					if (phrase instanceof ComplexVerbPhraseDTO){
						scProject.getBusinessLayerFacade().cleanNouns(((ComplexVerbPhraseDTO) phrase).getObject().getNoun());
						scProject.getBusinessLayerFacade().cleanNouns(((ComplexVerbPhraseDTO) phrase).getSimpleVerbPhrase().getObject().getNoun());
					} else if (phrase instanceof SimpleVerbPhraseDTO) scProject.getBusinessLayerFacade().cleanNouns(((SimpleVerbPhraseDTO) phrase).getObject().getNoun());
					else if (phrase instanceof NounPhraseDTO) scProject.getBusinessLayerFacade().cleanNouns(((NounPhraseDTO) phrase).getNoun());
					scProject.save();
					PropertiesOpenAction openPropertyAction = new PropertiesOpenAction();
					openPropertyAction.setProject(scProject);
					openPropertyAction.setParent(null);
					openPropertyAction.setPhraseOrTerm(domStat.getPhraseDTO());
					openPropertyAction.run();
					SCProjectHelper.refreshSCNavigator();
					close();
				} else {
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ERROR_UNSUPPORTED_FORMAT | SWT.OK);
					errorMB.setMessage("Domain Statement not valid for notion ");
					errorMB.setText("Error");
					errorMB.open();
				}
			} else {
				MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
						SWT.ERROR_UNSUPPORTED_FORMAT | SWT.OK);
				errorMB.setMessage("Domain Statement name not valid");
				errorMB.setText("Error");
				errorMB.open();
			}

		}
		if (buttonId == CANCEL) {
			if (!statementExists){
				if (phrase != null)
					phrase.deleteRecursively();
			} else if (null!=oldPhrase){
				domStat.getPhraseDTO().deleteRecursively();
				domStat.setPhraseDTO(oldPhrase);
			}
			close();
		}

	}

	@Override
	protected Point getInitialSize() {
	        return new Point(730, 450);
	    }

	public SCProject getScProject() {
		return scProject;
	}

	public void setScProject(SCProject scProject) {
		this.scProject = scProject;
	}

	private void addMissingStatements(){
		if (phrase instanceof SimpleVerbPhraseDTO){
			NounPhraseDTO np = ((SimpleVerbPhraseDTO)phrase).getObject();
			DomainStatementDTO ds = notion.getDomainStatementDTO(np);
			if (ds == null){
				ds = scProject.getBusinessLayerFacade().createDomainStatementDTO(np);
				notion.addDomainStatementDTO(ds);
			} else {
				//temporary noun phrase is deleted in SimpleVerbPhrase.setObject() method
				if (!((NounPhraseDTO)ds.getPhraseDTO()).equals(((SimpleVerbPhraseDTO)phrase).getObject()))
				((SimpleVerbPhraseDTO)phrase).setObject((NounPhraseDTO)ds.getPhraseDTO());

			}
		}

		if (phrase instanceof ComplexVerbPhraseDTO){
			NounPhraseDTO np = ((ComplexVerbPhraseDTO)phrase).getObject();
			DomainStatementDTO ds = notion.getDomainStatementDTO(np);
			if (ds == null){
				ds = scProject.getBusinessLayerFacade().createDomainStatementDTO(np);
				notion.addDomainStatementDTO(ds);
			} else {
				//temporary noun phrase is deleted in SimpleVerbPhrase.setObject() method
				if (!((NounPhraseDTO)ds.getPhraseDTO()).equals(((ComplexVerbPhraseDTO)phrase).getObject()))
				((ComplexVerbPhraseDTO)phrase).setObject((NounPhraseDTO)ds.getPhraseDTO());

			}
		}
	}

	private boolean isDomainStatementValid(){
		NounPhraseDTO nounPhrase, cNounPhrase;
		SimpleVerbPhraseDTO sVerbPhrase;
		ComplexVerbPhraseDTO cVerbPhrase;

		if (notion.getNamePhrase() == null)
			return false;

		if (notion.getNamePhrase().getNoun() == null)
			return false;

		if (phrase instanceof NounPhraseDTO){
			nounPhrase = (NounPhraseDTO)phrase;
			notion.getDomainStatementDTOList();
			if (nounPhrase.getNoun() != null){
				if (nounPhrase.getNoun().getSynonymUid() == 0 || !nounPhrase.isConsistent()){
					if (nounPhrase.getNoun().isConsistent()){
						String nounText= nounPhrase.getNounText();
						nounPhrase.setNoun(null);
						nounPhrase.setNounText(nounText);
						nounPhrase.connect();						
					}
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						nounPhrase.getNoun().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) nounPhrase.getNoun().autoAssignSense();
				}
				if (nounPhrase.getNoun().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to noun '"+nounPhrase.getNoun()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!nounPhrase.isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in noun '"+nounPhrase.getNoun()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
			if (!nounPhrase.getNoun().equals(notion.getNamePhrase().getNoun())){
				return false;
			}
			if (nounPhrase.getModifier() != null){
				if (nounPhrase.getModifier().getSynonymUid() == 0 || !nounPhrase.getModifier().isConsistent()){
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						nounPhrase.getModifier().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) nounPhrase.getModifier().autoAssignSense();
				}
				if (nounPhrase.getModifier().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to modifier '"+nounPhrase.getModifier()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!nounPhrase.getModifier().isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in modifier '"+nounPhrase.getModifier()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
			if (nounPhrase.getDeterminer() != null){
				if (nounPhrase.getDeterminer().getSynonymUid() == 0 || !nounPhrase.getDeterminer().isConsistent()){
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag())try {
						nounPhrase.getDeterminer().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) nounPhrase.getDeterminer().autoAssignSense();
				}
				if (nounPhrase.getDeterminer().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to determiner '"+nounPhrase.getDeterminer()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!nounPhrase.getDeterminer().isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in determiner '"+nounPhrase.getDeterminer()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
		}
		if (phrase instanceof SimpleVerbPhraseDTO){
			sVerbPhrase = (SimpleVerbPhraseDTO)phrase;
			nounPhrase = sVerbPhrase.getObject();
			if (nounPhrase.getNoun() != null){
				if (nounPhrase.getNoun().getSynonymUid() == 0 || !nounPhrase.isConsistent()){
					if (nounPhrase.getNoun().isConsistent()){
						String nounText= nounPhrase.getNounText();
						nounPhrase.setNoun(null);
						nounPhrase.setNounText(nounText);
						nounPhrase.connect();						
					}
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						nounPhrase.getNoun().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) nounPhrase.getNoun().autoAssignSense();
				}
				if (nounPhrase.getNoun().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to noun '"+nounPhrase.getNoun()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!nounPhrase.isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in noun '"+nounPhrase.getNoun()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
			if (!nounPhrase.getNoun().equals(notion.getNamePhrase().getNoun())){
				return false;
			}
			if (nounPhrase.getModifier() != null){
				if (nounPhrase.getModifier().getSynonymUid() == 0 || !nounPhrase.getModifier().isConsistent()){
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						nounPhrase.getModifier().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) nounPhrase.getModifier().autoAssignSense();
				}
				if (nounPhrase.getModifier().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to modifier '"+nounPhrase.getModifier()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!nounPhrase.getModifier().isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in modifier '"+nounPhrase.getModifier()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
			if (nounPhrase.getDeterminer() != null){
				if (nounPhrase.getDeterminer().getSynonymUid() == 0 || !nounPhrase.getDeterminer().isConsistent()){
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						nounPhrase.getDeterminer().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) nounPhrase.getDeterminer().autoAssignSense();
				}
				if (nounPhrase.getDeterminer().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to determiner '"+nounPhrase.getDeterminer()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!nounPhrase.getDeterminer().isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in determiner '"+nounPhrase.getDeterminer()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
			if (sVerbPhrase.getVerb() != null){
				if (sVerbPhrase.getVerb().getSynonymUid() == 0 || !sVerbPhrase.getVerb().isConsistent()){
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						sVerbPhrase.getVerb().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) sVerbPhrase.getVerb().autoAssignSense();
				}
				if (sVerbPhrase.getVerb().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to verb '"+sVerbPhrase.getVerb()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!sVerbPhrase.getVerb().isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in verb '"+sVerbPhrase.getVerb()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
		}
		if (phrase instanceof ComplexVerbPhraseDTO){
			cVerbPhrase = (ComplexVerbPhraseDTO)phrase;
			sVerbPhrase = cVerbPhrase.getSimpleVerbPhrase();
			cNounPhrase = cVerbPhrase.getObject();
			nounPhrase = sVerbPhrase.getObject();
			if (nounPhrase.getNoun() != null){
				if (nounPhrase.getNoun().getSynonymUid() == 0 || !nounPhrase.isConsistent()){
					if (nounPhrase.getNoun().isConsistent()){
						String nounText= nounPhrase.getNounText();
						nounPhrase.setNoun(null);
						nounPhrase.setNounText(nounText);
						nounPhrase.connect();						
					}
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						nounPhrase.getNoun().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) nounPhrase.getNoun().autoAssignSense();
				}
				if (nounPhrase.getNoun().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to noun '"+nounPhrase.getNoun()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!nounPhrase.isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in noun '"+nounPhrase.getNoun()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
			if (nounPhrase.getModifier() != null){
				if (nounPhrase.getModifier().getSynonymUid() == 0 || !nounPhrase.getModifier().isConsistent()){
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						nounPhrase.getModifier().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) nounPhrase.getModifier().autoAssignSense();
				}
				if (nounPhrase.getModifier().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to modifier '"+nounPhrase.getModifier()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!nounPhrase.getModifier().isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in modifier '"+nounPhrase.getModifier()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
			if (nounPhrase.getDeterminer() != null){
				if (nounPhrase.getDeterminer().getSynonymUid() == 0 || !nounPhrase.getDeterminer().isConsistent()){
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						nounPhrase.getDeterminer().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) nounPhrase.getDeterminer().autoAssignSense();
				}
				if (nounPhrase.getDeterminer().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to determiner '"+nounPhrase.getDeterminer()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!nounPhrase.getDeterminer().isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in determiner '"+nounPhrase.getDeterminer()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
			if (cNounPhrase.getNoun() != null){
				if (cNounPhrase.getNoun().getSynonymUid() == 0 || !cNounPhrase.isConsistent()){
					if (cNounPhrase.getNoun().isConsistent()){
						String nounText= cNounPhrase.getNounText();
						cNounPhrase.setNoun(null);
						cNounPhrase.setNounText(nounText);
						cNounPhrase.connect();
					}
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						cNounPhrase.getNoun().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) cNounPhrase.getNoun().autoAssignSense();
				}
				if (cNounPhrase.getNoun().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to noun '"+cNounPhrase.getNoun()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!cNounPhrase.isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in noun '"+cNounPhrase.getNoun()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
			if (!cNounPhrase.getNoun().equals(notion.getNamePhrase().getNoun())){
				return false;
			}
			if (cNounPhrase.getModifier() != null){
				if (cNounPhrase.getModifier().getSynonymUid() == 0 || !cNounPhrase.getModifier().isConsistent()){
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						cNounPhrase.getModifier().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) cNounPhrase.getModifier().autoAssignSense();
				}
				if (cNounPhrase.getModifier().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to modifier '"+cNounPhrase.getModifier()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!cNounPhrase.getModifier().isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in modifier '"+cNounPhrase.getModifier()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
			if (cNounPhrase.getDeterminer() != null){
				if (cNounPhrase.getDeterminer().getSynonymUid() == 0 || !cNounPhrase.getDeterminer().isConsistent()){
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
						cNounPhrase.getDeterminer().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) cNounPhrase.getDeterminer().autoAssignSense();
				}
				if (cNounPhrase.getDeterminer().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to determiner '"+cNounPhrase.getDeterminer()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!cNounPhrase.getDeterminer().isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in determiner '"+cNounPhrase.getDeterminer()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
			if (sVerbPhrase.getVerb() != null){
				if (sVerbPhrase.getVerb().getSynonymUid() == 0 || !sVerbPhrase.getVerb().isConsistent()){
					if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try{
						sVerbPhrase.getVerb().autoAddAndAssignSense();
					} catch (NullPointerException e){
						e.printStackTrace();
					}
					else if (SCProjectHelper.getSenseAutoAssigmentFlag()) sVerbPhrase.getVerb().autoAssignSense();
				}
				if (sVerbPhrase.getVerb().getSynonymUid() == 0){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Please assign sense to verb '"+sVerbPhrase.getVerb()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				} else if (!sVerbPhrase.getVerb().isConsistent()){
					MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(),
							SWT.ICON_ERROR  | SWT.OK);
					errorMB.setMessage("Inconsisient name and sense in verb '"+sVerbPhrase.getVerb()+"'.");
					errorMB.setText("Error");
					errorMB.open();
					return false;
				}
			}
		}

		if (!statementExists){
			for (DomainStatementDTO ds : notion.getDomainStatementDTOList()){
				if (ds.getPhraseDTO().equals(phrase)){
					return false;
				}
			}
		}

		return true;
	}

	public NotionDTO getNotion() {
		return notion;
	}

	public void setNotion(NotionDTO notion) {
		this.notion = notion;
	}
	
	/**
	 * If domain statement is in clipboard, fields are disabled/read-only
	 */
	private void disableFields() {
		BusinessLayerFacade facade = scProject.getBusinessLayerFacade();
		boolean enabledFlag = !facade.isAnyClipboardMember(domStat);
		domainStatementDescText.setEditable(enabledFlag);
		domainStatementDescText.setEnabled(enabledFlag);
		sentenceWidget.setEditable(enabledFlag);
		//sentenceWidget.setEnabled(enabledFlag);
	}
}
