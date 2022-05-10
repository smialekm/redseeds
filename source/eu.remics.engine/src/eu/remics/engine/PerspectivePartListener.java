package eu.remics.engine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import notiondiagram.NotionDiagram;
import notiondiagram.diagram.part.NotionDiagramDiagramEditor;
import notiondiagram.diagram.part.NotionDiagramDiagramEditorUtil;
import notiondiagram.impl.NotionDiagramImpl;

import org.apache.xerces.parsers.DOMParser;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListenerWithChecks;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import usecasediagram.UseCase;
import usecasediagram.UseCaseDiagram;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditor;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorUtil;
import usecasediagram.impl.UseCaseDiagramImpl;
import de.uni_koblenz.jgralab.GraphElement;
import eu.redseeds.common.Constants;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.engine.navigator.RecoveredSCProjectFilter;
import eu.redseeds.engine.navigator.SCNavigator;
import eu.redseeds.engine.navigator.SCProjectFilter;
import eu.redseeds.sc.editor.rsl.editors.UseCaseEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.ActorEditorInput;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditorInput;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.redseeds.scl.rsl.rslkernel.elements.RepresentableElementRelationship;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.common.Constans;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.navigator.listener.SelectionChangedListener;
import eu.remics.recovery.manager.views.UnassignedScenariosView;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.usecases.MUseCase;
import eu.remics.util.SCNavigatorHelper;

public class PerspectivePartListener implements IStartup{
	
	public SelectionChangedListener selectionListener = new SelectionChangedListener();
	
	static class FilteredNavigator{
		
		public static void showOnlyRequirementsSpecification(){
			SCNavigator scnavigator = (SCNavigator)SCNavigatorHelper.getSCNavigator();
			if(scnavigator == null){
				return;
			}
			scnavigator.getCommonViewer().setFilters(new ViewerFilter[]{new RecoveredSCProjectFilter(), new SCProjectFilter()});
		}

		public static void showWholeSCProject(){
			SCNavigator scnavigator = (SCNavigator)SCNavigatorHelper.getSCNavigator();
			if(scnavigator == null){
				return;
			}
			//scnavigator.getCommonViewer().resetFilters();
			scnavigator.getCommonViewer().setFilters(new ViewerFilter[]{new SCProjectFilter()});
		}
	}
	
