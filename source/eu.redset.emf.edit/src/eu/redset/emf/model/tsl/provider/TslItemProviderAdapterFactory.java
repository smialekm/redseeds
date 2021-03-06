/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.provider;

import eu.redset.emf.model.tsl.util.TslAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TslItemProviderAdapterFactory extends TslAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TslItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.TestSpecification} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestSpecificationItemProvider testSpecificationItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.TestSpecification}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestSpecificationAdapter() {
		if (testSpecificationItemProvider == null) {
			testSpecificationItemProvider = new TestSpecificationItemProvider(this);
		}

		return testSpecificationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.TestPackage} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestPackageItemProvider testPackageItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.TestPackage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestPackageAdapter() {
		if (testPackageItemProvider == null) {
			testPackageItemProvider = new TestPackageItemProvider(this);
		}

		return testPackageItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.Test} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestItemProvider testItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.Test}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestAdapter() {
		if (testItemProvider == null) {
			testItemProvider = new TestItemProvider(this);
		}

		return testItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.UseCaseTest} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UseCaseTestItemProvider useCaseTestItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.UseCaseTest}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUseCaseTestAdapter() {
		if (useCaseTestItemProvider == null) {
			useCaseTestItemProvider = new UseCaseTestItemProvider(this);
		}

		return useCaseTestItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.UseCaseTestScenario} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UseCaseTestScenarioItemProvider useCaseTestScenarioItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.UseCaseTestScenario}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUseCaseTestScenarioAdapter() {
		if (useCaseTestScenarioItemProvider == null) {
			useCaseTestScenarioItemProvider = new UseCaseTestScenarioItemProvider(this);
		}

		return useCaseTestScenarioItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UseCaseTestScenarioSentenceItemProvider useCaseTestScenarioSentenceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUseCaseTestScenarioSentenceAdapter() {
		if (useCaseTestScenarioSentenceItemProvider == null) {
			useCaseTestScenarioSentenceItemProvider = new UseCaseTestScenarioSentenceItemProvider(this);
		}

		return useCaseTestScenarioSentenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.TestScenario} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestScenarioItemProvider testScenarioItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.TestScenario}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestScenarioAdapter() {
		if (testScenarioItemProvider == null) {
			testScenarioItemProvider = new TestScenarioItemProvider(this);
		}

		return testScenarioItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.TestCase} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCaseItemProvider testCaseItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.TestCase}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestCaseAdapter() {
		if (testCaseItemProvider == null) {
			testCaseItemProvider = new TestCaseItemProvider(this);
		}

		return testCaseItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.TestCaseSentence} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCaseSentenceItemProvider testCaseSentenceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.TestCaseSentence}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestCaseSentenceAdapter() {
		if (testCaseSentenceItemProvider == null) {
			testCaseSentenceItemProvider = new TestCaseSentenceItemProvider(this);
		}

		return testCaseSentenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UseCaseTestScenarioInstanceItemProvider useCaseTestScenarioInstanceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUseCaseTestScenarioInstanceAdapter() {
		if (useCaseTestScenarioInstanceItemProvider == null) {
			useCaseTestScenarioInstanceItemProvider = new UseCaseTestScenarioInstanceItemProvider(this);
		}

		return useCaseTestScenarioInstanceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.SVOSentence} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SVOSentenceItemProvider svoSentenceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.SVOSentence}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSVOSentenceAdapter() {
		if (svoSentenceItemProvider == null) {
			svoSentenceItemProvider = new SVOSentenceItemProvider(this);
		}

		return svoSentenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.ControlSentence} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControlSentenceItemProvider controlSentenceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.ControlSentence}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createControlSentenceAdapter() {
		if (controlSentenceItemProvider == null) {
			controlSentenceItemProvider = new ControlSentenceItemProvider(this);
		}

		return controlSentenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.ConditionSentence} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionSentenceItemProvider conditionSentenceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.ConditionSentence}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createConditionSentenceAdapter() {
		if (conditionSentenceItemProvider == null) {
			conditionSentenceItemProvider = new ConditionSentenceItemProvider(this);
		}

		return conditionSentenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.Precondition} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PreconditionItemProvider preconditionItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.Precondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPreconditionAdapter() {
		if (preconditionItemProvider == null) {
			preconditionItemProvider = new PreconditionItemProvider(this);
		}

		return preconditionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.Postcondition} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PostconditionItemProvider postconditionItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.Postcondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPostconditionAdapter() {
		if (postconditionItemProvider == null) {
			postconditionItemProvider = new PostconditionItemProvider(this);
		}

		return postconditionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.Condition} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionItemProvider conditionItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.Condition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createConditionAdapter() {
		if (conditionItemProvider == null) {
			conditionItemProvider = new ConditionItemProvider(this);
		}

		return conditionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.TestRelationship} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestRelationshipItemProvider testRelationshipItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.TestRelationship}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestRelationshipAdapter() {
		if (testRelationshipItemProvider == null) {
			testRelationshipItemProvider = new TestRelationshipItemProvider(this);
		}

		return testRelationshipItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.TestInvocationRelationship} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestInvocationRelationshipItemProvider testInvocationRelationshipItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.TestInvocationRelationship}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestInvocationRelationshipAdapter() {
		if (testInvocationRelationshipItemProvider == null) {
			testInvocationRelationshipItemProvider = new TestInvocationRelationshipItemProvider(this);
		}

		return testInvocationRelationshipItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.DomainObject} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DomainObjectItemProvider domainObjectItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.DomainObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDomainObjectAdapter() {
		if (domainObjectItemProvider == null) {
			domainObjectItemProvider = new DomainObjectItemProvider(this);
		}

		return domainObjectItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.NotionAttribute} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NotionAttributeItemProvider notionAttributeItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.NotionAttribute}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNotionAttributeAdapter() {
		if (notionAttributeItemProvider == null) {
			notionAttributeItemProvider = new NotionAttributeItemProvider(this);
		}

		return notionAttributeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.NFTest} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NFTestItemProvider nfTestItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.NFTest}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNFTestAdapter() {
		if (nfTestItemProvider == null) {
			nfTestItemProvider = new NFTestItemProvider(this);
		}

		return nfTestItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.GUITest} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GUITestItemProvider guiTestItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.GUITest}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createGUITestAdapter() {
		if (guiTestItemProvider == null) {
			guiTestItemProvider = new GUITestItemProvider(this);
		}

		return guiTestItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.BLTest} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BLTestItemProvider blTestItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.BLTest}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBLTestAdapter() {
		if (blTestItemProvider == null) {
			blTestItemProvider = new BLTestItemProvider(this);
		}

		return blTestItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.Notion} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NotionItemProvider notionItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.Notion}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNotionAdapter() {
		if (notionItemProvider == null) {
			notionItemProvider = new NotionItemProvider(this);
		}

		return notionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.DomainStatement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DomainStatementItemProvider domainStatementItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.DomainStatement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDomainStatementAdapter() {
		if (domainStatementItemProvider == null) {
			domainStatementItemProvider = new DomainStatementItemProvider(this);
		}

		return domainStatementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.CCondition} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CConditionItemProvider cConditionItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.CCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCConditionAdapter() {
		if (cConditionItemProvider == null) {
			cConditionItemProvider = new CConditionItemProvider(this);
		}

		return cConditionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.TestValue} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestValueItemProvider testValueItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.TestValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestValueAdapter() {
		if (testValueItemProvider == null) {
			testValueItemProvider = new TestValueItemProvider(this);
		}

		return testValueItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.TestParameter} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestParameterItemProvider testParameterItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.TestParameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestParameterAdapter() {
		if (testParameterItemProvider == null) {
			testParameterItemProvider = new TestParameterItemProvider(this);
		}

		return testParameterItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.TestInstance} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestInstanceItemProvider testInstanceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.TestInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestInstanceAdapter() {
		if (testInstanceItemProvider == null) {
			testInstanceItemProvider = new TestInstanceItemProvider(this);
		}

		return testInstanceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.NFTestInstance} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NFTestInstanceItemProvider nfTestInstanceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.NFTestInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNFTestInstanceAdapter() {
		if (nfTestInstanceItemProvider == null) {
			nfTestInstanceItemProvider = new NFTestInstanceItemProvider(this);
		}

		return nfTestInstanceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.GUITestInstance} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GUITestInstanceItemProvider guiTestInstanceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.GUITestInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createGUITestInstanceAdapter() {
		if (guiTestInstanceItemProvider == null) {
			guiTestInstanceItemProvider = new GUITestInstanceItemProvider(this);
		}

		return guiTestInstanceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.BLTestInstance} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BLTestInstanceItemProvider blTestInstanceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.BLTestInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBLTestInstanceAdapter() {
		if (blTestInstanceItemProvider == null) {
			blTestInstanceItemProvider = new BLTestInstanceItemProvider(this);
		}

		return blTestInstanceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.redset.emf.model.tsl.TestDataValuesContext} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestDataValuesContextItemProvider testDataValuesContextItemProvider;

	/**
	 * This creates an adapter for a {@link eu.redset.emf.model.tsl.TestDataValuesContext}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTestDataValuesContextAdapter() {
		if (testDataValuesContextItemProvider == null) {
			testDataValuesContextItemProvider = new TestDataValuesContextItemProvider(this);
		}

		return testDataValuesContextItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (testSpecificationItemProvider != null) testSpecificationItemProvider.dispose();
		if (testPackageItemProvider != null) testPackageItemProvider.dispose();
		if (testItemProvider != null) testItemProvider.dispose();
		if (useCaseTestItemProvider != null) useCaseTestItemProvider.dispose();
		if (useCaseTestScenarioItemProvider != null) useCaseTestScenarioItemProvider.dispose();
		if (useCaseTestScenarioSentenceItemProvider != null) useCaseTestScenarioSentenceItemProvider.dispose();
		if (testScenarioItemProvider != null) testScenarioItemProvider.dispose();
		if (testCaseItemProvider != null) testCaseItemProvider.dispose();
		if (testCaseSentenceItemProvider != null) testCaseSentenceItemProvider.dispose();
		if (useCaseTestScenarioInstanceItemProvider != null) useCaseTestScenarioInstanceItemProvider.dispose();
		if (svoSentenceItemProvider != null) svoSentenceItemProvider.dispose();
		if (controlSentenceItemProvider != null) controlSentenceItemProvider.dispose();
		if (conditionSentenceItemProvider != null) conditionSentenceItemProvider.dispose();
		if (preconditionItemProvider != null) preconditionItemProvider.dispose();
		if (postconditionItemProvider != null) postconditionItemProvider.dispose();
		if (conditionItemProvider != null) conditionItemProvider.dispose();
		if (testRelationshipItemProvider != null) testRelationshipItemProvider.dispose();
		if (testInvocationRelationshipItemProvider != null) testInvocationRelationshipItemProvider.dispose();
		if (domainObjectItemProvider != null) domainObjectItemProvider.dispose();
		if (notionAttributeItemProvider != null) notionAttributeItemProvider.dispose();
		if (nfTestItemProvider != null) nfTestItemProvider.dispose();
		if (guiTestItemProvider != null) guiTestItemProvider.dispose();
		if (blTestItemProvider != null) blTestItemProvider.dispose();
		if (notionItemProvider != null) notionItemProvider.dispose();
		if (domainStatementItemProvider != null) domainStatementItemProvider.dispose();
		if (cConditionItemProvider != null) cConditionItemProvider.dispose();
		if (testValueItemProvider != null) testValueItemProvider.dispose();
		if (testParameterItemProvider != null) testParameterItemProvider.dispose();
		if (testInstanceItemProvider != null) testInstanceItemProvider.dispose();
		if (nfTestInstanceItemProvider != null) nfTestInstanceItemProvider.dispose();
		if (guiTestInstanceItemProvider != null) guiTestInstanceItemProvider.dispose();
		if (blTestInstanceItemProvider != null) blTestInstanceItemProvider.dispose();
		if (testDataValuesContextItemProvider != null) testDataValuesContextItemProvider.dispose();
	}

}
