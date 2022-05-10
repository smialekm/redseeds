package eu.redseeds.sc.current.ui.wizards;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
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
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.domain.controls.TerminologyWidget;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class SCLElementWizardPage extends WizardPage {
	
	private Text elementName;
	private Composite container;
	private ISelection selection;
	private Object type;
	private BusinessLayerFacade facade;
	private NounDTO noun = null;
	private NounDTO assignedNoun;
	
	public SCLElementWizardPage(ISelection selection, java.lang.Object type) {
		super(ResourceMessages.NewSCLElement_windowTitle);
		this.type = type;
		facade = SCProjectContainer.instance().getSCProject(
					SCProjectHelper.getIProject((IStructuredSelection)selection))
					.getBusinessLayerFacade();
		if (type == ActorDTO.class) {
			setTitle(ResourceMessages.NewActor_title);
			setDescription(ResourceMessages.NewActor_description);
		} else if (type == ActorsPackageDTO.class) {
			setTitle(ResourceMessages.NewActorsPackage_title);
			setDescription(ResourceMessages.NewActorsPackage_description);
		} else

		if (type == RequirementDTO.class) {
			setTitle(ResourceMessages.NewRequirement_title);
			setDescription(ResourceMessages.NewRequirement_description);
		} else if (type == RequirementsPackageDTO.class) {
			setTitle(ResourceMessages.NewRequirementsPackage_title);
			setDescription(ResourceMessages.NewRequirementsPackage_description);
		} else

		if (type == NotionDTO.class) {
			setTitle(ResourceMessages.NewNotion_title);
			setDescription(ResourceMessages.NewNotion_description);
		} else if (type == NotionsPackageDTO.class) {
			setTitle(ResourceMessages.NewNotionsPackage_title);
			setDescription(ResourceMessages.NewNotionsPackage_description);
		} else

		if (type == SystemElementDTO.class) {
			setTitle(ResourceMessages.NewSystemElement_title);
			setDescription(ResourceMessages.NewSystemElement_description);
		} else if (type == SystemElementsPackageDTO.class) {
			setTitle(ResourceMessages.NewSystemElementsPackage_title);
			setDescription(ResourceMessages.NewSystemElementsPackage_description);
		} else

		if (type == UseCaseDTO.class) {
			setTitle(ResourceMessages.NewUseCase_title);
			setDescription(ResourceMessages.NewUseCase_description);
		} else {
			setTitle(ResourceMessages.NewSCLElement_title);
			setDescription(ResourceMessages.NewSCLElement_description);
		}
		this.selection = selection;

	}

	@Override
	public void createControl(Composite parent) {
		String selectedItemPath = "";
		
		if (selection != null) {
			ITreeSelection treeSelection = (ITreeSelection) selection;
			TreePath[] treePaths = treeSelection.getPaths();
			if (treePaths.length == 1){
				TreePath treePath = treePaths[0];
				for (int i = 1; i < treePath.getSegmentCount(); i++) {
					selectedItemPath+=treePath.getSegment(i).toString();
					if (i<treePath.getSegmentCount()-1){
						selectedItemPath+="\\";
					}
				}
			}			
		}
		
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		Label labelSelectedItem = new Label(container, SWT.NULL);
		labelSelectedItem.setText("Parent");
		Text textSelectedItem = new Text(container, SWT.BORDER | SWT.SINGLE |SWT.READ_ONLY);
		textSelectedItem.setText(selectedItemPath);
		Label labelClipboardName = new Label(container, SWT.NULL);
		labelClipboardName.setText("Name");
		elementName = new Text(container, SWT.BORDER | SWT.SINGLE);
		elementName.setText("");
		
		

		Label emptyLabel = new Label(container, SWT.NULL);
		emptyLabel.setText("");
		
		if (type == NotionDTO.class || type == ActorDTO.class || type == SystemElementDTO.class) {
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
					if (type == NotionDTO.class || type == ActorDTO.class || type == SystemElementDTO.class) {
						noun.setName(elementName.getText());
						noun.setSynonymUid(0);
					}
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
			
		}
		
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
		if (type == RequirementDTO.class && type != UseCaseDTO.class) {
			if(!facade.isRequirementNameValid(elemNameString)) {
				updateStatus(ResourceMessages.Validation_uniqueName);
				return ResourceMessages.Validation_uniqueName;
			}
		}
		else if (type == UseCaseDTO.class) {
			if(!facade.isUseCaseNameValid(elemNameString)) {
				updateStatus(ResourceMessages.Validation_uniqueName);
				return ResourceMessages.Validation_uniqueName;
			}
		}
		else if (type == ActorDTO.class) {
			if(facade.getActorDTO(noun) != null || facade.getSystemElementDTO(noun) != null) {
				updateStatus(ResourceMessages.Validation_uniqueName);
				return ResourceMessages.Validation_uniqueName;
			}
			if (noun.getSynonymUid() == 0 && (!SCProjectHelper.getSenseAutoAddAndAssigmentFlag() || !RemoteJGWNL.getInstance().isConnected())) {
				updateStatus(ResourceMessages.Validation_nameSense);
				return ResourceMessages.Validation_nameSense;
			}
		}
		else if (type == NotionDTO.class) {
			if(facade.getNotionDTO(noun) != null) {
				updateStatus(ResourceMessages.Validation_uniqueName);
				return ResourceMessages.Validation_uniqueName;
			} 
			if (noun.getSynonymUid() == 0 && (!SCProjectHelper.getSenseAutoAddAndAssigmentFlag() || !RemoteJGWNL.getInstance().isConnected())) {
				updateStatus(ResourceMessages.Validation_nameSense);
				return ResourceMessages.Validation_nameSense;
			}
		}
		else if (type ==  SystemElementDTO.class) {
			if(facade.getSystemElementDTO(noun) != null || facade.getActorDTO(noun) != null) {
				updateStatus(ResourceMessages.Validation_uniqueName);
				return ResourceMessages.Validation_uniqueName;
			}
			if (noun.getSynonymUid() == 0 && (!SCProjectHelper.getSenseAutoAddAndAssigmentFlag() || !RemoteJGWNL.getInstance().isConnected())) {
				updateStatus(ResourceMessages.Validation_nameSense);
				return ResourceMessages.Validation_nameSense;
			}
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
			if (type == ActorDTO.class) {
				setDescription(ResourceMessages.NewActor_description);
			} else
			if (type == SystemElementDTO.class) {
				setDescription(ResourceMessages.NewSystemElement_description);
			} else
			if (type == NotionDTO.class) {
				setDescription(ResourceMessages.NewNotion_description);
			}
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
