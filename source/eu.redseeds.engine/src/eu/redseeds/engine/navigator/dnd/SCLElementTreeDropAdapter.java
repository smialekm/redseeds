package eu.redseeds.engine.navigator.dnd;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import notiondiagram.NotionDiagram;
import notiondiagram.diagram.part.NotionDiagramDiagramEditor;
import notiondiagram.diagram.part.NotionDiagramDiagramEditorUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import usecasediagram.UseCaseDiagram;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditor;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorUtil;
import de.uni_koblenz.jgralab.GraphElement;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.engine.dialogs.DropNotionOnNotionDialog;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.AddRequirementRelationshipDialog;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;
import eu.remics.recovery.model.domainlogic.usecases.MUnassignedScenariosList;
import eu.remics.recovery.model.dto.TerminologyOperationFailureException;

public class SCLElementTreeDropAdapter extends ViewerDropAdapter {

	public SCLElementTreeDropAdapter(Viewer viewer) {
		super(viewer);
		setFeedbackEnabled(false);
	}
	
	@Override
	public boolean performDrop(Object data) {
		Object target = getCurrentTarget();
		Object[] sources;
		if (((Object[]) data).length>0){
			sources = new Object[((Object[]) data).length];
			BusinessLayerFacade facade = (BusinessLayerFacade) ((SCLElement) target).getGraph();
			for (int i=0;i<((Object[]) data).length;i++)
				sources[i]=facade.getSCLElementById((Long) ((Object[]) data)[i]);
		} else sources = ((IStructuredSelection) getViewer().getSelection()).toArray();
		if(sources.length<=0){
			return false;
		}
		Object source = sources[sources.length - 1];
		if(!checkIfElementsHaveSameType(sources)) {
			return false;
		}
		if(!checkTargetInSCSourceInSC(source, target)) {
			return false;
		}
		//checking types (sometimes only one source object is checked - all source objects are of the same type)
		if (target instanceof RequirementsSpecificationDTO) {
			if(source instanceof RequirementsPackageDTO) {
				if(((RequirementsPackageDTO)source).getRequirementsSpecificationParent() != null) {//is a root package
					if(((RequirementsPackageDTO)source).getRequirementsSpecificationParent()
							.equals(
							SCProjectContainer.instance().getSCProject(source).getMainCase().getRequirementsSpecificationDTO())) {
						return false;// is root package in main SC
					}
					return false;// is root package in clipboard
				}
				if(((RequirementsPackageDTO)source).getParent().equals(target)) {//why drop on parent?
					return false;
				}
			} else {
				return false;
			}
		} else if (target instanceof RequirementDTO) {
			if(sources.length > 1) { //no multiple selection with dnd for reqs
				return false;
			}
			if(source.equals(target)) {//cannot drop on itself
				return false;
			}
			if(!compareSourceGraphs(source, target)){
				return false;
			}
			if(!(source instanceof RequirementDTO)) {
				return false;
			}
		} else if (target instanceof RequirementsPackageDTO) {//dropping on req pack possible for req and req packages
			if(checkTargetAmongSourceObjects(sources, target)) {//cannot drop on itself
				return false;
			}
			if(!compareSourceGraphs(source, target)){
				return false;
			}
			if(source instanceof RequirementsPackageDTO) {
				if(target.equals(((RequirementsPackageDTO)source).getParent())) {//why drop on parent?
					return false;
				}
			}
			if(source instanceof RequirementDTO) {
				if(target.equals(((RequirementDTO)source).getParent())) {//why drop on parent?
					return false;
				}
			}
			if(!(source instanceof RequirementDTO || source instanceof RequirementsPackageDTO || (source instanceof IFile && "usecasediagram_diagram".equals(((IFile) source).getFullPath().getFileExtension())))) {
				return false;
			}
			if (source instanceof IFile && null!=DiagramFileHelper.getUseCaseDiagramId(((IFile) source).getLocation().toOSString()) && ((RequirementsPackage)target).getId() == DiagramFileHelper.getUseCaseDiagramId(((IFile) source).getLocation().toOSString())){
				return false;
			}
		} else if (target instanceof ActorDTO) { 	
			if(sources.length > 1) { //no multiple selection with dnd for actors
				return false;
			}
			if(source.equals(target)) {//cannot drop on itself
				return false;
			}
			if(!(source instanceof ActorDTO || source instanceof NotionDTO || source instanceof SystemElementDTO)) {
				return false;
			}
		} else if (target instanceof ActorsPackageDTO) { //dropping on act pack possible for actors and actors packages
			if(checkTargetAmongSourceObjects(sources, target)) {//cannot drop on itself
				return false;
			}
			if(!compareSourceGraphs(source, target)){
				return false;
			}
			if(!(source instanceof ActorDTO || source instanceof ActorsPackageDTO && ((ActorsPackageDTO)source).getParent() != null)) {
				return false;
			}
		} else if (target instanceof NotionDTO) { 
			if(sources.length > 1) { //no multiple selection with dnd for notions
				return false;
			}
			if(source.equals(target)) {//cannot drop on itself
				return false;
			}
			if(!(source instanceof ActorDTO || source instanceof NotionDTO || source instanceof SystemElementDTO)) {
				return false;
			}
		} else if (target instanceof NotionsPackageDTO) {//dropping on act pack possible for notions and notions package
			if(checkTargetAmongSourceObjects(sources, target)) {//cannot drop on itself
				return false;
			}
			if(!compareSourceGraphs(source, target)){
				return false;
			}
			if(!(source instanceof NotionDTO || source instanceof NotionsPackageDTO && ((NotionsPackageDTO)source).getParent() != null || source instanceof IFile  && "notiondiagram_diagram".equals(((IFile) source).getFullPath().getFileExtension()))) {
				return false;
			} else if (source instanceof IFile && null!=DiagramFileHelper.getNotionDiagramId(((IFile) source).getLocation().toOSString()) && ((NotionsPackage)target).getId() == DiagramFileHelper.getNotionDiagramId(((IFile) source).getLocation().toOSString())){
				return false;
			}
		} else if (target instanceof SystemElementDTO) {
			if(sources.length > 1) { //no multiple selection with dnd for system elems
				return false;
			}
			if(source.equals(target)) {//cannot drop on itself
				return false;
			}
			if(!(source instanceof ActorDTO || source instanceof NotionDTO || source instanceof SystemElementDTO)) {
				return false;
			}
		} else if (target instanceof SystemElementsPackageDTO) {//dropping on sys el pack possible for sys elems and sys el package
			if(checkTargetAmongSourceObjects(sources, target)) {//cannot drop on itself
				return false;
			}
			if(!compareSourceGraphs(source, target)){
				return false;
			}
			if(!(source instanceof SystemElementDTO || source instanceof SystemElementsPackageDTO && ((SystemElementsPackageDTO)source).getParent() != null)){
				return false;
			}
		}
		for (int i=0;i<sources.length;i++)
			performOneElementDrop(sources[i], target);
		return true;
	}
	
