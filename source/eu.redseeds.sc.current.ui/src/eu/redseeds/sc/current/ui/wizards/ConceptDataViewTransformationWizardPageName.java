package eu.redseeds.sc.current.ui.wizards;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.TerminologyWidget;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;

public class ConceptDataViewTransformationWizardPageName extends WizardPage {
	
	private Text elementName;
	private Composite container;
	private BusinessLayerFacade facade;
	private NounDTO noun = null;
	private NounDTO assignedNoun;
	
	public ConceptDataViewTransformationWizardPageName(BusinessLayerFacade facade, String conceptDataViewName_windowTitle, String conceptDataViewName_title) {
		super(conceptDataViewName_windowTitle);
		this.facade = facade;
		setTitle(conceptDataViewName_title);
		setDescription(ResourceMessages.ConceptDataViewName_description);
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		Label labelClipboardName = new Label(container, SWT.NULL);
		labelClipboardName.setText("Name");
		elementName = new Text(container, SWT.BORDER | SWT.SINGLE);
		elementName.setText("");
		
		

		Label emptyLabel = new Label(container, SWT.NULL);
		emptyLabel.setText("");
		
		noun = facade.createNounDTO();
		noun.setSynonymUid(0);
		noun.setName(elementName.getText());
		
		final TerminologyWidget tw = new TerminologyWidget(container, null);
		tw.setWizard(this);
		tw.setBounds(10, 60, 650, 165);
		tw.setWizard(true);
		tw.setEnabled(false);
		tw.refresh();
		tw.addAssignSenseListener(new SelectionListener(){
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			@Override
			public void widgetSelected(SelectionEvent e) {
				tw.setTerm(noun);
				tw.setEnabled(true);
				dialogChanged();
				tw.refresh();
			}
		});
		
		elementName.addMouseListener(new MouseListener() {
			public void mouseDown(MouseEvent e) {
				List<NounDTO> nouns = facade.findNouns(noun);
				assignedNoun = null;
				if (nouns.size()>0 && !((NounDTO)nouns.get(0)).equals((Object)noun)){
					noun.setSynonymUid(((NounDTO)nouns.get(0)).getSynonymUid());
					assignedNoun = (NounDTO)nouns.get(0);
				}						
				tw.setTerm(noun);
				tw.setEnabled(true);
				if (dialogChanged().equals(ResourceMessages.Validation_nameSense)){
					tw.setButtonsEnabled(true);
				}
				tw.refresh();					
			}
			public void mouseUp(MouseEvent e) {
			}
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		
		elementName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				noun.setName(elementName.getText());
				noun.setSynonymUid(0);
				dialogChanged();
				assignedNoun = null;
				List<NounDTO> nouns = facade.findNouns(noun);
				if (nouns.size()>0 && !((NounDTO)nouns.get(0)).equals((Object)noun)){
					noun.setSynonymUid(((NounDTO)nouns.get(0)).getSynonymUid());
					assignedNoun = (NounDTO)nouns.get(0);
				}		
					
				tw.setTerm(noun);
				tw.setEnabled(true);
				if (dialogChanged().equals(ResourceMessages.Validation_nameSense)){
					tw.setButtonsEnabled(false);
				}
				tw.refresh();
			}
		});
		
		elementName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		elementName.setLayoutData(gd);
		elementName.setFocus();
		setControl(container);
		setPageComplete(false);

	}
	
	/**
	 * Ensures that data is properly set.
	 */
	private String dialogChanged() {
		String elemNameString = getSCLElementName();

		if(elemNameString.replace('_', ' ').trim().length() == 0) {
			updateStatus(ResourceMessages.Validation_name);
			return ResourceMessages.Validation_name;
		}
		if(facade.getNotionDTO(noun) != null) {
			updateStatus(ResourceMessages.Validation_uniqueName);
			return ResourceMessages.Validation_uniqueName;
		} 
		if (noun.getSynonymUid() == 0 && (!SCProjectHelper.getSenseAutoAddAndAssigmentFlag() || !RemoteJGWNL.getInstance().isConnected())) {
			updateStatus(ResourceMessages.Validation_nameSense);
			return ResourceMessages.Validation_nameSense;
		}
		updateStatus(null);
		return "";
	}
	
	public String getSCLElementName() {
		return elementName.getText();
	}
		
	private void updateStatus(String message) {
		if (message == null){
			setPageComplete(true);
			setErrorMessage(null);
			setDescription(ResourceMessages.ConceptDataViewName_description);
		} else {
			setPageComplete(false);
			setErrorMessage(message);
		}

	}
	
	@Override
	public Control getControl() {
		return container;
	}

	public NounDTO getAssignedNoun() {
		if (assignedNoun == null){			
			return noun;
		} else {
			assignedNoun.setSynonymUid(noun.getSynonymUid());
			noun.delete();
			return assignedNoun;
		}
	}

	public void deleteNoun(){
		if (noun != null)
			noun.delete();
	}

}