	@Override
	public void earlyStartup() {
		Display.getDefault().asyncExec(new Runnable(){
			@Override
			public void run() {
				final IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				initSelectionListener();
				IPerspectiveDescriptor descr = workbenchWindow.getActivePage().getPerspective();
				doAfterPerspectiveChange(descr.getId());
				createDiagramRefreshHelper();
				
				PlatformUI.getWorkbench().addWorkbenchListener(new IWorkbenchListener() {
					
					@Override
					public boolean preShutdown(IWorkbench workbench, boolean forced) {
						IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
						if( page.findView(Constans.AssignScenarioViewID) != null){
							IViewPart viewPart = page.findView(Constans.AssignScenarioViewID);
							page.hideView(viewPart);
						}
						if( page.findView(Constans.SplitScenarioViewID) != null){
							IViewPart viewPart = page.findView(Constans.SplitScenarioViewID);
							page.hideView(viewPart);
						}
						if( page.findView(Constans.FindSimilarScenariosViewID) != null){
							IViewPart viewPart = page.findView(Constans.FindSimilarScenariosViewID);
							page.hideView(viewPart);
						}
						if( page.findView(Constans.DetailedSimilarScenariosViewID) != null){
							IViewPart viewPart = page.findView(Constans.DetailedSimilarScenariosViewID);
							page.hideView(viewPart);
						}
						if( page.findView(Constans.PreviewViewID) != null){
							IViewPart viewPart = page.findView(Constans.PreviewViewID);
							page.hideView(viewPart);
						}
						if( page.findView(Constans.CodePreviewViewID) != null){
							IViewPart viewPart = page.findView(Constans.CodePreviewViewID);
							page.hideView(viewPart);
						}
						IEditorReference[] editors = page.getEditorReferences();
						for(IEditorReference editor : editors){
							if(editor.getEditor(true) instanceof UseCaseDiagramDiagramEditor){
								page.closeEditor(editor.getEditor(true), true);
							}
							if(editor.getEditor(true) instanceof NotionDiagramDiagramEditor){
								page.closeEditor(editor.getEditor(true), true);
							}
						}
						
						return true;
					}
					
					@Override
					public void postShutdown(IWorkbench workbench) {
					}
				});
				
				if(workbenchWindow != null){
					workbenchWindow.addPerspectiveListener(new PerspectiveAdapter(){
						
						@Override
						public void perspectiveActivated(IWorkbenchPage page,
								IPerspectiveDescriptor perspectiveDescriptor){
							super.perspectiveActivated(page, perspectiveDescriptor);
							
							doAfterPerspectiveChange(perspectiveDescriptor.getId());
						}
					});
					
					workbenchWindow.getActivePage().addPartListener(new IPartListener() {
						
						@Override
						public void partOpened(IWorkbenchPart part) {
							if(part instanceof UseCaseDiagramDiagramEditor){
								final UseCaseDiagramDiagramEditor editor = (UseCaseDiagramDiagramEditor)part;
								
								TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(editor.getDiagram().getDiagram().eResource());
								if(editingDomain == null) return;
								editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
									
									@Override
									protected void doExecute() {
										try {
											editor.getDiagram().getDiagram().eResource().save(usecasediagram.diagram.part.UseCaseDiagramDiagramEditorUtil
													.getSaveOptions());
											/*for(Resource res : editor.getEditingDomain().getResourceSet().getResources()){
												res.save(usecasediagram.diagram.part.UseCaseDiagramDiagramEditorUtil
														.getSaveOptions());
											}*/
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
								});
								
							}
							else if(part instanceof NotionDiagramDiagramEditor){
								final NotionDiagramDiagramEditor editor = (NotionDiagramDiagramEditor)part;
								
								TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(editor.getDiagram().getDiagram().eResource());
								if(editingDomain == null) return;
								editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
									
									@Override
									protected void doExecute() {
										try {
											editor.getDiagram().getDiagram().eResource().save(NotionDiagramDiagramEditorUtil
													.getSaveOptions());
											/*for(Resource res : editor.getEditingDomain().getResourceSet().getResources()){
												res.save(usecasediagram.diagram.part.UseCaseDiagramDiagramEditorUtil
														.getSaveOptions());
											}*/
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
								});
								
							}
						}
						
						@Override
						public void partDeactivated(IWorkbenchPart part) {
						}
						
						@Override
						public void partClosed(IWorkbenchPart part) {
							RecoveryModelHelper rmh = RecoveryModelHelper.instance();
							if(part instanceof IEditorPart){
								if(RecoveryModelHelper.instance() != null && rmh.getBussinessLayerFacade() != null){
									if(part instanceof UseCaseDiagramDiagramEditor){
										/*//usecases
										List<RSLUseCase> ucList = new ArrayList<RSLUseCase>();
										Iterable<RSLUseCase> usecases = rmh.getBussinessLayerFacade().getRSLUseCaseVertices();
										for(RSLUseCase uc : usecases){
											for(Stereotype stereotype : ((RSLUseCase)uc).getStereotypeList()){
												if(stereotype.getName().equals("temporary")){
													ucList.add(uc);
												}
											}
										}
										for(RSLUseCase uc : ucList){
											uc.delete();
										}
										//actors
										List<eu.redseeds.scl.rsl.rsldomainelements.actors.Actor> actList = new ArrayList<eu.redseeds.scl.rsl.rsldomainelements.actors.Actor>();
										Iterable<ActorOrSystemElement> actors = rmh.getBussinessLayerFacade().getActorOrSystemElementVertices();
										for(ActorOrSystemElement act : actors){
											for(Stereotype stereotype : act.getStereotypeList()){
												if(stereotype.getName().equals("temporary")){
													actList.add((Actor) act);
												}
											}
										}
										for(Actor act : actList){
											act.delete();
										}
										//associations
										List<Dependency> depList = new ArrayList<Dependency>();
										for (Dependency dep : rmh.getBussinessLayerFacade().getDependencyVertices()){
											for(Stereotype stereotype : dep.getStereotypeList()){
												if(stereotype.getName().equals("temporary")){
													depList.add(dep);
												}
											}
										}
										for(Dependency dep : depList){
											dep.delete();
										}*/
										UseCaseDiagramDiagramEditor editor = (UseCaseDiagramDiagramEditor) part;
										Diagram diag = editor.getDiagram();
										UseCaseDiagram ucdiagram = (UseCaseDiagram) diag.getElement();
										for(GraphElement elem : ucdiagram.getGraphElements())
											for(Stereotype stereotype : elem instanceof SCLElement?((SCLElement)elem).getStereotypeList():((RepresentableElementRelationship)elem).getStereotypeList()){
												if(stereotype.getName().equals("temporary")){
													if(elem instanceof SCLElement){
														IEditorInput input = null;
														if (elem instanceof UseCaseDTO){
															MUseCase.restoreRecoveredScenarios((UseCaseDTO) elem);
															SCProjectHelper.refreshUnassignedScenariosList();
															input = new UseCaseEditorInput();
															((UseCaseEditorInput) input).setUseCaseDTO((UseCaseDTO) elem);
														} else if (elem instanceof ActorDTO){
															input = new ActorEditorInput();
															((ActorEditorInput) input).setActorDTO((ActorDTO) elem);
														}
														IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
														if (input != null) {
															IEditorPart editorPart = page.findEditor(input);
															page.closeEditor(editorPart, true);
														}
														((SCLElement)elem).delete();
														break;
													} else {
														((RepresentableElementRelationship)elem).delete();
														break;
													}
												}
											}
									}
									if(part instanceof NotionDiagramDiagramEditor){
										/*//notions
										List<eu.redseeds.scl.rsl.rsldomainelements.notions.Notion> notList = new ArrayList<eu.redseeds.scl.rsl.rsldomainelements.notions.Notion>();
										Iterable<eu.redseeds.scl.rsl.rsldomainelements.notions.Notion> notions = rmh.getBussinessLayerFacade().getNotionVertices();
										for(Notion not : notions){
											for(Stereotype stereotype : not.getStereotypeList()){
												if(stereotype.getName().equals("temporary")){
													notList.add(not);
												}
											}
										}
										for(Notion not : notList){
											not.delete();
										}
										//(in)directed relations
										List<DomainElementRelationship> dirList = new ArrayList<DomainElementRelationship>();
										Iterable<DomainElementRelationship> dirRels = rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices();
										for(DomainElementRelationship dir : dirRels){
											for(Stereotype stereotype : dir.getStereotypeList()){
												if(stereotype.getName().equals("temporary")){
													dirList.add(dir);
												}
											}
										}
										for(DomainElementRelationship dir : dirList){
											dir.delete();
										}
										//generalizations
										List<NotionSpecialisation> genList = new ArrayList<NotionSpecialisation>();
										Iterable<NotionSpecialisation> genRels = rmh.getBussinessLayerFacade().getNotionSpecialisationVertices();
										for(NotionSpecialisation gen : genRels){
											for(Stereotype stereotype : gen.getStereotypeList()){
												if(stereotype.getName().equals("temporary")){
													genList.add(gen);
												}
											}
										}
										for(NotionSpecialisation gen : genList){
											gen.delete();
										}*/
										NotionDiagramDiagramEditor editor = (NotionDiagramDiagramEditor) part;
										Diagram diag = editor.getDiagram();
										NotionDiagram notdiagram = (NotionDiagram) diag.getElement();
										for(GraphElement elem : notdiagram.getGraphElements())
											for(Stereotype stereotype : elem instanceof SCLElement?((SCLElement)elem).getStereotypeList():((RepresentableElementRelationship)elem).getStereotypeList()){
												if(stereotype.getName().equals("temporary")){
													if(elem instanceof SCLElement){
														IEditorInput input = null;
														if(elem instanceof NotionDTO){
															input = new NotionEditorInput();
															((NotionEditorInput) input).setNotionDTO((NotionDTO) elem);
														}
														IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
														if (input != null) {
															IEditorPart editorPart = page.findEditor(input);
															page.closeEditor(editorPart, true);
														}
														((SCLElement)elem).delete();
														break;
													} else {
														((RepresentableElementRelationship)elem).delete();
														break;
													}
												}
											}
										
									}
									if(SCProjectHelper.getSCNavigator() != null){
										SCProjectHelper.refreshSCNavigator();
										SCProjectHelper.refreshActiveProject();
									}
								}
							}
						}
						
						@Override
						public void partBroughtToTop(IWorkbenchPart part) {
						}
						
						@Override
						public void partActivated(IWorkbenchPart part) {
						}
					});
				}
				FileContentChangeListener fileListener = addFileContentChangeListener();
				addExecutionListener(fileListener);
			}
		});
	}
	