	private void performOneElementDrop(Object source, Object target) {
		if(target instanceof RequirementsSpecificationDTO) {
			if(source instanceof RequirementsPackageDTO) {
				if(((RequirementsPackageDTO)source).getParent() != null) {
					((RequirementsPackageDTO)source).getParent()
						.removeChildRequirementsPackageDTO((RequirementsPackageDTO)source);
					((RequirementsSpecificationDTO)target).addRequirementsPackageDTO((RequirementsPackageDTO)source);
				}
				else if(((RequirementsPackageDTO)source).getRequirementsSpecificationParent() != null){
					((RequirementsPackageDTO)source).setRequirementsSpecificationParent(((RequirementsSpecificationDTO)target));
				}
			}
		}
		else if(target instanceof RequirementDTO && source instanceof RequirementDTO) {
			if(target instanceof UseCaseDTO && source instanceof UseCaseDTO){
				if(((UseCaseDTO)target).getName() != null && ((UseCaseDTO)source).getName() != null 
						&& ((UseCaseDTO)target).getName().startsWith("~") && ((UseCaseDTO)target).getParent().getName().equalsIgnoreCase("RecoveredScenarios")
						&& ((UseCaseDTO)source).getName().startsWith("~") && ((UseCaseDTO)source).getParent().getName().equalsIgnoreCase("RecoveredScenarios")){

					RecoveryManagerHelper.setFirstScenarioToUnsplit((UseCaseDTO)target);
					RecoveryManagerHelper.setSecondScenarioToUnsplit((UseCaseDTO)source);
					/*
					 * Needed use of Java reflection (by reason of plugin cycle loop)
					 */
					try {
						Method method = RecoveryManagerHelper.getInstanceOfCUnsplitScenario().getClass().getMethod("Unsplit", (Class<?>[])null);
						Object classInvokedOn = RecoveryManagerHelper.getInstanceOfCUnsplitScenario();
						method.invoke(classInvokedOn, (Object[])null);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				else if(((UseCaseDTO)source).getName().startsWith("~") && ((UseCaseDTO)source).getParent().getName().equalsIgnoreCase("RecoveredScenarios")){
					/*
					 * Needed use of Java reflection (by reason of plugin cycle loop)
					 */
					try {
						//Method method = RecoveryManagerHelper.getInstanceOfCAssignScenarioToUseCase().getClass().getMethod("ClicksAssignScenarioButton", (Class<?>[])null);
						RecoveryManagerHelper.setAssignTargetUseCase((UseCaseDTO)target);
						Method method = RecoveryManagerHelper.getInstanceOfCAssignScenarioToUseCase().getClass().getMethod("DropOnAssignScenario", (Class<?>[])null);
						Object classInvokedOn = RecoveryManagerHelper.getInstanceOfCAssignScenarioToUseCase();
						method.invoke(classInvokedOn, (Object[])null);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				else{
					RequirementDTO targetReq = (RequirementDTO) target;
					RequirementDTO sourceReq = (RequirementDTO) source;
					AddRequirementRelationshipDialog dialog = new AddRequirementRelationshipDialog(sourceReq, targetReq); 
					dialog.open();
				}
			}
			else{
				RequirementDTO targetReq = (RequirementDTO) target;
				RequirementDTO sourceReq = (RequirementDTO) source;
				AddRequirementRelationshipDialog dialog = new AddRequirementRelationshipDialog(sourceReq, targetReq); 
				dialog.open();
			}
		}
		if(target instanceof RequirementsPackageDTO){
			if(source instanceof RequirementDTO) {
				if(source instanceof UseCaseDTO) {
					boolean fromTree = true;
					for(ConstrainedLanguageScenarioDTO scen : ((UseCaseDTO)source).getConstrainedLanguageScenarioDTOList()){
						if(MUnassignedScenariosList.isInUnassignedList(scen)){
							MScenario.createsUseCase((UseCaseDTO)source, ((UseCaseDTO)source).getName().substring(1), (RequirementsPackageDTO)target);
							fromTree = false;
							break;
						}
					}
					if(fromTree){
						((RequirementDTO)source).setParent((RequirementsPackageDTO)target);
					}
				}
				else{
					((RequirementDTO)source).setParent((RequirementsPackageDTO)target);
				}
			}
			else if(source instanceof RequirementsPackageDTO) {
				((RequirementsPackageDTO)source).setParent((RequirementsPackageDTO)target);
			} else if (source instanceof IFile){
				final int useCasePackId = ((RequirementsPackage)target).getId();
				TransactionalEditingDomain editingDomain = null;
				UseCaseDiagram usecasediagram = null;
				for (IEditorReference e:PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences())
					if (e.getEditor(true) instanceof UseCaseDiagramDiagramEditor){
						UseCaseDiagramDiagramEditor edit = (UseCaseDiagramDiagramEditor) e.getEditor(true);
						if (edit.getEditorInput() instanceof FileEditorInput && source.equals(((FileEditorInput) edit.getEditorInput()).getFile())){
							editingDomain = edit.getEditingDomain();
							usecasediagram = (UseCaseDiagram) edit.getDiagram().getElement();
						}
					}
				if (null==editingDomain){
					String pathName = "/"+((IFile) source).getProject().getName()+"/CurrentSC/Use Cases Diagrams/"+((IFile) source).getName();
					URI diagramModelFileURI = URI.createPlatformResourceURI(pathName, false);
					URI domainModelFileURI = URI.createPlatformResourceURI(pathName.substring(0, pathName.lastIndexOf('_')), false);
					editingDomain = DiagramEditingDomainFactory.getInstance().createEditingDomain();
					Resource diagram = editingDomain.getResourceSet().createResource(diagramModelFileURI);
					Resource model = editingDomain.getResourceSet().createResource(domainModelFileURI);
					try {
						diagram.load(UseCaseDiagramDiagramEditorUtil.getSaveOptions());
						model.load(UseCaseDiagramDiagramEditorUtil.getSaveOptions());
					} catch (IOException e) {
						e.printStackTrace();
					}
					usecasediagram = (UseCaseDiagram) ((Diagram)diagram.getContents().get(0)).getElement();
				}
				final UseCaseDiagram fusecasediagram = usecasediagram;
				final ResourceSet resset = editingDomain.getResourceSet();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					
					@Override
					protected void doExecute() {
						fusecasediagram.setPackage(useCasePackId);
						try {
							for(Resource res : resset.getResources())
								res.save(UseCaseDiagramDiagramEditorUtil
									.getSaveOptions());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
		else if(target instanceof ActorDTO) {
			if(source instanceof ActorDTO) {
				((ActorDTO)target).addRelatedActor((ActorDTO)source);
			}
			else if(source instanceof NotionDTO) {
				((ActorDTO)target).addRelatedNotion((NotionDTO)source);
			}
			else if(source instanceof SystemElementDTO) {
				((ActorDTO)target).addRelatedSystemElement((SystemElementDTO)source);
			}
		}
		else if(target instanceof ActorsPackageDTO){
			if(source instanceof ActorDTO) {
				((ActorDTO)source).setParent((ActorsPackageDTO)target);
			}
			else if(source instanceof ActorsPackageDTO) {
				((ActorsPackageDTO)source).setParent((ActorsPackageDTO)target);
			}
		}
		else if(target instanceof NotionDTO) {
			if(source instanceof ActorDTO) {
				((NotionDTO)target).addRelatedActor((ActorDTO)source);
			}
			else if(source instanceof NotionDTO) {
				boolean rev = MNotion.canCreateRelation((NotionDTO) source, (NotionDTO) target, true, false);
				boolean r = !MNotion.canCreateRelation((NotionDTO) source, (NotionDTO) target, true, true);
				boolean a = !MNotion.canAddAtribute((NotionDTO) source, (NotionDTO) target);
				boolean g = !MNotion.canCreateGeneralization((NotionDTO) source,(NotionDTO) target);
				DropNotionOnNotionDialog dialog = new DropNotionOnNotionDialog(r, g, a);
				int result = dialog.open();
				if(result == DropNotionOnNotionDialog.ID_CREATE_RELATIONSHIP) {
					NotionDTO aSource = rev?(NotionDTO) target:(NotionDTO) source;
					NotionDTO aTarget = rev?(NotionDTO) source:(NotionDTO) target;
					DomainElementRelationshipDTO rel = ((NotionDTO)aTarget).addRelatedNotion((NotionDTO)aSource);
					if (!MNotion.canCreateRelation(aSource, aTarget, false, false))
						rel.setDirected(true);
				}
				else if (result == DropNotionOnNotionDialog.ID_CREATE_SPECIALISATION) {
					((NotionDTO)target).addSpecialisedNotion((NotionDTO)source);
				}
				else if (result == DropNotionOnNotionDialog.ID_ADD_ATTRIBUTE) {
					((NotionDTO)target).addNotionDTOAttribute((NotionDTO)source);
				}
				else if (result == DropNotionOnNotionDialog.ID_MERGE_NOTIONS) {
					InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(),
				            "New notion name", "Enter new notion name: ", ((NotionDTO)target).getName(), null);
					if(dlg.open() == Window.OK){
						
						try {
							MNotion.merges((NotionDTO)target, (NotionDTO)source, !dlg.getValue().replace('_', ' ').trim().isEmpty()?dlg.getValue():((NotionDTO)target).getName());
						} catch (TerminologyOperationFailureException e) {
							e.printStackTrace();
							Shell shell = new Shell();
							shell.forceActive();
							MessageDialog.openError(shell, "Error", "Terminology server is not connected.");
						}
						SCProjectHelper.refreshSCNavigator();
					}
				}
			}
			else if(source instanceof SystemElementDTO) {
				((NotionDTO)target).addRelatedSystemElement((SystemElementDTO)source);
			}
		}
		else if(target instanceof NotionsPackageDTO){
			if(source instanceof NotionDTO) {
				((NotionDTO)source).setParent((NotionsPackageDTO)target);
			}
			else if(source instanceof NotionsPackageDTO) {
				((NotionsPackageDTO)source).setParent((NotionsPackageDTO)target);
			} else if (source instanceof IFile){
				final int notionPackId = ((NotionsPackage)target).getId();
				TransactionalEditingDomain editingDomain = null;
				NotionDiagram notiondiagram = null;
				for (IEditorReference e:PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences())
					if (e.getEditor(true) instanceof NotionDiagramDiagramEditor){
						NotionDiagramDiagramEditor edit = (NotionDiagramDiagramEditor) e.getEditor(true);
						if (edit.getEditorInput() instanceof FileEditorInput && source.equals(((FileEditorInput) edit.getEditorInput()).getFile())){
							editingDomain = edit.getEditingDomain();
							notiondiagram = (NotionDiagram) edit.getDiagram().getElement();
						}
					}
				if (null==editingDomain){
					String pathName = "/"+((IFile) source).getProject().getName()+"/CurrentSC/Notions Diagrams/"+((IFile) source).getName();
					URI diagramModelFileURI = URI.createPlatformResourceURI(pathName, false);
					URI domainModelFileURI = URI.createPlatformResourceURI(pathName.substring(0, pathName.lastIndexOf('_')), false);
					editingDomain = DiagramEditingDomainFactory.getInstance().createEditingDomain();
					Resource diagram = editingDomain.getResourceSet().createResource(diagramModelFileURI);
					Resource model = editingDomain.getResourceSet().createResource(domainModelFileURI);
					try {
						diagram.load(NotionDiagramDiagramEditorUtil.getSaveOptions());
						model.load(NotionDiagramDiagramEditorUtil.getSaveOptions());
					} catch (IOException e) {
						e.printStackTrace();
					}
					notiondiagram = (NotionDiagram) ((Diagram)diagram.getContents().get(0)).getElement();
				}
				final ResourceSet resset = editingDomain.getResourceSet();
				final NotionDiagram fnotiondiagram = notiondiagram;
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					
					@Override
					protected void doExecute() {
						fnotiondiagram.setPackage(notionPackId);
						try {
							for(Resource res : resset.getResources())
								res.save(NotionDiagramDiagramEditorUtil
									.getSaveOptions());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
		else if(target instanceof SystemElementDTO) {
			if(source instanceof ActorDTO) {
				((SystemElementDTO)target).addRelatedActor((ActorDTO)source);
			}
			else if(source instanceof NotionDTO) {
				((SystemElementDTO)target).addRelatedNotion((NotionDTO)source);
			}
			else if(source instanceof SystemElementDTO) {
				((SystemElementDTO)target).addRelatedSystemElement((SystemElementDTO)source);
			}
		}
		else if(target instanceof SystemElementsPackageDTO){
			if(source instanceof SystemElementDTO) {
				((SystemElementDTO)source).setParent((SystemElementsPackageDTO)target);
			}
			else if(source instanceof SystemElementsPackageDTO) {
				((SystemElementsPackageDTO)source).setParent((SystemElementsPackageDTO)target);
			}
		}
	}

	@Override
	public boolean validateDrop(Object target, int operation,
			TransferData transferType) {
		if (!(target instanceof RequirementsSpecificationDTO
			|| target instanceof RequirementDTO
			|| target instanceof RequirementsPackageDTO
			|| target instanceof ActorDTO
			|| target instanceof ActorsPackageDTO
			|| target instanceof NotionDTO
			|| target instanceof NotionsPackageDTO
			|| target instanceof SystemElementDTO
			|| target instanceof SystemElementsPackageDTO))
			return false;
		return SCLElementTransfer.getInstance().isSupportedType(
				transferType);
	}
	
	/**
	 * Returns true if all elements in passed array are of the same type, false otherwise. 
	 * </br>Also returns true for one-element and empty arrays.
	 * @param array
	 * @return
	 */
	private boolean checkIfElementsHaveSameType(Object[] array) {
		if(array.length < 2) {//either empty or one elements array
			return true;
		}
		for (int i = 1; i < array.length; i++) {
			if(!array[i].getClass().equals(array[0].getClass())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns true if "target" object is among sourceArray objects, false otherwise. 
	 * @param sourceArray
	 * @param target
	 * @return
	 */
	private boolean checkTargetAmongSourceObjects(Object[] sourceArray, Object target) {
		for (int i = 0; i < sourceArray.length; i++) {
			if(target.equals(sourceArray[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determines whether obj1 and obj2 belong to the same graph
	 * @param obj1
	 * @param obj2
	 * @return False if elements belong to separate graphs or at least one element is not castable to de.uni_koblenz.jgralab.GraphElement class
	 */
	private boolean compareSourceGraphs(Object obj1, Object obj2) {
		if (obj1 instanceof IFile && obj2 instanceof GraphElement || obj2 instanceof IFile && obj1 instanceof GraphElement){
			IFile ifile = (IFile) (obj1 instanceof IFile?obj1:obj2);
			GraphElement gelem = (GraphElement) (obj1 instanceof IFile?obj2:obj1);
			if (ifile.getProject().equals(SCProjectContainer.instance().getSCProject(gelem).getEclipseProject()))
				return true;
			return false;
		}
		if(!(obj1 instanceof GraphElement)) {
			return false;
		}
		if(!(obj2 instanceof GraphElement)) {
			return false;
		}
		if(!((GraphElement)obj2).getGraph().equals(((GraphElement)obj1).getGraph())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Assumes that both are in the same graph
	 * @param source
	 * @param target
	 * @return
	 */
	private boolean checkTargetInSCSourceInSC(Object source, Object target) {
		if(target != null && !(target instanceof IFolder)){
			if(SCProjectContainer.instance().getSCProject(target) != null){
				BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(target).getBusinessLayerFacade();
				if(facade.isAnyClipboardMember(source)) {
					return false;
				}
				if(facade.isAnyClipboardMember(target)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if drag source is SCNavigator and drop target is UnassignedScenarioList viewer
	 * @param target
	 * @return False if there is attempt to drag element from SCNavigator and drop to UnassignedScenariosList 
	 */
	/*private boolean checkIfElementsAreInSameView(Object target) {
		if(!(target instanceof UseCaseDTO))
			return false;
		if(getViewer() instanceof TableViewer 
				&& RecoveryModelHelper.isInUnassignedList(((UseCaseDTO)target).getConstrainedLanguageScenarioDTOList().get(0)) ){
			return false;
		}
		return true;
	}*/

}
