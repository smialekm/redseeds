package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.Constants;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.sc.editor.rsl.actions.AddDomainElementAction;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditorInput;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.remics.recovery.model.NotionKindHelper;

/**
 * Listens for double-clicks on domain elements hyperlinks. If a user double-clicked on existing notion opens an editor for that notion. If the notion does not exist it is created. 
 *
 */
public class DomainElementHyperlinkListener implements MouseListener {
	
	private StyledText descriptionText = null;
	private Composite control = null;
	
	/**
	 * @param descriptionText control to create hyperlink in
	 * @param control main control on which styled text control resides
	 */
	public DomainElementHyperlinkListener(StyledText descriptionText, Composite control) {
		super();
		this.descriptionText = descriptionText;
		this.control = control;
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		if(!descriptionText.getText().endsWith("\n")) {
			descriptionText.append("\n");
		}
		int startStyle = 0;
		int endStyle = 0;
		if(descriptionText.getSelectionCount() > 0) {
			startStyle = Math.min(descriptionText.getSelectionRange().x, descriptionText.getSelectionRange().y);
			endStyle = Math.max(descriptionText.getSelectionRange().x, descriptionText.getSelectionRange().y);
		}
		else {
			startStyle = descriptionText.getCaretOffset();
			endStyle = startStyle;
		}
		StyleRange style = null;
		if(endStyle == descriptionText.getText().length()) {
			style = descriptionText.getStyleRangeAtOffset(endStyle - 1);
		}
		else {
			style = descriptionText.getStyleRangeAtOffset(endStyle);
		}
		if(style != null) {//needed when selection is too wide
			startStyle = style.start;
		}
		while(style != null && endStyle <= descriptionText.getText().length() - 1) { //look for style forward from caret position
			style = descriptionText.getStyleRangeAtOffset(++endStyle);
		}
		style = descriptionText.getStyleRangeAtOffset(startStyle); //ensure not null
		while(style != null && startStyle > 0) { //look for style backward from caret position
			style = descriptionText.getStyleRangeAtOffset(--startStyle);
		}
		if(startStyle == 0) {
			descriptionText.setSelectionRange(startStyle, endStyle - startStyle);
		} else {
			descriptionText.setSelectionRange(startStyle + 1, endStyle - startStyle - 1);
		}
		if(startStyle != 0 && endStyle != 0) {
			style = descriptionText.getStyleRanges(startStyle, endStyle - startStyle)[0];
		}
		
		if (style != null) {
			BusinessLayerFacade facade = DomainElementEditorControlFactory.getFacadeForControl(control);
			SCProject project = DomainElementEditorControlFactory.getSCPRojectForControl(control);
			
			String newNotionName = descriptionText.getSelectionText();
			
			if(DomainElementEditorControlFactory.compareColors(
					style.foreground, 
					SCProjectHelper.getShell().getDisplay().getSystemColor(
							Constants.HYPERLINK_FONT_COLOR_TARGET_DOES_NOT_EXIST))) { 
				//notion exists does not exist - create it
				MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				confirmMB.setMessage("Would you like to create a notion '" + newNotionName + "'?");
				confirmMB.setText("Create a notion");
				int result = confirmMB.open();
				if(result == SWT.NO) {
					return;
				}
				createNotion(project, facade, style, newNotionName);
			}
			//notion exists - open editor
			openNotionEditor(project, facade);
		}
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * creates notion corresponding to the selected/double-clicked text in the text widget
	 * @param project
	 * @param facade
	 * @param style used for changing color of a hyperlink, after notion is created
	 */
	private void createNotion(SCProject project, BusinessLayerFacade facade, StyleRange style, String notionName) {
		//create noun phrase with a noun corresponding to selected text 
//		String notionName = descriptionText.getSelectionText(); //for some reason loses selection here
		NounPhraseDTO nounPhrase = facade.createNounPhraseDTO();
		NounDTO noun = facade.createNounDTO(notionName);
		nounPhrase.setNoun(noun);
		//connect the phrase to check if phrases with that noun exist
		nounPhrase.connect();
		//create the notion
		SelectNotionSenseDialog dialog = new SelectNotionSenseDialog(SCProjectHelper.getShell(), nounPhrase);
		dialog.setNotionName(notionName);
		if(dialog.open() == Window.CANCEL) {
			return;
		}
		AddDomainElementAction action = new AddDomainElementAction(project);
		NotionDTO notion = facade.createNotionDTO();
		notion.setNamePhrase(nounPhrase);
		if (0==notion.getNamePhrase().getNoun().getSynonymUid()){
			if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) try {
				notion.getNamePhrase().getNoun().autoAddAndAssignSense();
			} catch (NullPointerException e){
				e.printStackTrace();
			}
			else if (SCProjectHelper.getSenseAutoAssigmentFlag()) notion.getNamePhrase().getNoun().autoAssignSense();
		}
		facade.cleanNouns(notion.getNamePhrase().getNoun());
		NotionKindHelper.setDefaultNotionKind(notion);
		action.setDomainElement(notion);
		action.run();
		style.foreground = SCProjectHelper.getShell().getDisplay().getSystemColor(Constants.HYPERLINK_FONT_COLOR_TARGET_EXISTS);
		style.length = notion.getName().length();
		descriptionText.setStyleRange(style);
		//notion created - link to "current" domain element
		if (control instanceof ActorEditorControl) {
			  if(!((ActorEditorControl) control).getActorDTO().checkIfRelated(notion)) {//create relation if needed
				  ((ActorEditorControl) control).getActorDTO().addRelatedNotion(notion);
			  }
		  } else if (control instanceof NotionEditorControl) {
			  if(!((NotionEditorControl) control).getNotionDTO().checkIfRelated(notion)) {//create relation if needed
				  ((NotionEditorControl) control).getNotionDTO().addRelatedNotion(notion);
			  }
		  } else if (control instanceof SystemElementEditorControl) {
			  if(!((SystemElementEditorControl) control).getSystemElementDTO().checkIfRelated(notion)) {
				((SystemElementEditorControl) control).getSystemElementDTO().addRelatedNotion(notion);
			  }
		 }
	}
	