	public void initSelectionListener() {
		if(SCNavigatorHelper.getSCNavigator() != null){
			SCNavigatorHelper.getSCNavigator().getSite().getWorkbenchWindow().getSelectionService().addPostSelectionListener(selectionListener);
		}
	}
	
	public void doAfterPerspectiveChange(String id){
		if(id.equals(Constans.TALEperspectiveID)){
			
			FilteredNavigator.showOnlyRequirementsSpecification();
			
			if(SCProjectHelper.getActiveProject() != null){
				if(RecoveryManagerHelper.getUnassignedScenarioListView() == null)
					return;
				((UnassignedScenariosView)RecoveryManagerHelper.getUnassignedScenarioListView()).refresh();
			}
		}
		if(id.equals(Constans.REDSEEDSperspectiveID)){
			FilteredNavigator.showWholeSCProject();
		}
	}
	
	public void createDiagramRefreshHelper(){
		SCProjectHelper.setDiagramRefreshHelper(new DiagramRefreshHelper());
	}
	
	public void addExecutionListener(FileContentChangeListener fileListener){
		ICommandService commService = (ICommandService) PlatformUI.getWorkbench().getAdapter(ICommandService.class);
		ExecutionListener execListener = new ExecutionListener(fileListener);
		commService.addExecutionListener(execListener);
	}
	
	public FileContentChangeListener addFileContentChangeListener(){
		FileContentChangeListener listener = new FileContentChangeListener();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener, IResourceChangeEvent.POST_CHANGE);
		return listener;
	}
	
}




/**
 * listener listens for save/delete/rename action in workbench
 *
 */
class ExecutionListener implements IExecutionListenerWithChecks {
	
	ITreeSelection fileSelection;
	private FileContentChangeListener fileListener;
	
	public ExecutionListener(FileContentChangeListener fileListener){
		this.fileListener = fileListener;
	}

	@Override
	public void notHandled(String commandId, NotHandledException exception) {
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.undo")) RecoveryManagerHelper.setUndoActionOccuredState(false);
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.redo")) RecoveryManagerHelper.setRedoActionOccuredState(false);
	}

	
	
	@Override
	public void postExecuteFailure(String commandId,
			ExecutionException exception) {
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.undo")) RecoveryManagerHelper.setUndoActionOccuredState(false);
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.redo")) RecoveryManagerHelper.setRedoActionOccuredState(false);
	}

