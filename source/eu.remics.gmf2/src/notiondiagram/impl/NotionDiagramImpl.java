/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import notiondiagram.AttributeRelation;
import notiondiagram.DirectedRelation;
import notiondiagram.Generalization;
import notiondiagram.IndirectRelation;
import notiondiagram.Notion;
import notiondiagram.NotionDiagram;
import notiondiagram.NotiondiagramPackage;
import notiondiagram.Relation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import de.uni_koblenz.jgralab.GraphElement;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditorInput;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementRelationship;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionSpecialisation;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.model.RecoveryModelHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Notion Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link notiondiagram.impl.NotionDiagramImpl#getRelations <em>Relations</em>}</li>
 *   <li>{@link notiondiagram.impl.NotionDiagramImpl#getAttributeRelations <em>Attribute Relations</em>}</li>
 *   <li>{@link notiondiagram.impl.NotionDiagramImpl#getGeneralizations <em>Generalizations</em>}</li>
 *   <li>{@link notiondiagram.impl.NotionDiagramImpl#getNotions <em>Notions</em>}</li>
 *   <li>{@link notiondiagram.impl.NotionDiagramImpl#getName <em>Name</em>}</li>
 *   <li>{@link notiondiagram.impl.NotionDiagramImpl#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NotionDiagramImpl extends EObjectImpl implements NotionDiagram {
	/**
	 * The cached value of the '{@link #getRelations() <em>Relations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelations()
	 * @generated
	 * @ordered
	 */
	protected EList<Relation> relations;

	/**
	 * The cached value of the '{@link #getAttributeRelations() <em>Attribute Relations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeRelations()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeRelation> attributeRelations;

	/**
	 * The cached value of the '{@link #getGeneralizations() <em>Generalizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneralizations()
	 * @generated
	 * @ordered
	 */
	protected EList<Generalization> generalizations;

	/**
	 * The cached value of the '{@link #getNotions() <em>Notions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotions()
	 * @generated
	 * @ordered
	 */
	protected EList<Notion> notions;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPackage() <em>Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackage()
	 * @generated
	 * @ordered
	 */
	protected static final int PACKAGE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPackage() <em>Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackage()
	 * @generated
	 * @ordered
	 */
	protected int package_ = PACKAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected List<GraphElement> deletedItems = new ArrayList<GraphElement>();
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected NotionDiagramImpl() {
		super();
		eAdapters().add(new AdapterImpl() {
			public void notifyChanged(Notification notification) {
				RecoveryModelHelper rmh = RecoveryModelHelper.instance(eResource());
				if(RecoveryManagerHelper.isModelDeleteActionOccur()){
					switch (notification.getEventType()) {
						case Notification.REMOVE:
							if(notification.getOldValue() instanceof Notion && null!=rmh.getNotionByVertexID(((Notion) notification.getOldValue()).getId())){
								NotionDTO not = rmh.getNotionByVertexID(((Notion) notification.getOldValue()).getId());
								IEditorInput input = new NotionEditorInput();
								((NotionEditorInput) input).setNotionDTO(not);
								IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
								IEditorPart editorPart = page.findEditor(input);
								page.closeEditor(editorPart, true);
								not.delete();
								SCProjectHelper.refreshSCNavigator();
							} else if (notification.getOldValue() instanceof Generalization && null!= rmh.getSpecialisationByVertexID(((Generalization) notification.getOldValue()).getId())){
								rmh.getSpecialisationByVertexID(((Generalization) notification.getOldValue()).getId()).delete();
							} else if (notification.getOldValue() instanceof Relation && null!=rmh.getDomainElementRelationshipByVertexID(((Relation) notification.getOldValue()).getId()) && (rmh.getDomainElementRelationshipByVertexID(((Relation) notification.getOldValue()).getId()).isDirected()?notification.getOldValue() instanceof DirectedRelation:notification.getOldValue() instanceof IndirectRelation)){
								rmh.getDomainElementRelationshipByVertexID(((Relation) notification.getOldValue()).getId()).delete();
							} else if (notification.getOldValue() instanceof AttributeRelation && rmh.getNotionByVertexID(((AttributeRelation)notification.getOldValue()).getTargetId()).getNotionDTOAttributeList().contains(rmh.getNotionByVertexID(((AttributeRelation)notification.getOldValue()).getSourceId()))){
								rmh.getNotionByVertexID(((AttributeRelation)notification.getOldValue()).getTargetId()).removeNotionDTOAttribute(rmh.getNotionByVertexID(((AttributeRelation)notification.getOldValue()).getSourceId()));
							}
							break;
					}
				}
				switch (notification.getEventType()) {
					case Notification.REMOVE:
						if(notification.getOldValue() instanceof Notion && null!=rmh.getNotionByVertexID(((Notion) notification.getOldValue()).getId())){
							eu.redseeds.scl.rsl.rsldomainelements.notions.Notion not = (eu.redseeds.scl.rsl.rsldomainelements.notions.Notion) rmh.getNotionByVertexID(((Notion) notification.getOldValue()).getId());
							deletedItems.add(not);
						} else if (notification.getOldValue() instanceof Generalization && null!= rmh.getSpecialisationByVertexID(((Generalization) notification.getOldValue()).getId())){
							NotionSpecialisation spec = (NotionSpecialisation) rmh.getSpecialisationByVertexID(((Generalization) notification.getOldValue()).getId());
							deletedItems.add(spec);
						} else if (notification.getOldValue() instanceof Relation && null!=rmh.getDomainElementRelationshipByVertexID(((Relation) notification.getOldValue()).getId()) && (rmh.getDomainElementRelationshipByVertexID(((Relation) notification.getOldValue()).getId()).isDirected()?notification.getOldValue() instanceof DirectedRelation:notification.getOldValue() instanceof IndirectRelation)){
							DomainElementRelationship rel = (DomainElementRelationship) rmh.getDomainElementRelationshipByVertexID(((Relation) notification.getOldValue()).getId());
							deletedItems.add(rel);
						}
						break;
					case Notification.ADD:
						if(notification.getOldValue() instanceof Notion && null!=rmh.getNotionByVertexID(((Notion) notification.getOldValue()).getId())){
							eu.redseeds.scl.rsl.rsldomainelements.notions.Notion not = (eu.redseeds.scl.rsl.rsldomainelements.notions.Notion) rmh.getNotionByVertexID(((Notion) notification.getOldValue()).getId());
							if (deletedItems.contains(not)) deletedItems.remove(not);
						} else if (notification.getOldValue() instanceof Generalization && null!= rmh.getSpecialisationByVertexID(((Generalization) notification.getOldValue()).getId())){
							NotionSpecialisation spec = (NotionSpecialisation) rmh.getSpecialisationByVertexID(((Generalization) notification.getOldValue()).getId());
							if (deletedItems.contains(spec)) deletedItems.remove(spec);
						} else if (notification.getOldValue() instanceof Relation && null!=rmh.getDomainElementRelationshipByVertexID(((Relation) notification.getOldValue()).getId()) && (rmh.getDomainElementRelationshipByVertexID(((Relation) notification.getOldValue()).getId()).isDirected()?notification.getOldValue() instanceof DirectedRelation:notification.getOldValue() instanceof IndirectRelation)){
							DomainElementRelationship rel = (DomainElementRelationship) rmh.getDomainElementRelationshipByVertexID(((Relation) notification.getOldValue()).getId());
							if (deletedItems.contains(rel)) deletedItems.remove(rel);
						}
						break;
				}
			}
		});
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NotiondiagramPackage.Literals.NOTION_DIAGRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Relation> getRelations() {
		if (relations == null) {
			relations = new EObjectContainmentEList<Relation>(Relation.class, this, NotiondiagramPackage.NOTION_DIAGRAM__RELATIONS);
		}
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource())) || RecoveryManagerHelper.isDeleteUndoRedoActionOccur()) return relations;
		Map<Integer,Notion> notions = new HashMap<Integer,Notion>();
		if (null!=this.notions) for (Notion not:getNotions()) notions.put(not.getId(),not);
		for(int i=0; i<relations.size(); i++){
			if(((RelationImpl) relations.get(i)).eIsSet(NotiondiagramPackage.RELATION__ID) && (null == rmh.getDomainElementRelationshipByVertexID(relations.get(i).getId()) || (rmh.getDomainElementRelationshipByVertexID(relations.get(i).getId()).isDirected()?relations.get(i) instanceof IndirectRelation:relations.get(i) instanceof DirectedRelation))){
				final int j = i;
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(relations.get(i));
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							relations.remove(j);
						}
						
						@Override
						public boolean canUndo() {
							return false;
						}
						
					});
					i--;
				}
			}
		}
		for (DomainElementRelationship rel:rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()) if (!rel.getSourceList().isEmpty() && rel.getSourceList().get(0) instanceof NotionDTO && !rel.getTargetList().isEmpty() && rel.getTargetList().get(0) instanceof NotionDTO && notions.containsKey(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)((DomainElementRelationshipDTO)rel).getSource()).getId()) && notions.containsKey(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)((DomainElementRelationshipDTO)rel).getTarget()).getId())){
			Relation rela = ((DomainElementRelationshipDTO)rel).isDirected()?new DirectedRelationImpl():new IndirectRelationImpl();
			rela.setSource(notions.get(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)((DomainElementRelationshipDTO)rel).getSource()).getId()));
			rela.setTarget(notions.get(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)((DomainElementRelationshipDTO)rel).getTarget()).getId()));
			rela.setId(rel.getId());
			boolean contains = false;
			for (final Relation r: relations) if (r.getId()==rela.getId()){
				contains =true;
				if(r.getSource() == null || r.getSource().getId()!=rela.getSource().getId()){
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
					if(editingDomain != null){
						final Notion source = rela.getSource();
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								r.setSource(source);
							}
							
							@Override
							public boolean canUndo() {
								return false;
							}
							
						});
					}
				}
				if(r.getTarget() == null || r.getTarget().getId()!=rela.getTarget().getId()){
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
					if(editingDomain != null){
						final Notion target = rela.getTarget();
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								r.setTarget(target);
							}
							
							@Override
							public boolean canUndo() {
								return false;
							}
							
						});
					}
				}
				break;
			}
			if(!contains){
				final Relation r = rela;
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							relations.add(r);
						}
						
						@Override
						public boolean canUndo() {
							return false;
						}
						
					});
				}
			}
		}
		return relations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<AttributeRelation> getAttributeRelations() {
		if (attributeRelations == null) {
			attributeRelations = new EObjectContainmentEList<AttributeRelation>(AttributeRelation.class, this, NotiondiagramPackage.NOTION_DIAGRAM__ATTRIBUTE_RELATIONS);
		}
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource())) || RecoveryManagerHelper.isDeleteUndoRedoActionOccur()) return attributeRelations;
		Map<Integer,Notion> notions = new HashMap<Integer,Notion>();
		if (null!=this.notions) for (Notion not:getNotions()) notions.put(not.getId(),not);
		for (int i =0;i<attributeRelations.size();i++){
			if(((AttributeRelationImpl) attributeRelations.get(i)).eIsSet(NotiondiagramPackage.ATTRIBUTE_RELATION__SOURCE_ID) && ((AttributeRelationImpl) attributeRelations.get(i)).eIsSet(NotiondiagramPackage.ATTRIBUTE_RELATION__TARGET_ID)){
				if (null == rmh.getNotionByVertexID(attributeRelations.get(i).getTargetId()) || null == rmh.getNotionByVertexID(attributeRelations.get(i).getSourceId())){
					continue;
				}
				boolean contains=false;
				for(NotionDTO not:rmh.getNotionByVertexID(attributeRelations.get(i).getTargetId()).getNotionDTOAttributeList())
					if (attributeRelations.get(i).getSourceId()==((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion) not).getId()){
						contains=true;
						break;
					}
				if (!contains){
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(attributeRelations.get(i));
					final int j=i;
					if(editingDomain != null){
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								attributeRelations.remove(j);
							}
							
							@Override
							public boolean canUndo() {
								return false;
							}
							
						});
						i--;
					}
				}
			}
		}
		for (int id:notions.keySet()) for(NotionDTO attr:rmh.getNotionByVertexID(id).getNotionDTOAttributeList()) if (notions.containsKey(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)attr).getId())){
			boolean contains=false;
			for(final AttributeRelation arel:attributeRelations) if (arel.getTargetId()==id && arel.getSourceId()==((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)attr).getId()){
				if(arel.getSource() == null){
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
					if(editingDomain != null){
						final Notion source = notions.get(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)attr).getId());
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								arel.setSource(source);
							}
							
							@Override
							public boolean canUndo() {
								return false;
							}
							
						});
					}
				}
				if(arel.getTarget() == null){
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
					if(editingDomain != null){
						final Notion target = notions.get(id);
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								arel.setTarget(target);
							}
							
							@Override
							public boolean canUndo() {
								return false;
							}
							
						});
					}
				}
				contains=true;
				break;
			}
			if (!contains){
				AttributeRelation arel = new AttributeRelationImpl();
				arel.setTarget(notions.get(id));
				arel.setSource(notions.get(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)attr).getId()));
				final AttributeRelation a = arel;
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							attributeRelations.add(a);
						}
						
						@Override
						public boolean canUndo() {
							return false;
						}
						
					});
				}
			}
		}
		return attributeRelations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Generalization> getGeneralizations() {
		if (generalizations == null) {
			generalizations = new EObjectContainmentEList<Generalization>(Generalization.class, this, NotiondiagramPackage.NOTION_DIAGRAM__GENERALIZATIONS);
		}
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource())) || RecoveryManagerHelper.isDeleteUndoRedoActionOccur()) return generalizations;
		Map<Integer,Notion> notions = new HashMap<Integer,Notion>();
		if (null!=this.notions) for (Notion not:getNotions()) notions.put(not.getId(),not);
		for(int i=0; i<generalizations.size(); i++){
			if(((GeneralizationImpl) generalizations.get(i)).eIsSet(NotiondiagramPackage.GENERALIZATION__ID) && null == rmh.getSpecialisationByVertexID(generalizations.get(i).getId())){
				final int j = i;
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(generalizations.get(i));
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							generalizations.remove(j);
						}
						
						@Override
						public boolean canUndo() {
							return false;
						}
						
					});
					i--;
				}
			}
		}
		for (NotionSpecialisation spec:rmh.getBussinessLayerFacade().getNotionSpecialisationVertices()){ 
			if (null != ((NotionSpecialisationDTO)spec).getGeneralNotion() && notions.containsKey(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)((NotionSpecialisationDTO)spec).getGeneralNotion()).getId()) 
					&& null != ((NotionSpecialisationDTO)spec).getSpecialisedNotion() && notions.containsKey(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)((NotionSpecialisationDTO)spec).getSpecialisedNotion()).getId())){
				Generalization gen = new GeneralizationImpl();
				gen.setSource(notions.get(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)((NotionSpecialisationDTO)spec).getSpecialisedNotion()).getId()));
				gen.setTarget(notions.get(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion)((NotionSpecialisationDTO)spec).getGeneralNotion()).getId()));
				gen.setId(spec.getId());
				boolean contains = false;
				for (final Generalization g: generalizations) if (g.getId()==gen.getId()){
					contains =true;
					if(g.getSource() == null || g.getSource().getId()!=gen.getSource().getId()){
						TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
						if(editingDomain != null){
							final Notion source = gen.getSource();
							editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

								@Override
								protected void doExecute() {
									g.setSource(source);
								}
								
								@Override
								public boolean canUndo() {
									return false;
								}
								
							});
						}
					}
					if(g.getTarget() == null || g.getTarget().getId()!=gen.getTarget().getId()){
						TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
						if(editingDomain != null){
							final Notion target = gen.getTarget();
							editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

								@Override
								protected void doExecute() {
									g.setTarget(target);
								}
								
								@Override
								public boolean canUndo() {
									return false;
								}
								
							});
						}
					}
					break;
				}
				if(!contains){
					final Generalization g = gen;
					TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);
					if(editingDomain != null){
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								generalizations.add(g);
							}
							
							@Override
							public boolean canUndo() {
								return false;
							}
							
						});
					}
				}
			}
		}
		return generalizations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Notion> getNotions() {
		if (notions == null) {
			notions = new EObjectContainmentEList<Notion>(Notion.class, this, NotiondiagramPackage.NOTION_DIAGRAM__NOTIONS);
		}
		RecoveryModelHelper rmh = null;
		if (null==eResource() || null==(rmh=RecoveryModelHelper.instance(eResource())) || RecoveryManagerHelper.isDeleteUndoRedoActionOccur()) return notions;
		for(int i=0; i<notions.size(); i++){
			if(null == rmh.getNotionByVertexID(notions.get(i).getId())){
				final int j = i;
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(notions.get(i));
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							notions.remove(j);
						}
						
						@Override
						public boolean canUndo() {
							return false;
						}
						
					});
					i--;
				}
			}
		}
		return notions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setName(String newName) {
		if (null!=newName && newName.isEmpty()) return;
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotiondiagramPackage.NOTION_DIAGRAM__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPackage() {
		return package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setPackage(int newPackage) {
		RecoveryModelHelper rmh = null;
		if (null!=(rmh=RecoveryModelHelper.instance(eResource())) && null==rmh.getNotionsPackageByVertexID(newPackage)) return;
		int oldPackage = package_;
		package_ = newPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NotiondiagramPackage.NOTION_DIAGRAM__PACKAGE, oldPackage, package_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NotiondiagramPackage.NOTION_DIAGRAM__RELATIONS:
				return ((InternalEList<?>)getRelations()).basicRemove(otherEnd, msgs);
			case NotiondiagramPackage.NOTION_DIAGRAM__ATTRIBUTE_RELATIONS:
				return ((InternalEList<?>)getAttributeRelations()).basicRemove(otherEnd, msgs);
			case NotiondiagramPackage.NOTION_DIAGRAM__GENERALIZATIONS:
				return ((InternalEList<?>)getGeneralizations()).basicRemove(otherEnd, msgs);
			case NotiondiagramPackage.NOTION_DIAGRAM__NOTIONS:
				return ((InternalEList<?>)getNotions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NotiondiagramPackage.NOTION_DIAGRAM__RELATIONS:
				return getRelations();
			case NotiondiagramPackage.NOTION_DIAGRAM__ATTRIBUTE_RELATIONS:
				return getAttributeRelations();
			case NotiondiagramPackage.NOTION_DIAGRAM__GENERALIZATIONS:
				return getGeneralizations();
			case NotiondiagramPackage.NOTION_DIAGRAM__NOTIONS:
				return getNotions();
			case NotiondiagramPackage.NOTION_DIAGRAM__NAME:
				return getName();
			case NotiondiagramPackage.NOTION_DIAGRAM__PACKAGE:
				return getPackage();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NotiondiagramPackage.NOTION_DIAGRAM__RELATIONS:
				getRelations().clear();
				getRelations().addAll((Collection<? extends Relation>)newValue);
				return;
			case NotiondiagramPackage.NOTION_DIAGRAM__ATTRIBUTE_RELATIONS:
				getAttributeRelations().clear();
				getAttributeRelations().addAll((Collection<? extends AttributeRelation>)newValue);
				return;
			case NotiondiagramPackage.NOTION_DIAGRAM__GENERALIZATIONS:
				getGeneralizations().clear();
				getGeneralizations().addAll((Collection<? extends Generalization>)newValue);
				return;
			case NotiondiagramPackage.NOTION_DIAGRAM__NOTIONS:
				getNotions().clear();
				getNotions().addAll((Collection<? extends Notion>)newValue);
				return;
			case NotiondiagramPackage.NOTION_DIAGRAM__NAME:
				setName((String)newValue);
				return;
			case NotiondiagramPackage.NOTION_DIAGRAM__PACKAGE:
				setPackage((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case NotiondiagramPackage.NOTION_DIAGRAM__RELATIONS:
				getRelations().clear();
				return;
			case NotiondiagramPackage.NOTION_DIAGRAM__ATTRIBUTE_RELATIONS:
				getAttributeRelations().clear();
				return;
			case NotiondiagramPackage.NOTION_DIAGRAM__GENERALIZATIONS:
				getGeneralizations().clear();
				return;
			case NotiondiagramPackage.NOTION_DIAGRAM__NOTIONS:
				getNotions().clear();
				return;
			case NotiondiagramPackage.NOTION_DIAGRAM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case NotiondiagramPackage.NOTION_DIAGRAM__PACKAGE:
				setPackage(PACKAGE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NotiondiagramPackage.NOTION_DIAGRAM__RELATIONS:
				return notions != null && !notions.isEmpty() && !getRelations().isEmpty();
			case NotiondiagramPackage.NOTION_DIAGRAM__ATTRIBUTE_RELATIONS:
				return notions != null && !notions.isEmpty() && !getAttributeRelations().isEmpty();
			case NotiondiagramPackage.NOTION_DIAGRAM__GENERALIZATIONS:
				return notions != null && !notions.isEmpty() && !getGeneralizations().isEmpty();
			case NotiondiagramPackage.NOTION_DIAGRAM__NOTIONS:
				return notions != null && !notions.isEmpty();
			case NotiondiagramPackage.NOTION_DIAGRAM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case NotiondiagramPackage.NOTION_DIAGRAM__PACKAGE:
				return package_ != PACKAGE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Name: ");
		result.append(name);
		result.append(", Package: ");
		result.append(package_);
		result.append(')');
		return result.toString();
	}

	@Override
	public List<GraphElement> getGraphElements() {
		List<GraphElement> list = new ArrayList<GraphElement>();
		RecoveryModelHelper rmh = null;
		if (null!=(rmh=RecoveryModelHelper.instance(eResource()))){
			GraphElement elem=null;
			for (Notion el: notions){
				elem=(GraphElement)rmh.getNotionByVertexID(el.getId());
				if (null!=elem) list.add(elem);
			}
			for (Relation el: relations){
				elem=(GraphElement) rmh.getDomainElementRelationshipByVertexID(el.getId());
				if (null!=elem) list.add(elem);
			}
			for (Generalization el: generalizations){
				elem=(GraphElement) rmh.getSpecialisationByVertexID(el.getId());
				if (null!=elem) list.add(elem);
			}
		}
		for (GraphElement ge:deletedItems){
			list.add(ge);
		}
		return list;
	}

} //NotionDiagramImpl