	/**
	 * opens editor for notion corresponding to the selected/double-clicked text in the text widget
	 * @param project
	 * @param facade
	 */
	private void openNotionEditor(SCProject project, BusinessLayerFacade facade) {
		//determine for which notion editor should be opened
		String notionName = descriptionText.getSelectionText();
		NotionDTO selectedNotion = null;
		if(facade != null) {
        	  List<NotionDTO> notions = facade.getAllNotions();
        	  //prepare lemmas
        	  List<String> lemmas = new ArrayList<String>();//TODO no repetitions in the lists
        	  if(RemoteJGWNL.getInstance().isConnected()) {
	        	  TermSenseDTO[] ts = RemoteJGWNL.getInstance().getTermSenses(notionName, Constants.TERM_NOUN);
	        	  for(TermSenseDTO sense : ts) {
	        		  lemmas.add(sense.getLemma());
	        	  }
        	  }
        	  for(NotionDTO notion : notions) {
        		  //first check by names
        		  if(notion.getName().equalsIgnoreCase(notionName)) {
        			  selectedNotion = notion;
        			  break;
        		  }
        		  //now check by lemmas
	        	  for(String lemma : lemmas) {
		        	  if(notion.getName().equalsIgnoreCase(lemma)) {
		        		  selectedNotion = notion;
		        		  break;
		        	  }
	        	  }
        	  }
        }
		if(selectedNotion == null) {
			//TODO some message?
			return;
		}
		//prepare and open editor
		NotionEditorInput notionInput = new NotionEditorInput();
		notionInput.setNotionDTO(selectedNotion);
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage();
		NotionEditor notionEditor = null ;
		try {
			notionEditor = (NotionEditor) activePage
					.openEditor(notionInput, NotionEditor.EDITOR_ID, false, 1);
		} catch (PartInitException e1) {
			Activator.log("Problem occurred during opening of notion editor: "+e1.getMessage(), IStatus.ERROR);
		}
		if(notionEditor != null) {
			notionEditor.setNotion((NotionDTO) selectedNotion);
			notionEditor.setFacade(facade);
			notionEditor.setScProject(project);
		}
	}

}