	@Override
	public void postExecuteSuccess(String commandId, Object returnValue) {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		if(commandId.equalsIgnoreCase("org.eclipse.ui.file.save") || commandId.equalsIgnoreCase("org.eclipse.ui.file.saveAll")){
			if(fileListener.save_undo_flag){
				fileListener.save_undo_flag = false;
				IHandlerService service = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
				try {
					service.executeCommand("org.eclipse.ui.edit.undo", null);
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (NotDefinedException e) {
					e.printStackTrace();
				} catch (NotEnabledException e) {
					e.printStackTrace();
				} catch (NotHandledException e) {
					e.printStackTrace();
				}
				try {
					service.executeCommand("org.eclipse.ui.file.save", null);
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (NotDefinedException e) {
					e.printStackTrace();
				} catch (NotEnabledException e) {
					e.printStackTrace();
				} catch (NotHandledException e) {
					e.printStackTrace();
				}
			}
			rmh.saveSCProject();
			SCProjectHelper.refreshSCNavigator();
		}
		else if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.delete") && fileSelection != null){
			if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId()
					.equals(Constans.TALEperspectiveID) 
					|| PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId()
					.equals(Constans.REDSEEDSperspectiveID)){
				for (Iterator<?> iterator = fileSelection.iterator(); iterator.hasNext();) {
					Object next = iterator.next();
					if(next instanceof IFile){
						IFile diagramFile = (IFile)next;
						if(!diagramFile.exists())
							deleteDomainModelResource(diagramFile);
					}
					if(next instanceof IProject){
						IWorkbenchWindow win = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
						IWorkbenchPage page = win.getActivePage();
						page.closeAllEditors(true);
						SCProjectHelper.refreshUnassignedScenariosList();
						if( page.findView(Constans.AssignScenarioViewID) != null){
							IViewPart viewPart = page.findView(Constans.AssignScenarioViewID);
							page.hideView(viewPart);
						}
						if( page.findView(Constans.SplitScenarioViewID) != null){
							IViewPart viewPart = page.findView(Constans.SplitScenarioViewID);
							page.hideView(viewPart);
						}
						if( page.findView(Constans.FindSimilarScenariosViewID) != null){
							IViewPart viewPart = page.findView(Constans.FindSimilarScenariosViewID);
							page.hideView(viewPart);
						}
						if( page.findView(Constans.DetailedSimilarScenariosViewID) != null){
							IViewPart viewPart = page.findView(Constans.DetailedSimilarScenariosViewID);
							page.hideView(viewPart);
						}
						if( page.findView(Constans.PreviewViewID) != null){
							IViewPart viewPart = page.findView(Constans.PreviewViewID);
							page.hideView(viewPart);
						}
						if( page.findView(Constans.CodePreviewViewID) != null){
							IViewPart viewPart = page.findView(Constans.CodePreviewViewID);
							page.hideView(viewPart);
						}
					}
				}
				if(rmh.getSCProject() != null)
					rmh.saveSCProject();
				if(SCProjectHelper.getSCNavigator() != null)
					SCProjectHelper.refreshSCNavigator();
			}
		}
		else if(commandId.equalsIgnoreCase("org.eclipse.ltk.ui.refactoring.commands.renameResource") && fileSelection != null){
			if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId()
					.equals(Constans.TALEperspectiveID) 
					|| PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId()
					.equals(Constans.REDSEEDSperspectiveID)){
				for (Iterator<?> iterator = fileSelection.iterator(); iterator.hasNext();) {
					Object next = iterator.next();
					if(next instanceof IFile){
						IFile diagramFile = (IFile) next;
						if(!diagramFile.exists()){
							if(fileListener.rename_undo_flag){
								fileListener.rename_undo_flag = false;
								if(fileListener.nameContainsHashChar){
									fileListener.nameContainsHashChar = false;
									MessageDialog.openError(new Shell(), "Wrong diagram name", "Diagram name contains invalid # character. Change will be reverted. Please rename it.");
								}
								else if(fileListener.nameContainsWhiteChars){
									fileListener.nameContainsWhiteChars = false;
									MessageDialog.openError(new Shell(), "Wrong diagram name", "Diagram name is empty or has only whitespaces. Change will be reverted. Please rename it.");
								}
								else{
									String suffix = "";
									if(diagramFile.getName().endsWith(".usecasediagram_diagram")){
										suffix = ".usecasediagram_diagram";
									}
									else if(diagramFile.getName().endsWith(".notiondiagram_diagram")){
										suffix = ".notiondiagram_diagram";
									}
									MessageDialog.openError(new Shell(), "Wrong diagram name", "Diagram name must ends with " + suffix + ". Change will be reverted. Please rename it.");
								}
								
								IHandlerService service = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
								try {
									service.executeCommand("org.eclipse.ui.edit.undo", null);
								} catch (ExecutionException e) {
									e.printStackTrace();
								} catch (NotDefinedException e) {
									e.printStackTrace();
								} catch (NotEnabledException e) {
									e.printStackTrace();
								} catch (NotHandledException e) {
									e.printStackTrace();
								}
							}
							else{
								renameDomainModelResource(diagramFile);
							}
						}
					}
					if(next instanceof IProject){
						if(fileListener.rename_undo_flag){
							fileListener.rename_undo_flag = false;
							MessageDialog.openError(new Shell(), "Wrong project name", "Project name contains invalid # character. Change will be reverted. Please rename it.");
							SCProjectHelper.refreshActiveProject();
							IHandlerService service = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
							try {
								service.executeCommand("org.eclipse.ui.edit.undo", null);
							} catch (ExecutionException e) {
								e.printStackTrace();
							} catch (NotDefinedException e) {
								e.printStackTrace();
							} catch (NotEnabledException e) {
								e.printStackTrace();
							} catch (NotHandledException e) {
								e.printStackTrace();
							}
						}
					}
				}
				if(rmh.getSCProject() != null)
					rmh.saveSCProject();
				if(SCProjectHelper.getSCNavigator() != null)
					SCProjectHelper.refreshSCNavigator();
			}
		} else if(commandId.equalsIgnoreCase("org.eclipse.ltk.ui.refactoring.commands.moveResources") && fileSelection != null){
			if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId()
					.equals(Constans.TALEperspectiveID) 
					|| PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId()
					.equals(Constans.REDSEEDSperspectiveID)){
				for (Iterator<?> iterator = fileSelection.iterator(); iterator.hasNext();) {
					Object next = iterator.next();
					if(next instanceof IFile){
						MessageDialog.openError(new Shell(), "Move diagram file forbidden", "You shouldn't change diagram file location. Drag it between packages instead.");
						SCProjectHelper.refreshActiveProject();
						IHandlerService service = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
						try {
							service.executeCommand("org.eclipse.ui.edit.undo", null);
						} catch (ExecutionException e) {
							e.printStackTrace();
						} catch (NotDefinedException e) {
							e.printStackTrace();
						} catch (NotEnabledException e) {
							e.printStackTrace();
						} catch (NotHandledException e) {
							e.printStackTrace();
						}
					}
				}
				if(rmh.getSCProject() != null)
					rmh.saveSCProject();
				if(SCProjectHelper.getSCNavigator() != null)
					SCProjectHelper.refreshSCNavigator();
			}
		}
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.undo")) RecoveryManagerHelper.setUndoActionOccuredState(false);
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.redo")) RecoveryManagerHelper.setRedoActionOccuredState(false);
	}

	@Override
	public void preExecute(String commandId, ExecutionEvent event) {
		if((commandId.equalsIgnoreCase("org.eclipse.ui.edit.delete") || commandId.equalsIgnoreCase("org.eclipse.ltk.ui.refactoring.commands.renameResource")) && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection() instanceof ITreeSelection){
			fileSelection = (ITreeSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		}
		if(commandId.equalsIgnoreCase("org.eclipse.ltk.ui.refactoring.commands.renameResource")){
			IWorkbenchWindow win = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			IWorkbenchPage page = win.getActivePage();
			page.closeAllEditors(true);
			SCProjectHelper.refreshActiveProject();
		}
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.undo")) RecoveryManagerHelper.setUndoActionOccuredState(true);
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.redo")) RecoveryManagerHelper.setRedoActionOccuredState(true);
	}
	
	private void deleteDomainModelResource(IFile diagram){
		if(diagram.getFileExtension().equalsIgnoreCase("usecasediagram_diagram")){
			IFolder currSCFolder = SCProjectHelper.getActiveProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			IFolder ucdiagrams = currSCFolder.getFolder(Constants.UCDIAGRAMS_FOLDER_NAME);
			IResource[] ucdiagramsRes = null;
			try {
				ucdiagramsRes = ((IContainer)ucdiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if(ucdiagramsRes != null){
				for(int i=0; i< ucdiagramsRes.length; i++){
					if(ucdiagramsRes[i] instanceof IFile){
						if(((IFile)ucdiagramsRes[i]).getFileExtension().equalsIgnoreCase("usecasediagram")){

							String modelFileName = diagram.getName().substring(0, diagram.getName().lastIndexOf('_'));
							if(ucdiagramsRes[i].getName().equals(modelFileName)){
								try {
									((IFile)ucdiagramsRes[i]).delete(true, new NullProgressMonitor());
								} catch (CoreException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
		else if(diagram.getFileExtension().equalsIgnoreCase("notiondiagram_diagram")){
			IFolder currSCFolder = SCProjectHelper.getActiveProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			IFolder notiondiagrams = currSCFolder.getFolder(Constants.NOTIONSDIAGRAMS_FOLDER_NAME);
			IResource[] notiondiagramsRes = null;
			try {
				notiondiagramsRes = ((IContainer)notiondiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if(notiondiagramsRes != null){
				for(int i=0; i< notiondiagramsRes.length; i++){
					if(notiondiagramsRes[i] instanceof IFile){
						if(((IFile)notiondiagramsRes[i]).getFileExtension().equalsIgnoreCase("notiondiagram")){

							String modelFileName = diagram.getName().substring(0, diagram.getName().lastIndexOf('_'));
							if(notiondiagramsRes[i].getName().equals(modelFileName)){
								try {
									((IFile)notiondiagramsRes[i]).delete(true, new NullProgressMonitor());
								} catch (CoreException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void renameDomainModelResource(IFile diagram){
		if(diagram.getFileExtension().equalsIgnoreCase("usecasediagram_diagram")){
			IFolder currSCFolder = SCProjectHelper.getActiveProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			IFolder ucdiagrams = currSCFolder.getFolder(Constants.UCDIAGRAMS_FOLDER_NAME);
			IResource[] ucdiagramsRes = null;
			try {
				ucdiagramsRes = ((IContainer)ucdiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if(ucdiagramsRes != null){
				for(int i=0; i< ucdiagramsRes.length; i++){
					if(ucdiagramsRes[i] instanceof IFile){
						if(((IFile)ucdiagramsRes[i]).getFileExtension().equalsIgnoreCase("usecasediagram_diagram")){
							String absoluteNewDiagramPath = ((IFile)ucdiagramsRes[i]).getRawLocation().toString();
							String newDiagramName = ((IFile)ucdiagramsRes[i]).getName();
							String oldDiagramName = diagram.getName();
							IPath relativeNewDiagramPath = ((IFile)ucdiagramsRes[i]).getFullPath();
							
							String oldModelLinkedToNewDiagram = parseDiagramResourceForDomainModelName(absoluteNewDiagramPath, oldDiagramName);
							if(oldModelLinkedToNewDiagram.equals(oldDiagramName.substring(0, oldDiagramName.lastIndexOf('_')))){
								updateReferences(relativeNewDiagramPath, newDiagramName, oldDiagramName);
							}
						}
					}
				}
				/*for(int i=0; i< ucdiagramsRes.length; i++){
					if(ucdiagramsRes[i] instanceof IFile){
						if(((IFile)ucdiagramsRes[i]).getFileExtension().equalsIgnoreCase("usecasediagram")){
							if(ucdiagramsRes[i].getName().equals(oldDomainResourceName)){
								try {
									IPath newPath = ucdiagramsRes[i].getFullPath().removeLastSegments(1);
									newPath = newPath.append(newDomainResourceName);
									((IFile)ucdiagramsRes[i]).move(newPath, true, new NullProgressMonitor());
								} catch (CoreException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}*/
			}
		}
		else if(diagram.getFileExtension().equalsIgnoreCase("notiondiagram_diagram")){
			IFolder currSCFolder = SCProjectHelper.getActiveProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			IFolder notiondiagrams = currSCFolder.getFolder(Constants.NOTIONSDIAGRAMS_FOLDER_NAME);
			IResource[] notiondiagramsRes = null;
			try {
				notiondiagramsRes = ((IContainer)notiondiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if(notiondiagramsRes != null){
				String newDiagramName = "";
				for(int i=0; i< notiondiagramsRes.length; i++){
					if(notiondiagramsRes[i] instanceof IFile){
						if(((IFile)notiondiagramsRes[i]).getFileExtension().equalsIgnoreCase("notiondiagram_diagram")){
							String absoluteNewDiagramPath = ((IFile)notiondiagramsRes[i]).getRawLocation().toString();
							newDiagramName = ((IFile)notiondiagramsRes[i]).getName();
							String oldDiagramName = diagram.getName();
							IPath relativeNewDiagramPath = ((IFile)notiondiagramsRes[i]).getFullPath();
							
							String oldModelLinkedToNewDiagram = parseDiagramResourceForDomainModelName(absoluteNewDiagramPath, oldDiagramName);
							if(oldModelLinkedToNewDiagram.equals(oldDiagramName.substring(0, oldDiagramName.lastIndexOf('_')))){
								updateReferences(relativeNewDiagramPath, newDiagramName, oldDiagramName);
							}
						}
					}
				}
				/*for(int i=0; i< notiondiagramsRes.length; i++){
					if(notiondiagramsRes[i] instanceof IFile){
						if(((IFile)notiondiagramsRes[i]).getFileExtension().equalsIgnoreCase("notiondiagram")){
							String oldDomainResourceName = diagram.getName().substring(0, diagram.getName().lastIndexOf('_'));
							String newDomainResourceName = newDiagramName.substring(0, newDiagramName.lastIndexOf('_'));
							if(notiondiagramsRes[i].getName().equals(oldDomainResourceName)){
								try {
									IPath newPath = notiondiagramsRes[i].getFullPath().removeLastSegments(1);
									newPath = newPath.append(newDomainResourceName);
									((IFile)notiondiagramsRes[i]).move(newPath, true, new NullProgressMonitor());
								} catch (CoreException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}*/
			}
		}
	}
	
	private void updateReferences(IPath relativeNewDiagramPath, String newDiagramName, String oldDiagramName) {
		String newModelName = newDiagramName.substring(0, newDiagramName.lastIndexOf('_'));
		String oldModelName = oldDiagramName.substring(0, oldDiagramName.lastIndexOf('_'));
		IPath tmp = relativeNewDiagramPath.removeLastSegments(1);
		String newModelPath = tmp.append(newModelName).toString();
		tmp = relativeNewDiagramPath.removeLastSegments(1);
		String oldModelPath = tmp.append(oldModelName).toString();
		
		URI diagramURI = URI.createPlatformResourceURI(relativeNewDiagramPath.toString(), false);
		URI newModelURI = URI.createPlatformResourceURI(newModelPath, false);
		URI oldModelURI = URI.createPlatformResourceURI(oldModelPath, false);
		
		ResourceSet resSet = new ResourceSetImpl();
		Resource diagramResource = resSet.getResource(diagramURI, true);
		Resource oldModelResource = resSet.getResource(oldModelURI, true);
		oldModelResource.setURI(newModelURI);
		
		String modelName = newModelName.substring(0, newModelName.lastIndexOf('.'));
		UseCaseDiagramImpl usecaseDiagram = null;
		NotionDiagramImpl notionDiagram = null;
		if(oldModelResource.getContents().get(0) instanceof UseCaseDiagramImpl){
			usecaseDiagram = (UseCaseDiagramImpl)oldModelResource.getContents().get(0);
			usecaseDiagram.setName(modelName);
		}
		else if(oldModelResource.getContents().get(0) instanceof NotionDiagramImpl){
			notionDiagram = (NotionDiagramImpl)oldModelResource.getContents().get(0);
			notionDiagram.setName(modelName);
		}
		((DiagramImpl)diagramResource.getContents().get(0)).setName(newDiagramName);
		((DiagramImpl)diagramResource.getContents().get(0)).setElement(oldModelResource.getContents().get(0));
		for(int i=0; i < ((DiagramImpl)diagramResource.getContents().get(0)).getPersistedChildren().size(); i++){
			Node node = (Node) ((DiagramImpl)diagramResource.getContents().get(0)).getPersistedChildren().get(i);
			if(usecaseDiagram != null){
				if(node.getElement() instanceof UseCase){
					UseCase uc = findUseCase((UseCase)node.getElement(), usecaseDiagram.getUsecases());
					if(uc != null)
						node.setElement(uc);
				}
				else if(node.getElement() instanceof usecasediagram.Actor){
					usecasediagram.Actor act = findActor((usecasediagram.Actor)node.getElement(), usecaseDiagram.getActors());
					if(act != null)
						node.setElement(act);
				}
			}
			if(notionDiagram != null){
				node.setElement(notionDiagram.getNotions().get(i));
			}
		}
		try {
			diagramResource.save(UseCaseDiagramDiagramEditorUtil.getSaveOptions());
			oldModelResource.save(UseCaseDiagramDiagramEditorUtil.getSaveOptions());
			Resource hangingModelResource = resSet.getResource(oldModelURI, true);
			hangingModelResource.delete(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private UseCase findUseCase(UseCase o, EList<UseCase> usecases) {
		for(UseCase uc : usecases){
			if(uc.getId() == o.getId()){
				return uc;
			}
		}
		return null;
	}
	
	private usecasediagram.Actor findActor(usecasediagram.Actor o, EList<usecasediagram.Actor> actors) {
		for(usecasediagram.Actor act : actors){
			if(act.getId() == o.getId()){
				return act;
			}
		}
		return null;
	}
	
	private String parseDiagramResourceForDomainModelName(String newDiagramResourcePath, String oldDiagramResourceName) {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(new InputSource(new InputStreamReader(new FileInputStream(newDiagramResourcePath), "UTF-8")));
			org.w3c.dom.Element root = parser.getDocument().getDocumentElement();
			org.w3c.dom.Node element = getNode("element", root.getChildNodes());
			
			String hrefValue = getNodeAttribute("href", element);
			hrefValue = hrefValue.substring(0, hrefValue.lastIndexOf('#'));
			
			if(hrefValue.equals(oldDiagramResourceName.substring(0, oldDiagramResourceName.lastIndexOf('_')))){
				return hrefValue;
			}
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private org.w3c.dom.Node getNode(String tag, NodeList nodes) {
		for(int i=0; i < nodes.getLength(); i++){
			if(nodes.item(i).getNodeName().equalsIgnoreCase(tag)){
				return nodes.item(i);
			}
		}
		return null;
	}
	
	private String getNodeAttribute(String attr, org.w3c.dom.Node node) {
		NamedNodeMap attrs = node.getAttributes();
		for(int i=0; i < attrs.getLength(); i++){
			if(attrs.item(i).getNodeName().equalsIgnoreCase(attr)){
				return attrs.item(i).getNodeValue();
			}
		}
		return "";
	}

	@Override
	public void notDefined(String commandId, NotDefinedException exception) {
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.undo")) RecoveryManagerHelper.setUndoActionOccuredState(false);
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.redo")) RecoveryManagerHelper.setRedoActionOccuredState(false);
	}

	@Override
	public void notEnabled(String commandId, NotEnabledException exception) {
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.undo")) RecoveryManagerHelper.setUndoActionOccuredState(false);
		if(commandId.equalsIgnoreCase("org.eclipse.ui.edit.redo")) RecoveryManagerHelper.setRedoActionOccuredState(false);
	}
}

/**
 * listener listens for resource change event in workbench
 *
 */
class FileContentChangeListener implements IResourceChangeListener {
	
	public boolean save_undo_flag = false;
	public boolean rename_undo_flag = false;
	public boolean nameContainsHashChar = false;
	public boolean nameContainsWhiteChars = false;

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		IResource resource;
		
		if(event.getType() != IResourceChangeEvent.POST_CHANGE){
			return;
		}
		
		for (IResourceDelta childDelta : event.getDelta().getAffectedChildren(IResourceDelta.CHANGED | IResourceDelta.OPEN | IResourceDelta.ADDED)) {
			resource = childDelta.getResource();
			if(SCProjectHelper.getActiveProject() != null){
				SCProjectHelper.refreshActiveProject();
			}
			//suppose it is project: if the project name contains '#' character (special case where diagrams EMF URIs cannot handle '#', cause it's special)
			if(resource.getProject().getName().contains("#")){
				rename_undo_flag = true;
				return;
			}
			//suppose it is diagram file
			IResourceDelta[] deltas = getResource(childDelta);
			if(deltas.length == 1){
				IResource file = deltas[0].getResource();
				String name = file.getName();
				boolean flag = name.endsWith(".usecasediagram_diagram") || name.endsWith(".notiondiagram_diagram");
				boolean flag2 = name.endsWith(".usecasediagram") || name.endsWith(".notiondiagram");
				boolean flag3 = name.equals(Constants.UCDIAGRAMS_FOLDER_NAME) || name.equals(Constants.NOTIONSDIAGRAMS_FOLDER_NAME);
				if(!flag && !flag2){
					if(name.contains(".")){
						rename_undo_flag = true;
						return;
					}
					else if(!flag3){
						rename_undo_flag = true;
						return;
					}
				}
				if(name.contains("#")){
					rename_undo_flag = true;
					nameContainsHashChar = true;
					return;
				}
				int idx = name.lastIndexOf('.');
				if(idx != -1){
					name = name.substring(0, idx);
					name = name.trim();
					if(name.isEmpty()){
						rename_undo_flag = true;
						nameContainsWhiteChars = true;
						return;
					}
				}
			}
		}
		
		try {
			event.getDelta().accept(new IResourceDeltaVisitor() {
				
				@Override
				public boolean visit(IResourceDelta delta) throws CoreException {
					RecoveryModelHelper rmh = RecoveryModelHelper.instance();
					if(delta.getKind() == IResourceDelta.CHANGED && delta.getKind() != IResourceDelta.REMOVED){
						try{
							IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
							if(editor == null)
								return false;
							if(editor instanceof UseCaseDiagramDiagramEditor){
								String editingDiagramFileName = editor.getEditorInput().getName();
								editingDiagramFileName = "/CurrentSC/Use Cases Diagrams/"+editingDiagramFileName;
								IFile editingDiagramFile = SCProjectHelper.getActiveProject().getFile(editingDiagramFileName);
								int packId = getDiagramPackageId(editingDiagramFile);
								RequirementsPackageDTO pack = rmh.getRequirementsPackageByVertexID(packId);
								String name = editingDiagramFile.getName();
								if(checkForIdenticalUCDiagramsNames(pack, name, editingDiagramFile)){
									save_undo_flag = true;
								}
							}
							else if(editor instanceof NotionDiagramDiagramEditor){
								String editingDiagramFileName = editor.getEditorInput().getName();
								editingDiagramFileName = "/CurrentSC/Notions Diagrams/"+editingDiagramFileName;
								IFile editingDiagramFile = SCProjectHelper.getActiveProject().getFile(editingDiagramFileName);
								int packId = getDiagramPackageId(editingDiagramFile);
								NotionsPackageDTO pack = rmh.getNotionsPackageByVertexID(packId);
								String name = editingDiagramFile.getName();
								if(checkForIdenticalNotionDiagramsNames(pack, name, editingDiagramFile)){
									save_undo_flag = true;
								}
							}
						}catch(NullPointerException e){
							return false;
						}
					}
					return false;
				}
			});
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	private IResourceDelta[] getResource(IResourceDelta delta) {
		IResourceDelta[] deltas = delta.getAffectedChildren(IResourceDelta.CHANGED | IResourceDelta.OPEN | IResourceDelta.ADDED);
		if(deltas.length == 1){
			if(deltas[0].getAffectedChildren(IResourceDelta.CHANGED | IResourceDelta.OPEN | IResourceDelta.ADDED).length == 0){
				String path = deltas[0].getResource().getFullPath().toString();
				if(path.contains(Constants.UCDIAGRAMS_FOLDER_NAME) || path.contains(Constants.NOTIONSDIAGRAMS_FOLDER_NAME))
					return deltas;
			}
			else{
				return getResource(deltas[0]);
			}
		}
		return new IResourceDelta[]{};
	}
	
	private Integer getDiagramPackageId(IFile current){
		if(SCProjectHelper.getActiveProject() != null){
			if(current.getFileExtension().equalsIgnoreCase("usecasediagram_diagram")){
				return DiagramFileHelper.getUseCaseDiagramId(current.getLocation().toOSString());
			}
			else if(current.getFileExtension().equalsIgnoreCase("notiondiagram_diagram")){
				return DiagramFileHelper.getNotionDiagramId(current.getLocation().toOSString());
			}
		}
		return null;
	}
	
	private ArrayList<IFile> getUseCaseDiagramsFromPackage(RequirementsPackageDTO reqpack, String name){

		ArrayList<IFile> ucdiagramsFiles = new ArrayList<IFile>();

		if(SCProjectHelper.getActiveProject() != null){

			IFolder currSCFolder = SCProjectHelper.getActiveProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			IFolder ucdiagrams = currSCFolder.getFolder(Constants.UCDIAGRAMS_FOLDER_NAME);
			IResource[] ucdiagramsRes = null;
			try {
				ucdiagramsRes = ((IContainer)ucdiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}

			if(ucdiagramsRes != null){
				for(int i=0; i< ucdiagramsRes.length; i++){
					if(ucdiagramsRes[i] instanceof IFile){
						if(((IFile)ucdiagramsRes[i]).getFileExtension().equalsIgnoreCase("usecasediagram_diagram")){
							String[] splitted = ucdiagramsRes[i].getName().split("\\.");
							String tmp = "";
							for(int j=0; j < splitted.length-1; j++){
								tmp += splitted[j];
							}
							if(null!=DiagramFileHelper.getUseCaseDiagramId(((IFile)ucdiagramsRes[i]).getLocation().toOSString()) && ((RequirementsPackage)reqpack).getId() == DiagramFileHelper.getUseCaseDiagramId(((IFile)ucdiagramsRes[i]).getLocation().toOSString())
									&& !name.equals(tmp)){
								ucdiagramsFiles.add((IFile)ucdiagramsRes[i]);
							}
						}
					}
				}
			}
		}
		return ucdiagramsFiles;
	}
	
	private boolean checkForIdenticalUCDiagramsNames(RequirementsPackageDTO reqpack, String name, IFile currentFile){
		String currentUCDiagramName = DiagramFileHelper.getUseCaseDiagramName(currentFile.getLocation().toOSString());
		String[] splitted = name.split("\\.");
		String curr = "";
		for(int i=0; i < splitted.length-1; i++){
			curr += splitted[i];
		}
		ArrayList<IFile> diagrams = getUseCaseDiagramsFromPackage(reqpack, curr);
		if(!diagrams.isEmpty())
			for(IFile obj : diagrams){
				String ucDiagramName = DiagramFileHelper.getUseCaseDiagramName(obj.getLocation().toOSString());
				String[] s = obj.getName().split("\\.");
				String objName = s[0];
				if(currentUCDiagramName.equals(ucDiagramName)){
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Identical names", "Diagram with identical name already exists under the same requirements package! Please rename it.");
					return true;
				}
				else if(currentUCDiagramName.equals(objName)){
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Matching names in model", "Diagram name match filename of diagram already exists in model! Please rename it.");
					return true;
				}
			}
		return false;
	}
	
	private ArrayList<IFile> getNotionDiagramsFromPackage(NotionsPackageDTO notionpack, String name){

		ArrayList<IFile> notiondiagramsFiles = new ArrayList<IFile>();

		if(SCProjectHelper.getActiveProject() != null){

			IFolder currSCFolder = SCProjectHelper.getActiveProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			IFolder notiondiagrams = currSCFolder.getFolder(Constants.NOTIONSDIAGRAMS_FOLDER_NAME);
			IResource[] notiondiagramsRes = null;
			try {
				notiondiagramsRes = ((IContainer)notiondiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}

			if(notiondiagramsRes != null){
				for(int i=0; i< notiondiagramsRes.length; i++){
					if(notiondiagramsRes[i] instanceof IFile){
						if(((IFile)notiondiagramsRes[i]).getFileExtension().equalsIgnoreCase("notiondiagram_diagram")){
							String[] splitted = notiondiagramsRes[i].getName().split("\\.");
							String tmp = "";
							for(int j=0; j < splitted.length-1; j++){
								tmp += splitted[j];
							}
							if(null!=DiagramFileHelper.getNotionDiagramId(((IFile)notiondiagramsRes[i]).getLocation().toOSString()) && ((NotionsPackage)notionpack).getId() == DiagramFileHelper.getNotionDiagramId(((IFile)notiondiagramsRes[i]).getLocation().toOSString())
									&& !name.equals(tmp)){
								notiondiagramsFiles.add((IFile)notiondiagramsRes[i]);
							}
						}
					}
				}
			}
		}
		return notiondiagramsFiles;
	}
	
	private boolean checkForIdenticalNotionDiagramsNames(NotionsPackageDTO notionpack, String name, IFile currentFile){
		String currentNotionDiagramName = DiagramFileHelper.getNotionDiagramName(currentFile.getLocation().toOSString());
		String[] splitted = name.split("\\.");
		String curr = "";
		for(int i=0; i < splitted.length-1; i++){
			curr += splitted[i];
		}
		ArrayList<IFile> diagrams = getNotionDiagramsFromPackage(notionpack, curr);
		if(!diagrams.isEmpty())
			for(IFile obj : diagrams){
				String notionDiagramName = DiagramFileHelper.getNotionDiagramName(obj.getLocation().toOSString());
				String[] s = obj.getName().split("\\.");
				String objName = s[0];
				if(currentNotionDiagramName.equals(notionDiagramName)){
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Identical names", "Diagram with identical name already exists under the same notions package! Please rename it.");
					return true;
				}
				else if(currentNotionDiagramName.equals(objName)){
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Matching names in model", "Diagram name match filename of diagram already exists in model! Please rename it.");
					return true;
				}
			}
		return false;
	}
}


