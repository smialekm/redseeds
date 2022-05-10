package eu.redseeds.sc.current.ui.wizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.TerminologyWidget;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;

public class RenameWizardPageSenses extends WizardPage {
	
	protected Composite container;
	private TerminologyWidget tw;
	private ISelection selection;
	private BusinessLayerFacade facade;

	protected RenameWizardPageSenses(ISelection selection) {
		super(ResourceMessages.Rename_windowTitle);
		this.selection = selection;
		setTitle(ResourceMessages.Rename_title);
		setDescription(ResourceMessages.Rename_notion_description);
		facade = SCProjectContainer.instance()
					.getSCProject(
							SCProjectHelper.getIProject((IStructuredSelection)getSelection())
					).getBusinessLayerFacade();
	}
	
	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 1;
		Object selectedObj = getSelectedObject(getSelection());
		if(selectedObj instanceof NotionDTO
				|| selectedObj instanceof ActorDTO
				|| selectedObj instanceof SystemElementDTO) {
		    GridData data = new GridData();
		    Label label = new Label(container, SWT.NONE);
		    label.setText("Notion: "+((RenameWizard)getWizard()).getNewElementName());
		    data = new GridData(GridData.FILL_HORIZONTAL);
			data.horizontalSpan = 1;
		    label.setLayoutData(data);
			
			data = new GridData();
			data.horizontalSpan = 1;
	    	tw = new TerminologyWidget(container, null);
	    	tw.setWizard(this);
	    	tw.addAssignSenseListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void widgetSelected(SelectionEvent e) {
					setPageComplete(true);
				}
	    		
	    	});
	    	tw.setLayoutData(data);
			tw.setEnabled(true);
			data = new GridData(GridData.FILL_HORIZONTAL);
		}
		
		setControl(container);
		setPageComplete(false);
	}

	public ISelection getSelection() {
		return selection;
	}
	
	protected Object getSelectedObject(ISelection selection) {
		if (selection != null) {
			ITreeSelection treeSelection = (ITreeSelection) selection;
			TreePath[] tr = treeSelection.getPaths();
			
			if(tr[0] != null) {
				Object selectedObj = tr[0].getLastSegment();
				return selectedObj;
			}
			else return null;
		}
		else return null;
	}
	
	public void setElementName(String name) {
		((Label)container.getChildren()[0]).setText("Notion: "+name);
		NounPhraseDTO nounPhrase = facade.createNounPhraseDTO();
		NounDTO noun = facade.createNounDTO();
		noun.setName(((RenameWizard)getWizard()).getNewElementName());
		nounPhrase.setNounText(noun.toString());
		nounPhrase.setNoun(noun);
		if (null!=facade.getNoun(noun.getName())) noun.setSynonymUid(facade.getNoun(noun.getName()).getSynonymUid());
		tw.setTerm(nounPhrase.getNoun());
		tw.setPhrase(nounPhrase);
		if (nounPhrase.getNoun().getSynonymUid()==0) setPageComplete(false);
		else setPageComplete(true);
	}
	
	public NounPhraseDTO getNamePhrase() {
		return (NounPhraseDTO)tw.getPhrase();
	}
	
	public void deletePhrase(){
		tw.getPhrase().deleteRecursively();
		tw.setPhrase(null);
	}
	
}
