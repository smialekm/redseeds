package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.PhrasePropertyEditor;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;

public class OpenPhraseListener implements MouseListener {

	private PhraseDTO phraseToOpen = null;
	private NotionDTO notionToOpen = null;
	private BusinessLayerFacade facade = null;
	private SCProject scProject = null;
	private PhrasePropertyEditor editor;

	public OpenPhraseListener() {
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseUp(MouseEvent e) {
		if (notionToOpen != null) {
			scProject = SCProjectContainer.instance()
					.getSCProject(notionToOpen);
			facade = scProject.getBusinessLayerFacade();
		}
		NotionEditorInput notionInput = new NotionEditorInput();

		notionInput.setNotionDTO(notionToOpen);
		IWorkbenchPage activePage = (IWorkbenchPage) editor.getSite().getPage();
		NotionEditor notionEditor;
		try {
			notionEditor = (NotionEditor) activePage.openEditor(notionInput,
					NotionEditor.EDITOR_ID, false, 1);
			notionEditor.setNotion(notionToOpen);
			notionEditor.setFacade(facade);
			notionEditor.setScProject(scProject);
		} catch (PartInitException e1) {
			e1.printStackTrace();
		}

		DomainStatementDTO domStat = notionToOpen
				.getDomainStatementDTO(phraseToOpen);
		DomainStatementDialog dialog = new DomainStatementDialog(
				SCProjectHelper.getShell());
		dialog.setDomStat(domStat);
		dialog.setScProject(scProject);
		dialog.setNotion(notionToOpen);
		dialog.open();
	}

	public void setEditor(PhrasePropertyEditor editor) {
		this.editor = editor;
	}

	public PhraseDTO getPhraseToOpen() {
		return phraseToOpen;
	}

	public void setPhraseToOpen(PhraseDTO phraseToOpen) {
		this.phraseToOpen = phraseToOpen;
	}

	public NotionDTO getNotionToOpen() {
		return notionToOpen;
	}

	public void setNotionToOpen(NotionDTO notionToOpen) {
		this.notionToOpen = notionToOpen;
	}
}