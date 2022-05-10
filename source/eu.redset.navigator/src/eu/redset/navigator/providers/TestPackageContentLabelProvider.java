package eu.redset.navigator.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;


import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.DomainStatement;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.NFTest;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.navigator.IImageKeys;


public class TestPackageContentLabelProvider extends EventManager implements
		IProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof TestPackage) {
			TestPackage tp = (TestPackage) parentElement;
			int length = 0;
			Object[] packageChildren = tp.getEReference0().toArray();
			Object[] testChildren = tp.getEReference1().toArray();
			Object[] testScenarios = tp.getEReference3().toArray();
			Object[] notions = tp.getNotions().toArray();
			Object[] nfTests = tp.getNFTests().toArray();
			Object[] guiTests = tp.getGUITests().toArray();
			Object[] blTests = tp.getBLTests().toArray();
			Object[] children = new Object[packageChildren.length + testChildren.length + testScenarios.length+notions.length + nfTests.length + guiTests.length + blTests.length];
			
			System.arraycopy(packageChildren, 0, children, 0, packageChildren.length);
			length = packageChildren.length;
			System.arraycopy(testChildren, 0, children, length, testChildren.length);
			length = length + testChildren.length;
			System.arraycopy(testScenarios, 0, children, length, testScenarios.length);
			length = length + testScenarios.length;
			System.arraycopy(notions, 0, children, length, notions.length);
			length = length + notions.length;
			System.arraycopy(nfTests, 0, children, length, nfTests.length);
			length = length + nfTests.length;
			System.arraycopy(guiTests, 0, children, length, guiTests.length);
			length = length + guiTests.length;
			System.arraycopy(blTests, 0, children, length, blTests.length);
			
			
			return children;
		} else if (parentElement instanceof UseCaseTest) {
			UseCaseTest uct = (UseCaseTest)parentElement;
			Object[] children = uct.getEReference0().toArray();
			
			return children;
		} else if (parentElement instanceof TestScenario) {
			TestScenario ts = (TestScenario)parentElement;
			Object[] children = ts.getEReference1().toArray(); 
			return children;
		} else if (parentElement instanceof TestCase) {
			TestCase tc = (TestCase)parentElement;
			List tcsList = tc.getSentences();
			ArrayList tcChildren = new ArrayList();
			for (int i=0; i<tcsList.size(); i++){
				TestCaseSentence tcs = (TestCaseSentence)tcsList.get(i);
				if (tcs.getScenarioSentence() instanceof ControlSentence) {
					ControlSentence cs = (ControlSentence)tcs.getScenarioSentence();
					for (int j=0; j<cs.getAttachedTests().size(); j++){
						if (cs.getAttachedTests().get(j) instanceof TestCase){
							tcChildren.add((TestCase)cs.getAttachedTests().get(j));
						}
					}
				}
			}
			return tcChildren.toArray();
		} else if (parentElement instanceof Notion) {
			Notion not = (Notion)parentElement;
			Object[] dsList = not.getDomainStatements().toArray();
			return dsList;
		} 
		
		
		
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof TestPackage) {
			TestPackage ts = (TestPackage) element;
			return SCProjectContainer.instance().getSCProject(ts).getEclipseProject();
		}
		
		if (element instanceof UseCaseTest) {
			UseCaseTest ts = (UseCaseTest) element;
			return SCProjectContainer.instance().getSCProject(ts).getEclipseProject();
		}
		
		if (element instanceof UseCaseTestScenario) {
			UseCaseTestScenario ucts = (UseCaseTestScenario) element;
			return SCProjectContainer.instance().getSCProject(ucts).getEclipseProject();
		}
		
		if (element instanceof TestScenario) {
			TestScenario ts = (TestScenario) element;
			return SCProjectContainer.instance().getSCProject(ts).getEclipseProject();
		}
		
		if (element instanceof TestCase) {
			TestCase tc = (TestCase) element;
			return SCProjectContainer.instance().getSCProject(tc).getEclipseProject();
		}
		
		if (element instanceof Notion) {
			Notion not = (Notion) element;
			return SCProjectContainer.instance().getSCProject(not).getEclipseProject();
		}
		
		if (element instanceof DomainStatement) {
			DomainStatement ds = (DomainStatement) element;
			return SCProjectContainer.instance().getSCProject(ds).getEclipseProject();
		}
		
		if (element instanceof NFTest) {
			NFTest nft = (NFTest) element;
			return SCProjectContainer.instance().getSCProject(nft).getEclipseProject();
		}
		
		if (element instanceof GUITest) {
			GUITest guit = (GUITest) element;
			return SCProjectContainer.instance().getSCProject(guit).getEclipseProject();
		}
		
		if (element instanceof BLTest) {
			BLTest blt = (BLTest) element;
			return SCProjectContainer.instance().getSCProject(blt).getEclipseProject();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof TestPackage){
			TestPackage ts = (TestPackage) element;
			int sum = 0;
			sum = ts.getEReference0().size();
			sum = sum + ts.getEReference1().size() + ts.getEReference3().size() + ts.getNotions().size() + ts.getNFTests().size() + ts.getGUITests().size() + ts.getBLTests().size();
			if (sum>0) {
				return true;
			}
		} else if (element instanceof UseCaseTest){
			UseCaseTest uct = (UseCaseTest) element;
			if (uct.getEReference0().size()>0)
				return true;
			return false;
		} else if (element instanceof TestScenario){
			TestScenario ts = (TestScenario) element;
			if (ts.getEReference1().size()>0)
				return true;
			return false;
		} else if (element instanceof Notion){
			Notion not = (Notion) element;
			if (not.getDomainStatements().size()>0)
				return true;
			return false;
		} else if (element instanceof TestCase){
			TestCase tc = (TestCase) element;
			if (tc.getSentences().size()>0)
				return true;
			return false;
		} 
		
		return false;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	//---------------
	//label provider
	//---------------

	@Override
	public String getText(Object element) {
		if (element instanceof TestPackage) {
			TestPackage parent = (TestPackage) element;
			return parent.getName();
		} else if (element instanceof UseCaseTest){
			UseCaseTest parent = (UseCaseTest) element;
			return parent.getName();
		} else if (element instanceof UseCaseTestScenario){
			UseCaseTestScenario parent = (UseCaseTestScenario)element;
			return parent.getName();
		} else if (element instanceof TestScenario){
			TestScenario parent = (TestScenario)element;
			return parent.getName();
		} else if (element instanceof TestCase){
			TestCase parent = (TestCase)element;
			return parent.getOrderNumberGlobal()+". "+parent.getUcName()+" - "+parent.getName();
		} else if (element instanceof Notion){
			Notion parent = (Notion)element;
			return parent.getNotionName();
		} else if (element instanceof DomainStatement){
			DomainStatement parent = (DomainStatement)element;
			return parent.getPhraseName();
		} else if (element instanceof NFTest){
			NFTest parent = (NFTest)element;
			return parent.getName();
		} else if (element instanceof GUITest){
			GUITest parent = (GUITest)element;
			return parent.getName();
		} else if (element instanceof BLTest){
			BLTest parent = (BLTest)element;
			return parent.getName();
		}
		else {
			return "";
		}
	}

	@Override
	public Image getImage(Object obj) {
		if (obj instanceof TestSpecification) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_FOLDER);
		} else if (obj instanceof TestPackage) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_FOLDER);
		} else if (obj instanceof UseCaseTest) {
			return ImageCache.getImage(IImageKeys.USE_CASE_TEST);
		} else if (obj instanceof UseCaseTestScenario) {
			return ImageCache.getImage(IImageKeys.USE_CASE_TEST_SCENARIO);
		} else if (obj instanceof TestScenario) {
			return ImageCache.getImage(IImageKeys.TEST_SCENARIO);
		} else if (obj instanceof TestCase) {
			return ImageCache.getImage(IImageKeys.TEST_CASE);
		} else if (obj instanceof Notion) {
			return ImageCache.getImage(IImageKeys.NOTION);
		} else if (obj instanceof DomainStatement) {
			return ImageCache.getImage(IImageKeys.DOMAIN_STATEMENT);
		} else if (obj instanceof GUITest) {
			return ImageCache.getImage(IImageKeys.TEST);
		} else if (obj instanceof BLTest) {
			return ImageCache.getImage(IImageKeys.TEST);
		} else if (obj instanceof NFTest) {
			return ImageCache.getImage(IImageKeys.TEST);
		}
		return PlatformUI.getWorkbench().getSharedImages().getImage(
				ISharedImages.IMG_OBJ_ELEMENT);
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		addListenerObject(listener);
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		removeListenerObject(listener);

	}

	/* (non-Javadoc)
	 * return true
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return true;
	}

}
